/*
SQLyog Ultimate v8.32 
MySQL - 5.7.16 : Database - boerdiqi
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`boerdiqi` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `boerdiqi`;

/*Table structure for table `att` */

DROP TABLE IF EXISTS `att`;

CREATE TABLE `att` (
  `att_id` int(11) NOT NULL AUTO_INCREMENT,
  `attr_title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '规格名称',
  `addtime` datetime DEFAULT NULL,
  `delflag` int(11) DEFAULT NULL COMMENT '删除标记1为存在,2为删除',
  PRIMARY KEY (`att_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `att` */

insert  into `att`(`att_id`,`attr_title`,`addtime`,`delflag`) values (1,'尺寸','2017-01-13 10:33:02',1),(2,'颜色','2017-01-13 10:33:02',1),(4,'性别','2017-01-18 15:35:18',1);

/*Table structure for table `attr` */

DROP TABLE IF EXISTS `attr`;

CREATE TABLE `attr` (
  `attr_id` int(11) NOT NULL AUTO_INCREMENT,
  `att_id` int(11) DEFAULT NULL,
  `v` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `delflag` int(11) DEFAULT NULL COMMENT '删除标记1为存在,2删除',
  PRIMARY KEY (`attr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `attr` */

insert  into `attr`(`attr_id`,`att_id`,`v`,`addtime`,`delflag`) values (1,1,'S','2017-01-13 10:33:02',1),(2,1,'M','2017-01-13 10:33:02',1),(3,1,'L','2017-01-13 10:33:02',1),(4,1,'XL','2017-01-13 10:33:02',1),(5,2,'红','2017-01-13 10:33:02',1),(6,2,'黄','2017-01-13 10:33:02',1),(7,2,'蓝','2017-01-13 10:33:02',1),(24,1,'11','2017-01-18 15:19:22',2),(25,1,'22','2017-01-18 15:19:26',2),(26,2,'11','2017-01-18 15:19:50',2),(27,1,'red','2017-01-18 15:20:22',2),(28,2,'red','2017-01-18 15:20:30',2),(29,4,'男','2017-01-18 15:35:18',1),(30,4,'女','2017-01-18 15:35:22',1),(31,1,'sss','2017-01-23 14:31:24',2),(32,2,'aaa','2017-01-23 14:34:02',2);

/*Table structure for table `dq_address` */

DROP TABLE IF EXISTS `dq_address`;

CREATE TABLE `dq_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收件人姓名',
  `tel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `province` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '省份',
  `city` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '城市',
  `area` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '县区',
  `addr` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '详细地址',
  `isDefault` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否默认地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_address` */

insert  into `dq_address`(`id`,`user_id`,`username`,`tel`,`province`,`city`,`area`,`addr`,`isDefault`) values (1,1,'张月峰','15869155845','浙江省','杭州市','滨江区','滨文路12号万恒创业园12楼','1'),(2,1,'陈佳杰','13857137145','浙江省','杭州市','西湖区','文三路昌地火炬大厦14楼','1'),(3,1,'杨俊','18768143568','浙江省','杭州市','滨江区','滨康路之江纺织荣乐公寓2051','2'),(4,2,'杨俊','18768143568','浙江省','杭州市','滨江区','滨康路之江纺织荣乐公寓2051','2');

/*Table structure for table `dq_admin` */

DROP TABLE IF EXISTS `dq_admin`;

CREATE TABLE `dq_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `join_time` datetime DEFAULT NULL COMMENT '注册时间',
  `ip` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登陆ip地址',
  `token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_admin` */

insert  into `dq_admin`(`id`,`username`,`password`,`role_id`,`join_time`,`ip`,`token`) values (1,'admin','698D51A19D8A121CE581499D7B701668',1,'2017-01-12 14:25:50','0:0:0:0:0:0:0:1','7cec15c1c0c844c59c369283d556a409'),(4,'yangjun','698D51A19D8A121CE581499D7B701668',3,'2017-01-12 14:30:13',NULL,NULL),(5,'yuefeng','698D51A19D8A121CE581499D7B701668',1,'2017-01-12 14:30:21','125.118.3.93','c9b1ceb0f5dc4dab9f1bda82edc647a0');

/*Table structure for table `dq_balance` */

DROP TABLE IF EXISTS `dq_balance`;

CREATE TABLE `dq_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `news` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '充值信息',
  `join_time` date DEFAULT NULL COMMENT '交易时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_balance` */

insert  into `dq_balance`(`id`,`user_id`,`news`,`join_time`) values (1,1,'在2017-01-22时充值了100元钱','2017-01-22'),(2,2,'在2017-01-22时充值了200元钱','2017-01-22');

/*Table structure for table `dq_big_type` */

DROP TABLE IF EXISTS `dq_big_type`;

CREATE TABLE `dq_big_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品分类名称',
  `num` int(11) DEFAULT NULL COMMENT '排序序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_big_type` */

insert  into `dq_big_type`(`id`,`name`,`num`) values (1,'雪地鞋',1),(2,'短靴',2),(3,'马丁靴/军靴',3);

/*Table structure for table `dq_collect` */

DROP TABLE IF EXISTS `dq_collect`;

CREATE TABLE `dq_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `product_id` int(11) DEFAULT NULL COMMENT '产品id',
  `join_time` datetime DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`),
  KEY `FK_dq_collect` (`user_id`),
  CONSTRAINT `FK_dq_collect` FOREIGN KEY (`user_id`) REFERENCES `dq_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_collect` */

insert  into `dq_collect`(`id`,`user_id`,`product_id`,`join_time`) values (1,1,1,'2017-01-23 09:22:05'),(2,1,61,'2017-01-23 09:22:05'),(3,2,1,'2017-01-23 09:22:05'),(4,2,61,'2017-01-23 09:22:05');

/*Table structure for table `dq_group_order` */

DROP TABLE IF EXISTS `dq_group_order`;

CREATE TABLE `dq_group_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单号',
  `sendtimes` datetime DEFAULT NULL COMMENT '快递时间',
  `paytimes` datetime DEFAULT NULL COMMENT '支付时间',
  `join_time` datetime DEFAULT NULL COMMENT '下单时间',
  `status` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单状态',
  `total_price` double DEFAULT NULL COMMENT '实付款',
  `mail` double DEFAULT NULL COMMENT '邮费',
  `user_ticket_id` int(11) DEFAULT NULL COMMENT '优惠券id',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货地址',
  `youbian` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮编',
  `tel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `express` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '快递号',
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物流编码',
  `message` mediumtext COLLATE utf8mb4_unicode_ci COMMENT '留言',
  `payType` int(11) DEFAULT NULL COMMENT '支付类型',
  `appId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `timeStamp` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nonceStr` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `packageValue` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `signType` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `paySign` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sendUrl` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_group_order` */

insert  into `dq_group_order`(`id`,`order_num`,`sendtimes`,`paytimes`,`join_time`,`status`,`total_price`,`mail`,`user_ticket_id`,`address`,`youbian`,`tel`,`name`,`user_id`,`express`,`code`,`message`,`payType`,`appId`,`timeStamp`,`nonceStr`,`packageValue`,`signType`,`paySign`,`sendUrl`) values (1,'1484883746839','2017-01-20 11:20:01','2017-01-20 11:20:01','2017-01-20 11:20:01','4',100,10,1,'浙江省杭州市滨江区滨文路12','31000','15869155845','张月峰',1,'2343453454534','2343','你好你好你好你好你好你好你好你好',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'1484883723434','2017-01-20 11:20:01','2017-01-20 11:20:01','2017-01-20 11:20:01','4',200,20,1,'浙江省杭州市滨江区滨文路12','31000','18768143568','杨俊',2,'2343453454534','2343','你好你好你好你好你好你好你好你好',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `dq_integral_order` */

DROP TABLE IF EXISTS `dq_integral_order`;

CREATE TABLE `dq_integral_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单号',
  `sendtimes` datetime DEFAULT NULL COMMENT '快递时间',
  `join_time` datetime DEFAULT NULL COMMENT '下单时间',
  `status` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单状态',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收获地址',
  `youbian` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮编',
  `tel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `express` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '快递号',
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物流编码',
  `message` mediumtext COLLATE utf8mb4_unicode_ci COMMENT '留言',
  `integral` int(11) DEFAULT NULL COMMENT '产品消费积分',
  `integral_product_id` int(11) DEFAULT NULL COMMENT '积分产品id',
  `num` int(11) DEFAULT NULL COMMENT '产品数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_integral_order` */

insert  into `dq_integral_order`(`id`,`order_num`,`sendtimes`,`join_time`,`status`,`address`,`youbian`,`tel`,`name`,`user_id`,`express`,`code`,`message`,`integral`,`integral_product_id`,`num`) values (1,'23434231123','2017-01-20 11:20:01','2017-01-20 11:20:01','4','浙江省杭州市滨江区滨文路12','31000','15869155845','张月峰',1,'2343453454534','234311','你好',220,12,2);

/*Table structure for table `dq_integral_product` */

DROP TABLE IF EXISTS `dq_integral_product`;

CREATE TABLE `dq_integral_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `details` mediumtext COLLATE utf8mb4_unicode_ci COMMENT '商品详情描述',
  `imgUrl` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品照片',
  `join_time` datetime DEFAULT NULL COMMENT '创建时间',
  `isOnline` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否上架',
  `nums` int(11) DEFAULT NULL COMMENT '销量',
  `delflag` int(11) DEFAULT NULL COMMENT '删除标记',
  `integral` int(11) DEFAULT NULL COMMENT '兑换商品所需积分',
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_integral_product` */

insert  into `dq_integral_product`(`id`,`name`,`details`,`imgUrl`,`join_time`,`isOnline`,`nums`,`delflag`,`integral`,`code`) values (12,'牛皮中跟马丁靴','牛皮中跟马丁靴','uploadstart/20170123_09572753.jpg','2017-02-10 04:36:30','0',0,1,100,'1022011003');

/*Table structure for table `dq_module` */

DROP TABLE IF EXISTS `dq_module`;

CREATE TABLE `dq_module` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '模板名称',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '模板路径',
  `id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pmid` int(11) DEFAULT NULL COMMENT '父模板id',
  `nflag` int(11) DEFAULT NULL COMMENT '模板级别',
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_module` */

insert  into `dq_module`(`mid`,`name`,`url`,`id`,`pmid`,`nflag`,`num`) values (1,'首页','#',NULL,NULL,0,1),(2,'业务','#',NULL,NULL,0,2),(3,'基本信息','#',NULL,NULL,0,3),(4,'用户浏览','#',NULL,NULL,0,7),(5,'管理员','#',NULL,NULL,0,8),(6,'欢迎您','right.html','main-menu',1,1,NULL),(7,'产品管理','product.html','product-menu',2,1,NULL),(8,'订单管理','order.html','order-menu',2,1,NULL),(9,'优惠券管理','ticket.html','ticket-menu',2,1,NULL),(11,'大类型','bigtype.html','bigtype.html',3,1,NULL),(12,'小类型','smalltype.html','smalltype-menu',3,1,NULL),(13,'类型','standard.html','standard-menu',3,1,NULL),(15,'用户管理','user.html','user-menu',4,1,NULL),(16,'管理员管理','admin.html','admin-menu\'',5,1,NULL),(17,'角色管理','role.html','role-menu',5,1,NULL),(18,'提现','withdraw.html','withdraw-menu',54,1,NULL),(19,'充值','balance.html','balance-menu',54,1,NULL),(20,'首页轮播图','picture1.html','picture1-menu',1,1,NULL),(21,'产品添加','/skuc/addProduct.do',NULL,7,2,NULL),(22,'产品修改','/skuc/addProduct.do',NULL,7,2,NULL),(23,'产品删除','/product/deleteProduct.do',NULL,7,2,NULL),(24,'产品审核','/product/changeStatus.do',NULL,7,2,NULL),(26,'上传图片','/picture/addPicture.do',NULL,7,2,NULL),(27,'审核图片','/picture/changeStatus.do',NULL,7,2,NULL),(28,'删除图片','/picture/deletePicture.do',NULL,7,2,NULL),(29,'添加优惠券','/ticket/addTicket.do',NULL,9,2,NULL),(30,'修改优惠券','/ticket/updateTicket.do',NULL,9,2,NULL),(31,'删除优惠券','/ticket/deleteTicket.do',NULL,9,2,NULL),(32,'添加大类型','/bigtype/addBigType.do',NULL,11,2,NULL),(33,'修改大类型','/bigtype/updateBigType.do',NULL,11,2,NULL),(34,'删除大类型','/bigtype/deleteBigType.do',NULL,11,2,NULL),(35,'添加小类型','/smalltype/addSmallType.do',NULL,12,2,NULL),(36,'修改小类型','/smalltype/updateSmallType.do',NULL,12,2,NULL),(37,'删除小类型','/smalltype/deleteSmallType.do',NULL,12,2,NULL),(38,'添加管理员','/admin/addAdmin.do',NULL,16,2,NULL),(39,'修改管理员','/admin/updateAdmin.do',NULL,16,2,NULL),(40,'删除管理员','/admin/deleteAdmin.do',NULL,16,2,NULL),(41,'添加角色','/role/addRole.do',NULL,17,2,NULL),(42,'修改角色','/role/updateRole.do',NULL,17,2,NULL),(43,'角色删除','/role/deleteRole.do',NULL,17,2,NULL),(44,'设置权限','/module/setModules.do',NULL,17,2,NULL),(45,'添加类型','/standard/addStandard.do',NULL,13,2,NULL),(46,'修改类型','/standard/updateStandard.do',NULL,13,2,NULL),(47,'删除类型','/standard/deleteStandard.do',NULL,13,2,NULL),(48,'积分信息','#',NULL,NULL,0,4),(49,'积分商品','integral_product.html','inte_pro-menu',48,1,NULL),(50,'积分商品添加','/integral_product/addProduct.do',NULL,49,2,NULL),(51,'积分商品修改','/integral_product/updateProduct.do',NULL,49,2,NULL),(52,'积分商品删除','/integral_product/deleteProduct.do',NULL,49,2,NULL),(53,'积分订单','integral_order.html','inte_order-menu',48,1,NULL),(54,'交易管理','#',NULL,NULL,0,5),(55,'统计管理','#',NULL,NULL,0,6),(56,'统计概况','totalCount.html','statistics-menu',55,1,NULL);

/*Table structure for table `dq_orders` */

DROP TABLE IF EXISTS `dq_orders`;

CREATE TABLE `dq_orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_group_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL COMMENT '产品id',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `sku_id` int(11) DEFAULT NULL COMMENT 'sku属性id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_orders` */

insert  into `dq_orders`(`id`,`order_group_id`,`product_id`,`num`,`sku_id`) values (1,1,1,20,32),(2,1,61,30,35),(3,1,60,40,30),(4,2,1,30,33),(5,2,61,40,36),(6,2,60,50,31),(7,2,1,20,35),(8,2,1,50,33);

/*Table structure for table `dq_picture` */

DROP TABLE IF EXISTS `dq_picture`;

CREATE TABLE `dq_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `imgUrl` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品照片路径',
  `type` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片类型',
  `join_time` datetime DEFAULT NULL COMMENT '图片上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_picture` */

insert  into `dq_picture`(`id`,`product_id`,`imgUrl`,`type`,`join_time`) values (10,NULL,'uploadstart/20170123_10370554.jpg','2','2017-01-23 10:37:07'),(11,NULL,'uploadstart/20170123_10371559.jpg','2','2017-01-23 10:37:17'),(12,NULL,'uploadstart/20170123_10372668.jpg','2','2017-01-23 10:37:28'),(13,61,'uploadstart/20170123_10375196.jpg','1','2017-01-23 10:37:52'),(17,NULL,'/uploadstart/1485152063255.jpg','2','2017-01-23 14:14:25'),(18,2,'/uploadstart/1486664974726.jpg','1','2017-02-10 02:29:36'),(19,NULL,'','2','2017-02-10 03:01:29'),(20,11,'/uploadstart/1486670942288.jpg','1','2017-02-10 04:09:03');

/*Table structure for table `dq_product` */

DROP TABLE IF EXISTS `dq_product`;

CREATE TABLE `dq_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `details` mediumtext COLLATE utf8mb4_unicode_ci COMMENT '商品详情描述',
  `imgUrl` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品照片',
  `small_type_id` int(11) DEFAULT NULL COMMENT '商品小分类id',
  `big_type_id` int(11) DEFAULT NULL COMMENT '商品大分类id',
  `standard_id` int(11) DEFAULT NULL COMMENT '类型id',
  `join_time` datetime DEFAULT NULL COMMENT '创建时间',
  `isOnline` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否上架',
  `mail` double DEFAULT NULL COMMENT '邮费',
  `nums` int(11) DEFAULT NULL COMMENT '销量',
  `delflag` int(11) DEFAULT NULL COMMENT '删除标记',
  `num` int(11) DEFAULT NULL COMMENT '排序值',
  `f_price` double DEFAULT NULL COMMENT '现价',
  `o_price` double DEFAULT NULL COMMENT '原价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_product` */

insert  into `dq_product`(`id`,`name`,`details`,`imgUrl`,`small_type_id`,`big_type_id`,`standard_id`,`join_time`,`isOnline`,`mail`,`nums`,`delflag`,`num`,`f_price`,`o_price`) values (1,'牛皮中筒靴','111','uploadstart/20170123_09572753.jpg',3,2,2,'2017-01-13 09:47:47','1',19,200,1,10,NULL,NULL),(59,'yuefeng','11','uploadstart/20170120_0905066.jpg',12,1,2,'2017-01-20 09:05:32','0',11,NULL,2,NULL,NULL,NULL),(60,'yuefeng','11','uploadstart/20170120_09060151.jpg',10,3,2,'2017-01-20 09:05:33','1',22,NULL,2,NULL,NULL,NULL),(61,'牛皮中跟马丁靴','222','uploadstart/20170123_09570912.jpg',8,3,1,'2017-01-20 11:54:28','0',10,NULL,1,22,1,1),(62,'111','111','/uploadstart/1485155419441.jpg',2,2,2,'2017-01-23 15:10:39','1',11,NULL,2,11,NULL,NULL),(63,'lalaai','222','/uploadstart/1486194546383.jpg',2,2,2,'2017-02-04 15:49:44','1',10,NULL,2,10,NULL,NULL),(64,'aiaiai','111','/uploadstart/1486196580009.jpg',13,1,2,'2017-02-04 16:23:27','1',1,NULL,2,1,1,1),(65,'aiaiai','111','/uploadstart/1486196580009.jpg',13,1,2,'2017-02-04 16:23:41','1',1,NULL,2,1,11,1),(66,'11','1','/uploadstart/1486359873343.jpg',3,2,2,'2017-02-06 13:44:52','1',1,NULL,2,1,1,1),(67,'11','1','/uploadstart/1486668498024.png',12,1,2,'2017-02-10 03:28:37','1',11,NULL,2,11,1,1);

/*Table structure for table `dq_role` */

DROP TABLE IF EXISTS `dq_role`;

CREATE TABLE `dq_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_role` */

insert  into `dq_role`(`id`,`name`) values (1,'超级管理员'),(3,'普通管理员');

/*Table structure for table `dq_role_module` */

DROP TABLE IF EXISTS `dq_role_module`;

CREATE TABLE `dq_role_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `module_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=437 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_role_module` */

insert  into `dq_role_module`(`id`,`role_id`,`module_id`) values (116,3,1),(117,3,6),(118,3,20),(119,3,2),(120,3,7),(121,3,21),(122,3,22),(123,3,23),(124,3,24),(125,3,25),(126,3,26),(127,3,27),(128,3,28),(129,3,8),(130,3,9),(131,3,29),(132,3,30),(133,3,31),(134,3,3),(135,3,11),(136,3,32),(137,3,33),(138,3,34),(139,3,12),(140,3,35),(141,3,36),(142,3,37),(143,3,4),(144,3,15),(145,3,45),(146,3,46),(147,3,47),(148,3,18),(149,3,19),(150,3,5),(151,3,16),(152,3,38),(153,3,39),(154,3,40),(155,3,17),(156,3,41),(157,3,42),(158,3,43),(159,3,44),(383,1,1),(384,1,6),(385,1,20),(386,1,2),(387,1,7),(388,1,21),(389,1,22),(390,1,23),(391,1,24),(392,1,26),(393,1,27),(394,1,28),(395,1,8),(396,1,9),(397,1,29),(398,1,30),(399,1,31),(400,1,3),(401,1,11),(402,1,32),(403,1,33),(404,1,34),(405,1,12),(406,1,35),(407,1,36),(408,1,37),(409,1,4),(410,1,15),(411,1,18),(412,1,19),(413,1,5),(414,1,16),(415,1,38),(416,1,39),(417,1,40),(418,1,17),(419,1,41),(420,1,42),(421,1,43),(422,1,44),(423,1,13),(424,1,45),(425,1,46),(426,1,47),(428,1,48),(429,1,49),(430,1,50),(431,1,51),(432,1,52),(433,1,53),(434,1,54),(435,1,55),(436,1,56);

/*Table structure for table `dq_shopcar` */

DROP TABLE IF EXISTS `dq_shopcar`;

CREATE TABLE `dq_shopcar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_shopcar` */

insert  into `dq_shopcar`(`id`,`user_id`) values (1,1),(2,2);

/*Table structure for table `dq_shopcar_product` */

DROP TABLE IF EXISTS `dq_shopcar_product`;

CREATE TABLE `dq_shopcar_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopcar_id` int(11) DEFAULT NULL COMMENT '购物车id',
  `product_id` int(11) DEFAULT NULL COMMENT '产品id',
  `sku_id` int(11) DEFAULT NULL,
  `join_time` datetime DEFAULT NULL COMMENT '添加时间',
  `number` int(11) DEFAULT NULL COMMENT '产品数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_shopcar_product` */

insert  into `dq_shopcar_product`(`id`,`shopcar_id`,`product_id`,`sku_id`,`join_time`,`number`) values (1,1,1,32,'2017-01-22 15:23:05',20),(2,1,61,35,'2017-01-22 15:23:05',40),(3,2,1,33,'2017-01-22 15:23:05',30),(4,2,61,36,'2017-01-22 15:23:05',50),(5,2,61,35,'2017-01-22 15:23:05',40);

/*Table structure for table `dq_small_type` */

DROP TABLE IF EXISTS `dq_small_type`;

CREATE TABLE `dq_small_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '小分类名称',
  `big_type_id` int(11) DEFAULT NULL COMMENT '大分类id',
  `num` int(11) DEFAULT NULL COMMENT '排列序号',
  `isHot` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否热门',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_small_type` */

insert  into `dq_small_type`(`id`,`name`,`big_type_id`,`num`,`isHot`) values (2,'低跟短靴',2,3,'1'),(3,'中跟短靴',2,4,'0'),(4,'内增高短靴',2,5,'0'),(5,'高跟短靴',2,6,'0'),(6,'坡跟短靴',2,7,'1'),(7,'低跟马丁靴',3,8,'1'),(8,'中跟马丁靴',3,9,'1'),(9,'内增高马丁靴',3,10,'1'),(10,'高跟马丁靴',3,11,'1'),(12,'111',1,111,NULL),(13,'111',1,11,'1'),(14,'22',1,22,NULL);

/*Table structure for table `dq_standard` */

DROP TABLE IF EXISTS `dq_standard`;

CREATE TABLE `dq_standard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_standard` */

insert  into `dq_standard`(`id`,`name`) values (1,'无'),(2,'2017热卖榜单'),(3,'2017掌柜推荐'),(4,'2017休闲鞋会场');

/*Table structure for table `dq_ticket` */

DROP TABLE IF EXISTS `dq_ticket`;

CREATE TABLE `dq_ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` float NOT NULL COMMENT '优惠券面额',
  `start_time` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  `details` text COMMENT '优惠券说明',
  `num` int(11) NOT NULL DEFAULT '0' COMMENT '优惠券数量',
  `unum` int(11) NOT NULL DEFAULT '1' COMMENT '每个用户可领数量',
  `minprice` float NOT NULL DEFAULT '0' COMMENT '最低使用金额',
  `state` int(11) NOT NULL DEFAULT '1' COMMENT '状态标记。1可领，2已领完',
  `flag` int(11) NOT NULL DEFAULT '1' COMMENT '状态标记。1未过期，2已过期',
  `delflag` int(11) NOT NULL DEFAULT '1' COMMENT '删除标记',
  `join_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `dq_ticket` */

insert  into `dq_ticket`(`id`,`name`,`price`,`start_time`,`end_time`,`details`,`num`,`unum`,`minprice`,`state`,`flag`,`delflag`,`join_time`) values (1,'元旦红包',20,'2017-01-26','2017-01-26','线上购物使用',99999999,5,100,1,3,1,'2017-01-19 12:41:43'),(2,'新春红包',40,'2017-01-26','2017-02-27','线上购物使用',99999999,5,200,1,2,1,'2017-01-20 11:58:07'),(3,'聖誕红包',50,'2017-01-26','2017-02-27','线上购物使用',999999,5,200,1,2,1,'2017-01-20 11:58:07');

/*Table structure for table `dq_user` */

DROP TABLE IF EXISTS `dq_user`;

CREATE TABLE `dq_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户手机号',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登陆密码',
  `openId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信openId',
  `imgUrl` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信头像路径',
  `integral` int(11) DEFAULT NULL COMMENT '用户所有积分',
  `nickname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `join_time` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_user` */

insert  into `dq_user`(`id`,`tel`,`password`,`openId`,`imgUrl`,`integral`,`nickname`,`join_time`) values (1,'18768143568','111',NULL,'http://wx.qlogo.cn/mmopen/5L8bhP5dIqFoh3KRUgnxOOD7liavWCaXFVNaV3G7tc0jHojI4lagZ8nsQcJBLqutHe6WDT5vLYefgbjrpTDZlKs4t2zg7wibtT/0',500,'雪域请竹','2017-01-12 14:25:50'),(2,'15869155845','111',NULL,'http://wx.qlogo.cn/mmopen/B7RZpDtiaNBZaLHURXzljwJ2wGbEGnk57NiabmEFr9niblLXfMbO7DIZMStEU1MDJlX6cUklms1xxERIkVMchT9mftIx6rKxuic3/0',1000,'Mr.Z','2017-01-12 14:25:50');

/*Table structure for table `dq_user_ticket` */

DROP TABLE IF EXISTS `dq_user_ticket`;

CREATE TABLE `dq_user_ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `ticket_id` int(11) DEFAULT NULL COMMENT '优惠券id',
  `number` int(11) DEFAULT NULL COMMENT '已领优惠券数量',
  `use_num` int(11) DEFAULT NULL COMMENT '已使用的张数',
  `isUsed` int(11) DEFAULT NULL COMMENT '是否已用完',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_user_ticket` */

insert  into `dq_user_ticket`(`id`,`user_id`,`ticket_id`,`number`,`use_num`,`isUsed`) values (1,1,1,5,3,1),(2,1,2,5,5,2),(3,1,3,5,1,1),(5,2,1,5,1,1),(6,2,2,5,5,2),(7,2,3,5,3,1);

/*Table structure for table `dq_withdraw` */

DROP TABLE IF EXISTS `dq_withdraw`;

CREATE TABLE `dq_withdraw` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `news` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '提现消息',
  `join_time` date DEFAULT NULL COMMENT '提现时间',
  `flag` int(11) DEFAULT NULL COMMENT '提现状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dq_withdraw` */

insert  into `dq_withdraw`(`id`,`user_id`,`news`,`join_time`,`flag`) values (1,1,'在2017-01-22时要提现100元钱','2017-01-22',2),(2,2,'在2017-01-22时要提现200元钱','2017-01-22',1),(3,1,'在2017-01-22时要提现500元钱','2017-01-22',1),(4,2,'在2017-01-22时要提现600元钱','2017-01-22',1);

/*Table structure for table `proatt` */

DROP TABLE IF EXISTS `proatt`;

CREATE TABLE `proatt` (
  `pid` int(11) NOT NULL,
  `att_id` int(11) NOT NULL,
  `sort` int(11) DEFAULT NULL,
  `delflag` int(11) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  PRIMARY KEY (`pid`,`att_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `proatt` */

insert  into `proatt`(`pid`,`att_id`,`sort`,`delflag`,`addtime`) values (0,1,1,1,'2017-02-08 10:52:29'),(0,2,2,1,'2017-02-08 10:52:29'),(0,4,3,1,'2017-02-08 10:52:29'),(1,1,1,1,'2017-01-23 15:08:21'),(1,2,2,1,'2017-01-23 15:08:21'),(1,4,3,1,'2017-01-23 15:08:21'),(53,1,1,1,'2017-01-19 15:41:18'),(53,2,2,1,'2017-01-19 15:41:18'),(53,4,3,1,'2017-01-19 15:41:18'),(58,1,1,1,'2017-01-19 17:10:12'),(58,2,2,1,'2017-01-19 17:10:12'),(58,4,3,1,'2017-01-19 17:10:12'),(59,1,1,1,'2017-01-20 09:05:32'),(59,2,2,1,'2017-01-20 09:05:32'),(59,4,3,1,'2017-01-20 09:05:32'),(60,1,1,1,'2017-01-20 09:30:28'),(60,2,2,1,'2017-01-20 09:30:28'),(60,4,3,1,'2017-01-20 09:30:28'),(61,1,1,1,'2017-02-08 11:06:00'),(61,2,2,1,'2017-02-08 11:06:00'),(61,4,3,1,'2017-02-08 11:06:00'),(62,1,1,1,'2017-01-23 15:10:39'),(62,2,2,1,'2017-01-23 15:10:39'),(63,1,1,1,'2017-02-04 15:50:01'),(63,2,2,1,'2017-02-04 15:50:01'),(64,1,1,1,'2017-02-04 16:23:27'),(65,1,1,1,'2017-02-04 16:23:41'),(66,1,1,1,'2017-02-06 14:36:54'),(66,2,2,1,'2017-02-06 14:36:54'),(67,1,1,1,'2017-02-10 03:28:37'),(67,2,2,1,'2017-02-10 03:28:37'),(67,4,3,1,'2017-02-10 03:28:37');

/*Table structure for table `proattr` */

DROP TABLE IF EXISTS `proattr`;

CREATE TABLE `proattr` (
  `pid` int(11) NOT NULL,
  `att_id` int(11) NOT NULL,
  `attr_id` int(11) NOT NULL,
  `delflag` int(11) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  PRIMARY KEY (`pid`,`att_id`,`attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `proattr` */

insert  into `proattr`(`pid`,`att_id`,`attr_id`,`delflag`,`addtime`) values (0,1,1,1,'2017-02-08 10:52:29'),(0,1,2,1,'2017-02-08 10:52:29'),(0,2,5,1,'2017-02-08 10:52:29'),(0,2,6,1,'2017-02-08 10:52:29'),(0,4,29,1,'2017-02-08 10:52:29'),(0,4,30,1,'2017-02-08 10:52:29'),(1,1,1,1,'2017-01-23 15:08:21'),(1,2,5,1,'2017-01-23 15:08:21'),(1,2,6,1,'2017-01-23 15:08:21'),(1,2,7,1,'2017-01-23 15:08:21'),(1,4,30,1,'2017-01-23 15:08:21'),(53,1,1,1,'2017-01-19 15:41:18'),(53,1,2,1,'2017-01-19 15:41:18'),(53,2,5,1,'2017-01-19 15:41:18'),(53,2,6,1,'2017-01-19 15:41:18'),(53,4,29,1,'2017-01-19 15:41:18'),(58,1,27,1,'2017-01-19 17:10:12'),(58,2,26,1,'2017-01-19 17:10:12'),(58,4,29,1,'2017-01-19 17:10:12'),(58,4,30,1,'2017-01-19 17:10:12'),(59,1,1,1,'2017-01-20 09:05:32'),(59,2,5,1,'2017-01-20 09:05:32'),(59,4,29,1,'2017-01-20 09:05:32'),(60,1,27,1,'2017-01-20 09:30:28'),(60,2,26,1,'2017-01-20 09:30:28'),(60,4,30,1,'2017-01-20 09:30:28'),(61,1,1,1,'2017-02-08 11:06:00'),(61,1,2,1,'2017-02-08 11:06:00'),(61,2,5,1,'2017-02-08 11:06:00'),(61,2,6,1,'2017-02-08 11:06:00'),(61,4,29,1,'2017-02-08 11:06:00'),(61,4,30,1,'2017-02-08 11:06:00'),(62,1,1,1,'2017-01-23 15:10:39'),(62,1,2,1,'2017-01-23 15:10:39'),(62,2,5,1,'2017-01-23 15:10:39'),(62,2,6,1,'2017-01-23 15:10:39'),(63,1,1,1,'2017-02-04 15:50:01'),(63,1,2,1,'2017-02-04 15:50:01'),(63,2,5,1,'2017-02-04 15:50:01'),(64,1,1,1,'2017-02-04 16:23:27'),(64,1,2,1,'2017-02-04 16:23:27'),(65,1,1,1,'2017-02-04 16:23:41'),(65,1,2,1,'2017-02-04 16:23:41'),(66,1,2,1,'2017-02-06 14:36:54'),(66,2,6,1,'2017-02-06 14:36:54'),(67,1,2,1,'2017-02-10 03:28:37'),(67,2,6,1,'2017-02-10 03:28:37'),(67,4,30,1,'2017-02-10 03:28:37');

/*Table structure for table `skuinfo` */

DROP TABLE IF EXISTS `skuinfo`;

CREATE TABLE `skuinfo` (
  `skuid` int(11) NOT NULL AUTO_INCREMENT,
  `attr_ids` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `attr_titles` text COLLATE utf8mb4_unicode_ci,
  `number` int(11) DEFAULT NULL COMMENT '库存',
  `original_price` double DEFAULT NULL COMMENT '原价',
  `favourable_price` double DEFAULT NULL COMMENT '现价',
  `limit_number` int(11) DEFAULT NULL COMMENT '限购',
  `integral` int(11) DEFAULT NULL COMMENT '积分',
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品编码',
  `pid` int(11) DEFAULT NULL,
  `delflag` int(11) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  PRIMARY KEY (`skuid`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `skuinfo` */

insert  into `skuinfo`(`skuid`,`attr_ids`,`attr_titles`,`number`,`original_price`,`favourable_price`,`limit_number`,`integral`,`code`,`pid`,`delflag`,`addtime`) values (21,'1-5-29','尺寸:S;颜色:红;性别:男',200,100,180,100,200,'101111',53,1,'2017-01-19 15:27:26'),(22,'2-5-29','尺寸:M;颜色:红;性别:男',200,100,140,100,200,'101111',53,1,'2017-01-19 15:31:03'),(23,'1-6-29','尺寸:S;颜色:黄;性别:男',200,100,160,100,200,'101111',53,1,'2017-01-19 15:39:29'),(24,'2-6-29','尺寸:M;颜色:黄;性别:男',200,100,120,100,200,'101111',53,1,'2017-01-19 15:39:29'),(25,'1-5-29','尺寸:S;颜色:红;性别:男',100,300,400,200,100,'500',58,2,'2017-01-19 17:03:22'),(26,'1-5-30','尺寸:S;颜色:红;性别:女',200,100,170,100,200,'101111',58,1,'2017-01-19 17:03:22'),(27,'27-26-29','尺寸:red;颜色:11;性别:男',11,11,11,11,100,'11',58,1,'2017-01-19 17:04:01'),(28,'27-26-30','尺寸:red;颜色:11;性别:女',1,3,4,2,100,'5',58,1,'2017-01-19 17:04:01'),(29,'1-5-29','尺寸:S;颜色:红;性别:男',1,3,4,2,100,'5',59,1,'2017-01-20 09:05:32'),(30,'1-5-29','尺寸:S;颜色:红;性别:男',1,3,4,2,100,'5',60,2,'2017-01-20 09:05:33'),(31,'27-26-30','尺寸:red;颜色:11;性别:女',5,3,2,4,100,'1',60,1,'2017-01-20 09:06:27'),(32,'1-5-30','尺寸:S;颜色:红;性别:女',200,100,90,100,100,'101111',1,1,'2017-01-20 10:07:52'),(33,'1-6-30','尺寸:S;颜色:黄;性别:女',200,100,150,100,200,'101111',1,1,'2017-01-20 10:07:52'),(34,'1-7-30','尺寸:S;颜色:蓝;性别:女',100,20,10,20,100,'100010',1,1,'2017-01-20 11:53:13'),(35,'1-5-29','尺寸:S;颜色:红;性别:男',200,100,180,100,200,'101111',61,1,'2017-01-20 11:54:28'),(36,'1-5-30','尺寸:S;颜色:红;性别:女',200,100,170,100,200,'101111',61,1,'2017-01-20 11:54:28'),(37,'1-6-29','尺寸:S;颜色:黄;性别:男',200,100,160,100,200,'101111',61,1,'2017-01-20 11:54:28'),(38,'1-6-30','尺寸:S;颜色:黄;性别:女',200,100,150,100,200,'101111',61,1,'2017-01-20 11:54:28'),(39,'2-5-29','尺寸:M;颜色:红;性别:男',200,100,140,100,200,'101111',61,1,'2017-01-20 11:54:28'),(40,'2-5-30','尺寸:M;颜色:红;性别:女',200,100,130,100,200,'101111',61,1,'2017-01-20 11:54:28'),(41,'2-6-29','尺寸:M;颜色:黄;性别:男',200,100,120,100,200,'101111',61,1,'2017-01-20 11:54:28'),(42,'2-6-30','尺寸:M;颜色:黄;性别:女',1,1,1,1,1,'1',61,1,'2017-01-20 11:54:28'),(43,'1-5','尺寸:S;颜色:红',11,11,11,11,100,'11',62,1,'2017-01-23 15:10:39'),(44,'1-6','尺寸:S;颜色:黄',11,11,11,11,100,'11',62,1,'2017-01-23 15:10:39'),(45,'2-5','尺寸:M;颜色:红',11,11,11,11,100,'11',62,1,'2017-01-23 15:10:39'),(46,'2-6','尺寸:M;颜色:黄',1,1,1,1,1,'1',62,1,'2017-01-23 15:10:39'),(47,'1-5','尺寸:S;颜色:红',1,1,1,1,2,'1',63,1,'2017-02-04 15:49:44'),(48,'2-5','尺寸:M;颜色:红',1,1,1,1,2,'1',63,1,'2017-02-04 15:49:44'),(49,'1','尺寸:S',1,1,1,1,11,'11',64,1,'2017-02-04 16:23:27'),(50,'2','尺寸:M',1,1,1,1,12,'11',64,1,'2017-02-04 16:23:27'),(51,'1','尺寸:S',1,1,11,1,11,'11',65,1,'2017-02-04 16:23:41'),(52,'2','尺寸:M',1,1,22,1,12,'11',65,1,'2017-02-04 16:23:41'),(53,'2-6','尺寸:M;颜色:黄',1,1,1,1,1,'1',66,1,'2017-02-06 13:44:52'),(54,'2-6-30','尺寸:M;颜色:黄;性别:女',1,1,1,1,1,'1',67,1,'2017-02-10 03:28:37');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
