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
-- Table structure for table `try_history`
--

DROP TABLE IF EXISTS `try_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `try_history` (
  `try_history_id` int unsigned NOT NULL AUTO_INCREMENT,
  `success_count` int unsigned DEFAULT '0',
  `try_count` int unsigned DEFAULT '1',
  `type` varchar(15) DEFAULT NULL,
  `basic_letter_id` int unsigned DEFAULT NULL,
  `member_id` int unsigned NOT NULL,
  `word_member_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`try_history_id`),
  KEY `fk_try_history_basic_letter_idx` (`basic_letter_id`),
  KEY `fk_try_history_member_idx` (`member_id`),
  KEY `fk_try_history_word_member_idx` (`word_member_id`),
  CONSTRAINT `fk_try_history_basic_letter_idx` FOREIGN KEY (`basic_letter_id`) REFERENCES `basic_letter` (`basic_letter_id`),
  CONSTRAINT `fk_try_history_member_idx` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `fk_try_history_word_member_idx` FOREIGN KEY (`word_member_id`) REFERENCES `word_member` (`word_member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `try_history`
--

LOCK TABLES `try_history` WRITE;
/*!40000 ALTER TABLE `try_history` DISABLE KEYS */;
INSERT INTO `try_history` VALUES (1,5,6,'BASIC_LETTER',1,8,NULL),(2,3,10,'BASIC_LETTER',2,8,NULL),(3,5,6,'BASIC_LETTER',3,8,NULL),(4,5,8,'BASIC_LETTER',4,8,NULL),(5,5,13,'BASIC_LETTER',5,8,NULL),(6,6,11,'BASIC_LETTER',6,8,NULL),(7,7,14,'BASIC_LETTER',7,8,NULL),(8,1,6,'BASIC_LETTER',8,8,NULL),(9,2,4,'BASIC_LETTER',9,8,NULL),(10,3,3,'BASIC_LETTER',10,8,NULL),(11,6,15,'BASIC_LETTER',11,8,NULL),(12,7,18,'BASIC_LETTER',12,8,NULL),(13,8,9,'BASIC_LETTER',13,8,NULL),(14,1,1,'BASIC_LETTER',14,8,NULL);
/*!40000 ALTER TABLE `try_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-05 10:40:17
