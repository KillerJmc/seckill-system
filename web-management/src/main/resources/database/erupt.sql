-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: erupt
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
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKf5wwh5osfukkeebw7h2yb4kmp` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';
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
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT NULL COMMENT '显示顺序',
  `erupt_dict_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKl0kiq8otpn3fvtlvarebt8xkh` (`code`,`erupt_dict_id`),
  KEY `FKrrbi2dt94rjd8sjt830m3w0a` (`erupt_dict_id`),
  CONSTRAINT `FKrrbi2dt94rjd8sjt830m3w0a` FOREIGN KEY (`erupt_dict_id`) REFERENCES `e_dict` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典项';
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
  `browser` varchar(255) DEFAULT NULL COMMENT '浏览器',
  `device_type` varchar(255) DEFAULT NULL COMMENT '设备类型',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `region` varchar(255) DEFAULT NULL COMMENT 'IP来源',
  `system_name` varchar(255) DEFAULT NULL COMMENT '操作系统',
  `token` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='登录日志';
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
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `param` varchar(2000) DEFAULT NULL COMMENT '自定义参数',
  `sort` int(11) DEFAULT NULL COMMENT '顺序',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `type` varchar(255) DEFAULT NULL COMMENT '菜单类型',
  `value` varchar(255) DEFAULT NULL COMMENT '类型值',
  `parent_menu_id` bigint(20) DEFAULT NULL COMMENT '上级菜单',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK95xpkppt33d2bka0g2d7rgwqt` (`code`),
  KEY `FK5mkgea183mm02v7ic1pdwxy5s` (`parent_menu_id`),
  CONSTRAINT `FK5mkgea183mm02v7ic1pdwxy5s` FOREIGN KEY (`parent_menu_id`) REFERENCES `e_upms_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COMMENT='菜单管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_upms_menu`
--

