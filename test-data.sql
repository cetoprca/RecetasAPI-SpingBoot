-- MySQL Script to seed the RecetasAPI database
-- Complete test data: 20 images, 20 cuisines, 30 tags, 50 ingredients,
-- 10 users, 100 recipes, ~400 steps, ~400 ratings, saved recipes, and follow relations
drop database recetasapi;
create database recetasapi;
USE recetasapi;
SET FOREIGN_KEY_CHECKS = 0;

-- =============================================
-- 1. IMAGES (profile pictures and banners only)
-- =============================================
INSERT INTO image (url) VALUES
('Alice.png'), ('Bob.png'), ('Charlie.png'), ('Diana.png'),
('Eve.png'), ('Frank.png'), ('Grace.png'), ('Henry.png'),
('Ivy.png'), ('Jack.png'),
('AliceBanner.png'), ('BobBanner.png'), ('CharlieBanner.png'),
('DianaBanner.png'), ('EveBanner.png'), ('FrankBanner.png'),
('GraceBanner.png'), ('HenryBanner.png'), ('IvyBanner.png'),
('JackBanner.png');

-- =============================================
-- 2. CUISINES (20 cuisines)
-- =============================================
INSERT INTO cuisine (id, name) VALUES
(1, 'Italian'), (2, 'Mexican'), (3, 'Japanese'),
(4, 'Indian'), (5, 'French'), (6, 'American'),
(7, 'Chinese'), (8, 'Thai'), (9, 'Spanish'),
(10, 'Greek'), (11, 'Korean'), (12, 'Lebanese'),
(13, 'Vietnamese'), (14, 'Brazilian'), (15, 'Moroccan'),
(16, 'Caribbean'), (17, 'Ethiopian'), (18, 'Peruvian'),
(19, 'Turkish'), (20, 'Portuguese');

-- =============================================
-- 3. TAGS (30 tags)
-- =============================================
INSERT INTO tag (id, name) VALUES
(1, 'Vegetarian'), (2, 'Quick'), (3, 'Dessert'),
(4, 'Spicy'), (5, 'Healthy'), (6, 'Gluten-Free'),
(7, 'Vegan'), (8, 'Breakfast'), (9, 'Main Course'),
(10, 'Easy'), (11, 'Appetizer'), (12, 'Soup'),
(13, 'Salad'), (14, 'Seafood'), (15, 'Meat'),
(16, 'Brunch'), (17, 'BBQ'), (18, 'Comfort Food'),
(19, 'Fried'), (20, 'Baked'), (21, 'One-Pot'),
(22, 'Party'), (23, 'Snack'), (24, 'Sauce'),
(25, 'Dairy-Free'), (26, 'Lunch'), (27, 'Dinner'),
(28, 'Holiday'), (29, 'Fermented'), (30, 'Raw');

-- =============================================
-- 4. INGREDIENTS (50 ingredients)
-- =============================================
INSERT INTO ingredient (id, name) VALUES
(1, 'Pasta'), (2, 'Tomato'), (3, 'Cheese'),
(4, 'Rice'), (5, 'Chicken'), (6, 'Fish'),
(7, 'Onion'), (8, 'Garlic'), (9, 'Olive Oil'),
(10, 'Flour'), (11, 'Eggs'), (12, 'Milk'),
(13, 'Chocolate'), (14, 'Sugar'), (15, 'Butter'),
(16, 'Peppers'), (17, 'Soy Sauce'), (18, 'Tofu'),
(19, 'Beans'), (20, 'Avocado'), (21, 'Lemon'),
(22, 'Basil'), (23, 'Salt'), (24, 'Pepper'),
(25, 'Vanilla'), (26, 'Cilantro'), (27, 'Ginger'),
(28, 'Coconut Milk'), (29, 'Cumin'), (30, 'Paprika'),
(31, 'Honey'), (32, 'Sesame Oil'), (33, 'Noodles'),
(34, 'Mushroom'), (35, 'Carrot'), (36, 'Potato'),
(37, 'Lime'), (38, 'Pineapple'), (39, 'Yogurt'),
(40, 'Cream Cheese'), (41, 'Cinnamon'), (42, 'Nutmeg'),
(43, 'Oregano'), (44, 'Thyme'), (45, 'Rosemary'),
(46, 'Bay Leaf'), (47, 'Olives'), (48, 'Capers'),
(49, 'Saffron'), (50, 'Curry Powder');

-- =============================================
-- 5. USERS (10 users)
-- =============================================
INSERT INTO user (id, display_name, password, biography, profile_image_url, banner_image_url) VALUES
('alice', 'Alice', '$2a$10$oHMVwMjWCVI.B9jDQnAJuuNZLq7riNn.6yKOBQzwu9vKBo53P.aGq',
 'Italian chef specializing in traditional recipes from Tuscany', 'Alice.png', 'AliceBanner.png'),
('bob', 'Bob', '$2a$10$oHMVwMjWCVI.B9jDQnAJuuNZLq7riNn.6yKOBQzwu9vKBo53P.aGq',
 'Mexican street food enthusiast and home cook', 'Bob.png', 'BobBanner.png'),
('charlie', 'Charlie', '$2a$10$oHMVwMjWCVI.B9jDQnAJuuNZLq7riNn.6yKOBQzwu9vKBo53P.aGq',
 'Japanese cuisine lover exploring ramen and sushi techniques', 'Charlie.png', 'CharlieBanner.png'),
('diana', 'Diana', '$2a$10$oHMVwMjWCVI.B9jDQnAJuuNZLq7riNn.6yKOBQzwu9vKBo53P.aGq',
 'Indian spice master creating authentic curries and biryanis', 'Diana.png', 'DianaBanner.png'),
('eve', 'Eve', '$2a$10$oHMVwMjWCVI.B9jDQnAJuuNZLq7riNn.6yKOBQzwu9vKBo53P.aGq',
 'French pastry chef with a passion for baking and patisserie', 'Eve.png', 'EveBanner.png'),
('frank', 'Frank', '$2a$10$oHMVwMjWCVI.B9jDQnAJuuNZLq7riNn.6yKOBQzwu9vKBo53P.aGq',
 'American BBQ pitmaster and grill expert from Texas', 'Frank.png', 'FrankBanner.png'),
('grace', 'Grace', '$2a$10$oHMVwMjWCVI.B9jDQnAJuuNZLq7riNn.6yKOBQzwu9vKBo53P.aGq',
 'Chinese wok master creating dim sum and traditional stir-fries', 'Grace.png', 'GraceBanner.png'),
('henry', 'Henry', '$2a$10$oHMVwMjWCVI.B9jDQnAJuuNZLq7riNn.6yKOBQzwu9vKBo53P.aGq',
 'Thai street food fan balancing sweet sour and spicy flavors', 'Henry.png', 'HenryBanner.png'),
('ivy', 'Ivy', '$2a$10$oHMVwMjWCVI.B9jDQnAJuuNZLq7riNn.6yKOBQzwu9vKBo53P.aGq',
 'Spanish tapas lover cooking paella and traditional pintxos', 'Ivy.png', 'IvyBanner.png'),
('jack', 'Jack', '$2a$10$oHMVwMjWCVI.B9jDQnAJuuNZLq7riNn.6yKOBQzwu9vKBo53P.aGq',
 'Greek Mediterranean chef focusing on fresh ingredients and olive oil', 'Jack.png', 'JackBanner.png');

-- =============================================
-- 6. RECIPES (100 recipes, 10 per user)
-- =============================================
INSERT INTO recipe (id, title, description, image_url, prep_time, cook_time, total_time, is_public, creation_date, user_id, cuisine_id) VALUES
-- Alice's Italian recipes (IDs 1-10, cuisine 1)
(1, 'Spaghetti Carbonara', 'Classic Roman pasta with eggs pecorino and guanciale', NULL, 15, 20, 35, TRUE, '2026-01-15', 'alice', 1),
(2, 'Margherita Pizza', 'Neapolitan pizza with San Marzano tomatoes and mozzarella', NULL, 30, 15, 45, TRUE, '2026-01-20', 'alice', 1),
(3, 'Penne Arrabbiata', 'Spicy pasta with garlic tomatoes and chili flakes', NULL, 10, 15, 25, TRUE, '2026-02-10', 'alice', 1),
(4, 'Caprese Salad', 'Fresh tomato mozzarella and basil salad with olive oil', NULL, 10, 0, 10, TRUE, '2026-03-01', 'alice', 1),
(5, 'Lasagna', 'Layered pasta with bolognese sauce béchamel and parmesan', NULL, 45, 45, 90, TRUE, '2026-03-15', 'alice', 1),
(6, 'Risotto alla Milanese', 'Creamy saffron risotto from Milan', NULL, 15, 30, 45, TRUE, '2026-04-01', 'alice', 1),
(7, 'Tiramisu', 'Coffee-flavored Italian dessert with mascarpone', NULL, 30, 0, 30, TRUE, '2026-04-15', 'alice', 1),
(8, 'Fettuccine Alfredo', 'Creamy pasta with butter and parmesan cheese', NULL, 15, 20, 35, TRUE, '2026-05-01', 'alice', 1),
(9, 'Bruschetta', 'Toasted bread topped with fresh tomatoes basil and garlic', NULL, 15, 5, 20, TRUE, '2026-05-10', 'alice', 1),
(10, 'Minestrone Soup', 'Hearty Italian vegetable soup with pasta and beans', NULL, 20, 40, 60, TRUE, '2026-05-20', 'alice', 1),
-- Bob's Mexican recipes (IDs 11-20, cuisine 2)
(11, 'Tacos al Pastor', 'Marinated pork tacos with pineapple and cilantro', NULL, 40, 20, 60, TRUE, '2026-02-01', 'bob', 2),
(12, 'Guacamole', 'Fresh avocado dip with lime cilantro and onion', NULL, 10, 0, 10, TRUE, '2026-02-15', 'bob', 2),
(13, 'Churros', 'Crispy fried dough with cinnamon sugar coating', NULL, 20, 20, 40, TRUE, '2026-03-05', 'bob', 2),
(14, 'Enchiladas Verdes', 'Corn tortillas filled with chicken and green salsa', NULL, 30, 25, 55, TRUE, '2026-03-20', 'bob', 2),
(15, 'Pozole', 'Traditional hominy soup with pork and red chili', NULL, 30, 90, 120, TRUE, '2026-04-05', 'bob', 2),
(16, 'Tres Leches Cake', 'Sponge cake soaked in three kinds of milk', NULL, 20, 30, 50, TRUE, '2026-04-20', 'bob', 2),
(17, 'Quesadillas', 'Crispy tortillas filled with melted cheese and peppers', NULL, 10, 10, 20, TRUE, '2026-05-05', 'bob', 2),
(18, 'Burritos', 'Large flour tortillas stuffed with beans rice and meat', NULL, 25, 20, 45, TRUE, '2026-05-15', 'bob', 2),
(19, 'Nachos Supreme', 'Crispy tortilla chips with cheese beans and salsa', NULL, 15, 10, 25, TRUE, '2026-05-25', 'bob', 2),
(20, 'Tamales', 'Corn dough stuffed with meat steamed in husks', NULL, 45, 60, 105, TRUE, '2026-06-01', 'bob', 2),
-- Charlie's Japanese recipes (IDs 21-30, cuisine 3)
(21, 'Sushi Rolls', 'Fresh salmon and avocado maki rolls', NULL, 45, 15, 60, TRUE, '2026-02-05', 'charlie', 3),
(22, 'Tonkotsu Ramen', 'Rich pork bone broth with noodles and soft egg', NULL, 30, 240, 270, TRUE, '2026-02-20', 'charlie', 3),
(23, 'Tempura', 'Light battered and deep-fried vegetables and shrimp', NULL, 20, 15, 35, TRUE, '2026-03-10', 'charlie', 3),
(24, 'Teriyaki Chicken', 'Grilled chicken glazed with sweet soy teriyaki sauce', NULL, 20, 25, 45, TRUE, '2026-03-25', 'charlie', 3),
(25, 'Miso Soup', 'Traditional soybean paste soup with tofu and seaweed', NULL, 10, 15, 25, TRUE, '2026-04-10', 'charlie', 3),
(26, 'Yakitori', 'Grilled chicken skewers basted with tare sauce', NULL, 20, 15, 35, TRUE, '2026-04-25', 'charlie', 3),
(27, 'Udon Noodles', 'Thick wheat noodles in hot broth with tempura', NULL, 15, 20, 35, TRUE, '2026-05-10', 'charlie', 3),
(28, 'Gyoza', 'Pan-fried dumplings filled with pork and cabbage', NULL, 30, 20, 50, TRUE, '2026-05-20', 'charlie', 3),
(29, 'Okonomiyaki', 'Savory Japanese pancake with cabbage and pork', NULL, 20, 25, 45, TRUE, '2026-06-05', 'charlie', 3),
(30, 'Matcha Ice Cream', 'Creamy green tea ice cream with authentic matcha', NULL, 15, 0, 15, TRUE, '2026-06-15', 'charlie', 3),
-- Diana's Indian recipes (IDs 31-40, cuisine 4)
(31, 'Chicken Tikka Masala', 'Marinated chicken in creamy spiced tomato sauce', NULL, 30, 30, 60, TRUE, '2026-03-01', 'diana', 4),
(32, 'Butter Chicken', 'Rich and creamy tomato-based chicken curry', NULL, 20, 35, 55, TRUE, '2026-03-15', 'diana', 4),
(33, 'Vegetable Biryani', 'Layered rice dish with spiced vegetables and saffron', NULL, 30, 45, 75, TRUE, '2026-04-01', 'diana', 4),
(34, 'Samosas', 'Crispy fried pastries filled with spiced potatoes', NULL, 30, 25, 55, TRUE, '2026-04-10', 'diana', 4),
(35, 'Garlic Naan', 'Soft leavened flatbread brushed with garlic butter', NULL, 60, 15, 75, TRUE, '2026-04-20', 'diana', 4),
(36, 'Dal Makhani', 'Slow-cooked black lentils with cream and butter', NULL, 15, 120, 135, TRUE, '2026-05-01', 'diana', 4),
(37, 'Palak Paneer', 'Cottage cheese in creamy spinach gravy', NULL, 20, 25, 45, TRUE, '2026-05-10', 'diana', 4),
(38, 'Rogan Josh', 'Kashmiri lamb curry with aromatic spices', NULL, 25, 60, 85, TRUE, '2026-05-20', 'diana', 4),
(39, 'Chicken Korma', 'Mild creamy curry with cashews and yogurt', NULL, 20, 35, 55, TRUE, '2026-06-01', 'diana', 4),
(40, 'Mango Lassi', 'Refreshing yogurt drink with sweet mango pulp', NULL, 5, 0, 5, TRUE, '2026-06-10', 'diana', 4),
-- Eve's French recipes (IDs 41-50, cuisine 5)
(41, 'French Onion Soup', 'Caramelized onion soup with melted gruyere crouton', NULL, 20, 50, 70, TRUE, '2026-03-05', 'eve', 5),
(42, 'Croissants', 'Buttery flaky laminated French pastry', NULL, 120, 20, 140, TRUE, '2026-03-20', 'eve', 5),
(43, 'Coq au Vin', 'Braised chicken in red wine with mushrooms and pearl onions', NULL, 30, 90, 120, TRUE, '2026-04-05', 'eve', 5),
(44, 'Creme Brulee', 'Rich vanilla custard with caramelized sugar top', NULL, 20, 50, 70, TRUE, '2026-04-15', 'eve', 5),
(45, 'Ratatouille', 'Provencal vegetable stew with herbs de Provence', NULL, 20, 40, 60, TRUE, '2026-04-25', 'eve', 5),
(46, 'Quiche Lorraine', 'Savory custard tart with bacon and gruyere', NULL, 25, 40, 65, TRUE, '2026-05-05', 'eve', 5),
(47, 'Bouillabaisse', 'Traditional Provencal fish stew with rouille', NULL, 30, 45, 75, TRUE, '2026-05-15', 'eve', 5),
(48, 'Macarons', 'Delicate almond meringue cookies with ganache filling', NULL, 30, 15, 45, TRUE, '2026-05-25', 'eve', 5),
(49, 'Beef Bourguignon', 'Slow braised beef in Burgundy wine with vegetables', NULL, 30, 150, 180, TRUE, '2026-06-05', 'eve', 5),
(50, 'Crepes Suzette', 'Thin pancakes in orange butter sauce flambeed', NULL, 15, 20, 35, TRUE, '2026-06-15', 'eve', 5),
-- Frank's American recipes (IDs 51-60, cuisine 6)
(51, 'BBQ Ribs', 'Slow-smoked pork ribs with tangy barbecue sauce', NULL, 30, 180, 210, TRUE, '2026-03-10', 'frank', 6),
(52, 'Classic Cheeseburger', 'Juicy beef patty with cheddar lettuce and tomato', NULL, 15, 15, 30, TRUE, '2026-03-25', 'frank', 6),
(53, 'Apple Pie', 'Classic American apple pie with flaky lattice crust', NULL, 30, 50, 80, TRUE, '2026-04-05', 'frank', 6),
(54, 'Mac and Cheese', 'Creamy baked macaroni with sharp cheddar crust', NULL, 15, 30, 45, TRUE, '2026-04-15', 'frank', 6),
(55, 'Fried Chicken', 'Crispy buttermilk soaked fried chicken pieces', NULL, 120, 25, 145, TRUE, '2026-04-25', 'frank', 6),
(56, 'Buffalo Wings', 'Crispy chicken wings tossed in spicy buffalo sauce', NULL, 15, 30, 45, TRUE, '2026-05-05', 'frank', 6),
(57, 'Clam Chowder', 'Creamy New England clam chowder with potatoes', NULL, 20, 40, 60, TRUE, '2026-05-15', 'frank', 6),
(58, 'Pancakes', 'Fluffy buttermilk pancakes with maple syrup', NULL, 10, 15, 25, TRUE, '2026-05-25', 'frank', 6),
(59, 'Cobb Salad', 'Loaded salad with chicken bacon egg and avocado', NULL, 20, 15, 35, TRUE, '2026-06-05', 'frank', 6),
(60, 'Chocolate Chip Cookies', 'Classic soft-baked cookies with chocolate chunks', NULL, 15, 12, 27, TRUE, '2026-06-15', 'frank', 6),
-- Grace's Chinese recipes (IDs 61-70, cuisine 7)
(61, 'Kung Pao Chicken', 'Spicy Sichuan stir-fry with peanuts and chili', NULL, 20, 15, 35, TRUE, '2026-03-15', 'grace', 7),
(62, 'Dim Sum', 'Steamed dumplings with pork and shrimp filling', NULL, 40, 20, 60, TRUE, '2026-04-01', 'grace', 7),
(63, 'Egg Fried Rice', 'Quick fried rice with eggs vegetables and soy sauce', NULL, 10, 10, 20, TRUE, '2026-04-10', 'grace', 7),
(64, 'Mapo Tofu', 'Silken tofu in spicy Sichuan chili bean sauce', NULL, 15, 15, 30, TRUE, '2026-04-20', 'grace', 7),
(65, 'Peking Duck', 'Crispy roasted duck with pancakes and hoisin sauce', NULL, 60, 120, 180, TRUE, '2026-05-01', 'grace', 7),
(66, 'Spring Rolls', 'Crispy fried rolls stuffed with vegetables and pork', NULL, 25, 15, 40, TRUE, '2026-05-10', 'grace', 7),
(67, 'Hot and Sour Soup', 'Tangy spicy soup with tofu mushrooms and bamboo', NULL, 15, 20, 35, TRUE, '2026-05-20', 'grace', 7),
(68, 'Chow Mein', 'Stir-fried noodles with vegetables and soy sauce', NULL, 15, 10, 25, TRUE, '2026-06-01', 'grace', 7),
(69, 'Wonton Soup', 'Clear broth with pork filled wonton dumplings', NULL, 30, 20, 50, TRUE, '2026-06-10', 'grace', 7),
(70, 'Char Siu', 'Cantonese roasted BBQ pork with honey glaze', NULL, 30, 45, 75, TRUE, '2026-06-20', 'grace', 7),
-- Henry's Thai recipes (IDs 71-80, cuisine 8)
(71, 'Pad Thai', 'Stir-fried rice noodles with shrimp peanuts and tamarind', NULL, 20, 15, 35, TRUE, '2026-04-01', 'henry', 8),
(72, 'Green Curry', 'Aromatic coconut curry with green chilies and basil', NULL, 20, 25, 45, TRUE, '2026-04-10', 'henry', 8),
(73, 'Tom Yum Soup', 'Hot and sour shrimp soup with lemongrass and galangal', NULL, 15, 20, 35, TRUE, '2026-04-20', 'henry', 8),
(74, 'Massaman Curry', 'Rich mild coconut curry with potatoes and peanuts', NULL, 25, 45, 70, TRUE, '2026-05-01', 'henry', 8),
(75, 'Pad See Ew', 'Stir-fried wide rice noodles with soy sauce and broccoli', NULL, 15, 15, 30, TRUE, '2026-05-10', 'henry', 8),
(76, 'Fresh Spring Rolls', 'Rice paper rolls with shrimp herbs and vermicelli', NULL, 25, 0, 25, TRUE, '2026-05-20', 'henry', 8),
(77, 'Mango Sticky Rice', 'Sweet sticky rice with ripe mango and coconut cream', NULL, 15, 25, 40, TRUE, '2026-06-01', 'henry', 8),
(78, 'Tom Kha Soup', 'Creamy coconut soup with chicken galangal and mushrooms', NULL, 15, 20, 35, TRUE, '2026-06-10', 'henry', 8),
(79, 'Red Curry', 'Spicy coconut curry with bamboo shoots and Thai basil', NULL, 20, 25, 45, TRUE, '2026-06-20', 'henry', 8),
(80, 'Chicken Satay', 'Grilled marinated chicken skewers with peanut sauce', NULL, 30, 15, 45, TRUE, '2026-07-01', 'henry', 8),
-- Ivy's Spanish recipes (IDs 81-90, cuisine 9)
(81, 'Seafood Paella', 'Saffron rice with prawns mussels and calamari', NULL, 30, 40, 70, TRUE, '2026-04-05', 'ivy', 9),
(82, 'Tortilla Espanola', 'Spanish potato and onion omelette', NULL, 15, 25, 40, TRUE, '2026-04-15', 'ivy', 9),
(83, 'Gazpacho', 'Chilled tomato and vegetable soup from Andalusia', NULL, 20, 0, 20, TRUE, '2026-04-25', 'ivy', 9),
(84, 'Patatas Bravas', 'Crispy fried potatoes with spicy tomato sauce', NULL, 15, 20, 35, TRUE, '2026-05-05', 'ivy', 9),
(85, 'Churros con Chocolate', 'Crispy churros served with thick hot chocolate', NULL, 20, 20, 40, TRUE, '2026-05-15', 'ivy', 9),
(86, 'Pulpo a la Gallega', 'Galician-style octopus with paprika and olive oil', NULL, 30, 40, 70, TRUE, '2026-05-25', 'ivy', 9),
(87, 'Pisto', 'Spanish ratatouille with peppers zucchini and tomato', NULL, 15, 30, 45, TRUE, '2026-06-05', 'ivy', 9),
(88, 'Crema Catalana', 'Catalan custard with caramelized sugar and citrus', NULL, 20, 35, 55, TRUE, '2026-06-15', 'ivy', 9),
(89, 'Albondigas', 'Spanish meatballs in rich tomato and almond sauce', NULL, 20, 30, 50, TRUE, '2026-06-25', 'ivy', 9),
(90, 'Gambas al Ajillo', 'Sizzling garlic shrimp in olive oil with chili', NULL, 10, 10, 20, TRUE, '2026-07-05', 'ivy', 9),
-- Jack's Greek recipes (IDs 91-100, cuisine 10)
(91, 'Greek Salad', 'Crisp cucumber tomato feta and olive salad', NULL, 15, 0, 15, TRUE, '2026-04-10', 'jack', 10),
(92, 'Moussaka', 'Layered eggplant potato and spiced meat with béchamel', NULL, 30, 60, 90, TRUE, '2026-04-25', 'jack', 10),
(93, 'Souvlaki', 'Grilled pork skewers with lemon oregano and pita', NULL, 30, 15, 45, TRUE, '2026-05-05', 'jack', 10),
(94, 'Tzatziki', 'Creamy cucumber yogurt dip with garlic and dill', NULL, 10, 0, 10, TRUE, '2026-05-15', 'jack', 10),
(95, 'Spanakopita', 'Flaky phyllo pie filled with spinach and feta', NULL, 25, 35, 60, TRUE, '2026-05-25', 'jack', 10),
(96, 'Dolmades', 'Grape leaves stuffed with herbed rice and lemon', NULL, 30, 40, 70, TRUE, '2026-06-05', 'jack', 10),
(97, 'Baklava', 'Layered phyllo pastry with walnuts and honey syrup', NULL, 30, 40, 70, TRUE, '2026-06-15', 'jack', 10),
(98, 'Avgolemono Soup', 'Traditional Greek lemon chicken soup with rice', NULL, 15, 30, 45, TRUE, '2026-06-25', 'jack', 10),
(99, 'Gyros', 'Spiced meat wrapped in pita with tzatziki and veg', NULL, 30, 25, 55, TRUE, '2026-07-05', 'jack', 10),
(100, 'Feta Stuffed Peppers', 'Baked bell peppers filled with feta herbs and rice', NULL, 15, 30, 45, TRUE, '2026-07-15', 'jack', 10);

