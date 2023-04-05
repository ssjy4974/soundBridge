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
-- Table structure for table `voice`
--

DROP TABLE IF EXISTS `voice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voice` (
  `voice_id` int unsigned NOT NULL AUTO_INCREMENT,
  `voice_age` int unsigned NOT NULL,
  `voice_gender` varchar(10) NOT NULL,
  `voice_name` varchar(200) NOT NULL,
  `member_id` int unsigned DEFAULT NULL,
  `model_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`voice_id`),
  KEY `fk_vocie_member_idx` (`member_id`),
  CONSTRAINT `fk_vocie_member_idx` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voice`
--

LOCK TABLES `voice` WRITE;
/*!40000 ALTER TABLE `voice` DISABLE KEYS */;
INSERT INTO `voice` VALUES (1,30,'M','오세솔',7,NULL),(2,20,'W','미경',39,'vmikyung'),(3,10,'W','다인',40,'ndain'),(4,20,'W','아라',41,'nara'),(5,20,'W','민영',42,'nminyoung'),(6,20,'W','예진',43,'nyejin'),(7,20,'W','미진',44,'mijin'),(8,20,'M','진호',45,'jinho'),(9,20,'M','민상',46,'nminsang'),(10,20,'M','신우',47,'nsinu'),(11,10,'M','하준',48,'nhajun'),(12,20,'W','지윤',49,'njiyun'),(13,20,'W','수진',50,'nsujin'),(14,20,'M','지훈',51,'njihun'),(15,20,'M','주안',52,'njooahn'),(16,20,'M','성훈',53,'nseonghoon'),(17,20,'M','지환',54,'njihwan'),(18,20,'M','시윤',55,'nsiyoon'),(19,10,'W','가람',56,'ngaram'),(20,20,'W','은영',57,'neunyoung'),(21,20,'W','선경',58,'nsunkyung'),(22,20,'W','유진',59,'nyujin'),(23,20,'M','태진',60,'ntaejin'),(24,20,'M','영일',61,'nyoungil'),(25,20,'M','승표',62,'nseungpyo'),(26,20,'M','원탁',63,'nwontak');
/*!40000 ALTER TABLE `voice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-05 10:40:15
