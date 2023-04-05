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
-- Table structure for table `feedback_board`
--

DROP TABLE IF EXISTS `feedback_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback_board` (
  `feedback_board_id` int unsigned NOT NULL AUTO_INCREMENT,
  `end_time` datetime(6) NOT NULL,
  `start_time` datetime(6) NOT NULL,
  `title` varchar(50) NOT NULL,
  `member_id` int unsigned NOT NULL,
  PRIMARY KEY (`feedback_board_id`),
  KEY `fk_feedback_board_member_idx` (`member_id`),
  CONSTRAINT `fk_feedback_board_member_idx` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback_board`
--

LOCK TABLES `feedback_board` WRITE;
/*!40000 ALTER TABLE `feedback_board` DISABLE KEYS */;
INSERT INTO `feedback_board` VALUES (1,'2023-04-06 12:30:00.000000','2023-04-06 11:30:00.000000','기본 발음 피드백 요청',8),(2,'2023-04-06 12:00:00.000000','2023-04-06 10:00:00.000000','곧감 발음 피드백 요청',8),(3,'2023-04-07 14:00:00.000000','2023-04-07 12:30:00.000000','축구 발음 피드백 요청',8),(4,'2023-04-07 14:30:00.000000','2023-04-07 11:30:00.000000','문장 연습 좀 도와주세요',8),(5,'2023-04-08 09:30:00.000000','2023-04-08 09:00:00.000000','발음 연습 좀 도와주세요',8),(6,'2023-04-08 15:00:00.000000','2023-04-08 13:30:00.000000','인삿말 피드백 요청',8),(7,'2023-04-08 16:30:00.000000','2023-04-08 14:50:00.000000','도와주세요!!!',8),(8,'2023-04-08 15:30:00.000000','2023-04-08 15:00:00.000000','발음 좀 알려주세요',8);
/*!40000 ALTER TABLE `feedback_board` ENABLE KEYS */;
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
