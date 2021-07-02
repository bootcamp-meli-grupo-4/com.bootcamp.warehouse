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
INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `id_country_house_fk`) VALUES ('1', 'contra123', '1', 'user_one', '1');
INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `id_country_house_fk`) VALUES ('2', 'contra123', '1', 'user_two', '2');
INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `id_country_house_fk`) VALUES ('3', 'contra123', '1', 'user_three', '3');
INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `id_country_house_fk`) VALUES ('4', 'contra123', '1', 'user_four', '4');

--SELLER
INSERT INTO `seller` (`id`) VALUES ('1');
INSERT INTO `seller` (`id`) VALUES ('2');

--SELLER
INSERT INTO `representant` (`id`) VALUES ('3');
INSERT INTO `representant` (`id`) VALUES ('4');

--PRODUCTS
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`) VALUES ('1', 'Uva', '1', '1');
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`) VALUES ('2', 'Goiaba', '1', '1');
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`) VALUES ('3', 'Cenorua', '1', '1');
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`) VALUES ('4', 'Alface', '1', '1');
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`) VALUES ('5', 'Beterraba', '1', '1');
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`) VALUES ('6', 'Picanha', '1', '2');
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`) VALUES ('7', 'Costelinha de porco', '1', '2');
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`) VALUES ('8', 'Asa de frango', '1', '2');
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`) VALUES ('9', 'Bife Ancho', '1', '2');
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`) VALUES ('10', 'Chorizo', '1', '2');
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`) VALUES ('11', 'Pao de queijo', '1', '3');
INSERT INTO `product` (`id`, `name`, `seller_id`, `category_id`) VALUES ('12', 'Batata palito', '1', '3');

--WAREHOUSES
INSERT INTO `warehouse` (`id`, `representant_id`) VALUES ('1', '3');
INSERT INTO `warehouse` (`id`, `representant_id`) VALUES ('2', '3');

--SECTORS
INSERT INTO `sector` (`id`, `category_id`, `warehouse_id`,`quantity_max`,`current_quantity`) VALUES ('1', '1', '1','10','0');
INSERT INTO `sector` (`id`, `category_id`, `warehouse_id`,`quantity_max`,`current_quantity`) VALUES ('2', '2', '2','10','4');
INSERT INTO `sector` (`id`, `category_id`, `warehouse_id`,`quantity_max`,`current_quantity`) VALUES ('3', '3', '2','5','1');