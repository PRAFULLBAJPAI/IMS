CREATE DATABASE  IF NOT EXISTS `ims` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ims`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: ims
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `branch_master`
--

DROP TABLE IF EXISTS `branch_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch_master` (
  `branch_code` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `branch_name` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `pin` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`branch_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch_master`
--

LOCK TABLES `branch_master` WRITE;
/*!40000 ALTER TABLE `branch_master` DISABLE KEYS */;
INSERT INTO `branch_master` VALUES ('BR-1','ABC Banglore','Banglore Branch','Banglore','India','br.banglore@gmail.com','9696853246','123456','Karnataka'),('BR-2','DEF Noida','Noida Branch','Noida','India','br.noida@gmail.com','9895742563','456789','Uttar Pradesh'),('BR-3','GHI Gurugram','Gurugram Branch','Gurugram','India','br.gurugram@gmail.com','8596421569','789123','Haryana'),('BR-4','JKL delhi','Delhi Branch','Delhi','India','br.delhi@gmail.com','6985412589','369258','Delhi'),('BR-5','HTJ Jalandhar','Jalandhar Branch','Jalandhar','India','br.jalandhar@gmail.com','9875123645','144635','Punjab'),('BR-6','XYH Kapurthla','Kapurthala Branch','Kapurthala','India','br.kapurthala@gmail.com','9875123365','144601','Punjab');
/*!40000 ALTER TABLE `branch_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_master`
--

DROP TABLE IF EXISTS `employee_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_master` (
  `emp_id` varchar(225) NOT NULL,
  `emp_role` varchar(255) DEFAULT NULL,
  `emp_name` varchar(255) DEFAULT NULL,
  `emp_pan` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `pin` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `branch` varchar(255) DEFAULT NULL,
  `login_flag` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `group_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`emp_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_master`
--

