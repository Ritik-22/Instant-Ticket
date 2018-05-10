CREATE DATABASE  IF NOT EXISTS `railway_database` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `railway_database`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: railway_database
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `idadmin` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `phoneNo` bigint(20) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idadmin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basefare`
--

DROP TABLE IF EXISTS `basefare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basefare` (
  `fareforadult` int(11) NOT NULL,
  `fareforchild` int(11) NOT NULL,
  `idtrain` int(11) DEFAULT NULL,
  KEY `fkt_idx` (`idtrain`),
  CONSTRAINT `fkt` FOREIGN KEY (`idtrain`) REFERENCES `train` (`idtrain`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basefare`
--

LOCK TABLES `basefare` WRITE;
/*!40000 ALTER TABLE `basefare` DISABLE KEYS */;
/*!40000 ALTER TABLE `basefare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passes`
--

DROP TABLE IF EXISTS `passes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passes` (
  `stopNo` int(11) NOT NULL,
  `distance` int(11) DEFAULT NULL,
  `idtrain` int(11) DEFAULT NULL,
  `idstation` int(11) DEFAULT NULL,
  KEY `fk_tid_idx` (`idtrain`),
  KEY `fk_sid_idx` (`idstation`),
  CONSTRAINT `fk_sid` FOREIGN KEY (`idstation`) REFERENCES `station` (`idstation`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tid` FOREIGN KEY (`idtrain`) REFERENCES `train` (`idtrain`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passes`
--

LOCK TABLES `passes` WRITE;
/*!40000 ALTER TABLE `passes` DISABLE KEYS */;
/*!40000 ALTER TABLE `passes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `platformno` int(11) NOT NULL,
  `arrtime` time NOT NULL,
  `deptime` time DEFAULT NULL,
  `day` varchar(5) DEFAULT NULL,
  `idtrain` int(11) DEFAULT NULL,
  UNIQUE KEY `arrtime_UNIQUE` (`arrtime`),
  KEY `fk_ti_idx` (`idtrain`),
  CONSTRAINT `fk_ti` FOREIGN KEY (`idtrain`) REFERENCES `train` (`idtrain`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'10:00:00','10:00:59','M',10001),(1,'10:00:01','10:01:00','T',10001),(1,'10:05:00','10:05:05','M',10002);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `station` (
  `idstation` int(11) NOT NULL,
  `stationName` varchar(45) DEFAULT NULL,
  `noOfP` int(11) DEFAULT NULL,
  PRIMARY KEY (`idstation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'Director Office',1),(2,'CS',2),(3,'Civil',4),(4,'ComputerCenter',1),(5,'library',2),(6,'ATC',2);
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `idticket` int(11) NOT NULL AUTO_INCREMENT,
  `source` varchar(45) NOT NULL,
  `destination` varchar(45) NOT NULL,
  `idtrain` int(11) NOT NULL,
  `curdatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `noOfadults` int(4) DEFAULT NULL,
  `noOfChild` int(4) DEFAULT NULL,
  `distance` int(5) DEFAULT NULL,
  `fare` int(5) DEFAULT NULL,
  PRIMARY KEY (`idticket`),
  UNIQUE KEY `idticket_UNIQUE` (`idticket`),
  KEY `fk_t_idx` (`idtrain`),
  CONSTRAINT `fk_t` FOREIGN KEY (`idtrain`) REFERENCES `train` (`idtrain`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train`
--

DROP TABLE IF EXISTS `train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `train` (
  `idtrain` int(11) NOT NULL,
  `trainName` varchar(45) DEFAULT NULL,
  `traintype` varchar(45) DEFAULT NULL,
  `destination` varchar(45) DEFAULT NULL,
  `baseFare` float DEFAULT NULL,
  PRIMARY KEY (`idtrain`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train`
--

LOCK TABLES `train` WRITE;
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` VALUES (10001,'sgsits_express','Bullet','library',10),(10002,'cs_express','super_bullet','ComputerCenter',15);
/*!40000 ALTER TABLE `train` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'railway_database'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-13 22:09:46
