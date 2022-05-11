-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: erupt
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
-- Table structure for table `e_dict`
--

DROP TABLE IF EXISTS `e_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `e_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKf5wwh5osfukkeebw7h2yb4kmp` (`code`),
  KEY `FKi8lurtplfsktg01y6uevop5yp` (`create_user_id`),
  KEY `FKhxpr89ae0g4rd9xpfgcdjf0sa` (`update_user_id`),
  CONSTRAINT `FKhxpr89ae0g4rd9xpfgcdjf0sa` FOREIGN KEY (`update_user_id`) REFERENCES `e_upms_user` (`id`),
  CONSTRAINT `FKi8lurtplfsktg01y6uevop5yp` FOREIGN KEY (`create_user_id`) REFERENCES `e_upms_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_dict`
--

LOCK TABLES `e_dict` WRITE;
/*!40000 ALTER TABLE `e_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `e_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `e_dict_item`
--

DROP TABLE IF EXISTS `e_dict_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `e_dict_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `erupt_dict_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKl0kiq8otpn3fvtlvarebt8xkh` (`code`,`erupt_dict_id`),
  KEY `FKij9x8hwy16dra7d49h483lu2u` (`create_user_id`),
  KEY `FKmlg0pjfxwih4i6r0g0iilh1lu` (`update_user_id`),
  KEY `FKrrbi2dt94rjd8sjt830m3w0a` (`erupt_dict_id`),
  CONSTRAINT `FKij9x8hwy16dra7d49h483lu2u` FOREIGN KEY (`create_user_id`) REFERENCES `e_upms_user` (`id`),
  CONSTRAINT `FKmlg0pjfxwih4i6r0g0iilh1lu` FOREIGN KEY (`update_user_id`) REFERENCES `e_upms_user` (`id`),
  CONSTRAINT `FKrrbi2dt94rjd8sjt830m3w0a` FOREIGN KEY (`erupt_dict_id`) REFERENCES `e_dict` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_dict_item`
--

