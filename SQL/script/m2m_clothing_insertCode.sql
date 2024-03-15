use m2m_clothing
go

delete from Account
go

INSERT INTO Account (username, email, hashed_password, is_admin)
VALUES ('john.doe', 'john.doe@example.com', 'your_hashed_password1', 0),
       ('jane.smith', 'jane.smith@example.com', 'your_hashed_password2', 0),
       ('admin', 'admin@m2m_clothing.com', 'your_hashed_password3', 1);


go
delete from Category
go

INSERT INTO Category (category_name, logo, description)
VALUES ('Outerwear', 'outerwear_logo.avif', 'Jackets, coats and vests'),
       ('Tops', 'tops_logo.jpg', 'T-shirts, blouses, sweatshirts'),
       ('Bottoms', 'bottoms_logo.png', 'Trousers, jeans, shorts, skirts'),
       ('Jewels & Accessories', 'Jewels_logo.webp', 'Jewelry, handbags, belts'),
       ('Headwear', 'headwear_logo.webp', 'Hats, caps, beanies'),
       ('Footwear', 'footwear_logo.jpg', 'Shoes, boots, sandals');

go 












