-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: seckill_service
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
-- Table structure for table `sk_product`
--

DROP TABLE IF EXISTS `sk_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sk_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `name` varchar(50) NOT NULL COMMENT '商品名称',
  `price` double NOT NULL COMMENT '商品价格',
  `info` varchar(100) NOT NULL COMMENT '商品信息',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_name_uindex` (`name`),
  UNIQUE KEY `product_product_id_uindex` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sk_product`
--

LOCK TABLES `sk_product` WRITE;
/*!40000 ALTER TABLE `sk_product` DISABLE KEYS */;
INSERT INTO `sk_product` VALUES (1,1,'限量一万元定期存款',10000,'一万元定期存款，限量出售，仅售10万份！有兴趣的朋友赶紧前来抢购。','2022-11-04 17:49:18','2022-11-04 17:49:33');
/*!40000 ALTER TABLE `sk_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sk_seckill_activity`
--

DROP TABLE IF EXISTS `sk_seckill_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sk_seckill_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `seckill_id` int(11) NOT NULL COMMENT '秒杀活动id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `amount` int(11) NOT NULL COMMENT '活动中商品总量',
  `start_time` datetime NOT NULL COMMENT '秒杀活动开始时间',
  `activity_info` varchar(300) NOT NULL COMMENT '秒杀活动信息',
  `activity_rule_id` bigint(20) NOT NULL COMMENT '秒杀规则id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `seckill_activity_seckill_id_uindex` (`seckill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='秒杀活动';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sk_seckill_activity`
--

LOCK TABLES `sk_seckill_activity` WRITE;
/*!40000 ALTER TABLE `sk_seckill_activity` DISABLE KEYS */;
INSERT INTO `sk_seckill_activity` VALUES (1,1298850693,1,100000,'2022-11-21 16:47:00','2022我们携手攻克时艰，感谢有你，对本行的支持，才有了我们今天的成功，为回馈广大客户，我们现决定将一件产品进行售卖，因为产品较为难得，所以采取抢购的方法。',1,'2022-11-04 17:50:48','2022-11-21 16:46:32');
/*!40000 ALTER TABLE `sk_seckill_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sk_seckill_activity_rule`
--

DROP TABLE IF EXISTS `sk_seckill_activity_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sk_seckill_activity_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rule_id` int(11) NOT NULL COMMENT '规则id',
  `rule_name` varchar(20) NOT NULL COMMENT '规则名称',
  `work_status` tinyint(1) NOT NULL COMMENT '工作状态（在岗或失业）',
  `min_age` int(11) DEFAULT '0' COMMENT '最小年龄',
  `max_age` int(11) DEFAULT '200' COMMENT '最大年龄',
  `in_credit_blacklist` tinyint(1) NOT NULL COMMENT '是否在失信人名单中',
  `max_overdue_times` int(11) DEFAULT '100' COMMENT '三年内最多逾期次数',
  `max_overdue_days` int(11) DEFAULT '365' COMMENT '三年内最多逾期天数',
  `max_overdue_money` double DEFAULT '100000000' COMMENT '三年内最多逾期金额',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='秒杀活动规则';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sk_seckill_activity_rule`
--

LOCK TABLES `sk_seckill_activity_rule` WRITE;
/*!40000 ALTER TABLE `sk_seckill_activity_rule` DISABLE KEYS */;
INSERT INTO `sk_seckill_activity_rule` VALUES (1,1,'默认规则',1,18,35,0,3,30,100000,'2022-11-04 17:52:12','2022-11-04 17:52:26');
/*!40000 ALTER TABLE `sk_seckill_activity_rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sk_seckill_application_form`
--

DROP TABLE IF EXISTS `sk_seckill_application_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sk_seckill_application_form` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `seckill_id` int(11) NOT NULL COMMENT '秒杀活动id',
  `account_id` int(11) NOT NULL COMMENT '客户id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `seckill_application_account_id_uindex` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='秒杀活动客户申请表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sk_seckill_application_form`
--

LOCK TABLES `sk_seckill_application_form` WRITE;
/*!40000 ALTER TABLE `sk_seckill_application_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `sk_seckill_application_form` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03  0:39:52
