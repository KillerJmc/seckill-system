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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_upms_login_log`
--

LOCK TABLES `e_upms_login_log` WRITE;
/*!40000 ALTER TABLE `e_upms_login_log` DISABLE KEYS */;
INSERT INTO `e_upms_login_log` VALUES (1,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 18:12:52','0|0|0|内网IP|内网IP','Windows 10','QQrN4PofYTzMESDQ',1),(2,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 18:18:29','0|0|0|内网IP|内网IP','Windows 10','Pc3M2UwbA05O97QS',1),(3,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 18:30:37','0|0|0|内网IP|内网IP','Windows 10','SGC5uO9V2GxAv9N6',1),(4,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 18:31:48','0|0|0|内网IP|内网IP','Windows 10','4JuB2tOZgOzQnpiA',1),(5,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 18:34:56','0|0|0|内网IP|内网IP','Windows 10','NC4Uk0B4AsE6UHRF',1),(6,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 19:02:46','0|0|0|内网IP|内网IP','Windows 10','W10YWYbZ4HYIdnlS',1),(7,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 19:18:18','0|0|0|内网IP|内网IP','Windows 10','3R7vWLcsBbiDz1uN',1),(8,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 19:24:11','0|0|0|内网IP|内网IP','Windows 10','wQBNzuDZ8qLQ0SQA',1),(9,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 19:57:11','0|0|0|内网IP|内网IP','Windows 10','dzfKaFwn3wfAAQFO',1),(10,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 20:37:50','0|0|0|内网IP|内网IP','Windows 10','uk7gDRSAjwGo5pmD',1),(11,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 21:26:21','0|0|0|内网IP|内网IP','Windows 10','QTyYT3jG2Wd6S4ez',1),(12,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 22:34:42','0|0|0|内网IP|内网IP','Windows 10','Al17ZgPKZCUjXlzc',1),(13,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 22:34:42','0|0|0|内网IP|内网IP','Windows 10','neHc355gROYladcT',1),(14,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 22:39:07','0|0|0|内网IP|内网IP','Windows 10','w6JCvlGcnrYoy2CP',1),(15,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 22:52:39','0|0|0|内网IP|内网IP','Windows 10','zU7omEVdW6wNzj2T',1),(16,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 23:20:09','0|0|0|内网IP|内网IP','Windows 10','CF4lTKmwpL39grFL',1),(17,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 23:20:09','0|0|0|内网IP|内网IP','Windows 10','TaiGcwruoxtC92Bi',1),(18,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 23:22:03','0|0|0|内网IP|内网IP','Windows 10','3e73OkjVJYhdbB5a',1),(19,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 23:23:48','0|0|0|内网IP|内网IP','Windows 10','KAcEH2HeLPQ11xKe',1),(20,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 23:39:13','0|0|0|内网IP|内网IP','Windows 10','Oer7MPZE1ENjitl0',1),(21,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 23:43:33','0|0|0|内网IP|内网IP','Windows 10','c1pwjhc9bVEXgSd7',1),(22,'Chrome 9 98','Computer','192.168.2.100','2022-02-14 23:45:11','0|0|0|内网IP|内网IP','Windows 10','ugOSqbdr7j0g6R8D',1),(23,'Chrome 9 98','Computer','192.168.2.100','2022-02-15 00:19:45','0|0|0|内网IP|内网IP','Windows 10','r5nq6Eg1xBGQ9CbX',1),(24,'Chrome 9 98','Computer','192.168.2.100','2022-02-15 00:31:14','0|0|0|内网IP|内网IP','Windows 10','Bj13WXnuPPujxmtY',1),(25,'Chrome 9 98','Computer','192.168.2.100','2022-02-15 00:35:38','0|0|0|内网IP|内网IP','Windows 10','gInR8VIvgmZARbF3',1),(26,'Chrome 9 98','Computer','192.168.2.100','2022-02-15 00:37:51','0|0|0|内网IP|内网IP','Windows 10','aEviikpujk9Jm64i',1),(27,'Chrome 9 98','Computer','192.168.2.100','2022-02-15 00:43:06','0|0|0|内网IP|内网IP','Windows 10','K9XacegqZapY1iw8',1),(28,'Chrome 9 98','Computer','192.168.2.100','2022-02-15 01:04:20','0|0|0|内网IP|内网IP','Windows 10','bbp9TZG21qg5lW4y',1),(29,'Chrome 9 98','Computer','192.168.1.9','2022-02-15 01:31:06','0|0|0|内网IP|内网IP','Windows 10','uYpVykGzqq6q1jwZ',1),(30,'Chrome 9 98','Computer','192.168.1.9','2022-02-15 01:34:03','0|0|0|内网IP|内网IP','Windows 10','GIUAW5hAlJfXgqCf',1),(31,'Chrome 9 98','Computer','192.168.1.9','2022-02-15 01:36:41','0|0|0|内网IP|内网IP','Windows 10','B5LF65rgii2xr08z',1),(32,'Chrome 9 98','Computer','192.168.1.9','2022-02-15 01:45:50','0|0|0|内网IP|内网IP','Windows 10','e5sIe7SHFCVSMiSP',1),(33,'Chrome 9 98','Computer','192.168.1.9','2022-02-15 02:00:08','0|0|0|内网IP|内网IP','Windows 10','Qa6Vv8GI3aQ0wevV',1),(34,'Chrome 9 98','Computer','192.168.1.9','2022-02-15 02:14:58','0|0|0|内网IP|内网IP','Windows 10','dQhTZ75wb0sSahLJ',1),(35,'Chrome 9 98','Computer','192.168.1.9','2022-02-15 02:26:46','0|0|0|内网IP|内网IP','Windows 10','g2at628FQoN9WP3K',1),(36,'Chrome 9 98','Computer','192.168.1.9','2022-02-15 02:30:18','0|0|0|内网IP|内网IP','Windows 10','P7w08LqruVu8XVD7',1),(37,'Chrome 9 98','Computer','192.168.1.9','2022-02-15 02:34:21','0|0|0|内网IP|内网IP','Windows 10','46dhRDyNYsSsj05a',1),(38,'Chrome 9 98','Computer','192.168.1.9','2022-02-15 02:58:40','0|0|0|内网IP|内网IP','Windows 10','JNprKkgQSuxdHU4w',1),(39,'Chrome 9 98','Computer','192.168.1.9','2022-02-15 03:00:05','0|0|0|内网IP|内网IP','Windows 10','YHmgWcPq1O79gTk1',1),(40,'Chrome 9 98','Computer','192.168.1.9','2022-02-15 03:20:03','0|0|0|内网IP|内网IP','Windows 10','MEu3o4M9ZyFL8Exz',1),(41,'Chrome 9 98','Computer','192.168.1.9','2022-02-15 03:28:03','0|0|0|内网IP|内网IP','Windows 10','9HNdK3LfO9ZK6PoD',1),(42,'Chrome 9 98','Computer','192.168.1.9','2022-02-15 03:29:15','0|0|0|内网IP|内网IP','Windows 10','dAHXTyWBzalOr2r2',1),(43,'Chrome 9 98','Computer','192.168.1.9','2022-02-15 03:30:14','0|0|0|内网IP|内网IP','Windows 10','CyicwMqIoNUjLdA8',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_upms_menu`
--

