/*
SQLyog Trial v13.1.8 (64 bit)
MySQL - 8.0.31 : Database - chat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`chat` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `chat`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int NOT NULL COMMENT '账号',
  `name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '匿名用户' COMMENT '昵称',
  `password` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `state` int NOT NULL DEFAULT '0' COMMENT '是否在线',
  `sex` varchar(3) COLLATE utf8mb4_general_ci DEFAULT '机械人' COMMENT '性别',
  `email` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `version` int DEFAULT '1' COMMENT '乐观锁',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `account` */

/*Table structure for table `account_group_relation` */

DROP TABLE IF EXISTS `account_group_relation`;

CREATE TABLE `account_group_relation` (
  `id` int NOT NULL COMMENT 'id',
  `account_id` int NOT NULL COMMENT '账户',
  `group_id` int NOT NULL COMMENT '群聊',
  `version` int DEFAULT '1' COMMENT '乐观锁',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `version` (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `account_group_relation` */

/*Table structure for table `account_message` */

DROP TABLE IF EXISTS `account_message`;

CREATE TABLE `account_message` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `from_id` int NOT NULL COMMENT '发送id',
  `to_Id` int NOT NULL COMMENT '接收id',
  `type` int DEFAULT '0' COMMENT '是否查阅',
  `message` tinytext COLLATE utf8mb4_general_ci COMMENT '消息内容',
  `version` int DEFAULT '1' COMMENT '乐观锁',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`,`to_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `account_message` */

/*Table structure for table `account_relation` */

DROP TABLE IF EXISTS `account_relation`;

CREATE TABLE `account_relation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account_id_1` int NOT NULL COMMENT '账户1',
  `account_id_2` int NOT NULL COMMENT '账户2',
  `type` int NOT NULL DEFAULT '1' COMMENT '关系类型',
  `version` int DEFAULT '1' COMMENT '乐观锁',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `account_relation` */

/*Table structure for table `group` */

DROP TABLE IF EXISTS `group`;

CREATE TABLE `group` (
  `id` int NOT NULL COMMENT '群聊id',
  `name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '群聊昵称',
  `owner_id` int NOT NULL COMMENT '群主id',
  `version` int DEFAULT '1' COMMENT '乐观锁',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `group` */

/*Table structure for table `group_message` */

DROP TABLE IF EXISTS `group_message`;

CREATE TABLE `group_message` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `group_id` int NOT NULL COMMENT '群聊id',
  `account_id` int DEFAULT NULL COMMENT '账户id',
  `message` tinytext CHARACTER SET utf8mb3 COMMENT '消息内容',
  `version` int DEFAULT '1' COMMENT '乐观锁',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `group_message` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
