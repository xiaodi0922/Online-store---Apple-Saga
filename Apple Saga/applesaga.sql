--

-- Table structure for table CUSTOMER

--

CREATE TABLE CUSTOMER
(
  customerId INT(5) UNIQUE AUTO_INCREMENT,
  customerName VARCHAR(50)UNIQUE,
  customerContact VARCHAR(20),
  customerAddress VARCHAR(70),
  customerEmail VARCHAR(50)UNIQUE,
  customerPassword VARCHAR(20),
  PRIMARY KEY(customerId)
);
INSERT INTO CUSTOMER(customerName, customerContact, customerAddress, customerEmail, customerPassword)  VALUES ( 'Ali bin Abu', '60123456789', '123 Jalan Ampang, Kuala Lumpur, Malaysia', 'ali@example.com', 'aliabu6789');
INSERT INTO CUSTOMER(customerName, customerContact, customerAddress, customerEmail, customerPassword) VALUES ( 'Siti binti Ahmad', '60123456790', '456 Jalan Pudu, Petaling Jaya, Malaysia', 'siti@example.com', 'sitiAhmad95');
INSERT INTO CUSTOMER(customerName, customerContact, customerAddress, customerEmail, customerPassword)  VALUES ( 'Ahmad bin Ismail',  '60123456791', '789 Jalan Bukit Bintang, Penang, Malaysia', 'ahmad@example.com', 'ahmadIsmail22');
INSERT INTO CUSTOMER(customerName, customerContact, customerAddress, customerEmail, customerPassword)  VALUES ( 'Linda binti Rahman', '60123456792', '321 Jalan Raja Chulan, Johor Bahru, Malaysia', 'linda@example.com', 'Linda123');
INSERT INTO CUSTOMER(customerName, customerContact, customerAddress, customerEmail, customerPassword)  VALUES ( 'Mohd bin Abdullah', '60123456793', '567 Jalan Sultan Ismail, Kuantan, Malaysia', 'mohd@example.com', 'mohdAbdullah0915');
INSERT INTO CUSTOMER(customerName, customerContact, customerAddress, customerEmail, customerPassword) VALUES ( 'Aminah binti Hassan', '60123456795', '234 Jalan Kuching, Melaka, Malaysia', 'aminah@example.com', 'aminah6795');
INSERT INTO CUSTOMER(customerName, customerContact, customerAddress, customerEmail, customerPassword) VALUES ( 'Lee Siew Mei', '60123456800', '123 Jalan Puchong, Petaling Jaya, Malaysia', 'lee@example.com', 'siewmeilee00');
INSERT INTO CUSTOMER(customerName, customerContact, customerAddress, customerEmail, customerPassword) VALUES ( 'Chong Wei Liang', '60123456801', '456 Jalan Perak, Penang, Malaysia', 'chong@example.com', 'wlchong94');
INSERT INTO CUSTOMER(customerName, customerContact, customerAddress, customerEmail, customerPassword) VALUES ( 'Kumar a/l Raju', '60123456802', '789 Jalan Raja Chulan, Kuala Lumpur, Malaysia', 'kumar@example.com', 'kumar30');
INSERT INTO CUSTOMER(customerName, customerContact, customerAddress, customerEmail, customerPassword) VALUES ( 'Nguyen Thi Linh', '60123456803', '890 Jalan Bukit Bintang, Kuala Lumpur, Malaysia', 'nguyen@example.com', 'nguyenrei29');


CREATE TABLE PRODUCT_CATEGORY
(
   categoryId INT(5) UNIQUE AUTO_INCREMENT,
   categoryName VARCHAR (30) UNIQUE,
   PRIMARY KEY (categoryId)
);
INSERT INTO PRODUCT_CATEGORY VALUES (1, 'Apple Watch ');
INSERT INTO PRODUCT_CATEGORY VALUES (2, 'Airpod');
INSERT INTO PRODUCT_CATEGORY VALUES (3, 'iPad');
INSERT INTO PRODUCT_CATEGORY VALUES (4, 'iPhone' );