LOCK TABLES `e_upms_menu` WRITE;
/*!40000 ALTER TABLE `e_upms_menu` DISABLE KEYS */;
INSERT INTO `e_upms_menu` VALUES (1,'2022-02-14 18:12:44',NULL,'$manager','fa fa-cogs','系统管理',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL),(2,'2022-02-14 18:12:44',NULL,'EruptMenu','fa fa-list-ul','菜单维护',NULL,NULL,10,1,'tree','EruptMenu',NULL,NULL,1),(3,'2022-02-14 18:12:44',NULL,'EruptRole',NULL,'角色维护',NULL,NULL,20,1,'table','EruptRole',NULL,NULL,1),(4,'2022-02-14 18:12:44',NULL,'EruptOrg','fa fa-users','组织维护',NULL,NULL,30,1,'tree','EruptOrg',NULL,NULL,1),(5,'2022-02-14 18:12:44',NULL,'EruptPost','fa fa-id-card','岗位维护',NULL,NULL,35,1,'table','EruptPost',NULL,NULL,1),(6,'2022-02-14 18:12:44',NULL,'EruptUser','fa fa-user','用户维护',NULL,NULL,40,1,'table','EruptUser',NULL,NULL,1),(7,'2022-02-14 18:12:44',NULL,'EruptDict',NULL,'字典维护',NULL,NULL,50,1,'table','EruptDict',NULL,NULL,1),(8,'2022-02-14 18:12:44',NULL,'EruptDictItem',NULL,'字典项',NULL,NULL,10,2,'table','EruptDictItem',NULL,NULL,7),(9,'2022-02-14 18:12:44',NULL,'EruptLoginLog',NULL,'登录日志',NULL,NULL,60,1,'table','EruptLoginLog',NULL,NULL,1),(10,'2022-02-14 18:12:44',NULL,'EruptOperateLog',NULL,'操作日志',NULL,NULL,70,1,'table','EruptOperateLog',NULL,NULL,1),(11,'2022-02-14 18:13:30',NULL,'Activity',NULL,'活动',NULL,NULL,80,1,NULL,NULL,1,NULL,NULL),(12,'2022-02-14 18:14:12',NULL,'ActivityRule',NULL,'规则管理',NULL,NULL,90,1,'table','ActivityRule',1,NULL,11),(13,'2022-02-14 18:14:34',NULL,'Product',NULL,'产品管理',NULL,NULL,100,1,'table','Product',1,NULL,11),(14,'2022-02-14 18:15:04',NULL,'SeckillActivity',NULL,'活动管理',NULL,NULL,110,1,'table','SeckillActivity',1,NULL,11),(16,'2022-02-15 00:21:05',NULL,'SeckillApplicationForm',NULL,'客户申请表',NULL,'NO_EDIT',120,1,'table','SeckillApplicationForm',1,NULL,11),(17,'2022-02-15 00:22:17','2022-02-15 00:23:14','SeckillSuccess',NULL,'秒杀成功客户表',NULL,'NO_EDIT',130,1,'table','SeckillSuccess',1,1,11);
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_upms_operate_log`
--

LOCK TABLES `e_upms_operate_log` WRITE;
/*!40000 ALTER TABLE `e_upms_operate_log` DISABLE KEYS */;
INSERT INTO `e_upms_operate_log` VALUES (1,'新增 | 菜单配置','2022-02-14 18:13:30',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/EruptMenu','POST','{\"code\":\"Activity\",\"name\":\"活动\",\"status\":\"1\",\"sort\":80}',_binary '',64,1),(2,'新增 | 菜单配置','2022-02-14 18:14:12',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/EruptMenu','POST','{\"code\":\"ActivityRule\",\"name\":\"规则管理\",\"status\":\"1\",\"type\":\"table\",\"value\":\"ActivityRule\",\"sort\":90,\"parentMenu\":{\"id\":11,\"name\":\"活动\"}}',_binary '',22,1),(3,'新增 | 菜单配置','2022-02-14 18:14:34',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/EruptMenu','POST','{\"code\":\"Product\",\"name\":\"产品管理\",\"status\":\"1\",\"type\":\"table\",\"value\":\"Product\",\"sort\":100,\"parentMenu\":{\"id\":11,\"name\":\"活动\"}}',_binary '',20,1),(4,'新增 | 菜单配置','2022-02-14 18:15:04',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/EruptMenu','POST','{\"code\":\"SeckillActivity\",\"name\":\"活动管理\",\"status\":\"1\",\"type\":\"table\",\"value\":\"SeckillActivity\",\"sort\":110,\"parentMenu\":{\"id\":11,\"name\":\"活动\"}}',_binary '',29,1),(5,'新增 | 筛选规则管理','2022-02-14 18:15:49',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/ActivityRule','POST','{\"ruleName\":\"Test\",\"workStatus\":true,\"minAge\":1,\"maxAge\":60,\"inCreditBlacklist\":false,\"maxOverdueTimes\":1,\"maxOverdueDays\":1,\"maxOverdueMoney\":1}',_binary '',27,1),(6,'新增 | 筛选规则管理','2022-02-14 18:18:48',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/ActivityRule','POST','{\"ruleId\":2,\"ruleName\":\"Test\",\"workStatus\":true,\"minAge\":1,\"maxAge\":60,\"inCreditBlacklist\":false,\"maxOverdueMoney\":0}',_binary '',43,1),(7,'新增 | 产品管理页面','2022-02-14 18:19:09',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/Product','POST','{\"productId\":2,\"name\":\"两万元测试\",\"info\":\"Test\"}',_binary '',18,1),(8,'新增 | 活动管理','2022-02-14 19:19:35',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/SeckillActivity','POST','{\"seckillId\":111,\"productId\":\"2\",\"amount\":100,\"startTime\":\"2022-02-15 19:19:20\",\"activityInfo\":\"666666\",\"activityRuleId\":\"2\"}',_binary '',77,1),(9,'新增 | 菜单配置','2022-02-14 20:03:01',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/EruptMenu','POST','{\"code\":\"JoinSeckillSuccess\",\"name\":\"成功申请列表\",\"status\":\"1\",\"type\":\"link\",\"value\":\"JoinSeckillSuccess\",\"sort\":120,\"parentMenu\":{\"id\":14,\"name\":\"活动管理\"}}',_binary '',48,1),(10,'修改 | 菜单配置','2022-02-14 20:03:55',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/EruptMenu','PUT','{\"code\":\"JoinSeckillSuccess\",\"name\":\"成功申请列表\",\"status\":\"1\",\"type\":\"button\",\"value\":\"JoinSeckillSuccess\",\"sort\":120,\"parentMenu\":{\"id\":14,\"name\":\"活动管理\"},\"id\":15}',_binary '',44,1),(11,'活动管理 | 秒杀成功用户列表','2022-02-14 20:05:14',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/SeckillActivity/operator/-1718533187','POST','{\"ids\":[],\"param\":null}',_binary '',7,1),(12,'活动管理 | 秒杀成功用户列表','2022-02-14 20:05:17',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/SeckillActivity/operator/-1718533187','POST','{\"ids\":[],\"param\":null}',_binary '',3,1),(13,'活动管理 | 已申请用户列表','2022-02-14 20:05:19',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/SeckillActivity/operator/-1907691754','POST','{\"ids\":[],\"param\":null}',_binary '',3,1),(14,'活动管理 | 已申请用户列表','2022-02-14 20:05:23',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/SeckillActivity/operator/-1907691754','POST','{\"ids\":[],\"param\":null}',_binary '',3,1),(15,'活动管理 | 已申请用户列表','2022-02-14 20:05:25',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/SeckillActivity/operator/-1907691754','POST','{\"ids\":[],\"param\":null}',_binary '',3,1),(16,'活动管理 | 秒杀成功用户列表','2022-02-14 20:05:27',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/SeckillActivity/operator/-1718533187','POST','{\"ids\":[],\"param\":null}',_binary '',3,1),(17,'活动管理 | 已申请用户列表','2022-02-14 20:37:58',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/SeckillActivity/operator/-1907691754','POST','{\"ids\":[\"1\"],\"param\":null}',_binary '',20,1),(18,'活动管理 | 已申请用户列表','2022-02-14 20:49:15',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/SeckillActivity/operator/-1907691754','POST','{\"ids\":[\"1\"],\"param\":null}',_binary '',6,1),(19,'修改 | 菜单配置','2022-02-14 21:27:06',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/EruptMenu','PUT','{\"code\":\"JoinSeckillSuccess\",\"name\":\"成功申请列表\",\"status\":\"1\",\"type\":\"newWindow\",\"value\":\"JoinSeckillSuccess\",\"sort\":120,\"parentMenu\":{\"id\":14,\"name\":\"活动管理\"},\"id\":15}',_binary '',58,1),(20,'活动管理 | 已申请用户列表','2022-02-14 21:27:25',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/SeckillActivity/operator/-1907691754','POST','{\"ids\":[\"1\"],\"param\":null}',_binary '',5,1),(21,'活动管理 | 秒杀成功用户列表','2022-02-14 21:27:28',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/SeckillActivity/operator/-1718533187','POST','{\"ids\":[\"1\"],\"param\":null}',_binary '',5,1),(22,'删除 | 菜单配置','2022-02-14 22:35:33',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/EruptMenu/15','DELETE',NULL,_binary '',68,1),(23,'新增 | 产品管理页面','2022-02-14 22:39:28',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/Product','POST','{\"productId\":3,\"name\":\"30000\",\"info\":\"test\"}',_binary '',41,1),(24,'活动管理 | 已申请用户列表','2022-02-14 23:20:17',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/SeckillActivity/operator/-1907691754','POST','{\"ids\":[\"1\"],\"param\":null}',_binary '',19,1),(25,'活动管理 | 已申请用户列表','2022-02-14 23:22:07',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/SeckillActivity/operator/-1907691754','POST','{\"ids\":[\"1\"],\"param\":null}',_binary '',19,1),(26,'活动管理 | 已申请用户列表','2022-02-14 23:23:55',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/SeckillActivity/operator/-1907691754','POST','{\"ids\":[\"1\"],\"param\":null}',_binary '',20,1),(27,'新增 | 菜单配置','2022-02-15 00:21:05',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/EruptMenu','POST','{\"code\":\"SeckillApplicationForm\",\"name\":\"客户申请表\",\"status\":\"1\",\"type\":\"table\",\"value\":\"SeckillApplicationForm\",\"sort\":120,\"powerOff\":\"NO_EDIT\",\"parentMenu\":{\"id\":11,\"name\":\"活动\"}}',_binary '',102,1),(28,'新增 | 菜单配置','2022-02-15 00:22:10',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/EruptMenu','POST','{\"code\":\"SeckillSuccess\",\"name\":\"秒杀成功客户表\",\"type\":\"table\",\"value\":\"SeckillSuccess\",\"powerOff\":\"NO_EDIT\"}',_binary '',7,1),(29,'新增 | 菜单配置','2022-02-15 00:22:17',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/EruptMenu','POST','{\"code\":\"SeckillSuccess\",\"name\":\"秒杀成功客户表\",\"status\":\"1\",\"type\":\"table\",\"value\":\"SeckillSuccess\",\"powerOff\":\"NO_EDIT\"}',_binary '',26,1),(30,'修改 | 菜单配置','2022-02-15 00:22:53',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/EruptMenu','PUT','{\"code\":\"SeckillSuccess\",\"name\":\"秒杀成功客户表\",\"status\":\"1\",\"type\":\"table\",\"value\":\"SeckillSuccess\",\"powerOff\":\"NO_EDIT\",\"parentMenu\":{\"id\":\"11\",\"name\":\"活动\"},\"id\":17}',_binary '',46,1),(31,'修改 | 菜单配置','2022-02-15 00:23:14',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/data/modify/EruptMenu','PUT','{\"code\":\"SeckillSuccess\",\"name\":\"秒杀成功客户表\",\"status\":\"1\",\"type\":\"table\",\"value\":\"SeckillSuccess\",\"sort\":130,\"powerOff\":\"NO_EDIT\",\"parentMenu\":{\"id\":11,\"name\":\"活动\"},\"id\":17}',_binary '',30,1),(32,'导出Excel | 参与成功的客户信息','2022-02-15 00:45:02',NULL,'192.168.2.100','0|0|0|内网IP|内网IP','http://localhost:8080/erupt-api/excel/export/SeckillSuccess','POST','[]',_binary '',807,1),(33,'新增 | 参与成功的客户信息','2022-02-15 01:31:42',NULL,'192.168.1.9','0|0|0|内网IP|内网IP','http://localhost:81/erupt-api/data/modify/SeckillSuccess','POST','{\"accountId\":23478,\"orderId\":\"234\"}',_binary '',114,1),(34,'导出Excel | 筛选成功的客户信息','2022-02-15 02:35:17',NULL,'192.168.1.9','0|0|0|内网IP|内网IP','http://localhost:81/erupt-api/excel/export/SeckillApplicationForm','POST','[{\"key\":\"seckillId\",\"value\":\"23456\"}]',_binary '',2246,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_upms_user`
--

LOCK TABLES `e_upms_user` WRITE;
/*!40000 ALTER TABLE `e_upms_user` DISABLE KEYS */;
INSERT INTO `e_upms_user` VALUES (1,'erupt','2022-02-14 18:12:43',NULL,'erupt',NULL,_binary '',_binary '','610d44f73b7709169e8e06ca4ac5af8e',NULL,NULL,_binary '',NULL,NULL,NULL,NULL,NULL,NULL);
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

-- Dump completed on 2022-02-15  3:38:44
