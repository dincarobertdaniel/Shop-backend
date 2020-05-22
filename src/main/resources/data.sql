CREATE DATABASE IF NOT EXISTS `e-commerce_shop`;
USE `e-commerce_shop`;

CREATE TABLE `brand`
(
    `brand_id`          int(11) NOT NULL,
    `brand_description` varchar(1000) DEFAULT NULL,
    `brand_name`        varchar(255)  DEFAULT NULL
) ;


INSERT INTO `brand` (`brand_id`, `brand_description`, `brand_name`)
VALUES (1,
        'Samsung follows a simple business philosophy:\r\nto devote its talent and technology to creating superior products and services that contribute to a better global society.\r\nTo achieve this, Samsung sets a high value on its people and technologies.',
        'Samsung'),
       (2,
        'Apple Inc. is an American multinational technology company headquartered in Cupertino,\r\nCalifornia, that designs, develops, and sells consumer electronics, computer software, and online services. It is considered one of the Big Four technology companies,\r\nalongside Amazon, Google, and Microsoft.',
        'Apple'),
       (3,
        'Sony is a Japanese multinational conglomerate corporation headquartered in Kōnan, Minato, Tokyo.\r\nIts diversified business includes consumer and professional electronics, gaming, entertainment and financial services.\r\nThe company owns the largest music entertainment business in the world, the largest video game console business and one of the largest video game publishing businesses,\r\nand is one of the leading manufacturers of electronic products for the consumer and professional markets, and a leading player in the film and television entertainment industry.',
        'Sony'),
       (4,
        'LG makes electronics, chemicals, and telecom products and operates subsidiaries such as LG Electronics, Zenith, LG Display, LG Uplus, LG Innotek and LG Chem in over 80 countries.',
        'LG'),
       (5,
        'Xiaomi is a Chinese electronics company founded in April 2010 and headquartered in Beijing.\r\nXiaomi makes and invests in smartphones, mobile apps, laptops, bags, earphones, shoes, fitness bands, and many other products. Xiaomi is also the fourth company after Apple,\r\nSamsung and Huawei to have self-developed mobile phone chip capabilities.',
        'Xiaomi');


CREATE TABLE `customer`
(
    `customer_id`  int(11)      NOT NULL,
    `user_name`    varchar(10)  NOT NULL,
    `password`     varchar(50)  NOT NULL,
    `first_name`   varchar(50) DEFAULT NULL,
    `last_name`    varchar(50) DEFAULT NULL,
    `phone_number` varchar(20) DEFAULT NULL,
    `address`      varchar(255) NOT NULL,
    `city`         varchar(255) NOT NULL,
    `country`      varchar(255) NOT NULL,
    `email`        varchar(255) NOT NULL,
    `zip_code`     varchar(10) DEFAULT NULL
);



INSERT INTO `customer` (`customer_id`, `user_name`, `password`, `first_name`, `last_name`, `phone_number`, `address`,
                        `city`, `country`, `email`, `zip_code`)
