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
-- Table structure for table `daily_word`
--

DROP TABLE IF EXISTS `daily_word`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `daily_word` (
  `daily_word_id` int unsigned NOT NULL AUTO_INCREMENT,
  `guide_word` varchar(20) NOT NULL,
  `word` varchar(20) NOT NULL,
  PRIMARY KEY (`daily_word_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_word`
--

LOCK TABLES `daily_word` WRITE;
/*!40000 ALTER TABLE `daily_word` DISABLE KEYS */;
INSERT INTO `daily_word` VALUES (20,'안녕','안녕'),(21,'비빔밥','비빔밥'),(22,'축꾸','축구'),(23,'구지','굳이'),(24,'돈까스','돈까스'),(25,'',''),(26,'하하','하하'),(27,'ㅓㅏ;','ㅓㅏ;'),(28,'ㅓㅏㅣ;ㅏ;','ㅓㅏㅣ;ㅏ;'),(29,'ㅓㅏㅣ;ㅓㅏㅣ;','ㅓㅏㅣ;ㅓㅏㅣ;'),(30,'바바바','바바바'),(31,' 축꾸',' 축구'),(32,'하이용','하이용'),(33,'곧깜','곧감'),(34,'하이','하이'),(35,'쉗','쉣'),(36,'이스터에그','이스터에그'),(37,'농구','농구'),(38,'아아','아아'),(39,'어어','어어'),(40,'오오','오오'),(41,'ㄴ이이','ㄴ이이'),(42,'왜','왜'),(43,'하이라이스','하이라이스'),(44,'안녕하세요요요요','안녕하세요요요요'),(45,'가위바위','가위바위'),(46,'카레라이스','카레라이스'),(47,'이런','이런'),(48,'fsfda','fsfda'),(49,'ㅇㅇㅇㅇㅇㅇㅇ','ㅇㅇㅇㅇㅇㅇㅇ'),(50,'zz','zz'),(51,'가나다라마바사','가나다라마바사'),(52,'김밥','김밥'),(53,'가나다라','가나다라'),(54,'가나다라마','가나다라마'),(55,'가가','가가');
/*!40000 ALTER TABLE `daily_word` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-05 10:40:12