LOCK TABLES `e_dict_item` WRITE;
/*!40000 ALTER TABLE `e_dict_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `e_dict_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `e_upms_login_log`
--

DROP TABLE IF EXISTS `e_upms_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `e_upms_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `browser` varchar(255) DEFAULT NULL,
  `device_type` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `system_name` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `erupt_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjqnr9oel32kajm6bpvqwomv6m` (`erupt_user_id`),
  CONSTRAINT `FKjqnr9oel32kajm6bpvqwomv6m` FOREIGN KEY (`erupt_user_id`) REFERENCES `e_upms_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_upms_login_log`
--

LOCK TABLES `e_upms_login_log` WRITE;
/*!40000 ALTER TABLE `e_upms_login_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `e_upms_login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `e_upms_menu`
--

DROP TABLE IF EXISTS `e_upms_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `e_upms_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `param` varchar(2000) DEFAULT NULL,
  `power_off` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `parent_menu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK95xpkppt33d2bka0g2d7rgwqt` (`code`),
  KEY `FK4p5siq6l1rf9y47bosayghcsv` (`create_user_id`),
  KEY `FKtm66wffkyyluinneyva8kd2bc` (`update_user_id`),
  KEY `FK5mkgea183mm02v7ic1pdwxy5s` (`parent_menu_id`),
  CONSTRAINT `FK4p5siq6l1rf9y47bosayghcsv` FOREIGN KEY (`create_user_id`) REFERENCES `e_upms_user` (`id`),
  CONSTRAINT `FK5mkgea183mm02v7ic1pdwxy5s` FOREIGN KEY (`parent_menu_id`) REFERENCES `e_upms_menu` (`id`),
  CONSTRAINT `FKtm66wffkyyluinneyva8kd2bc` FOREIGN KEY (`update_user_id`) REFERENCES `e_upms_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_upms_menu`
--

LOCK TABLES `e_upms_menu` WRITE;
/*!40000 ALTER TABLE `e_upms_menu` DISABLE KEYS */;
INSERT INTO `e_upms_menu` VALUES (1,'2022-02-14 18:12:44',NULL,'$manager','fa fa-cogs','系统管理',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL),(2,'2022-02-14 18:12:44',NULL,'EruptMenu','fa fa-list-ul','菜单维护',NULL,NULL,10,1,'tree','EruptMenu',NULL,NULL,1),(3,'2022-02-14 18:12:44','2022-02-16 14:59:29','EruptRole',NULL,'角色维护',NULL,NULL,20,2,'table','EruptRole',NULL,1,1),(4,'2022-02-14 18:12:44','2022-02-16 14:59:55','EruptOrg','fa fa-users','组织维护',NULL,NULL,30,2,'tree','EruptOrg',NULL,1,1),(5,'2022-02-14 18:12:44','2022-02-16 14:59:37','EruptPost','fa fa-id-card','岗位维护',NULL,NULL,35,2,'table','EruptPost',NULL,1,1),(6,'2022-02-14 18:12:44',NULL,'EruptUser','fa fa-user','用户维护',NULL,NULL,40,1,'table','EruptUser',NULL,NULL,1),(7,'2022-02-14 18:12:44','2022-02-16 14:59:46','EruptDict',NULL,'字典维护',NULL,NULL,50,2,'table','EruptDict',NULL,1,1),(8,'2022-02-14 18:12:44',NULL,'EruptDictItem',NULL,'字典项',NULL,NULL,10,2,'table','EruptDictItem',NULL,NULL,7),(9,'2022-02-14 18:12:44',NULL,'EruptLoginLog',NULL,'登录日志',NULL,NULL,60,1,'table','EruptLoginLog',NULL,NULL,1),(10,'2022-02-14 18:12:44',NULL,'EruptOperateLog',NULL,'操作日志',NULL,NULL,70,1,'table','EruptOperateLog',NULL,NULL,1),(11,'2022-02-14 18:13:30',NULL,'Activity',NULL,'活动',NULL,NULL,80,1,NULL,NULL,1,NULL,NULL),(12,'2022-02-14 18:14:12','2022-02-16 16:19:54','ActivityRule',NULL,'规则管理',NULL,NULL,10,1,'table','ActivityRule',1,2,11),(13,'2022-02-14 18:14:34','2022-02-16 16:20:02','Product',NULL,'产品管理',NULL,NULL,20,1,'table','Product',1,2,11),(14,'2022-02-14 18:15:04','2022-02-16 16:23:23','SeckillActivity',NULL,'活动管理',NULL,NULL,30,1,'table','SeckillActivity',1,2,11),(16,'2022-02-15 00:21:05','2022-02-16 16:20:16','SeckillApplicationForm',NULL,'客户申请表',NULL,'NO_EDIT',50,1,'table','SeckillApplicationForm',1,2,11),(17,'2022-02-15 00:22:17','2022-02-16 16:20:20','SeckillSuccess',NULL,'秒杀成功客户表',NULL,'NO_EDIT',60,1,'table','SeckillSuccess',1,2,11),(18,'2022-02-16 16:17:45','2022-02-16 16:23:19','Storage',NULL,'库存管理',NULL,NULL,40,1,'table','Storage',2,2,11);
/*!40000 ALTER TABLE `e_upms_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `e_upms_operate_log`
--

DROP TABLE IF EXISTS `e_upms_operate_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `e_upms_operate_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `api_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `error_info` longtext,
  `ip` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `req_addr` varchar(4000) DEFAULT NULL,
  `req_method` varchar(255) DEFAULT NULL,
  `req_param` varchar(4000) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `total_time` bigint(20) DEFAULT NULL,
  `erupt_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK27xepkxthq9snq3yk6k7iad33` (`erupt_user_id`),
  CONSTRAINT `FK27xepkxthq9snq3yk6k7iad33` FOREIGN KEY (`erupt_user_id`) REFERENCES `e_upms_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_upms_operate_log`
--

LOCK TABLES `e_upms_operate_log` WRITE;
/*!40000 ALTER TABLE `e_upms_operate_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `e_upms_operate_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `e_upms_org`
--

DROP TABLE IF EXISTS `e_upms_org`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `e_upms_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `parent_org_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtj7222kjnkt7pv9kfn9g8ck4h` (`parent_org_id`),
  CONSTRAINT `FKtj7222kjnkt7pv9kfn9g8ck4h` FOREIGN KEY (`parent_org_id`) REFERENCES `e_upms_org` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_upms_org`
--

LOCK TABLES `e_upms_org` WRITE;
/*!40000 ALTER TABLE `e_upms_org` DISABLE KEYS */;
/*!40000 ALTER TABLE `e_upms_org` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `e_upms_post`
--

DROP TABLE IF EXISTS `e_upms_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `e_upms_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKltq5h3n5cyyk5nxtjhg9lhidg` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_upms_post`
--

LOCK TABLES `e_upms_post` WRITE;
/*!40000 ALTER TABLE `e_upms_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `e_upms_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `e_upms_role`
--

DROP TABLE IF EXISTS `e_upms_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `e_upms_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `power_off` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKjgxkp7mr4183tcwosrbqpsl3a` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_upms_role`
--

LOCK TABLES `e_upms_role` WRITE;
/*!40000 ALTER TABLE `e_upms_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `e_upms_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `e_upms_role_menu`
--

DROP TABLE IF EXISTS `e_upms_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `e_upms_role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FKr6bl403chgwjnb6jk0uqqd9x8` (`menu_id`),
  CONSTRAINT `FKgsdnakqsme4htxkiapwmf6tbi` FOREIGN KEY (`role_id`) REFERENCES `e_upms_role` (`id`),
  CONSTRAINT `FKr6bl403chgwjnb6jk0uqqd9x8` FOREIGN KEY (`menu_id`) REFERENCES `e_upms_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_upms_role_menu`
--

LOCK TABLES `e_upms_role_menu` WRITE;
/*!40000 ALTER TABLE `e_upms_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `e_upms_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `e_upms_user`
--

DROP TABLE IF EXISTS `e_upms_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `e_upms_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_admin` bit(1) DEFAULT NULL,
  `is_md5` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `white_ip` varchar(2000) DEFAULT NULL,
  `erupt_org_id` bigint(20) DEFAULT NULL,
  `erupt_post_id` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `erupt_menu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK812t22yn0295dkkvx5gjgahax` (`account`),
  KEY `FK1re8jv3614mkk2wfxscvgvmnm` (`erupt_org_id`),
  KEY `FK53cice19aydjcuykpv847ocdv` (`erupt_post_id`),
  KEY `FKdvwfw4x66ahh1tavd69cnx4i0` (`create_user_id`),
  KEY `FKct3f9stm4eti10401f7rbh5ey` (`update_user_id`),
  KEY `FKga0jd7sahnn1tv14mq4dy5kba` (`erupt_menu_id`),
  CONSTRAINT `FK1re8jv3614mkk2wfxscvgvmnm` FOREIGN KEY (`erupt_org_id`) REFERENCES `e_upms_org` (`id`),
  CONSTRAINT `FK53cice19aydjcuykpv847ocdv` FOREIGN KEY (`erupt_post_id`) REFERENCES `e_upms_post` (`id`),
  CONSTRAINT `FKct3f9stm4eti10401f7rbh5ey` FOREIGN KEY (`update_user_id`) REFERENCES `e_upms_user` (`id`),
  CONSTRAINT `FKdvwfw4x66ahh1tavd69cnx4i0` FOREIGN KEY (`create_user_id`) REFERENCES `e_upms_user` (`id`),
  CONSTRAINT `FKga0jd7sahnn1tv14mq4dy5kba` FOREIGN KEY (`erupt_menu_id`) REFERENCES `e_upms_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_upms_user`
--

LOCK TABLES `e_upms_user` WRITE;
/*!40000 ALTER TABLE `e_upms_user` DISABLE KEYS */;
INSERT INTO `e_upms_user` VALUES (1,'erupt','2022-02-14 18:12:43',NULL,'erupt',NULL,_binary '',_binary '','610d44f73b7709169e8e06ca4ac5af8e',NULL,NULL,_binary '',NULL,NULL,NULL,NULL,NULL,NULL),(2,'Jmc','2022-02-16 15:09:24','2022-02-16 15:09:24','Jmc',NULL,_binary '',_binary '','202cb962ac59075b964b07152d234b70',NULL,NULL,_binary '',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `e_upms_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `e_upms_user_role`
--

DROP TABLE IF EXISTS `e_upms_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `e_upms_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK3h4lekfh26f5f8b7by3ejges6` (`role_id`),
  CONSTRAINT `FK3h4lekfh26f5f8b7by3ejges6` FOREIGN KEY (`role_id`) REFERENCES `e_upms_role` (`id`),
  CONSTRAINT `FKes2ylim5w3ej690ss84sb956x` FOREIGN KEY (`user_id`) REFERENCES `e_upms_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_upms_user_role`
--

LOCK TABLES `e_upms_user_role` WRITE;
/*!40000 ALTER TABLE `e_upms_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `e_upms_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-23 21:24:08
