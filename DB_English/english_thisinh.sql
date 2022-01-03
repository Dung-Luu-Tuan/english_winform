-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: english
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `thisinh`
--

DROP TABLE IF EXISTS `thisinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thisinh` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hoten` varchar(45) DEFAULT NULL,
  `gioitinh` varchar(45) DEFAULT NULL,
  `ngaysinh` datetime DEFAULT NULL,
  `noisinh` varchar(45) DEFAULT NULL,
  `cmnd` varchar(45) DEFAULT NULL,
  `ngaycap` datetime DEFAULT NULL,
  `noicap` varchar(45) DEFAULT NULL,
  `sdt` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thisinh`
--

LOCK TABLES `thisinh` WRITE;
/*!40000 ALTER TABLE `thisinh` DISABLE KEYS */;
INSERT INTO `thisinh` VALUES (1,'Lưu Tuấn Dũng','Nam','2022-01-15 00:00:00','dasdas','0123456789','2021-12-31 00:00:00','dasdas','113244444','dsadaasdas'),(14,'Hoa Thanh Danh','Nam','2022-01-01 00:00:00','Củ Chi','1231231321','2012-01-12 00:00:00','Củ CHi','041534654','asds@dsadas'),(15,'Tăng Minh Tiến','Nam','2021-12-30 00:00:00','TPHCM','015456465','2016-01-07 00:00:00','TPHCM','105456456','dad@dasds'),(16,'Đinh Lê Minh Bạch','Nam','2022-01-14 00:00:00','WibuLand','452456','2021-12-31 00:00:00','WibuLand','154564654','dasd@dadsad');
/*!40000 ALTER TABLE `thisinh` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-03 14:29:47
