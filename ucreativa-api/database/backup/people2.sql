# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 3.85.1.218 (MySQL 5.7.33-0ubuntu0.16.04.1)
# Database: ucdevops
# Generation Time: 2021-02-28 02:18:53 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table people
# ------------------------------------------------------------

DROP TABLE IF EXISTS `people`;

CREATE TABLE `people` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `mass` int(11) DEFAULT NULL,
  `hair_color` varchar(100) DEFAULT NULL,
  `skin_color` varchar(100) DEFAULT NULL,
  `eye_color` varchar(100) DEFAULT NULL,
  `birth_year` varchar(50) DEFAULT NULL,
  `gender` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;

INSERT INTO `people` (`id`, `name`, `height`, `mass`, `hair_color`, `skin_color`, `eye_color`, `birth_year`, `gender`)
VALUES
	(1,'Luke Skywalker',172,77,'blond','fair','blue','19BBY','male'),
	(2,'C-3PO',167,75,'n/a','gold','yellow','112BBy','n/a'),
	(3,'R2-D2',96,32,'n/a','white,blue','red','33BBy','n/a'),
	(4,'Darth Vader',202,136,'none','white','yellow','41.9BBy','male'),
	(5,'Leia Organa',150,49,'brwon','light','brown','19BBY','female'),
	(6,'Owen Lars',178,120,'brown,grey','light','blue','52BBY','male'),
	(7,'Beru Whitesun lars',165,75,'brown','light','blue','47BBY','female'),
	(8,'R5-D4',97,32,'n/a','white, red','red','unknown','n/a'),
	(9,'Biggs Darklighter',183,84,'black','light','brown','24BBy','male'),
	(10,'Obi-Wan Kenobi',182,77,'auburn, white','fair','blue-gray','57BBY','male'),
	(11,'Anakin Skywalker',188,84,'blond','fair','blue','41.9BBY','male'),
	(12,'Wilhuff Tarkin',180,9,'auburn, grey','fair','blue','64BBY','male'),
	(13,'Chewbacca',228,112,'brown','unknown','blue','200BBY','male'),
	(14,'Han Solo',180,80,'brown','fair','brown','29BBY','male'),
	(15,'Greedo',173,74,'n/a','green','black','44BBY','male'),
	(16,'Jabba Desilijic Tiure',175,1358,'n/a','green-tan, brown','orange','600BBY','hermaphrodi'),
	(17,'Wedge Antilles',170,77,'brown','fair','hazel','21BBY','male'),
	(18,'Jek Tono Porkins',180,110,'brown','fair','blue','unknown','male'),
	(20,'Yoda',66,17,'white','green','brown','896BBY','male');

/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
