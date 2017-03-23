-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: dms
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `committee`
--

DROP TABLE IF EXISTS `committee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `committee` (
  `committeememberid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `societyid` int(11) DEFAULT NULL,
  `societyname` varchar(45) DEFAULT NULL,
  `positionid` int(11) DEFAULT NULL,
  `positionname` varchar(45) DEFAULT NULL,
  `isactive` int(11) DEFAULT '1',
  `appointedon` datetime DEFAULT CURRENT_TIMESTAMP,
  `removedon` datetime DEFAULT NULL,
  `contactNo` varchar(45) DEFAULT NULL,
  `flat` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`committeememberid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `committee`
--

LOCK TABLES `committee` WRITE;
/*!40000 ALTER TABLE `committee` DISABLE KEYS */;
INSERT INTO `committee` VALUES (1,2,NULL,1,NULL,1,NULL,1,'2017-03-13 00:00:00',NULL,NULL,NULL),(2,8,NULL,1,NULL,1,NULL,0,'2017-03-23 00:00:00','2017-03-23 08:39:20',NULL,NULL);
/*!40000 ALTER TABLE `committee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `committeemaster`
--

DROP TABLE IF EXISTS `committeemaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `committeemaster` (
  `positionid` int(11) NOT NULL AUTO_INCREMENT,
  `positionname` varchar(45) DEFAULT NULL,
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  `isactive` int(11) DEFAULT '1',
  PRIMARY KEY (`positionid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `committeemaster`
--

