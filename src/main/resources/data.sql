--CATEGORIES
INSERT INTO `category` (`id`, `name`) VALUES ('1', 'fresco');
INSERT INTO `category` (`id`, `name`) VALUES ('2', 'refrigerado');
INSERT INTO `category` (`id`, `name`) VALUES ('3', 'congelado');

--PRODUCTS
-- INSERT INTO `product` (`id`, `name`, `seller_id`) VALUES ('1', 'Banana');


--WAREHOUSES

--SECTORS


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
-- INSERT INTO `seller` (`id`) VALUES ('1');
-- INSERT INTO `seller` (`id`) VALUES ('2');