-- =============================================
-- 7. STEPS (4 steps per recipe, ~400 total)
-- =============================================
INSERT INTO step (id, title, description, position, image_url, recipe_id) VALUES
-- Recipe 1: Spaghetti Carbonara
(1, 'Boil Water', 'Bring a large pot of salted water to a rolling boil', 1, NULL, 1),
(2, 'Cook Pasta', 'Add spaghetti and cook until al dente', 2, NULL, 1),
(3, 'Mix Eggs and Cheese', 'Whisk eggs with grated pecorino romano and pepper', 3, NULL, 1),
(4, 'Combine and Serve', 'Toss hot pasta with egg mixture until creamy and serve', 4, NULL, 1),
-- Recipe 2: Margherita Pizza
(5, 'Prepare Dough', 'Mix flour water yeast and salt to form dough', 1, NULL, 2),
(6, 'Knead and Rise', 'Knead dough until smooth and let rise for 1 hour', 2, NULL, 2),
(7, 'Shape and Top', 'Stretch dough add tomato sauce mozzarella and basil', 3, NULL, 2),
(8, 'Bake Pizza', 'Bake in preheated oven at 250°C for 12 minutes', 4, NULL, 2),
-- Recipe 3: Penne Arrabbiata
(9, 'Cook Pasta', 'Boil penne in salted water until al dente', 1, NULL, 3),
(10, 'Sauté Garlic and Chili', 'Sauté sliced garlic and dried chili in olive oil', 2, NULL, 3),
(11, 'Add Tomatoes', 'Pour crushed tomatoes and simmer for 10 minutes', 3, NULL, 3),
(12, 'Toss and Serve', 'Mix penne with sauce and serve with parsley', 4, NULL, 3),
-- Recipe 4: Caprese Salad
(13, 'Slice Tomatoes', 'Slice fresh ripe tomatoes into even rounds', 1, NULL, 4),
(14, 'Slice Mozzarella', 'Slice fresh mozzarella into similar sized rounds', 2, NULL, 4),
(15, 'Arrange on Plate', 'Alternate tomato and mozzarella slices on a plate', 3, NULL, 4),
(16, 'Season and Drizzle', 'Add fresh basil leaves salt pepper and olive oil', 4, NULL, 4),
-- Recipe 5: Lasagna
(17, 'Make Meat Sauce', 'Brown beef with onion garlic and crushed tomatoes', 1, NULL, 5),
(18, 'Prepare Béchamel', 'Melt butter whisk in flour and milk until thick', 2, NULL, 5),
(19, 'Layer Everything', 'Alternate pasta sheets meat sauce and béchamel', 3, NULL, 5),
(20, 'Bake and Rest', 'Bake at 180°C for 40 minutes let rest before cutting', 4, NULL, 5),
-- Recipe 6: Risotto alla Milanese
(21, 'Sauté Onion', 'Finely dice onion and sauté in butter until translucent', 1, NULL, 6),
(22, 'Toast Rice', 'Add arborio rice and toast for 2 minutes stirring constantly', 2, NULL, 6),
(23, 'Add Wine and Stock', 'Deglaze with white wine then add warm stock gradually', 3, NULL, 6),
(24, 'Finish with Saffron', 'Stir in saffron butter and parmesan then serve', 4, NULL, 6),
-- Recipe 7: Tiramisu
(25, 'Brew Espresso', 'Make strong espresso and let it cool completely', 1, NULL, 7),
(26, 'Make Mascarpone Cream', 'Whip egg yolks with sugar then fold in mascarpone', 2, NULL, 7),
(27, 'Layer Ladyfingers', 'Dip ladyfingers in espresso and layer in dish', 3, NULL, 7),
(28, 'Chill and Serve', 'Alternate layers and refrigerate for 6 hours', 4, NULL, 7),
-- Recipe 8: Fettuccine Alfredo
(29, 'Cook Fettuccine', 'Boil fettuccine in salted water until al dente', 1, NULL, 8),
(30, 'Melt Butter', 'Melt butter in a large pan over medium heat', 2, NULL, 8),
(31, 'Add Cream and Cheese', 'Pour heavy cream and stir in grated parmesan', 3, NULL, 8),
(32, 'Toss Pasta', 'Add cooked pasta to sauce toss and serve immediately', 4, NULL, 8),
-- Recipe 9: Bruschetta
(33, 'Toast Bread', 'Slice baguette and toast until golden and crispy', 1, NULL, 9),
(34, 'Dice Tomatoes', 'Dice fresh tomatoes and mix with chopped basil', 2, NULL, 9),
(35, 'Rub Garlic', 'Rub toasted bread with a clove of garlic', 3, NULL, 9),
(36, 'Top and Serve', 'Spoon tomato mixture onto bread and drizzle olive oil', 4, NULL, 9),
-- Recipe 10: Minestrone Soup
(37, 'Chop Vegetables', 'Dice carrots celery onion and zucchini into small pieces', 1, NULL, 10),
(38, 'Sauté Base', 'Sauté onion and garlic in olive oil until soft', 2, NULL, 10),
(39, 'Add Broth and Simmer', 'Add vegetable broth tomatoes beans and pasta', 3, NULL, 10),
(40, 'Season and Serve', 'Simmer until vegetables are tender season and serve', 4, NULL, 10),
-- Recipe 11: Tacos al Pastor
(41, 'Marinate Pork', 'Marinate pork in achiote pineapple juice and spices', 1, NULL, 11),
(42, 'Grill Meat', 'Grill marinated pork on high heat until charred', 2, NULL, 11),
(43, 'Chop and Warm Tortillas', 'Dice grilled meat and warm corn tortillas', 3, NULL, 11),
(44, 'Assemble Tacos', 'Fill tortillas with meat pineapple onion and cilantro', 4, NULL, 11),
-- Recipe 12: Guacamole
(45, 'Halve Avocados', 'Cut avocados in half remove pit and scoop flesh', 1, NULL, 12),
(46, 'Mash Avocados', 'Mash avocado to desired consistency with a fork', 2, NULL, 12),
(47, 'Mix Ingredients', 'Stir in diced onion tomato cilantro and lime juice', 3, NULL, 12),
(48, 'Season and Serve', 'Add salt to taste and serve immediately with chips', 4, NULL, 12),
-- Recipe 13: Churros
(49, 'Make Dough', 'Boil water with butter sugar and vanilla then add flour', 1, NULL, 13),
(50, 'Pipe and Fry', 'Pipe dough through star tip into hot oil', 2, NULL, 13),
(51, 'Drain and Coat', 'Drain on paper towels then roll in cinnamon sugar', 3, NULL, 13),
(52, 'Serve Warm', 'Serve churros warm with chocolate dipping sauce', 4, NULL, 13),
-- Recipe 14: Enchiladas Verdes
(53, 'Cook Chicken', 'Poach chicken breasts then shred with forks', 1, NULL, 14),
(54, 'Prepare Salsa', 'Blend tomatillos cilantro and jalapeño for green salsa', 2, NULL, 14),
(55, 'Fill and Roll', 'Fill tortillas with chicken roll and place in baking dish', 3, NULL, 14),
(56, 'Bake with Cheese', 'Cover with salsa and cheese bake until bubbly', 4, NULL, 14),
-- Recipe 15: Pozole
(57, 'Boil Pork', 'Simmer pork shoulder with garlic and onion until tender', 1, NULL, 15),
(58, 'Add Hominy', 'Add drained hominy and continue simmering', 2, NULL, 15),
(59, 'Season Broth', 'Add chili powder oregano and cumin to the broth', 3, NULL, 15),
(60, 'Serve with Toppings', 'Serve with shredded cabbage radish lime and chips', 4, NULL, 15),
-- Recipe 16: Tres Leches Cake
(61, 'Bake Sponge', 'Beat egg whites separately fold into batter and bake', 1, NULL, 16),
(62, 'Poke Holes', 'Poke holes all over the cooled cake with a fork', 2, NULL, 16),
(63, 'Make Milk Mixture', 'Mix condensed milk evaporated milk and heavy cream', 3, NULL, 16),
(64, 'Soak and Chill', 'Pour milk mixture over cake and refrigerate overnight', 4, NULL, 16),
-- Recipe 17: Quesadillas
(65, 'Prepare Filling', 'Slice peppers onions and cheese into thin strips', 1, NULL, 17),
(66, 'Assemble Quesadilla', 'Place filling between two tortillas', 2, NULL, 17),
(67, 'Pan Fry', 'Cook in a hot dry pan until golden on both sides', 3, NULL, 17),
(68, 'Cut and Serve', 'Cut into wedges and serve with salsa and sour cream', 4, NULL, 17),
-- Recipe 18: Burritos
(69, 'Cook Rice and Beans', 'Prepare seasoned rice and warm refried beans', 1, NULL, 18),
(70, 'Cook Meat Filling', 'Brown ground beef with taco seasoning', 2, NULL, 18),
(71, 'Warm Tortillas', 'Warm large flour tortillas on a griddle', 3, NULL, 18),
(72, 'Wrap Burritos', 'Fill tortillas roll tightly and serve with toppings', 4, NULL, 18),
-- Recipe 19: Nachos Supreme
(73, 'Spread Chips', 'Arrange tortilla chips on a baking sheet', 1, NULL, 19),
(74, 'Add Toppings', 'Sprinkle cheese beans jalapeños and seasoned meat', 2, NULL, 19),
(75, 'Melt Cheese', 'Bake until cheese is fully melted and bubbly', 3, NULL, 19),
(76, 'Garnish and Serve', 'Top with salsa guacamole and sour cream', 4, NULL, 19),
-- Recipe 20: Tamales
(77, 'Prepare Masa', 'Mix masa harina with lard broth and baking powder', 1, NULL, 20),
(78, 'Prepare Filling', 'Cook shredded pork with red chili sauce', 2, NULL, 20),
(79, 'Soak Husks', 'Soak corn husks in warm water until pliable', 3, NULL, 20),
(80, 'Steam Tamales', 'Spread masa add filling wrap and steam for 1 hour', 4, NULL, 20),
-- Recipe 21: Sushi Rolls
(81, 'Cook Sushi Rice', 'Cook rice and season with rice vinegar sugar and salt', 1, NULL, 21),
(82, 'Slice Fillings', 'Cut salmon avocado and cucumber into thin strips', 2, NULL, 21),
(83, 'Roll Sushi', 'Lay nori on bamboo mat spread rice and add fillings', 3, NULL, 21),
(84, 'Slice and Plate', 'Roll tightly then slice into bite-sized pieces', 4, NULL, 21),
-- Recipe 22: Tonkotsu Ramen
(85, 'Simmer Broth', 'Simmer pork bones with aromatics for 4 hours', 1, NULL, 22),
(86, 'Cook Noodles', 'Boil fresh ramen noodles until tender', 2, NULL, 22),
(87, 'Prepare Toppings', 'Slice chashu pork halve soft-boiled egg and chop scallions', 3, NULL, 22),
(88, 'Assemble Bowls', 'Pour broth over noodles and arrange toppings', 4, NULL, 22),
-- Recipe 23: Tempura
(89, 'Make Batter', 'Mix ice-cold water with flour and egg yolk', 1, NULL, 23),
(90, 'Prepare Vegetables', 'Slice shrimp and vegetables into bite-sized pieces', 2, NULL, 23),
(91, 'Dip and Fry', 'Dip pieces in batter and deep fry until light golden', 3, NULL, 23),
(92, 'Drain and Serve', 'Drain on paper towels and serve with tentsuyu sauce', 4, NULL, 23),
-- Recipe 24: Teriyaki Chicken
(93, 'Make Teriyaki Sauce', 'Combine soy sauce mirin sake and sugar in a pan', 1, NULL, 24),
(94, 'Marinate Chicken', 'Marinate chicken thighs in sauce for 30 minutes', 2, NULL, 24),
(95, 'Grill Chicken', 'Grill chicken basting with extra teriyaki sauce', 3, NULL, 24),
(96, 'Slice and Serve', 'Slice chicken and drizzle with remaining sauce', 4, NULL, 24),
-- Recipe 25: Miso Soup
(97, 'Make Dashi', 'Simmer kombu and bonito flakes then strain', 1, NULL, 25),
(98, 'Add Tofu', 'Cube silken tofu and add to the hot dashi', 2, NULL, 25),
(99, 'Dissolve Miso', 'Dissolve miso paste in a ladle of broth then stir in', 3, NULL, 25),
(100, 'Garnish and Serve', 'Add sliced green onions and serve immediately', 4, NULL, 25),
-- Recipe 26: Yakitori
(101, 'Make Tare Sauce', 'Simmer soy sauce mirin sake and sugar until thickened', 1, NULL, 26),
(102, 'Skewer Chicken', 'Thread chicken thigh pieces and scallions onto skewers', 2, NULL, 26),
(103, 'Grill and Baste', 'Grill skewers basting with tare sauce until caramelized', 3, NULL, 26),
(104, 'Serve Hot', 'Serve yakitori hot with a sprinkle of shichimi togarashi', 4, NULL, 26),
-- Recipe 27: Udon Noodles
(105, 'Make Broth', 'Simmer dashi with soy sauce mirin and sake', 1, NULL, 27),
(106, 'Cook Udon', 'Boil thick udon noodles according to package directions', 2, NULL, 27),
(107, 'Fry Tempura', 'Make shrimp tempura as a topping', 3, NULL, 27),
(108, 'Combine and Serve', 'Place noodles in bowl pour broth and top with tempura', 4, NULL, 27),
-- Recipe 28: Gyoza
(109, 'Make Filling', 'Mix ground pork shredded cabbage ginger and garlic', 1, NULL, 28),
(110, 'Fill Wrappers', 'Place filling on gyoza wrappers and fold edges', 2, NULL, 28),
(111, 'Pan Fry', 'Fry gyoza in oil then add water and cover to steam', 3, NULL, 28),
(112, 'Crisp and Serve', 'Uncover and cook until bottoms are crispy then serve', 4, NULL, 28),
-- Recipe 29: Okonomiyaki
(113, 'Make Batter', 'Mix flour grated nagaimo egg dashi and cabbage', 1, NULL, 29),
(114, 'Add Toppings', 'Add sliced pork belly and cook on a hot griddle', 2, NULL, 29),
(115, 'Flip and Cook', 'Flip carefully and cook until both sides are golden', 3, NULL, 29),
(116, 'Drizzle Sauces', 'Drizzle with okonomiyaki sauce mayo and bonito flakes', 4, NULL, 29),
-- Recipe 30: Matcha Ice Cream
(117, 'Heat Milk', 'Warm milk with sugar until sugar dissolves', 1, NULL, 30),
(118, 'Whisk Matcha', 'Sift matcha powder and whisk into milk mixture', 2, NULL, 30),
(119, 'Add Cream', 'Stir in heavy cream and vanilla extract', 3, NULL, 30),
(120, 'Churn and Freeze', 'Churn in ice cream maker then freeze until firm', 4, NULL, 30),
-- Recipe 31: Chicken Tikka Masala
(121, 'Marinate Chicken', 'Marinate chicken in yogurt lemon juice and spices', 1, NULL, 31),
(122, 'Grill Chicken', 'Grill marinated chicken pieces until charred', 2, NULL, 31),
(123, 'Make Masala Sauce', 'Cook onion garlic ginger and tomato with cream', 3, NULL, 31),
(124, 'Combine and Simmer', 'Add grilled chicken to sauce and simmer for 15 minutes', 4, NULL, 31),
-- Recipe 32: Butter Chicken
(125, 'Marinate Overnight', 'Marinate chicken in yogurt and tandoori spices', 1, NULL, 32),
(126, 'Char Chicken', 'Cook chicken in a hot pan until charred', 2, NULL, 32),
(127, 'Prepare Gravy', 'Blend tomatoes onion and cashews into a smooth puree', 3, NULL, 32),
(128, 'Finish with Butter', 'Add butter cream and fenugreek then simmer', 4, NULL, 32),
-- Recipe 33: Vegetable Biryani
(129, 'Parboil Rice', 'Boil basmati rice with whole spices until half done', 1, NULL, 33),
(130, 'Cook Vegetables', 'Sauté mixed vegetables with biryani masala', 2, NULL, 33),
(131, 'Layer in Pot', 'Layer rice and vegetables in a heavy bottom pot', 3, NULL, 33),
(132, 'Dum Cook', 'Seal pot with dough and cook on low heat for 30 minutes', 4, NULL, 33),
-- Recipe 34: Samosas
(133, 'Make Filling', 'Cook mashed potatoes with peas ginger and spices', 1, NULL, 34),
(134, 'Prepare Dough', 'Knead flour oil and water into a stiff dough', 2, NULL, 34),
(135, 'Shape Samosas', 'Roll dough fill with potato mixture and seal edges', 3, NULL, 34),
(136, 'Deep Fry', 'Deep fry samosas until golden and crispy', 4, NULL, 34),
-- Recipe 35: Garlic Naan
(137, 'Activate Yeast', 'Mix yeast with warm water and sugar let foam', 1, NULL, 35),
(138, 'Knead Dough', 'Add flour yogurt and ghee knead until smooth', 2, NULL, 35),
(139, 'Shape and Rest', 'Divide dough into balls and let rest for 30 minutes', 3, NULL, 35),
(140, 'Cook with Garlic', 'Roll out brush with garlic butter and cook in hot pan', 4, NULL, 35),
-- Recipe 36: Dal Makhani
(141, 'Soak Lentils', 'Soak black lentils and kidney beans overnight', 1, NULL, 36),
(142, 'Cook Lentils', 'Simmer lentils with water until very soft', 2, NULL, 36),
(143, 'Temper Spices', 'Fry cumin garlic ginger and tomato in butter', 3, NULL, 36),
(144, 'Slow Cook', 'Add tempered spices to lentils and simmer for 2 hours', 4, NULL, 36),
-- Recipe 37: Palak Paneer
(145, 'Blanch Spinach', 'Blanch spinach in boiling water then blend to puree', 1, NULL, 37),
(146, 'Fry Paneer', 'Cube and pan-fry paneer until golden on all sides', 2, NULL, 37),
(147, 'Cook Spices', 'Sauté cumin garlic ginger and green chili in oil', 3, NULL, 37),
(148, 'Combine and Simmer', 'Add spinach puree and paneer simmer for 10 minutes', 4, NULL, 37),
-- Recipe 38: Rogan Josh
(149, 'Brown Lamb', 'Brown lamb pieces in hot oil in batches', 1, NULL, 38),
(150, 'Cook Aromatics', 'Fry sliced onion until golden add ginger garlic paste', 2, NULL, 38),
(151, 'Add Spices', 'Stir in Kashmiri chili fennel garam masala and yogurt', 3, NULL, 38),
(152, 'Slow Braise', 'Add water cover and simmer until lamb is very tender', 4, NULL, 38),
-- Recipe 39: Chicken Korma
(153, 'Fry Onions', 'Slice and deep fry onions until golden and crispy', 1, NULL, 39),
(154, 'Make Paste', 'Blend fried onions with cashews yogurt and spices', 2, NULL, 39),
(155, 'Cook Chicken', 'Sauté chicken pieces until sealed on all sides', 3, NULL, 39),
(156, 'Simmer Korma', 'Add paste and cream simmer gently until chicken is cooked', 4, NULL, 39),
-- Recipe 40: Mango Lassi
(157, 'Peel Mango', 'Peel and chop ripe mango into chunks', 1, NULL, 40),
(158, 'Blend Ingredients', 'Blend mango with yogurt milk and sugar until smooth', 2, NULL, 40),
(159, 'Add Cardamom', 'Add a pinch of ground cardamom and blend again', 3, NULL, 40),
(160, 'Serve Cold', 'Pour over ice and garnish with chopped pistachios', 4, NULL, 40),
-- Recipe 41: French Onion Soup
(161, 'Slice Onions', 'Thinly slice a large amount of onions', 1, NULL, 41),
(162, 'Caramelize Slowly', 'Cook onions in butter on low heat for 40 minutes until deep brown', 2, NULL, 41),
(163, 'Add Broth', 'Pour in beef broth and thyme simmer for 15 minutes', 3, NULL, 41),
(164, 'Top with Gruyere', 'Ladle into bowls top with bread and cheese then broil', 4, NULL, 41),
-- Recipe 42: Croissants
(165, 'Make Dough', 'Mix flour milk yeast sugar and salt into dough', 1, NULL, 42),
(166, 'Laminate with Butter', 'Roll dough around cold butter and fold repeatedly', 2, NULL, 42),
(167, 'Shape Croissants', 'Cut triangles roll up and curve into crescents', 3, NULL, 42),
(168, 'Proof and Bake', 'Proof until doubled then brush with egg and bake', 4, NULL, 42),
-- Recipe 43: Coq au Vin
(169, 'Season and Sear', 'Season chicken pieces and sear in hot oil until golden', 1, NULL, 43),
(170, 'Cook Aromatics', 'Sauté pearl onions mushrooms and bacon lardons', 2, NULL, 43),
(171, 'Deglaze with Wine', 'Pour in Burgundy wine and bring to a simmer', 3, NULL, 43),
(172, 'Braise Slowly', 'Cover and braise in the oven for 90 minutes until tender', 4, NULL, 43),
-- Recipe 44: Creme Brulee
(173, 'Heat Cream', 'Heat heavy cream with vanilla bean until just simmering', 1, NULL, 44),
(174, 'Temper Eggs', 'Whisk egg yolks with sugar then slowly add hot cream', 2, NULL, 44),
(175, 'Bake Custard', 'Pour mixture into ramekins and bake in water bath', 3, NULL, 44),
(176, 'Torch Sugar', 'Chill then sprinkle sugar on top and torch until caramelized', 4, NULL, 44),
-- Recipe 45: Ratatouille
(177, 'Slice Vegetables', 'Slice eggplant zucchini bell peppers and tomato evenly', 1, NULL, 45),
(178, 'Make Base Sauce', 'Cook onion garlic and crushed tomatoes as the base', 2, NULL, 45),
(179, 'Arrange Vegetables', 'Arrange sliced vegetables in concentric circles on sauce', 3, NULL, 45),
(180, 'Bake with Herbs', 'Drizzle with olive oil add herbs and bake for 40 minutes', 4, NULL, 45),
-- Recipe 46: Quiche Lorraine
(181, 'Make Pastry', 'Rub butter into flour add water and chill', 1, NULL, 46),
(182, 'Blind Bake', 'Line tart tin with pastry and blind bake until golden', 2, NULL, 46),
(183, 'Prepare Filling', 'Cook bacon lardons and whisk eggs with cream and gruyere', 3, NULL, 46),
(184, 'Bake Quiche', 'Pour filling into crust and bake until set and golden', 4, NULL, 46),
-- Recipe 47: Bouillabaisse
(185, 'Make Rouille', 'Blend garlic breadcrumbs chili and olive oil into paste', 1, NULL, 47),
(186, 'Cook Aromatics', 'Sauté fennel onion and garlic in olive oil', 2, NULL, 47),
(187, 'Add Broth and Fish', 'Add tomatoes saffron broth and assorted fish pieces', 3, NULL, 47),
(188, 'Simmer and Serve', 'Simmer briefly and serve with rouille and crusty bread', 4, NULL, 47),
-- Recipe 48: Macarons
(189, 'Sift Almond Flour', 'Sift almond flour and powdered sugar together twice', 1, NULL, 48),
(190, 'Make Meringue', 'Whip egg whites with sugar until stiff glossy peaks', 2, NULL, 48),
(191, 'Macaronage', 'Fold dry ingredients into meringue until lava-like consistency', 3, NULL, 48),
(192, 'Pipe and Rest', 'Pipe rounds rest for 30 minutes then bake and fill', 4, NULL, 48),
-- Recipe 49: Beef Bourguignon
(193, 'Sear Beef', 'Season and sear beef cubes in batches until browned', 1, NULL, 49),
(194, 'Cook Vegetables', 'Sauté carrots onions and garlic in the same pot', 2, NULL, 49),
(195, 'Add Wine and Braise', 'Pour in Burgundy wine add bouquet garni and cover', 3, NULL, 49),
(196, 'Slow Cook', 'Braise in oven at 160°C for 2.5 hours until fork tender', 4, NULL, 49),
-- Recipe 50: Crepes Suzette
(197, 'Make Crepe Batter', 'Whisk flour eggs milk melted butter and sugar until smooth', 1, NULL, 50),
(198, 'Cook Crepes', 'Pour thin layer of batter into hot pan and flip', 2, NULL, 50),
(199, 'Make Orange Sauce', 'Melt butter with orange juice zest and sugar', 3, NULL, 50),
(200, 'Flambe and Serve', 'Add crepes to sauce pour Grand Marnier and ignite', 4, NULL, 50),
-- Recipe 51: BBQ Ribs
(201, 'Remove Membrane', 'Remove the tough membrane from the back of ribs', 1, NULL, 51),
(202, 'Apply Dry Rub', 'Coat ribs generously with brown sugar and spice rub', 2, NULL, 51),
(203, 'Smoke Low and Slow', 'Smoke ribs at 110°C for 3 hours until tender', 3, NULL, 51),
(204, 'Glaze and Sear', 'Brush with BBQ sauce and sear on high heat', 4, NULL, 51),
-- Recipe 52: Classic Cheeseburger
(205, 'Form Patties', 'Shape ground beef into patties season with salt and pepper', 1, NULL, 52),
(206, 'Grill Patties', 'Grill patties over high heat for 4 minutes per side', 2, NULL, 52),
(207, 'Melt Cheese', 'Place cheddar slice on each patty and cover to melt', 3, NULL, 52),
(208, 'Assemble Burger', 'Build burger with lettuce tomato onion and sauce on bun', 4, NULL, 52),
-- Recipe 53: Apple Pie
(209, 'Make Pie Dough', 'Cut cold butter into flour add ice water and chill', 1, NULL, 53),
(210, 'Prepare Filling', 'Toss sliced apples with sugar cinnamon and lemon juice', 2, NULL, 53),
(211, 'Assemble Pie', 'Roll dough line pie dish add filling and lattice top', 3, NULL, 53),
(212, 'Bake Until Golden', 'Bake at 190°C for 50 minutes until golden and bubbly', 4, NULL, 53),
-- Recipe 54: Mac and Cheese
(213, 'Cook Pasta', 'Boil macaroni until al dente then drain', 1, NULL, 54),
(214, 'Make Cheese Sauce', 'Melt butter whisk in flour milk and shredded cheddar', 2, NULL, 54),
(215, 'Combine and Top', 'Mix pasta with sauce and top with breadcrumbs and cheese', 3, NULL, 54),
(216, 'Bake Until Crispy', 'Bake at 190°C for 25 minutes until golden and bubbly', 4, NULL, 54),
-- Recipe 55: Fried Chicken
(217, 'Brine Chicken', 'Soak chicken pieces in buttermilk and spices overnight', 1, NULL, 55),
(218, 'Season Flour', 'Mix flour with paprika garlic powder salt and pepper', 2, NULL, 55),
(219, 'Dredge Chicken', 'Dredge buttermilk soaked chicken in seasoned flour', 3, NULL, 55),
(220, 'Deep Fry', 'Fry at 170°C until golden and cooked through', 4, NULL, 55),
-- Recipe 56: Buffalo Wings
(221, 'Prep Wings', 'Cut wings at joints and pat completely dry', 1, NULL, 56),
(222, 'Bake Wings', 'Bake wings on a rack at 200°C until crispy', 2, NULL, 56),
(223, 'Make Buffalo Sauce', 'Melt butter and whisk with hot sauce and vinegar', 3, NULL, 56),
(224, 'Toss and Serve', 'Toss baked wings in buffalo sauce serve with blue cheese', 4, NULL, 56),
-- Recipe 57: Clam Chowder
(225, 'Cook Bacon', 'Dice and cook bacon until crispy', 1, NULL, 57),
(226, 'Sauté Vegetables', 'Cook onion celery and garlic in bacon fat', 2, NULL, 57),
(227, 'Add Potatoes and Cream', 'Add diced potatoes clam juice and heavy cream', 3, NULL, 57),
(228, 'Finish with Clams', 'Add chopped clams heat through season and serve', 4, NULL, 57),
-- Recipe 58: Pancakes
(229, 'Mix Dry Ingredients', 'Whisk flour sugar baking powder and salt together', 1, NULL, 58),
(230, 'Combine Wet', 'Whisk buttermilk egg and melted butter separately', 2, NULL, 58),
(231, 'Cook Pancakes', 'Pour batter onto hot griddle flip when bubbles form', 3, NULL, 58),
(232, 'Serve with Toppings', 'Stack pancakes and serve with butter and maple syrup', 4, NULL, 58),
-- Recipe 59: Cobb Salad
(233, 'Cook Chicken', 'Season and grill chicken breast then slice', 1, NULL, 59),
(234, 'Boil Eggs', 'Hard boil eggs then peel and quarter', 2, NULL, 59),
(235, 'Chop Vegetables', 'Dice tomatoes avocado and chop romaine lettuce', 3, NULL, 59),
(236, 'Assemble Salad', 'Arrange ingredients in rows on lettuce and drizzle dressing', 4, NULL, 59),
-- Recipe 60: Chocolate Chip Cookies
(237, 'Cream Butter and Sugar', 'Beat softened butter with both sugars until fluffy', 1, NULL, 60),
(238, 'Add Eggs and Vanilla', 'Mix in eggs and vanilla extract until combined', 2, NULL, 60),
(239, 'Fold Dry Ingredients', 'Fold in flour baking soda salt and chocolate chunks', 3, NULL, 60),
(240, 'Bake Cookies', 'Scoop onto baking sheet and bake at 175°C for 12 minutes', 4, NULL, 60),
-- Recipe 61: Kung Pao Chicken
(241, 'Marinate Chicken', 'Dice chicken and marinate with soy sauce and cornstarch', 1, NULL, 61),
(242, 'Make Sauce', 'Mix soy sauce vinegar sugar and sesame oil', 2, NULL, 61),
(243, 'Stir Fry', 'Stir fry chicken with peanuts chili and Sichuan pepper', 3, NULL, 61),
(244, 'Add Sauce', 'Pour in sauce toss quickly and serve over rice', 4, NULL, 61),
-- Recipe 62: Dim Sum
(245, 'Make Dough', 'Mix flour with hot water to form a smooth dough', 1, NULL, 62),
(246, 'Prepare Filling', 'Mix ground pork shrimp ginger and soy sauce', 2, NULL, 62),
(247, 'Fold Dumplings', 'Roll dough place filling and pleat edges to seal', 3, NULL, 62),
(248, 'Steam Dumplings', 'Steam dumplings for 10 minutes until translucent', 4, NULL, 62),
-- Recipe 63: Egg Fried Rice
(249, 'Prep Ingredients', 'Chop scallions dice carrots and beat eggs', 1, NULL, 63),
(250, 'Scramble Eggs', 'Scramble eggs in hot oil then remove from wok', 2, NULL, 63),
(251, 'Stir Fry Rice', 'Add cold rice to wok stir fry with vegetables', 3, NULL, 63),
(252, 'Combine and Season', 'Return eggs add soy sauce and sesame oil toss well', 4, NULL, 63),
-- Recipe 64: Mapo Tofu
(253, 'Prepare Tofu', 'Cut silken tofu into cubes and blanch in hot water', 1, NULL, 64),
(254, 'Cook Meat', 'Brown ground pork in a wok with Sichuan chili bean paste', 2, NULL, 64),
(255, 'Add Tofu', 'Gently add tofu and Sichuan peppercorns to the wok', 3, NULL, 64),
(256, 'Simmer and Thicken', 'Simmer briefly then thicken with cornstarch slurry', 4, NULL, 64),
-- Recipe 65: Peking Duck
(257, 'Prepare Duck', 'Prick skin and pour boiling water over the duck', 1, NULL, 65),
(258, 'Air Dry', 'Hang duck to dry in a cool place for several hours', 2, NULL, 65),
(259, 'Roast Duck', 'Roast duck at 180°C until skin is deep golden and crisp', 3, NULL, 65),
(260, 'Carve and Serve', 'Carve skin and meat serve with pancakes and hoisin', 4, NULL, 65),
-- Recipe 66: Spring Rolls
(261, 'Prepare Filling', 'Shred cabbage carrot and bean sprouts', 1, NULL, 66),
(262, 'Cook Filling', 'Stir fry vegetables with pork and soy sauce then cool', 2, NULL, 66),
(263, 'Wrap Rolls', 'Wrap filling in spring roll pastry and seal edges', 3, NULL, 66),
(264, 'Deep Fry', 'Deep fry until golden and crispy serve with sweet chili', 4, NULL, 66),
-- Recipe 67: Hot and Sour Soup
(265, 'Prepare Broth', 'Bring chicken broth to a simmer with ginger and soy', 1, NULL, 67),
(266, 'Add Ingredients', 'Add sliced mushrooms tofu and bamboo shoots', 2, NULL, 67),
(267, 'Season Soup', 'Add vinegar white pepper and chili oil to taste', 3, NULL, 67),
(268, 'Thicken and Egg Drop', 'Thicken with cornstarch and drizzle in beaten egg', 4, NULL, 67),
-- Recipe 68: Chow Mein
(269, 'Cook Noodles', 'Boil chow mein noodles until al dente then drain', 1, NULL, 68),
(270, 'Stir Fry Vegetables', 'Stir fry sliced bell pepper carrot and cabbage', 2, NULL, 68),
(271, 'Add Noodles', 'Add noodles to wok with soy sauce and oyster sauce', 3, NULL, 68),
(272, 'Toss and Serve', 'Toss everything together and serve hot', 4, NULL, 68),
-- Recipe 69: Wonton Soup
(273, 'Make Filling', 'Mix ground pork shrimp ginger and soy sauce', 1, NULL, 69),
(274, 'Fold Wontons', 'Place filling on wonton wrappers and fold into triangles', 2, NULL, 69),
(275, 'Boil Wontons', 'Boil wontons in water until they float and are cooked', 3, NULL, 69),
(276, 'Serve in Broth', 'Place wontons in bowls and pour hot chicken broth over', 4, NULL, 69),
-- Recipe 70: Char Siu
(277, 'Mix Marinade', 'Combine hoisin honey soy sauce and five spice', 1, NULL, 70),
(278, 'Marinate Pork', 'Marinate pork shoulder in mixture overnight', 2, NULL, 70),
(279, 'Roast Pork', 'Roast pork at 190°C basting with honey glaze', 3, NULL, 70),
(280, 'Slice and Serve', 'Let rest then slice thinly and serve with rice', 4, NULL, 70),
-- Recipe 71: Pad Thai
(281, 'Soak Noodles', 'Soak rice noodles in warm water until softened', 1, NULL, 71),
(282, 'Make Pad Thai Sauce', 'Mix tamarind paste fish sauce sugar and lime juice', 2, NULL, 71),
(283, 'Stir Fry', 'Stir fry shrimp tofu egg and noodles in a hot wok', 3, NULL, 71),
(284, 'Toss with Sauce', 'Add sauce toss well and top with peanuts and lime', 4, NULL, 71),
-- Recipe 72: Green Curry
(285, 'Make Curry Paste', 'Blend green chilies lemongrass galangal and shrimp paste', 1, NULL, 72),
(286, 'Fry Paste', 'Fry curry paste in coconut cream until fragrant', 2, NULL, 72),
(287, 'Add Chicken and Veg', 'Add chicken pieces eggplant and Thai basil', 3, NULL, 72),
(288, 'Simmer in Coconut', 'Pour coconut milk and simmer until chicken is cooked', 4, NULL, 72),
-- Recipe 73: Tom Yum Soup
(289, 'Prepare Broth', 'Simmer lemongrass galangal and kaffir lime in water', 1, NULL, 73),
(290, 'Add Shrimp', 'Add shrimp and mushrooms to the aromatic broth', 2, NULL, 73),
(291, 'Season Soup', 'Add fish sauce lime juice and Thai chilies', 3, NULL, 73),
(292, 'Garnish and Serve', 'Top with cilantro and serve steaming hot', 4, NULL, 73),
-- Recipe 74: Massaman Curry
(293, 'Make Massaman Paste', 'Toast and grind cumin coriander and cloves', 1, NULL, 74),
(294, 'Cook Meat', 'Brown beef chunks in oil until caramelized', 2, NULL, 74),
(295, 'Add Curry Paste', 'Fry paste then add coconut milk potatoes and peanuts', 3, NULL, 74),
(296, 'Simmer Until Tender', 'Simmer until beef is very tender and sauce thickens', 4, NULL, 74),
-- Recipe 75: Pad See Ew
(297, 'Soak Noodles', 'Soak wide rice noodles in warm water until pliable', 1, NULL, 75),
(298, 'Make Sauce', 'Mix soy sauce oyster sauce sugar and white pepper', 2, NULL, 75),
(299, 'Stir Fry', 'Stir fry chicken broccoli and noodles in hot wok', 3, NULL, 75),
(300, 'Add Sauce and Serve', 'Add sauce toss until caramelized and serve', 4, NULL, 75),
-- Recipe 76: Fresh Spring Rolls
(301, 'Prepare Vermicelli', 'Cook rice vermicelli according to package directions', 1, NULL, 76),
(302, 'Slice Ingredients', 'Slice shrimp cucumber lettuce and herbs into strips', 2, NULL, 76),
(303, 'Soak Rice Paper', 'Dip rice paper in warm water until softened', 3, NULL, 76),
(304, 'Roll and Serve', 'Fill roll tightly and serve with peanut dipping sauce', 4, NULL, 76),
-- Recipe 77: Mango Sticky Rice
(305, 'Soak Rice', 'Soak sticky rice in water overnight then drain', 1, NULL, 77),
(306, 'Steam Rice', 'Steam sticky rice in a cheesecloth over boiling water', 2, NULL, 77),
(307, 'Make Coconut Sauce', 'Warm coconut milk with sugar and salt', 3, NULL, 77),
(308, 'Plate with Mango', 'Serve sticky rice with sliced mango and coconut sauce', 4, NULL, 77),
-- Recipe 78: Tom Kha Soup
(309, 'Simmer Aromatics', 'Simmer lemongrass galangal and kaffir lime in water', 1, NULL, 78),
(310, 'Add Chicken', 'Add sliced chicken and mushrooms to the pot', 2, NULL, 78),
(311, 'Pour Coconut Milk', 'Add coconut milk and bring to a gentle simmer', 3, NULL, 78),
(312, 'Season and Serve', 'Season with fish sauce lime juice and chilies', 4, NULL, 78),
-- Recipe 79: Red Curry
(313, 'Fry Curry Paste', 'Fry red curry paste in coconut cream until aromatic', 1, NULL, 79),
(314, 'Add Protein', 'Add sliced chicken or tofu to the paste', 2, NULL, 79),
(315, 'Add Vegetables', 'Add bamboo shoots bell peppers and Thai basil', 3, NULL, 79),
(316, 'Simmer in Coconut', 'Pour coconut milk and simmer until everything is cooked', 4, NULL, 79),
-- Recipe 80: Chicken Satay
(317, 'Make Marinade', 'Blend coconut milk curry powder turmeric and ginger', 1, NULL, 80),
(318, 'Marinate and Skewer', 'Marinate chicken strips then thread onto skewers', 2, NULL, 80),
(319, 'Grill Skewers', 'Grill skewers turning frequently until cooked through', 3, NULL, 80),
(320, 'Make Peanut Sauce', 'Simmer peanut butter coconut milk and curry paste', 4, NULL, 80),
-- Recipe 81: Seafood Paella
(321, 'Prepare Stock', 'Simmer shrimp shells and fish bones for stock', 1, NULL, 81),
(322, 'Sauté Aromatics', 'Sauté onion garlic and peppers in olive oil', 2, NULL, 81),
(323, 'Add Rice and Saffron', 'Stir in bomba rice and saffron threads', 3, NULL, 81),
(324, 'Add Seafood and Cook', 'Arrange seafood on top and cook until rice is done', 4, NULL, 81),
-- Recipe 82: Tortilla Espanola
(325, 'Slice Potatoes', 'Peel and thinly slice potatoes and onion', 1, NULL, 82),
(326, 'Fry Potatoes', 'Fry potatoes and onion in olive oil until soft', 2, NULL, 82),
(327, 'Mix with Eggs', 'Drain oil and mix potatoes with beaten eggs', 3, NULL, 82),
(328, 'Cook Tortilla', 'Cook in a pan flip and cook until golden both sides', 4, NULL, 82),
-- Recipe 83: Gazpacho
(329, 'Chop Vegetables', 'Chop tomatoes cucumber pepper and onion roughly', 1, NULL, 83),
(330, 'Blend Ingredients', 'Blend vegetables with garlic olive oil and vinegar', 2, NULL, 83),
(331, 'Season and Chill', 'Season with salt and refrigerate for at least 2 hours', 3, NULL, 83),
(332, 'Serve Cold', 'Serve cold with diced vegetable garnish and croutons', 4, NULL, 83),
-- Recipe 84: Patatas Bravas
(333, 'Cut Potatoes', 'Cut potatoes into bite-sized cubes', 1, NULL, 84),
(334, 'Fry Potatoes', 'Fry potatoes in hot oil until golden and crispy', 2, NULL, 84),
(335, 'Make Bravas Sauce', 'Blend tomatoes with smoked paprika and chili', 3, NULL, 84),
(336, 'Serve with Sauce', 'Drizzle bravas sauce and aioli over potatoes', 4, NULL, 84),
-- Recipe 85: Churros con Chocolate
(337, 'Make Choux Dough', 'Boil water with butter and sugar then add flour', 1, NULL, 85),
(338, 'Pipe and Fry', 'Pipe into hot oil and fry until golden', 2, NULL, 85),
(339, 'Drain and Coat', 'Drain and roll in cinnamon sugar', 3, NULL, 85),
(340, 'Serve with Chocolate', 'Serve with thick melted dark chocolate for dipping', 4, NULL, 85),
-- Recipe 86: Pulpo a la Gallega
(341, 'Tenderize Octopus', 'Boil octopus with a cork until tender', 1, NULL, 86),
(342, 'Slice Tentacles', 'Slice octopus tentacles into rounds', 2, NULL, 86),
(343, 'Arrange on Plate', 'Arrange octopus slices on a wooden plate', 3, NULL, 86),
(344, 'Season and Serve', 'Drizzle olive oil sprinkle paprika and sea salt', 4, NULL, 86),
-- Recipe 87: Pisto
(345, 'Chop Vegetables', 'Dice zucchini bell peppers onion and tomato', 1, NULL, 87),
(346, 'Sauté Each Separately', 'Sauté each vegetable separately then combine', 2, NULL, 87),
(347, 'Simmer Together', 'Add tomatoes and simmer until thick and melded', 3, NULL, 87),
(348, 'Serve with Egg', 'Serve with a fried egg on top and crusty bread', 4, NULL, 87),
-- Recipe 88: Crema Catalana
(349, 'Infuse Milk', 'Heat milk with lemon zest and cinnamon stick', 1, NULL, 88),
(350, 'Temper Yolks', 'Whisk yolks with sugar then slowly add hot milk', 2, NULL, 88),
(351, 'Cook Custard', 'Cook gently until thickened then chill', 3, NULL, 88),
(352, 'Caramelize Sugar', 'Top with sugar and torch until caramelized', 4, NULL, 88),
-- Recipe 89: Albondigas
(353, 'Make Meatballs', 'Mix ground beef with breadcrumbs garlic and parsley', 1, NULL, 89),
(354, 'Brown Meatballs', 'Fry meatballs in olive oil until browned on all sides', 2, NULL, 89),
(355, 'Prepare Sauce', 'Blend tomatoes with almonds saffron and garlic', 3, NULL, 89),
(356, 'Simmer Together', 'Add meatballs to sauce and simmer for 20 minutes', 4, NULL, 89),
-- Recipe 90: Gambas al Ajillo
(357, 'Heat Olive Oil', 'Pour generous olive oil in a small cazuela', 1, NULL, 90),
(358, 'Add Garlic and Chili', 'Add sliced garlic and dried chili to cold oil', 2, NULL, 90),
(359, 'Cook Shrimp', 'Add peeled shrimp and cook until just pink', 3, NULL, 90),
(360, 'Serve Sizzling', 'Sprinkle parsley and serve sizzling with bread', 4, NULL, 90),
-- Recipe 91: Greek Salad
(361, 'Chop Vegetables', 'Chop tomatoes cucumber and red onion into chunks', 1, NULL, 91),
(362, 'Add Olives', 'Add Kalamata olives and bell pepper slices', 2, NULL, 91),
(363, 'Top with Feta', 'Place a thick slice of feta cheese on top', 3, NULL, 91),
(364, 'Dress and Serve', 'Drizzle olive oil and oregano serve without mixing', 4, NULL, 91),
-- Recipe 92: Moussaka
(365, 'Slice Eggplant', 'Slice eggplant and potato into thin rounds', 1, NULL, 92),
(366, 'Fry Slices', 'Fry eggplant and potato slices until golden', 2, NULL, 92),
(367, 'Make Meat Sauce', 'Cook lamb with onion tomato and cinnamon', 3, NULL, 92),
(368, 'Layer and Bake', 'Layer potatoes eggplant meat sauce and béchamel then bake', 4, NULL, 92),
-- Recipe 93: Souvlaki
(369, 'Marinate Pork', 'Marinate pork cubes in lemon olive oil and oregano', 1, NULL, 93),
(370, 'Skewer Meat', 'Thread marinated pork onto metal skewers', 2, NULL, 93),
(371, 'Grill Skewers', 'Grill over high heat turning until charred and cooked', 3, NULL, 93),
(372, 'Serve in Pita', 'Serve skewers in warm pita with tzatziki and tomato', 4, NULL, 93),
-- Recipe 94: Tzatziki
(373, 'Grate Cucumber', 'Grate cucumber and squeeze out excess water', 1, NULL, 94),
(374, 'Mix with Yogurt', 'Combine cucumber with thick Greek yogurt', 2, NULL, 94),
(375, 'Add Garlic and Dill', 'Stir in minced garlic lemon juice and fresh dill', 3, NULL, 94),
(376, 'Season and Chill', 'Season with salt chill for 1 hour and serve', 4, NULL, 94),
-- Recipe 95: Spanakopita
(377, 'Prepare Filling', 'Sauté spinach with onion dill and feta cheese', 1, NULL, 95),
(378, 'Layer Phyllo', 'Brush phyllo sheets with butter and layer in pan', 2, NULL, 95),
(379, 'Bake Pie', 'Add filling cover with more phyllo and bake until golden', 3, NULL, 95),
(380, 'Rest and Serve', 'Let rest for 10 minutes then cut into squares', 4, NULL, 95),
-- Recipe 96: Dolmades
(381, 'Prepare Rice', 'Cook rice with herbs pine nuts and currants', 1, NULL, 96),
(382, 'Wrap Leaves', 'Place rice mixture on grape leaves and roll tightly', 2, NULL, 96),
(383, 'Layer in Pot', 'Line pot with extra leaves and arrange dolmades snugly', 3, NULL, 96),
(384, 'Simmer with Lemon', 'Add water and lemon juice simmer for 40 minutes', 4, NULL, 96),
-- Recipe 97: Baklava
(385, 'Layer Phyllo', 'Butter and layer phyllo sheets in a baking dish', 1, NULL, 97),
(386, 'Add Nut Mixture', 'Sprinkle chopped walnuts and cinnamon between layers', 2, NULL, 97),
(387, 'Bake Until Golden', 'Cut into diamonds and bake at 160°C until golden', 3, NULL, 97),
(388, 'Soak in Syrup', 'Pour honey lemon syrup over hot baklava and cool', 4, NULL, 97),
-- Recipe 98: Avgolemono Soup
(389, 'Cook Rice', 'Simmer rice in chicken broth until tender', 1, NULL, 98),
(390, 'Shred Chicken', 'Add shredded cooked chicken to the broth', 2, NULL, 98),
(391, 'Temper Eggs', 'Whisk eggs with lemon juice then slowly add hot broth', 3, NULL, 98),
(392, 'Combine and Serve', 'Stir egg mixture back into soup and serve immediately', 4, NULL, 98),
-- Recipe 99: Gyros
(393, 'Season Meat', 'Season ground lamb with oregano garlic and spices', 1, NULL, 99),
(394, 'Form and Cook', 'Form into a loaf and roast then slice thinly', 2, NULL, 99),
(395, 'Warm Pita', 'Warm pita bread on a hot griddle', 3, NULL, 99),
(396, 'Assemble Gyros', 'Fill pita with meat tomato onion and tzatziki', 4, NULL, 99),
-- Recipe 100: Feta Stuffed Peppers
(397, 'Prep Peppers', 'Cut bell peppers in half and remove seeds', 1, NULL, 100),
(398, 'Make Filling', 'Mix crumbled feta with rice herbs and pine nuts', 2, NULL, 100),
(399, 'Stuff Peppers', 'Fill pepper halves with the rice and feta mixture', 3, NULL, 100),
(400, 'Bake and Serve', 'Bake at 180°C for 30 minutes until peppers are tender', 4, NULL, 100);

