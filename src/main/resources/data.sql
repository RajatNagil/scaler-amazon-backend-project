-- INSERT SAMPLE CATEGORIES
INSERT INTO category (id, name, description) VALUES
(1, 'Electronics', 'Phones, Laptops, Tablets, Accessories'),
(2, 'Fashion', 'Men and Women Clothing'),
(3, 'Home Appliances', 'Household and Kitchen Appliances');

-- INSERT SAMPLE PRODUCTS
INSERT INTO product (id, title, description, image_url, price, stock, category_id) VALUES
(1, 'Apple iPhone 15', 'Latest Apple iPhone model with A17 chip', 'https://example.com/iphone15.jpg', 89999, 20, 1),
(2, 'Samsung Galaxy S24', 'Flagship Samsung phone with Snapdragon Gen3', 'https://example.com/s24.jpg', 79999, 15, 1),
(3, 'Dell XPS 13', 'Premium ultrabook with Intel 13th Gen CPU', 'https://example.com/xps13.jpg', 114999, 10, 1),
(4, 'Men T-Shirt', 'Comfortable cotton t-shirt', 'https://example.com/menshirt.jpg', 499, 100, 2),
(5, 'LED Smart TV 55 Inch', '4K Ultra HD Smart TV', 'https://example.com/tv55.jpg', 42999, 8, 3);

-- Insert ADMIN USER (password: admin123)
INSERT INTO users (id, full_name, email, password, role) VALUES
(1, 'Admin User', 'admin@amazonclone.com', 
'$2a$10$ScbBDaH6MhrfMvj2Z9val./sP0hI48iKOKxACsyFTVzP5SkW9RhrS', 'ADMIN');

-- Create ADMIN CART
INSERT INTO cart (id, total_amount, user_id) VALUES
(1, 0, 1);
