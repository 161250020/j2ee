-- MySQL dump 10.13  Distrib 5.7.24, for Win64 (x86_64)
--
-- Host: localhost    Database: j2ee_assignment
-- ------------------------------------------------------
-- Server version	5.7.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cyber_bank_info`
--

DROP TABLE IF EXISTS `cyber_bank_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cyber_bank_info` (
  `id` varchar(100) NOT NULL,
  `money` double DEFAULT NULL COMMENT '网银账户里面余额有多少；',
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模拟网银，会员和商户都有一个账户；';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cyber_bank_info`
--

LOCK TABLES `cyber_bank_info` WRITE;
/*!40000 ALTER TABLE `cyber_bank_info` DISABLE KEYS */;
INSERT INTO `cyber_bank_info` VALUES ('member1',1000,'49494949'),('member2',857,'49494949'),('rest1',1000,'49494949'),('rest2',1000,'50505050'),('rest3',1000,'49494949'),('yummy',1143,'49494949');
/*!40000 ALTER TABLE `cyber_bank_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_address_info`
--

DROP TABLE IF EXISTS `delivery_address_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery_address_info` (
  `id` varchar(100) NOT NULL,
  `member_id` varchar(100) DEFAULT NULL COMMENT '创建送餐地址会员的ID',
  `address` varchar(100) DEFAULT NULL COMMENT '送餐地址的名称，用于在页面上面显示出来；',
  `city` varchar(25) DEFAULT NULL COMMENT '送餐地址的定位的市',
  `district` varchar(25) DEFAULT NULL COMMENT '送餐地址的定位的区',
  `in_use` int(11) DEFAULT NULL COMMENT '该配送地址：\n0：不再使用；\n1：仍在使用；',
  `town` varchar(45) DEFAULT NULL COMMENT '送餐地址的定位的镇',
  `street` varchar(45) DEFAULT NULL COMMENT '送餐地址的定位的街道',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员送餐的地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_address_info`
--

LOCK TABLES `delivery_address_info` WRITE;
/*!40000 ALTER TABLE `delivery_address_info` DISABLE KEYS */;
INSERT INTO `delivery_address_info` VALUES ('1eac30c5-2764-44cd-acaa-42c9791f9ccc','0e8bb84a-d51e-420d-9502-a286f028b85d','南京玄武地址','南京','玄武',1,'锁金村街道','韶山路'),('253d0ffa-d0d1-41f9-a065-9d33852e0691','0e8bb84a-d51e-420d-9502-a286f028b85d','南京玄武玄武街道','南京','玄武',1,'玄武门街道','高楼门'),('402545a4-c161-4d76-93bb-707507af2d5a','40202048-a264-4137-9e5f-8e0ee0ba4d4a','南京大厂的地址','南京','大厂',1,'大厂','大厂'),('4ab5d57d-826b-43bc-8cb3-bd197f45acc2','40202048-a264-4137-9e5f-8e0ee0ba4d4a','南京鼓楼新街口','南京','鼓楼',1,'新街口','广州路'),('666ed518-ba16-4133-ab8f-2d086d48db7a','40202048-a264-4137-9e5f-8e0ee0ba4d4a','南京白下','南京','白下',0,'白下','白下');
/*!40000 ALTER TABLE `delivery_address_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager_info`
--

DROP TABLE IF EXISTS `manager_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager_info` (
  `username` varchar(100) NOT NULL COMMENT '经理登录时使用的用户名；',
  `password` varchar(100) DEFAULT NULL,
  `id` varchar(100) NOT NULL,
  PRIMARY KEY (`username`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经理的基本信息；';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager_info`
--

LOCK TABLES `manager_info` WRITE;
/*!40000 ALTER TABLE `manager_info` DISABLE KEYS */;
INSERT INTO `manager_info` VALUES ('manager1','49494949','1'),('manager2','49494949','2'),('manager3','49494949','3');
/*!40000 ALTER TABLE `manager_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_info`
--

DROP TABLE IF EXISTS `member_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_info` (
  `id` varchar(100) NOT NULL COMMENT '为uuid，同一mail多次注册的ID不同，为pk的；',
  `username` varchar(100) NOT NULL COMMENT '用户名，用于登录，为pk，不可更改',
  `password` varchar(100) DEFAULT NULL COMMENT '密码是使用ASCII码每位都转换一下地加密了；',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `phone_number` varchar(15) DEFAULT NULL COMMENT '电话',
  `mail` varchar(100) NOT NULL COMMENT '邮箱，每个会员的邮箱都是pk，绑定的，不可更改，每个邮箱仅可以注册一个会员的账户；\n\n注册会员账户的时候，可以先遍历会员的表格，看看是否邮箱已注册（且账户未注销），如果已注册，需要错误提示---该邮箱账户已注册！',
  `create_date` date DEFAULT NULL COMMENT '会员账户创建的日期',
  `bank_id` varchar(100) DEFAULT NULL COMMENT '该会员ID绑定的网银的ID',
  `logoff` int(11) DEFAULT NULL COMMENT '该会员账户是否已注销，\nlogoff项只有两个值：\n0（未注销---注册时初始化为这个）；\n1（已注销---后续再用此邮件的账号则可以注册）；',
  PRIMARY KEY (`id`,`username`,`mail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员的基本信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_info`
--

LOCK TABLES `member_info` WRITE;
/*!40000 ALTER TABLE `member_info` DISABLE KEYS */;
INSERT INTO `member_info` VALUES ('0e8bb84a-d51e-420d-9502-a286f028b85d','member2','505050505050','会员2','123456789','799544872@qq.com','2019-03-06','member2',0),('40202048-a264-4137-9e5f-8e0ee0ba4d4a','member1','505050505050','会员1','12345678912','799544881@qq.com','2019-03-05','member1',0);
/*!40000 ALTER TABLE `member_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_level_money_info`
--

DROP TABLE IF EXISTS `member_level_money_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_level_money_info` (
  `id` varchar(100) NOT NULL,
  `member_id` varchar(100) NOT NULL COMMENT '会员的ID',
  `level` int(11) DEFAULT NULL COMMENT '会员的级别；\n初始化为0；',
  `sum_money` double DEFAULT NULL COMMENT '会员消费的总金额（不包含退款的金额）；\n初始化为0；',
  PRIMARY KEY (`id`,`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='现实当中的会员消费的金额和相应的等级；';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_level_money_info`
--

LOCK TABLES `member_level_money_info` WRITE;
/*!40000 ALTER TABLE `member_level_money_info` DISABLE KEYS */;
INSERT INTO `member_level_money_info` VALUES ('9e68912a-1132-42bf-917f-ef04a0663e52','0e8bb84a-d51e-420d-9502-a286f028b85d',1,313),('d6425ca9-689d-4cfe-9849-310a4131316c','40202048-a264-4137-9e5f-8e0ee0ba4d4a',0,0);
/*!40000 ALTER TABLE `member_level_money_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_level_preferencialstrategies`
--

DROP TABLE IF EXISTS `member_level_preferencialstrategies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_level_preferencialstrategies` (
  `id` varchar(100) NOT NULL,
  `sum_money` double DEFAULT NULL COMMENT '会员消费到可以享受优惠，所需要达到的金额；',
  `level` int(11) NOT NULL COMMENT '会员对应的等级；',
  `ps_money` double DEFAULT NULL COMMENT '会员等级对应的每天优惠的钱数；',
  `comment` varchar(100) DEFAULT NULL COMMENT '对应的优惠的说明；',
  PRIMARY KEY (`id`,`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员消费，以达到的：会员等级和对应的优惠钱数（每天可以接收到的红包的金额）---为规定数量，不是现实当中的情况；';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_level_preferencialstrategies`
--

LOCK TABLES `member_level_preferencialstrategies` WRITE;
/*!40000 ALTER TABLE `member_level_preferencialstrategies` DISABLE KEYS */;
INSERT INTO `member_level_preferencialstrategies` VALUES ('1',100,1,1,'消费达100元，每日1元红包'),('2',500,2,2,'消费达500元，每日2元红包'),('3',1000,3,3,'消费达1000元，每日3元红包'),('4',1500,4,4,'消费达1500元，每日4元红包'),('5',2000,5,5,'消费达2000元，每日5元红包'),('6',2500,6,6,'消费达2500元，每日6元红包'),('7',3000,7,7,'消费达3000元，每日7元红包');
/*!40000 ALTER TABLE `member_level_preferencialstrategies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_order_content_info`
--

DROP TABLE IF EXISTS `member_order_content_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_order_content_info` (
  `id` varchar(100) NOT NULL,
  `order_list_id` varchar(100) DEFAULT NULL COMMENT '一次点餐产生一个order_list_id，这里表示这条记录对应的order_list_id；',
  `type` int(11) DEFAULT NULL COMMENT '0：该商品为单品；\n1：该商品为套餐；\n2：该商品为优惠商品；',
  `name` varchar(100) DEFAULT NULL COMMENT '商品的名称；',
  `num` int(11) DEFAULT NULL COMMENT '商品的数量；',
  `price` double DEFAULT NULL COMMENT '该条记录的产生商品的总价格；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员点餐的订单的商品的内容，一次点餐可以有多个商品（此表产生多个信息记录），一个order_list_id，order_list_id相同则为同一次点餐；';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_order_content_info`
--

LOCK TABLES `member_order_content_info` WRITE;
/*!40000 ALTER TABLE `member_order_content_info` DISABLE KEYS */;
INSERT INTO `member_order_content_info` VALUES ('0cd29012-9c6e-42ff-88f8-511435e6b4fb','5c7b8189-08d6-4f24-b0c4-2f776965c113',1,'套餐4',1,12.5),('0ee9845b-8627-472d-93b2-015cfd336e73','1285e8f4-b808-4414-a9c7-3476ffadcc15',0,'单品3',1,15.5),('10717bb4-cd44-438c-83c9-ce47556049b0','dc291c77-06eb-4a29-aa8d-c487b95c04a3',0,'单品2',3,30),('210bba6e-7c4e-4bdb-aafc-e6cd94cba5ac','5c7b8189-08d6-4f24-b0c4-2f776965c113',0,'单品3',1,15.5),('276e6771-7e6f-46c1-803e-c16caacb7ec9','18cc16d5-56a0-4ce6-ad5d-49968b703440',1,'套餐4',3,37.5),('371ea88e-7760-46d5-92ac-520ae09fa761','4b6215f2-73dc-4b83-885d-f7bc183dc95a',0,'头孢',2,80),('3ac43d2c-87dd-4822-8ff0-fbd4db0f712d','f9032af4-a9f2-43b9-bcda-8c7225217d9f',0,'单品2',1,10),('438c3221-f1be-4709-87db-c3e644dc65a3','3cc1d027-fdc4-44a3-b5e0-96f38bdcb109',0,'头孢',1,40),('582a703c-5d7f-4ee9-b92d-ed3cfb4aeff2','6eb6b6ad-1a25-4569-80bb-330ee351727f',0,'止咳',1,40),('5db5a753-ebc4-4e7e-9a51-dcfee5f62b16','38e312df-52b4-4976-b1d9-03050ab19dba',0,'止咳',1,40),('65a1ae7e-32eb-40d7-b5e9-4cd629605be4','1a34ae2f-b053-4153-861a-5ed498a8dd37',0,'单品3',1,15.5),('66a3434b-1ffa-42c6-84e6-0203dfeb339e','5c7b8189-08d6-4f24-b0c4-2f776965c113',0,'单品2',1,10),('74398b2e-911a-458c-8a42-964fa9f921b7','f9032af4-a9f2-43b9-bcda-8c7225217d9f',0,'单品3',1,15.5),('8366e6dd-7ad1-41a7-9e3f-d44f0259d629','651e1597-74f2-4961-8b9e-562b7c01980c',0,'单品3',1,15.5),('8f81a325-927e-4485-bfbd-756d440cc76c','7e226866-b24d-47c3-8b52-23d7a05d3ad5',0,'单品3',1,15.5),('aac53a1c-6ebe-490f-9b95-80ad890c8989','4b6215f2-73dc-4b83-885d-f7bc183dc95a',1,'感冒套餐',1,50),('b1ce62a1-fd44-4ad7-988b-5acead324cfd','18cc16d5-56a0-4ce6-ad5d-49968b703440',2,'优惠2',2,29),('eb6dcb42-ac4e-42ad-b2d9-a1612857ce38','7e226866-b24d-47c3-8b52-23d7a05d3ad5',1,'套餐4',1,12.5);
/*!40000 ALTER TABLE `member_order_content_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_order_info`
--

DROP TABLE IF EXISTS `member_order_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_order_info` (
  `id` varchar(100) NOT NULL,
  `order_list_id` varchar(100) NOT NULL COMMENT '一次会员点单只有一个order_list_id；',
  `order_time` datetime DEFAULT NULL COMMENT '会员下订单的时间；',
  `delivery_time_defined` varchar(100) DEFAULT NULL COMMENT '会员下订单要求送达的时间；（分钟）\n默认：\n不同市：120min；\n不同区：60min；\n不同镇：40min；\n不同街道：30min；\n都一样：20min；',
  `delivery_time_received` datetime DEFAULT NULL COMMENT '会员订单确认送达/默认收货的时间；',
  `member_id` varchar(100) DEFAULT NULL COMMENT '会员的ID；',
  `restaurant_id` varchar(100) DEFAULT NULL COMMENT '餐厅的ID；\n7位ID；',
  `sum_price` double DEFAULT NULL COMMENT '该次订单的总价；\n使用完优惠券的总价；',
  `result` int(11) DEFAULT NULL COMMENT '该订单的结果；\n0：退订；\n1：未在规定时间支付；\n2：订单成功；\n//3：配送失败；---暂时未考虑\n4：余额不足；\n初始化为1；',
  `state` int(11) DEFAULT NULL COMMENT '订单的状态：\n0：订单下达成功；\n1：已生产；\n2：已配送；\n3：已送达；',
  `delivery_address_id` varchar(100) DEFAULT NULL COMMENT '配送地点的address；',
  `ps_money_yummy` double DEFAULT NULL COMMENT '使用yummy公司的优惠券优惠的金额；',
  `account` int(11) DEFAULT NULL COMMENT '是否被结算：\n已结算：1；\n未结算：0；\n\n初始化为：0；',
  `delivery_time_os` varchar(45) DEFAULT NULL COMMENT '系统下订单要求送达的时间；（分钟）\n默认：\n不同市：120min；\n不同区：60min；\n不同镇：40min；\n不同街道：30min；\n都一样：20min；',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`,`order_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员点餐的订单的基本信息，例如何时点餐等等；';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_order_info`
--

LOCK TABLES `member_order_info` WRITE;
/*!40000 ALTER TABLE `member_order_info` DISABLE KEYS */;
INSERT INTO `member_order_info` VALUES ('0e92ebd5-d5ed-4ba8-8e18-7d686d593a0d','4b6215f2-73dc-4b83-885d-f7bc183dc95a','2019-03-06 10:43:50','50',NULL,'0e8bb84a-d51e-420d-9502-a286f028b85d','pBIeVHQ',130,0,3,'253d0ffa-d0d1-41f9-a065-9d33852e0691',0,0,'40','2019-03-06 10:43:57'),('1d152896-c013-456a-943c-2ba21c0e5115','6eb6b6ad-1a25-4569-80bb-330ee351727f','2019-03-12 00:02:01','50','2019-03-12 00:03:40','0e8bb84a-d51e-420d-9502-a286f028b85d','9999999',39,2,3,'1eac30c5-2764-44cd-acaa-42c9791f9ccc',1,0,'120','2019-03-12 00:02:07'),('3b4c6d72-14c3-41da-84da-535cee25c783','f9032af4-a9f2-43b9-bcda-8c7225217d9f','2019-03-06 19:57:33','60',NULL,'40202048-a264-4137-9e5f-8e0ee0ba4d4a','SiLDYh2',25.5,1,0,'402545a4-c161-4d76-93bb-707507af2d5a',0,0,'60',NULL),('42964815-4723-4da8-860a-c943e9a9e440','18cc16d5-56a0-4ce6-ad5d-49968b703440','2019-03-11 23:27:03','60',NULL,'0e8bb84a-d51e-420d-9502-a286f028b85d','SiLDYh2',65.5,1,0,'1eac30c5-2764-44cd-acaa-42c9791f9ccc',1,0,'60',NULL),('54a6e61f-05d9-47bb-90f3-7b09d033985a','5c7b8189-08d6-4f24-b0c4-2f776965c113','2019-03-11 23:47:41','60','2019-03-12 00:03:49','0e8bb84a-d51e-420d-9502-a286f028b85d','SiLDYh2',37,2,3,'1eac30c5-2764-44cd-acaa-42c9791f9ccc',1,0,'60','2019-03-11 23:47:52'),('5c382b82-9dde-4a95-aa83-b25f5f8d8303','38e312df-52b4-4976-b1d9-03050ab19dba','2019-03-12 00:02:23','120',NULL,'0e8bb84a-d51e-420d-9502-a286f028b85d','9999999',40,2,0,'253d0ffa-d0d1-41f9-a065-9d33852e0691',0,0,'120','2019-03-12 00:02:29'),('7be955d7-fca0-4299-acec-220f970b6f64','651e1597-74f2-4961-8b9e-562b7c01980c','2019-03-11 23:57:00','60',NULL,'0e8bb84a-d51e-420d-9502-a286f028b85d','SiLDYh2',15.5,1,0,'1eac30c5-2764-44cd-acaa-42c9791f9ccc',0,0,'60',NULL),('8059ab22-f2ba-4db4-a81f-bf0bd3757b76','1a34ae2f-b053-4153-861a-5ed498a8dd37','2019-03-11 10:00:01','60',NULL,'0e8bb84a-d51e-420d-9502-a286f028b85d','SiLDYh2',14.5,1,0,'1eac30c5-2764-44cd-acaa-42c9791f9ccc',1,0,'60',NULL),('8cb33556-204d-46d2-a27b-3ce71c69c606','7e226866-b24d-47c3-8b52-23d7a05d3ad5','2019-03-11 23:56:04','60',NULL,'0e8bb84a-d51e-420d-9502-a286f028b85d','SiLDYh2',27,2,0,'1eac30c5-2764-44cd-acaa-42c9791f9ccc',1,0,'60','2019-03-11 23:56:41'),('a2529102-f0f3-45c8-9bd1-4bc7123f089d','1285e8f4-b808-4414-a9c7-3476ffadcc15','2019-03-06 20:13:14','60',NULL,'40202048-a264-4137-9e5f-8e0ee0ba4d4a','SiLDYh2',15.5,1,0,'402545a4-c161-4d76-93bb-707507af2d5a',0,0,'60',NULL),('e4abccbe-2f5c-491a-8f63-cd7452f7fad9','3cc1d027-fdc4-44a3-b5e0-96f38bdcb109','2019-03-06 10:54:19','50','2019-03-06 10:56:36','0e8bb84a-d51e-420d-9502-a286f028b85d','pBIeVHQ',40,2,3,'1eac30c5-2764-44cd-acaa-42c9791f9ccc',0,1,'20','2019-03-06 10:54:23'),('fb3911cf-108c-45cf-a7f3-fd0ee0c1cf35','dc291c77-06eb-4a29-aa8d-c487b95c04a3','2019-03-06 10:49:19','60',NULL,'0e8bb84a-d51e-420d-9502-a286f028b85d','SiLDYh2',30,1,0,'253d0ffa-d0d1-41f9-a065-9d33852e0691',0,0,'60',NULL);
/*!40000 ALTER TABLE `member_order_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_ps_receive_info`
--

DROP TABLE IF EXISTS `member_ps_receive_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_ps_receive_info` (
  `id` varchar(100) NOT NULL,
  `member_id` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL COMMENT '领取会员优惠红包的日期；',
  `ps_money` double DEFAULT NULL COMMENT '优惠的红包的金额数量；',
  `result` int(11) DEFAULT NULL COMMENT '红包的结果：\n0：未使用；\n1：已使用；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员当天的红包优惠领取的情况，存在一条数据则代表该数据的会员在数据中的日期当天领取了多少钱的优惠；';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_ps_receive_info`
--

LOCK TABLES `member_ps_receive_info` WRITE;
/*!40000 ALTER TABLE `member_ps_receive_info` DISABLE KEYS */;
INSERT INTO `member_ps_receive_info` VALUES ('4172a7d2-0e9d-4b9b-834c-5f00e7fd16ad','0e8bb84a-d51e-420d-9502-a286f028b85d','2019-03-12',1,1),('75e7b572-0948-4d23-94cd-558caf8f5cb8','0e8bb84a-d51e-420d-9502-a286f028b85d','2019-03-06',0,1),('9608d1d9-d88f-46a6-942d-75a0c8bf7b19','40202048-a264-4137-9e5f-8e0ee0ba4d4a','2019-03-06',0,0),('dab1d4de-a4c4-454d-b278-c9f9de8d2456','0e8bb84a-d51e-420d-9502-a286f028b85d','2019-03-11',1,1);
/*!40000 ALTER TABLE `member_ps_receive_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_info`
--

DROP TABLE IF EXISTS `restaurant_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_info` (
  `id` varchar(100) NOT NULL,
  `login_id` varchar(45) NOT NULL COMMENT '7位编码，用于登录；',
  `password` varchar(100) DEFAULT NULL,
  `mail` varchar(100) NOT NULL COMMENT '注册餐厅的邮箱；',
  `name` varchar(100) DEFAULT NULL COMMENT '餐厅的名称；',
  `date` date DEFAULT NULL COMMENT '注册餐厅的日期；',
  `address` varchar(100) DEFAULT NULL COMMENT '餐厅的地址的名称，用于显示在网页上面；',
  `type_id` int(11) DEFAULT NULL COMMENT '餐厅的经营类型的ID；',
  `bank_id` varchar(100) DEFAULT NULL COMMENT '绑定的网银的ID；',
  `picture` varchar(100) DEFAULT NULL COMMENT '商店显示的图片的相对路径；',
  `city` varchar(25) DEFAULT NULL COMMENT '送餐地址的定位的市',
  `district` varchar(25) DEFAULT NULL COMMENT '送餐地址的定位的区',
  `town` varchar(45) DEFAULT NULL COMMENT '送餐地址的定位的镇',
  `street` varchar(45) DEFAULT NULL COMMENT '送餐地址的定位的街道',
  PRIMARY KEY (`id`,`login_id`,`mail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='餐厅的基本信息；';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_info`
--

LOCK TABLES `restaurant_info` WRITE;
/*!40000 ALTER TABLE `restaurant_info` DISABLE KEYS */;
INSERT INTO `restaurant_info` VALUES ('1','SiLDYh2','50505050','799544881@qq.com','美食餐厅','2019-03-05','南京鼓楼新街口',1,'rest1',NULL,'南京','鼓楼','新街口','新街口'),('10','9999999','50505050','099544881@qq.com','餐厅9999','2019-03-05','北京',10,'rest1',NULL,'北京','d9','t9','s9'),('11','0000000','50505050','1199544881@qq.com','餐厅1010','2019-03-05','上海',1,'rest2',NULL,'上海','d10','t10','s10'),('12','1212121','50505050','1799544881@qq.com','餐厅0101','2019-03-05','上海',2,'rest2',NULL,'上海','d11','t11','s11'),('13','2121212','50505050','11799544881@qq.com','餐厅1212','2019-03-05','广州',3,'rest2',NULL,'广州','d12','t12','s12'),('14','1313131','50505050','111799544881@qq.com','餐厅2121','2019-03-05','深圳',4,'rest2',NULL,'深圳','d13','t13','s13'),('15','3131313','50505050','22799544881@qq.com','餐厅3131','2019-03-05','深圳',5,'rest1',NULL,'深圳','d13','t13','s13'),('2','1111111','50505050','199544881@qq.com','餐厅111','2019-03-05','北京',2,'rest1',NULL,'北京','d1','t1','s1'),('3','2222222','50505050','299544881@qq.com','餐厅0000','2019-03-05','上海',3,'rest1',NULL,'上海','d2','t2','s2'),('4','3333333','50505050','399544881@qq.com','餐厅3333','2019-03-05','广州',4,'rest1',NULL,'广州','d3','t3','s3'),('5','4444444','50505050','499544881@qq.com','餐厅4444','2019-03-05','深圳',5,'rest2',NULL,'深圳','d4','t4','s4'),('6','5555555','50505050','599544881@qq.com','餐厅5555','2019-03-05','南京',6,'rest2',NULL,'南京','d5','t5','s5'),('7','6666666','50505050','699544881@qq.com','餐厅6666','2019-03-05','南京',7,'rest2',NULL,'南京','d6','t6','s6'),('8','7777777','50505050','899544881@qq.com','餐厅7777','2019-03-05','南京',8,'rest1',NULL,'南京','d7','t7','s7'),('9','8888888','50505050','999544881@qq.com','餐厅8888','2019-03-05','北京',9,'rest1',NULL,'北京','d8','t8','s8'),('98cc6cb2-6db4-46cb-bb8d-5ec3dc50e922','pBIeVHQ','5050505050','799544782@qq.com','医药店','2019-03-06','南京玄武',10,'rest3',NULL,'南京','玄武','锁金村街道','韶山路');
/*!40000 ALTER TABLE `restaurant_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_modify_application_info`
--

DROP TABLE IF EXISTS `restaurant_modify_application_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_modify_application_info` (
  `id` varchar(100) NOT NULL,
  `new_name` varchar(100) DEFAULT NULL COMMENT '餐厅的新名称；',
  `new_address` varchar(100) DEFAULT NULL COMMENT '餐厅的新地址；',
  `new_type_id` int(11) DEFAULT NULL COMMENT '餐厅的新售卖商品的类型的ID；',
  `new_city` varchar(25) DEFAULT NULL COMMENT '餐厅新地址的地点的市',
  `new_district` varchar(25) DEFAULT NULL COMMENT '餐厅新地址的地点的区',
  `restaurant_id` varchar(100) DEFAULT NULL COMMENT '申请的餐厅的ID；\n7位编码；',
  `result` int(11) DEFAULT NULL COMMENT '审批结果：\n0：未审批；\n1：审批通过失败；\n2：审批通过成功；\n\n初始化为：0；',
  `new_town` varchar(45) DEFAULT NULL COMMENT '餐厅新地址的地点的镇',
  `new_street` varchar(45) DEFAULT NULL COMMENT '餐厅新地址的地点的街道',
  `manager_id` varchar(100) DEFAULT NULL COMMENT '审批的经理的ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='餐厅申请---信息修改，的记录的信息；';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_modify_application_info`
--

LOCK TABLES `restaurant_modify_application_info` WRITE;
/*!40000 ALTER TABLE `restaurant_modify_application_info` DISABLE KEYS */;
INSERT INTO `restaurant_modify_application_info` VALUES ('1b0ad156-9006-4890-b93e-8fab07b1abef','美食餐厅','南京鼓楼新街口',1,'南京','鼓楼','SiLDYh2',1,'新街口','新街口',NULL),('4c351049-0b55-4153-b018-33c841d2b4a1','医药店','南京玄武',10,'南京','玄武','pBIeVHQ',2,'锁金村街道','韶山路','2'),('f26f9d0f-49da-4d33-b4dd-8fd11a4406d0','美食餐厅','南京鼓楼新街口',1,'南京','鼓楼','SiLDYh2',2,'新街口','新街口','1');
/*!40000 ALTER TABLE `restaurant_modify_application_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_preferencial_info`
--

DROP TABLE IF EXISTS `restaurant_preferencial_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_preferencial_info` (
  `id` varchar(100) NOT NULL,
  `restaurant_id` varchar(100) DEFAULT NULL COMMENT '为7位的数值；',
  `com_name` varchar(100) DEFAULT NULL,
  `raw_price` double DEFAULT NULL COMMENT '原价；',
  `now_price` double DEFAULT NULL COMMENT '现价；',
  `num` int(11) DEFAULT NULL COMMENT '数量；',
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='餐厅发布优惠的信息（可为未来一个时间段）；\n参考餐厅发布的单品的信息；';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_preferencial_info`
--

LOCK TABLES `restaurant_preferencial_info` WRITE;
/*!40000 ALTER TABLE `restaurant_preferencial_info` DISABLE KEYS */;
INSERT INTO `restaurant_preferencial_info` VALUES ('0e35d8ec-2394-4faf-9349-d167afa4e138','pBIeVHQ','感冒套餐优惠',50,40,100,'2019-03-07','2019-03-23'),('2b82fc89-9939-4f04-aa6d-1b81f2bf3d95','SiLDYh2','优惠2',20,14.5,144,'2018-01-09','2019-08-08'),('3e018454-ca65-4ce7-aa8e-fcf7e15cf276','SiLDYh2','优惠5',18,16,30,'2019-02-03','2019-04-05'),('5aef9e7b-1372-4fb0-aada-e6390b5d393e','SiLDYh2','优惠1',15,12,120,'2018-01-06','2018-01-07'),('81f20f92-c70d-4014-9370-284c38919b5d','SiLDYh2','优惠4',18,16,30,'2019-02-03','2019-04-05');
/*!40000 ALTER TABLE `restaurant_preferencial_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_publish_application_info`
--

DROP TABLE IF EXISTS `restaurant_publish_application_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_publish_application_info` (
  `id` varchar(100) NOT NULL,
  `restaurant_id` varchar(100) DEFAULT NULL,
  `com_type` int(11) DEFAULT NULL COMMENT '1：单品；\n2：套餐；\n3：优惠；',
  `name` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `result` int(11) DEFAULT NULL COMMENT '审批结果：\n0：未审批；\n1：审批通过失败；\n2：审批通过成功；',
  `manager_id` varchar(100) DEFAULT NULL,
  `raw_price` double DEFAULT NULL COMMENT '仅当type为3的时候，会有数据，其余时候为null；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='餐厅发布商品的审批';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_publish_application_info`
--

LOCK TABLES `restaurant_publish_application_info` WRITE;
/*!40000 ALTER TABLE `restaurant_publish_application_info` DISABLE KEYS */;
INSERT INTO `restaurant_publish_application_info` VALUES ('874a2437-139d-4399-82ba-50ee387acbde','SiLDYh2',2,'套餐4',12.5,20,'2019-01-05','2019-03-13',2,'2',0),('b9a851d1-5bda-451e-938a-e2a49755d009','SiLDYh2',1,'单品4',12.5,20,'2019-05-04','2019-02-05',1,'2',0),('cf885bdb-80cd-4dcf-878b-e96868801795','SiLDYh2',3,'优惠5',16,30,'2019-02-03','2019-04-05',2,'2',18),('f5fb22c5-b8b2-4e8a-8731-193ff6e70e71','9999999',1,'止咳',40,100,'2019-03-08','2019-03-15',2,'3',0);
/*!40000 ALTER TABLE `restaurant_publish_application_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_register_application_info`
--

DROP TABLE IF EXISTS `restaurant_register_application_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_register_application_info` (
  `id` varchar(100) NOT NULL,
  `login_id` varchar(45) DEFAULT NULL COMMENT '在restaurant_info的表格当中，这个login_id需要是不存在的；存储为餐厅的7位ID；',
  `password` varchar(100) DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL COMMENT '在restaurant_info的表格当中，这个mail需要是不存在的；',
  `name` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `city` varchar(25) DEFAULT NULL,
  `district` varchar(25) DEFAULT NULL,
  `result` int(11) DEFAULT NULL COMMENT '审批结果：\n0：未审批；\n1：审批通过失败；\n2：审批通过成功；',
  `town` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `bank_id` varchar(100) DEFAULT NULL,
  `manager_id` varchar(100) DEFAULT NULL COMMENT '审批的经理的ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='餐厅申请---注册，的记录的信息；参考restaurat_info的表格里面的内容；\n审批结束之后，需要重写表格当中的记录的结果；';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_register_application_info`
--

LOCK TABLES `restaurant_register_application_info` WRITE;
/*!40000 ALTER TABLE `restaurant_register_application_info` DISABLE KEYS */;
INSERT INTO `restaurant_register_application_info` VALUES ('6660e444-a73a-40be-9b90-58a37930e2f4','pBIeVHQ','50505050','799544882@qq.com','药品店','2019-03-06','南京玄武',10,'南京','玄武',2,'锁金村街道','韶山路','rest3','2'),('af7d4b74-0825-4354-ba18-17d17edee25c','SiLDYh2','50505050','799544882@qq.com','餐厅2222','2019-03-05','南京鼓楼新街口',2,'南京','鼓楼',2,'新街口','新街口','rest1','1');
/*!40000 ALTER TABLE `restaurant_register_application_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_setmeal_info`
--

DROP TABLE IF EXISTS `restaurant_setmeal_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_setmeal_info` (
  `id` varchar(100) NOT NULL,
  `restaurant_id` varchar(100) DEFAULT NULL,
  `set_meal_name` varchar(100) DEFAULT NULL,
  `setmeal_price` double DEFAULT NULL,
  `setmeal_num` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='餐厅发布套餐的信息（可为未来一个时间段）；\n参考餐厅发布的单品的信息；';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_setmeal_info`
--

LOCK TABLES `restaurant_setmeal_info` WRITE;
/*!40000 ALTER TABLE `restaurant_setmeal_info` DISABLE KEYS */;
INSERT INTO `restaurant_setmeal_info` VALUES ('3178cd17-9b6c-405b-bfd1-c72aa2bcb5fd','SiLDYh2','套餐4',12.5,18,'2019-01-05','2019-03-13'),('4ecdd7bc-cadd-4255-bab9-54e104fe03ff','SiLDYh2','套餐2',22,200,'2018-06-03','2018-06-04'),('61776b0c-b1eb-4c4f-8fce-9ccd9141f539','SiLDYh2','套餐3',12.5,92,'2018-09-09','2019-06-06'),('83f9bdc3-27d1-4635-89dc-d222bfeb3d4b','pBIeVHQ','感冒套餐',50,49,'2019-03-06','2019-03-16'),('963c2880-c6ec-4fa7-9ceb-43619755a97f','SiLDYh2','套餐1',2.5,96,'2018-01-09','2019-05-05');
/*!40000 ALTER TABLE `restaurant_setmeal_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_singleproduct_info`
--

DROP TABLE IF EXISTS `restaurant_singleproduct_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_singleproduct_info` (
  `id` varchar(100) NOT NULL,
  `restaurant_id` varchar(100) DEFAULT NULL COMMENT '发布单品的餐厅的ID；',
  `commodity_name` varchar(100) DEFAULT NULL COMMENT '单品的名称；',
  `com_price` double DEFAULT NULL COMMENT '单品的单价；',
  `com_num` int(11) DEFAULT NULL COMMENT '单品的数量；',
  `start_date` date DEFAULT NULL COMMENT '单品发布的开始日期；',
  `end_date` date DEFAULT NULL COMMENT '单品发布的结束日期；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='餐厅发布单品的信息（可为未来的一个时间段）；';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_singleproduct_info`
--

LOCK TABLES `restaurant_singleproduct_info` WRITE;
/*!40000 ALTER TABLE `restaurant_singleproduct_info` DISABLE KEYS */;
INSERT INTO `restaurant_singleproduct_info` VALUES ('00eeabe9-cf57-4ac7-a503-d343d5b9e147','pBIeVHQ','头孢',40,37,'2019-03-06','2019-03-15'),('725df825-1330-4137-a09f-0d6e285f4226','SiLDYh2','单品3',15.5,62,'2018-09-01','2019-09-09'),('916231aa-2c78-40e1-806a-7ac9d8f6b614','SiLDYh2','单品1',10,100,'2018-01-01','2019-02-02'),('a0095055-a41d-41c4-8327-c4782bbc812f','SiLDYh2','单品2',10,92,'2018-01-01','2019-04-04'),('bd2fe6ff-cd4e-4d3c-b952-57d8203de34a','9999999','止咳',40,98,'2019-03-08','2019-03-15');
/*!40000 ALTER TABLE `restaurant_singleproduct_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_types`
--

DROP TABLE IF EXISTS `restaurant_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_types` (
  `id` int(11) NOT NULL,
  `type_name` varchar(100) DEFAULT NULL COMMENT '餐厅类型的名称；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='餐厅的类型和对应的ID，可以参考饿了么对于餐厅注册的经营商品类型的分类；';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_types`
--

LOCK TABLES `restaurant_types` WRITE;
/*!40000 ALTER TABLE `restaurant_types` DISABLE KEYS */;
INSERT INTO `restaurant_types` VALUES (1,'美食'),(2,'快餐便当'),(3,'特色菜系'),(4,'异国料理'),(5,'小吃夜宵'),(6,'甜点饮品'),(7,'果蔬生鲜'),(8,'商店超市'),(9,'鲜花绿植'),(10,'医药健康');
/*!40000 ALTER TABLE `restaurant_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yummy_settle_accounts_info`
--

DROP TABLE IF EXISTS `yummy_settle_accounts_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yummy_settle_accounts_info` (
  `id` varchar(100) NOT NULL,
  `account_id` varchar(100) NOT NULL COMMENT '公司结算的ID；',
  `total_in_money` double DEFAULT NULL COMMENT '总收入的金额；',
  `total_out_money` double DEFAULT NULL COMMENT '总支出的金额；---用作ps（优惠）的；',
  `rest_money` double DEFAULT NULL COMMENT '余额；',
  `manager_id` varchar(100) DEFAULT NULL COMMENT '结算的经理的ID；',
  `account_time` datetime DEFAULT NULL COMMENT '此次结算时间；',
  PRIMARY KEY (`id`,`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='yummy公司结算的总账单，每次结算一次的时候会生成一条结算的记录，每次结算都有一条account_id；\n无需结算已退款的订单---退款的时候，就已经给了member和餐厅了；';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yummy_settle_accounts_info`
--

LOCK TABLES `yummy_settle_accounts_info` WRITE;
/*!40000 ALTER TABLE `yummy_settle_accounts_info` DISABLE KEYS */;
INSERT INTO `yummy_settle_accounts_info` VALUES ('65fb787d-e5de-4db9-9506-e2f8be2c7a05','a37f3bb7-1036-412d-aa30-682374c6cad9',40,0,20,'2','2019-03-06 10:58:37');
/*!40000 ALTER TABLE `yummy_settle_accounts_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-12  0:36:54
