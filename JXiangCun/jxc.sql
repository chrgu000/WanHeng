/*
SQLyog Ultimate v8.32 
MySQL - 5.5.40 : Database - jxiangcun
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jxiangcun` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `jxiangcun`;

/*Table structure for table `jxc_address` */

DROP TABLE IF EXISTS `jxc_address`;

CREATE TABLE `jxc_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `province` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `city` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `area` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `isDefault` char(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_address` */

insert  into `jxc_address`(`id`,`name`,`tel`,`address`,`province`,`city`,`area`,`isDefault`) values (1,'杨俊','18768143568','杭州市滨江区滨文路12号','浙江省','杭州市','滨江区','1');

/*Table structure for table `jxc_admin` */

DROP TABLE IF EXISTS `jxc_admin`;

CREATE TABLE `jxc_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `power_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_admin` */

insert  into `jxc_admin`(`id`,`username`,`password`,`power_id`) values (1,'admin','21232F297A57A5A743894A0E4A801FC3',1);

/*Table structure for table `jxc_city` */

DROP TABLE IF EXISTS `jxc_city`;

CREATE TABLE `jxc_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '城市名称',
  `num` int(11) DEFAULT NULL COMMENT '二级序列号',
  `ch` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '一级序列号',
  `hot_city` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '热门城市',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_city` */

insert  into `jxc_city`(`id`,`name`,`num`,`ch`,`hot_city`) values (9,'杭州',0,'H','0');

/*Table structure for table `jxc_date_price` */

DROP TABLE IF EXISTS `jxc_date_price`;

CREATE TABLE `jxc_date_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `original_price` double DEFAULT NULL,
  `favourable_price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=733 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_date_price` */

insert  into `jxc_date_price`(`id`,`product_id`,`date`,`original_price`,`favourable_price`) values (732,35,'2016-08-30 00:00:00',350,351),(731,35,'2016-08-29 00:00:00',250,251),(730,35,'2016-08-28 00:00:00',250,251),(729,35,'2016-08-27 00:00:00',250,251),(728,35,'2016-08-26 00:00:00',250,251),(727,35,'2016-08-25 00:00:00',350,351),(726,35,'2016-08-24 00:00:00',350,351),(725,35,'2016-08-23 00:00:00',150,151),(724,35,'2016-08-22 00:00:00',250,251),(723,35,'2016-08-21 00:00:00',250,251),(722,35,'2016-08-20 00:00:00',250,251),(721,35,'2016-08-19 00:00:00',250,251),(720,35,'2016-08-18 00:00:00',350,351),(719,35,'2016-08-17 00:00:00',350,351),(718,35,'2016-08-16 00:00:00',350,351),(717,35,'2016-08-15 00:00:00',250,251),(716,35,'2016-08-14 00:00:00',250,251),(715,35,'2016-08-13 00:00:00',250,251),(714,35,'2016-08-12 00:00:00',250,251),(713,35,'2016-08-11 00:00:00',350,351),(712,35,'2016-08-10 00:00:00',350,351),(711,35,'2016-08-09 00:00:00',350,351),(710,35,'2016-08-08 00:00:00',250,251),(709,35,'2016-08-07 00:00:00',250,251),(708,35,'2016-08-06 00:00:00',250,251),(707,35,'2016-08-05 00:00:00',250,251),(706,35,'2016-08-04 00:00:00',350,351),(705,35,'2016-08-03 00:00:00',350,351),(704,35,'2016-08-02 00:00:00',350,351),(703,35,'2016-08-01 00:00:00',250,251),(702,35,'2016-08-31 00:00:00',250,251),(701,35,'2016-08-30 00:00:00',250,251),(700,35,'2016-08-29 00:00:00',250,251),(699,35,'2016-08-28 00:00:00',350,351),(698,35,'2016-08-27 00:00:00',350,351),(697,35,'2016-08-26 00:00:00',350,351),(696,35,'2016-08-25 00:00:00',250,251),(695,35,'2016-08-24 00:00:00',250,251),(694,35,'2016-08-23 00:00:00',150,151),(693,35,'2016-08-22 00:00:00',250,251),(692,35,'2016-08-21 00:00:00',350,351),(691,35,'2016-08-20 00:00:00',350,351),(690,35,'2016-08-19 00:00:00',350,351),(689,35,'2016-08-18 00:00:00',250,251),(688,35,'2016-08-17 00:00:00',250,251),(687,35,'2016-08-16 00:00:00',250,251),(686,35,'2016-08-15 00:00:00',250,251),(685,35,'2016-08-14 00:00:00',350,351),(684,35,'2016-08-13 00:00:00',350,351),(683,35,'2016-08-12 00:00:00',350,351),(682,35,'2016-08-11 00:00:00',250,251),(681,35,'2016-08-10 00:00:00',250,251),(680,35,'2016-08-09 00:00:00',250,251),(679,35,'2016-08-08 00:00:00',250,251),(678,35,'2016-08-07 00:00:00',350,351),(677,35,'2016-08-06 00:00:00',350,351),(676,35,'2016-08-05 00:00:00',350,351),(675,35,'2016-08-04 00:00:00',250,251),(674,35,'2016-08-03 00:00:00',250,251),(673,35,'2016-08-02 00:00:00',250,251),(672,35,'2016-08-01 00:00:00',250,251);

