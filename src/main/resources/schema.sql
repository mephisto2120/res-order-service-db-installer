-- SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
-- SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
-- SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
--
-- -- -----------------------------------------------------
-- -- Schema res_order_db
-- -- -----------------------------------------------------
--
-- -- -----------------------------------------------------
-- -- Schema res_order_db
-- -- -----------------------------------------------------
-- CREATE SCHEMA IF NOT EXISTS `res_order_db` DEFAULT CHARACTER SET utf8 ;
-- USE `res_order_db` ;
--
-- -- -----------------------------------------------------
-- -- Table `res_order_db`.`consumer`
-- -- -----------------------------------------------------
-- DROP TABLE IF EXISTS `res_order_db`.`consumer` ;
--
-- CREATE TABLE IF NOT EXISTS `res_order_db`.`consumer` (
--
--
--                             `id` int(3) NOT NULL,
--                             `name` varchar(25) DEFAULT NULL,
--                             `last_name` varchar(25) DEFAULT NULL,
--                             `email` varchar(25) DEFAULT NULL,
--                             `login` varchar(25) DEFAULT NULL,
--                             `pass` varchar(25) DEFAULT NULL,
--                             `phone_nr` int(25) DEFAULT NULL,
--                             `card` int(25) DEFAULT NULL
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE SCHEMA IF NOT EXISTS `res_order_db` DEFAULT CHARACTER SET utf8;
USE `res_order_db`;
CREATE TABLE IF NOT EXISTS consumer (`id` BIGINT(3) PRIMARY KEY AUTO_INCREMENT, `name` VARCHAR(255), `last_name` VARCHAR(255), `phone_no` VARCHAR(255), payment DECIMAL(5,2));
CREATE TABLE IF NOT EXISTS delivery(`id` BIGINT(40) PRIMARY KEY, `delivery_info_id` BIGINT(40), UNIQUE KEY (`delivery_info_id`));
CREATE TABLE IF NOT EXISTS payment(`id` BIGINT(40) PRIMARY KEY, `payment_info_id` BIGINT(40), UNIQUE KEY (`payment_info_id`));
CREATE TABLE IF NOT EXISTS orders (`id` BIGINT(40) PRIMARY KEY, `payment_information_id` BIGINT(40), `delivery_information_id` BIGINT(40), FOREIGN KEY (`delivery_information_id`) REFERENCES `delivery`(`delivery_info_id`), FOREIGN KEY (`payment_information_id`) REFERENCES `payment`(`payment_info_id`));