-- =============================================
-- 8. RECIPE_TAG (3-4 tags per recipe)
-- =============================================
INSERT INTO recipe_tag (recipe_id, tag_id) VALUES
-- Italian (1-10)
(1, 9), (1, 10), (1, 18), (2, 1), (2, 9), (2, 20), (2, 27),
(3, 1), (3, 4), (3, 2), (3, 10), (4, 1), (4, 2), (4, 5), (4, 13),
(5, 9), (5, 15), (5, 20), (5, 18), (6, 1), (6, 9), (6, 27), (6, 21),
(7, 3), (7, 28), (8, 1), (8, 2), (8, 18), (9, 1), (9, 11), (9, 2),
(10, 1), (10, 12), (10, 5), (10, 21),
-- Mexican (11-20)
(11, 4), (11, 15), (11, 9), (11, 22), (12, 1), (12, 2), (12, 5), (12, 23),
(13, 3), (13, 19), (13, 2), (14, 4), (14, 15), (14, 9), (14, 20),
(15, 4), (15, 15), (15, 12), (15, 18), (16, 3), (16, 20), (16, 28),
(17, 1), (17, 2), (17, 23), (18, 15), (18, 9), (18, 22), (19, 23), (19, 22), (19, 2),
(20, 15), (20, 9), (20, 28),
-- Japanese (21-30)
(21, 5), (21, 14), (21, 9), (21, 30), (22, 18), (22, 15), (22, 12), (22, 9),
(23, 1), (23, 14), (23, 19), (24, 2), (24, 15), (24, 9), (24, 10),
(25, 1), (25, 2), (25, 5), (25, 12), (26, 2), (26, 15), (26, 23), (26, 17),
(27, 12), (27, 9), (27, 5), (28, 11), (28, 19), (28, 23), (29, 9), (29, 19), (29, 18),
(30, 3), (30, 5), (30, 2),
-- Indian (31-40)
(31, 4), (31, 15), (31, 9), (31, 18), (32, 4), (32, 15), (32, 5), (32, 9),
(33, 1), (33, 4), (33, 9), (33, 21), (34, 1), (34, 11), (34, 19), (34, 4),
(35, 1), (35, 2), (35, 10), (35, 16), (36, 1), (36, 5), (36, 9), (36, 21),
(37, 1), (37, 5), (37, 9), (38, 4), (38, 15), (38, 9), (38, 18),
(39, 15), (39, 9), (39, 5), (40, 5), (40, 2), (40, 8), (40, 10),
-- French (41-50)
(41, 1), (41, 12), (41, 18), (42, 8), (42, 16), (42, 20), (43, 15), (43, 9), (43, 27),
(44, 3), (44, 20), (44, 28), (45, 1), (45, 5), (45, 9), (46, 8), (46, 16), (46, 20),
(47, 14), (47, 12), (47, 9), (48, 3), (48, 20), (48, 22), (49, 15), (49, 9), (49, 18),
(50, 8), (50, 16), (50, 3),
-- American (51-60)
(51, 15), (51, 17), (51, 9), (51, 27), (52, 15), (52, 2), (52, 18), (53, 3), (53, 20),
(54, 1), (54, 18), (54, 2), (55, 15), (55, 19), (55, 9), (55, 18),
(56, 4), (56, 11), (56, 22), (57, 5), (57, 12), (57, 14), (58, 8), (58, 2), (58, 10),
(59, 5), (59, 13), (59, 26), (60, 3), (60, 23), (60, 10),
-- Chinese (61-70)
(61, 4), (61, 15), (61, 2), (61, 19), (62, 11), (62, 22), (62, 10),
(63, 2), (63, 10), (63, 18), (63, 21), (64, 1), (64, 4), (64, 5), (64, 9),
(65, 15), (65, 9), (65, 28), (66, 1), (66, 11), (66, 19), (67, 4), (67, 12), (67, 5),
(68, 2), (68, 9), (68, 10), (69, 12), (69, 11), (69, 18),
(70, 15), (70, 17), (70, 9),
-- Thai (71-80)
(71, 4), (71, 14), (71, 2), (71, 19), (72, 4), (72, 9), (72, 5), (72, 14),
(73, 4), (73, 14), (73, 12), (73, 2), (74, 4), (74, 15), (74, 9), (74, 5),
(75, 2), (75, 9), (75, 10), (76, 1), (76, 5), (76, 11), (76, 30),
(77, 3), (77, 5), (77, 7), (78, 5), (78, 12), (78, 14), (79, 4), (79, 9), (79, 5),
(80, 11), (80, 17), (80, 23),
-- Spanish (81-90)
(81, 14), (81, 9), (81, 21), (81, 27), (82, 1), (82, 16), (82, 2), (83, 1), (83, 5), (83, 13), (83, 2),
(84, 1), (84, 11), (84, 19), (84, 4), (85, 3), (85, 23), (85, 10),
(86, 14), (86, 11), (86, 5), (87, 1), (87, 5), (87, 9), (88, 3), (88, 28), (88, 5),
(89, 15), (89, 11), (89, 9), (90, 11), (90, 14), (90, 2), (90, 10),
-- Greek (91-100)
(91, 1), (91, 5), (91, 13), (91, 2), (92, 15), (92, 9), (92, 20), (93, 15), (93, 2), (93, 17),
(94, 1), (94, 2), (94, 5), (94, 24), (95, 1), (95, 20), (95, 11), (96, 1), (96, 11), (96, 5),
(97, 3), (97, 20), (97, 28), (98, 5), (98, 12), (98, 2), (99, 15), (99, 2), (99, 18),
(100, 1), (100, 20), (100, 5), (100, 11);

