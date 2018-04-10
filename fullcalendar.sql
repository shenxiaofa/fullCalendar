/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.17-log : Database - fullcalendar
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fullcalendar` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `fullcalendar`;

/*Table structure for table `calendar` */

DROP TABLE IF EXISTS `calendar`;

CREATE TABLE `calendar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `start` varchar(50) NOT NULL,
  `end` varchar(50) DEFAULT NULL,
  `allday` tinyint(1) NOT NULL DEFAULT '0',
  `color` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `calendar` */

insert  into `calendar`(`id`,`title`,`start`,`end`,`allday`,`color`) values (11,'first to the egg','2018-04-02','2018-04-06',1,'#000000'),(12,'first to the key','2018-04-10','2018-04-12',1,'#f30'),(13,'first','2018-04-08','04/09/2018',1,'#360');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
