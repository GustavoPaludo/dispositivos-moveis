-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mobile
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL,
  `product_quantity` int DEFAULT NULL,
  `register_date` datetime(6) DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKruk3w6dhxi1ef9cosucu0a03l` (`order_id`),
  KEY `FK3d704slv66tw6x5hmbm6p2x3u` (`product_id`),
  CONSTRAINT `FK3d704slv66tw6x5hmbm6p2x3u` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKruk3w6dhxi1ef9cosucu0a03l` FOREIGN KEY (`order_id`) REFERENCES `customer_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,1,'2024-05-26 14:08:43.141000',1,1),(2,1,'2024-05-26 14:08:45.938000',1,2),(3,1,'2024-05-26 14:08:48.584000',1,4),(4,1,'2024-05-26 14:11:14.021000',2,1),(5,1,'2024-05-26 14:11:14.032000',2,2),(6,1,'2024-05-26 14:11:14.129000',3,1),(7,1,'2024-05-26 14:11:14.137000',3,2),(52,1,'2024-05-26 14:24:20.505000',52,3),(53,1,'2024-05-26 14:25:33.918000',53,2),(54,1,'2024-05-26 14:25:33.928000',53,3),(55,1,'2024-05-26 14:25:33.936000',53,4),(56,1,'2024-05-26 14:38:54.625000',54,4),(102,1,'2024-06-09 16:54:29.068000',102,1),(103,1,'2024-06-09 16:54:42.534000',103,2),(104,1,'2024-06-09 16:54:42.545000',103,3),(105,1,'2024-06-09 16:54:42.559000',103,4),(106,1,'2024-06-09 17:12:11.006000',104,2),(107,1,'2024-06-09 17:12:11.012000',104,3),(108,1,'2024-06-09 17:23:16.491000',105,1),(152,1,'2024-06-09 17:26:42.356000',152,1),(153,1,'2024-06-09 17:26:42.368000',152,4),(202,1,'2024-06-09 17:27:29.125000',202,1),(203,1,'2024-06-09 17:27:29.136000',202,4),(204,1,'2024-06-09 17:27:36.668000',203,3),(205,1,'2024-06-09 17:27:36.681000',203,4),(206,1,'2024-06-09 17:52:53.030000',204,1),(207,1,'2024-06-09 17:52:53.036000',204,2),(208,1,'2024-06-09 17:52:53.041000',204,3),(209,2,'2024-06-09 17:52:53.046000',204,4),(210,1,'2024-06-09 17:53:26.425000',205,1),(211,2,'2024-06-09 17:53:26.433000',205,2),(212,1,'2024-06-09 17:53:26.442000',205,3),(213,1,'2024-06-09 17:53:26.448000',205,4),(252,3,'2024-06-09 18:21:54.905000',252,4),(253,3,'2024-06-09 18:24:58.634000',253,2),(254,2,'2024-06-09 18:26:34.837000',254,3),(302,1,'2024-06-14 20:57:17.959000',302,1),(303,1,'2024-06-14 21:00:25.289000',303,3);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_seq`
--

DROP TABLE IF EXISTS `cart_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_seq`
--

LOCK TABLES `cart_seq` WRITE;
/*!40000 ALTER TABLE `cart_seq` DISABLE KEYS */;
INSERT INTO `cart_seq` VALUES (401);
/*!40000 ALTER TABLE `cart_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_order`
--

DROP TABLE IF EXISTS `customer_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_order` (
  `id` bigint NOT NULL,
  `register_date` datetime(6) DEFAULT NULL,
  `total_value` double DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf0hyuyamjo7t2121k1hw1psy0` (`user_id`),
  CONSTRAINT `FKf0hyuyamjo7t2121k1hw1psy0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_order`
--

LOCK TABLES `customer_order` WRITE;
/*!40000 ALTER TABLE `customer_order` DISABLE KEYS */;
INSERT INTO `customer_order` VALUES (1,'2024-05-26 14:08:35.279000',205,NULL),(2,'2024-05-26 14:11:14.003000',200,NULL),(3,'2024-05-26 14:11:14.120000',200,NULL),(52,'2024-05-26 14:24:17.449000',10,NULL),(53,'2024-05-26 14:25:33.910000',115,1),(54,'2024-05-26 14:38:54.615000',5,NULL),(102,'2024-06-09 16:54:29.030000',100,NULL),(103,'2024-06-09 16:54:42.525000',115,NULL),(104,'2024-06-09 17:12:10.999000',110,1),(105,'2024-06-09 17:23:16.483000',100,1),(152,'2024-06-09 17:26:42.337000',105,1),(202,'2024-06-09 17:27:29.099000',105,1),(203,'2024-06-09 17:27:36.659000',15,1),(204,'2024-06-09 17:52:53.018000',220,1),(205,'2024-06-09 17:53:26.416000',315,1),(252,'2024-06-09 18:21:54.876000',15,1),(253,'2024-06-09 18:24:58.612000',300,1),(254,'2024-06-09 18:26:34.829000',20,1),(302,'2024-06-14 20:57:17.904000',100,NULL),(303,'2024-06-14 21:00:25.281000',10,1);
/*!40000 ALTER TABLE `customer_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_order_seq`
--

DROP TABLE IF EXISTS `customer_order_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_order_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_order_seq`
--

LOCK TABLES `customer_order_seq` WRITE;
/*!40000 ALTER TABLE `customer_order_seq` DISABLE KEYS */;
INSERT INTO `customer_order_seq` VALUES (401);
/*!40000 ALTER TABLE `customer_order_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Teste','Copo',100,5),(2,'New test','Prato',100,4),(3,'Hello world','Cortina',10,4),(4,'Hello','Lampada',5,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `register_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'gustavofrancepaludo@gmail.com','Paludo','Gustavo','senha123','2024-05-26 14:25:21.319000'),(2,'ana@gmail.com','aninha','ana','senha123','2024-05-26 14:30:18.113000');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-15 19:22:45
