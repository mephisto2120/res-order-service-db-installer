USE `res_order_db`;


INSERT INTO `consumer` (`name`, `last_name`)
SELECT 'Stefan', 'Batory';
-- line below does not work on mysql 10.3.35 but works on 10.5
--WHERE NOT EXISTS (SELECT 1 FROM `consumer` WHERE `name` = 'Stefan' AND `last_name` = 'Batory');