LOCK TABLES `committeemaster` WRITE;
/*!40000 ALTER TABLE `committeemaster` DISABLE KEYS */;
INSERT INTO `committeemaster` VALUES (1,'Secretary','2017-03-13 20:10:20',1),(2,'Chairman','2017-03-13 20:10:20',1),(3,'Treasurer','2017-03-13 20:10:20',1),(4,'Member','2017-03-13 20:10:20',1);
/*!40000 ALTER TABLE `committeemaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `configid` int(11) NOT NULL AUTO_INCREMENT,
  `configkey` varchar(45) DEFAULT NULL,
  `configvalue` varchar(45) DEFAULT NULL,
  `isactive` int(11) DEFAULT '1',
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`configid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'maxseqno','20',1,'2017-03-13 20:20:52');
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docsubtype`
--

DROP TABLE IF EXISTS `docsubtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docsubtype` (
  `docsubtypeid` int(11) NOT NULL AUTO_INCREMENT,
  `doctypeid` int(11) DEFAULT NULL,
  `docsubtypename` varchar(45) DEFAULT NULL,
  `docsubtypedesc` varchar(45) DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  `active` int(11) DEFAULT NULL,
  PRIMARY KEY (`docsubtypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docsubtype`
--

LOCK TABLES `docsubtype` WRITE;
/*!40000 ALTER TABLE `docsubtype` DISABLE KEYS */;
INSERT INTO `docsubtype` VALUES (8,5,'Flat Sale Deed','Flat Sale Deed','8','2017-03-23 10:16:06',1);
/*!40000 ALTER TABLE `docsubtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctype`
--

DROP TABLE IF EXISTS `doctype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctype` (
  `doctypeid` int(11) NOT NULL AUTO_INCREMENT,
  `doctypename` varchar(45) DEFAULT NULL,
  `doctypedesc` varchar(45) DEFAULT NULL,
  `active` int(11) DEFAULT '1',
  `createdby` varchar(45) DEFAULT NULL,
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`doctypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctype`
--

LOCK TABLES `doctype` WRITE;
/*!40000 ALTER TABLE `doctype` DISABLE KEYS */;
INSERT INTO `doctype` VALUES (5,'Agreement  Legal Documents','Agreement  Legal Documents',1,'8','2017-03-23 10:15:16');
/*!40000 ALTER TABLE `doctype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `documentid` int(11) NOT NULL AUTO_INCREMENT,
  `societyid` int(11) DEFAULT NULL,
  `doctypeid` int(11) DEFAULT NULL,
  `docsubtypeid` int(11) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`documentid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES (2,1,1,1,NULL,'123','2017-03-18 12:28:57'),(3,1,1,1,NULL,'123','2017-03-18 12:43:19'),(4,1,1,1,NULL,'123','2017-03-18 13:26:13'),(5,1,1,1,NULL,'123','2017-03-18 13:29:45'),(6,1,1,1,NULL,'123','2017-03-18 13:30:28'),(7,1,2,2,NULL,'123','2017-03-18 13:38:26'),(8,1,5,8,NULL,'123','2017-03-23 10:17:55');
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documentdetails`
--

DROP TABLE IF EXISTS `documentdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documentdetails` (
  `documentdetailsid` int(11) NOT NULL AUTO_INCREMENT,
  `documentid` int(11) DEFAULT NULL,
  `datakey` varchar(45) DEFAULT NULL,
  `datavalue` varchar(45) DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`documentdetailsid`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentdetails`
--

LOCK TABLES `documentdetails` WRITE;
/*!40000 ALTER TABLE `documentdetails` DISABLE KEYS */;
INSERT INTO `documentdetails` VALUES (1,2,'societyid','1','123','2017-03-18 12:28:57'),(2,2,'doctypeid','1','123','2017-03-18 12:28:57'),(3,2,'docsubtypeid','1','123','2017-03-18 12:28:57'),(4,2,'1','hello','123','2017-03-18 12:28:57'),(5,2,'2','03/18/2017','123','2017-03-18 12:28:57'),(6,3,'societyid','1','123','2017-03-18 12:43:19'),(7,3,'doctypeid','1','123','2017-03-18 12:43:19'),(8,3,'docsubtypeid','1','123','2017-03-18 12:43:19'),(9,3,'1','hello','123','2017-03-18 12:43:19'),(10,3,'2','03/18/2017','123','2017-03-18 12:43:19'),(11,4,'societyid','1','123','2017-03-18 13:26:13'),(12,4,'doctypeid','1','123','2017-03-18 13:26:13'),(13,4,'docsubtypeid','1','123','2017-03-18 13:26:13'),(14,4,'1','hello','123','2017-03-18 13:26:13'),(15,4,'2','03/18/2017','123','2017-03-18 13:26:13'),(16,5,'societyid','1','123','2017-03-18 13:29:45'),(17,5,'doctypeid','1','123','2017-03-18 13:29:45'),(18,5,'docsubtypeid','1','123','2017-03-18 13:29:45'),(19,5,'1','hello','123','2017-03-18 13:29:45'),(20,5,'2','03/18/2017','123','2017-03-18 13:29:45'),(21,6,'societyid','1','123','2017-03-18 13:30:28'),(22,6,'doctypeid','1','123','2017-03-18 13:30:28'),(23,6,'docsubtypeid','1','123','2017-03-18 13:30:28'),(24,6,'1','hello','123','2017-03-18 13:30:28'),(25,6,'2','03/18/2017','123','2017-03-18 13:30:28'),(26,7,'societyid','1','123','2017-03-18 13:38:26'),(27,7,'doctypeid','2','123','2017-03-18 13:38:26'),(28,7,'docsubtypeid','2','123','2017-03-18 13:38:26'),(29,7,'3','Pranav','123','2017-03-18 13:38:26'),(30,8,'societyid','1','123','2017-03-23 10:17:55'),(31,8,'doctypeid','5','123','2017-03-23 10:17:55'),(32,8,'docsubtypeid','8','123','2017-03-23 10:17:55'),(33,8,'16','A and B','123','2017-03-23 10:17:55'),(34,8,'17','Pranav VG','123','2017-03-23 10:17:55');
/*!40000 ALTER TABLE `documentdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `filesid` int(11) NOT NULL AUTO_INCREMENT,
  `societyid` int(11) DEFAULT NULL,
  `doctypeid` int(11) DEFAULT NULL,
  `docsubtypeid` int(11) DEFAULT NULL,
  `documentid` int(11) DEFAULT NULL,
  `filename` varchar(45) DEFAULT NULL,
  `filepath` varchar(45) DEFAULT NULL,
  `mimetype` varchar(45) DEFAULT NULL,
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  `createdby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`filesid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
INSERT INTO `files` VALUES (1,1,1,1,6,'1-1-1-1489824078320.jpg','/DMS/1-1-1-1489824078320.jpg','','2017-03-18 13:31:18','1'),(2,1,1,1,6,'1-1-1-1489824078451.jpg','/DMS/1-1-1-1489824078451.jpg','','2017-03-18 13:31:18','1'),(3,1,1,1,6,'1-1-1-1489824078669.jpg','/DMS/1-1-1-1489824078669.jpg','','2017-03-18 13:31:18','1'),(4,1,2,2,7,'1-2-2-1489824517010.jpg','/DMS/1-2-2-1489824517010.jpg','','2017-03-18 13:38:37','1'),(5,1,5,8,8,'1-5-8-1490244518201.jpg','/DMS/1-5-8-1490244518201.jpg','','2017-03-23 10:18:38','1');
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formstructure`
--

DROP TABLE IF EXISTS `formstructure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formstructure` (
  `fieldid` int(11) NOT NULL AUTO_INCREMENT,
  `fieldname` varchar(45) DEFAULT NULL,
  `fieldtype` varchar(45) DEFAULT NULL,
  `datatype` varchar(45) DEFAULT NULL,
  `sequence` varchar(45) DEFAULT NULL,
  `active` int(11) DEFAULT '1',
  `createdby` varchar(45) DEFAULT NULL,
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  `docsubtypeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`fieldid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formstructure`
--

LOCK TABLES `formstructure` WRITE;
/*!40000 ALTER TABLE `formstructure` DISABLE KEYS */;
INSERT INTO `formstructure` VALUES (16,'Agreement Between','optional','text','1',1,'8','2017-03-23 10:16:24',8),(17,'Name of Flat Owner','optional','text','1',1,'8','2017-03-23 10:16:49',8);
/*!40000 ALTER TABLE `formstructure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photos`
--

DROP TABLE IF EXISTS `photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photos` (
  `photosid` int(11) NOT NULL AUTO_INCREMENT,
  `phototype` varchar(45) DEFAULT NULL,
  `docid` int(11) DEFAULT NULL,
  `docpath` varchar(2000) DEFAULT NULL,
  `docname` varchar(1000) DEFAULT NULL,
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  `isactive` int(11) DEFAULT '1',
  `contenttype` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`photosid`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photos`
--

LOCK TABLES `photos` WRITE;
/*!40000 ALTER TABLE `photos` DISABLE KEYS */;
INSERT INTO `photos` VALUES (19,'society',1,'SocietyImages/1489638815298-2017_kawasaki_ninja_1000_5k-t2.jpg','1489638815298-2017_kawasaki_ninja_1000_5k-t2.jpg','2017-03-16 10:03:35',0,'image/jpeg'),(20,'society',1,'SocietyImages/1489638820017-2017_ktm_moto2_motogp_race_bike_4k-t2.jpg','1489638820017-2017_ktm_moto2_motogp_race_bike_4k-t2.jpg','2017-03-16 10:03:40',0,'image/jpeg'),(21,'society',1,'SocietyImages/1489638825184-2017_ktm_rc16_motogp_race_bike-t2.jpg','1489638825184-2017_ktm_rc16_motogp_race_bike-t2.jpg','2017-03-16 10:03:45',0,'image/jpeg'),(22,'society',1,'SocietyImages/1489638831917-2017_yamaha_mt_07-t2.jpg','1489638831917-2017_yamaha_mt_07-t2.jpg','2017-03-16 10:03:51',0,'image/jpeg'),(23,'society',1,'SocietyImages/1489638837459-repsol_honda_rc213v_motogp_2017_8k-t2.jpg','1489638837459-repsol_honda_rc213v_motogp_2017_8k-t2.jpg','2017-03-16 10:03:57',0,'image/jpeg'),(24,'society',1,'SocietyImages/1489678750208-twain-32-64.jpg','1489678750208-twain-32-64.jpg','2017-03-16 21:09:10',0,'image/jpeg'),(25,'society',1,'SocietyImages/1489819299807-inside_explosion_hd-1366x768.jpg','1489819299807-inside_explosion_hd-1366x768.jpg','2017-03-18 12:11:39',1,'image/jpeg');
/*!40000 ALTER TABLE `photos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `society`
--

DROP TABLE IF EXISTS `society`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `society` (
  `societyid` int(11) NOT NULL AUTO_INCREMENT,
  `societytypeid` int(11) DEFAULT NULL,
  `societyname` varchar(1000) DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  `isactive` int(11) DEFAULT '1',
  PRIMARY KEY (`societyid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `society`
--

LOCK TABLES `society` WRITE;
/*!40000 ALTER TABLE `society` DISABLE KEYS */;
INSERT INTO `society` VALUES (1,1,'Kalpana Complex CoOp Hsg Soc ltd','123','2017-03-13 20:06:07',1),(2,1,'Satyam Complex','2','2017-03-19 10:18:32',1);
/*!40000 ALTER TABLE `society` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `societydocmapping`
--

DROP TABLE IF EXISTS `societydocmapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `societydocmapping` (
  `societydocmappingid` int(11) NOT NULL AUTO_INCREMENT,
  `doctypeid` int(11) DEFAULT NULL,
  `societyid` int(11) DEFAULT NULL,
  `isactive` int(11) DEFAULT '1',
  PRIMARY KEY (`societydocmappingid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `societydocmapping`
--

LOCK TABLES `societydocmapping` WRITE;
/*!40000 ALTER TABLE `societydocmapping` DISABLE KEYS */;
INSERT INTO `societydocmapping` VALUES (3,5,1,1);
/*!40000 ALTER TABLE `societydocmapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `societymanager`
--

DROP TABLE IF EXISTS `societymanager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `societymanager` (
  `societymanagerid` int(11) NOT NULL AUTO_INCREMENT,
  `societyid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `isactive` int(11) DEFAULT '1',
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`societymanagerid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `societymanager`
--

LOCK TABLES `societymanager` WRITE;
/*!40000 ALTER TABLE `societymanager` DISABLE KEYS */;
INSERT INTO `societymanager` VALUES (2,1,8,1,'2017-03-13 23:57:02');
/*!40000 ALTER TABLE `societymanager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `societyprofile`
--

DROP TABLE IF EXISTS `societyprofile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `societyprofile` (
  `societyid` int(11) NOT NULL,
  `societyprofileid` int(11) NOT NULL AUTO_INCREMENT,
  `addressline1` varchar(1000) DEFAULT NULL,
  `addressline2` varchar(1000) DEFAULT NULL,
  `ward` varchar(125) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `state` varchar(125) DEFAULT NULL,
  `pincode` varchar(125) DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  `isactive` int(11) DEFAULT '1',
  `registrationno` varchar(255) DEFAULT NULL,
  `estdate` datetime DEFAULT NULL,
  PRIMARY KEY (`societyprofileid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `societyprofile`
--

LOCK TABLES `societyprofile` WRITE;
/*!40000 ALTER TABLE `societyprofile` DISABLE KEYS */;
INSERT INTO `societyprofile` VALUES (1,1,'Panchal Nagar12','Station Road','Nallasopara West','Thane','Maharashtra','401203',NULL,'2017-03-13 20:06:07',1,'TNA/VSI/12334','1988-03-08 00:00:00'),(2,2,'Panchal Nagar1','Panchal Nagar','Nallasopara West','Thane','Maharashtra','401209','2','2017-03-19 10:18:32',1,'TNA/VSI/1233465465','2017-03-19 00:00:00');
/*!40000 ALTER TABLE `societyprofile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `societytypemaster`
--

DROP TABLE IF EXISTS `societytypemaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `societytypemaster` (
  `societytypeid` int(11) NOT NULL AUTO_INCREMENT,
  `societytypename` varchar(45) DEFAULT NULL,
  `societytypedesc` varchar(45) DEFAULT NULL,
  `isactive` int(11) DEFAULT '1',
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`societytypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `societytypemaster`
--

LOCK TABLES `societytypemaster` WRITE;
/*!40000 ALTER TABLE `societytypemaster` DISABLE KEYS */;
INSERT INTO `societytypemaster` VALUES (1,'Residential','Residential',1,'2017-03-13 20:03:01'),(2,'Commercial','Commercial',1,'2017-03-13 20:03:01');
/*!40000 ALTER TABLE `societytypemaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant`
--

DROP TABLE IF EXISTS `tenant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant` (
  `tenantid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `tenantname` varchar(100) DEFAULT NULL,
  `tenantaddress` varchar(1000) DEFAULT NULL,
  `tenantcontactnumber` varchar(45) DEFAULT NULL,
  `tenantaltnumber` varchar(45) DEFAULT NULL,
  `tenantemail` varchar(45) DEFAULT NULL,
  `tenantaadharno` varchar(45) DEFAULT NULL,
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  `active` int(11) DEFAULT '1',
  PRIMARY KEY (`tenantid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant`
--

LOCK TABLES `tenant` WRITE;
/*!40000 ALTER TABLE `tenant` DISABLE KEYS */;
INSERT INTO `tenant` VALUES (3,8,'IDK','IDK','1234567891','1234567891','test@test.com','12345665432','2017-03-22 01:08:51',1);
/*!40000 ALTER TABLE `tenant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(125) DEFAULT NULL,
  `lastname` varchar(125) DEFAULT NULL,
  `username` varchar(125) DEFAULT NULL,
  `password` varchar(125) DEFAULT NULL,
  `createdate` datetime DEFAULT CURRENT_TIMESTAMP,
  `active` int(11) DEFAULT '1',
  `mobileNo` varchar(45) DEFAULT NULL,
  `createdBy` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'Prasad','VG',NULL,'pranav','2017-03-22 01:08:51',1,'8976724255','123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userprofile`
--

DROP TABLE IF EXISTS `userprofile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userprofile` (
  `userprofileid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `flatno` varchar(45) DEFAULT NULL,
  `wing` varchar(45) DEFAULT NULL,
  `tower` varchar(45) DEFAULT NULL,
  `occupancy` varchar(45) DEFAULT NULL,
  `alternateno` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `aadharno` varchar(45) DEFAULT NULL,
  `jointowners` varchar(45) DEFAULT NULL,
  `purchasedate` datetime DEFAULT NULL,
  `possessiondate` datetime DEFAULT NULL,
  `builtuparea` varchar(45) DEFAULT NULL,
  `carpetarea` varchar(45) DEFAULT NULL,
  `parkingtype` varchar(45) DEFAULT NULL,
  `vehicletype` varchar(45) DEFAULT NULL,
  `parkingallotmentno` varchar(45) DEFAULT NULL,
  `floor` varchar(45) DEFAULT NULL,
  `societyid` int(11) DEFAULT NULL,
  PRIMARY KEY (`userprofileid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userprofile`
--

LOCK TABLES `userprofile` WRITE;
/*!40000 ALTER TABLE `userprofile` DISABLE KEYS */;
INSERT INTO `userprofile` VALUES (7,8,'203','B','1','leased','','vgpranav@gmail.com','123412341234','','2017-03-23 00:00:00','2017-03-22 00:00:00','300','350','NO','2 Wheeler','No','1',1);
/*!40000 ALTER TABLE `userprofile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendors`
--

DROP TABLE IF EXISTS `vendors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendors` (
  `vendorid` int(11) NOT NULL AUTO_INCREMENT,
  `companyname` varchar(500) DEFAULT NULL,
  `jobnature` varchar(255) DEFAULT NULL,
  `contactperson` varchar(255) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `contactno` varchar(45) DEFAULT NULL,
  `alternateno` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `isactive` int(11) DEFAULT '1',
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  `createdby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`vendorid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendors`
--

LOCK TABLES `vendors` WRITE;
/*!40000 ALTER TABLE `vendors` DISABLE KEYS */;
INSERT INTO `vendors` VALUES (1,'Test123456789','test','test','test','0001','0001','test@abc.com','test',1,'2017-03-19 00:22:25',NULL),(2,'test12','test1','test1','test1 test1 test1 test1 test1 v v','1234','1134','test1','test1test1test1test1test1test1test1',1,'2017-03-19 00:39:48',NULL),(3,'test1111111','test1','test1','test1 test1 test1 test1 test1 v v','1234','1134','test1','test1test1test1test1test1test1test1',1,'2017-03-19 00:40:26',NULL),(4,'New','New','NewNew','NewNewNewNew','12345','12345','New@New.New','New New New ',1,'2017-03-23 09:43:01',NULL);
/*!40000 ALTER TABLE `vendors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendorsocietymapping`
--

DROP TABLE IF EXISTS `vendorsocietymapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendorsocietymapping` (
  `vendorsocietymappingid` int(11) NOT NULL AUTO_INCREMENT,
  `vendorid` varchar(45) DEFAULT NULL,
  `societyid` varchar(45) DEFAULT NULL,
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vendorsocietymappingid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendorsocietymapping`
--

LOCK TABLES `vendorsocietymapping` WRITE;
/*!40000 ALTER TABLE `vendorsocietymapping` DISABLE KEYS */;
INSERT INTO `vendorsocietymapping` VALUES (1,'1','1','2017-03-18 21:57:38'),(2,'2','1','2017-03-19 00:39:48'),(3,'3','1','2017-03-19 00:40:26'),(4,'4','1','2017-03-23 09:43:01');
/*!40000 ALTER TABLE `vendorsocietymapping` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-23 11:38:33
