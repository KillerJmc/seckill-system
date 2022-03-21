-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: seckill_payment_db
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sk_seckill_success`
--

DROP TABLE IF EXISTS `sk_seckill_success`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sk_seckill_success` (
  `id` int NOT NULL AUTO_INCREMENT,
  `seckill_id` int NOT NULL COMMENT '秒杀活动id',
  `account_id` int NOT NULL COMMENT '客户id',
  `order_id` varchar(100) NOT NULL COMMENT '订单id',
  `paid` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已支付订单',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sk_seckill_success_seckill_id_uindex` (`seckill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=402 DEFAULT CHARSET=utf8mb3 COMMENT='秒杀成功客户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sk_seckill_success`
--

LOCK TABLES `sk_seckill_success` WRITE;
/*!40000 ALTER TABLE `sk_seckill_success` DISABLE KEYS */;
INSERT INTO `sk_seckill_success` VALUES (401,10000003,10000003,'1505782085225418752',0,'2022-03-21 13:43:20','2022-03-21 13:43:20');
/*!40000 ALTER TABLE `sk_seckill_success` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sk_storage`
--

DROP TABLE IF EXISTS `sk_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sk_storage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `seckill_id` int NOT NULL COMMENT '秒杀活动id',
  `amount` int NOT NULL COMMENT '库存数量',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `storage_seckill_id_uindex` (`seckill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='库存';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sk_storage`
--

LOCK TABLES `sk_storage` WRITE;
/*!40000 ALTER TABLE `sk_storage` DISABLE KEYS */;
INSERT INTO `sk_storage` VALUES (1,1298850693,10000,'2022-03-04 13:26:23','2022-03-12 14:40:39');
/*!40000 ALTER TABLE `sk_storage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-21 15:19:50
