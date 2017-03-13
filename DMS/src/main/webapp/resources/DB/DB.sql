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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `committee`
--

LOCK TABLES `committee` WRITE;
/*!40000 ALTER TABLE `committee` DISABLE KEYS */;
INSERT INTO `committee` VALUES (1,2,NULL,1,NULL,1,NULL,1,'2017-03-13 00:00:00',NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docsubtype`
--

LOCK TABLES `docsubtype` WRITE;
/*!40000 ALTER TABLE `docsubtype` DISABLE KEYS */;
INSERT INTO `docsubtype` VALUES (1,1,'Flat Sale Deed','Flat Sale Deed',NULL,'2017-03-13 20:15:57',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctype`
--

LOCK TABLES `doctype` WRITE;
/*!40000 ALTER TABLE `doctype` DISABLE KEYS */;
INSERT INTO `doctype` VALUES (1,'Agreement  Legal Documents','Agreement  Legal Documents',1,NULL,'2017-03-13 20:15:08');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formstructure`
--

LOCK TABLES `formstructure` WRITE;
/*!40000 ALTER TABLE `formstructure` DISABLE KEYS */;
INSERT INTO `formstructure` VALUES (1,'Agreement Between','optional','text','1',1,NULL,'2017-03-13 20:22:52',1),(2,'Date of Agreement','optional','date','1',1,NULL,'2017-03-13 20:23:18',1);
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
  `docpath` varchar(45) DEFAULT NULL,
  `docname` varchar(45) DEFAULT NULL,
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  `isactive` int(11) DEFAULT '1',
  `contenttype` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`photosid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photos`
--

LOCK TABLES `photos` WRITE;
/*!40000 ALTER TABLE `photos` DISABLE KEYS */;
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
  `societyprofileid` int(11) DEFAULT NULL,
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
  PRIMARY KEY (`societyid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `society`
--

LOCK TABLES `society` WRITE;
/*!40000 ALTER TABLE `society` DISABLE KEYS */;
INSERT INTO `society` VALUES (1,1,'Kalpana Complex CoOp Hsg Soc ltd',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123','2017-03-13 20:06:07',1,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `societydocmapping`
--

LOCK TABLES `societydocmapping` WRITE;
/*!40000 ALTER TABLE `societydocmapping` DISABLE KEYS */;
INSERT INTO `societydocmapping` VALUES (1,1,1,1);
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
  `createdon` varchar(45) DEFAULT 'current_timestamp',
  PRIMARY KEY (`societymanagerid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `societymanager`
--

LOCK TABLES `societymanager` WRITE;
/*!40000 ALTER TABLE `societymanager` DISABLE KEYS */;
INSERT INTO `societymanager` VALUES (1,1,2,1,'current_timestamp');
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
  `societytypeid` int(11) DEFAULT NULL,
  `societyname` varchar(1000) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `societyprofile`
--

LOCK TABLES `societyprofile` WRITE;
/*!40000 ALTER TABLE `societyprofile` DISABLE KEYS */;
INSERT INTO `societyprofile` VALUES (1,NULL,NULL,1,'Panchal Nagar','Station Road','Nallasopara West','Thane','Maharashtra','401203',NULL,'2017-03-13 20:06:07',1,'TNA/VSI/12334','1988-03-08 00:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,NULL,NULL,'123456','2017-03-13 19:58:13',1,'9920650410',NULL),(2,'Pranav','VG',NULL,'pranav','2017-03-13 20:08:34',1,'8976724255','123');
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
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `mobileNo` varchar(45) DEFAULT NULL,
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
  `password` varchar(45) DEFAULT NULL,
  `societyid` int(11) DEFAULT NULL,
  PRIMARY KEY (`userprofileid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userprofile`
--

LOCK TABLES `userprofile` WRITE;
/*!40000 ALTER TABLE `userprofile` DISABLE KEYS */;
INSERT INTO `userprofile` VALUES (1,2,NULL,NULL,NULL,'203','B','2','self','','vgpranav@gmail.com','1234123412341234','','2017-03-13 00:00:00','2017-03-13 00:00:00','465','520','NA','2Wheeler','NA','2',NULL,1);
/*!40000 ALTER TABLE `userprofile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'dms'
--

--
-- Dumping routines for database 'dms'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-13 20:40:16