-- =============================================
-- 9. RECIPE_INGREDIENT (4-7 ingredients per recipe)
-- =============================================
INSERT INTO recipe_ingredient (recipe_id, ingredient_id) VALUES
-- Italian (1-10)
(1, 1), (1, 11), (1, 3), (1, 24), (2, 10), (2, 2), (2, 3), (2, 22), (2, 9),
(3, 1), (3, 8), (3, 16), (3, 9), (3, 2), (4, 2), (4, 3), (4, 22), (4, 9), (4, 23),
(5, 1), (5, 3), (5, 2), (5, 7), (5, 8), (5, 15), (6, 4), (6, 3), (6, 7), (6, 8), (6, 15), (6, 49),
(7, 11), (7, 14), (7, 12), (7, 25), (7, 3), (8, 1), (8, 3), (8, 15), (8, 12),
(9, 2), (9, 22), (9, 8), (9, 9), (9, 10), (10, 2), (10, 7), (10, 35), (10, 1), (10, 19),
-- Mexican (11-20)
(11, 5), (11, 7), (11, 8), (11, 16), (11, 38), (11, 26), (12, 20), (12, 7), (12, 37), (12, 23), (12, 26),
(13, 10), (13, 14), (13, 11), (13, 15), (13, 41), (14, 5), (14, 7), (14, 8), (14, 3),
(15, 5), (15, 7), (15, 8), (15, 16), (15, 19), (16, 11), (16, 14), (16, 12), (16, 10),
(17, 10), (17, 3), (17, 16), (17, 7), (18, 10), (18, 4), (18, 19), (18, 15), (18, 2),
(19, 3), (19, 19), (19, 2), (19, 20), (19, 7), (20, 5), (20, 7), (20, 8), (20, 16), (20, 26),
-- Japanese (21-30)
(21, 4), (21, 6), (21, 20), (21, 21), (22, 33), (22, 5), (22, 11), (22, 17), (22, 27),
(23, 10), (23, 11), (23, 7), (23, 16), (24, 5), (24, 17), (24, 8), (24, 31),
(25, 18), (25, 17), (25, 7), (25, 6), (26, 5), (26, 17), (26, 8), (26, 32),
(27, 33), (27, 17), (27, 32), (27, 11), (28, 5), (28, 7), (28, 8), (28, 10), (28, 27),
(29, 10), (29, 11), (29, 7), (29, 5), (30, 12), (30, 14), (30, 25), (30, 11),
-- Indian (31-40)
(31, 5), (31, 39), (31, 2), (31, 8), (31, 27), (31, 50), (32, 5), (32, 2), (32, 12), (32, 15), (32, 50),
(33, 4), (33, 35), (33, 39), (33, 49), (33, 50), (33, 7), (34, 10), (34, 36), (34, 29), (34, 4),
(35, 10), (35, 39), (35, 8), (35, 15), (36, 19), (36, 2), (36, 8), (36, 15), (36, 50),
(37, 18), (37, 8), (37, 27), (37, 50), (38, 5), (38, 39), (38, 7), (38, 8), (38, 50),
(39, 5), (39, 39), (39, 7), (39, 50), (39, 12), (40, 39), (40, 12), (40, 14), (40, 41),
-- French (41-50)
(41, 7), (41, 15), (41, 3), (41, 44), (42, 10), (42, 15), (42, 11), (42, 12), (42, 14),
(43, 5), (43, 34), (43, 7), (43, 8), (43, 46), (44, 12), (44, 25), (44, 11), (44, 14),
(45, 16), (45, 7), (45, 8), (45, 2), (45, 9), (46, 10), (46, 11), (46, 12), (46, 3), (46, 15),
(47, 6), (47, 2), (47, 7), (47, 49), (47, 9), (48, 10), (48, 11), (48, 14), (48, 13),
(49, 5), (49, 7), (49, 35), (49, 46), (49, 34), (50, 10), (50, 11), (50, 15), (50, 21), (50, 14),
-- American (51-60)
(51, 5), (51, 7), (51, 8), (51, 2), (51, 31), (52, 5), (52, 3), (52, 7), (52, 2), (52, 10),
(53, 10), (53, 15), (53, 14), (53, 41), (53, 21), (54, 1), (54, 3), (54, 15), (54, 12),
(55, 5), (55, 10), (55, 11), (55, 30), (55, 24), (56, 5), (56, 15), (56, 4), (56, 8),
(57, 6), (57, 15), (57, 7), (57, 8), (57, 36), (58, 10), (58, 11), (58, 15), (58, 12), (58, 14),
(59, 5), (59, 11), (59, 20), (59, 2), (59, 7), (60, 10), (60, 15), (60, 14), (60, 11), (60, 13),
-- Chinese (61-70)
(61, 5), (61, 8), (61, 27), (61, 17), (61, 32), (62, 5), (62, 6), (62, 8), (62, 27), (62, 10),
(63, 4), (63, 11), (63, 35), (63, 17), (63, 32), (64, 18), (64, 5), (64, 8), (64, 16), (64, 17),
(65, 5), (65, 17), (65, 8), (65, 31), (66, 5), (66, 35), (66, 7), (66, 10), (66, 17),
(67, 18), (67, 34), (67, 8), (67, 17), (67, 11), (68, 33), (68, 5), (68, 35), (68, 7), (68, 17),
(69, 5), (69, 8), (69, 27), (69, 10), (69, 17), (70, 5), (70, 31), (70, 17), (70, 8), (70, 32),
-- Thai (71-80)
(71, 33), (71, 6), (71, 11), (71, 37), (71, 28), (72, 5), (72, 28), (72, 8), (72, 27), (72, 22),
(73, 6), (73, 34), (73, 37), (73, 8), (73, 27), (74, 5), (74, 28), (74, 36), (74, 29), (74, 37),
(75, 33), (75, 5), (75, 11), (75, 17), (75, 7), (76, 33), (76, 6), (76, 26), (76, 35), (76, 8),
(77, 4), (77, 28), (77, 14), (77, 23), (78, 5), (78, 28), (78, 34), (78, 37), (78, 8),
(79, 5), (79, 28), (79, 16), (79, 22), (79, 7), (80, 5), (80, 28), (80, 50), (80, 27), (80, 30),
-- Spanish (81-90)
(81, 4), (81, 6), (81, 49), (81, 8), (81, 7), (82, 11), (82, 36), (82, 7), (82, 9),
(83, 2), (83, 7), (83, 8), (83, 9), (83, 16), (84, 36), (84, 2), (84, 30), (84, 8), (84, 9),
(85, 10), (85, 14), (85, 15), (85, 13), (86, 6), (86, 9), (86, 30), (86, 23),
(87, 16), (87, 7), (87, 8), (87, 2), (87, 9), (88, 11), (88, 14), (88, 12), (88, 21), (88, 41),
(89, 5), (89, 10), (89, 8), (89, 2), (89, 9), (90, 6), (90, 8), (90, 9), (90, 16), (90, 26),
-- Greek (91-100)
(91, 2), (91, 7), (91, 47), (91, 3), (91, 9), (92, 11), (92, 36), (92, 5), (92, 2), (92, 10), (92, 41),
(93, 5), (93, 21), (93, 9), (93, 7), (93, 8), (94, 39), (94, 7), (94, 8), (94, 37), (94, 9),
(95, 10), (95, 15), (95, 3), (95, 7), (95, 11), (96, 4), (96, 21), (96, 7), (96, 9), (96, 8),
(97, 10), (97, 15), (97, 31), (97, 41), (97, 21), (98, 5), (98, 4), (98, 11), (98, 21),
(99, 5), (99, 2), (99, 7), (99, 10), (99, 39), (100, 16), (100, 4), (100, 3), (100, 7), (100, 8);