LOCK TABLES `employee_master` WRITE;
/*!40000 ALTER TABLE `employee_master` DISABLE KEYS */;
INSERT INTO `employee_master` VALUES ('DI-1','admin','Swati Jangra','GHTY675TY7','Sector-63','Noida','201301','Noida','Uttar Pradesh','8727053999','swatijangra1994@gmail.com','Active','Noida Branch','1','123','GRP-1'),('DI-2','trainee','Akansha Rathore','ABCDEF56RT','Sector 63','Noida','201301','Uttar Pradesh','India','8279619928','akansharathore252@gmail.com','Active','Noida Branch','0','8279619928','GRP-3'),('DI-3','admin','Divyanshu Yadav','HGFRD5678T','Sector 63','Noida','201301','Utta','India','9027063126','divyanshuyadav26680@gmail.com','Active','Noida Branch','1','123456','GRP-3'),('DI-4','trainee','Ekta Mishra','ABCDEF56RT','Sector 63','Noida','201301','Uttar Pradesh','India','9638527412','ektamishra751@gmail.com','Active','Noida Branch','0','9638527412','GRP-3'),('DI-5','admin','njbhg','ABCDEF56RT','Rail Coach Factory','Kapurthala','144602','Punjab','India','8727053999','divyanshu@doritech.in','Active','Jalandhar Branch','0','8727053999','GRP-1'),('DI-6','admin','Shiv Kumar','ABCDEF56RT','Sector 63','Noida','201301','Uttar Pradesh','India','09638527412','ektamishra751@gmail.com','Active','Noida Branch','0','09638527412','GRP-6'),('DI-7','admin','Swati Jangra','GHTY675TY7','Sector-63','Noida','201301','Noida','Uttar Pradesh','8727053999','divyadav262122@gmail.com','Active','Noida Branch','0','8727053999','GRP-1');
/*!40000 ALTER TABLE `employee_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_master`
--

DROP TABLE IF EXISTS `group_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_master` (
  `group_id` varchar(225) NOT NULL,
  `created_date` date DEFAULT NULL,
  `group_description` varchar(255) DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `emp_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_master`
--

LOCK TABLES `group_master` WRITE;
/*!40000 ALTER TABLE `group_master` DISABLE KEYS */;
INSERT INTO `group_master` VALUES ('GRP-1','2024-01-02','Admin Group','Admin Group','DI-1'),('GRP-10','2024-01-11','hjg','m,nb','DI-3'),('GRP-11','2024-01-11','zxcv','xcvcb','DI-2'),('GRP-12','2024-01-11','dfg','dfg','DI-1'),('GRP-13','2024-01-12','group2','Emergency Group','DI-6'),('GRP-14','2024-01-17','grp1','group-1','DI-6'),('GRP-15','2024-01-22','Gigitals','Digitals','DI-3'),('GRP-16','2024-01-17','group2','Emergency Group','DI-4'),('GRP-17','2024-01-09','grp','Emergency Group','DI-1'),('GRP-18','2024-01-19','grp1','GRP-1','DI-6'),('GRP-2','2024-01-02','Trainer Group','Trainer Group','DI-1'),('GRP-3','2024-01-02','Trainee Group','Trainee Group','DI-1'),('GRP-4','2024-01-04','zxc','zxcf','DI-1'),('GRP-5','2024-01-11','xzdfg','sadf','DI-1'),('GRP-6','2024-01-11','we','we','DI-4'),('GRP-7','2024-01-11','as','as','DI-4'),('GRP-8','2024-01-11','RE','RE','DI-2'),('GRP-9','2024-01-11','grp','grp','DI-1');
/*!40000 ALTER TABLE `group_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_access`
--

DROP TABLE IF EXISTS `menu_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_access` (
  `id` int NOT NULL,
  `menu_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_access`
--

LOCK TABLES `menu_access` WRITE;
/*!40000 ALTER TABLE `menu_access` DISABLE KEYS */;
INSERT INTO `menu_access` VALUES (741,2,101,'admin'),(742,3,101,'admin'),(743,4,101,'admin'),(744,5,101,'admin'),(745,8,101,'admin'),(746,9,101,'admin'),(747,15,101,'admin'),(748,16,101,'admin'),(750,12,103,'trainee'),(751,11,103,'trainee'),(752,10,103,'trainee'),(754,1,102,'trainer'),(773,7,101,'admin'),(942,6,101,'admin'),(970,1,101,'admin');
/*!40000 ALTER TABLE `menu_access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_master`
--

DROP TABLE IF EXISTS `menu_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_master` (
  `menu_id` int NOT NULL,
  `child_id` int DEFAULT NULL,
  `menu_handler_name` varchar(255) DEFAULT NULL,
  `menu_icon` varchar(255) DEFAULT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `status` json DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_master`
--

LOCK TABLES `menu_master` WRITE;
/*!40000 ALTER TABLE `menu_master` DISABLE KEYS */;
INSERT INTO `menu_master` VALUES (1,0,'./trainingMaster','fas fa-tachometer-alt','Training Master',1,NULL),(2,0,'./employeeMasterScreen','fa-solid fa-user-pen','Employee Master',1,NULL),(3,0,'./groupMasterScreen','fas fa-file-upload','Group Master',1,NULL),(4,0,'./assignTraining','fas fa-photo-video','Assign Training',0,NULL),(5,0,'./reportAdmin','fas fa-users','All Reports',2,NULL),(6,0,'./handler-6','fas fa-money-check-alt','Employee Reporting',0,NULL),(7,0,'./handler-7','fas fa-cogs','MIS',0,NULL),(8,0,'./menuAccessScreen','fas fa-user','Role Master',1,NULL),(9,0,'./overDueScreen','fas fa-users','Pending Reports',2,NULL),(10,0,'./traineeOngoingActivity','fas fa-tachometer-alt','My Trainings',0,NULL),(11,0,'./completedActivity','fa-solid fa-user-pen','Completed Trainings',0,NULL),(12,0,'./','fas fa-file-upload','My Certificates',0,NULL),(13,0,'./','fas fa-photo-video','Assessment Evaluation',0,NULL),(14,0,'./','fas fa-users','Training Status',0,NULL),(15,1,'./allMasters','fas fa-user','All Masters',0,NULL),(16,2,'./reports','fas fa-users','Reports',0,NULL);
/*!40000 ALTER TABLE `menu_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `param_entity`
--

DROP TABLE IF EXISTS `param_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `param_entity` (
  `id` int NOT NULL,
  `code` varchar(255) NOT NULL,
  `serial` varchar(255) NOT NULL,
  `descp1` varchar(255) DEFAULT NULL,
  `descp2` varchar(255) DEFAULT NULL,
  `descp3` varchar(255) DEFAULT NULL,
  `descp4` varchar(255) DEFAULT NULL,
  `descp5` varchar(255) DEFAULT NULL,
  `param1` varchar(255) DEFAULT NULL,
  `param2` varchar(255) DEFAULT NULL,
  `param3` varchar(255) DEFAULT NULL,
  `param4` varchar(255) DEFAULT NULL,
  `param5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`code`,`serial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `param_entity`
--

LOCK TABLES `param_entity` WRITE;
/*!40000 ALTER TABLE `param_entity` DISABLE KEYS */;
INSERT INTO `param_entity` VALUES (1,'CATEGORY','EMPLOYEE','MANAGEMENT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'CATEGORY','EMPLOYEE','ACCOUNTS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'CATEGORY','EMPLOYEE','IT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'CATEGORY','EMPLOYEE','HR',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'CATEGORY','EMPLOYEE','SALES',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'CATEGORY','EMPLOYEE','FINANCE',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'EMPLOYEE_ID','SEQUENCE','DI-','8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'BRANCH_ID','SEQUENCE','BR-','7',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'GROUP_ID','SEQUENCE','GRP-','19',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,'ASSESSMENT','ATTEMPTS','ATTEMPT','10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'ROLE','admin','admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'ROLE','trainer','trainer',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,'ROLE','trainee','trainee',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `param_entity` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-16 17:10:20
