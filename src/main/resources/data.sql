--PERMISSION
INSERT INTO `permission`(`id`,`name`) VALUES (1, 'REGISTER_STOCK_PERMISSION');
INSERT INTO `permission`(`id`,`name`) VALUES (2, 'BUY_PRODUCT_PERMISSION');
INSERT INTO `permission`(`id`,`name`) VALUES (3, 'AUTHENTICATED_USER_PERMISSION');
INSERT INTO `permission`(`id`,`name`) VALUES (4, 'EDIT_PURCHASE_ORDER_PERMISSION');

--ROLE
INSERT INTO `role`(`id`,`name`) VALUES(1,'SELLER');
INSERT INTO `role`(`id`,`name`) VALUES(2,'BUYER');
INSERT INTO `role`(`id`,`name`) VALUES(3,'REPRESENTANT');

--ROLE-PERMISSION
INSERT INTO `role_permission` (`permission_id`,`role_id`)VALUES(3, 1); -- REPRESENTANT HAS AUTHENTICATED_USER_PERMISSION
INSERT INTO `role_permission` (`permission_id`,`role_id`)VALUES(3, 2); -- BUYER HAS AUTHENTICATED_USER_PERMISSION
INSERT INTO `role_permission` (`permission_id`,`role_id`)VALUES(3, 3); -- SELLER HAS AUTHENTICATED_USER_PERMISSION

INSERT INTO `role_permission` (`permission_id`,`role_id`)VALUES(2, 2); -- BUYER HAS BUY_PRODUCT_PERMISSION
INSERT INTO `role_permission` (`permission_id`,`role_id`)VALUES(4, 2); -- BUYER HAS EDIT_PURCHASE_ORDER_PERMISSION
INSERT INTO `role_permission` (`permission_id`,`role_id`)VALUES(1, 3); -- REPRESENTANT HAS REGISTER_STOCK_PERMISSION

--STATUS PURCHASE ORDER
INSERT INTO `status_purchase_order` (`id`, `name`) VALUES ('1', 'Novo');
INSERT INTO `status_purchase_order` (`id`, `name`) VALUES ('2', 'Em Processo');
INSERT INTO `status_purchase_order` (`id`, `name`) VALUES ('3', 'Finalizado');

--CATEGORIES
INSERT INTO `category` (`id`, `name`) VALUES ('1', 'fresco');
INSERT INTO `category` (`id`, `name`) VALUES ('2', 'refrigerado');
INSERT INTO `category` (`id`, `name`) VALUES ('3', 'congelado');

--COUNTRY
INSERT INTO `country_houses` (`id`, `country`, `name`) VALUES ('1', 'Argentina', 'Casa central de Argentina');
INSERT INTO `country_houses` (`id`, `country`, `name`) VALUES ('2', 'Chile', 'Casa central de Chile');
INSERT INTO `country_houses` (`id`, `country`, `name`) VALUES ('3', 'Uruguay', 'Casa central de Uruguay');
INSERT INTO `country_houses` (`id`, `country`, `name`) VALUES ('4', 'Colombia', 'Casa central de Colombia');

--ACCOUNTS
INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `id_country_house_fk`, `role_id`) VALUES ('1', 'contra123', '1', 'user_one', '1', 1);
INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `id_country_house_fk`, `role_id`) VALUES ('2', 'contra123', '1', 'user_two', '2', 1);
INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `id_country_house_fk`, `role_id`) VALUES ('3', 'contra123', '1', 'user_three', '3', 3);
INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `id_country_house_fk`, `role_id`) VALUES ('4', 'contra123', '1', 'user_four', '4', 3);
INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `id_country_house_fk`, `role_id`) VALUES ('5', 'contra123', '1', 'user_five', '4', 2);

--SELLER
INSERT INTO `seller` (`id`) VALUES ('1');
INSERT INTO `seller` (`id`) VALUES ('2');

--REPRESENTANT
INSERT INTO `representant` (`id`) VALUES ('3');
INSERT INTO `representant` (`id`) VALUES ('4');

--BUYER
INSERT INTO `buyer` (`id`) VALUES ('5');

--PRODUCTS
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`, `price`) VALUES ('1', 'Uva', '1', '1', 20);
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`, `price`) VALUES ('2', 'Goiaba', '1', '1', 30);
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`, `price`) VALUES ('3', 'Cenorua', '1', '1', 40);
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`, `price`) VALUES ('4', 'Alface', '1', '1', 50);
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`, `price`) VALUES ('5', 'Beterraba', '1', '1', 60);
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`, `price`) VALUES ('6', 'Picanha', '1', '2', 70);
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`, `price`) VALUES ('7', 'Costelinha de porco', '1', '2', 80);
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`, `price`) VALUES ('8', 'Asa de frango', '1', '2', 90);
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`, `price`) VALUES ('9', 'Bife Ancho', '1', '2', 100);
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`, `price`) VALUES ('10', 'Chorizo', '1', '2', 200);
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`, `price`) VALUES ('11', 'Pao de queijo', '1', '3', 210);
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`, `price`) VALUES ('12', 'Batata palito', '1', '3', 220);

--WAREHOUSES
INSERT INTO `warehouse` (`id`, `representant_id`) VALUES ('1', '3');
INSERT INTO `warehouse` (`id`, `representant_id`) VALUES ('2', '3');

--SECTORS
INSERT INTO `sector` (`id`, `category_id`, `warehouse_id`, `current_quantity`, `quantity_max`) VALUES ('1', '1', '1', 0, 10000);
INSERT INTO `sector` (`id`, `category_id`, `warehouse_id`, `current_quantity`, `quantity_max`) VALUES ('2', '2', '2', 0, 15000);
INSERT INTO `sector` (`id`, `category_id`, `warehouse_id`, `current_quantity`, `quantity_max`) VALUES ('3', '3', '2', 0, 17000);

-- ORDERS
INSERT INTO `orders` (`id`,`order_date`,`sector_id`) VALUES (1,'2021-08-01',1);

-- PRODUCTS STOCK
INSERT INTO `product_stock` (`id`,`current_quantity`,`current_temperature`,`due_date`,`initial_quantity`,`manufacturing_date`,`manufacturing_time`,`minimum_temperature`,`order_id`,`product_id`) VALUES (1,5,30,'2020-09-20',1,'2020-09-20','2020-09-20',25,1,1);
INSERT INTO `product_stock` (`id`,`current_quantity`,`current_temperature`,`due_date`,`initial_quantity`,`manufacturing_date`,`manufacturing_time`,`minimum_temperature`,`order_id`,`product_id`) VALUES (2,5,30,'2020-09-20',1,'2020-09-20','2020-09-20',25,1,1);
INSERT INTO `product_stock` (`id`,`current_quantity`,`current_temperature`,`due_date`,`initial_quantity`,`manufacturing_date`,`manufacturing_time`,`minimum_temperature`,`order_id`,`product_id`) VALUES (3,5,30,'2020-09-20',1,'2020-09-20','2020-09-20',25,1,2);
INSERT INTO `product_stock` (`id`,`current_quantity`,`current_temperature`,`due_date`,`initial_quantity`,`manufacturing_date`,`manufacturing_time`,`minimum_temperature`,`order_id`,`product_id`) VALUES (4,5,30,'2020-09-20',1,'2020-09-20','2020-09-20',25,1,3);