VALUES (1, 'JacOB', 'root1234', 'Jacob', 'Smith', '415-961-9106', '2097  Black Oak Hollow Road', 'San Francisco', 'US',
        'jacsm@gmail.com', '94104'),
       (2, 'JoMCruz', 'root2345', 'Joe', 'Cruz', '302-281-4273', '3066  Argonne Street', 'Philadelphia', 'US',
        'joecruz@gmail.com', '19108'),
       (3, 'VesRa', 'root3456', 'Vestru', 'Rachel', '+356 2169 1314',
        'Paola Health Centre, Pjazza Antoine De Paule, Malta PLA 1266', 'Paola', 'Malta', 'vesra@gmail.com',
        'PLA 1266'),
       (4, 'hibmo', 'root4567', 'Hiba', 'Mowad', '00966 4 3222864', 'P.o box 17846, Jeddah 21494', 'Jeddah',
        'Saudi Arabia', 'himbo@gmail.com', 'N/A'),
       (5, 'Martina23', 'root5678', 'Martina', 'Botello', '769 710 527', 'Avda. Los llanos 13', 'Cidamón', 'Spain',
        'martina23@gmail.com', '26291'),
       (6, 'VikaVee', 'root6789', 'Vika', 'Vingyte', '8 (611) 16909',
        'Aido g. 8, LT-78322, Šiaulių m. sav. (PC Akropolis)', 'Šiauliai', 'Lithuania', 'vikavee@gmail.com', 'N/A'),
       (7, 'wadhm', 'root7890', 'Wadha', 'Mohammadi', '+996 (312) 897373', 'ул. Чехова, 28', 'Бишкек', 'Kyrgystan',
        'wadhm@gmail.com', 'N/A'),
       (8, 'rondun', 'root8910', 'Ronnie', 'Dutton', '0664 197 74667', 'Sankt Georgener Hauptstrasse 35', 'UNTERGRÜNAU',
        'Austria', 'rondun@gmail.com', '6652'),
       (9, 'doyeJ', 'root9012', 'Do-yeon', 'Jang', '+82-7-908-4915',
        '182-11, Sicheongbyeolgwan, Seosomun-dong, Jung-gu', 'Seoul', 'Korea', 'doyeJ@gmail.com', '100-739'),
       (10, 'lucciannn', 'root1034', 'Alvisio', 'Lucciano', '0394 2906006', 'Lungodora Napoli 101', 'Casotto', 'Italy',
        'lucciannn@gmail.com', '36040');



CREATE TABLE `invoice`
(
    `invoice_id`   int(11) NOT NULL,
    `amount`       double  DEFAULT NULL,
    `invoice_date` date    DEFAULT NULL,
    `customer_id`  int(11) DEFAULT NULL
);


INSERT INTO `invoice` (`invoice_id`, `amount`, `invoice_date`, `customer_id`)
VALUES (1, 2199.99, '2020-03-28', 5);



CREATE TABLE `orders`
(
    `order_id`     int(11) NOT NULL,
    `quantity`     int(11) NOT NULL,
    `amount`       double  NOT NULL,
    `customer_id`  int(11)     DEFAULT NULL,
    `date_placed`  date        DEFAULT NULL,
    `order_status` varchar(25) DEFAULT NULL,
    `invoice_id`   int(11)     DEFAULT NULL
) ;


INSERT INTO `orders` (`order_id`, `quantity`, `amount`, `customer_id`, `date_placed`, `order_status`, `invoice_id`)
VALUES (1, 3, 13099.97, 1, '2020-02-02', 'AWAITING_PAYMENT', NULL),
       (2, 1, 590.99, 3, '2020-03-01', 'PROCESSING_IN_PROGRESS', NULL),
       (3, 1, 2199.99, 5, '2020-03-27', 'PAYMENT_ACCEPTED', 1);



CREATE TABLE `orders_products`
(
    `orders_id`  int(11) DEFAULT NULL,
    `product_id` int(11) DEFAULT NULL
) ;



INSERT INTO `orders_products` (`orders_id`, `product_id`)
VALUES (1, 2),
       (1, 2),
       (1, 1),
       (2, 4),
       (3, 5);



CREATE TABLE `payment`
(
    `payment_id`     int(11) NOT NULL,
    `payment_date`   date        DEFAULT NULL,
    `amount`         double      DEFAULT NULL,
    `allowed`        tinyint(1)  DEFAULT NULL,
    `payment_method` varchar(50) DEFAULT NULL,
    `customer_id`    int(11)     DEFAULT NULL,
    `invoice_id`     int(11)     DEFAULT NULL,
    `order_id`       int(11)     DEFAULT NULL
);



INSERT INTO `payment` (`payment_id`, `payment_date`, `amount`, `allowed`, `payment_method`, `customer_id`, `invoice_id`,
                       `order_id`)
