-- MySQL dump 10.13  Distrib 8.0.23, for Linux (x86_64)
--
-- Host: localhost    Database: bicycle_rent
-- ------------------------------------------------------
-- Server version	8.0.23-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!4010bicycle_detail SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bicycle_detail`
--

DROP TABLE IF EXISTS `bicycle_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bicycle_detail` (
  `bicycle_id` bigint NOT NULL,
  `detail_id` bigint NOT NULL,
  KEY `FK6h0upmlwsddi0hbqaib7ankpq` (`detail_id`),
  KEY `FKl1e88yn5mqu0uyi3blqhtc3oq` (`bicycle_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bicycle_detail`
--

LOCK TABLES `bicycle_detail` WRITE;
/*!40000 ALTER TABLE `bicycle_detail` DISABLE KEYS */;
INSERT INTO `bicycle_detail` VALUES (1,3),(1,4),(5,3),(5,4),(4,1),(4,2),(4,3),(4,4),(2,1),(2,2),(2,4),(3,1),(3,2),(3,4);
/*!40000 ALTER TABLE `bicycle_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bicycles`
--

DROP TABLE IF EXISTS `bicycles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bicycles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `weight` double NOT NULL DEFAULT '0',
  `num_of_speeds` int NOT NULL,
  `price` double NOT NULL,
  `photo` longtext NOT NULL,
  `description` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bicycles`
--

LOCK TABLES `bicycles` WRITE;
/*!40000 ALTER TABLE `bicycles` DISABLE KEYS */;
INSERT INTO `bicycles` VALUES (1,'UP! 12\" Clifford','For kids',15,6,180,'up-clifford-yellow.jpg','UP! 12\" Clifford Kid\'s bike in Yellow is perfect for getting Kids out on trail adventures. '),(2,'TITAN Expert 29″ NEW 2018','Mountain',11.5,21,290,'titan-expert-2018.jpg','This Expert 29 ″ model is developed taking into account the latest trends in the cycling industry. Copes with rough terrain.'),(3,'Gravity Flint, VG07402','Mountain',14,24,270,'gravity-flint-VG07402.jpg','Flint opens the Gravity line of bicycles with 27.5 rims (the modern standard for mountain bikes. In 27.5, a balance was found between control precision, acceleration dynamics and coasting).'),(4,'Ardis Lido 26\"','City',9.75,1,220,'ardis-lido-26.jpg','This is a bright and practical bike for the city. All kinds of adjustment of the handlebar and seat according to the position will allow anyone to find a comfortable position while riding this wonderful bike.'),(5,'Gravity Doggie, VG01802','For kids',5,1,150,'gravity-doggie-VG01802.jpg','The gravity doggie bike, with a low frame and 18 \"wheels, is perfect for girls who are approximately 110 to 120 cm tall.');
/*!40000 ALTER TABLE `bicycles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `details`
--

DROP TABLE IF EXISTS `details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` longtext NOT NULL,
  `photo` longtext NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `details`
--

