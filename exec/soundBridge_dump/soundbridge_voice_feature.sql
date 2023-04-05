CREATE DATABASE  IF NOT EXISTS `soundbridge` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `soundbridge`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: j8a703.p.ssafy.io    Database: soundbridge
-- ------------------------------------------------------
-- Server version	8.0.32-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `voice_feature`
--

DROP TABLE IF EXISTS `voice_feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voice_feature` (
  `voice_feature_id` int unsigned NOT NULL AUTO_INCREMENT,
  `feature_id` int unsigned NOT NULL,
  `voice_id` int unsigned NOT NULL,
  PRIMARY KEY (`voice_feature_id`),
  KEY `fk_voice_feature_feature_idx` (`feature_id`),
  KEY `fk_voice_feature_voice_idx` (`voice_id`),
  CONSTRAINT `fk_voice_feature_feature_idx` FOREIGN KEY (`feature_id`) REFERENCES `feature` (`feature_id`),
  CONSTRAINT `fk_voice_feature_voice_idx` FOREIGN KEY (`voice_id`) REFERENCES `voice` (`voice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voice_feature`
--

LOCK TABLES `voice_feature` WRITE;
/*!40000 ALTER TABLE `voice_feature` DISABLE KEYS */;
INSERT INTO `voice_feature` VALUES (4,12,1),(5,15,1),(11,2,2),(12,3,2),(13,1,3),(14,18,3),(15,1,4),(16,18,4),(17,4,5),(18,9,5),(19,3,6),(20,5,6),(21,4,7),(22,10,7),(23,4,8),(24,9,8),(25,14,9),(26,15,9),(27,3,10),(28,15,10),(29,1,11),(30,2,11),(31,8,12),(32,9,12),(33,11,13),(34,15,13),(35,8,14),(36,15,14),(37,2,15),(38,9,15),(39,3,16),(40,13,16),(41,10,17),(42,14,17),(43,9,18),(44,16,18),(45,2,19),(46,5,19),(47,4,20),(48,8,20),(49,3,21),(50,15,21),(51,9,22),(52,10,22),(53,8,23),(54,15,23),(55,13,24),(56,15,24),(57,15,25),(58,16,25),(59,15,26),(60,16,26);
/*!40000 ALTER TABLE `voice_feature` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-05 10:40:16
