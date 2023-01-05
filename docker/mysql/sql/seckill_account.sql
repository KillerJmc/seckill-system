-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: seckill_account
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sk_customer`
--

DROP TABLE IF EXISTS `sk_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sk_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` int(11) NOT NULL COMMENT '账号，等于自然主键 + 10000',
  `id_number` char(18) NOT NULL COMMENT '身份证号',
  `name` varchar(20) NOT NULL COMMENT '客户姓名',
  `password` varchar(100) NOT NULL COMMENT '账号密码',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_account_id_uindex` (`account`),
  UNIQUE KEY `customer_id_number_uindex` (`id_number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='客户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sk_customer`
--

LOCK TABLES `sk_customer` WRITE;
/*!40000 ALTER TABLE `sk_customer` DISABLE KEYS */;
INSERT INTO `sk_customer` VALUES (1,10000,'123456200108097890','江明畅','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','2022-11-04 17:44:20','2022-11-04 17:44:40');
/*!40000 ALTER TABLE `sk_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sk_customer_info`
--

DROP TABLE IF EXISTS `sk_customer_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sk_customer_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` int(11) NOT NULL COMMENT '账号',
  `work_status` tinyint(1) NOT NULL COMMENT '是否有工作',
  `in_credit_blacklist` tinyint(1) NOT NULL COMMENT '是否被列入失信人名单',
  `overdue_times` int(11) NOT NULL DEFAULT '0' COMMENT '三年内逾期次数',
  `overdue_money` double NOT NULL DEFAULT '0' COMMENT '三年内逾期金额',
  `overdue_days` int(11) NOT NULL DEFAULT '0' COMMENT '三年内逾期天数',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_info_id_number_uindex` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='客户信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sk_customer_info`
--

LOCK TABLES `sk_customer_info` WRITE;
/*!40000 ALTER TABLE `sk_customer_info` DISABLE KEYS */;
INSERT INTO `sk_customer_info` VALUES (1,10000,1,0,0,0,0,'2022-11-04 17:46:48','2022-11-04 17:47:02');
/*!40000 ALTER TABLE `sk_customer_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sk_pre_screening`
--

DROP TABLE IF EXISTS `sk_pre_screening`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sk_pre_screening` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `seckill_id` int(11) DEFAULT NULL COMMENT '秒杀活动id',
  `account` int(11) NOT NULL COMMENT '账号',
  `name` varchar(20) NOT NULL COMMENT '客户姓名',
  `pass` tinyint(1) NOT NULL COMMENT '是否通过初筛',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='初筛';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sk_pre_screening`
--

LOCK TABLES `sk_pre_screening` WRITE;
/*!40000 ALTER TABLE `sk_pre_screening` DISABLE KEYS */;
/*!40000 ALTER TABLE `sk_pre_screening` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03  0:39:51
