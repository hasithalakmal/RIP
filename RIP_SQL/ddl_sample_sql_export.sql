CREATE DATABASE  IF NOT EXISTS `testquery` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `testquery`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: testquery
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.13-MariaDB

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
-- Table structure for table `tbl1`
--

DROP TABLE IF EXISTS `tbl1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl1` (
  `tbl1col1` int(11) NOT NULL AUTO_INCREMENT,
  `tbl1col2` varchar(45) NOT NULL,
  `tbl1col3` float DEFAULT NULL,
  `tbl1col4` double DEFAULT NULL,
  `tbl1col5` char(10) DEFAULT NULL,
  `tbl1col6` date DEFAULT NULL,
  `tbl1col7` datetime DEFAULT NULL,
  `tbl1col8` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`tbl1col1`),
  UNIQUE KEY `tbl1col_UNIQUE` (`tbl1col2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl1`
--

LOCK TABLES `tbl1` WRITE;
/*!40000 ALTER TABLE `tbl1` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl2`
--

DROP TABLE IF EXISTS `tbl2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl2` (
  `tbl2col1` int(11) NOT NULL AUTO_INCREMENT,
  `tbl2col2` int(11) DEFAULT '1',
  `tbl2col3` varchar(45) DEFAULT 'rip test',
  `tbl2col4` blob,
  `tbl2col5` double DEFAULT '12.34',
  `tbl2col6` float DEFAULT '1.2345',
  `tbl2col7` date DEFAULT NULL,
  `tbl2col8` datetime DEFAULT NULL,
  `tbl2col9` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `tbl2col10` char(10) DEFAULT 'hasi',
  PRIMARY KEY (`tbl2col1`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl2`
--

LOCK TABLES `tbl2` WRITE;
/*!40000 ALTER TABLE `tbl2` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl3`
--

DROP TABLE IF EXISTS `tbl3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl3` (
  `tbl3col1` int(11) NOT NULL AUTO_INCREMENT,
  `tbl3col2` varchar(45) DEFAULT NULL,
  `tbl3col3` varchar(45) DEFAULT NULL,
  `tbl3col4` int(11) DEFAULT NULL,
  PRIMARY KEY (`tbl3col1`),
  KEY `fk1_idx` (`tbl3col4`),
  CONSTRAINT `fk1` FOREIGN KEY (`tbl3col4`) REFERENCES `tbl1` (`tbl1col1`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl3`
--

LOCK TABLES `tbl3` WRITE;
/*!40000 ALTER TABLE `tbl3` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl4`
--

DROP TABLE IF EXISTS `tbl4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl4` (
  `idtbl4` int(11) NOT NULL,
  `tbl4col` int(11) NOT NULL,
  PRIMARY KEY (`idtbl4`,`tbl4col`),
  CONSTRAINT `fk2` FOREIGN KEY (`idtbl4`) REFERENCES `tbl1` (`tbl1col1`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl4`
--

LOCK TABLES `tbl4` WRITE;
/*!40000 ALTER TABLE `tbl4` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'testquery'
--

--
-- Dumping routines for database 'testquery'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-20  8:07:24