CREATE TABLE PRODUCT
(
   productId INT(5) UNIQUE AUTO_INCREMENT,
   productName VARCHAR(70),
   productPrice DOUBLE(7,2),
   productColour VARCHAR(15),
   stockQuantity INT(5),
   categoryId INT(5),
   imageURL VARCHAR(150),
   PRIMARY KEY (productId),
   FOREIGN KEY (categoryId) REFERENCES PRODUCT_CATEGORY (categoryId)
);
INSERT INTO PRODUCT VALUES (1, 'iPhone 14', 4299.00, 'White', 20, 4, 'Iphone_14.jpg'); 
INSERT INTO PRODUCT VALUES (2, 'iPhone 13', 3199.00, 'Pink', 15,4, 'Iphone_13.jpeg');
INSERT INTO PRODUCT VALUES (3, 'iPhone 15', 5699.00, 'Black', 35, 4, 'Iphone_15.jpeg');
INSERT INTO PRODUCT VALUES (4, 'iPhone 15 Pro Max', 6499.00, 'Blue Titanium', 15, 4, 'Iphone_15_Pro_Max.jpeg');
INSERT INTO PRODUCT VALUES (5, 'iPad Pro 12.9-Inch', 5299.00, 'Gray', 15,3, 'iPad Pro 12.9-Inch.jpeg');
INSERT INTO PRODUCT VALUES (6, 'AirPods (3rd Gen)', 749.00, 'white', 30, 2, 'Airpods_3rd_Gen.jpeg');
INSERT INTO PRODUCT VALUES (7, 'AirPods Pro', 999.00, 'white', 10, 2, 'Airpods_Pro.jpeg');
INSERT INTO PRODUCT VALUES (8, 'Apple Watch Series 9', 1899.00, 'Sliver', 25,1, 'Apple_Watch_Series_9.jpeg');
INSERT INTO PRODUCT VALUES (9, 'Apple Watch SE', 1199.00, 'Sliver', 29,1, 'Apple_Watch_SE.jpeg');
INSERT INTO PRODUCT VALUES (10,'iPad Air 11-Inch', 2999.00, 'Purple',31, 3, 'Ipad_Air_11-Inch.jpeg');




CREATE TABLE SHOPPING_ORDER
(
   orderId INT(5) UNIQUE AUTO_INCREMENT,
   customerId INT(5),
   PRIMARY KEY (orderId),
   FOREIGN KEY (customerId) REFERENCES CUSTOMER (customerId)
);
INSERT INTO SHOPPING_ORDER VALUES (1, 1);
INSERT INTO SHOPPING_ORDER VALUES (2, 3);
INSERT INTO SHOPPING_ORDER VALUES (3, 2);
INSERT INTO SHOPPING_ORDER VALUES (4, 2);
INSERT INTO SHOPPING_ORDER VALUES (5,3);
INSERT INTO SHOPPING_ORDER VALUES (6, 7);
INSERT INTO SHOPPING_ORDER VALUES (7, 4);
INSERT INTO SHOPPING_ORDER VALUES (8, 5);
INSERT INTO SHOPPING_ORDER VALUES (9, 10);
INSERT INTO SHOPPING_ORDER VALUES (10, 6);


CREATE TABLE DELIVERY 
(
   deliveryId INT(5) UNIQUE AUTO_INCREMENT,
   orderId INT(5) , 
   deliveryStatus VARCHAR(15) DEFAULT 'IN-DELIVERY',
   PRIMARY KEY (deliveryId),
   FOREIGN KEY (orderId) REFERENCES SHOPPING_ORDER (orderId)
);
INSERT INTO DELIVERY VALUES ('1', '1','IN-DELIVERY');
INSERT INTO DELIVERY VALUES ('2','2', 'IN-DELIVERY');
INSERT INTO DELIVERY VALUES ('3', '3', 'IN-DELIVERY');
INSERT INTO DELIVERY VALUES ('4','4', 'IN-DELIVERY');
INSERT INTO DELIVERY VALUES ('5', '5', 'IN-DELIVERY');
INSERT INTO DELIVERY VALUES ('6','6', 'IN-DELIVERY');
INSERT INTO DELIVERY VALUES ('7','7', 'IN-DELIVERY');
INSERT INTO DELIVERY VALUES ('8','8', 'IN-DELIVERY');
INSERT INTO DELIVERY VALUES ('9','9', 'IN-DELIVERY');
INSERT INTO DELIVERY VALUES ('10','10', 'IN-DELIVERY');