LOCK TABLES `e_upms_menu` WRITE;
/*!40000 ALTER TABLE `e_upms_menu` DISABLE KEYS */;
INSERT INTO `e_upms_menu` VALUES (1,NULL,'2022-11-29 03:08:36',NULL,NULL,'$manager','fa fa-cogs','系统管理',NULL,1,1,NULL,NULL,NULL),(2,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptMenu','','菜单管理',NULL,0,1,'tree','EruptMenu',1),(3,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptMenu@ADD',NULL,'新增',NULL,10,1,'button','EruptMenu@ADD',2),(4,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptMenu@EDIT',NULL,'修改',NULL,20,1,'button','EruptMenu@EDIT',2),(5,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptMenu@DELETE',NULL,'删除',NULL,30,1,'button','EruptMenu@DELETE',2),(6,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptMenu@VIEW_DETAIL',NULL,'详情',NULL,40,1,'button','EruptMenu@VIEW_DETAIL',2),(7,NULL,'2022-11-29 03:08:36','Jmc','2022-11-29 03:11:19','EruptRole',NULL,'角色管理',NULL,10,2,'table','EruptRole',1),(8,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptRole@ADD',NULL,'新增',NULL,10,1,'button','EruptRole@ADD',7),(9,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptRole@EDIT',NULL,'修改',NULL,20,1,'button','EruptRole@EDIT',7),(10,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptRole@DELETE',NULL,'删除',NULL,30,1,'button','EruptRole@DELETE',7),(11,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptRole@VIEW_DETAIL',NULL,'详情',NULL,40,1,'button','EruptRole@VIEW_DETAIL',7),(12,NULL,'2022-11-29 03:08:36','Jmc','2022-11-29 03:11:27','EruptOrg',NULL,'组织维护',NULL,20,2,'tree','EruptOrg',1),(13,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptOrg@ADD',NULL,'新增',NULL,10,1,'button','EruptOrg@ADD',12),(14,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptOrg@EDIT',NULL,'修改',NULL,20,1,'button','EruptOrg@EDIT',12),(15,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptOrg@DELETE',NULL,'删除',NULL,30,1,'button','EruptOrg@DELETE',12),(16,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptOrg@VIEW_DETAIL',NULL,'详情',NULL,40,1,'button','EruptOrg@VIEW_DETAIL',12),(17,NULL,'2022-11-29 03:08:36','Jmc','2022-11-29 03:11:35','EruptPost',NULL,'岗位维护',NULL,30,2,'tree','EruptPost',1),(18,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptPost@ADD',NULL,'新增',NULL,10,1,'button','EruptPost@ADD',17),(19,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptPost@EDIT',NULL,'修改',NULL,20,1,'button','EruptPost@EDIT',17),(20,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptPost@DELETE',NULL,'删除',NULL,30,1,'button','EruptPost@DELETE',17),(21,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptPost@VIEW_DETAIL',NULL,'详情',NULL,40,1,'button','EruptPost@VIEW_DETAIL',17),(22,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptUser','','用户配置',NULL,40,1,'table','EruptUser',1),(23,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptUser@ADD',NULL,'新增',NULL,10,1,'button','EruptUser@ADD',22),(24,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptUser@EDIT',NULL,'修改',NULL,20,1,'button','EruptUser@EDIT',22),(25,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptUser@DELETE',NULL,'删除',NULL,30,1,'button','EruptUser@DELETE',22),(26,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptUser@VIEW_DETAIL',NULL,'详情',NULL,40,1,'button','EruptUser@VIEW_DETAIL',22),(27,NULL,'2022-11-29 03:08:36','Jmc','2022-11-29 03:11:42','EruptDict',NULL,'数据字典',NULL,50,2,'table','EruptDict',1),(28,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptDict@ADD',NULL,'新增',NULL,10,1,'button','EruptDict@ADD',27),(29,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptDict@EDIT',NULL,'修改',NULL,20,1,'button','EruptDict@EDIT',27),(30,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptDict@DELETE',NULL,'删除',NULL,30,1,'button','EruptDict@DELETE',27),(31,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptDict@EXPORT',NULL,'导出',NULL,40,1,'button','EruptDict@EXPORT',27),(32,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptDict@VIEW_DETAIL',NULL,'详情',NULL,50,1,'button','EruptDict@VIEW_DETAIL',27),(33,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptDictItem','','字典项',NULL,60,2,'table','EruptDictItem',1),(34,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptDictItem@ADD',NULL,'新增',NULL,10,1,'button','EruptDictItem@ADD',33),(35,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptDictItem@EDIT',NULL,'修改',NULL,20,1,'button','EruptDictItem@EDIT',33),(36,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptDictItem@DELETE',NULL,'删除',NULL,30,1,'button','EruptDictItem@DELETE',33),(37,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptDictItem@EXPORT',NULL,'导出',NULL,40,1,'button','EruptDictItem@EXPORT',33),(38,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptDictItem@VIEW_DETAIL',NULL,'详情',NULL,50,1,'button','EruptDictItem@VIEW_DETAIL',33),(39,NULL,'2022-11-29 03:08:36','Jmc','2022-11-29 12:42:33','EruptOnline',NULL,'在线用户',NULL,65,2,'table','EruptOnline',1),(40,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptOnline@EXPORT',NULL,'导出',NULL,10,1,'button','EruptOnline@EXPORT',39),(41,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptLoginLog','','登录日志',NULL,70,1,'table','EruptLoginLog',1),(42,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptLoginLog@EXPORT',NULL,'导出',NULL,10,1,'button','EruptLoginLog@EXPORT',41),(43,NULL,'2022-11-29 03:08:36',NULL,NULL,'EruptOperateLog','','操作日志',NULL,80,1,'table','EruptOperateLog',1),(44,'Jmc','2022-11-29 03:13:36','Jmc','2022-11-29 03:14:01','bn77Kyld',NULL,'活动',NULL,90,1,NULL,NULL,NULL),(45,'Jmc','2022-11-29 03:14:16','Jmc','2022-11-29 03:22:28','hSMgFYLN',NULL,'规则管理',NULL,100,1,'table','ActivityRule',44),(46,'Jmc','2022-11-29 03:14:29','Jmc','2022-11-29 03:33:24','iL38EDSu',NULL,'产品管理',NULL,110,1,'table','Product',44),(47,'Jmc','2022-11-29 03:14:42','Jmc','2022-11-29 03:37:12','aS5wk06w',NULL,'活动管理',NULL,120,1,'table','SeckillActivity',44),(48,'Jmc','2022-11-29 03:14:49','Jmc','2022-11-29 12:00:19','ekjpLQYW',NULL,'库存管理',NULL,130,1,'table','Storage',44),(49,'Jmc','2022-11-29 03:15:02','Jmc','2022-11-29 12:32:36','HYGAMsw2',NULL,'客户申请表',NULL,140,1,'table','SeckillApplicationForm',44),(50,'Jmc','2022-11-29 03:15:15','Jmc','2022-11-29 12:34:58','72dpjGl5',NULL,'秒杀成功客户表',NULL,150,1,'table','SeckillSuccess',44),(51,NULL,'2022-11-29 03:17:33',NULL,NULL,'8404BQSE',NULL,'新增',NULL,10,1,'button','ActivityRule@ADD',45),(52,NULL,'2022-11-29 03:17:33',NULL,NULL,'phrYvIcV',NULL,'修改',NULL,20,1,'button','ActivityRule@EDIT',45),(53,NULL,'2022-11-29 03:17:33',NULL,NULL,'wm4o84pB',NULL,'删除',NULL,30,1,'button','ActivityRule@DELETE',45),(54,NULL,'2022-11-29 03:17:33',NULL,NULL,'Kt6WiW4e',NULL,'详情',NULL,40,1,'button','ActivityRule@VIEW_DETAIL',45),(55,NULL,'2022-11-29 03:33:24',NULL,NULL,'DFBxNjo4',NULL,'新增',NULL,10,1,'button','Product@ADD',46),(56,NULL,'2022-11-29 03:33:24',NULL,NULL,'gSPxyY5M',NULL,'修改',NULL,20,1,'button','Product@EDIT',46),(57,NULL,'2022-11-29 03:33:24',NULL,NULL,'2Ukv52NY',NULL,'删除',NULL,30,1,'button','Product@DELETE',46),(58,NULL,'2022-11-29 03:33:24',NULL,NULL,'ZtkNCEg4',NULL,'详情',NULL,40,1,'button','Product@VIEW_DETAIL',46),(59,NULL,'2022-11-29 03:37:12',NULL,NULL,'4OEKyrTa',NULL,'新增',NULL,10,1,'button','SeckillActivity@ADD',47),(60,NULL,'2022-11-29 03:37:12',NULL,NULL,'5it07euC',NULL,'修改',NULL,20,1,'button','SeckillActivity@EDIT',47),(61,NULL,'2022-11-29 03:37:12',NULL,NULL,'Ctwtn9eh',NULL,'删除',NULL,30,1,'button','SeckillActivity@DELETE',47),(62,NULL,'2022-11-29 03:37:12',NULL,NULL,'XLatX7NI',NULL,'详情',NULL,40,1,'button','SeckillActivity@VIEW_DETAIL',47),(63,NULL,'2022-11-29 12:00:19',NULL,NULL,'xmyKprbV',NULL,'新增',NULL,10,1,'button','Storage@ADD',48),(64,NULL,'2022-11-29 12:00:19',NULL,NULL,'b9ng71Ze',NULL,'修改',NULL,20,1,'button','Storage@EDIT',48),(65,NULL,'2022-11-29 12:00:19',NULL,NULL,'sToXYzVf',NULL,'删除',NULL,30,1,'button','Storage@DELETE',48),(66,NULL,'2022-11-29 12:00:19',NULL,NULL,'0BUfHraB',NULL,'详情',NULL,40,1,'button','Storage@VIEW_DETAIL',48),(79,NULL,'2022-11-29 12:32:36','Jmc','2022-11-29 12:33:44','vsNeUgjM',NULL,'新增',NULL,10,1,'button','SeckillApplicationForm@NO_ADD',49),(80,NULL,'2022-11-29 12:32:36','Jmc','2022-11-29 12:33:28','Ecivl6uJ',NULL,'修改',NULL,20,1,'button','SeckillApplicationForm@NO_EDIT',49),(81,NULL,'2022-11-29 12:32:36',NULL,NULL,'H6rQmmZh',NULL,'删除',NULL,30,1,'button','SeckillApplicationForm@DELETE',49),(82,NULL,'2022-11-29 12:32:36',NULL,NULL,'LpUDTBZU',NULL,'导出',NULL,40,1,'button','SeckillApplicationForm@EXPORT',49),(83,NULL,'2022-11-29 12:32:36',NULL,NULL,'F1Tk3gGj',NULL,'导入',NULL,50,1,'button','SeckillApplicationForm@IMPORTABLE',49),(84,NULL,'2022-11-29 12:32:36',NULL,NULL,'s7uEHWgX',NULL,'详情',NULL,60,1,'button','SeckillApplicationForm@VIEW_DETAIL',49),(85,NULL,'2022-11-29 12:34:58','Jmc','2022-11-29 12:35:13','uqg9WmIt',NULL,'修改',NULL,10,1,'button','SeckillSuccess@NO_EDIT',50),(86,NULL,'2022-11-29 12:34:58',NULL,NULL,'opz6rbYO',NULL,'删除',NULL,20,1,'button','SeckillSuccess@DELETE',50),(87,NULL,'2022-11-29 12:34:58',NULL,NULL,'07GG4Xa0',NULL,'导出',NULL,30,1,'button','SeckillSuccess@EXPORT',50),(88,NULL,'2022-11-29 12:34:58',NULL,NULL,'ELrs3NbA',NULL,'导入',NULL,40,1,'button','SeckillSuccess@IMPORTABLE',50),(89,NULL,'2022-11-29 12:34:58',NULL,NULL,'fTF9W34D',NULL,'详情',NULL,50,1,'button','SeckillSuccess@VIEW_DETAIL',50);
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
  `api_name` varchar(255) DEFAULT NULL COMMENT '功能名称',
  `create_time` datetime DEFAULT NULL COMMENT '记录时间',
  `error_info` varchar(4000) DEFAULT NULL COMMENT '错误信息',
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP地址',
  `operate_user` varchar(255) DEFAULT NULL COMMENT '操作人',
  `region` varchar(255) DEFAULT NULL COMMENT 'IP来源',
  `req_addr` varchar(4000) DEFAULT NULL COMMENT '请求地址',
  `req_method` varchar(64) DEFAULT NULL COMMENT '请求方法',
  `req_param` longtext COMMENT '请求参数',
  `status` bit(1) DEFAULT NULL COMMENT '是否成功',
  `total_time` bigint(20) DEFAULT NULL COMMENT '请求耗时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='操作日志';
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
  `code` varchar(64) DEFAULT NULL COMMENT '组织编码',
  `name` varchar(255) DEFAULT NULL COMMENT '组织名称',
  `sort` int(11) DEFAULT NULL COMMENT '显示顺序',
  `parent_org_id` bigint(20) DEFAULT NULL COMMENT '上级组织',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKc2wj35ujq2m84uw59dx6wy3gj` (`code`),
  KEY `FKtj7222kjnkt7pv9kfn9g8ck4h` (`parent_org_id`),
  CONSTRAINT `FKtj7222kjnkt7pv9kfn9g8ck4h` FOREIGN KEY (`parent_org_id`) REFERENCES `e_upms_org` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织维护';
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
  `code` varchar(64) DEFAULT NULL COMMENT '岗位编码',
  `name` varchar(255) DEFAULT NULL COMMENT '岗位名称',
  `weight` int(11) DEFAULT NULL COMMENT '岗位权重',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKltq5h3n5cyyk5nxtjhg9lhidg` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位维护';
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
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `sort` int(11) DEFAULT NULL COMMENT '展示顺序',
  `status` bit(1) DEFAULT NULL COMMENT '状态',
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKjgxkp7mr4183tcwosrbqpsl3a` (`code`),
  KEY `FKad39xpgtpmhq0fp5newnabv1g` (`create_user_id`),
  KEY `FKbghup2p4f1x9eokeygyg8p658` (`update_user_id`),
  CONSTRAINT `FKad39xpgtpmhq0fp5newnabv1g` FOREIGN KEY (`create_user_id`) REFERENCES `e_upms_user` (`id`),
  CONSTRAINT `FKbghup2p4f1x9eokeygyg8p658` FOREIGN KEY (`update_user_id`) REFERENCES `e_upms_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色管理';
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `account` varchar(255) DEFAULT NULL COMMENT '用户名',
  `is_admin` bit(1) DEFAULT NULL COMMENT '超管用户',
  `status` bit(1) DEFAULT NULL COMMENT '账户状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `expire_date` datetime DEFAULT NULL COMMENT '账号失效时间',
  `is_md5` bit(1) DEFAULT NULL COMMENT 'md5加密',
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `reset_pwd_time` datetime DEFAULT NULL COMMENT '重置密码时间',
  `white_ip` varchar(2000) DEFAULT NULL COMMENT 'ip白名单',
  `erupt_org_id` bigint(20) DEFAULT NULL COMMENT '所属组织',
  `erupt_post_id` bigint(20) DEFAULT NULL COMMENT '岗位',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL,
  `erupt_menu_id` bigint(20) DEFAULT NULL COMMENT '首页菜单',
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_upms_user`
--

LOCK TABLES `e_upms_user` WRITE;
/*!40000 ALTER TABLE `e_upms_user` DISABLE KEYS */;
INSERT INTO `e_upms_user` VALUES (1,'erupt','erupt',_binary '',_binary '','2022-11-29 03:08:36',NULL,NULL,NULL,_binary '','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,'2022-11-29 03:12:22',NULL,NULL,NULL,NULL,NULL,NULL),(2,'Jmc','Jmc',_binary '',_binary '','2022-11-29 03:09:49','2022-11-29 03:09:55',NULL,NULL,_binary '','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,'2022-11-29 03:10:46',NULL,NULL,NULL,1,1,NULL);
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
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FKes2ylim5w3ej690ss84sb956x` (`user_id`),
  CONSTRAINT `FK3h4lekfh26f5f8b7by3ejges6` FOREIGN KEY (`role_id`) REFERENCES `e_upms_role` (`id`),
  CONSTRAINT `FKes2ylim5w3ej690ss84sb956x` FOREIGN KEY (`user_id`) REFERENCES `e_upms_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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

-- Dump completed on 2023-01-03  0:39:51
