-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: poo
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `hor_usu`
--

DROP TABLE IF EXISTS `hor_usu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hor_usu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `horario` int(11) DEFAULT NULL,
  `motorista` int(11) DEFAULT NULL,
  `passageiro` int(11) DEFAULT NULL,
  `confirmado` tinyint(4) DEFAULT NULL,
  `permanente` tinyint(4) DEFAULT NULL,
  `aberto` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `motorista_idx` (`motorista`,`id`),
  KEY `passag_idx` (`passageiro`),
  KEY `hor_idx` (`horario`),
  CONSTRAINT `hor` FOREIGN KEY (`horario`) REFERENCES `horario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mot` FOREIGN KEY (`motorista`) REFERENCES `motorista` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `passag` FOREIGN KEY (`passageiro`) REFERENCES `passageiro` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hor_usu`
--

LOCK TABLES `hor_usu` WRITE;
/*!40000 ALTER TABLE `hor_usu` DISABLE KEYS */;
/*!40000 ALTER TABLE `hor_usu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-21  2:18:15