CREATE TABLE PAYMENT
(
   paymentId INT(5) UNIQUE AUTO_INCREMENT,
   paymentMethod VARCHAR(20),
   orderId INT(5) ,
   PRIMARY KEY (paymentId),
   FOREIGN KEY (orderId) REFERENCES SHOPPING_ORDER (orderId)
);
INSERT INTO PAYMENT VALUES (1, 'Credit', 1);
INSERT INTO PAYMENT VALUES (2, 'Credit',  2);
INSERT INTO PAYMENT VALUES (3, 'Online Banking', 3);
INSERT INTO PAYMENT VALUES (4, 'Credit', 4);
INSERT INTO PAYMENT VALUES (5, 'e-Wallet', 5);
INSERT INTO PAYMENT VALUES (6, 'e-Wallet', 6);
INSERT INTO PAYMENT VALUES (7, 'Online Banking', 7);
INSERT INTO PAYMENT VALUES (8, 'Debit', 8);
INSERT INTO PAYMENT VALUES (9, 'Online Banking', 9);
INSERT INTO PAYMENT VALUES (10, 'e-Wallet', 10);


CREATE TABLE REVIEW
(
   caseId INT(5) UNIQUE AUTO_INCREMENT,
   customerId INT(5),
   productId INT(5),
   reviewDescription VARCHAR(200),
   PRIMARY KEY (caseId),
   FOREIGN KEY (customerId) REFERENCES CUSTOMER (customerId),
   FOREIGN KEY (productId) REFERENCES PRODUCT (productId)
);
INSERT INTO REVIEW VALUES ('1', '1',  '1', 'The product arrived in perfect condition and works great.');
INSERT INTO REVIEW VALUES ('2', '3', '3', 'Greatest product');
INSERT INTO REVIEW VALUES ('3', '2', '4', 'The product quality is excellent, highly recommended.');
INSERT INTO REVIEW VALUES ('4', '2', '10', 'Item very gud !!!');
INSERT INTO REVIEW VALUES ('6', '7',  '3', 'Greatest electronic devices.');
INSERT INTO REVIEW VALUES ('7', '4',  '6','Delivery takes longer than expected.');
INSERT INTO REVIEW VALUES ('8', '5',  '8', 'The product got abit scratch');
INSERT INTO REVIEW VALUES ('9', '10',  '8', 'I am satisfied with my purchase, and the delivery was fast.');
INSERT INTO REVIEW VALUES ('10', '6',  '9','Love the product so much !!!');


CREATE TABLE ORDER_LIST
(
   productId INT(5) REFERENCES PRODUCT (productId),
   orderId INT(5) REFERENCES SHOPPING_ORDER (orderId),
   productQuantity INT(20),
   PRIMARY KEY (productId, orderId)
);

INSERT INTO ORDER_LIST (productId, orderId, productQuantity) VALUES (1, 1, 2);
INSERT INTO ORDER_LIST (productId, orderId, productQuantity) VALUES (2, 1, 1);
INSERT INTO ORDER_LIST (productId, orderId, productQuantity) VALUES (3, 2, 1);
INSERT INTO ORDER_LIST (productId, orderId, productQuantity) VALUES (4, 3, 3);
INSERT INTO ORDER_LIST (productId, orderId, productQuantity) VALUES (5, 3, 1);
INSERT INTO ORDER_LIST (productId, orderId, productQuantity) VALUES (6, 4, 2);
INSERT INTO ORDER_LIST (productId, orderId, productQuantity) VALUES (7, 4, 1);
INSERT INTO ORDER_LIST (productId, orderId, productQuantity) VALUES (8, 5, 1);
INSERT INTO ORDER_LIST (productId, orderId, productQuantity) VALUES (9, 6, 1);
INSERT INTO ORDER_LIST (productId, orderId, productQuantity) VALUES (10, 7, 2);
INSERT INTO ORDER_LIST (productId, orderId, productQuantity) VALUES (11, 8, 1);
INSERT INTO ORDER_LIST (productId, orderId, productQuantity) VALUES (12, 9, 1);
INSERT INTO ORDER_LIST (productId, orderId, productQuantity) VALUES (13, 10, 1);
INSERT INTO ORDER_LIST (productId, orderId, productQuantity) VALUES (1, 11, 1);
INSERT INTO ORDER_LIST (productId, orderId, productQuantity) VALUES (2, 11, 1);

CREATE TABLE cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customerId INT NOT NULL
);

CREATE TABLE cart_item (
    cartId INT NOT NULL,
    productId INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (cartId) REFERENCES cart(id)
);


