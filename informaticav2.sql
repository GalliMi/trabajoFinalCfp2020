CREATE DATABASE  IF NOT EXISTS `informaticav2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `informaticav2`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: informatica
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `articulos`
--

DROP TABLE IF EXISTS `articulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulos` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `fabricantes_codigo` int(11) NOT NULL,
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_articulos_fabricantes_idx` (`fabricantes_codigo`),
  CONSTRAINT `fk_articulos_fabricantes` FOREIGN KEY (`fabricantes_codigo`) REFERENCES `fabricantes` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulos`
--

LOCK TABLES `articulos` WRITE;
/*!40000 ALTER TABLE `articulos` DISABLE KEYS */;
INSERT INTO `articulos` VALUES (101,'Teclado',100.00,3,23),(102,'Disco duro 300 Gb',500.00,5,8),(103,'Mouse',80.00,3,35),(104,'Memoria USB',140.00,4,34),(105,'Memoria RAM',290.00,1,18),(106,'Disco duro extraíble 250 Gb',650.00,5,2),(107,'Memoria USB',279.00,1,4),(108,'DVD Rom',450.00,2,6),(109,'CD Rom',120.00,2,8),(110,'Tarjeta de red',100.00,3,14),(111,'SSD2',234.00,3,13),(112,'DDR4',122.00,2,8),(113,'Usb ',444.00,2,44),(114,'CPU i7',345.00,6,22);
/*!40000 ALTER TABLE `articulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `codcliente` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `apellido` varchar(80) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `localidad` varchar(45) NOT NULL,
  `Provincia` varchar(45) NOT NULL DEFAULT 'CABA',
  `cod_postal` varchar(6) DEFAULT NULL,
  `Telefono` varchar(30) DEFAULT NULL,
  `DNI` tinytext NOT NULL,
  PRIMARY KEY (`codcliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'José','Ruiz','Av. Corrientes  2301','Buenos Aires','CABA','2340','656789043','13455268'),(2,'Luis','Fernandez','Lavalle 3390 3ro C','Buenos Aires','CABA','4538','675789066','34890345'),(3,'Antonio','Gómez','Lamadrid 239','Mar del Plata ','Buenos Aires','3454','654389044','37335908'),(4,'Andrea','Romero Vázquez','Sarmiento 863','Cordoba','Cordoba','7898','643678057','23398034'),(5,'José','Pérez','Rosedales 922','Bariloche','Río Negro','8042','646789043','29034587');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra` (
  `clientes_codcliente` int(11) NOT NULL,
  `articulos_codigo` int(11) NOT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `unidades` int(11) NOT NULL,
  PRIMARY KEY (`clientes_codcliente`,`articulos_codigo`,`fecha`),
  KEY `fk_clientes_has_articulos_articulos1_idx` (`articulos_codigo`),
  KEY `fk_clientes_has_articulos_clientes1_idx` (`clientes_codcliente`),
  CONSTRAINT `fk_clientes_has_articulos_articulos1` FOREIGN KEY (`articulos_codigo`) REFERENCES `articulos` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_clientes_has_articulos_clientes1` FOREIGN KEY (`clientes_codcliente`) REFERENCES `clientes` (`codcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` VALUES (1,102,'2018-11-12 09:23:45',1),(1,104,'2019-01-16 08:23:11',1),(1,106,'2018-11-11 13:23:44',2),(1,109,'2019-01-16 13:23:56',2),(2,103,'2018-11-12 10:03:06',1),(2,104,'2019-03-23 14:23:06',1),(2,105,'2018-11-13 15:20:17',1),(2,110,'2019-02-11 13:23:31',1),(3,101,'2019-03-22 10:23:54',4),(3,106,'2018-11-14 19:03:43',2),(4,102,'2018-12-15 12:23:42',1),(4,104,'2019-03-23 13:03:15',2),(5,101,'2018-12-15 13:23:50',3);
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fabricantes`
--

DROP TABLE IF EXISTS `fabricantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fabricantes` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fabricantes`
--

LOCK TABLES `fabricantes` WRITE;
/*!40000 ALTER TABLE `fabricantes` DISABLE KEYS */;
INSERT INTO `fabricantes` VALUES (1,'Kingston'),(2,'Adata'),(3,'Logitech'),(4,'Western Digital'),(5,'Seagate'),(6,'Intel'),(7,'Amd'),(8,'Microsoft');
/*!40000 ALTER TABLE `fabricantes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-22 17:04:52
