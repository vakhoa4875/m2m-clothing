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
delete from Product
go

INSERT INTO product (product_id, product_name, price, quantity, description, sold, pictures, videos, category_id)
VALUES
(1,'Hanes mens Ecosmart Hoodie, Midweight Fleece Sweatshirt, Pullover Hooded Sweatshirt for Men',16.26,1083012,'FLEECE TO FEEL GOOD ABOUT - Hanes EcoSmart men's hoodie is made with cotton sourced from American farms. HOODED DESIGN - Classic hoodie styling featuring a dyed-to-match drawstring for the perfect fit. KANGAROO POCKET - Front kangaroo pocket keeps hands warm and holds small, everyday essentials. COMFY COZY - Midweight fleece keeps you warm without the bulk. HANES QUALITY - Sporty ribbed cuffs and hem hold its shape. MADE TO LAST - Double-needle stitching at the neck and armholes for added sturdiness.',4.5,180502,361004,'1-1.jpg,1-2.jpg,1-3.jpg',1.mp4,1),(2,'Fruit of the Loom Eversoft Fleece Hoodies, Pullover & Full Zip, Moisture Wicking & Breathable, Sizes S-4X',35.76,353118,'Male Model is 6’0” wearing a Size Medium. Female Model is 5’9” wearing size Small Eversoft fabric provides premium softness was after wash Double-needle stitching on the neck and hems for durability Ribbed cuffs and waistband that hold their shape Shoulder-to-shoulder neck tape for comfort and durability',4.6,58853,117706,'2-1.jpg,2-2.jpg,2-3.jpg',2.mp4,1),






