LOCK TABLES `details` WRITE;
/*!40000 ALTER TABLE `details` DISABLE KEYS */;
INSERT INTO `details` VALUES (1,'Детское сидение для велосипеда','Всесезонное заднее безопасное сидение на велосипед для детей.','children_back_seat.jpg',250),(2,'Велосипедный шлем Abus TEC-TICAL 2.1','Усовершенствованная модель шлема высокого уровня для езды на шоссейных велосипедах. Низкий вес, 31 вентиляционное отверстие, съемная подкладка, конструкция Multi shell In-mold. Дополнительные элементы жесткости ActiCage. Цвет: черный','bicycle_helmet.jpg',150),(3,'Шлем велосипедный детский Stern','Велосипедный шлем Stern для девочек с конструкцией glue-on. Качественная система вентиляции гарантирует превосходную циркуляцию воздуха независимо от скорости. Изделие соответствует международным стандартам безопасности и надежности.','bicycle_helmet_child.jpg',120),(4,'Перчатки Fox Ranger Gel slate blue','Легкие велосипедные перчатки с длинными пальцами. Силиконовые вставками на пальцах для хорошего сцепления, отличная функциональность. Гелевые подушки снижают вибрацию и давление на руки. Совместимость с сенсорным экраном. Цвет: голубой','bicycle_gloves.jpg',90);
/*!40000 ALTER TABLE `details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL,
  `user_id` bigint NOT NULL,
  `place_id` bigint DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `FKoq6pbd7um89xccacm0xsk0xjx` (`place_id`),
  CONSTRAINT `FKoq6pbd7um89xccacm0xsk0xjx` FOREIGN KEY (`place_id`) REFERENCES `places` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'PAYED',1,3),(4,'PAYED',1,5),(7,'PAYED',1,5),(10,'PAYED',1,1),(13,'PAYED',3,3),(15,'FORMED',3,1),(17,'PAYED',1,1),(18,'PAYED',1,5),(21,'PAYED',1,2),(22,'FORMED',1,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_bicycles`
--

DROP TABLE IF EXISTS `orders_bicycles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_bicycles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `bicycle_id` bigint NOT NULL,
  `time_start` time NOT NULL,
  `time_end` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqdonmh71n7wrmqj2hpjf5rpvn` (`order_id`),
  KEY `FKoby5hghb1djk4vkbbn2fm8r4p` (`bicycle_id`),
  CONSTRAINT `FKoby5hghb1djk4vkbbn2fm8r4p` FOREIGN KEY (`bicycle_id`) REFERENCES `bicycles` (`id`),
  CONSTRAINT `FKqdonmh71n7wrmqj2hpjf5rpvn` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_bicycles`
--

LOCK TABLES `orders_bicycles` WRITE;
/*!40000 ALTER TABLE `orders_bicycles` DISABLE KEYS */;
INSERT INTO `orders_bicycles` VALUES (21,1,4,'02:30:00','10:00:00'),(22,4,5,'15:00:00','17:30:00'),(23,7,4,'10:00:00','11:00:00'),(24,7,4,'10:00:00','11:00:00'),(25,10,1,'02:00:00','03:00:00'),(26,13,5,'00:00:00','03:00:00'),(27,15,4,'10:00:00','12:00:00'),(28,17,2,'10:00:00','11:00:00'),(29,18,3,'10:00:00','11:30:00'),(30,21,4,'10:00:00','11:30:00'),(31,21,4,'10:00:00','11:30:00'),(32,21,1,'10:00:00','11:30:00');
/*!40000 ALTER TABLE `orders_bicycles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_details`
--

DROP TABLE IF EXISTS `orders_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_details` (
  `order_id` bigint NOT NULL,
  `detail_id` bigint NOT NULL,
  KEY `FKm70kqyc6841y8vbjm6gjdljrr` (`detail_id`),
  KEY `FK5o977kj2vptwo70fu7w7so9fe` (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_details`
--

LOCK TABLES `orders_details` WRITE;
/*!40000 ALTER TABLE `orders_details` DISABLE KEYS */;
INSERT INTO `orders_details` VALUES (1,4),(1,3),(1,4),(4,3),(7,1),(13,4),(18,4);
/*!40000 ALTER TABLE `orders_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `places`
--

DROP TABLE IF EXISTS `places`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `places` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `place` longtext NOT NULL,
  `lat` double NOT NULL,
  `len` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `places`
--

LOCK TABLES `places` WRITE;
/*!40000 ALTER TABLE `places` DISABLE KEYS */;
INSERT INTO `places` VALUES (1,'Деміївська метро',50.404792,30.516738),(2,'Васильківська метро',50.393328,30.488286),(3,'ФКНК',50.383371,30.471322),(5,'Парк Тараса Шевченка',50.441773,30.512751);
/*!40000 ALTER TABLE `places` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `places_orders`
--

DROP TABLE IF EXISTS `places_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `places_orders` (
  `place_id` bigint NOT NULL,
  `orders_id` bigint NOT NULL,
  UNIQUE KEY `UK_2yjsti9aqwmdkufhl656xw6j9` (`orders_id`),
  KEY `FKe9nqosiarss505wr1vvq2uf48` (`place_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `places_orders`
--

LOCK TABLES `places_orders` WRITE;
/*!40000 ALTER TABLE `places_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `places_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` longtext NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Lizon','lizon@gmail.com','$2a$10$nZcEI958eTdXnu4PsW8J7.oVKq1G6dVEP975GSUmHg03.KLfUd8.a','USER'),(2,'Валера','valera11@gmail.com','$2a$10$og4YxGg3VXMD4otFstFzI.gkLYLr8y3YoZ6pqq26QKdPh5/CoHfC6','USER'),(3,'Sanya','sanya@gmail.com','$2a$10$OHztFv9ipcwK38EGxyHcZeX/T35UYp4PxCWdHfqsWv5Cyc4h9qcmG','USER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-19 18:51:08
