CREATE TABLE `wish` (
  `id` IDENTITY NOT NULL,
  `priority` int(11) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `link` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`));