/*Table structure for table `jxc_distance` */

DROP TABLE IF EXISTS `jxc_distance`;

CREATE TABLE `jxc_distance` (
  `id` int(11) DEFAULT NULL,
  `distance` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_distance` */

insert  into `jxc_distance`(`id`,`distance`) values (1,'100公里以内'),(2,'100-200公里'),(3,'200-300公里'),(4,'300公里');

/*Table structure for table `jxc_evaluate` */

DROP TABLE IF EXISTS `jxc_evaluate`;

CREATE TABLE `jxc_evaluate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评价内容',
  `merchant_id` int(11) DEFAULT NULL COMMENT '商户id',
  `nickname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评价者昵称',
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像路径',
  `createDate` datetime DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_evaluate` */

insert  into `jxc_evaluate`(`id`,`content`,`merchant_id`,`nickname`,`path`,`createDate`,`num`) values (1,'niusmniu',5,'雪域青竹','http://wx.qlogo.cn/mmopen/PiajxSqBRaEIgBOlBWUumgbYIbiceKTyic9GVxp6TZulaCu1OHoYxEDOQYknmn6JwSH29Orf9Qbo1HhEEMVZeARqw/0',NULL,5);

/*Table structure for table `jxc_invite` */

DROP TABLE IF EXISTS `jxc_invite`;

CREATE TABLE `jxc_invite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `invite_id` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_invite` */

/*Table structure for table `jxc_inviter` */

DROP TABLE IF EXISTS `jxc_inviter`;

CREATE TABLE `jxc_inviter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nickname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_inviter` */

/*Table structure for table `jxc_mark` */

DROP TABLE IF EXISTS `jxc_mark`;

CREATE TABLE `jxc_mark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签',
  `num` int(11) DEFAULT NULL COMMENT '序号',
  `isFront` char(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否在首页',
  `title_id` int(11) DEFAULT NULL COMMENT '村游微农乡投标题id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_mark` */

insert  into `jxc_mark`(`id`,`name`,`num`,`isFront`,`title_id`) values (1,'瑜伽',1,'0',2),(2,'桑拿',1,'1',1),(4,'氧吧',2,'1',1),(5,'养生',2,'1',1),(6,'温泉',4,'1',1),(9,'云海',0,'1',1),(8,'避暑',0,'1',1),(10,'禅修',0,'1',1),(11,'农户',0,'1',2),(12,'生态',1,'1',2),(13,'放养',2,'1',2),(14,'黑猪肉',3,'1',1),(15,'土猪肉',4,'1',2),(16,'榨油',1,'0',2),(17,'绿色',2,'1',2),(18,'环保',3,'1',2),(19,'合作社',3,'1',2);

/*Table structure for table `jxc_mark_merchant` */

DROP TABLE IF EXISTS `jxc_mark_merchant`;

CREATE TABLE `jxc_mark_merchant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mark_id` int(11) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_mark_merchant` */

insert  into `jxc_mark_merchant`(`id`,`mark_id`,`merchant_id`) values (91,5,5),(90,4,5),(99,5,6),(98,4,6),(97,2,6),(89,2,5),(104,9,7),(103,6,7),(102,5,7),(101,4,7),(100,2,7),(107,12,8),(106,11,8),(105,1,8);

/*Table structure for table `jxc_merchant` */

DROP TABLE IF EXISTS `jxc_merchant`;

CREATE TABLE `jxc_merchant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` int(11) DEFAULT NULL,
  `price_id` int(11) DEFAULT NULL,
  `sequnence_id` int(11) DEFAULT NULL,
  `title_id` int(11) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `details` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `original_price` double DEFAULT NULL,
  `favourable_price` double DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `longitude` double DEFAULT NULL COMMENT '经度',
  `latitude` double NOT NULL COMMENT '纬度',
  `isOk` int(11) DEFAULT NULL COMMENT '点赞数',
  `sight_spot_id` int(11) DEFAULT NULL COMMENT '景点id',
  `predetermine` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '预定须知',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_merchant` */

insert  into `jxc_merchant`(`id`,`city_id`,`price_id`,`sequnence_id`,`title_id`,`title`,`sub_title`,`content`,`details`,`original_price`,`favourable_price`,`path`,`longitude`,`latitude`,`isOk`,`sight_spot_id`,`predetermine`) values (6,9,2,NULL,1,'杭州千岛湖湖畔','杭州千岛湖湖畔居度假公寓','位于千岛湖镇，千岛湖阳光路469号阳光水岸度假村4号楼B座303','11',399,299,'uploadstart/20160823_11305644.jpg',0,12.3,15,3,'123<br />'),(5,9,2,NULL,1,'杭州西湖湖畔','杭州西湖湖畔居度假公寓','位于西湖湖滨商圈，上城区解放路201号','在解百新世纪商厦附近 。该酒店距西湖景观区直线距离约0.7公里',599,499,'uploadstart/20160822_13283846.jpg',0,33,13,4,'123<br />'),(7,9,3,NULL,1,' 杭州西湖灵隐玖间',' 杭州西湖灵隐玖间堂禅缘客栈 ','杭州西湖区西湖区灵隐白乐桥50号（灵隐寺景区内）','123',599,579,'uploadstart/20160823_1131088.jpg',0,12,9,5,'123<br />'),(8,9,4,NULL,2,'杭州西溪纳德润泽园','位于西溪湿地商圈，杭州西湖区花坞路3号','在西溪国家湿地公园南门附近','离景区很近，早餐不错  尊享大床很大,细节很用心 ',599,559,'uploadstart/20160823_11313024.jpg',22,32,NULL,8,'222<br />');

/*Table structure for table `jxc_order` */

DROP TABLE IF EXISTS `jxc_order`;

CREATE TABLE `jxc_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `beginTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `days` int(22) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `appId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `timeStamp` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nonceStr` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `packageValue` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `signType` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `paySign` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sendUrl` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `agent` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `number` int(11) DEFAULT NULL COMMENT '订单数量',
  `product_id` int(11) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `isFree` char(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否免费',
  `title_id` int(11) DEFAULT NULL,
  `isInvite` char(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `spare_num` int(11) DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `free_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_order` */

insert  into `jxc_order`(`id`,`order_num`,`beginTime`,`endTime`,`createTime`,`status`,`total_price`,`days`,`user_id`,`appId`,`timeStamp`,`nonceStr`,`packageValue`,`signType`,`paySign`,`sendUrl`,`agent`,`title`,`number`,`product_id`,`merchant_id`,`isFree`,`title_id`,`isInvite`,`spare_num`,`endDate`,`free_num`) values (1,'2222222',NULL,NULL,NULL,'1',20,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'1',1,NULL,NULL,NULL,NULL),(2,'2222222',NULL,NULL,NULL,'1',20,1,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,1,'1',1,NULL,NULL,NULL,NULL),(3,'1471913750378',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,NULL,1,'0',NULL,NULL,NULL),(4,'1471917252218',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,'1',1,'0',NULL,NULL,NULL),(5,'1471917298301',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,'1',1,'0',NULL,NULL,NULL),(6,'1471918241476',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,'1',1,'0',NULL,NULL,NULL),(7,'1471918244211',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,'1',1,'0',NULL,NULL,NULL),(8,'1471918307045',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,'1',1,'0',NULL,NULL,NULL),(9,'1471918339797',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,'1',1,'0',NULL,NULL,NULL),(10,'1471918462766',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,'1',1,'0',NULL,NULL,NULL),(11,'1471918501354',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,'1',1,'0',NULL,NULL,NULL),(12,'1471918516180',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,'1',1,'0',NULL,NULL,NULL),(13,'1471918544986',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,'1',1,'0',NULL,NULL,NULL),(14,'1471918616274',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,'1',1,'0',NULL,NULL,NULL),(15,'1471918740731',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,'1',1,'0',NULL,NULL,NULL),(16,'1471918762047',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,'1',1,'0',NULL,NULL,NULL),(17,'1471918829846',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,'1',1,'0',NULL,NULL,NULL),(18,'1471918853774',NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,5,'1',1,'0',NULL,NULL,NULL);

/*Table structure for table `jxc_picture` */

DROP TABLE IF EXISTS `jxc_picture`;

CREATE TABLE `jxc_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片路径',
  `merchant_id` int(11) DEFAULT NULL COMMENT '商品id',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用途的路径',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片的应用类型',
  `num` int(11) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_picture` */

insert  into `jxc_picture`(`id`,`path`,`merchant_id`,`url`,`type`,`num`) values (4,'uploadstart/20160822_13371769.jpg',5,'www.baidu.com','hehe',NULL),(5,'uploadstart/20160822_13372933.jpg',5,'www.baidu.com','hehe',NULL),(6,'uploadstart/20160822_13374174.jpg',5,'www.baidu.com','hehe',NULL),(7,'uploadstart/20160822_13375050.jpg',5,'www.baidu.com','hehe',NULL),(8,'uploadstart/20160822_13380081.jpg',5,'www.baidu.com','hehe',NULL),(9,'uploadstart/20160823_1135230.jpg',6,'www.sina.com.cn','xixi',NULL),(10,'uploadstart/20160823_11353343.jpg',6,'www.sina.com.cn','xixi',NULL),(11,'uploadstart/20160823_11354541.jpg',6,'www.sina.com.cn','xixi',NULL),(12,'uploadstart/20160823_11355667.jpg',6,'www.sina.com.cn','xixi',NULL),(13,'uploadstart/20160823_11361179.jpg',6,'www.sina.com.cn','xixi',NULL),(14,'uploadstart/20160823_11380984.jpg',7,'www.taobao.com','lala',NULL),(15,'uploadstart/20160823_11381785.jpg',7,'www.taobao.com','lala',NULL),(16,'uploadstart/20160823_11382589.jpg',7,'www.taobao.com','lala',NULL),(17,'uploadstart/20160823_11383691.jpg',7,'www.taobao.com','lala',NULL),(18,'uploadstart/20160823_11384550.jpg',7,'www.taobao.com','lala',NULL);

/*Table structure for table `jxc_power` */

DROP TABLE IF EXISTS `jxc_power`;

CREATE TABLE `jxc_power` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_power` */

insert  into `jxc_power`(`id`,`name`) values (1,'管理员'),(2,'超级管理员');

/*Table structure for table `jxc_price` */

DROP TABLE IF EXISTS `jxc_price`;

CREATE TABLE `jxc_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_price` */

insert  into `jxc_price`(`id`,`price`) values (1,'0-150'),(2,'150-300'),(3,'300-500'),(4,'500-1000'),(5,'1000以上');

/*Table structure for table `jxc_product` */

DROP TABLE IF EXISTS `jxc_product`;

CREATE TABLE `jxc_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL COMMENT '支持数量',
  `isFree` char(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `isUse` char(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `free_num` int(11) DEFAULT NULL,
  `pointDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_product` */

insert  into `jxc_product`(`id`,`title`,`sub_title`,`path`,`merchant_id`,`number`,`isFree`,`endDate`,`isUse`,`free_num`,`pointDate`) values (5,'杭州千岛湖渔水谣民宿','位于千岛湖中心湖区','uploadstart/20160823_11314563.jpg',6,0,'1','2016-08-30 00:00:00',NULL,12,NULL),(6,'千岛湖云顶度假公寓','杭州淳安县千岛湖镇阳光路阳光水岸度假村','uploadstart/20160823_11315313.jpg',6,0,'1','2016-08-27 00:00:00',NULL,1,NULL),(7,'千岛湖畅想宾馆','淳安县千岛湖镇南山大街','uploadstart/20160823_11320227.jpg',6,0,'0',NULL,NULL,NULL,NULL),(8,'7天酒店杭州千岛湖中心湖景区店','杭州淳安县千岛湖南景路','uploadstart/20160823_11321836.jpg',6,0,'0',NULL,NULL,NULL,NULL),(9,'千岛湖丽景酒店','淳安县阳光路千岛湖阳光水岸度假村内','uploadstart/20160823_11323377.jpg',6,0,'0',NULL,NULL,NULL,NULL),(10,'杭州西湖等花开精品民宿','位于西湖灵隐度假景区','uploadstart/20160822_13273566.jpg',5,0,'1','2016-08-23 00:00:00',NULL,12,NULL),(11,'锦江之星品尚杭州西湖大道南宋御街酒店','位于西湖湖滨商圈，上城区中山中路196号，靠近定安路地铁站D口 ','uploadstart/20160822_13274757.jpg',5,0,'0',NULL,NULL,NULL,NULL),(12,'杭州浙航湖滨酒店','位于西湖湖滨商圈，上城区青年路48号（近地铁1号线、定安路口），靠近定安路地铁站A口 ','uploadstart/20160822_13280545.jpg',5,0,'0',NULL,NULL,NULL,NULL),(13,'杭州J.S美宿公馆青年酒店西湖店','位于西湖湖滨商圈，杭州上城区光复路159号，在杭州市第一人民医院附近 ','uploadstart/20160822_13281687.jpg',5,0,'0',NULL,NULL,NULL,NULL),(14,'杭州文凯大酒店','位于文教区商圈，杭州西湖区文一路296号（物美广场汇和大厦），在中竹大厦附近 。','uploadstart/20160822_1328252.jpg',5,0,'0',NULL,NULL,NULL,NULL),(15,'杭州银图电影主题酒店西溪湿地店','位于西溪湿地商圈，杭州西湖区区西溪路908号A座（近西溪国家湿地公园南门） 。','uploadstart/20160823_11325379.jpg',7,0,'0',NULL,NULL,NULL,NULL),(16,'杭州灵隐湛然堂度假小酒店','位于西湖灵隐度假景区，杭州西湖风景名胜区灵隐支路白乐桥242号，靠近浙江朱仁民艺术馆 ','uploadstart/20160823_11330220.jpg',7,0,'0',NULL,NULL,NULL,NULL),(17,'杭州蜗牛酒店','位于黄龙体育中心，杭州玉古路青芝坞113号','uploadstart/20160823_11331115.jpg',7,0,'0',NULL,NULL,NULL,NULL),(20,'杭州西溪纳德润泽园度假酒店','位于西溪湿地商圈，杭州西湖区花坞路3号','uploadstart/20160823_11333562.jpg',8,0,'0',NULL,NULL,NULL,NULL),(21,'杭州栖泊酒店','位于西溪湿地商圈，杭州西湖区天目山路413号近西溪国家湿地公园','uploadstart/20160823_11334442.jpg',8,0,'0',NULL,NULL,NULL,NULL),(22,'杭州素舍精品酒店西溪店','位于西溪湿地商圈，杭州西湖区文三西路和紫金港路交叉口','uploadstart/20160823_11335336.jpg',8,0,'0',NULL,NULL,NULL,NULL),(23,'杭州同悦酒店','位于西溪湿地商圈，杭州余杭区向往街199号','uploadstart/20160823_1134080.jpg',8,0,'0',NULL,NULL,NULL,NULL),(24,'如家快捷杭州五常大道西溪湿地印象城店','位于西溪湿地商圈，余杭区五常大道丰岭路11号','uploadstart/20160823_11350157.jpg',8,0,'0',NULL,NULL,NULL,NULL),(34,'杭州千岛湖湖畔','杭州千岛湖湖畔居度假公寓','uploadstart/20160829_16245227.jpg',6,NULL,'0',NULL,NULL,NULL,'2016-08-30 00:00:00'),(35,'杭州千岛湖湖畔','杭州千岛湖湖畔居度假公寓','uploadstart/20160829_16275833.jpg',6,NULL,'0',NULL,NULL,NULL,'2016-08-23 00:00:00'),(36,'杭州千岛湖湖畔','杭州千岛湖湖畔居度假公寓','uploadstart/20160829_16450034.jpg',6,NULL,'1','2016-08-30 00:00:00',NULL,12,NULL);

/*Table structure for table `jxc_search` */

DROP TABLE IF EXISTS `jxc_search`;

CREATE TABLE `jxc_search` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `chr` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '链接',
  `num` int(11) DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_search` */

insert  into `jxc_search`(`id`,`title`,`chr`,`url`,`num`) values (2,'11','111','11',11);

/*Table structure for table `jxc_selfdom` */

DROP TABLE IF EXISTS `jxc_selfdom`;

CREATE TABLE `jxc_selfdom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `selfdom` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_selfdom` */

/*Table structure for table `jxc_sequence` */

DROP TABLE IF EXISTS `jxc_sequence`;

CREATE TABLE `jxc_sequence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sequence` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_sequence` */

insert  into `jxc_sequence`(`id`,`sequence`) values (1,'好评优先'),(2,'收藏最多'),(3,'价格最低'),(4,'价格最高');

/*Table structure for table `jxc_sight_spot` */

DROP TABLE IF EXISTS `jxc_sight_spot`;

CREATE TABLE `jxc_sight_spot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `title_id` int(11) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_sight_spot` */

insert  into `jxc_sight_spot`(`id`,`name`,`city_id`,`path`,`content`,`title_id`,`longitude`,`latitude`) values (3,'千岛湖',9,'uploadstart/20160823_11432961.jpg','千岛湖阳光路469号阳光水岸度假村',1,30.254897,120.258744),(4,'西湖景区',9,'uploadstart/20160822_13294066.jpg','杭州西湖区虎跑路四眼井',1,0,0),(5,'灵隐寺',9,'uploadstart/20160823_11434126.jpg','杭州西湖灵隐玖间堂禅缘客栈',1,0,0),(8,'西溪湿地',9,'uploadstart/20160823_11435596.jpg','xixi',2,0,0);

/*Table structure for table `jxc_support` */

DROP TABLE IF EXISTS `jxc_support`;

CREATE TABLE `jxc_support` (
  `id` int(11) DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nickname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `support_time` datetime DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_support` */

/*Table structure for table `jxc_ticket` */

DROP TABLE IF EXISTS `jxc_ticket`;

CREATE TABLE `jxc_ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `title_id` int(11) DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `isUse` char(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '券是否被使用过',
  `useDate` datetime DEFAULT NULL COMMENT '消费时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_ticket` */

insert  into `jxc_ticket`(`id`,`price`,`title_id`,`endDate`,`content`,`user_id`,`isUse`,`useDate`) values (3,22,1,'2016-08-18 00:00:00','33',NULL,'0',NULL);

/*Table structure for table `jxc_title` */

DROP TABLE IF EXISTS `jxc_title`;

CREATE TABLE `jxc_title` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_title` */

insert  into `jxc_title`(`id`,`name`) values (1,'人人村游'),(2,'人人微农'),(3,'人人乡投');

/*Table structure for table `jxc_user` */

DROP TABLE IF EXISTS `jxc_user`;

CREATE TABLE `jxc_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `regist_time` datetime DEFAULT NULL,
  `open_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `money` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jxc_user` */

insert  into `jxc_user`(`id`,`tel`,`regist_time`,`open_id`,`path`,`nickname`,`money`) values (3,'18768143568',NULL,NULL,NULL,'雪域青竹',NULL),(4,'18768143568',NULL,NULL,NULL,'雪域青竹',NULL),(5,'18768143568',NULL,NULL,NULL,'雪域青竹',NULL),(6,'18768143568',NULL,NULL,NULL,'雪域青竹',NULL),(7,'18768143568',NULL,NULL,NULL,'雪域',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