VALUES (1, '2020-03-28', 2199.99, 1, 'CREDIT_CARD', 5, 1, 3);



CREATE TABLE `product`
(
    `product_id`          int(11)     NOT NULL,
    `product_name`        varchar(255) DEFAULT NULL,
    `product_category`    varchar(255) DEFAULT NULL,
    `product_description` varchar(255) DEFAULT NULL,
    `units_in_stock`      int(11)      DEFAULT NULL,
    `price`               float       NOT NULL,
    `product_brand`       varchar(50) NOT NULL
);



INSERT INTO `product` (`product_id`, `product_name`, `product_category`, `product_description`, `units_in_stock`,
                       `price`, `product_brand`)
VALUES (1, 'Samsung Galaxy S20 Plus', 'ELECTRONICS',
        '4G Edition, Octa Core, 128GB, 8GB RAM, Dual SIM, 5-Camere, Cosmic Black', 140, 4299.99, '1'),
       (2, 'Iphone 11', 'ELECTRONICS', '256GB, White', 300, 4399.99, '2'),
       (3, 'PlayStation 4 Pro', 'ELECTRONICS', '1TB Black + Fortnite Neo Versa Bundle', 20, 1899.99, '3'),
       (4, 'SmartWatch Amazfit Stratos', 'ELECTRONICS',
        'All-Day Heart Rate and Activity Tracking, GPS, 5 ATM Water Resistance', 70, 590.99, '5'),
       (5, 'LG 4K Ultra HD Smart LED TV', 'ELECTRONICS', '55UN7300PUF Alexa Built-In 55 Inch', 144, 2199.99, '4');



CREATE TABLE `shipment`
(
    `id`               int(11) NOT NULL,
    `shipping_date`    date         DEFAULT NULL,
    `status`           varchar(25)  DEFAULT NULL,
    `tracking_number`  varchar(50)  DEFAULT NULL,
    `order_id`         int(11)      DEFAULT NULL,
    `customer_id`      int(11)      DEFAULT NULL,
    `shipping_address` varchar(200) DEFAULT NULL,
    `billing_address`  varchar(200) DEFAULT NULL,
    `delivery_cost`    double       DEFAULT NULL
);



INSERT INTO `shipment` (`id`, `shipping_date`, `status`, `tracking_number`, `order_id`, `customer_id`,
                        `shipping_address`, `billing_address`, `delivery_cost`)
VALUES (1, '2020-04-02', 'PACKED', 'SHIP0001RO', 3, 5, 'Avda. Los llanos 13', 'Avda. Los llanos 13', 20);


ALTER TABLE `brand`
    ADD PRIMARY KEY (`brand_id`);


ALTER TABLE `customer`
    ADD PRIMARY KEY (`customer_id`);


ALTER TABLE `invoice`
    ADD PRIMARY KEY (`invoice_id`);


ALTER TABLE `orders`
    ADD PRIMARY KEY (`order_id`);



ALTER TABLE `orders_products`
    ADD KEY `product_id` (`product_id`),
    ADD KEY `orders_id` (`orders_id`);


ALTER TABLE `payment`
    ADD PRIMARY KEY (`payment_id`);


ALTER TABLE `product`
    ADD PRIMARY KEY (`product_id`);


ALTER TABLE `shipment`
    ADD PRIMARY KEY (`id`);


ALTER TABLE `brand`
    MODIFY `brand_id` int(11) NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 6;


ALTER TABLE `customer`
    MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 11;


ALTER TABLE `invoice`
    MODIFY `invoice_id` int(11) NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 2;


ALTER TABLE `orders`
    MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 4;


ALTER TABLE `payment`
    MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 2;


ALTER TABLE `product`
    MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 6;


ALTER TABLE `shipment`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 2;


ALTER TABLE `orders_products`
    ADD CONSTRAINT `orders_id` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`order_id`),
    ADD CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);
COMMIT;

