# Pagination Implementation

## Previous Implementation

The original API returned **all matching recipes at once** with no pagination support.

### Endpoints

#### `POST /api/recipe/filter`
- **Body**: `FilterDTO` (plain JSON with filter criteria)
- **Response**: `List<RecipeCardDTO>` — the entire result set, no matter how large
- **Privacy**: Filtered in-memory after fetching (null entries for non-public recipes), meaning the database always loaded everything

```json
// Request
{ "cuisine": 3, "tags": [1, 5] }

// Response — all results in a flat array
[
  { "id": 1, "title": "...", ... },
  { "id": 2, "title": "...", ... },
  ...
]
```

#### `GET /api/user/savedRecipes`
- **Response**: `List<RecipeCardDTO>` — loaded the user's entire saved recipes set via JPA relationship into memory

#### `GET /api/user/{userID}` (via UserDTO)
- Returned user data which could include recipe lists, but no paginated endpoint for recipes by user existed

### Problems
1. **No pagination = all data in one response** — unsustainable for an infinite-scroller UI
2. **Privacy filter applied after DB fetch** — the stream filtered out non-public recipes with `null`, but the DB still loaded them
3. **No dedicated endpoint for recipes by user**
4. **`GET /api/user/savedRecipes` loaded the entire relationship collection** — no DB-level limit

---

## New Implementation

Three endpoints now return **Spring Data `Page<RecipeCardDTO>`** with configurable page/size and ordering by `creationDate DESC` (most recent first).

### New Files

#### `DTO/PaginationDTO.java`
```java
public record PaginationDTO(Integer page, Integer size) {
    public PaginationDTO {
        if (page == null || page < 0) page = 0;
        if (size == null || size < 1) size = 20;
    }
}
```
A reusable record. Defaults to page 0, size 20 when fields are null/invalid.

#### `DTO/RecipeFilterRequest.java`
```java
public class RecipeFilterRequest {
    private FilterDTO filter;
    private PaginationDTO pagination;
    // getters + setters
}
```
Wrapper that keeps `FilterDTO` unchanged and adds separate pagination.

### Modified Files

#### `repository/RecipeRepository.java`
Two new JPQL query methods added:

```java
@Query("SELECT r FROM Recipe r WHERE r.author.id = :userId")
Page<Recipe> findByAuthorId(@Param("userId") Integer userId, Pageable pageable);

@Query(value = "SELECT r FROM User u JOIN u.savedRecipes r WHERE u.id = :userId",
       countQuery = "SELECT COUNT(r) FROM User u JOIN u.savedRecipes r WHERE u.id = :userId")
Page<Recipe> findSavedRecipesByUserId(@Param("userId") Integer userId, Pageable pageable);
```
Both return `Page<Recipe>` so pagination happens at the database level (SQL `LIMIT`/`OFFSET` + count query).

#### `service/RecipeService.java`
- `findByFilter(FilterDTO)` → replaced with `findByFilter(Specification<Recipe>, Pageable)` returning `Page<Recipe>`
- Added `findSavedRecipesByUserId(Integer userId, Pageable)` → `Page<Recipe>`
- Added `findByAuthorId(Integer userId, Pageable)` → `Page<Recipe>`

#### `controllers/RecipeController.java`

**`POST /api/recipe/filter`** (modified):
- Accepts `RecipeFilterRequest` body instead of plain `FilterDTO`
- Builds `Pageable` from `PaginationDTO` + `Sort.by(DESC, "creationDate")`
- Privacy spec moved into `Specification` — `isPublic = true OR author = loggedUser` — so the database handles both filtering and pagination correctly
- Returns `Page<RecipeCardDTO>`

**`POST /api/recipe/byUser/{userId}`** (new):
- Accepts optional `PaginationDTO` body
- Same privacy + author filter combined into a single `Specification`
- Returns `Page<RecipeCardDTO>`

#### `controllers/UserController.java`

**`POST /api/user/savedRecipes`** (changed from `GET`):
- Accepts optional `PaginationDTO` body
- Uses `recipeService.findSavedRecipesByUserId()` for DB-level pagination
- Returns `Page<RecipeCardDTO>` with `isSaved = true`
- Changed to `POST` because the endpoint now accepts a request body

### API Contract

```
POST /api/recipe/filter
Body: { "filter": { "cuisine": 3, "tags": [1] }, "pagination": { "page": 0, "size": 20 } }
→ Page<RecipeCardDTO>

POST /api/recipe/byUser/5
Body: { "page": 0, "size": 20 }   (optional, defaults to 0/20)
→ Page<RecipeCardDTO>

POST /api/user/savedRecipes
Body: { "page": 0, "size": 20 }   (optional, defaults to 0/20)
→ Page<RecipeCardDTO>
```

### Response Format (all endpoints)

```json
{
  "content": [
    { "id": 1, "title": "...", "tags": [...], ... },
    { "id": 2, "title": "...", ... }
  ],
  "totalPages": 5,
  "totalElements": 48,
  "last": false,
  "first": true,
  "number": 0,
  "size": 20,
  "numberOfElements": 20,
  "empty": false
}
```

### Infinite Scroll Usage

1. First request: `page: 0, size: 20`
2. Check `last` in response — if `true`, no more pages
3. Next request: `page: 1, size: 20`
4. Repeat until `last: true`

### Key Design Decisions

- **Privacy filter in `Specification`**: Previously done in-memory (after DB fetch), which would break pagination counts. Now part of the JPA `Specification` so the DB returns correct counts.
- **`FilterDTO` unchanged**: Kept clean by wrapping it in `RecipeFilterRequest` alongside `PaginationDTO`.
- **`PaginationDTO` reusable**: Can be used by any future endpoint that needs pagination.
- **All sorting `DESC` by `creationDate`**: For a Twitter-like feed, newest content first.
