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
-- Table structure for table `basic_letter`
--

DROP TABLE IF EXISTS `basic_letter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `basic_letter` (
  `basic_letter_id` int unsigned NOT NULL AUTO_INCREMENT,
  `guide_image` varchar(200) NOT NULL,
  `guide_letter` varchar(10) NOT NULL,
  `letter` varchar(10) NOT NULL,
  `letter_image` varchar(200) NOT NULL,
  `start_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`basic_letter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basic_letter`
--

LOCK TABLES `basic_letter` WRITE;
/*!40000 ALTER TABLE `basic_letter` DISABLE KEYS */;
INSERT INTO `basic_letter` VALUES (1,'기역.png','그','기역','basicLetter1.png','543'),(2,'니은.png','느','니은','basicLetter2.png','368'),(3,'디귿.png','드','디귿','basicLetter3.png','268'),(4,'리을.png','르','리을','basicLetter4.png','419'),(5,'미음.png','므','미음','basicLetter5.png','227'),(6,'비읍.png','브','비읍','basicLetter6.png','193'),(7,'시옷.png','스','시옷','basicLetter7.png','310'),(8,'이응.png','으','이응','basicLetter8.png','590'),(9,'지읒.png','즈','지읒','basicLetter9.png','494'),(10,'치읓.png','츠','치읓','basicLetter10.png',NULL),(11,'키읔.png','크','키읔','basicLetter11.png',NULL),(12,'티읕.png','트','티읕','basicLetter12.png',NULL),(13,'피읖.png','프','피읖','basicLetter13.png',NULL),(14,'히읗.png','흐','히읗','basicLetter14.png','633');
/*!40000 ALTER TABLE `basic_letter` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-05 10:40:11
