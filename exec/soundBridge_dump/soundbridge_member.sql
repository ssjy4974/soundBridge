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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` int unsigned NOT NULL AUTO_INCREMENT,
  `age` int NOT NULL,
  `del_flag` int NOT NULL,
  `email` varchar(30) NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `nickname` varchar(30) NOT NULL,
  `profile` varchar(150) DEFAULT 'default.png',
  `role` varchar(15) DEFAULT NULL,
  `voice_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `UKmbmcqelty0fbrvxp1q58dn57t` (`email`),
  KEY `fk_member_voice_idx` (`voice_id`),
  CONSTRAINT `fk_member_voice_idx` FOREIGN KEY (`voice_id`) REFERENCES `voice` (`voice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,10,0,'sjy4974@naver.com','male','준용','http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg','APPLICANT',1),(2,20,0,'l6778@naver.com','male','이종민','http://k.kakaocdn.net/dn/ccSXzB/btrZjfRnqsF/Wzk9K2TvIH7sx1VxJWzcZK/img_640x640.jpg','APPLICANT',2),(3,20,0,'ehgus964@gmail.com','male','서울_5반_김도현','https://lh3.googleusercontent.com/a/AGNmyxY7noF4i74rowTnhZ1tpD0vXHCl0Nxr7aAaSw6-=s96-c','APPLICANT',1),(4,50,0,'leo9876@naver.com','male','김도현','http://k.kakaocdn.net/dn/nOF6D/btrTz01cqjL/BJ1gdChPwZzKQk2bwPZAjK/img_640x640.jpg','HELPER',1),(5,20,0,'asng@kakao.com','male','승태','http://k.kakaocdn.net/dn/bFL4uf/btrWRP3ldZS/W6xoK5uVyT8KD8sOt5kW10/img_640x640.jpg','APPLICANT',2),(7,20,0,'asngtae@gmail.com','male','안승태','https://lh3.googleusercontent.com/a/AGNmyxZLoFmQXFhQyX6Ui-3jHK-gjwxU5ulRWNmc8p_K=s96-c','HELPER',1),(8,10,0,'ghunji@nate.com','male','지강훈','http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg','APPLICANT',2),(9,40,0,'cavalier7@naver.com','male','고성현','http://k.kakaocdn.net/dn/rIRT4/btrQpyeeUTm/l8mMPXjBxoy1mSbPfUmVu0/img_640x640.jpg','HELPER',1),(10,30,0,'tpthf1@gmail.com','male','서울_8반_오세솔','https://lh3.googleusercontent.com/a/AGNmyxbz09OfKqsEtm0FxuuyhRr7fWd-6OfMCJe6-ucA=s96-c','HELPER',1),(13,30,0,'o_s_s_@naver.com','male','오세솔','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/sesol.png','HELPER',26),(39,10,0,'1','female','미경','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/mikyung.png','HELPER',1),(40,20,0,'2','female','다인','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/dain.png','HELPER',1),(41,20,0,'3','female','아라','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/ara.png','HELPER',1),(42,50,0,'4','female','민영','http://k.kakaocdn.net/dn/nOF6D/btrTz01cqjL/BJ1gdChPwZzKQk2bwPZAjK/img_640x640.jpg','HELPER',1),(43,20,0,'5','female','예진','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/yejin.png','HELPER',3),(44,20,0,'6','female','미진','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/mijin.png','HELPER',1),(45,10,0,'7','male','진호','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/jinho.png','HELPER',3),(46,40,0,'8','male','민상','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/minsang.png','HELPER',1),(47,30,0,'9','male','신우','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/sinwoo.png','HELPER',1),(48,20,0,'10','male','하준','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/hajun.png','HELPER',1),(49,20,0,'11','female','지윤','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/jiyun.png','HELPER',1),(50,30,0,'12','female','수진','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/sujin.png','HELPER',1),(51,20,0,'13','male','지훈','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/jihun.png','HELPER',1),(52,20,0,'14','male','주안','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/juan.png','HELPER',1),(53,20,0,'15','male','성훈','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/sunghun.png','HELPER',1),(54,20,0,'16','male','지환','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/jihwan.png','HELPER',1),(55,20,0,'17','male','시윤','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/siyun.png','HELPER',1),(56,20,0,'18','female','가람','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/garam.png','HELPER',1),(57,20,0,'19','female','은영','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/enyeong.png','HELPER',1),(58,20,0,'20','female','선경','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/sunkyung.png','HELPER',1),(59,20,0,'21','female','유진','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/youjin.png','HELPER',1),(60,20,0,'22','male','태진','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/taejin.png','HELPER',1),(61,20,0,'23','male','영일','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/yeongil.png','HELPER',1),(62,20,0,'24','male','승표','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/seungpyo.png','HELPER',1),(63,20,0,'25','male','원탁','https://yeon-chung.s3.ap-northeast-2.amazonaws.com/wontak.png','HELPER',1),(68,20,0,'ljm67780726@gmail.com','male','이종민','https://lh3.googleusercontent.com/a/AGNmyxYWzWAd4UVgtCzoJA5Uo6GQD36nOMBQhQQZmiBc=s96-c','HELPER',2),(69,0,0,'wlqus2020@gmail.com',NULL,'Gh J','https://lh3.googleusercontent.com/a/AGNmyxYEzMrZuGSV8NMXf2icx0GT2JvCuK4Nk_LvcWuQ=s96-c','HELPER',1),(70,20,0,'sng_a@naver.com','M','sng','https://ssl.pstatic.net/static/pwe/address/img_profile.png',NULL,NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-05 10:40:14
