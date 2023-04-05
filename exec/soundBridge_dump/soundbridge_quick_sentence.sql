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
-- Table structure for table `quick_sentence`
--

DROP TABLE IF EXISTS `quick_sentence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quick_sentence` (
  `quick_sentence_id` int unsigned NOT NULL AUTO_INCREMENT,
  `sentence` varchar(100) NOT NULL,
  `sentence_count` int unsigned DEFAULT '0',
  `category_id` int unsigned NOT NULL,
  PRIMARY KEY (`quick_sentence_id`),
  KEY `fk_quick_sentence_category_idx` (`category_id`),
  CONSTRAINT `fk_quick_sentence_category_idx` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quick_sentence`
--

LOCK TABLES `quick_sentence` WRITE;
/*!40000 ALTER TABLE `quick_sentence` DISABLE KEYS */;
INSERT INTO `quick_sentence` VALUES (1,'이거 얼마예요',3,1),(2,'계산 해주세요',1,1),(3,'화장실이 어디인가요',0,1),(4,'메뉴판 좀 주세요',0,1),(5,'강남역 방향이 어디인가요',0,2),(6,'1번 출구는 어디인가요',0,2),(7,'화장실은 어디인가요',0,2),(8,'역삼역 방향은 어디인가요',0,2),(9,'결재 부탁드립니다',0,3),(10,'퇴근 하겠습니다',0,3),(11,'만나서 반갑습니다',0,3),(12,'질문 있습니다',0,3),(13,'저는 청각 장애인입니다',0,4),(14,'도와 주실 수 있나요',0,4),(15,'제 이름은 지강훈입니다',0,4),(16,'만나서 반갑습니다',0,4);
/*!40000 ALTER TABLE `quick_sentence` ENABLE KEYS */;
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
