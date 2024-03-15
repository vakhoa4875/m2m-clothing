use m2m_clothing
go

delete from Product
go

SET IDENTITY_INSERT Product ON;
INSERT INTO product (product_id, product_name, price, quantity, description, average_rate, rate_count, sold, pictures, videos, category_id)
values
(1,'Hanes mens Ecosmart Hoodie, Midweight Fleece Sweatshirt, Pullover Hooded Sweatshirt for Men',16.26,1083012,'"FLEECE TO FEEL GOOD ABOUT - Hanes EcoSmart men`s hoodie is made with cotton sourced from American farms. 
HOODED DESIGN - Classic hoodie styling featuring a dyed-to-match drawstring for the perfect fit. 
KANGAROO POCKET - Front kangaroo pocket keeps hands warm and holds small, everyday essentials. 
COMFY COZY - Midweight fleece keeps you warm without the bulk. 
HANES QUALITY - Sporty ribbed cuffs and hem hold its shape. 
MADE TO LAST - Double-needle stitching at the neck and armholes for added sturdiness."',4.5,180502,361004,'1-1.jpg,1-2.jpg,1-3.jpg','1.mp4',1),
(2,'Fruit of the Loom Eversoft Fleece Hoodies, Pullover & Full Zip, Moisture Wicking & Breathable, Sizes S-4X',35.76,353118,'"Male Model is 6’0” wearing a Size Medium. 
Female Model is 5’9” wearing size Small 
Eversoft fabric provides premium softness was after wash Double-needle stitching on the neck and hems for durability 
Ribbed cuffs and waistband that hold their shape Shoulder-to-shoulder neck tape for comfort and durability"',4.6,58853,117706,'2-1.jpg,2-2.jpg,2-3.jpg','2.mp4',1),
(3,'Gildan Fleece Hoodie Sweatshirt, Style G18500, Multipack',18.42,724074,'preshrunk 50% cotton/50% dryblend polyester moisture-wicking fabric air-jet yarn for softer feel and no pilling double-lined hood with matching drawstring double-needle stitching',4.7,120679,241358,'3-1.jpg,3-2.jpg,3-3.jpg','3.mp4',1),
(4,'Columbia Men`s Glennaker Rain Jacket',65,1278,'"WATERPROOF TECHNOLOGY: You’ll love our Columbia Men`s Glennaker Lake Rain Jacket, it features our Hydroplus waterproof nylon fabric for the ultimate in lightweight, wet weather, protection and comfort. 
COMPACT AND PACKABLE: A versatile waterproof jacket with an attached hood to ensure complete rain coverage, while its packable design lets you stow it away into its own chest pocket. Perfect for when the last drizzle subsides. 
HANDY FEATURES: Featuring a convenient stow-away hood, zippered hand pockets, adjustable sleeve cuffs, and a drawcord adjustable hem locks in the dry and keeps out the wet. 
VERSATILE FIT: This rain jacket features a timeless, versatile fit, that`s perfect functional rainy-day wear. 
BUILT TO LAST: Columbia’s attention to detail is what sets our apparel apart. Specifying only the highest quality materials, expert stitching and craftsmanship. This is a long-lasting jacket you will enjoy for seasons to come. Sleeve : Start at the center back of your neck and measure across the shoulder and down to the wrist. Round up to the next even number."',4.6,213,426,'4-1.jpg,4-2.jpg,4-3.jpg',NULL,1),
(5,'Champion Men`s Jacket, Stadium Packable Wind and Water Resistant Jacket (Reg. Or Big & Tall)',66.01,35244,'"THE FIT - This unisex jacket has a standard fit that`s perfect for layering. 
THE FEEL - Made from a 2.8 oz. lightweight fabric blend with a slick, wind and water-resistant coating that shields you from the elements. 
THE LOOK - Half-zip jacket with a bungee cord scuba-style hood, elastic cuffs, front pocket, and an open bottom hem with a bungee cord that protects against the elements. All over prints brings a stylish edge. 
PACKABLE - This rain jacket can be packed up into the front pocket and easily stowed away. 
SPOT THE C - This raincoat features the iconic C logo at the wrist. Note: C logo patch color may vary from image."',4.6,5874,11748,'5-1.jpg,5-2.jpg,5-3.jpg','5.mp4',1),
(6,'Columbia Men`s Flashback Windbreaker',50.29,6384,'"COMFORTABLE WINDBREAKER: The Columbia Men’s Flashback Windbreaker is a water resistant shell, designed to shield you and keep you warm on cool blustery days. 
WATER RESISTANT SHELL: Created from our matte face wind-breaking material, the 100% polyester plain weave shell, is water repellent and can handle most elements while keeping you warm. 
VERSATILE USE: This jacket is an excellent outer layer to help take the edge off cool mornings – even better for wind-chilled days – this windbreaker is an everyday staple for those in between seasons.
FULL HOOD: Designed to protect your head and trap the warmth, the hood on this coat is the perfect element when the wind chill outside becomes a nuisance. 
BUILT TO LAST: Columbia’s attention to detail is what sets our apparel apart. Specifying only the highest quality materials, expert stitching and craftsmanship. This is a long-lasting jacket you will enjoy for seasons to come."',4.4,1064,2128,'6-1.jpg,6-2.jpg,6-3.jpg',NULL,1),
(7,'Charles River Apparel Pack-n-go Windbreaker Pullover Hooded Jacket',37,11316,'"Packable Style- Our lightweight windbreaker folds into its own front pouch pocket with zipper closure for easy packing on the go. An essential for backpacking, exercise, or casual use 
Lightweight Pullover Windbreaker - Made with 100% Softex Polyester, our breathable fabric features underarm grommets for added ventilation and protects against rain and wind 
Running Rain Gear Features - Unisex Anorak-style half-zip has a durable design with a large front pocket, reinforced hood with adjustable shockcord drawstring, elasticized cuffs, and an adjustable shockcord hem 
Travel Essential – This jacket is compact for your luggage or backpack and is a perfect choice for traveling and vacations any time of the year. Machine washable material; available in a large variety of sizes and colors 
For 40 years and three generations, we have been guided by our roots. Inspired by the change of seasons, and the abundance of natural beauty around us, Charles River Apparel creates timeless styles that weather the elements as well as the trends"',4.6,1886,3772,'7-1.jpg,7-2.jpg,7-3.jpg','7.mp4',1),
(8,'Tommy Hilfiger Women`s Casual Band Jacket, Fall Fashion',109.5,2328,'"Classic Tommy Hilfiger style: If you’re into blazer jackets for women perfect for fall no need to look further; with brass-style buttons and band style fashion detailing, this jacket is a fashion statement must-have for any wardrobe 
Designer detailing: This jacket features a tailored fit, decorative buttons on both lapels, a stylish collar you can fold down or pop-up and an open front with two, small side pockets 
Fashion meets function: Made of soft French terry cloth, this coat is thick enough to ward off the chill, but thin enough to keep it dressy if paired with the right items 
Match with: Slip into your favorite pair of jeans and add a light camisole for women or tank top under the jacket; dress it up with a pair of slacks for business casual, add a tennis skirt, maxi skirt, midi skirt, or skirt of any length for date night 
Fall into Winter: Add a sweater under your jacket when it gets cold; on your feet, wear booties for women with pants, knee high boots with skinnies or a classic pair of high heeled pumps to complete the look for day or night"',4.4,388,776,'8-1.jpg,8-2.jpg,8-3.jpg',NULL,1),
(9,'Calvin Klein Women`s One Button Lux Blazer',71.74,1320,'"Universal match: Dress up or down no matter the season, great for spring, summer, fall and winter; easily transition from day to night by pairing with a blouse, shirt, jacket, cardigan, heels or sandals 
Year-round luxury: Wear a coat over these women’s blazer in the winter, cardigan in the spring and summer or wear them to relax around the home"',4.5,220,440,'9-1.jpg,9-2.jpg',NULL,1),
(10,'Calvin Klein Women`s Two Button Lux Blazer (Petite, Standard, & Plus)',99,8736,'"Lapel collar 
Flap pockets"',4.3,1456,2912,'10-1.jpg,10-2.jpg,10-3.jpg',NULL,1),
(11,'Men`s Duck Work Vest',46.03,240,'"Durable work duck vest with 100% duck cotton shell for lasting performance and 100% polyester lining for added comfort 
Patented tape measure patch and Cardura welt pocket for convenient storage Functional media pocket to keep your devices secure 
Pen pocket opening for easy access to your writing instruments 
Rugged metal zipper and zipper pull for a durable and stylish look"',4.7,40,80,'11-1.jpg,11-2.jpg,11-3.jpg',NULL,1),
(12,'Columbia Men`s Steens Mountain Vest',39.99,342,'"DEEP PILE FLEECE: The Columbia Collegiate Men`s Flanker Vest II is crafted of deep and cozy 250g MTR filament fleece for ultimate warmth and college flair 
ULTIMATE COMFORT: With a collared neck and full zip closure, your core will stay warm, while freeing up the arms for active use. The perfect layering piece for ultimate range of motion and comfort during cold winter days 
COLLEGE STYLE, CLASSIC FIT: Designed with a stylish cut, this vest shows off your college pride and is super comfortable for those chilly outdoor events 
HANDY FEATURES: This fleece vest provides two well positioned zippered side pockets to keep hands and small items warm and secure 
BUILT TO LAST: Columbia’s attention to detail is what sets our apparel apart. Specifying only the highest quality materials, expert stitching and craftsmanship. This is a long-lasting vest you will enjoy for seasons to come"',4.2,57,114,'12-1.jpg,12-2.jpg,12-3.jpg',NULL,1),
(13,'Calvin Klein Men`s Lightweight Packable Hooded Puffer Vest',49.99,3654,'"Quality performance: Vest for men with front center zipper that extends into the hood with an adjustable bungee system to maintain warmth; keep your valuables safe with 2 side zipper pockets 
Keeping warm: Interior insulation and water-resistant outer fabric provide added warmth and block out cold temperatures, so you’re ready for whatever the weather throws your way 
Structured design: Features elastic binding at the armholes and cord piping along the zipper to add structure to the vest while still allowing for full range of motion 
Travel-friendly: Easily pack and store these men’s outerwear vests; lightweight fabric and interior insulation allow the vest to fit where you need it 
Comfort and style: This men’s vest is sure to become a staple in your closet; bring it along in your work bag, wear it for a night out, on vacation or as part of your daily outfit"',4.6,609,1218,'13-1.jpg,13-2.jpg,13-3.jpg',NULL,1),
(14,'Columbia Women`s Flash Forward Windbreaker',89.97,162,'"Water resistant fabric 
Draw cord adjustable hood 
Zippered hand pockets 
Elastic cuffs 
Draw cord adjustable hem"',4.1,27,54,'14-1.jpg,14-2.jpg,14-3.jpg','14.mp4',1),
(15,'Legendary Whitetails Men`s Buck Camp Flannel, Long Sleeve Plaid Button Down Casual Shirt, Corduroy Cuffs',36.46,72,'"100% Cotton 
Imported 
[Relaxed Fit]: Refer to our size chart for the ideal fit; The Buck Camp Flannel is designed to highlight the chest and waist, offering a comfortable yet stylish shape; Its double-pleated back ensures ease of movement without any pulling or tugging; Adhere to care instructions for optimal shrinkage management 
[Quality of Material]: Legendary Whitetails flannel shirt for men, recognized for its top-tier quality, is crafted from 100% soft brushed cotton flannel and promises warmth and breathability; This 5.1 ounce Buck Camp Flannel Shirt is ideally weighted for layering or standalone wear, indoors and out; Beyond its softness, its pill-resistant premium fabric quality ensures a consistently sharp look and lasting comfort 
[Authentic Designs]: Our plaid shirt men`s designs stay true to their images, ensuring you get exactly what you see; immediate out of box comfort with no need for a ""break-in"" period; Its fade-resistant fabric ensures your men`s flannel long sleeve shirt looks as vibrant as day one, wash after wash 
[Traditional Style]: The classic single pocket design gives you a clean look while providing an option for storage; use the pencil slot to hold your pencil when scoring on the range or safeguarding your sunglasses 
[Corduroy Lined Cuffs & Collar]: Experience the classic touch of quintessential corduroy lining in our flannel shirt for men; not only does it enhance durability, but it also ensures the collar and cuffs maintain their shape"',3.9,12,24,'15-1.jpg,15-2.jpg',NULL,1),
(16,'"Amazon Essentials Men`s
Regular-Fit Long-Sleeve
Henley Shirt (Available in
Big & Tall)"',16.1,79122,'"REGULAR FIT: Comfortable, easy fit through the shoulders, chest, and waist
LIGHTWEIGHT T-SHIRT JERSEY: Soft and comfortable knit fabric for a go-to lightweight t-shirt.
HENLEY T-SHIRT: The perfect alternative to your favorite t shirt. Pair this super soft henley with jeans or chinos for comfortable everyday style.
DETAILS: Ribbed neck and sleeve hem trim, raglan sleeve with sturdy overlock stitching, and shirttail hem."',4.4,13187,26374,'16-1.jpg,16-2.jpg,16-3.jpg',NULL,2),(17,'"Hanes Men’s X-Temp Short
Sleeve Polo Shirt, Midweight
Men`s Shirt"',13.36,147618,'"SO SOFT - Midweight pique fabric feels super-soft up against your skin.
KEEPS YOU COMFORTABLE - X-Temp technology is designed to keep you cool and dry, no matter what the day brings.
ODOR CONTROL - FreshIQ advanced odor protection technology attacks the odor-causing bacteria in your clothing.
STAY-FLAT COLLAR - Classic polo style featuring a ribbed stay-flat collar.
FRONT BUTTON PLACKET – Men’s shirts are accented with a tailored 3-button placket.
TEARAWAY TAG - Simply remove the tearaway tag for itch-free comfort."',4.4,24603,49206,'17-1.jpg,17-2.jpg,17-3.jpg',NULL,2),
(18,'"Russell Athletic Men`s 
Dri-Power Long Sleeve Tees, 
Moisture Wicking, Odor 
Protection, UPF 30+"',8.59,84870,'"Lightweight cotton blend provides premium softness wash after wash
Dri-Power moisture wicking technology keeps you cool and dry
Odor protection helps to keep fabric fresh
UPF 30+ provides protection from harmful UV rays
Tag free for comfort"',4.4,14145,28290,'18-1.jpg,18-2.jpg,18-3.jpg',NULL,2),
(19,'"Amazon Essentials Men`s 
Regular-Fit Long-Sleeve 
T-Shirt"',14.2,48264,'"REGULAR FIT: Comfortable, easy fit through the shoulders, chest, and waist
LIGHTWEIGHT T-SHIRT JERSEY: Soft and comfortable knit fabric for a go-to lightweight t-shirt.
CLASSIC T-SHIRT: This wardrobe staple features classic t-shirt construction, a pull-on crew neckline, and short-sleeves.
DETAILS: Ribbed neck trim, tagless printed label for comfort, and soft jersey-knit taping on neck seam.
LENGTH: 29"" from side neck on US size M."',4.2,8044,16088,'19-1.jpg,19-2.jpg,19-3.jpg','19.mp4',2),
(20,'"Amazon Essentials Men`s 
Tech Stretch Tank T-Shirt"',13.2,37554,'"RELAXED FIT: Comfortable, relaxed fit through shoulders, chest, and waist
TECH STRETCH FABRIC: An ultra-light weight and breathable fabric, with a soft smooth sheen finish. Made with quick dry and moisture-wicking finishes, helping you stay comfortable and cool while you workout. This knit fabric has a 4 way gentle stretch for full flexibility during wear.
ACTIVE TANK TOP: Train in confidence with this staple workout tank t-shirt. Designed with a longer body length for added coverage during workouts. Whether you`re heading out for a jog, a quick game of basketball or running errands with the kids, our apparel is made to work out, live in, and lounge.
DETAILS: Featuring a pull-on crew neckline and a sleeveless racer back design for added mobility during workouts."',4.3,6259,12518,'20-1.jpg,20-2.jpg,20-3.jpg','20.mp4',2),
(21,'"Kanu Surf Men`s UPF 50+ 
Long Sleeve Rashguard 
Swim Shirt"',18.58,9102,'"Loose-fit rashguard in solid tone featuring long sleeves and crew neckline
UPF 50+ protection
Comfortable and versatile"',4.5,1517,3034,'21-1.jpg,21-2.jpg,21-3.jpg',NULL,2),
(22,'"Clique Men`s Parma 
Colorblock Polo"',17.98,264,'"Colorblocked polo with three button placket
Self collar and coverstitch detailing"',4.4,44,88,'22-1.jpg,22-2.jpg,22-3.jpg','22.mp4',2),
(23,'"Gildan Adult Fleece 
Crewneck Sweatshirt, Style 
G18000, Multipack, Ash Grey 
(1-Pack), X-Large"',13.48,5142,'"Classic fit for loose comfort
Brushed interior provides superior cozy softness
Rib cuffs and bottom band with spandex for enhanced stretch and recovery
Durable double-needle stitching at shoulders, armholes, neck, waistband, and cuffs
Tear away label for customizable comfort
Screen printing, embroidery, heat transfer, patches and DTG"',4.8,857,1714,'23-1.jpg,23-2.jpg,23-3.jpg',NULL,2),
(24,'"Hanes Men’s Tank Top 
2-Pack, X-Temp Performance 
Moisture-wicking Shirt, 2-Pack"',12.49,57756,'"Either tagless or with easily removed tearaway tag for comfort
X-Temp technology is designed to adapt to your temperature and activity to keep you cool and dry for all day comfort
Performance tank features 40+ UPF rating
Moisture wicking with dynamic vapor control
Black satin label easily removed for comfort
Either or with easily removed tearaway tag for comfort"',4.5,9626,19252,'24-1.jpg,24-2.jpg,24-3.jpg',NULL,2),
(25,'"Amazon Essentials Men`s 
Slim-Fit Tank Top"',10.4,38142,'"SLIM FIT: Slim fit fit through chest, and waist.
LIGHTWEIGHT JERSEY: Soft and comfortable fabric for your go-to lightweight tank. Solids are 100% cotton and heathers are a cotton-poly blend.
TANK TOP: This tank top is your perfect hot wather closet staple. Layer it with a collared shirt or pair it with shorts, jeans, or even chinos for those extra hot days.
DETAILS: Jesey binding at armholes and neck for a clean look. Tagless printed label at back neck for extra comfort. Straight hem."',4.4,6357,12714,'25-1.jpg,25-2.jpg,25-3.jpg','25.mp4',2),
(26,'"Wrangler Authentics Men`s 
Long Sleeve Heavyweight 
Fleece Shirt"',27.96,238578,'"BUILT FOR COMFORT: Made with comfort and function in mind, this two-sided brushed fleece will keep you warm all season long. Your new wardrobe favorite.
VERSATILE FIT: Perfect for layering in the winter or during cool fall evenings, this relaxed fit flannel doesn`t restrict movement so you can keep warm and keep moving on those colder days.
FUNCTIONAL STYLE: Made with 100% Polyester, this brushed fleece shirt is designed with style and function in mind. Wear it on the job or out to lunch with friends, this essential staple can be worn for many occasions.
DURABLE FINISHINGS: Finished with heavy-duty plastic buttons, this fleece is built to last. Button cuff closure and collar lined with Polyester designed to keep the warmth in, and the cold air out.
ADDED STORAGE: Constructed with (2) button front pockets, this fleece shirt has easy-access storage for all your basic necessities."',4.6,39763,79526,'26-1.jpg,26-2.jpg,26-3.jpg','26.mp4',2),
(27,'"Amazon Essentials Men`s 
V-Neck Sweater (Available 
in Big & Tall)"',25.6,150990,'"REGULAR FIT: Comfortable, easy fit through the shoulders, chest, and waist
COTTON YARN: Knit from a 100% cotton yarn with a soft hand and natural stretch.
EVERYDAY SWEATER: A go-to layering piece, this v-neck pullover sweater is lightweight enough to layer and soft enough to wear on its own.
DETAILS: Ribbed at the v-neckline, sleeve cuffs and bottom hem.
OFFICIAL LICENSED PRODUCT: Amazon Essentials offers one-of-a-kind products featuring your favorite characters to add wonder to your wardrobe! All products are officially licensed and designed in-house."',4.5,25165,50330,'27-1.jpg,27-2.jpg,27-3.jpg','27.mp4',2),
(28,'"Hanes Mens Originals 
Tri-Blend Tank Top, Lightweight 
Tanks for Men, Sleeveless 
Tank Shirt"',9.69,2634,'"BASICS THAT ARE FAR FROM BASIC - A stylish collection of cool, modern essentials designed for comfort, made for every body. Be yourself in Hanes Originals.
SOFT & COMFY FEEL - Our best cotton, polyester and rayon come together to make this soft and comfy men`s muscle tee.
RECYCLED MATERIALS - Made with recycled polyester for a soft, and lightweight feel.
HANES QUALITY - Banded collar, armholes, and double-needle stitching on the hem.
COLD WATER WASH - Hanes recommends machine washing in cold water to reduce energy consumption."',4.3,439,878,'28-1.jpg,28-2.jpg,28-3.jpg',NULL,2),
(29,'Legendary Whitetails Men`s Tough as Buck Double Layer Thermal Henley Shirt-Casual Long Sleeve Waffle Knit Regular Fit',44.99,84,'"VINTAGE STYLE: Since the 19th Century, hardworking men of all classes including loggers and hunters have been wearing thermal undershirts as cold weather essentials, our updated thermal henley shirt will fulfill your need for both function and fashion
WARM & COMFORTABLE: Made from 11 oz cotton/poly double layer thermal fabric; bonded construction traps air between the layers to retain warmth
VERSATILE: Our three button henley is easy to pull on and off for variable temperature control, thick enough to be worn alone or as a baselayer
RELAXED FIT: This henley includes the right amount of room in all the right places, layers perfectly under the Tough as Buck vests and jackets and the split hem allows it to be worn tucked in or untucked
DURABLE QUALITY: Triple needle stitching dual layer shirt"',4.5,14,28,'29-1.jpg,29-2.jpg,29-3.jpg',NULL,2),
(30,'Champion, Classic and Comfortable Tee, Long-Sleeve T-Shirt for Men (Reg. Or Big & Tall)',25,109332,'"THE FIT - Standard fit long-sleeve t-shirt for men.
THE FEEL - Comfortable, 5.5 oz. ring-spun cotton or cotton blend that feels great on the skin.
THE LOOK - Classic long-sleeve t-shirt with a ribbed crewneck collar and sturdy stitchwork.
SPOT THE C - This long-sleeve tee has an embroidered C patch on the chest and a C logo patch on the sleeve.
EASY CARE - Machine wash this long-sleeve men`s t-shirt on cold with like colors to reduce energy."',4.6,18222,36444,'30-1.jpg,30-2.jpg,30-3.jpg','30.mp4',2)



SET IDENTITY_INSERT product OFF;