-- =============================================
-- 10. RATINGS (~400, 4 per recipe, varied scores by user)
-- =============================================
INSERT INTO rating (id, title, description, stars, recipe_id, user_id) VALUES
-- Recipe 1: Spaghetti Carbonara (by alice) -- HIGH
(1, 'Incredible!', 'The creamiest carbonara I have ever made', 5, 1, 'bob'),
(2, 'Perfect recipe', 'Followed exactly and it turned out amazing', 5, 1, 'charlie'),
(3, 'Restaurant quality', 'Tastes better than most Italian restaurants', 4, 1, 'diana'),
(4, 'New favorite', 'This is my go-to carbonara recipe now', 5, 1, 'eve'),
-- Recipe 2: Margherita Pizza (by alice) -- HIGH
(5, 'Authentic taste', 'Tastes just like Naples', 5, 2, 'frank'),
(6, 'Perfect crust', 'The crust was crispy and chewy at the same time', 4, 2, 'grace'),
(7, 'Family loved it', 'Even the kids helped make this one', 4, 2, 'henry'),
(8, 'So fresh', 'The fresh basil makes all the difference', 5, 2, 'ivy'),
-- Recipe 3: Penne Arrabbiata (by alice) -- HIGH
(9, 'Spicy perfection', 'Just the right amount of heat', 5, 3, 'bob'),
(10, 'Quick and easy', 'Made this on a busy weeknight and it was perfect', 4, 3, 'diana'),
(11, 'Simple but delicious', 'Minimal ingredients maximum flavor', 5, 3, 'eve'),
(12, 'Great kick', 'The chili flakes give it a wonderful kick', 4, 3, 'jack'),
-- Recipe 4: Caprese Salad (by alice) -- MEDIUM-HIGH
(13, 'Classic done right', 'Simple elegant and delicious', 4, 4, 'charlie'),
(14, 'Fresh and light', 'Perfect summer salad', 5, 4, 'frank'),
(15, 'Good but simple', 'Hard to mess this one up', 4, 4, 'grace'),
(16, 'Needs better tomatoes', 'Only as good as your tomatoes are', 4, 4, 'henry'),
-- Recipe 5: Lasagna (by alice) -- HIGH
(17, 'Best lasagna ever', 'Worth every minute of preparation', 5, 5, 'bob'),
(18, 'Hearty and satisfying', 'Perfect comfort food for cold days', 5, 5, 'eve'),
(19, 'Family favorite', 'Made this for Sunday dinner and everyone cleaned their plates', 5, 5, 'ivy'),
(20, 'Labor of love', 'Time consuming but absolutely worth it', 4, 5, 'jack'),
-- Recipe 6: Risotto alla Milanese (by alice) -- MEDIUM-HIGH
(21, 'Creamy perfection', 'The saffron gives it a beautiful color and flavor', 5, 6, 'bob'),
(22, 'Needs patience', 'You really have to stir constantly', 4, 6, 'charlie'),
(23, 'Delicious', 'Tastes like Milan', 4, 6, 'diana'),
(24, 'Good but rich', 'A bit heavy for a starter but very tasty', 4, 6, 'frank'),
-- Recipe 7: Tiramisu (by alice) -- HIGH
(25, 'Heavenly', 'Light creamy and utterly divine', 5, 7, 'bob'),
(26, 'Perfect dessert', 'Made this for a dinner party and it was a hit', 5, 7, 'charlie'),
(27, 'Authentic Italian', 'Tastes like the tiramisu I had in Rome', 5, 7, 'diana'),
(28, 'So easy', 'Much simpler to make than I thought', 4, 7, 'grace'),
-- Recipe 8: Fettuccine Alfredo (by alice) -- MEDIUM
(29, 'Rich and creamy', 'Very indulgent and satisfying', 4, 8, 'henry'),
(30, 'Simple recipe', 'Few ingredients but great result', 4, 8, 'ivy'),
(31, 'Too heavy', 'A bit too rich for my taste', 3, 8, 'jack'),
(32, 'Good comfort food', 'Perfect when you need something comforting', 4, 8, 'frank'),
-- Recipe 9: Bruschetta (by alice) -- HIGH
(33, 'Perfect appetizer', 'So fresh and flavorful', 5, 9, 'bob'),
(34, 'Summer classic', 'Made with garden tomatoes and it was incredible', 5, 9, 'charlie'),
(35, 'Quick and tasty', 'Ready in minutes and always a crowd pleaser', 5, 9, 'diana'),
(36, 'Great starter', 'The garlic rub makes all the difference', 4, 9, 'eve'),
-- Recipe 10: Minestrone Soup (by alice) -- MEDIUM
(37, 'Hearty and healthy', 'Perfect winter warmer', 4, 10, 'bob'),
(38, 'Good but basic', 'A solid minestrone nothing revolutionary', 3, 10, 'charlie'),
(39, 'Great for meal prep', 'Tastes even better the next day', 4, 10, 'diana'),
(40, 'Filling', 'Very satisfying and packed with vegetables', 4, 10, 'frank'),
-- Recipe 11: Tacos al Pastor (by bob) -- LOW-MEDIUM
(41, 'Not authentic', 'Good but not like the ones in Mexico', 3, 11, 'alice'),
(42, 'Great flavor', 'The pineapple really makes it', 4, 11, 'charlie'),
(43, 'Average tacos', 'Decent but needed more seasoning', 3, 11, 'diana'),
(44, 'Pretty good', 'Would make again with more spice', 3, 11, 'eve'),
-- Recipe 12: Guacamole (by bob) -- HIGH
(45, 'Perfect guac', 'Fresh creamy and perfectly seasoned', 5, 12, 'alice'),
(46, 'Best recipe', 'Never buying store guac again', 5, 12, 'charlie'),
(47, 'So fresh', 'The lime and cilantro balance is perfect', 4, 12, 'diana'),
(48, 'Crowd pleaser', 'Disappears every time I make it', 5, 12, 'frank'),
-- Recipe 13: Churros (by bob) -- MEDIUM-HIGH
(49, 'Crispy and sweet', 'Better than fair churros', 4, 13, 'alice'),
(50, 'Fun to make', 'Great weekend project with kids', 4, 13, 'eve'),
(51, 'Needs practice', 'My first batch was a mess but second was perfect', 3, 13, 'grace'),
(52, 'Delicious', 'The cinnamon sugar coating is addictive', 4, 13, 'henry'),
-- Recipe 14: Enchiladas Verdes (by bob) -- MEDIUM-HIGH
(53, 'Delicious enchiladas', 'The green salsa is fantastic', 4, 14, 'alice'),
(54, 'Authentic', 'Tastes like abuela used to make', 5, 14, 'charlie'),
(55, 'Good but mild', 'Could use more heat for my taste', 3, 14, 'diana'),
(56, 'Easy weeknight meal', 'Quick to assemble and bake', 4, 14, 'jack'),
-- Recipe 15: Pozole (by bob) -- MEDIUM
(57, 'Hearty soup', 'So warming and flavorful', 4, 15, 'alice'),
(58, 'Too much work', 'Great result but takes forever', 3, 15, 'eve'),
(59, 'Authentic flavors', 'The hominy is perfect', 4, 15, 'ivy'),
(60, 'Needs more spice', 'Added extra chili and it was better', 3, 15, 'jack'),
-- Recipe 16: Tres Leches Cake (by bob) -- HIGH
(61, 'Incredibly moist', 'The three milks make it so tender', 5, 16, 'alice'),
(62, 'Perfect dessert', 'Light and not too sweet', 4, 16, 'charlie'),
(63, 'Family favorite', 'Requested for every birthday now', 5, 16, 'diana'),
(64, 'Soaking is key', 'Dont rush the soaking process', 4, 16, 'frank'),
-- Recipe 17: Quesadillas (by bob) -- HIGH
(65, 'Simple and perfect', 'Crispy cheesy and so good', 5, 17, 'alice'),
(66, 'Quick lunch', 'Ready in 10 minutes and always satisfying', 5, 17, 'eve'),
(67, 'Great snack', 'Perfect for after school or late night', 4, 17, 'grace'),
(68, 'Versatile', 'You can put anything in these', 4, 17, 'henry'),
-- Recipe 18: Burritos (by bob) -- MEDIUM
(69, 'Loaded and tasty', 'Packed with flavor', 4, 18, 'alice'),
(70, 'Too heavy', 'Couldnt finish it but it was good', 3, 18, 'charlie'),
(71, 'Good meal prep', 'Make a batch and freeze for later', 4, 18, 'diana'),
(72, 'Solid recipe', 'Nothing special but gets the job done', 3, 18, 'eve'),
-- Recipe 19: Nachos Supreme (by bob) -- MEDIUM-HIGH
(73, 'Party favorite', 'Made these for game night and they vanished', 4, 19, 'alice'),
(74, 'So indulgent', 'Loaded with everything', 4, 19, 'charlie'),
(75, 'Good but messy', 'Tastes great but hard to eat gracefully', 3, 19, 'frank'),
(76, 'Great sharing food', 'Perfect for groups', 5, 19, 'grace'),
-- Recipe 20: Tamales (by bob) -- LOW-MEDIUM
(77, 'Too much work', 'Delicious but took me all day', 3, 20, 'alice'),
(78, 'Not bad', 'First time making tamales and they turned out okay', 3, 20, 'charlie'),
(79, 'Authentic', 'Tastes just like my grandmothers', 5, 20, 'diana'),
(80, 'Masa was dry', 'I think I overmixed the masa', 2, 20, 'henry'),
-- Recipe 21: Sushi Rolls (by charlie) -- HIGH
(81, 'Professional quality', 'Looks and tastes like a sushi restaurant', 5, 21, 'alice'),
(82, 'So fresh', 'The fish was perfectly fresh and delicious', 5, 21, 'bob'),
(83, 'Great technique', 'The rolling instructions were very clear', 4, 21, 'diana'),
(84, 'Fun to make', 'Sushi night is now a weekly tradition', 5, 21, 'eve'),
-- Recipe 22: Tonkotsu Ramen (by charlie) -- VERY HIGH
(85, 'Worth the wait', 'The broth is incredible after hours of simmering', 5, 22, 'alice'),
(86, 'Best ramen ever', 'Better than most ramen shops I have been to', 5, 22, 'bob'),
(87, 'Labor intensive', 'Takes all day but the result is phenomenal', 4, 22, 'frank'),
(88, 'Perfect comfort', 'The ultimate comfort food bowl', 5, 22, 'grace'),
-- Recipe 23: Tempura (by charlie) -- MEDIUM
(89, 'Light and crispy', 'The batter was perfectly light', 4, 23, 'alice'),
(90, 'Good technique', 'The ice water trick really works', 4, 23, 'bob'),
(91, 'Soggy on reheating', 'Great fresh but not good left over', 3, 23, 'diana'),
(92, 'Fun appetizer', 'Everyone loved the variety', 4, 23, 'ivy'),
-- Recipe 24: Teriyaki Chicken (by charlie) -- HIGH
(93, 'Better than takeout', 'So much better than ordering in', 5, 24, 'alice'),
(94, 'Glaze is perfect', 'The homemade teriyaki sauce is amazing', 4, 24, 'bob'),
(95, 'Quick dinner', 'Ready in under 30 minutes and delicious', 5, 24, 'eve'),
(96, 'Family approved', 'My picky kids loved this', 4, 24, 'henry'),
-- Recipe 25: Miso Soup (by charlie) -- HIGH
(97, 'Simple perfection', 'So simple but so satisfying', 5, 25, 'alice'),
(98, 'Restaurant quality', 'Tastes just like my favorite sushi spot', 5, 25, 'bob'),
(99, 'Daily staple', 'I make this almost every morning now', 4, 25, 'diana'),
(100, 'Good base', 'Easy to customize with different toppings', 4, 25, 'frank'),
-- Recipe 26: Yakitori (by charlie) -- MEDIUM-HIGH
(101, 'Great appetizer', 'Perfect for a party or starter', 4, 26, 'alice'),
(102, 'Char is key', 'The slightly charred bits are the best part', 5, 26, 'bob'),
(103, 'Needs proper skewers', 'Metal skewers work much better than bamboo', 3, 26, 'grace'),
(104, 'Delicious glaze', 'The tare sauce caramelizes beautifully', 4, 26, 'henry'),
-- Recipe 27: Udon Noodles (by charlie) -- MEDIUM
(105, 'Comforting bowl', 'Warm filling and delicious', 4, 27, 'alice'),
(106, 'Good but simple', 'Nice and straightforward recipe', 3, 27, 'bob'),
(107, 'Great for cold days', 'The broth is so warming', 4, 27, 'diana'),
(108, 'Noodles were mushy', 'I think I overcooked the udon', 3, 27, 'ivy'),
-- Recipe 28: Gyoza (by charlie) -- HIGH
(109, 'Perfect potstickers', 'The crispy bottom is amazing', 5, 28, 'alice'),
(110, 'Worth the folding', 'Takes time to fold but so worth it', 4, 28, 'bob'),
(111, 'Freeze extras', 'Make a double batch and freeze some', 5, 28, 'eve'),
(112, 'Great dipping sauce', 'The dipping sauce recipe is perfect', 4, 28, 'jack'),
-- Recipe 29: Okonomiyaki (by charlie) -- MEDIUM
(113, 'Interesting dish', 'Never had anything like it before', 4, 29, 'alice'),
(114, 'Fun to customize', 'Great for using up leftovers', 4, 29, 'bob'),
(115, 'Sauces matter', 'The toppings really make this dish', 3, 29, 'diana'),
(116, 'Good but filling', 'Very heavy for one person', 3, 29, 'frank'),
-- Recipe 30: Matcha Ice Cream (by charlie) -- HIGH
(117, 'Unique dessert', 'The matcha flavor is authentic and delicious', 5, 30, 'alice'),
(118, 'Smooth and creamy', 'Perfect texture and beautiful color', 5, 30, 'bob'),
(119, 'No ice cream maker?', 'Made it without a machine and it still worked', 4, 30, 'diana'),
(120, 'Addictive', 'Could not stop eating this', 5, 30, 'henry'),
-- Recipe 31: Chicken Tikka Masala (by diana) -- HIGH
(121, 'Better than takeout', 'The best tikka masala I have ever made', 5, 31, 'alice'),
(122, 'Incredible flavor', 'The marinade makes the chicken so tender', 5, 31, 'bob'),
(123, 'Restaurant quality', 'Tastes like my favorite Indian restaurant', 5, 31, 'charlie'),
(124, 'Perfect spice level', 'Not too hot but full of flavor', 4, 31, 'eve'),
-- Recipe 32: Butter Chicken (by diana) -- HIGH
(125, 'Rich and creamy', 'The sauce is absolutely divine', 5, 32, 'alice'),
(126, 'Crowd favorite', 'Made this for a dinner party and everyone begged for the recipe', 5, 32, 'bob'),
(127, 'Melt in mouth', 'The chicken is so tender and the sauce so creamy', 5, 32, 'frank'),
(128, 'Perfect comfort', 'My go-to comfort food now', 5, 32, 'grace'),
-- Recipe 33: Vegetable Biryani (by diana) -- MEDIUM-HIGH
(129, 'Aromatic and delicious', 'The spices smell incredible while cooking', 5, 33, 'alice'),
(130, 'Good but time consuming', 'Many steps but the result is worth it', 4, 33, 'bob'),
(131, 'Great leftovers', 'Tastes even better the next day', 4, 33, 'charlie'),
(132, 'Layer technique', 'The layering is key to perfect biryani', 4, 33, 'eve'),
-- Recipe 34: Samosas (by diana) -- HIGH
(133, 'Crispy and delicious', 'The filling is perfectly spiced', 5, 34, 'alice'),
(134, 'Worth the effort', 'Making samosas from scratch is so rewarding', 4, 34, 'bob'),
(135, 'Freeze well', 'Make extra and freeze for later', 5, 34, 'charlie'),
(136, 'Great party snack', 'Everyone loves these at parties', 5, 34, 'henry'),
-- Recipe 35: Garlic Naan (by diana) -- HIGH
(137, 'Perfect naan', 'Better than any restaurant naan I have had', 5, 35, 'alice'),
(138, 'So soft and buttery', 'The garlic butter is heavenly', 5, 35, 'bob'),
(139, 'Easy to make', 'I was surprised how simple this was', 4, 35, 'grace'),
(140, 'Game changer', 'I will never buy naan again', 5, 35, 'ivy'),
-- Recipe 36: Dal Makhani (by diana) -- HIGH
(141, 'Rich and creamy', 'The slow cooking makes it incredible', 5, 36, 'alice'),
(142, 'Authentic taste', 'Tastes like the dal from my favorite Indian spot', 5, 36, 'bob'),
(143, 'Perfect side dish', 'Goes perfectly with any Indian meal', 4, 36, 'charlie'),
(144, 'Worth the wait', 'The long simmer really develops the flavor', 5, 36, 'frank'),
-- Recipe 37: Palak Paneer (by diana) -- MEDIUM-HIGH
(145, 'Delicious and healthy', 'So much flavor and nutritious too', 5, 37, 'alice'),
(146, 'Smooth spinach', 'The creamy spinach is perfect', 4, 37, 'bob'),
(147, 'Paneer was great', 'Homemade paneer makes a difference', 4, 37, 'charlie'),
(148, 'Good but mild', 'Could use more spice for my taste', 3, 37, 'grace'),
-- Recipe 38: Rogan Josh (by diana) -- HIGH
(149, 'Rich lamb curry', 'The lamb melts in your mouth', 5, 38, 'alice'),
(150, 'Kashmiri perfection', 'The deep red color is beautiful', 5, 38, 'bob'),
(151, 'Complex flavors', 'Every bite has layers of flavor', 5, 38, 'eve'),
(152, 'Tender lamb', 'The slow cooking makes the lamb incredibly tender', 4, 38, 'jack'),
-- Recipe 39: Chicken Korma (by diana) -- HIGH
(153, 'Mild but flavorful', 'Perfect for when you want something creamy', 5, 39, 'alice'),
(154, 'Family favorite', 'Even my kids love this mild curry', 5, 39, 'bob'),
(155, 'Cashew magic', 'The cashew paste makes it so rich', 4, 39, 'charlie'),
(156, 'Great introduction', 'Perfect curry for people new to Indian food', 4, 39, 'ivy'),
-- Recipe 40: Mango Lassi (by diana) -- HIGH
(157, 'Refreshing drink', 'Perfect for hot days', 5, 40, 'alice'),
(158, 'Sweet perfection', 'The mango makes it naturally sweet', 5, 40, 'bob'),
(159, 'Restaurant quality', 'Tastes just like the lassi at my favorite Indian restaurant', 5, 40, 'charlie'),
(160, 'Quick and easy', 'Ready in 5 minutes and always delicious', 4, 40, 'eve'),
-- Recipe 41: French Onion Soup (by eve) -- HIGH
(161, 'Deep rich flavor', 'The caramelized onions are incredible', 5, 41, 'alice'),
(162, 'Perfect winter soup', 'Worth the time to caramelize the onions properly', 5, 41, 'bob'),
(163, 'Cheesy perfection', 'The melted gruyere on top is heavenly', 4, 41, 'charlie'),
(164, 'Restaurant quality', 'Better than any French onion soup I have had out', 5, 41, 'diana'),
-- Recipe 42: Croissants (by eve) -- LOW
(165, 'Too difficult', 'Way too much work and mine didnt turn out well', 2, 42, 'alice'),
(166, 'Butter leak', 'The butter leaked out while baking', 2, 42, 'bob'),
(167, 'Needs more butter', 'Followed exactly but they were dry', 3, 42, 'charlie'),
(168, 'Not worth it', 'Store bought is almost as good', 1, 42, 'frank'),
-- Recipe 43: Coq au Vin (by eve) -- LOW
(169, 'Too salty', 'The wine reduction made it very salty', 2, 43, 'alice'),
(170, 'Not tender enough', 'My chicken was still tough after braising', 1, 43, 'bob'),
(171, 'Good but heavy', 'Very rich and heavy for a weeknight', 3, 43, 'charlie'),
(172, 'Needs more time', 'I should have braised it longer', 2, 43, 'grace'),
-- Recipe 44: Creme Brulee (by eve) -- HIGH
(173, 'Perfect custard', 'Silky smooth and the caramelized sugar cracks perfectly', 5, 44, 'alice'),
(174, 'Impressive dessert', 'Looks so fancy but is surprisingly simple', 5, 44, 'bob'),
(175, 'Kitchen torch needed', 'Really need a torch for the best result', 4, 44, 'diana'),
(176, 'So satisfying', 'The crack of the sugar is so satisfying', 5, 44, 'henry'),
-- Recipe 45: Ratatouille (by eve) -- MEDIUM-HIGH
(177, 'Beautiful dish', 'The layered vegetables look stunning', 5, 45, 'alice'),
(178, 'Tastes like summer', 'Perfect way to use garden vegetables', 4, 45, 'bob'),
(179, 'Good but bland', 'Needs more herbs and seasoning', 3, 45, 'charlie'),
(180, 'Healthy and delicious', 'Great for a light dinner', 4, 45, 'frank'),
-- Recipe 46: Quiche Lorraine (by eve) -- MEDIUM
(181, 'Classic quiche', 'The filling is perfectly creamy', 4, 46, 'alice'),
(182, 'Crust was soggy', 'The bottom crust didnt crisp up enough', 3, 46, 'bob'),
(183, 'Perfect brunch', 'Great for a weekend brunch with salad', 4, 46, 'charlie'),
(184, 'Good but rich', 'A small slice goes a long way', 3, 46, 'frank'),
-- Recipe 47: Bouillabaisse (by eve) -- MEDIUM
(185, 'Complex flavors', 'The saffron and fennel make it special', 4, 47, 'alice'),
(186, 'Hard to source', 'Some ingredients are hard to find', 3, 47, 'bob'),
(187, 'Worth the effort', 'Tastes like the South of France', 4, 47, 'charlie'),
(188, 'Good seafood soup', 'Not quite as good as Marseille but close', 3, 47, 'diana'),
-- Recipe 48: Macarons (by eve) -- LOW
(189, 'So finicky', 'Took three attempts to get right', 2, 48, 'alice'),
(190, 'No feet', 'My macarons didnt develop feet', 1, 48, 'bob'),
(191, 'Taste good though', 'Even the ugly ones taste delicious', 3, 48, 'charlie'),
(192, 'Too sweet', 'Way too much sugar for my taste', 2, 48, 'grace'),
-- Recipe 49: Beef Bourguignon (by eve) -- HIGH
(193, 'Melt in mouth beef', 'The most tender beef I have ever cooked', 5, 49, 'alice'),
(194, 'Company worthy', 'Made this for a dinner party and it was a showstopper', 5, 49, 'bob'),
(195, 'Rich and deep', 'The wine sauce is incredibly flavorful', 4, 49, 'diana'),
(196, 'Worth the wait', 'Slow cooking pays off big time', 5, 49, 'henry'),
-- Recipe 50: Crepes Suzette (by eve) -- HIGH
(197, 'Impressive dessert', 'The flambe is so dramatic and fun', 5, 50, 'alice'),
(198, 'Perfect crepes', 'Light thin and perfectly cooked', 4, 50, 'bob'),
(199, 'Orange magic', 'The orange butter sauce is heavenly', 5, 50, 'charlie'),
(200, 'Fun to make', 'Great dinner party trick', 4, 50, 'grace'),
-- Recipe 51: BBQ Ribs (by frank) -- HIGH
(201, 'Fall off bone', 'The most tender ribs I have ever made', 5, 51, 'alice'),
(202, 'Perfect smoke ring', 'Beautiful color and incredible flavor', 5, 51, 'bob'),
(203, 'Best ribs ever', 'Better than any BBQ restaurant', 5, 51, 'charlie'),
(204, 'Rub is amazing', 'The dry rub recipe is perfect', 4, 51, 'diana'),
-- Recipe 52: Classic Cheeseburger (by frank) -- HIGH
(205, 'Perfect burger', 'Juicy flavorful and perfectly cooked', 5, 52, 'alice'),
(206, 'Better than fast food', 'So much better than any drive through', 5, 52, 'bob'),
(207, 'Simple perfection', 'Sometimes simple is best', 5, 52, 'eve'),
(208, 'Crust is key', 'The crust on the patty is amazing', 4, 52, 'henry'),
-- Recipe 53: Apple Pie (by frank) -- HIGH
(209, 'Like grandmas', 'Tastes just like my grandmothers pie', 5, 53, 'alice'),
(210, 'Perfect lattice', 'Beautiful and delicious', 5, 53, 'bob'),
(211, 'Best apple pie', 'The filling is perfectly spiced', 5, 53, 'charlie'),
(212, 'Crust is flaky', 'The butter crust is incredibly flaky', 4, 53, 'diana'),
-- Recipe 54: Mac and Cheese (by frank) -- HIGH
(213, 'Ultimate comfort', 'The creamiest mac and cheese ever', 5, 54, 'alice'),
(214, 'Crispy topping', 'The breadcrumb topping is perfect', 5, 54, 'bob'),
(215, 'Kids love it', 'My children devoured this', 5, 54, 'charlie'),
(216, 'Gooey perfection', 'So cheesy and satisfying', 4, 54, 'grace'),
-- Recipe 55: Fried Chicken (by frank) -- HIGH
(217, 'Crispiest chicken', 'The crunch is incredible', 5, 55, 'alice'),
(218, 'Perfect seasoning', 'The paprika in the flour is genius', 5, 55, 'bob'),
(219, 'Juicy inside', 'Perfectly cooked through and juicy', 5, 55, 'charlie'),
(220, 'Better than KFC', 'Never ordering fried chicken again', 4, 55, 'diana'),
-- Recipe 56: Buffalo Wings (by frank) -- MEDIUM-HIGH
(221, 'Perfect game day', 'Made these for the Super Bowl and they were a hit', 5, 56, 'alice'),
(222, 'Crispy not fried', 'Baking them is so much easier', 4, 56, 'bob'),
(223, 'Sauce is great', 'The homemade sauce is way better than bottled', 4, 56, 'charlie'),
(224, 'Good but mild', 'I like mine spicier but this is a solid base', 3, 56, 'eve'),
-- Recipe 57: Clam Chowder (by frank) -- MEDIUM
(225, 'Creamy and delicious', 'Perfect New England chowder', 4, 57, 'alice'),
(226, 'Good but heavy', 'Very rich and filling', 3, 57, 'bob'),
(227, 'Real clam flavor', 'You can taste the fresh clams', 4, 57, 'charlie'),
(228, 'Needs more clams', 'I added extra clams and it was better', 3, 57, 'diana'),
-- Recipe 58: Pancakes (by frank) -- HIGH
(229, 'Fluffiest pancakes', 'So light and fluffy', 5, 58, 'alice'),
(230, 'Perfect breakfast', 'Made these for Saturday morning and they were perfect', 5, 58, 'bob'),
(231, 'Easy recipe', 'Simple ingredients and amazing results', 4, 58, 'charlie'),
(232, 'Buttermilk is key', 'The buttermilk makes them incredibly tender', 5, 58, 'grace'),
-- Recipe 59: Cobb Salad (by frank) -- MEDIUM-HIGH
(233, 'Loaded salad', 'Everything you could want in a salad', 4, 59, 'alice'),
(234, 'Great for lunch', 'Filling enough for a meal', 4, 59, 'bob'),
(235, 'Beautiful presentation', 'The rows of ingredients look amazing', 5, 59, 'charlie'),
(236, 'Good but basic', 'Nothing special but solid', 3, 59, 'eve'),
-- Recipe 60: Chocolate Chip Cookies (by frank) -- HIGH
(237, 'Perfect cookies', 'Chewy gooey and absolutely perfect', 5, 60, 'alice'),
(238, 'Best recipe ever', 'I have tried dozens of recipes and this is the best', 5, 60, 'bob'),
(239, 'Disappear fast', 'They didnt last an hour in my house', 5, 60, 'charlie'),
(240, 'Chocolate chunks', 'Using chunks instead of chips is genius', 4, 60, 'henry'),
-- Recipe 61: Kung Pao Chicken (by grace) -- HIGH
(241, 'Better than takeout', 'So much better than Chinese takeout', 5, 61, 'alice'),
(242, 'Perfect heat level', 'The Sichuan peppercorns are amazing', 5, 61, 'bob'),
(243, 'Peanuts make it', 'The roasted peanuts add great texture', 4, 61, 'charlie'),
(244, 'Quick stir fry', 'Comes together in minutes', 5, 61, 'diana'),
-- Recipe 62: Dim Sum (by grace) -- HIGH
(245, 'Professional quality', 'Tastes like a real dim sum restaurant', 5, 62, 'alice'),
(246, 'Pleating takes practice', 'The folding technique needs practice but so worth it', 4, 62, 'bob'),
(247, 'Steam to perfection', 'The wrapper texture is perfect when steamed', 5, 62, 'charlie'),
(248, 'Fun weekend project', 'Great for a cooking party with friends', 4, 62, 'eve'),
-- Recipe 63: Egg Fried Rice (by grace) -- HIGH
(249, 'Perfect leftover rice', 'Best way to use up leftover rice', 5, 63, 'alice'),
(250, 'Quick and easy', 'Ready in 10 minutes and always delicious', 5, 63, 'bob'),
(251, 'Wok hei', 'Getting the wok really hot makes a difference', 4, 63, 'charlie'),
(252, 'Better than takeout', 'Will never order fried rice again', 5, 63, 'frank'),
-- Recipe 64: Mapo Tofu (by grace) -- MEDIUM
(253, 'Authentic Sichuan', 'The numbing spice is perfect', 5, 64, 'alice'),
(254, 'Too spicy for me', 'Way too much Sichuan pepper for my taste', 2, 64, 'bob'),
(255, 'Good but simple', 'Tasty but nothing special', 3, 64, 'charlie'),
(256, 'Needs pork', 'I made it vegetarian but it needs the pork for flavor', 3, 64, 'henry'),
-- Recipe 65: Peking Duck (by grace) -- HIGH
(257, 'Showstopper dish', 'The crispy skin is incredible', 5, 65, 'alice'),
(258, 'Labor intensive', 'Takes all day but absolutely worth it', 4, 65, 'bob'),
(259, 'Perfect pancakes', 'Homemade pancakes make it special', 5, 65, 'charlie'),
(260, 'Crispiest skin', 'The air drying technique is genius', 5, 65, 'diana'),
-- Recipe 66: Spring Rolls (by grace) -- MEDIUM-HIGH
(261, 'Crispy and light', 'Not greasy at all', 4, 66, 'alice'),
(262, 'Great appetizer', 'Perfect for parties', 5, 66, 'bob'),
(263, 'Sealing tip', 'Use flour paste to seal the edges', 4, 66, 'charlie'),
(264, 'Good but oily', 'A bit too oily for my liking', 3, 66, 'eve'),
-- Recipe 67: Hot and Sour Soup (by grace) -- MEDIUM
(265, 'Perfect balance', 'The hot and sour are perfectly balanced', 4, 67, 'alice'),
(266, 'Good cold remedy', 'Perfect when you are feeling under the weather', 4, 67, 'bob'),
(267, 'Needs more kick', 'I added extra white pepper and vinegar', 3, 67, 'charlie'),
(268, 'Quick and easy', 'Comes together very quickly', 4, 67, 'frank'),
-- Recipe 68: Chow Mein (by grace) -- HIGH
(269, 'Perfect noodles', 'The noodles have the perfect texture', 5, 68, 'alice'),
(270, 'Better than takeout', 'So much fresher than delivery', 5, 68, 'bob'),
(271, 'Quick weeknight meal', 'Ready in under 20 minutes', 4, 68, 'charlie'),
(272, 'Versatile', 'You can use any vegetables you have on hand', 4, 68, 'diana'),
-- Recipe 69: Wonton Soup (by grace) -- MEDIUM-HIGH
(273, 'Comfort in a bowl', 'The wontons are so flavorful', 5, 69, 'alice'),
(274, 'Great homemade', 'Much better than frozen wontons', 4, 69, 'bob'),
(275, 'Clear broth', 'The clear broth is light and delicious', 4, 69, 'charlie'),
(276, 'Folding takes time', 'The folding is time consuming but relaxing', 3, 69, 'eve'),
-- Recipe 70: Char Siu (by grace) -- HIGH
(277, 'Better than BBQ shop', 'The best char siu I have made at home', 5, 70, 'alice'),
(278, 'Perfect glaze', 'The honey glaze caramelizes beautifully', 5, 70, 'bob'),
(279, 'Great for bao', 'Made bao buns with this filling', 4, 70, 'charlie'),
(280, 'So tender', 'The pork is incredibly tender and flavorful', 5, 70, 'frank'),
-- Recipe 71: Pad Thai (by henry) -- MEDIUM-HIGH
(281, 'Good attempt', 'Not as good as street food in Thailand but close', 4, 71, 'alice'),
(282, 'Tamarind is key', 'The tamarind gives it authentic flavor', 4, 71, 'bob'),
(283, 'Too sweet', 'I found it a bit too sweet', 3, 71, 'charlie'),
(284, 'Great weeknight dinner', 'Quick easy and delicious', 4, 71, 'diana'),
-- Recipe 72: Green Curry (by henry) -- MEDIUM
(285, 'Aromatic curry', 'The green curry paste is very fragrant', 4, 72, 'alice'),
(286, 'Good but mild', 'Could use more heat for an authentic green curry', 3, 72, 'bob'),
(287, 'Coconut heaven', 'The coconut milk makes it so creamy', 4, 72, 'charlie'),
(288, 'Best with chicken', 'Perfect with chicken and Thai basil', 4, 72, 'eve'),
-- Recipe 73: Tom Yum Soup (by henry) -- HIGH
(289, 'Authentic flavor', 'Tastes just like Thailand', 5, 73, 'alice'),
(290, 'Perfect balance', 'Sour spicy and savory all at once', 5, 73, 'bob'),
(291, 'Shrimp are key', 'Fresh shrimp make a huge difference', 4, 73, 'charlie'),
(292, 'So warming', 'Perfect soup for any season', 4, 73, 'diana'),
-- Recipe 74: Massaman Curry (by henry) -- MEDIUM
(293, 'Rich and mild', 'Very rich and comforting', 4, 74, 'alice'),
(294, 'Too sweet for me', 'The sweetness was overpowering', 2, 74, 'bob'),
(295, 'Peanut crunch', 'The peanuts add great texture', 3, 74, 'charlie'),
(296, 'Good but not Massaman', 'Nice curry but not authentic Massaman', 3, 74, 'frank'),
-- Recipe 75: Pad See Ew (by henry) -- HIGH
(297, 'Perfect street food', 'Tastes just like Bangkok street food', 5, 75, 'alice'),
(298, 'Charred flavor', 'The wok char gives it authentic flavor', 5, 75, 'bob'),
(299, 'Wide noodles', 'The wide rice noodles are perfect', 4, 75, 'charlie'),
(300, 'Quick and satisfying', 'Better than takeout', 4, 75, 'diana'),
-- Recipe 76: Fresh Spring Rolls (by henry) -- HIGH
(301, 'Fresh and healthy', 'Perfect light lunch or appetizer', 5, 76, 'alice'),
(302, 'Peanut sauce', 'The peanut dipping sauce is amazing', 5, 76, 'bob'),
(303, 'Fun to assemble', 'Great interactive meal for guests', 4, 76, 'charlie'),
(304, 'Rice paper tips', 'Do not soak the rice paper too long', 4, 76, 'grace'),
-- Recipe 77: Mango Sticky Rice (by henry) -- MEDIUM
(305, 'Sweet perfection', 'Classic Thai dessert done right', 5, 77, 'alice'),
(306, 'Good but sticky', 'My rice was too sticky, need more practice', 3, 77, 'bob'),
(307, 'Coconut cream', 'The warm coconut sauce is heavenly', 4, 77, 'charlie'),
(308, 'Mango must be ripe', 'The mango needs to be perfectly ripe', 4, 77, 'eve'),
-- Recipe 78: Tom Kha Soup (by henry) -- HIGH
(309, 'Creamy and fragrant', 'The galangal and coconut are perfect together', 5, 78, 'alice'),
(310, 'So comforting', 'My favorite Thai soup recipe', 5, 78, 'bob'),
(311, 'Mushrooms matter', 'Use fresh mushrooms for best flavor', 4, 78, 'charlie'),
(312, 'Perfect balance', 'Sour creamy and savory all in one', 4, 78, 'diana'),
-- Recipe 79: Red Curry (by henry) -- LOW-MEDIUM
(313, 'Bland paste', 'The curry paste lacked depth of flavor', 2, 79, 'alice'),
(314, 'Too watery', 'My curry was too thin, needed more simmering', 3, 79, 'bob'),
(315, 'Good but basic', 'A decent red curry nothing outstanding', 3, 79, 'charlie'),
(316, 'Added extra paste', 'I doubled the curry paste and it was better', 3, 79, 'jack'),
-- Recipe 80: Chicken Satay (by henry) -- HIGH
(317, 'Perfect peanut sauce', 'The satay sauce is the star of this recipe', 5, 80, 'alice'),
(318, 'Great appetizer', 'Perfect for parties and gatherings', 5, 80, 'bob'),
(319, 'Marinade is key', 'The turmeric marinade makes it authentic', 4, 80, 'charlie'),
(320, 'Grill or pan fry', 'Works great in a grill pan too', 4, 80, 'diana'),
-- Recipe 81: Seafood Paella (by ivy) -- HIGH
(321, 'Perfect paella', 'The socarrat on the bottom is perfect', 5, 81, 'alice'),
(322, 'Tastes like Valencia', 'So authentic and delicious', 5, 81, 'bob'),
(323, 'Saffron essential', 'Real saffron makes all the difference', 5, 81, 'charlie'),
(324, 'Seafood heaven', 'Loaded with fresh seafood', 5, 81, 'eve'),
-- Recipe 82: Tortilla Espanola (by ivy) -- HIGH
(325, 'Perfect tortilla', 'Creamy in the center and golden outside', 5, 82, 'alice'),
(326, 'Authentic Spanish', 'Tastes like Madrid', 5, 82, 'bob'),
(327, 'Onion is debatable', 'I prefer mine with onion, good recipe', 4, 82, 'charlie'),
(328, 'Great tapas', 'Perfect for a tapas spread', 4, 82, 'diana'),
-- Recipe 83: Gazpacho (by ivy) -- HIGH
(329, 'So refreshing', 'Perfect cold soup for summer', 5, 83, 'alice'),
(330, 'Best gazpacho', 'Creamy and full of vegetable flavor', 5, 83, 'bob'),
(331, 'Chill overnight', 'Tastes much better after overnight chilling', 5, 83, 'charlie'),
(332, 'Great with croutons', 'The crunchy toppings make it', 4, 83, 'frank'),
-- Recipe 84: Patatas Bravas (by ivy) -- HIGH
(333, 'Crispy perfection', 'The potatoes are perfectly crispy', 5, 84, 'alice'),
(334, 'Bravas sauce is key', 'The spicy tomato sauce is incredible', 5, 84, 'bob'),
(335, 'Great tapa', 'Essential Spanish tapas dish', 4, 84, 'charlie'),
(336, 'Double fry', 'Double frying makes them extra crispy', 5, 84, 'diana'),
-- Recipe 85: Churros con Chocolate (by ivy) -- HIGH
(337, 'Crispy and light', 'Better than fairground churros', 5, 85, 'alice'),
(338, 'Chocolate is rich', 'The thick hot chocolate is divine', 5, 85, 'bob'),
(339, 'Best dessert', 'Perfect for a sweet craving', 4, 85, 'charlie'),
(340, 'Pipe carefully', 'The dough can be tricky to pipe', 4, 85, 'eve'),
-- Recipe 86: Pulpo a la Gallega (by ivy) -- MEDIUM
(341, 'Tender octopus', 'The boiling technique makes it tender', 4, 86, 'alice'),
(342, 'Good but chewy', 'My octopus was still a bit chewy', 3, 86, 'bob'),
(343, 'Smoked paprika', 'The smoked paprika is essential', 4, 86, 'charlie'),
(344, 'Spanish classic', 'A classic done well', 4, 86, 'grace'),
-- Recipe 87: Pisto (by ivy) -- HIGH
(345, 'Spanish ratatouille', 'Perfect vegetable dish', 5, 87, 'alice'),
(346, 'Healthy and delicious', 'Great way to eat vegetables', 4, 87, 'bob'),
(347, 'Egg on top', 'The fried egg on top makes it a meal', 5, 87, 'charlie'),
(348, 'Summer vegetables', 'Best with fresh summer vegetables', 4, 87, 'diana'),
-- Recipe 88: Crema Catalana (by ivy) -- HIGH
(349, 'Like creme brulee', 'Better than French creme brulee', 5, 88, 'alice'),
(350, 'Citrus twist', 'The lemon and cinnamon infusion is lovely', 5, 88, 'bob'),
(351, 'Perfect texture', 'Silky smooth custard', 4, 88, 'charlie'),
(352, 'Torch or broil', 'I used the broiler and it worked fine', 4, 88, 'eve'),
-- Recipe 89: Albondigas (by ivy) -- MEDIUM
(353, 'Great meatballs', 'The almond sauce is unique and delicious', 4, 89, 'alice'),
(354, 'Good but dense', 'Meatballs were a bit dense', 3, 89, 'bob'),
(355, 'Sauce is amazing', 'The sauce is the best part', 4, 89, 'charlie'),
(356, 'Spanish comfort', 'Perfect comfort food', 4, 89, 'frank'),
-- Recipe 90: Gambas al Ajillo (by ivy) -- HIGH
(357, 'Best tapas ever', 'So simple but so incredibly good', 5, 90, 'alice'),
(358, 'Garlic lovers dream', 'The garlic infused olive oil is amazing with bread', 5, 90, 'bob'),
(359, 'Sizzling presentation', 'Serve in a hot cazuela for drama', 5, 90, 'charlie'),
(360, 'Quick and impressive', 'Ready in minutes but looks fancy', 4, 90, 'diana'),
-- Recipe 91: Greek Salad (by jack) -- HIGH
(361, 'Perfect summer salad', 'So fresh and satisfying', 5, 91, 'alice'),
(362, 'Feta is key', 'Good quality feta makes all the difference', 5, 91, 'bob'),
(363, 'Simple perfection', 'Simple ingredients done right', 5, 91, 'charlie'),
(364, 'Oregano dressing', 'The dried oregano in the dressing is perfect', 4, 91, 'diana'),
-- Recipe 92: Moussaka (by jack) -- LOW-MEDIUM
(365, 'Too greasy', 'The eggplant absorbed too much oil', 2, 92, 'alice'),
(366, 'Good but heavy', 'Very rich and heavy for a main dish', 3, 92, 'bob'),
(367, 'Time consuming', 'Many steps but the final dish is impressive', 3, 92, 'charlie'),
(368, 'Soggy layers', 'My layers were too wet and slid apart', 2, 92, 'eve'),
-- Recipe 93: Souvlaki (by jack) -- HIGH
(369, 'Perfect grilled meat', 'The lemon oregano marinade is perfection', 5, 93, 'alice'),
(370, 'Tastes like Greece', 'Authentic flavors that transport you', 5, 93, 'bob'),
(371, 'Great with tzatziki', 'Make the tzatziki to go with it', 5, 93, 'charlie'),
(372, 'Quick dinner', 'Perfect for a quick weeknight meal', 4, 93, 'diana'),
-- Recipe 94: Tzatziki (by jack) -- HIGH
(373, 'Creamy and fresh', 'The best tzatziki I have ever made', 5, 94, 'alice'),
(374, 'Strain the yogurt', 'Straining the yogurt makes it thicker', 5, 94, 'bob'),
(375, 'Garlicky goodness', 'Perfect amount of garlic', 5, 94, 'charlie'),
(376, 'Dill makes it', 'Fresh dill is a must', 4, 94, 'frank'),
-- Recipe 95: Spanakopita (by jack) -- MEDIUM-HIGH
(377, 'Flaky perfection', 'The phyllo is perfectly crisp', 4, 95, 'alice'),
(378, 'Great vegetarian', 'So flavorful even meat lovers enjoy it', 5, 95, 'bob'),
(379, 'Phyllo tips', 'Keep phyllo covered with damp cloth while working', 4, 95, 'charlie'),
(380, 'Good but salty', 'The feta made it a bit too salty', 3, 95, 'eve'),
-- Recipe 96: Dolmades (by jack) -- MEDIUM
(381, 'Authentic taste', 'Tastes like my yiayias', 4, 96, 'alice'),
(382, 'Time consuming', 'Very labor intensive but delicious', 3, 96, 'bob'),
(383, 'Herby rice', 'The herb and rice filling is perfect', 4, 96, 'charlie'),
(384, 'Good appetizer', 'Great for a meze spread', 4, 96, 'grace'),
-- Recipe 97: Baklava (by jack) -- HIGH
(385, 'Sticky sweet heaven', 'The honey syrup soaks in perfectly', 5, 97, 'alice'),
(386, 'Best baklava', 'Better than any bakery version', 5, 97, 'bob'),
(387, 'Phyllo layering', 'Take your time with the phyllo layers', 5, 97, 'charlie'),
(388, 'Syrup temperature', 'Pour cold syrup on hot baklava for best results', 4, 97, 'diana'),
-- Recipe 98: Avgolemono Soup (by jack) -- HIGH
(389, 'Comfort in a bowl', 'The lemon egg broth is magical', 5, 98, 'alice'),
(390, 'So comforting', 'Perfect when you are sick or cold', 5, 98, 'bob'),
(391, 'Temper the eggs', 'Tempering the eggs properly is crucial', 4, 98, 'charlie'),
(392, 'Greek penicillin', 'My Greek friend calls this Greek penicillin', 5, 98, 'eve'),
-- Recipe 99: Gyros (by jack) -- MEDIUM-HIGH
(393, 'Tastes like Greece', 'Authentic gyros flavors at home', 4, 99, 'alice'),
(394, 'Great sandwich', 'Perfect lunch or dinner', 5, 99, 'bob'),
(395, 'Lamb blend', 'The blend of spices for the meat is perfect', 4, 99, 'charlie'),
(396, 'Needs vertical spit', 'Hard to replicate the vertical spit at home', 3, 99, 'diana'),
-- Recipe 100: Feta Stuffed Peppers (by jack) -- MEDIUM
(397, 'Great vegetarian', 'Perfect main for meatless Monday', 4, 100, 'alice'),
(398, 'Good but dry', 'The rice filling was a bit dry', 3, 100, 'bob'),
(399, 'Colorful dish', 'Beautiful presentation on a plate', 4, 100, 'charlie'),
(400, 'Needs more flavor', 'Good but needed more herbs and seasoning', 3, 100, 'diana');

-- =============================================
-- 11. USER_SAVED_RECIPE (15 saved recipes per user)
-- =============================================
INSERT INTO user_saved_recipe (user_id, recipe_id) VALUES
-- Alice saves from Bob, Charlie, Diana, Eve, Frank
('alice', 11), ('alice', 13), ('alice', 15),
('alice', 22), ('alice', 24), ('alice', 26),
('alice', 32), ('alice', 34), ('alice', 36),
('alice', 42), ('alice', 44), ('alice', 46),
('alice', 52), ('alice', 54), ('alice', 56),
-- Bob saves from Alice, Charlie, Diana, Eve, Frank
('bob', 1), ('bob', 3), ('bob', 5),
('bob', 21), ('bob', 23), ('bob', 25),
('bob', 31), ('bob', 33), ('bob', 35),
('bob', 41), ('bob', 43), ('bob', 45),
('bob', 51), ('bob', 53), ('bob', 55),
-- Charlie saves from Alice, Bob, Diana, Eve, Frank
('charlie', 2), ('charlie', 4), ('charlie', 6),
('charlie', 12), ('charlie', 14), ('charlie', 16),
('charlie', 37), ('charlie', 38), ('charlie', 39),
('charlie', 47), ('charlie', 49), ('charlie', 50),
('charlie', 57), ('charlie', 58), ('charlie', 60),
-- Diana saves from Alice, Bob, Charlie, Eve, Frank
('diana', 7), ('diana', 8), ('diana', 9),
('diana', 17), ('diana', 18), ('diana', 19),
('diana', 27), ('diana', 28), ('diana', 29),
('diana', 42), ('diana', 44), ('diana', 46),
('diana', 52), ('diana', 54), ('diana', 56),
-- Eve saves from Alice, Bob, Charlie, Diana, Frank
('eve', 1), ('eve', 2), ('eve', 3),
('eve', 11), ('eve', 12), ('eve', 13),
('eve', 21), ('eve', 22), ('eve', 23),
('eve', 31), ('eve', 32), ('eve', 33),
('eve', 51), ('eve', 52), ('eve', 53),
-- Frank saves from Alice, Bob, Charlie, Diana, Eve
('frank', 4), ('frank', 5), ('frank', 6),
('frank', 14), ('frank', 15), ('frank', 16),
('frank', 24), ('frank', 25), ('frank', 26),
('frank', 34), ('frank', 35), ('frank', 36),
('frank', 44), ('frank', 45), ('frank', 46),
-- Grace saves from Alice, Bob, Charlie, Diana, Eve
('grace', 7), ('grace', 8), ('grace', 9),
('grace', 17), ('grace', 18), ('grace', 19),
('grace', 27), ('grace', 28), ('grace', 29),
('grace', 37), ('grace', 38), ('grace', 39),
('grace', 47), ('grace', 48), ('grace', 49),
-- Henry saves from Alice, Bob, Charlie, Diana, Eve
('henry', 1), ('henry', 2), ('henry', 10),
('henry', 11), ('henry', 12), ('henry', 20),
('henry', 21), ('henry', 22), ('henry', 30),
('henry', 31), ('henry', 32), ('henry', 40),
('henry', 41), ('henry', 42), ('henry', 50),
-- Ivy saves from Alice, Bob, Charlie, Diana, Eve
('ivy', 3), ('ivy', 4), ('ivy', 5),
('ivy', 13), ('ivy', 14), ('ivy', 15),
('ivy', 23), ('ivy', 24), ('ivy', 25),
('ivy', 33), ('ivy', 34), ('ivy', 35),
('ivy', 43), ('ivy', 44), ('ivy', 45),
-- Jack saves from Alice, Bob, Charlie, Diana, Eve
('jack', 6), ('jack', 7), ('jack', 8),
('jack', 16), ('jack', 17), ('jack', 18),
('jack', 26), ('jack', 27), ('jack', 28),
('jack', 36), ('jack', 37), ('jack', 38),
('jack', 46), ('jack', 47), ('jack', 48);

-- =============================================
-- 12. FOLLOWING (directed follow relationships)
-- =============================================
INSERT INTO following (user_id, followed_user_id) VALUES
('alice', 'bob'), ('alice', 'charlie'), ('alice', 'diana'), ('alice', 'eve'), ('alice', 'frank'),
('bob', 'alice'), ('bob', 'charlie'), ('bob', 'diana'), ('bob', 'henry'), ('bob', 'jack'),
('charlie', 'alice'), ('charlie', 'bob'), ('charlie', 'grace'), ('charlie', 'ivy'),
('diana', 'alice'), ('diana', 'eve'), ('diana', 'frank'), ('diana', 'jack'),
('eve', 'alice'), ('eve', 'bob'), ('eve', 'diana'), ('eve', 'henry'),
('frank', 'alice'), ('frank', 'eve'), ('frank', 'grace'), ('frank', 'jack'),
('grace', 'alice'), ('grace', 'charlie'), ('grace', 'henry'), ('grace', 'ivy'),
('henry', 'alice'), ('henry', 'bob'), ('henry', 'frank'), ('henry', 'jack'),
('ivy', 'alice'), ('ivy', 'charlie'), ('ivy', 'diana'), ('ivy', 'grace'),
('jack', 'alice'), ('jack', 'eve'), ('jack', 'frank'), ('jack', 'henry');

SET FOREIGN_KEY_CHECKS = 1;
