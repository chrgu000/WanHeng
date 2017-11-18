/*
Navicat MySQL Data Transfer

Source Server         : 幼儿园
Source Server Version : 50173
Source Host           : 106.15.32.105:3306
Source Database       : kindergarten

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-11-08 16:15:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for kg_admin
-- ----------------------------
DROP TABLE IF EXISTS `kg_admin`;
CREATE TABLE `kg_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `join_time` datetime DEFAULT NULL COMMENT '注册时间',
  `garden_id` int(11) DEFAULT NULL COMMENT '园区id',
  `ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'ip地址',
  `token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of kg_admin
-- ----------------------------
INSERT INTO `kg_admin` VALUES ('1', 'admin', '698D51A19D8A121CE581499D7B701668', '1', null, null, '183.129.153.186', '33cfb0a572be4bcf9f536421be4ba405');
INSERT INTO `kg_admin` VALUES ('2', 'admin2', '698D51A19D8A121CE581499D7B701668', '1', '2017-02-27 16:09:23', null, '125.118.7.124', 'f0ef6b029a1146e7bace63b96cfc96d2');
INSERT INTO `kg_admin` VALUES ('3', 'admin3', '96E79218965EB72C92A549DD5A330112', '1', '2017-03-15 15:05:44', null, '60.186.192.53', 'bf3952fb765046bba8b6445c84a54bfa');
INSERT INTO `kg_admin` VALUES ('4', 'yangjun', '698D51A19D8A121CE581499D7B701668', '2', '2017-03-15 15:26:19', null, '115.205.0.116', 'e0414052e277496db75addc86bc13c47');
INSERT INTO `kg_admin` VALUES ('5', '123', 'F59BD65F7EDAFB087A81D4DCA06C4910', '1', '2017-03-20 10:18:19', null, null, null);
INSERT INTO `kg_admin` VALUES ('6', '123456', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '2017-03-20 12:54:06', null, '125.118.7.124', 'f980acf31d6a4c41a01e40fd2bdbc426');
INSERT INTO `kg_admin` VALUES ('7', '1234567', 'FCEA920F7412B5DA7BE0CF42B8C93759', '2', '2017-03-20 12:54:17', null, '125.118.7.124', '7a469a2ab702405b82d4e38e10ff2692');

-- ----------------------------
-- Table structure for kg_admin_garden
-- ----------------------------
DROP TABLE IF EXISTS `kg_admin_garden`;
CREATE TABLE `kg_admin_garden` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(11) DEFAULT NULL,
  `garden_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of kg_admin_garden
-- ----------------------------
INSERT INTO `kg_admin_garden` VALUES ('58', '4', '6');
INSERT INTO `kg_admin_garden` VALUES ('57', '4', '3');
INSERT INTO `kg_admin_garden` VALUES ('56', '4', '2');

-- ----------------------------
-- Table structure for kg_baby
-- ----------------------------
DROP TABLE IF EXISTS `kg_baby`;
CREATE TABLE `kg_baby` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登陆密码',
  `garden_id` int(11) DEFAULT NULL COMMENT '园区id',
  `tel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `sex` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '性别',
  `birth_date` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '生日',
  `header_pic_path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像路径',
  `expectation` mediumtext COLLATE utf8_unicode_ci COMMENT '期望',
  `introduce` mediumtext COLLATE utf8_unicode_ci COMMENT '简介',
  `join_time` datetime DEFAULT NULL COMMENT '注册时间',
  `teacher_id` int(11) DEFAULT NULL COMMENT '老师id',
  `father_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '父亲姓名',
  `father_tel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '父亲联系方式',
  `mother_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '母亲姓名',
  `mother_tel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '母亲联系方式',
  `emergency_contact_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '紧急联系人姓名',
  `emergency_contact_tel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '紧急联系人联系方式',
  `state` int(11) DEFAULT NULL COMMENT '审核状态',
  `isStudy` int(11) DEFAULT NULL COMMENT '是否在读',
  `endDate` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '课程结束时间',
  `small_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '小名',
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '家庭住址',
  `content` mediumtext COLLATE utf8_unicode_ci COMMENT '过敏史/病史',
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '个人编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of kg_baby
-- ----------------------------
INSERT INTO `kg_baby` VALUES ('33', '桑大菜', 'D964173DC44DA83EEAFA3AEBBEE9A1A0', '1', '13685788505', '男', '2017-03-1', '/uploadstart/1488888207195236.jpg', null, '桑哦事哦吧哈哈哈吧嘻嘻嘻嘻', '2017-02-27 16:34:07', '41', '桑拿房', '13336180002', 'w w w w', '13685788505', '桑', '13337180002', '1', '1', '2019-05-08', null, null, null, null);
INSERT INTO `kg_baby` VALUES ('36', 'mlb', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '18767161991', '男', '2017-03-1', '/uploadstart/1488363155653477.gif', null, '我发给刚吃饭刚刚分布图拜托拜托拜托拜托把他把他把他把他挺好特别特别TVTV他个TVVR给他VRvTV通过TV他VRVR挺好听不听话\n', '2017-02-28 11:59:28', '45', 'mlb', '18767161991', 'mlb', '18767161991', 'mlb', '18767161991', '1', '1', '2018-11-21', null, null, null, null);
INSERT INTO `kg_baby` VALUES ('41', '佳佳', '5690DDDFA28AE085D23518A035707282', '1', '18006861299', '男', null, null, null, null, '2017-03-13 15:00:02', '47', null, null, null, null, null, null, '1', '1', '2018-08-24', null, null, null, null);
INSERT INTO `kg_baby` VALUES ('42', '李萱', '62E3315071617045D5587F465E9380A1', '1', '13867483023', '男', '2014-01-19', '/uploadstart/1490342141475.jpg', null, null, '2017-03-14 10:18:08', '47', '王群', '13757167731', '李萱', '13867483023', '张雁', '13588030518', '1', '1', '2018-09-24', 'Daniel', '杭州文一路378号嘉南公寓瀑水居5-3-402', '无', null);
INSERT INTO `kg_baby` VALUES ('43', '小鱼', '2B79A5946358F586B2117C401B2E80BA', '1', '13666727122', '男', '2015-08-24', '/uploadstart/1489458464509.jpg', null, null, '2017-03-14 10:18:34', '47', null, '15267091633', null, '13666727122', '佳佳', '18006861299', '1', '1', '2019-01-26', null, null, null, null);
INSERT INTO `kg_baby` VALUES ('46', '牛牛', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '13588844962', '男', null, null, null, null, '2017-03-20 10:18:58', '44', null, null, null, null, null, null, '0', '0', '2017-03-20', '牛牛', null, null, null);
INSERT INTO `kg_baby` VALUES ('52', '陆陆', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '13967116683', '男', null, null, null, null, '2017-04-20 11:24:26', '50', null, null, null, null, null, null, '1', '0', '2017-04-24', '陆陆', null, null, null);
INSERT INTO `kg_baby` VALUES ('53', '杨俊', 'AFA0511C134699AC9D6AC98BBF956225', '2', '18768143568', '男', null, null, null, null, '2017-05-04 11:01:23', null, null, null, null, null, null, null, '0', '1', null, '毛毛', null, null, '00021753');
INSERT INTO `kg_baby` VALUES ('54', '朱舒薇', '20917C851C4A54F2A054390DAC9085B7', '8', '18867103985', '男', null, null, null, null, '2017-08-23 19:42:25', null, null, null, null, null, null, null, '0', '0', '2017-09-5', '薇薇', null, null, 'null1754');
INSERT INTO `kg_baby` VALUES ('59', '张果', '96E79218965EB72C92A549DD5A330112', '8', '15869155845', '男', '2009-09-6', '/uploadstart/1504578632544.jpg', null, null, '2017-09-05 10:24:47', '67', null, null, null, null, null, null, '1', '1', '2018-05-01', '果果', null, null, '00071759');
INSERT INTO `kg_baby` VALUES ('60', '钟简熙', 'F4CF676496261E18F12CEA039CDACBF5', '14', '15868184587', '男', null, null, null, null, '2017-09-16 11:18:08', null, null, null, null, null, null, null, '0', '1', null, '简宝', null, null, '00131760');
INSERT INTO `kg_baby` VALUES ('61', '杨子谦', 'FE7BA39B308460D08EBE8CCA3EF9DBF4', '14', '18814826263', '男', null, null, null, null, '2017-09-16 16:53:31', null, null, null, null, null, null, null, '0', '1', null, '谦谦', null, null, '00131761');
INSERT INTO `kg_baby` VALUES ('62', 'QQ', '5690DDDFA28AE085D23518A035707282', '1', '15906814879', '男', null, null, null, null, '2017-09-20 15:59:48', '51', null, null, null, null, null, null, '1', '1', '2018-12-07', 'QQ', null, null, '00011762');
INSERT INTO `kg_baby` VALUES ('63', '朱彦铮', '53D4145A22741D0345697B5506518495', '14', '13305720882', '男', '2015-06-25', '/uploadstart/1510128535564.jpg', null, null, '2017-09-25 14:09:03', '65', '朱俊鑫', '15306720882', '顾冰', '13305720882', null, null, '1', '1', '2019-01-17', '夏天', null, null, '00131763');
INSERT INTO `kg_baby` VALUES ('64', '恺恺', '3EBEC6A2B43FE6FB8277C7FD864185B9', '8', '18268199479', '男', null, null, null, null, '2017-09-29 17:00:12', null, null, null, null, null, null, null, '0', '1', null, '恺恺', null, null, '00071764');
INSERT INTO `kg_baby` VALUES ('65', '丁蕙兰', 'E10ADC3949BA59ABBE56E057F20F883E', '12', '15658138326', '男', null, null, null, null, '2017-10-27 05:37:35', null, null, null, null, null, null, null, '0', '1', null, '阿蕙', null, null, '00111765');
INSERT INTO `kg_baby` VALUES ('66', '小番薯', 'E807F1FCF82D132F9BB018CA6738A19F', '12', '15325001882', '男', null, null, null, null, '2017-11-08 14:43:51', null, null, null, null, null, null, null, '0', '1', null, '苏王子', null, null, '00111766');
INSERT INTO `kg_baby` VALUES ('67', '管宏', '734B923E5707EBDBB375A0E26392D924', '3', '13388617079', '男', null, null, null, null, '2017-11-08 14:44:20', null, null, null, null, null, null, null, '0', '1', null, '宏宏', null, null, '00031767');
INSERT INTO `kg_baby` VALUES ('68', '秋刀鱼', '7D02217FFCA0E96659548691F24B6A4C', '1', '15757152760', '男', null, null, null, null, '2017-11-08 14:45:06', null, null, null, null, null, null, null, '0', '1', null, '鱼鱼', null, null, '00011768');
INSERT INTO `kg_baby` VALUES ('69', '小地瓜', 'E807F1FCF82D132F9BB018CA6738A19F', '14', '13388616979', '男', null, null, null, null, '2017-11-08 14:45:53', null, null, null, null, null, null, null, '0', '1', null, '苏王女', null, null, '00131769');
INSERT INTO `kg_baby` VALUES ('70', '李心蕾', 'A79672A6EEC263835A14E70938DF143F', '13', '18814880627', '女', '2014-07-7', null, null, null, '2017-11-08 14:45:59', null, null, null, null, null, null, null, '0', '1', null, '七七', null, null, '00121770');

-- ----------------------------
-- Table structure for kg_diary
-- ----------------------------
DROP TABLE IF EXISTS `kg_diary`;
CREATE TABLE `kg_diary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `diary_type_id` int(11) DEFAULT NULL COMMENT '工作日记类型',
  `path` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片路径',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师id',
  `create_time` date DEFAULT NULL COMMENT '上传时间',
  `content` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片说明',
  `min_path` varchar(255) DEFAULT NULL COMMENT '压缩图片路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of kg_diary
-- ----------------------------
INSERT INTO `kg_diary` VALUES ('42', '1', '/uploadstart/1489391359346177.jpg', '39', '2017-03-13', '', '/uploadstart-min/1489391359346177.jpg');
INSERT INTO `kg_diary` VALUES ('43', '1', '/uploadstart/1489459691007352.png', '47', '2017-03-14', '2017.3.14', '/uploadstart-min/1489459691007352.png');
INSERT INTO `kg_diary` VALUES ('68', '1', '/uploadstart/1489725469734175.jpg', '39', '2017-03-17', '今天宝宝表现很好', '/uploadstart-min/1489725469734175.jpg');
INSERT INTO `kg_diary` VALUES ('78', '1', '/uploadstart/1489984600946629.jpg', '39', '2017-03-20', '哈哈哈哈哈哈哈', '/uploadstart-min/1489984600946629.jpg');
INSERT INTO `kg_diary` VALUES ('79', '1', '/uploadstart/1489997853337343.jpg', '49', '2017-03-20', '：：：：：', '/uploadstart-min/1489997853337343.jpg');
INSERT INTO `kg_diary` VALUES ('91', '1', '/uploadstart/1490338806372494.jpg', '47', '2017-03-24', '3.24\n', '/uploadstart-min/1490338806372494.jpg');
INSERT INTO `kg_diary` VALUES ('92', '2', '/uploadstart/1490338867515262.jpg', '47', '2017-03-24', '', '/uploadstart-min/1490338867515262.jpg');
INSERT INTO `kg_diary` VALUES ('93', '2', '/uploadstart/1490338879407530.jpg', '47', '2017-03-24', '', '/uploadstart-min/1490338879407530.jpg');
INSERT INTO `kg_diary` VALUES ('95', '1', '/uploadstart/1504578726403158.jpg', '67', '2017-09-05', '国际经济', '/uploadstart-min/1504578726403158.jpg');

-- ----------------------------
-- Table structure for kg_diary_type
-- ----------------------------
DROP TABLE IF EXISTS `kg_diary_type`;
CREATE TABLE `kg_diary_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '工作日记类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of kg_diary_type
-- ----------------------------
INSERT INTO `kg_diary_type` VALUES ('1', '晨检表(每天)');
INSERT INTO `kg_diary_type` VALUES ('2', '午睡情况表(每天)');
INSERT INTO `kg_diary_type` VALUES ('3', '主教示范规划表(每周五)');
INSERT INTO `kg_diary_type` VALUES ('4', '助教参考引导表(每周五)');
INSERT INTO `kg_diary_type` VALUES ('5', '儿童工作示范确认表(每月)');
INSERT INTO `kg_diary_type` VALUES ('6', '会议记录表');
INSERT INTO `kg_diary_type` VALUES ('7', '家长沟通记录表(每周)');
INSERT INTO `kg_diary_type` VALUES ('8', '来离园签到表(每天)');

-- ----------------------------
-- Table structure for kg_footprint
-- ----------------------------
DROP TABLE IF EXISTS `kg_footprint`;
CREATE TABLE `kg_footprint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `baby_id` int(11) DEFAULT NULL COMMENT 'babyId',
  `isShit` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '是否大便',
  `isSiesta` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '是否午睡',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师id',
  `date` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '日期',
  `state` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '录入状态',
  `garden_id` int(11) DEFAULT NULL COMMENT '园区id',
  `siesta` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '午休情况',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1720 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of kg_footprint
-- ----------------------------
INSERT INTO `kg_footprint` VALUES ('119', '33', '否', '否', '41', '2017-03-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('126', '33', '否', '否', '41', '2017-03-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('133', '33', '否', '否', '41', '2017-03-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('140', '33', '否', '否', '41', '2017-03-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('147', '33', '否', '否', '41', '2017-03-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('154', '33', '否', '否', '41', '2017-03-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('158', '33', '否', '否', '41', '2017-03-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('163', '33', '否', '否', '41', '2017-03-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('168', '33', '否', '否', '41', '2017-03-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('173', '33', '否', '否', '41', '2017-03-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('176', '43', '否', '否', '47', '2017-03-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('182', '33', '否', '否', '41', '2017-03-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('183', '36', '否', '否', '45', '2017-03-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('185', '41', '否', '否', '47', '2017-03-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('186', '42', '否', '否', '47', '2017-03-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('187', '43', '否', '否', '47', '2017-03-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('190', '33', '否', '否', '41', '2017-03-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('191', '36', '否', '否', '45', '2017-03-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('193', '41', '否', '否', '47', '2017-03-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('194', '42', '否', '否', '47', '2017-03-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('195', '43', '否', '否', '47', '2017-03-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('199', '33', '否', '否', '41', '2017-03-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('200', '36', '否', '否', '45', '2017-03-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('202', '41', '否', '否', '47', '2017-03-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('203', '42', '否', '否', '47', '2017-03-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('204', '43', '否', '否', '47', '2017-03-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('208', '33', '否', '否', '41', '2017-03-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('209', '36', '否', '否', '45', '2017-03-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('211', '41', '否', '否', '47', '2017-03-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('212', '42', '否', '否', '47', '2017-03-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('213', '43', '是', '是', '47', '2017-03-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('217', '33', '否', '否', '41', '2017-03-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('218', '36', '否', '否', '45', '2017-03-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('220', '41', '否', '否', '47', '2017-03-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('221', '42', '否', '否', '47', '2017-03-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('222', '43', '否', '否', '47', '2017-03-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('227', '33', '否', '否', '41', '2017-03-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('228', '36', '否', '否', '45', '2017-03-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('230', '41', '否', '否', '47', '2017-03-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('231', '42', '否', '否', '47', '2017-03-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('232', '43', '否', '否', '47', '2017-03-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('237', '33', '否', '否', '41', '2017-03-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('238', '36', '否', '否', '45', '2017-03-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('240', '41', '否', '否', '47', '2017-03-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('241', '42', '否', '否', '47', '2017-03-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('242', '43', '否', '否', '47', '2017-03-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('247', '33', '否', '否', '41', '2017-03-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('248', '36', '否', '否', '45', '2017-03-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('250', '41', '否', '否', '47', '2017-03-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('251', '42', '否', '否', '47', '2017-03-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('252', '43', '否', '否', '47', '2017-03-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('257', '33', '否', '否', '41', '2017-03-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('258', '36', '否', '否', '45', '2017-03-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('260', '41', '否', '否', '47', '2017-03-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('261', '42', '否', '否', '47', '2017-03-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('262', '43', '否', '否', '47', '2017-03-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('267', '33', '否', '否', '41', '2017-04-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('268', '36', '否', '否', '45', '2017-04-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('270', '41', '否', '否', '47', '2017-04-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('271', '42', '否', '否', '47', '2017-04-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('272', '43', '否', '否', '47', '2017-04-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('277', '33', '否', '否', '41', '2017-04-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('278', '36', '否', '否', '45', '2017-04-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('280', '41', '否', '否', '47', '2017-04-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('281', '42', '否', '否', '47', '2017-04-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('282', '43', '否', '否', '47', '2017-04-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('287', '33', '否', '否', '41', '2017-04-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('288', '36', '否', '否', '45', '2017-04-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('290', '41', '否', '否', '47', '2017-04-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('291', '42', '否', '否', '47', '2017-04-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('292', '43', '否', '否', '47', '2017-04-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('297', '33', '否', '否', '41', '2017-04-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('298', '36', '否', '否', '45', '2017-04-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('300', '41', '否', '否', '47', '2017-04-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('301', '42', '否', '否', '47', '2017-04-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('302', '43', '否', '否', '47', '2017-04-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('307', '33', '否', '否', '41', '2017-04-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('308', '36', '否', '否', '45', '2017-04-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('310', '41', '否', '否', '47', '2017-04-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('311', '42', '否', '否', '47', '2017-04-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('312', '43', '否', '否', '47', '2017-04-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('317', '33', '否', '否', '41', '2017-04-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('318', '36', '否', '否', '45', '2017-04-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('320', '41', '否', '否', '47', '2017-04-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('321', '42', '否', '否', '47', '2017-04-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('322', '43', '否', '否', '47', '2017-04-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('327', '33', '否', '否', '41', '2017-04-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('328', '36', '否', '否', '45', '2017-04-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('330', '41', '否', '否', '47', '2017-04-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('331', '42', '否', '否', '47', '2017-04-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('332', '43', '否', '否', '47', '2017-04-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('337', '33', '否', '否', '41', '2017-04-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('338', '36', '否', '否', '45', '2017-04-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('340', '41', '否', '否', '47', '2017-04-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('341', '42', '否', '否', '47', '2017-04-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('342', '43', '否', '否', '47', '2017-04-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('347', '33', '否', '否', '41', '2017-04-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('348', '36', '否', '否', '45', '2017-04-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('350', '41', '否', '否', '47', '2017-04-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('351', '42', '否', '否', '47', '2017-04-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('352', '43', '否', '否', '47', '2017-04-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('357', '33', '否', '否', '41', '2017-04-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('358', '36', '否', '否', '45', '2017-04-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('361', '42', '否', '否', '47', '2017-04-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('362', '43', '否', '否', '47', '2017-04-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('367', '33', '否', '否', '41', '2017-04-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('368', '36', '否', '否', '45', '2017-04-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('370', '41', '否', '否', '47', '2017-04-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('371', '42', '否', '否', '47', '2017-04-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('372', '43', '否', '否', '47', '2017-04-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('377', '33', '否', '否', '41', '2017-04-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('378', '36', '否', '否', '45', '2017-04-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('380', '41', '否', '否', '47', '2017-04-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('381', '42', '否', '否', '47', '2017-04-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('382', '43', '否', '否', '47', '2017-04-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('387', '33', '否', '否', '41', '2017-04-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('388', '36', '否', '否', '45', '2017-04-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('390', '41', '否', '否', '47', '2017-04-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('391', '42', '否', '否', '47', '2017-04-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('392', '43', '否', '否', '47', '2017-04-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('397', '33', '否', '否', '41', '2017-04-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('398', '36', '否', '否', '45', '2017-04-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('400', '41', '否', '否', '47', '2017-04-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('401', '42', '否', '否', '47', '2017-04-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('402', '43', '否', '否', '47', '2017-04-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('407', '33', '否', '否', '41', '2017-04-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('408', '36', '否', '否', '45', '2017-04-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('410', '41', '否', '否', '47', '2017-04-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('411', '42', '否', '否', '47', '2017-04-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('412', '43', '否', '否', '47', '2017-04-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('416', '52', '否', '否', '50', '2017-04-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('418', '33', '否', '否', '41', '2017-04-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('419', '36', '否', '否', '45', '2017-04-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('421', '41', '否', '否', '47', '2017-04-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('422', '42', '否', '否', '47', '2017-04-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('423', '43', '否', '否', '47', '2017-04-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('427', '52', '否', '否', '50', '2017-04-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('429', '33', '否', '否', '41', '2017-04-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('430', '36', '否', '否', '45', '2017-04-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('432', '41', '否', '否', '47', '2017-04-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('433', '42', '否', '否', '47', '2017-04-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('434', '43', '否', '否', '47', '2017-04-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('439', '33', '否', '否', '41', '2017-04-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('440', '36', '否', '否', '45', '2017-04-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('442', '41', '否', '否', '47', '2017-04-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('443', '42', '否', '否', '47', '2017-04-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('444', '43', '否', '否', '47', '2017-04-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('449', '33', '否', '否', '41', '2017-04-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('450', '36', '否', '否', '45', '2017-04-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('452', '41', '否', '否', '47', '2017-04-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('453', '42', '否', '否', '47', '2017-04-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('454', '43', '否', '否', '47', '2017-04-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('459', '33', '否', '否', '41', '2017-04-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('460', '36', '否', '否', '45', '2017-04-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('462', '41', '否', '否', '47', '2017-04-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('463', '42', '否', '否', '47', '2017-04-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('464', '43', '否', '否', '47', '2017-04-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('469', '33', '否', '否', '41', '2017-04-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('470', '36', '否', '否', '45', '2017-04-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('472', '41', '否', '否', '47', '2017-04-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('473', '42', '否', '否', '47', '2017-04-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('474', '43', '否', '否', '47', '2017-04-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('479', '33', '否', '否', '41', '2017-05-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('480', '36', '否', '否', '45', '2017-05-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('482', '41', '否', '否', '47', '2017-05-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('483', '42', '否', '否', '47', '2017-05-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('484', '43', '否', '否', '47', '2017-05-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('489', '33', '否', '否', '41', '2017-05-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('490', '36', '否', '否', '45', '2017-05-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('492', '41', '否', '否', '47', '2017-05-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('493', '42', '否', '否', '47', '2017-05-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('494', '43', '否', '否', '47', '2017-05-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('499', '33', '否', '否', '41', '2017-05-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('500', '36', '否', '否', '45', '2017-05-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('502', '41', '否', '否', '47', '2017-05-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('503', '42', '否', '否', '47', '2017-05-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('504', '43', '否', '否', '47', '2017-05-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('509', '33', '否', '否', '41', '2017-05-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('510', '36', '否', '否', '45', '2017-05-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('512', '41', '否', '否', '47', '2017-05-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('513', '42', '否', '否', '47', '2017-05-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('514', '43', '否', '否', '47', '2017-05-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('519', '33', '否', '否', '41', '2017-05-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('520', '36', '否', '否', '45', '2017-05-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('522', '41', '否', '否', '47', '2017-05-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('523', '42', '否', '否', '47', '2017-05-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('524', '43', '否', '否', '47', '2017-05-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('529', '33', '否', '否', '41', '2017-05-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('530', '36', '否', '否', '45', '2017-05-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('532', '41', '否', '否', '47', '2017-05-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('533', '42', '否', '否', '47', '2017-05-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('534', '43', '否', '否', '47', '2017-05-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('539', '33', '否', '否', '41', '2017-05-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('540', '36', '否', '否', '45', '2017-05-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('542', '41', '否', '否', '47', '2017-05-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('543', '42', '否', '否', '47', '2017-05-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('544', '43', '否', '否', '47', '2017-05-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('549', '33', '否', '是', '41', '2017-05-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('550', '36', '否', '是', '45', '2017-05-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('552', '41', '否', '是', '47', '2017-05-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('553', '42', '否', '是', '47', '2017-05-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('554', '43', '否', '是', '47', '2017-05-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('559', '33', '否', '是', '41', '2017-05-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('560', '36', '否', '是', '45', '2017-05-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('562', '41', '否', '是', '47', '2017-05-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('563', '42', '否', '是', '47', '2017-05-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('564', '43', '否', '是', '47', '2017-05-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('569', '33', '否', '是', '41', '2017-05-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('570', '36', '否', '是', '45', '2017-05-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('572', '41', '否', '是', '47', '2017-05-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('573', '42', '否', '是', '47', '2017-05-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('574', '43', '否', '是', '47', '2017-05-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('579', '33', '否', '是', '41', '2017-05-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('580', '36', '否', '是', '45', '2017-05-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('582', '41', '否', '是', '47', '2017-05-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('583', '42', '否', '是', '47', '2017-05-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('584', '43', '否', '是', '47', '2017-05-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('589', '33', '否', '是', '41', '2017-05-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('590', '36', '否', '是', '45', '2017-05-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('592', '41', '否', '是', '47', '2017-05-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('593', '42', '否', '是', '47', '2017-05-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('594', '43', '否', '是', '47', '2017-05-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('599', '33', '否', '是', '41', '2017-05-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('600', '36', '否', '是', '45', '2017-05-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('602', '41', '否', '是', '47', '2017-05-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('603', '42', '否', '是', '47', '2017-05-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('604', '43', '否', '是', '47', '2017-05-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('609', '33', '否', '是', '41', '2017-05-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('610', '36', '否', '是', '45', '2017-05-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('612', '41', '否', '是', '47', '2017-05-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('613', '42', '否', '是', '47', '2017-05-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('614', '43', '否', '是', '47', '2017-05-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('619', '33', '否', '是', '41', '2017-05-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('620', '36', '否', '是', '45', '2017-05-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('622', '41', '否', '是', '47', '2017-05-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('623', '42', '否', '是', '47', '2017-05-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('624', '43', '否', '是', '47', '2017-05-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('629', '33', '否', '是', '41', '2017-05-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('630', '36', '否', '是', '45', '2017-05-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('632', '41', '否', '是', '47', '2017-05-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('633', '42', '否', '是', '47', '2017-05-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('634', '43', '否', '是', '47', '2017-05-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('639', '33', '否', '是', '41', '2017-05-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('640', '36', '否', '是', '45', '2017-05-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('642', '41', '否', '是', '47', '2017-05-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('643', '42', '否', '是', '47', '2017-05-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('644', '43', '否', '是', '47', '2017-05-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('649', '33', '否', '是', '41', '2017-05-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('650', '36', '否', '是', '45', '2017-05-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('652', '41', '否', '是', '47', '2017-05-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('653', '42', '否', '是', '47', '2017-05-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('654', '43', '否', '是', '47', '2017-05-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('659', '33', '否', '是', '41', '2017-05-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('660', '36', '否', '是', '45', '2017-05-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('662', '41', '否', '是', '47', '2017-05-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('663', '42', '否', '是', '47', '2017-05-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('664', '43', '否', '是', '47', '2017-05-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('669', '33', '否', '是', '41', '2017-05-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('670', '36', '否', '是', '45', '2017-05-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('672', '41', '否', '是', '47', '2017-05-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('673', '42', '否', '是', '47', '2017-05-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('674', '43', '否', '是', '47', '2017-05-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('679', '33', '否', '是', '41', '2017-05-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('680', '36', '否', '是', '45', '2017-05-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('682', '41', '否', '是', '47', '2017-05-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('683', '42', '否', '是', '47', '2017-05-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('684', '43', '否', '是', '47', '2017-05-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('689', '33', '否', '是', '41', '2017-05-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('690', '36', '否', '是', '45', '2017-05-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('692', '41', '否', '是', '47', '2017-05-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('693', '42', '否', '是', '47', '2017-05-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('694', '43', '否', '是', '47', '2017-05-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('699', '33', '否', '是', '41', '2017-05-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('700', '36', '否', '是', '45', '2017-05-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('702', '41', '否', '是', '47', '2017-05-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('703', '42', '否', '是', '47', '2017-05-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('704', '43', '否', '是', '47', '2017-05-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('709', '33', '否', '是', '41', '2017-06-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('710', '36', '否', '是', '45', '2017-06-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('712', '41', '否', '是', '47', '2017-06-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('713', '42', '否', '是', '47', '2017-06-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('714', '43', '否', '是', '47', '2017-06-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('719', '33', '否', '是', '41', '2017-06-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('720', '36', '否', '是', '45', '2017-06-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('722', '41', '否', '是', '47', '2017-06-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('723', '42', '否', '是', '47', '2017-06-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('724', '43', '否', '是', '47', '2017-06-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('729', '33', '否', '是', '41', '2017-06-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('730', '36', '否', '是', '45', '2017-06-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('732', '41', '否', '是', '47', '2017-06-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('733', '42', '否', '是', '47', '2017-06-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('734', '43', '否', '是', '47', '2017-06-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('739', '33', '否', '是', '41', '2017-06-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('740', '36', '否', '是', '45', '2017-06-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('742', '41', '否', '是', '47', '2017-06-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('743', '42', '否', '是', '47', '2017-06-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('744', '43', '否', '是', '47', '2017-06-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('749', '33', '否', '是', '41', '2017-06-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('750', '36', '否', '是', '45', '2017-06-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('752', '41', '否', '是', '47', '2017-06-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('753', '42', '否', '是', '47', '2017-06-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('754', '43', '否', '是', '47', '2017-06-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('759', '33', '否', '是', '41', '2017-06-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('760', '36', '否', '是', '45', '2017-06-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('762', '41', '否', '是', '47', '2017-06-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('763', '42', '否', '是', '47', '2017-06-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('764', '43', '否', '是', '47', '2017-06-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('769', '33', '否', '是', '41', '2017-06-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('770', '36', '否', '是', '45', '2017-06-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('772', '41', '否', '是', '47', '2017-06-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('773', '42', '否', '是', '47', '2017-06-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('774', '43', '否', '是', '47', '2017-06-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('779', '33', '否', '是', '41', '2017-06-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('780', '36', '否', '是', '45', '2017-06-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('782', '41', '否', '是', '47', '2017-06-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('783', '42', '否', '是', '47', '2017-06-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('784', '43', '否', '是', '47', '2017-06-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('789', '33', '否', '是', '41', '2017-06-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('790', '36', '否', '是', '45', '2017-06-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('792', '41', '否', '是', '47', '2017-06-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('793', '42', '否', '是', '47', '2017-06-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('794', '43', '否', '是', '47', '2017-06-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('799', '33', '否', '是', '41', '2017-06-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('800', '36', '否', '是', '45', '2017-06-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('802', '41', '否', '是', '47', '2017-06-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('803', '42', '否', '是', '47', '2017-06-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('804', '43', '否', '是', '47', '2017-06-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('809', '33', '否', '是', '41', '2017-06-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('810', '36', '否', '是', '45', '2017-06-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('812', '41', '否', '是', '47', '2017-06-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('813', '42', '否', '是', '47', '2017-06-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('814', '43', '否', '是', '47', '2017-06-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('819', '33', '否', '是', '41', '2017-06-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('820', '36', '否', '是', '45', '2017-06-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('822', '41', '否', '是', '47', '2017-06-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('823', '42', '否', '是', '47', '2017-06-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('824', '43', '否', '是', '47', '2017-06-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('829', '33', '否', '是', '41', '2017-06-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('830', '36', '否', '是', '45', '2017-06-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('832', '41', '否', '是', '47', '2017-06-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('833', '42', '否', '是', '47', '2017-06-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('834', '43', '否', '是', '47', '2017-06-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('839', '33', '否', '是', '41', '2017-06-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('840', '36', '否', '是', '45', '2017-06-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('842', '41', '否', '是', '47', '2017-06-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('843', '42', '否', '是', '47', '2017-06-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('844', '43', '否', '是', '47', '2017-06-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('849', '33', '否', '是', '41', '2017-06-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('850', '36', '否', '是', '45', '2017-06-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('852', '41', '否', '是', '47', '2017-06-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('853', '42', '否', '是', '47', '2017-06-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('854', '43', '否', '是', '47', '2017-06-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('859', '33', '否', '是', '41', '2017-06-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('860', '36', '否', '是', '45', '2017-06-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('862', '41', '否', '是', '47', '2017-06-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('863', '42', '否', '是', '47', '2017-06-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('864', '43', '否', '是', '47', '2017-06-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('869', '33', '否', '是', '41', '2017-06-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('870', '36', '否', '是', '45', '2017-06-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('872', '41', '否', '是', '47', '2017-06-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('873', '42', '否', '是', '47', '2017-06-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('874', '43', '否', '是', '47', '2017-06-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('879', '33', '否', '是', '41', '2017-06-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('880', '36', '否', '是', '45', '2017-06-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('882', '41', '否', '是', '47', '2017-06-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('883', '42', '否', '是', '47', '2017-06-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('884', '43', '否', '是', '47', '2017-06-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('889', '33', '否', '是', '41', '2017-06-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('890', '36', '否', '是', '45', '2017-06-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('892', '41', '否', '是', '47', '2017-06-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('893', '42', '否', '是', '47', '2017-06-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('894', '43', '否', '是', '47', '2017-06-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('899', '33', '否', '是', '41', '2017-06-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('900', '36', '否', '是', '45', '2017-06-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('902', '41', '否', '是', '47', '2017-06-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('903', '42', '否', '是', '47', '2017-06-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('904', '43', '否', '是', '47', '2017-06-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('909', '33', '否', '是', '41', '2017-06-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('910', '36', '否', '是', '45', '2017-06-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('912', '41', '否', '是', '47', '2017-06-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('913', '42', '否', '是', '47', '2017-06-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('914', '43', '否', '是', '47', '2017-06-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('919', '33', '否', '是', '41', '2017-06-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('920', '36', '否', '是', '45', '2017-06-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('922', '41', '否', '是', '47', '2017-06-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('923', '42', '否', '是', '47', '2017-06-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('924', '43', '否', '是', '47', '2017-06-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('929', '33', '否', '是', '41', '2017-07-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('930', '36', '否', '是', '45', '2017-07-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('932', '41', '否', '是', '47', '2017-07-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('933', '42', '否', '是', '47', '2017-07-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('934', '43', '否', '是', '47', '2017-07-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('939', '33', '否', '是', '41', '2017-07-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('940', '36', '否', '是', '45', '2017-07-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('942', '41', '否', '是', '47', '2017-07-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('943', '42', '否', '是', '47', '2017-07-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('944', '43', '否', '是', '47', '2017-07-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('949', '33', '否', '是', '41', '2017-07-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('950', '36', '否', '是', '45', '2017-07-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('952', '41', '否', '是', '47', '2017-07-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('953', '42', '否', '是', '47', '2017-07-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('954', '43', '否', '是', '47', '2017-07-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('959', '33', '否', '是', '41', '2017-07-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('960', '36', '否', '是', '45', '2017-07-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('962', '41', '否', '是', '47', '2017-07-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('963', '42', '否', '是', '47', '2017-07-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('964', '43', '否', '是', '47', '2017-07-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('969', '33', '否', '是', '41', '2017-07-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('970', '36', '否', '是', '45', '2017-07-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('972', '41', '否', '是', '47', '2017-07-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('973', '42', '否', '是', '47', '2017-07-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('974', '43', '否', '是', '47', '2017-07-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('979', '33', '否', '是', '41', '2017-07-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('980', '36', '否', '是', '45', '2017-07-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('982', '41', '否', '是', '47', '2017-07-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('983', '42', '否', '是', '47', '2017-07-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('984', '43', '否', '是', '47', '2017-07-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('989', '33', '否', '是', '41', '2017-07-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('990', '36', '否', '是', '45', '2017-07-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('992', '41', '否', '是', '47', '2017-07-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('993', '42', '否', '是', '47', '2017-07-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('994', '43', '否', '是', '47', '2017-07-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('999', '33', '否', '是', '41', '2017-07-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1000', '36', '否', '是', '45', '2017-07-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1002', '41', '否', '是', '47', '2017-07-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1003', '42', '否', '是', '47', '2017-07-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1004', '43', '否', '是', '47', '2017-07-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1009', '33', '否', '是', '41', '2017-07-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1010', '36', '否', '是', '45', '2017-07-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1012', '41', '否', '是', '47', '2017-07-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1013', '42', '否', '是', '47', '2017-07-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1014', '43', '否', '是', '47', '2017-07-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1019', '33', '否', '是', '41', '2017-07-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1020', '36', '否', '是', '45', '2017-07-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1022', '41', '否', '是', '47', '2017-07-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1023', '42', '否', '是', '47', '2017-07-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1024', '43', '否', '是', '47', '2017-07-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1029', '33', '否', '是', '41', '2017-07-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1030', '36', '否', '是', '45', '2017-07-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1032', '41', '否', '是', '47', '2017-07-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1033', '42', '否', '是', '47', '2017-07-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1034', '43', '否', '是', '47', '2017-07-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1039', '33', '否', '是', '41', '2017-07-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1040', '36', '否', '是', '45', '2017-07-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1042', '41', '否', '是', '47', '2017-07-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1043', '42', '否', '是', '47', '2017-07-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1044', '43', '否', '是', '47', '2017-07-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1049', '33', '否', '是', '41', '2017-07-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1050', '36', '否', '是', '45', '2017-07-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1052', '41', '否', '是', '47', '2017-07-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1053', '42', '否', '是', '47', '2017-07-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1054', '43', '否', '是', '47', '2017-07-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1059', '33', '否', '是', '41', '2017-07-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1060', '36', '否', '是', '45', '2017-07-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1062', '41', '否', '是', '47', '2017-07-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1063', '42', '否', '是', '47', '2017-07-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1064', '43', '否', '是', '47', '2017-07-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1069', '33', '否', '是', '41', '2017-07-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1070', '36', '否', '是', '45', '2017-07-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1072', '41', '否', '是', '47', '2017-07-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1073', '42', '否', '是', '47', '2017-07-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1074', '43', '否', '是', '47', '2017-07-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1079', '33', '否', '是', '41', '2017-07-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1080', '36', '否', '是', '45', '2017-07-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1082', '41', '否', '是', '47', '2017-07-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1083', '42', '否', '是', '47', '2017-07-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1084', '43', '否', '是', '47', '2017-07-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1089', '33', '否', '是', '41', '2017-07-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1090', '36', '否', '是', '45', '2017-07-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1092', '41', '否', '是', '47', '2017-07-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1093', '42', '否', '是', '47', '2017-07-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1094', '43', '否', '是', '47', '2017-07-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1099', '33', '否', '是', '41', '2017-07-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1100', '36', '否', '是', '45', '2017-07-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1102', '41', '否', '是', '47', '2017-07-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1103', '42', '否', '是', '47', '2017-07-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1104', '43', '否', '是', '47', '2017-07-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1109', '33', '否', '是', '41', '2017-07-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1110', '36', '否', '是', '45', '2017-07-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1112', '41', '否', '是', '47', '2017-07-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1113', '42', '否', '是', '47', '2017-07-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1114', '43', '否', '是', '47', '2017-07-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1119', '33', '否', '是', '41', '2017-07-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1120', '36', '否', '是', '45', '2017-07-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1122', '41', '否', '是', '47', '2017-07-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1123', '42', '否', '是', '47', '2017-07-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1124', '43', '否', '是', '47', '2017-07-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1129', '33', '否', '是', '41', '2017-07-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1130', '36', '否', '是', '45', '2017-07-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1132', '41', '否', '是', '47', '2017-07-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1133', '42', '否', '是', '47', '2017-07-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1134', '43', '否', '是', '47', '2017-07-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1139', '33', '否', '是', '41', '2017-08-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1140', '36', '否', '是', '45', '2017-08-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1142', '41', '否', '是', '47', '2017-08-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1143', '42', '否', '是', '47', '2017-08-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1144', '43', '否', '是', '47', '2017-08-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1149', '33', '否', '是', '41', '2017-08-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1150', '36', '否', '是', '45', '2017-08-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1152', '41', '否', '是', '47', '2017-08-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1153', '42', '否', '是', '47', '2017-08-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1154', '43', '否', '是', '47', '2017-08-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1159', '33', '否', '是', '41', '2017-08-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1160', '36', '否', '是', '45', '2017-08-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1162', '41', '否', '是', '47', '2017-08-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1163', '42', '否', '是', '47', '2017-08-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1164', '43', '否', '是', '47', '2017-08-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1169', '33', '否', '是', '41', '2017-08-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1170', '36', '否', '是', '45', '2017-08-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1172', '41', '否', '是', '47', '2017-08-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1173', '42', '否', '是', '47', '2017-08-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1174', '43', '否', '是', '47', '2017-08-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1179', '33', '否', '是', '41', '2017-08-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1180', '36', '否', '是', '45', '2017-08-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1182', '41', '否', '是', '47', '2017-08-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1183', '42', '否', '是', '47', '2017-08-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1184', '43', '否', '是', '47', '2017-08-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1189', '33', '否', '是', '41', '2017-08-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1190', '36', '否', '是', '45', '2017-08-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1192', '41', '否', '是', '47', '2017-08-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1193', '42', '否', '是', '47', '2017-08-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1194', '43', '否', '是', '47', '2017-08-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1199', '33', '否', '是', '41', '2017-08-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1200', '36', '否', '是', '45', '2017-08-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1202', '41', '否', '是', '47', '2017-08-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1203', '42', '否', '是', '47', '2017-08-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1204', '43', '否', '是', '47', '2017-08-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1209', '33', '否', '是', '41', '2017-08-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1210', '36', '否', '是', '45', '2017-08-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1212', '41', '否', '是', '47', '2017-08-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1213', '42', '否', '是', '47', '2017-08-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1214', '43', '否', '是', '47', '2017-08-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1219', '33', '否', '是', '41', '2017-08-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1220', '36', '否', '是', '45', '2017-08-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1222', '41', '否', '是', '47', '2017-08-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1223', '42', '否', '是', '47', '2017-08-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1224', '43', '否', '是', '47', '2017-08-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1229', '33', '否', '是', '41', '2017-08-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1230', '36', '否', '是', '45', '2017-08-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1232', '41', '否', '是', '47', '2017-08-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1233', '42', '否', '是', '47', '2017-08-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1234', '43', '否', '是', '47', '2017-08-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1239', '33', '否', '是', '41', '2017-08-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1240', '36', '否', '是', '45', '2017-08-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1242', '41', '否', '是', '47', '2017-08-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1243', '42', '否', '是', '47', '2017-08-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1244', '43', '否', '是', '47', '2017-08-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1249', '33', '否', '是', '41', '2017-08-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1250', '36', '否', '是', '45', '2017-08-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1252', '41', '否', '是', '47', '2017-08-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1253', '42', '否', '是', '47', '2017-08-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1254', '43', '否', '是', '47', '2017-08-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1259', '33', '否', '是', '41', '2017-08-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1260', '36', '否', '是', '45', '2017-08-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1262', '41', '否', '是', '47', '2017-08-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1263', '42', '否', '是', '47', '2017-08-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1264', '43', '否', '是', '47', '2017-08-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1269', '33', '否', '是', '41', '2017-08-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1270', '36', '否', '是', '45', '2017-08-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1272', '41', '否', '是', '47', '2017-08-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1273', '42', '否', '是', '47', '2017-08-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1274', '43', '否', '是', '47', '2017-08-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1279', '33', '否', '是', '41', '2017-08-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1280', '36', '否', '是', '45', '2017-08-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1282', '41', '否', '是', '47', '2017-08-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1283', '42', '否', '是', '47', '2017-08-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1284', '43', '否', '是', '47', '2017-08-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1289', '33', '否', '是', '41', '2017-08-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1290', '36', '否', '是', '45', '2017-08-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1292', '41', '否', '是', '47', '2017-08-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1293', '42', '否', '是', '47', '2017-08-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1294', '43', '否', '是', '47', '2017-08-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1299', '33', '否', '是', '41', '2017-08-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1300', '36', '否', '是', '45', '2017-08-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1302', '41', '否', '是', '47', '2017-08-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1303', '42', '否', '是', '47', '2017-08-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1304', '43', '否', '是', '47', '2017-08-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1309', '33', '否', '是', '41', '2017-08-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1310', '36', '否', '是', '45', '2017-08-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1312', '41', '否', '是', '47', '2017-08-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1313', '42', '否', '是', '47', '2017-08-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1314', '43', '否', '是', '47', '2017-08-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1319', '33', '否', '是', '41', '2017-08-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1320', '36', '否', '是', '45', '2017-08-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1322', '41', '否', '是', '47', '2017-08-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1323', '42', '否', '是', '47', '2017-08-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1324', '43', '否', '是', '47', '2017-08-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1329', '33', '否', '是', '41', '2017-08-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1330', '36', '否', '是', '45', '2017-08-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1332', '41', '否', '是', '47', '2017-08-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1333', '42', '否', '是', '47', '2017-08-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1334', '43', '否', '是', '47', '2017-08-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1339', '33', '否', '是', '41', '2017-08-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1340', '36', '否', '是', '45', '2017-08-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1342', '41', '否', '是', '47', '2017-08-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1343', '42', '否', '是', '47', '2017-08-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1344', '43', '否', '是', '47', '2017-08-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1349', '33', '否', '是', '41', '2017-08-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1350', '36', '否', '是', '45', '2017-08-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1352', '41', '否', '是', '47', '2017-08-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1353', '42', '否', '是', '47', '2017-08-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1354', '43', '否', '是', '47', '2017-08-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1359', '33', '否', '是', '41', '2017-08-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1360', '36', '否', '是', '45', '2017-08-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1362', '41', '否', '是', '47', '2017-08-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1363', '42', '否', '是', '47', '2017-08-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1364', '43', '否', '是', '47', '2017-08-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1369', '33', '否', '是', '41', '2017-09-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1370', '36', '否', '是', '45', '2017-09-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1372', '41', '否', '是', '47', '2017-09-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1373', '42', '否', '是', '47', '2017-09-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1374', '43', '否', '是', '47', '2017-09-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1378', '33', '否', '是', '41', '2017-09-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1379', '36', '否', '是', '45', '2017-09-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1380', '41', '否', '是', '47', '2017-09-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1381', '42', '否', '是', '47', '2017-09-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1382', '43', '否', '是', '47', '2017-09-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1383', '33', '否', '是', '41', '2017-09-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1384', '36', '否', '是', '45', '2017-09-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1385', '41', '否', '是', '47', '2017-09-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1386', '42', '否', '是', '47', '2017-09-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1387', '43', '否', '是', '47', '2017-09-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1388', '33', '否', '是', '41', '2017-09-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1389', '36', '否', '是', '45', '2017-09-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1390', '41', '否', '是', '47', '2017-09-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1391', '42', '否', '是', '47', '2017-09-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1392', '43', '否', '是', '47', '2017-09-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1393', '59', '否', '是', '67', '2017-09-06', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1394', '33', '否', '是', '41', '2017-09-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1395', '36', '否', '是', '45', '2017-09-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1396', '41', '否', '是', '47', '2017-09-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1397', '42', '否', '是', '47', '2017-09-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1398', '43', '否', '是', '47', '2017-09-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1399', '59', '否', '是', '67', '2017-09-07', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1400', '33', '否', '是', '41', '2017-09-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1401', '36', '否', '是', '45', '2017-09-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1402', '41', '否', '是', '47', '2017-09-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1403', '42', '否', '是', '47', '2017-09-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1404', '43', '否', '是', '47', '2017-09-08', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1405', '59', '否', '是', '67', '2017-09-08', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1406', '33', '否', '是', '41', '2017-09-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1407', '36', '否', '是', '45', '2017-09-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1408', '41', '否', '是', '47', '2017-09-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1409', '42', '否', '是', '47', '2017-09-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1410', '43', '否', '是', '47', '2017-09-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1411', '59', '否', '是', '67', '2017-09-11', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1412', '33', '否', '是', '41', '2017-09-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1413', '36', '否', '是', '45', '2017-09-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1414', '41', '否', '是', '47', '2017-09-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1415', '42', '否', '是', '47', '2017-09-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1416', '43', '否', '是', '47', '2017-09-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1417', '59', '否', '是', '67', '2017-09-12', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1418', '33', '否', '是', '41', '2017-09-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1419', '36', '否', '是', '45', '2017-09-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1420', '41', '否', '是', '47', '2017-09-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1421', '42', '否', '是', '47', '2017-09-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1422', '43', '否', '是', '47', '2017-09-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1423', '59', '否', '是', '67', '2017-09-13', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1424', '33', '否', '是', '41', '2017-09-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1425', '36', '否', '是', '45', '2017-09-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1426', '41', '否', '是', '47', '2017-09-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1427', '42', '否', '是', '47', '2017-09-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1428', '43', '否', '是', '47', '2017-09-14', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1429', '59', '否', '是', '67', '2017-09-14', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1430', '33', '否', '是', '41', '2017-09-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1431', '36', '否', '是', '45', '2017-09-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1432', '41', '否', '是', '47', '2017-09-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1433', '42', '否', '是', '47', '2017-09-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1434', '43', '否', '是', '47', '2017-09-15', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1435', '59', '否', '是', '67', '2017-09-15', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1436', '33', '否', '是', '41', '2017-09-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1437', '36', '否', '是', '45', '2017-09-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1438', '41', '否', '是', '47', '2017-09-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1439', '42', '否', '是', '47', '2017-09-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1440', '43', '否', '是', '47', '2017-09-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1441', '59', '否', '是', '67', '2017-09-18', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1442', '33', '否', '是', '41', '2017-09-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1443', '36', '否', '是', '45', '2017-09-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1444', '41', '否', '是', '47', '2017-09-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1445', '42', '否', '是', '47', '2017-09-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1446', '43', '否', '是', '47', '2017-09-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1447', '59', '否', '是', '67', '2017-09-19', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1448', '33', '否', '是', '41', '2017-09-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1449', '36', '否', '是', '45', '2017-09-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1450', '41', '否', '是', '47', '2017-09-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1451', '42', '否', '是', '47', '2017-09-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1452', '43', '否', '是', '47', '2017-09-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1453', '59', '否', '是', '67', '2017-09-20', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1454', '33', '否', '是', '41', '2017-09-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1455', '36', '否', '是', '45', '2017-09-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1456', '41', '否', '是', '47', '2017-09-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1457', '42', '否', '是', '47', '2017-09-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1458', '43', '否', '是', '47', '2017-09-21', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1459', '59', '否', '是', '67', '2017-09-21', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1460', '33', '否', '是', '41', '2017-09-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1461', '36', '否', '是', '45', '2017-09-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1462', '41', '否', '是', '47', '2017-09-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1463', '42', '否', '是', '47', '2017-09-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1464', '43', '否', '是', '47', '2017-09-22', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1465', '59', '否', '是', '67', '2017-09-22', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1466', '33', '否', '是', '41', '2017-09-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1467', '36', '否', '是', '45', '2017-09-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1468', '41', '否', '是', '47', '2017-09-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1469', '42', '否', '是', '47', '2017-09-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1470', '43', '否', '是', '47', '2017-09-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1471', '59', '否', '是', '67', '2017-09-25', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1472', '33', '否', '是', '41', '2017-09-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1473', '36', '否', '是', '45', '2017-09-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1474', '41', '否', '是', '47', '2017-09-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1475', '42', '否', '是', '47', '2017-09-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1476', '43', '否', '是', '47', '2017-09-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1477', '59', '否', '是', '67', '2017-09-26', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1478', '33', '否', '是', '41', '2017-09-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1479', '36', '否', '是', '45', '2017-09-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1480', '41', '否', '是', '47', '2017-09-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1481', '42', '否', '是', '47', '2017-09-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1482', '43', '否', '是', '47', '2017-09-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1483', '59', '否', '是', '67', '2017-09-27', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1484', '33', '否', '是', '41', '2017-09-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1485', '36', '否', '是', '45', '2017-09-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1486', '41', '否', '是', '47', '2017-09-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1487', '42', '否', '是', '47', '2017-09-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1488', '43', '否', '是', '47', '2017-09-28', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1489', '59', '否', '是', '67', '2017-09-28', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1490', '33', '否', '是', '41', '2017-09-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1491', '36', '否', '是', '45', '2017-09-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1492', '41', '否', '是', '47', '2017-09-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1493', '42', '否', '是', '47', '2017-09-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1494', '43', '否', '是', '47', '2017-09-29', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1495', '59', '否', '是', '67', '2017-09-29', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1496', '33', '否', '是', '41', '2017-10-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1497', '36', '否', '是', '45', '2017-10-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1498', '41', '否', '是', '47', '2017-10-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1499', '42', '否', '是', '47', '2017-10-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1500', '43', '否', '是', '47', '2017-10-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1501', '59', '否', '是', '67', '2017-10-02', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1502', '62', '否', '是', '51', '2017-10-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1503', '63', '否', '是', '65', '2017-10-02', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1504', '33', '否', '是', '41', '2017-10-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1505', '36', '否', '是', '45', '2017-10-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1506', '41', '否', '是', '47', '2017-10-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1507', '42', '否', '是', '47', '2017-10-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1508', '43', '否', '是', '47', '2017-10-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1509', '59', '否', '是', '67', '2017-10-03', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1510', '62', '否', '是', '51', '2017-10-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1511', '63', '否', '是', '65', '2017-10-03', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1512', '33', '否', '是', '41', '2017-10-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1513', '36', '否', '是', '45', '2017-10-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1514', '41', '否', '是', '47', '2017-10-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1515', '42', '否', '是', '47', '2017-10-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1516', '43', '否', '是', '47', '2017-10-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1517', '59', '否', '是', '67', '2017-10-04', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1518', '62', '否', '是', '51', '2017-10-04', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1519', '63', '否', '是', '65', '2017-10-04', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1520', '33', '否', '是', '41', '2017-10-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1521', '36', '否', '是', '45', '2017-10-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1522', '41', '否', '是', '47', '2017-10-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1523', '42', '否', '是', '47', '2017-10-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1524', '43', '否', '是', '47', '2017-10-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1525', '59', '否', '是', '67', '2017-10-05', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1526', '62', '否', '是', '51', '2017-10-05', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1527', '63', '否', '是', '65', '2017-10-05', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1528', '33', '否', '是', '41', '2017-10-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1529', '36', '否', '是', '45', '2017-10-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1530', '41', '否', '是', '47', '2017-10-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1531', '42', '否', '是', '47', '2017-10-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1532', '43', '否', '是', '47', '2017-10-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1533', '59', '否', '是', '67', '2017-10-06', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1534', '62', '否', '是', '51', '2017-10-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1535', '63', '否', '是', '65', '2017-10-06', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1536', '33', '否', '是', '41', '2017-10-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1537', '36', '否', '是', '45', '2017-10-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1538', '41', '否', '是', '47', '2017-10-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1539', '42', '否', '是', '47', '2017-10-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1540', '43', '否', '是', '47', '2017-10-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1541', '59', '否', '是', '67', '2017-10-09', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1542', '62', '是', '是', '51', '2017-10-09', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1543', '63', '否', '是', '65', '2017-10-09', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1544', '33', '否', '是', '41', '2017-10-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1545', '36', '否', '是', '45', '2017-10-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1546', '41', '否', '是', '47', '2017-10-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1547', '42', '否', '是', '47', '2017-10-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1548', '43', '否', '是', '47', '2017-10-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1549', '59', '否', '是', '67', '2017-10-10', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1550', '62', '否', '是', '51', '2017-10-10', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1551', '63', '否', '是', '65', '2017-10-10', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1552', '33', '否', '是', '41', '2017-10-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1553', '36', '否', '是', '45', '2017-10-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1554', '41', '否', '是', '47', '2017-10-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1555', '42', '否', '是', '47', '2017-10-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1556', '43', '否', '是', '47', '2017-10-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1557', '59', '否', '是', '67', '2017-10-11', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1558', '62', '否', '是', '51', '2017-10-11', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1559', '63', '否', '是', '65', '2017-10-11', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1560', '33', '否', '是', '41', '2017-10-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1561', '36', '否', '是', '45', '2017-10-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1562', '41', '否', '是', '47', '2017-10-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1563', '42', '否', '是', '47', '2017-10-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1564', '43', '否', '是', '47', '2017-10-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1565', '59', '否', '是', '67', '2017-10-12', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1566', '62', '否', '是', '51', '2017-10-12', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1567', '63', '否', '是', '65', '2017-10-12', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1568', '33', '否', '是', '41', '2017-10-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1569', '36', '否', '是', '45', '2017-10-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1570', '41', '否', '是', '47', '2017-10-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1571', '42', '否', '是', '47', '2017-10-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1572', '43', '否', '是', '47', '2017-10-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1573', '59', '否', '是', '67', '2017-10-13', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1574', '62', '否', '是', '51', '2017-10-13', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1575', '63', '否', '是', '65', '2017-10-13', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1576', '33', '否', '是', '41', '2017-10-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1577', '36', '否', '是', '45', '2017-10-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1578', '41', '否', '是', '47', '2017-10-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1579', '42', '否', '是', '47', '2017-10-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1580', '43', '否', '是', '47', '2017-10-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1581', '59', '否', '是', '67', '2017-10-16', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1582', '62', '否', '是', '51', '2017-10-16', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1583', '63', '否', '是', '65', '2017-10-16', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1584', '33', '否', '是', '41', '2017-10-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1585', '36', '否', '是', '45', '2017-10-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1586', '41', '否', '是', '47', '2017-10-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1587', '42', '否', '是', '47', '2017-10-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1588', '43', '否', '是', '47', '2017-10-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1589', '59', '否', '是', '67', '2017-10-17', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1590', '62', '否', '是', '51', '2017-10-17', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1591', '63', '否', '是', '65', '2017-10-17', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1592', '33', '否', '是', '41', '2017-10-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1593', '36', '否', '是', '45', '2017-10-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1594', '41', '否', '是', '47', '2017-10-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1595', '42', '否', '是', '47', '2017-10-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1596', '43', '否', '是', '47', '2017-10-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1597', '59', '否', '是', '67', '2017-10-18', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1598', '62', '否', '是', '51', '2017-10-18', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1599', '63', '否', '是', '65', '2017-10-18', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1600', '33', '否', '是', '41', '2017-10-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1601', '36', '否', '是', '45', '2017-10-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1602', '41', '否', '是', '47', '2017-10-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1603', '42', '否', '是', '47', '2017-10-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1604', '43', '否', '是', '47', '2017-10-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1605', '59', '否', '是', '67', '2017-10-19', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1606', '62', '否', '是', '51', '2017-10-19', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1607', '63', '否', '是', '65', '2017-10-19', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1608', '33', '否', '是', '41', '2017-10-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1609', '36', '否', '是', '45', '2017-10-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1610', '41', '否', '是', '47', '2017-10-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1611', '42', '否', '是', '47', '2017-10-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1612', '43', '否', '是', '47', '2017-10-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1613', '59', '否', '是', '67', '2017-10-20', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1614', '62', '否', '是', '51', '2017-10-20', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1615', '63', '否', '是', '65', '2017-10-20', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1616', '33', '否', '是', '41', '2017-10-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1617', '36', '否', '是', '45', '2017-10-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1618', '41', '否', '是', '47', '2017-10-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1619', '42', '否', '是', '47', '2017-10-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1620', '43', '否', '是', '47', '2017-10-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1621', '59', '否', '是', '67', '2017-10-23', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1622', '62', '否', '是', '51', '2017-10-23', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1623', '63', '否', '是', '65', '2017-10-23', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1624', '33', '否', '是', '41', '2017-10-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1625', '36', '否', '是', '45', '2017-10-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1626', '41', '否', '是', '47', '2017-10-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1627', '42', '否', '是', '47', '2017-10-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1628', '43', '否', '是', '47', '2017-10-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1629', '59', '否', '是', '67', '2017-10-24', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1630', '62', '否', '是', '51', '2017-10-24', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1631', '63', '否', '是', '65', '2017-10-24', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1632', '33', '否', '是', '41', '2017-10-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1633', '36', '否', '是', '45', '2017-10-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1634', '41', '否', '是', '47', '2017-10-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1635', '42', '否', '是', '47', '2017-10-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1636', '43', '否', '是', '47', '2017-10-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1637', '59', '否', '是', '67', '2017-10-25', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1638', '62', '否', '是', '51', '2017-10-25', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1639', '63', '否', '是', '65', '2017-10-25', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1640', '33', '否', '是', '41', '2017-10-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1641', '36', '否', '是', '45', '2017-10-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1642', '41', '否', '是', '47', '2017-10-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1643', '42', '否', '是', '47', '2017-10-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1644', '43', '否', '是', '47', '2017-10-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1645', '59', '否', '是', '67', '2017-10-26', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1646', '62', '否', '是', '51', '2017-10-26', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1647', '63', '否', '是', '65', '2017-10-26', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1648', '33', '否', '是', '41', '2017-10-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1649', '36', '否', '是', '45', '2017-10-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1650', '41', '否', '是', '47', '2017-10-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1651', '42', '否', '是', '47', '2017-10-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1652', '43', '否', '是', '47', '2017-10-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1653', '59', '否', '是', '67', '2017-10-27', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1654', '62', '否', '是', '51', '2017-10-27', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1655', '63', '否', '是', '65', '2017-10-27', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1656', '33', '否', '是', '41', '2017-10-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1657', '36', '否', '是', '45', '2017-10-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1658', '41', '否', '是', '47', '2017-10-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1659', '42', '否', '是', '47', '2017-10-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1660', '43', '否', '是', '47', '2017-10-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1661', '59', '否', '是', '67', '2017-10-30', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1662', '62', '否', '是', '51', '2017-10-30', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1663', '63', '否', '是', '65', '2017-10-30', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1664', '33', '否', '是', '41', '2017-10-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1665', '36', '否', '是', '45', '2017-10-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1666', '41', '否', '是', '47', '2017-10-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1667', '42', '否', '是', '47', '2017-10-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1668', '43', '否', '是', '47', '2017-10-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1669', '59', '否', '是', '67', '2017-10-31', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1670', '62', '否', '是', '51', '2017-10-31', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1671', '63', '否', '是', '65', '2017-10-31', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1672', '33', '否', '是', '41', '2017-11-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1673', '36', '否', '是', '45', '2017-11-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1674', '41', '否', '是', '47', '2017-11-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1675', '42', '否', '是', '47', '2017-11-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1676', '43', '否', '是', '47', '2017-11-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1677', '59', '否', '是', '67', '2017-11-01', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1678', '62', '否', '是', '51', '2017-11-01', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1679', '63', '否', '是', '65', '2017-11-01', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1680', '33', '否', '是', '41', '2017-11-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1681', '36', '否', '是', '45', '2017-11-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1682', '41', '否', '是', '47', '2017-11-02', '1', '1', '无');
INSERT INTO `kg_footprint` VALUES ('1683', '42', '否', '是', '47', '2017-11-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1684', '43', '否', '是', '47', '2017-11-02', '1', '1', '无');
INSERT INTO `kg_footprint` VALUES ('1685', '59', '否', '是', '67', '2017-11-02', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1686', '62', '否', '是', '51', '2017-11-02', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1687', '63', '否', '是', '65', '2017-11-02', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1688', '33', '否', '是', '41', '2017-11-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1689', '36', '否', '是', '45', '2017-11-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1690', '41', '否', '是', '47', '2017-11-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1691', '42', '否', '是', '47', '2017-11-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1692', '43', '否', '是', '47', '2017-11-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1693', '59', '否', '是', '67', '2017-11-03', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1694', '62', '否', '是', '51', '2017-11-03', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1695', '63', '否', '是', '65', '2017-11-03', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1696', '33', '否', '是', '41', '2017-11-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1697', '36', '否', '是', '45', '2017-11-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1698', '41', '否', '是', '47', '2017-11-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1699', '42', '否', '是', '47', '2017-11-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1700', '43', '否', '是', '47', '2017-11-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1701', '59', '否', '是', '67', '2017-11-06', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1702', '62', '否', '是', '51', '2017-11-06', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1703', '63', '否', '是', '65', '2017-11-06', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1704', '33', '否', '是', '41', '2017-11-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1705', '36', '否', '是', '45', '2017-11-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1706', '41', '否', '是', '47', '2017-11-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1707', '42', '否', '是', '47', '2017-11-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1708', '43', '否', '是', '47', '2017-11-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1709', '59', '否', '是', '67', '2017-11-07', '1', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1710', '62', '否', '是', '51', '2017-11-07', '1', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1711', '63', '否', '是', '65', '2017-11-07', '1', '14', '正常');
INSERT INTO `kg_footprint` VALUES ('1712', '33', '否', '是', '41', '2017-11-08', '0', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1713', '36', '否', '是', '45', '2017-11-08', '0', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1714', '41', '否', '是', '47', '2017-11-08', '0', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1715', '42', '否', '是', '47', '2017-11-08', '0', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1716', '43', '否', '是', '47', '2017-11-08', '0', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1717', '59', '否', '是', '67', '2017-11-08', '0', '8', '正常');
INSERT INTO `kg_footprint` VALUES ('1718', '62', '否', '是', '51', '2017-11-08', '0', '1', '正常');
INSERT INTO `kg_footprint` VALUES ('1719', '63', '否', '是', '65', '2017-11-08', '0', '14', '正常');

-- ----------------------------
-- Table structure for kg_garden
-- ----------------------------
DROP TABLE IF EXISTS `kg_garden`;
CREATE TABLE `kg_garden` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '园区名称',
  `introduce` mediumtext COLLATE utf8_unicode_ci COMMENT '园区简介',
  `code` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '园区编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of kg_garden
-- ----------------------------
INSERT INTO `kg_garden` VALUES ('1', '西溪诚园儿童之家', '<p>\n	<span><span style=\"background-color:#F5F5F5;\">111</span></span>\n</p>', '0001');
INSERT INTO `kg_garden` VALUES ('2', '玉园儿童之家（商铺）', '<br />', '0002');
INSERT INTO `kg_garden` VALUES ('3', '田园牧歌儿童之家IC1', '园区介绍', '0003');
INSERT INTO `kg_garden` VALUES ('5', '玉园IC1儿童之家', '玉园IC1儿童之家', '0004');
INSERT INTO `kg_garden` VALUES ('6', '玉园IC2儿童之家', '玉园IC2儿童之家', '0005');
INSERT INTO `kg_garden` VALUES ('7', '玉园CASA儿童之家', '玉园CASA儿童之家', '0006');
INSERT INTO `kg_garden` VALUES ('8', '中蒙基地IC1儿童之家', '中蒙基地IC1儿童之家', '0007');
INSERT INTO `kg_garden` VALUES ('9', '中蒙基地IC2儿童之家', '中蒙基地IC2儿童之家', '0008');
INSERT INTO `kg_garden` VALUES ('10', '中蒙基地CASA儿童之家', '中蒙基地CASA儿童之家', '0009');
INSERT INTO `kg_garden` VALUES ('11', '悦活轩儿童之家IC1', '悦活轩儿童之家', '0010');
INSERT INTO `kg_garden` VALUES ('12', '黄龙和山儿童之家', '黄龙和山儿童之家', '0011');
INSERT INTO `kg_garden` VALUES ('13', '大华海派儿童之家', '大华海派儿童之家', '0012');
INSERT INTO `kg_garden` VALUES ('14', '华东院儿童之家IC1', '华东院儿童之家', '0013');
INSERT INTO `kg_garden` VALUES ('15', '田园牧歌儿童之家IC2', 'ic2', null);
INSERT INTO `kg_garden` VALUES ('16', '田园牧歌儿童之家CASA', '田园牧歌儿童之家CASA', null);
INSERT INTO `kg_garden` VALUES ('17', '华东园儿童之家IC2', '华东园儿童之家IC2', null);
INSERT INTO `kg_garden` VALUES ('18', '悦活轩儿童之家IC2', '悦活轩儿童之家', null);
INSERT INTO `kg_garden` VALUES ('19', '嘉信国际儿童之家IC1', '嘉信国际儿童之家IC1', null);

-- ----------------------------
-- Table structure for kg_log
-- ----------------------------
DROP TABLE IF EXISTS `kg_log`;
CREATE TABLE `kg_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评语内容',
  `reply` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '回复内容',
  `create_time` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评语时间',
  `living_ability` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '生活能力',
  `learning_ability` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学习能力',
  `communication_ability` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '交往能力',
  `baby_id` int(11) DEFAULT NULL COMMENT 'babyId',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师id',
  `state` tinyint(1) DEFAULT NULL COMMENT '审核状态',
  `flag` int(11) DEFAULT NULL COMMENT '是否审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of kg_log
-- ----------------------------
INSERT INTO `kg_log` VALUES ('31', '好', '谢谢', '2017-03-13', '棒', '好', '好', '33', '41', '1', '1');
INSERT INTO `kg_log` VALUES ('32', '非常好', '谢谢老师', '2017-03-14', '生活', '好', '好', '43', '47', '1', '1');
INSERT INTO `kg_log` VALUES ('45', '好', null, '2017-04-20', '好', '好', '好', '52', '50', '0', '0');
INSERT INTO `kg_log` VALUES ('53', '颐和园', '猪骨粥', '2017-09-3', '胡惠誉', null, null, '59', '67', '1', '1');
INSERT INTO `kg_log` VALUES ('54', '111', null, '2017-09-11', '111', null, null, '62', '51', '0', '0');
INSERT INTO `kg_log` VALUES ('55', '学会了', null, '2017-09-10', '学会了', null, null, '62', '51', '1', '1');

-- ----------------------------
-- Table structure for kg_module
-- ----------------------------
DROP TABLE IF EXISTS `kg_module`;
CREATE TABLE `kg_module` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '模块内容',
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '模块url',
  `id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pmid` int(11) DEFAULT NULL,
  `nflag` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of kg_module
-- ----------------------------
INSERT INTO `kg_module` VALUES ('1', '首页', '#', null, null, '0', '1');
INSERT INTO `kg_module` VALUES ('2', '儿童', '#', null, null, '0', '2');
INSERT INTO `kg_module` VALUES ('3', '教师', '#', null, null, '0', '3');
INSERT INTO `kg_module` VALUES ('4', '园区', '#', null, null, '0', '5');
INSERT INTO `kg_module` VALUES ('5', '管理员', '#', null, null, '0', '6');
INSERT INTO `kg_module` VALUES ('6', '欢迎你', 'right.html', 'main-menu', '1', '1', null);
INSERT INTO `kg_module` VALUES ('7', '学生管理', 'baby.html', 'baby-menu', '2', '1', null);
INSERT INTO `kg_module` VALUES ('8', '教师管理', 'teacher.html', 'teacher-menu', '3', '1', null);
INSERT INTO `kg_module` VALUES ('9', '园区管理', 'garden.html', 'garden-menu', '4', '1', null);
INSERT INTO `kg_module` VALUES ('10', '管理员管理', 'admin.html', 'admin-menu', '5', '1', null);
INSERT INTO `kg_module` VALUES ('11', '儿童列表', '/baby/loadBaby.do', null, '7', '2', null);
INSERT INTO `kg_module` VALUES ('12', '儿童修改', '/baby/updBaby.do', null, '7', '2', null);
INSERT INTO `kg_module` VALUES ('13', '儿童删除', '/baby/deleteBaby.do', null, '7', '2', null);
INSERT INTO `kg_module` VALUES ('14', '儿童审核', '/baby/b_changeState.do', null, '7', '2', null);
INSERT INTO `kg_module` VALUES ('15', '日志列表', '/log/stu_loadLog.do', null, '7', '2', null);
INSERT INTO `kg_module` VALUES ('16', '日志修改', '/log/stu_updLog.do', null, '7', '2', null);
INSERT INTO `kg_module` VALUES ('17', '日志删除', '/log/stu_deleteLog.do', null, '7', '2', null);
INSERT INTO `kg_module` VALUES ('18', '日志审核', '/log/stu_changeState.do', null, '7', '2', null);
INSERT INTO `kg_module` VALUES ('19', '足迹列表', '/footprint/stu_loadFootprint.do', null, '7', '2', null);
INSERT INTO `kg_module` VALUES ('20', '足迹修改', '/footprint/stu_updFootprint.do', null, '7', '2', null);
INSERT INTO `kg_module` VALUES ('21', '足迹删除', '/footprint/stu_deleteFootprint.do', null, '7', '2', null);
INSERT INTO `kg_module` VALUES ('22', '照片加载', '/picture/loadPicture.do', null, '7', '2', null);
INSERT INTO `kg_module` VALUES ('23', '照片删除', '/picture/deletePicture.do', null, '7', '2', null);
INSERT INTO `kg_module` VALUES ('24', '教师列表', '/teacher/loadTeacher.do', null, '8', '2', null);
INSERT INTO `kg_module` VALUES ('25', '教师修改', '/teacher/updTeacher.do', null, '8', '2', null);
INSERT INTO `kg_module` VALUES ('26', '教师删除', '/teacher/deleteTeacher.do', null, '8', '2', null);
INSERT INTO `kg_module` VALUES ('27', '教师审核', '/teacher/t_changeState.do', null, '8', '2', null);
INSERT INTO `kg_module` VALUES ('28', '日志列表', '/log/tea_loadLog.do', null, '8', '2', null);
INSERT INTO `kg_module` VALUES ('29', '日志修改', '/log/tea_updLog.do', null, '8', '2', null);
INSERT INTO `kg_module` VALUES ('30', '日志删除', '/log/tea_deleteLog.do', null, '8', '2', null);
INSERT INTO `kg_module` VALUES ('31', '日志审核', '/log/tea_changeState.do', null, '8', '2', null);
INSERT INTO `kg_module` VALUES ('32', '足迹列表', '/footprint/tea_loadFootprint.do', null, '8', '2', null);
INSERT INTO `kg_module` VALUES ('33', '足迹修改', '/footprint/tea_updFootprint.do', null, '8', '2', null);
INSERT INTO `kg_module` VALUES ('34', '足迹删除', '/footprint/tea_deleteFootprint.do', null, '8', '2', null);
INSERT INTO `kg_module` VALUES ('35', '园区列表', '/garden/loadGarden.do', null, '9', '2', null);
INSERT INTO `kg_module` VALUES ('36', '园区增加', '/garden/addGarden.do', null, '9', '2', null);
INSERT INTO `kg_module` VALUES ('37', '园区修改', '/garden/updGarden.do', null, '9', '2', null);
INSERT INTO `kg_module` VALUES ('38', '园区删除', '/garden/deleteGarden.do', null, '9', '2', null);
INSERT INTO `kg_module` VALUES ('39', '管理员列表', '/admin//loadAdmin.do', null, '10', '2', null);
INSERT INTO `kg_module` VALUES ('40', '管理员修改', '/admin/updAdmin.do', null, '10', '2', null);
INSERT INTO `kg_module` VALUES ('41', '管理员增加', '/admin/addAdmin.do', null, '10', '2', null);
INSERT INTO `kg_module` VALUES ('42', '管理员删除', '/admin/deleteAdmin.do', null, '10', '2', null);
INSERT INTO `kg_module` VALUES ('43', '角色管理', 'role.html', 'role-memu', '5', '1', null);
INSERT INTO `kg_module` VALUES ('44', '角色列表', '/role/loadRole.do', null, '43', '2', null);
INSERT INTO `kg_module` VALUES ('45', '角色增加', '/role/addRole.do', null, '43', '2', null);
INSERT INTO `kg_module` VALUES ('46', '角色修改', '/role/updRole.do', null, '43', '2', null);
INSERT INTO `kg_module` VALUES ('47', '角色删除', '/role/deleteRole.do', null, '43', '2', null);
INSERT INTO `kg_module` VALUES ('48', '权限设置', '/role/setModules.do', null, '43', '2', null);
INSERT INTO `kg_module` VALUES ('49', '班级', '#', null, null, '0', '4');
INSERT INTO `kg_module` VALUES ('50', '班级风彩管理', 'style.html', 'style-menu', '49', '1', null);
INSERT INTO `kg_module` VALUES ('51', '班级风彩列表', '/style/loadStyle.do', null, '50', '2', null);
INSERT INTO `kg_module` VALUES ('52', '班级风彩修改', '/style/updateStyle.do', null, '50', '2', null);
INSERT INTO `kg_module` VALUES ('53', '班级风彩删除', '/style/deleteStyle.do', null, '50', '2', null);
INSERT INTO `kg_module` VALUES ('54', '工作日记列表', '/diary/loadDiary.do', null, '8', '2', null);
INSERT INTO `kg_module` VALUES ('55', '工作日记删除', '/diary/deleteDiary.do', null, '8', '2', null);

-- ----------------------------
-- Table structure for kg_operator_log
-- ----------------------------
DROP TABLE IF EXISTS `kg_operator_log`;
CREATE TABLE `kg_operator_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `level` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `method` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `msg` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=504 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of kg_operator_log
-- ----------------------------
INSERT INTO `kg_operator_log` VALUES ('1', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-01-23 16:15:01');
INSERT INTO `kg_operator_log` VALUES ('2', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.118.3.93', '2017-01-23 15:46:47');
INSERT INTO `kg_operator_log` VALUES ('3', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:yuefeng,125.118.3.93', '2017-01-23 15:50:21');
INSERT INTO `kg_operator_log` VALUES ('4', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.118.3.93', '2017-01-23 16:17:08');
INSERT INTO `kg_operator_log` VALUES ('5', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-23 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('6', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-24 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('7', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-24 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('8', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-24 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('9', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-24 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('10', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-25 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('11', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-25 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('12', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-25 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('13', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-25 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('14', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-26 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('15', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-26 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('16', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-26 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('17', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-26 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('18', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-27 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('19', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-27 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('20', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-27 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('21', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-27 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('22', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-28 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('23', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-28 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('24', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-28 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('25', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-28 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('26', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-29 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('27', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-29 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('28', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-29 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('29', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-29 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('30', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-30 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('31', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-30 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('32', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-30 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('33', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-30 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('34', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-31 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('35', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-31 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('36', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-31 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('37', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-01-31 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('38', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-01 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('39', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-01 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('40', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-01 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('41', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-01 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('42', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-02 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('43', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-02 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('44', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-02 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('45', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-02 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('46', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-03 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('47', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-03 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('48', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-03 09:07:13');
INSERT INTO `kg_operator_log` VALUES ('49', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.200.233.46', '2017-02-03 10:00:39');
INSERT INTO `kg_operator_log` VALUES ('50', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-03 10:22:47');
INSERT INTO `kg_operator_log` VALUES ('51', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-03 11:22:44');
INSERT INTO `kg_operator_log` VALUES ('52', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-03 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('53', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-03 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('54', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-03 12:00:03');
INSERT INTO `kg_operator_log` VALUES ('55', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.200.233.46', '2017-02-03 12:59:43');
INSERT INTO `kg_operator_log` VALUES ('56', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-03 13:14:13');
INSERT INTO `kg_operator_log` VALUES ('57', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-03 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('58', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-04 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('59', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-04 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('60', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.200.233.46', '2017-02-04 09:58:34');
INSERT INTO `kg_operator_log` VALUES ('61', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.200.233.46', '2017-02-04 11:23:40');
INSERT INTO `kg_operator_log` VALUES ('62', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-04 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('63', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-04 13:11:55');
INSERT INTO `kg_operator_log` VALUES ('64', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-04 13:54:59');
INSERT INTO `kg_operator_log` VALUES ('65', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.200.233.46', '2017-02-04 14:06:25');
INSERT INTO `kg_operator_log` VALUES ('66', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-04 14:45:07');
INSERT INTO `kg_operator_log` VALUES ('67', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-04 17:04:58');
INSERT INTO `kg_operator_log` VALUES ('68', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-04 17:12:57');
INSERT INTO `kg_operator_log` VALUES ('69', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-04 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('70', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-05 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('71', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-05 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('72', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-05 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('73', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-05 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('74', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-06 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('75', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-06 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('76', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-06 10:46:25');
INSERT INTO `kg_operator_log` VALUES ('77', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-06 11:01:43');
INSERT INTO `kg_operator_log` VALUES ('78', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 11:12:50');
INSERT INTO `kg_operator_log` VALUES ('79', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 11:17:50');
INSERT INTO `kg_operator_log` VALUES ('80', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 11:19:58');
INSERT INTO `kg_operator_log` VALUES ('81', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 11:25:40');
INSERT INTO `kg_operator_log` VALUES ('82', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 11:26:27');
INSERT INTO `kg_operator_log` VALUES ('83', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.118.0.55', '2017-02-06 11:27:28');
INSERT INTO `kg_operator_log` VALUES ('84', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 11:46:11');
INSERT INTO `kg_operator_log` VALUES ('85', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.118.0.55', '2017-02-06 11:48:18');
INSERT INTO `kg_operator_log` VALUES ('86', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 11:48:29');
INSERT INTO `kg_operator_log` VALUES ('87', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-06 11:48:29');
INSERT INTO `kg_operator_log` VALUES ('88', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-06 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('89', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-06 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('90', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-06 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('91', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 12:40:23');
INSERT INTO `kg_operator_log` VALUES ('92', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 12:47:31');
INSERT INTO `kg_operator_log` VALUES ('93', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 13:18:41');
INSERT INTO `kg_operator_log` VALUES ('94', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 13:19:32');
INSERT INTO `kg_operator_log` VALUES ('95', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 13:30:10');
INSERT INTO `kg_operator_log` VALUES ('96', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 13:42:45');
INSERT INTO `kg_operator_log` VALUES ('97', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 13:55:35');
INSERT INTO `kg_operator_log` VALUES ('98', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-06 14:03:57');
INSERT INTO `kg_operator_log` VALUES ('99', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 14:19:21');
INSERT INTO `kg_operator_log` VALUES ('100', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 14:47:49');
INSERT INTO `kg_operator_log` VALUES ('101', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 14:49:43');
INSERT INTO `kg_operator_log` VALUES ('102', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-06 15:13:32');
INSERT INTO `kg_operator_log` VALUES ('103', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 15:45:11');
INSERT INTO `kg_operator_log` VALUES ('104', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 15:51:14');
INSERT INTO `kg_operator_log` VALUES ('105', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 16:04:54');
INSERT INTO `kg_operator_log` VALUES ('106', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-06 16:47:34');
INSERT INTO `kg_operator_log` VALUES ('107', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 17:22:49');
INSERT INTO `kg_operator_log` VALUES ('108', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-06 17:24:35');
INSERT INTO `kg_operator_log` VALUES ('109', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-06 17:29:04');
INSERT INTO `kg_operator_log` VALUES ('110', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-06 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('111', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-07 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('112', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-07 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('113', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-07 08:47:13');
INSERT INTO `kg_operator_log` VALUES ('114', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-07 08:49:43');
INSERT INTO `kg_operator_log` VALUES ('115', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-07 11:17:23');
INSERT INTO `kg_operator_log` VALUES ('116', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-27 11:23:55');
INSERT INTO `kg_operator_log` VALUES ('117', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-07 11:29:19');
INSERT INTO `kg_operator_log` VALUES ('118', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-07 11:32:53');
INSERT INTO `kg_operator_log` VALUES ('119', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 00:00:59');
INSERT INTO `kg_operator_log` VALUES ('120', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 00:02:58');
INSERT INTO `kg_operator_log` VALUES ('121', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-07 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('122', 'com.dq.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-07 12:00:05');
INSERT INTO `kg_operator_log` VALUES ('123', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.118.0.55', '2017-02-07 12:30:28');
INSERT INTO `kg_operator_log` VALUES ('124', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.118.0.55', '2017-02-07 12:33:35');
INSERT INTO `kg_operator_log` VALUES ('125', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 01:01:49');
INSERT INTO `kg_operator_log` VALUES ('126', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 01:07:52');
INSERT INTO `kg_operator_log` VALUES ('127', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 01:27:03');
INSERT INTO `kg_operator_log` VALUES ('128', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 01:44:02');
INSERT INTO `kg_operator_log` VALUES ('129', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 02:22:40');
INSERT INTO `kg_operator_log` VALUES ('130', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 02:57:03');
INSERT INTO `kg_operator_log` VALUES ('131', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 03:06:56');
INSERT INTO `kg_operator_log` VALUES ('132', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 03:14:42');
INSERT INTO `kg_operator_log` VALUES ('133', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 03:16:31');
INSERT INTO `kg_operator_log` VALUES ('134', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 03:35:53');
INSERT INTO `kg_operator_log` VALUES ('135', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 04:02:47');
INSERT INTO `kg_operator_log` VALUES ('136', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 04:10:00');
INSERT INTO `kg_operator_log` VALUES ('137', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.118.0.55', '2017-02-07 16:07:11');
INSERT INTO `kg_operator_log` VALUES ('138', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 04:28:34');
INSERT INTO `kg_operator_log` VALUES ('139', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 04:28:48');
INSERT INTO `kg_operator_log` VALUES ('140', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.118.0.55', '2017-02-07 16:32:37');
INSERT INTO `kg_operator_log` VALUES ('141', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 05:15:02');
INSERT INTO `kg_operator_log` VALUES ('142', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 08:22:43');
INSERT INTO `kg_operator_log` VALUES ('143', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 08:25:00');
INSERT INTO `kg_operator_log` VALUES ('144', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-07 08:45:25');
INSERT INTO `kg_operator_log` VALUES ('145', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-07 08:53:06');
INSERT INTO `kg_operator_log` VALUES ('146', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,122.224.166.70', '2017-02-07 20:55:44');
INSERT INTO `kg_operator_log` VALUES ('147', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-07 21:00:55');
INSERT INTO `kg_operator_log` VALUES ('148', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-07 21:01:32');
INSERT INTO `kg_operator_log` VALUES ('149', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,122.224.166.70', '2017-02-07 21:08:28');
INSERT INTO `kg_operator_log` VALUES ('150', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-07 21:08:29');
INSERT INTO `kg_operator_log` VALUES ('151', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,122.224.166.70', '2017-02-07 21:17:02');
INSERT INTO `kg_operator_log` VALUES ('152', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-07 22:19:22');
INSERT INTO `kg_operator_log` VALUES ('153', 'com.kg.serviceImpl.RedisServiceImpl', 'ERROR', 'getRedisClient', 'getRedisClent error', '2017-02-07 22:20:11');
INSERT INTO `kg_operator_log` VALUES ('154', 'com.kg.serviceImpl.RedisServiceImpl', 'ERROR', 'getRedisClient', 'getRedisClent error', '2017-02-07 22:20:16');
INSERT INTO `kg_operator_log` VALUES ('155', 'com.kg.serviceImpl.RedisServiceImpl', 'ERROR', 'getRedisClient', 'getRedisClent error', '2017-02-07 22:20:24');
INSERT INTO `kg_operator_log` VALUES ('156', 'com.kg.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-08 00:00:00');
INSERT INTO `kg_operator_log` VALUES ('157', 'com.kg.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-08 06:00:00');
INSERT INTO `kg_operator_log` VALUES ('158', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 09:47:30');
INSERT INTO `kg_operator_log` VALUES ('159', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 09:55:34');
INSERT INTO `kg_operator_log` VALUES ('160', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 10:01:22');
INSERT INTO `kg_operator_log` VALUES ('161', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-08 10:17:20');
INSERT INTO `kg_operator_log` VALUES ('162', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 10:48:10');
INSERT INTO `kg_operator_log` VALUES ('163', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-08 11:01:02');
INSERT INTO `kg_operator_log` VALUES ('164', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 11:09:25');
INSERT INTO `kg_operator_log` VALUES ('165', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 11:18:48');
INSERT INTO `kg_operator_log` VALUES ('166', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 11:35:00');
INSERT INTO `kg_operator_log` VALUES ('167', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 11:37:04');
INSERT INTO `kg_operator_log` VALUES ('168', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 11:54:59');
INSERT INTO `kg_operator_log` VALUES ('169', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 15:21:02');
INSERT INTO `kg_operator_log` VALUES ('170', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 15:22:02');
INSERT INTO `kg_operator_log` VALUES ('171', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.118.0.55', '2017-02-08 15:33:54');
INSERT INTO `kg_operator_log` VALUES ('172', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.118.0.55', '2017-02-08 15:34:16');
INSERT INTO `kg_operator_log` VALUES ('173', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 15:48:30');
INSERT INTO `kg_operator_log` VALUES ('174', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 16:09:11');
INSERT INTO `kg_operator_log` VALUES ('175', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.118.0.55', '2017-02-08 16:21:15');
INSERT INTO `kg_operator_log` VALUES ('176', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 16:24:00');
INSERT INTO `kg_operator_log` VALUES ('177', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 16:24:15');
INSERT INTO `kg_operator_log` VALUES ('178', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 16:24:42');
INSERT INTO `kg_operator_log` VALUES ('179', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 16:25:06');
INSERT INTO `kg_operator_log` VALUES ('180', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-08 16:59:40');
INSERT INTO `kg_operator_log` VALUES ('181', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.118.0.55', '2017-02-08 17:04:04');
INSERT INTO `kg_operator_log` VALUES ('182', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.118.0.55', '2017-02-08 17:18:59');
INSERT INTO `kg_operator_log` VALUES ('183', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-08 17:22:12');
INSERT INTO `kg_operator_log` VALUES ('184', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,122.224.166.70', '2017-02-08 20:49:52');
INSERT INTO `kg_operator_log` VALUES ('185', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-09 08:49:33');
INSERT INTO `kg_operator_log` VALUES ('186', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-09 09:45:46');
INSERT INTO `kg_operator_log` VALUES ('187', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-09 10:30:19');
INSERT INTO `kg_operator_log` VALUES ('188', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-09 10:52:53');
INSERT INTO `kg_operator_log` VALUES ('189', 'com.kg.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-09 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('190', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-09 13:06:35');
INSERT INTO `kg_operator_log` VALUES ('191', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-09 14:12:50');
INSERT INTO `kg_operator_log` VALUES ('192', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-09 16:26:12');
INSERT INTO `kg_operator_log` VALUES ('193', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-10 11:39:59');
INSERT INTO `kg_operator_log` VALUES ('194', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-10 13:16:36');
INSERT INTO `kg_operator_log` VALUES ('195', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 10 16:57:54 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=16AACE325E6228D06BEFC2FFEB3AE0B7', '2017-02-10 16:57:54');
INSERT INTO `kg_operator_log` VALUES ('196', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 10 16:59:37 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=8CA976260550F22ADD35DA78A695FB3A', '2017-02-10 16:59:37');
INSERT INTO `kg_operator_log` VALUES ('197', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 10 16:59:54 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=2232FEC5805C6E942F591F8E59653AC7', '2017-02-10 16:59:54');
INSERT INTO `kg_operator_log` VALUES ('198', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 10 16:59:54 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160038</statusCode>\n    <statusMsg>短信验证码发送过频繁</statusMsg>\n</Response>\n', '2017-02-10 16:59:54');
INSERT INTO `kg_operator_log` VALUES ('199', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 10 17:00:35 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=AE4BA34D4F91B73BFE5904235D2CB56D', '2017-02-10 17:00:35');
INSERT INTO `kg_operator_log` VALUES ('200', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 10 17:13:56 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=C5436933617B66AB9074B73B73430966', '2017-02-10 17:13:56');
INSERT INTO `kg_operator_log` VALUES ('201', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 15:04:23');
INSERT INTO `kg_operator_log` VALUES ('202', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 15:05:12');
INSERT INTO `kg_operator_log` VALUES ('203', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 15:06:09');
INSERT INTO `kg_operator_log` VALUES ('204', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 15:14:53');
INSERT INTO `kg_operator_log` VALUES ('205', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 15:17:33');
INSERT INTO `kg_operator_log` VALUES ('206', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 15:21:48');
INSERT INTO `kg_operator_log` VALUES ('207', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 15:29:06');
INSERT INTO `kg_operator_log` VALUES ('208', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 15:33:23');
INSERT INTO `kg_operator_log` VALUES ('209', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 15:51:54');
INSERT INTO `kg_operator_log` VALUES ('210', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 15:54:32');
INSERT INTO `kg_operator_log` VALUES ('211', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 15:58:09');
INSERT INTO `kg_operator_log` VALUES ('212', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 16:11:49');
INSERT INTO `kg_operator_log` VALUES ('213', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 16:18:25');
INSERT INTO `kg_operator_log` VALUES ('214', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 16:20:53');
INSERT INTO `kg_operator_log` VALUES ('215', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 16:23:34');
INSERT INTO `kg_operator_log` VALUES ('216', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 16:26:44');
INSERT INTO `kg_operator_log` VALUES ('217', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 16:35:38');
INSERT INTO `kg_operator_log` VALUES ('218', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 16:39:44');
INSERT INTO `kg_operator_log` VALUES ('219', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 16:40:38');
INSERT INTO `kg_operator_log` VALUES ('220', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 16:43:29');
INSERT INTO `kg_operator_log` VALUES ('221', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 16:46:40');
INSERT INTO `kg_operator_log` VALUES ('222', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 16:49:41');
INSERT INTO `kg_operator_log` VALUES ('223', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 16:52:33');
INSERT INTO `kg_operator_log` VALUES ('224', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 16:56:56');
INSERT INTO `kg_operator_log` VALUES ('225', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 16:58:31');
INSERT INTO `kg_operator_log` VALUES ('226', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 16:59:47');
INSERT INTO `kg_operator_log` VALUES ('227', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:02:24');
INSERT INTO `kg_operator_log` VALUES ('228', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:02:53');
INSERT INTO `kg_operator_log` VALUES ('229', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:05:15');
INSERT INTO `kg_operator_log` VALUES ('230', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:05:47');
INSERT INTO `kg_operator_log` VALUES ('231', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:06:34');
INSERT INTO `kg_operator_log` VALUES ('232', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:08:28');
INSERT INTO `kg_operator_log` VALUES ('233', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:12:21');
INSERT INTO `kg_operator_log` VALUES ('234', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:14:44');
INSERT INTO `kg_operator_log` VALUES ('235', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:17:09');
INSERT INTO `kg_operator_log` VALUES ('236', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:18:40');
INSERT INTO `kg_operator_log` VALUES ('237', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:34:45');
INSERT INTO `kg_operator_log` VALUES ('238', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:42:30');
INSERT INTO `kg_operator_log` VALUES ('239', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:43:04');
INSERT INTO `kg_operator_log` VALUES ('240', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:43:34');
INSERT INTO `kg_operator_log` VALUES ('241', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:45:00');
INSERT INTO `kg_operator_log` VALUES ('242', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:47:49');
INSERT INTO `kg_operator_log` VALUES ('243', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-12 17:52:51');
INSERT INTO `kg_operator_log` VALUES ('244', 'com.kg.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-12 18:00:00');
INSERT INTO `kg_operator_log` VALUES ('245', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Sun Feb 12 19:03:40 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=FAE7C0A5EB39F3E1964F3F50937BCF22', '2017-02-12 19:03:40');
INSERT INTO `kg_operator_log` VALUES ('246', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Sun Feb 12 19:03:41 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160043</statusCode>\n    <statusMsg>应用与模板id不匹配</statusMsg>\n</Response>\n', '2017-02-12 19:03:41');
INSERT INTO `kg_operator_log` VALUES ('247', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Sun Feb 12 19:06:25 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=87829D324BE1F930934752DA9CBDCF4B', '2017-02-12 19:06:25');
INSERT INTO `kg_operator_log` VALUES ('248', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Sun Feb 12 19:06:25 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160043</statusCode>\n    <statusMsg>应用与模板id不匹配</statusMsg>\n</Response>\n', '2017-02-12 19:06:25');
INSERT INTO `kg_operator_log` VALUES ('249', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Sun Feb 12 19:11:25 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=D6E0AE8C1E409AD400A11989EABD27B0', '2017-02-12 19:11:25');
INSERT INTO `kg_operator_log` VALUES ('250', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Sun Feb 12 19:11:25 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160043</statusCode>\n    <statusMsg>应用与模板id不匹配</statusMsg>\n</Response>\n', '2017-02-12 19:11:25');
INSERT INTO `kg_operator_log` VALUES ('251', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Sun Feb 12 19:15:23 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=3D16322544F75AC995DC39AED518A9D8', '2017-02-12 19:15:23');
INSERT INTO `kg_operator_log` VALUES ('252', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-13 09:58:42');
INSERT INTO `kg_operator_log` VALUES ('253', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 10:18:22 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=F9AF0381F011482E64304C59C6A7503E', '2017-02-13 10:18:22');
INSERT INTO `kg_operator_log` VALUES ('254', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 10:24:20 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=7D33B77140CD001C6E8BD1305D1C8819', '2017-02-13 10:24:20');
INSERT INTO `kg_operator_log` VALUES ('255', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 10:25:38 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=ACF50EB9E273CD4AD0801DF51EBCDE65', '2017-02-13 10:25:38');
INSERT INTO `kg_operator_log` VALUES ('256', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 10:27:21 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=46A16A097AAAF1B6B3626124079E8186', '2017-02-13 10:27:21');
INSERT INTO `kg_operator_log` VALUES ('257', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 10:36:00 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=3E0DC63981D99AC03C9B3755582657A5', '2017-02-13 10:36:00');
INSERT INTO `kg_operator_log` VALUES ('258', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 10:36:46 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=AC353D98DAF2E7895C7687CBECB5C6A6', '2017-02-13 10:36:46');
INSERT INTO `kg_operator_log` VALUES ('259', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 10:37:19 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=6CF764DBAB6E9C319613BA61DCC7A32A', '2017-02-13 10:37:19');
INSERT INTO `kg_operator_log` VALUES ('260', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 10:37:19 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 10:37:19');
INSERT INTO `kg_operator_log` VALUES ('261', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 10:42:34 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=01FB9C38F3F4F150C7DEBB1FC492B6F5', '2017-02-13 10:42:34');
INSERT INTO `kg_operator_log` VALUES ('262', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 10:42:35 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 10:42:35');
INSERT INTO `kg_operator_log` VALUES ('263', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 10:45:25 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=A7104DFF4015319CD4E118FE7B3F8386', '2017-02-13 10:45:25');
INSERT INTO `kg_operator_log` VALUES ('264', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 10:45:26 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 10:45:26');
INSERT INTO `kg_operator_log` VALUES ('265', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-13 10:47:56');
INSERT INTO `kg_operator_log` VALUES ('266', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 11:32:03 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=2C75FB5DB214100226DCCB965A42886F', '2017-02-13 11:32:03');
INSERT INTO `kg_operator_log` VALUES ('267', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 11:32:48 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=60AD00E26EE0C7CB8811EEF923B8C744', '2017-02-13 11:32:48');
INSERT INTO `kg_operator_log` VALUES ('268', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 11:48:54 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=F7A824244E382C708BDB1ED0FCB6FE7B', '2017-02-13 11:48:54');
INSERT INTO `kg_operator_log` VALUES ('269', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 11:53:47 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=CEBD6FBFDE596C26227E82FBBD9BE6BF', '2017-02-13 11:53:47');
INSERT INTO `kg_operator_log` VALUES ('270', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 11:57:11 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=8494652DD8808FF1BDBC49A477FC7CC3', '2017-02-13 11:57:11');
INSERT INTO `kg_operator_log` VALUES ('271', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 11:57:21 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 11:57:21');
INSERT INTO `kg_operator_log` VALUES ('272', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 11:59:00 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=AB467AC02661A9174531446B66361ADE', '2017-02-13 11:59:00');
INSERT INTO `kg_operator_log` VALUES ('273', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 11:59:42 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=D99F2A48393D29071235720CC13D6D30', '2017-02-13 11:59:42');
INSERT INTO `kg_operator_log` VALUES ('274', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 11:59:43 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 11:59:43');
INSERT INTO `kg_operator_log` VALUES ('275', 'com.kg.quartz.DS', 'INFO', 'work', 'quartz', '2017-02-13 12:00:00');
INSERT INTO `kg_operator_log` VALUES ('276', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 13:03:51 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=11455BFF62EC7D42290AEAF84779A448', '2017-02-13 13:03:51');
INSERT INTO `kg_operator_log` VALUES ('277', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 13:03:51 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 13:03:51');
INSERT INTO `kg_operator_log` VALUES ('278', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 13:09:05 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=B7A4DAC9247217D84024B4EB550B3B1C', '2017-02-13 13:09:05');
INSERT INTO `kg_operator_log` VALUES ('279', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 13:09:06 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 13:09:06');
INSERT INTO `kg_operator_log` VALUES ('280', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 13:23:29 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=6C68BAD6DD35E5FE2802BBBF46290C02', '2017-02-13 13:23:29');
INSERT INTO `kg_operator_log` VALUES ('281', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 13:23:30 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 13:23:30');
INSERT INTO `kg_operator_log` VALUES ('282', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 13:28:13 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=15E31E21D11B34834132A40A66941122', '2017-02-13 13:28:13');
INSERT INTO `kg_operator_log` VALUES ('283', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 13:28:13 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 13:28:13');
INSERT INTO `kg_operator_log` VALUES ('284', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 13:29:01 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=B11A1BC37D6D24DE478DD4A397DC6CD1', '2017-02-13 13:29:01');
INSERT INTO `kg_operator_log` VALUES ('285', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 13:29:01 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 13:29:01');
INSERT INTO `kg_operator_log` VALUES ('286', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 15:33:39 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=5CD1964A69BEE06A0B6B56B59211B9CD', '2017-02-13 15:33:39');
INSERT INTO `kg_operator_log` VALUES ('287', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 15:34:57 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=47CAB7A06C5E13916EB58459328337F5', '2017-02-13 15:34:57');
INSERT INTO `kg_operator_log` VALUES ('288', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 16:48:00 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=7A333C8D4D3E3B4E3920AB904214BB06', '2017-02-13 16:48:00');
INSERT INTO `kg_operator_log` VALUES ('289', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 16:48:01 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 16:48:01');
INSERT INTO `kg_operator_log` VALUES ('290', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 18:46:16 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=A26805A6AC6E7C2B1A2A897F9CE8A7F5', '2017-02-13 18:46:16');
INSERT INTO `kg_operator_log` VALUES ('291', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 18:48:30 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=1B45CCD9DBC4615F826DCAEB0CC70C89', '2017-02-13 18:48:30');
INSERT INTO `kg_operator_log` VALUES ('292', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 18:48:30 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 18:48:30');
INSERT INTO `kg_operator_log` VALUES ('293', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 18:49:18 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=F96482620B9F7C43EFD06D0F1DBFE0FD', '2017-02-13 18:49:18');
INSERT INTO `kg_operator_log` VALUES ('294', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 18:49:19 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 18:49:19');
INSERT INTO `kg_operator_log` VALUES ('295', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 18:50:00 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=22EB40DA61FCD0180CDE64AB1014E73C', '2017-02-13 18:50:00');
INSERT INTO `kg_operator_log` VALUES ('296', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 18:50:00 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 18:50:00');
INSERT INTO `kg_operator_log` VALUES ('297', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 19:51:59 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=8A276EA2CD98A187A9DE41D0BD3558B4', '2017-02-13 19:51:59');
INSERT INTO `kg_operator_log` VALUES ('298', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 19:52:01 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 19:52:01');
INSERT INTO `kg_operator_log` VALUES ('299', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 19:53:22 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=C8F9B8F7138C146B1937707FA623A191', '2017-02-13 19:53:22');
INSERT INTO `kg_operator_log` VALUES ('300', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 19:53:22 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 19:53:22');
INSERT INTO `kg_operator_log` VALUES ('301', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 20:23:48 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=05D3A6750ED035978697666B82BEB58F', '2017-02-13 20:23:48');
INSERT INTO `kg_operator_log` VALUES ('302', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Mon Feb 13 20:23:49 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-13 20:23:49');
INSERT INTO `kg_operator_log` VALUES ('303', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-14 14:00:29');
INSERT INTO `kg_operator_log` VALUES ('304', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-14 17:05:30');
INSERT INTO `kg_operator_log` VALUES ('305', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-14 19:41:39');
INSERT INTO `kg_operator_log` VALUES ('306', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-14 20:08:22');
INSERT INTO `kg_operator_log` VALUES ('307', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-15 10:08:18');
INSERT INTO `kg_operator_log` VALUES ('308', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-16 11:51:21');
INSERT INTO `kg_operator_log` VALUES ('309', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-16 11:51:43');
INSERT INTO `kg_operator_log` VALUES ('310', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-16 11:52:21');
INSERT INTO `kg_operator_log` VALUES ('311', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-16 15:45:55');
INSERT INTO `kg_operator_log` VALUES ('312', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-17 08:58:20');
INSERT INTO `kg_operator_log` VALUES ('313', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-17 09:10:06');
INSERT INTO `kg_operator_log` VALUES ('314', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 17 11:28:03 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=B9E79C36E9A353230D1C46DE0124726E', '2017-02-17 11:28:03');
INSERT INTO `kg_operator_log` VALUES ('315', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 17 11:31:15 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=A30DF9982A3B577624CAB3CCC72EC58E', '2017-02-17 11:31:15');
INSERT INTO `kg_operator_log` VALUES ('316', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 17 11:50:06 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=7854A5871C32F18FFC32ED914FA924BC', '2017-02-17 11:50:06');
INSERT INTO `kg_operator_log` VALUES ('317', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 17 11:51:47 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=70F5D071818C5C7C40F46F5455AD5E23', '2017-02-17 11:51:47');
INSERT INTO `kg_operator_log` VALUES ('318', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-17 11:59:16');
INSERT INTO `kg_operator_log` VALUES ('319', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-17 15:18:42');
INSERT INTO `kg_operator_log` VALUES ('320', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-17 15:23:03');
INSERT INTO `kg_operator_log` VALUES ('321', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-17 15:46:22');
INSERT INTO `kg_operator_log` VALUES ('322', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,183.128.223.2', '2017-02-17 16:55:28');
INSERT INTO `kg_operator_log` VALUES ('323', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.198.133.146', '2017-02-17 16:55:58');
INSERT INTO `kg_operator_log` VALUES ('324', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,183.128.223.2', '2017-02-17 16:57:32');
INSERT INTO `kg_operator_log` VALUES ('325', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.198.133.146', '2017-02-17 17:03:57');
INSERT INTO `kg_operator_log` VALUES ('326', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-19 13:05:48');
INSERT INTO `kg_operator_log` VALUES ('327', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-19 13:20:52');
INSERT INTO `kg_operator_log` VALUES ('328', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 09:30:12');
INSERT INTO `kg_operator_log` VALUES ('329', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 09:31:21');
INSERT INTO `kg_operator_log` VALUES ('330', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 09:37:45');
INSERT INTO `kg_operator_log` VALUES ('331', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 10:14:26');
INSERT INTO `kg_operator_log` VALUES ('332', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 10:25:10');
INSERT INTO `kg_operator_log` VALUES ('333', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 10:27:51');
INSERT INTO `kg_operator_log` VALUES ('334', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 10:28:38');
INSERT INTO `kg_operator_log` VALUES ('335', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 10:32:34');
INSERT INTO `kg_operator_log` VALUES ('336', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 10:46:06');
INSERT INTO `kg_operator_log` VALUES ('337', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 11:33:03');
INSERT INTO `kg_operator_log` VALUES ('338', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 12:43:22');
INSERT INTO `kg_operator_log` VALUES ('339', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 12:47:57');
INSERT INTO `kg_operator_log` VALUES ('340', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 13:43:11');
INSERT INTO `kg_operator_log` VALUES ('341', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 13:45:09');
INSERT INTO `kg_operator_log` VALUES ('342', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 13:59:31');
INSERT INTO `kg_operator_log` VALUES ('343', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 14:02:31');
INSERT INTO `kg_operator_log` VALUES ('344', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 14:06:42');
INSERT INTO `kg_operator_log` VALUES ('345', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 15:04:45');
INSERT INTO `kg_operator_log` VALUES ('346', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 15:21:57');
INSERT INTO `kg_operator_log` VALUES ('347', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 15:25:13');
INSERT INTO `kg_operator_log` VALUES ('348', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 15:26:40');
INSERT INTO `kg_operator_log` VALUES ('349', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 15:33:23');
INSERT INTO `kg_operator_log` VALUES ('350', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 15:40:18');
INSERT INTO `kg_operator_log` VALUES ('351', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 15:46:17');
INSERT INTO `kg_operator_log` VALUES ('352', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 15:53:10');
INSERT INTO `kg_operator_log` VALUES ('353', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.120.62.186', '2017-02-20 16:49:50');
INSERT INTO `kg_operator_log` VALUES ('354', 'com.dq.controller.AdminController', 'INFO', 'login', '管理员登录:admin,127.0.0.1', '2017-02-20 16:25:15');
INSERT INTO `kg_operator_log` VALUES ('355', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 17:16:45');
INSERT INTO `kg_operator_log` VALUES ('356', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 17:18:41');
INSERT INTO `kg_operator_log` VALUES ('357', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 17:19:40');
INSERT INTO `kg_operator_log` VALUES ('358', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'request====<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-20 17:30:51');
INSERT INTO `kg_operator_log` VALUES ('359', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'buy======result:<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-20 17:30:52');
INSERT INTO `kg_operator_log` VALUES ('360', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-20 19:52:36');
INSERT INTO `kg_operator_log` VALUES ('361', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-21 08:47:53');
INSERT INTO `kg_operator_log` VALUES ('362', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'request====<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-21 08:48:01');
INSERT INTO `kg_operator_log` VALUES ('363', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'buy======result:<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-21 08:48:01');
INSERT INTO `kg_operator_log` VALUES ('364', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'request====<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-21 09:01:57');
INSERT INTO `kg_operator_log` VALUES ('365', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'buy======result:<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-21 09:02:12');
INSERT INTO `kg_operator_log` VALUES ('366', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'request====<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-21 09:47:24');
INSERT INTO `kg_operator_log` VALUES ('367', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'buy======result:<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-21 09:47:25');
INSERT INTO `kg_operator_log` VALUES ('368', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'request====<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-21 09:51:36');
INSERT INTO `kg_operator_log` VALUES ('369', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'buy======result:<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-21 09:51:36');
INSERT INTO `kg_operator_log` VALUES ('370', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'request====<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-21 10:45:02');
INSERT INTO `kg_operator_log` VALUES ('371', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'buy======result:<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-21 10:45:03');
INSERT INTO `kg_operator_log` VALUES ('372', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'request====<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-21 10:46:09');
INSERT INTO `kg_operator_log` VALUES ('373', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'buy======result:<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-21 10:46:09');
INSERT INTO `kg_operator_log` VALUES ('374', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-21 10:47:14');
INSERT INTO `kg_operator_log` VALUES ('375', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'request====<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-21 10:59:22');
INSERT INTO `kg_operator_log` VALUES ('376', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'buy======result:<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-21 10:59:23');
INSERT INTO `kg_operator_log` VALUES ('377', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-21 11:04:22');
INSERT INTO `kg_operator_log` VALUES ('378', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-21 11:20:05');
INSERT INTO `kg_operator_log` VALUES ('379', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-21 11:20:54');
INSERT INTO `kg_operator_log` VALUES ('380', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-21 11:22:32');
INSERT INTO `kg_operator_log` VALUES ('381', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-21 11:24:19');
INSERT INTO `kg_operator_log` VALUES ('382', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-21 11:25:07');
INSERT INTO `kg_operator_log` VALUES ('383', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-21 13:06:39');
INSERT INTO `kg_operator_log` VALUES ('384', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-21 13:11:25');
INSERT INTO `kg_operator_log` VALUES ('385', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-21 13:45:02');
INSERT INTO `kg_operator_log` VALUES ('386', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-21 13:48:32');
INSERT INTO `kg_operator_log` VALUES ('387', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-21 14:00:10');
INSERT INTO `kg_operator_log` VALUES ('388', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-21 14:00:41');
INSERT INTO `kg_operator_log` VALUES ('389', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.120.62.186', '2017-02-21 16:59:54');
INSERT INTO `kg_operator_log` VALUES ('390', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'request====<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-22 09:57:23');
INSERT INTO `kg_operator_log` VALUES ('391', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'buy======result:<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-22 09:57:23');
INSERT INTO `kg_operator_log` VALUES ('392', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'request====<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-22 10:36:06');
INSERT INTO `kg_operator_log` VALUES ('393', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'buy======result:<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[商户号mch_id与appid不匹配]]></return_msg></xml>', '2017-02-22 10:36:06');
INSERT INTO `kg_operator_log` VALUES ('394', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'request====<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[签名错误]]></return_msg></xml>', '2017-02-22 10:40:27');
INSERT INTO `kg_operator_log` VALUES ('395', 'com.dq.serviceImpl.GroupOrderServiceImpl', 'INFO', 'subGOpay', 'buy======result:<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[签名错误]]></return_msg></xml>', '2017-02-22 10:40:27');
INSERT INTO `kg_operator_log` VALUES ('396', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.120.62.186', '2017-02-22 10:56:54');
INSERT INTO `kg_operator_log` VALUES ('397', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.120.62.186', '2017-02-22 11:22:44');
INSERT INTO `kg_operator_log` VALUES ('398', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.120.62.186', '2017-02-22 11:38:59');
INSERT INTO `kg_operator_log` VALUES ('399', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.120.62.186', '2017-02-22 12:39:35');
INSERT INTO `kg_operator_log` VALUES ('400', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-22 13:11:37');
INSERT INTO `kg_operator_log` VALUES ('401', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-22 13:15:51');
INSERT INTO `kg_operator_log` VALUES ('402', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-22 13:18:22');
INSERT INTO `kg_operator_log` VALUES ('403', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-22 13:20:16');
INSERT INTO `kg_operator_log` VALUES ('404', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-22 13:40:32');
INSERT INTO `kg_operator_log` VALUES ('405', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-22 13:44:24');
INSERT INTO `kg_operator_log` VALUES ('406', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:yangjun,0:0:0:0:0:0:0:1', '2017-02-22 13:48:39');
INSERT INTO `kg_operator_log` VALUES ('407', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-22 13:48:58');
INSERT INTO `kg_operator_log` VALUES ('408', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:yangjun,0:0:0:0:0:0:0:1', '2017-02-22 13:49:09');
INSERT INTO `kg_operator_log` VALUES ('409', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-22 13:52:01');
INSERT INTO `kg_operator_log` VALUES ('410', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:yangjun,0:0:0:0:0:0:0:1', '2017-02-22 13:52:27');
INSERT INTO `kg_operator_log` VALUES ('411', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-22 14:03:55');
INSERT INTO `kg_operator_log` VALUES ('412', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:yangjun,0:0:0:0:0:0:0:1', '2017-02-22 14:04:49');
INSERT INTO `kg_operator_log` VALUES ('413', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-22 14:16:57');
INSERT INTO `kg_operator_log` VALUES ('414', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:yangjun,0:0:0:0:0:0:0:1', '2017-02-22 14:17:39');
INSERT INTO `kg_operator_log` VALUES ('415', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-22 14:22:23');
INSERT INTO `kg_operator_log` VALUES ('416', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:yangjun,0:0:0:0:0:0:0:1', '2017-02-22 14:22:42');
INSERT INTO `kg_operator_log` VALUES ('417', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:yangjun,0:0:0:0:0:0:0:1', '2017-02-22 14:26:16');
INSERT INTO `kg_operator_log` VALUES ('418', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:yangjun,0:0:0:0:0:0:0:1', '2017-02-22 14:27:29');
INSERT INTO `kg_operator_log` VALUES ('419', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:yangjun,0:0:0:0:0:0:0:1', '2017-02-22 14:28:07');
INSERT INTO `kg_operator_log` VALUES ('420', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:yangjun,0:0:0:0:0:0:0:1', '2017-02-22 14:37:08');
INSERT INTO `kg_operator_log` VALUES ('421', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:yangjun,0:0:0:0:0:0:0:1', '2017-02-22 14:37:45');
INSERT INTO `kg_operator_log` VALUES ('422', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:yangjun,0:0:0:0:0:0:0:1', '2017-02-22 14:42:38');
INSERT INTO `kg_operator_log` VALUES ('423', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.120.62.186', '2017-02-22 16:51:31');
INSERT INTO `kg_operator_log` VALUES ('424', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.120.62.186', '2017-02-22 16:52:13');
INSERT INTO `kg_operator_log` VALUES ('425', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-22 17:20:56');
INSERT INTO `kg_operator_log` VALUES ('426', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-22 21:43:45');
INSERT INTO `kg_operator_log` VALUES ('427', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.120.62.186', '2017-02-23 15:16:52');
INSERT INTO `kg_operator_log` VALUES ('428', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:18:11 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=89640F244CC223F2B664AAEE04A3CD1D', '2017-02-23 16:18:11');
INSERT INTO `kg_operator_log` VALUES ('429', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:18:11 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>111180</statusCode>\n    <statusMsg>【账号】应用已暂停</statusMsg>\n</Response>\n', '2017-02-23 16:18:11');
INSERT INTO `kg_operator_log` VALUES ('430', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:25:01 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=4D9B24C3B1A958EA692E54BD2AD5D183', '2017-02-23 16:25:01');
INSERT INTO `kg_operator_log` VALUES ('431', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:25:02 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160043</statusCode>\n    <statusMsg>应用与模板id不匹配</statusMsg>\n</Response>\n', '2017-02-23 16:25:02');
INSERT INTO `kg_operator_log` VALUES ('432', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:25:22 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=7BCCD85E7CB8CEDFA9FB1D10CE86F442', '2017-02-23 16:25:22');
INSERT INTO `kg_operator_log` VALUES ('433', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:25:22 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160043</statusCode>\n    <statusMsg>应用与模板id不匹配</statusMsg>\n</Response>\n', '2017-02-23 16:25:22');
INSERT INTO `kg_operator_log` VALUES ('434', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:26:51 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=DAF5FCD9D3A3FFD4B294DE269118AE48', '2017-02-23 16:26:51');
INSERT INTO `kg_operator_log` VALUES ('435', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:26:51 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160043</statusCode>\n    <statusMsg>应用与模板id不匹配</statusMsg>\n</Response>\n', '2017-02-23 16:26:51');
INSERT INTO `kg_operator_log` VALUES ('436', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:29:34 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=CA0F2179BBD59B78A65E2A9B89C2FAA7', '2017-02-23 16:29:34');
INSERT INTO `kg_operator_log` VALUES ('437', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:29:34 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160043</statusCode>\n    <statusMsg>应用与模板id不匹配</statusMsg>\n</Response>\n', '2017-02-23 16:29:34');
INSERT INTO `kg_operator_log` VALUES ('438', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:29:37 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=2093BDC151907B8F41A915970E3C4C7C', '2017-02-23 16:29:37');
INSERT INTO `kg_operator_log` VALUES ('439', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:29:37 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160043</statusCode>\n    <statusMsg>应用与模板id不匹配</statusMsg>\n</Response>\n', '2017-02-23 16:29:37');
INSERT INTO `kg_operator_log` VALUES ('440', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:31:29 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=6D7550E408EF0C1C21BA85B9C8477225', '2017-02-23 16:31:29');
INSERT INTO `kg_operator_log` VALUES ('441', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:31:34 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>111180</statusCode>\n    <statusMsg>【账号】应用已暂停</statusMsg>\n</Response>\n', '2017-02-23 16:31:34');
INSERT INTO `kg_operator_log` VALUES ('442', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:34:49 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=9668E1B57A8587AA7244EE230104F498', '2017-02-23 16:34:49');
INSERT INTO `kg_operator_log` VALUES ('443', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:34:51 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160043</statusCode>\n    <statusMsg>应用与模板id不匹配</statusMsg>\n</Response>\n', '2017-02-23 16:34:51');
INSERT INTO `kg_operator_log` VALUES ('444', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:38:44 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=65913BC314DABF9D737EEABE032DC190', '2017-02-23 16:38:44');
INSERT INTO `kg_operator_log` VALUES ('445', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.204.88.104', '2017-02-23 16:40:14');
INSERT INTO `kg_operator_log` VALUES ('446', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:41:20 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=7AEA2AC75546BD7BC059811B9E8BDF61', '2017-02-23 16:41:20');
INSERT INTO `kg_operator_log` VALUES ('447', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:45:24 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=30A7FC70DB5AB7803F3A9A727F1065BF', '2017-02-23 16:45:24');
INSERT INTO `kg_operator_log` VALUES ('448', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:49:18 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=343C3E485AAB158E434668C61455CC0D', '2017-02-23 16:49:18');
INSERT INTO `kg_operator_log` VALUES ('449', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:52:53 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=165B6773A0B96A4D3E3E79620040FE87', '2017-02-23 16:52:53');
INSERT INTO `kg_operator_log` VALUES ('450', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:54:56 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=003D8FCB6A4149A1A09D2698FD1B6801', '2017-02-23 16:54:56');
INSERT INTO `kg_operator_log` VALUES ('451', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:55:22 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=484798696A074DA6D2F018DB2CBBF2D5', '2017-02-23 16:55:22');
INSERT INTO `kg_operator_log` VALUES ('452', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:56:25 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=9E0C3C90149788663FC183726CEF2ADC', '2017-02-23 16:56:25');
INSERT INTO `kg_operator_log` VALUES ('453', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-23 16:56:33');
INSERT INTO `kg_operator_log` VALUES ('454', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:59:16 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=FB6D650A80ACA30C934711F6C7200950', '2017-02-23 16:59:16');
INSERT INTO `kg_operator_log` VALUES ('455', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:59:39 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=C17BB241479B24051CB29848DE78337E', '2017-02-23 16:59:39');
INSERT INTO `kg_operator_log` VALUES ('456', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 16:59:39 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-23 16:59:39');
INSERT INTO `kg_operator_log` VALUES ('457', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 17:00:21 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=DEECA08B5BFAA112D39C871C429A1776', '2017-02-23 17:00:21');
INSERT INTO `kg_operator_log` VALUES ('458', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 17:00:21 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-23 17:00:21');
INSERT INTO `kg_operator_log` VALUES ('459', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 17:00:51 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=D8AFD26C41FD815766D23AD3FC9A8565', '2017-02-23 17:00:51');
INSERT INTO `kg_operator_log` VALUES ('460', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 17:01:35 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=EEA0F7C6C9345BD22083F78291B907EE', '2017-02-23 17:01:35');
INSERT INTO `kg_operator_log` VALUES ('461', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Thu Feb 23 17:03:52 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/aaf98f894d7439d8014d9370459d1636/SMS/TemplateSMS?sig=357F1CCA5BEBDC26B1F62854FE511F53', '2017-02-23 17:03:52');
INSERT INTO `kg_operator_log` VALUES ('462', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.119.2.243', '2017-02-23 22:24:51');
INSERT INTO `kg_operator_log` VALUES ('463', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.119.2.243', '2017-02-23 22:25:18');
INSERT INTO `kg_operator_log` VALUES ('464', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,125.119.2.243', '2017-02-24 01:55:26');
INSERT INTO `kg_operator_log` VALUES ('465', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 09:39:21 CST 2017 sendTemplateSMS url = https://https://app.cloopen.com:8883/2013-12-26/Accounts/8a216da85a3c0c39015a4089973601e1/SMS/TemplateSMS?sig=5478FFDCC2452CF9D57764CD5C5F20A5', '2017-02-24 09:39:21');
INSERT INTO `kg_operator_log` VALUES ('466', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'ERROR', 'error', 'https', '2017-02-24 09:39:24');
INSERT INTO `kg_operator_log` VALUES ('467', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 09:50:09 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/8a216da85a3c0c39015a4089973601e1/SMS/TemplateSMS?sig=2916098B445E53A69160C7D1148A0604', '2017-02-24 09:50:09');
INSERT INTO `kg_operator_log` VALUES ('468', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-24 10:02:25');
INSERT INTO `kg_operator_log` VALUES ('469', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-24 10:19:48');
INSERT INTO `kg_operator_log` VALUES ('470', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-24 10:29:10');
INSERT INTO `kg_operator_log` VALUES ('471', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,0:0:0:0:0:0:0:1', '2017-02-24 10:31:39');
INSERT INTO `kg_operator_log` VALUES ('472', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 10:54:00 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/8a216da85a3c0c39015a4089973601e1/SMS/TemplateSMS?sig=BE49959F29A6A959EEE66F732E5A8C99', '2017-02-24 10:54:00');
INSERT INTO `kg_operator_log` VALUES ('473', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.204.88.104', '2017-02-24 12:30:10');
INSERT INTO `kg_operator_log` VALUES ('474', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 12:31:01 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/8a216da85a3c0c39015a4089973601e1/SMS/TemplateSMS?sig=A331CFD0DE7BFBE0FD5E75EB86374E24', '2017-02-24 12:31:01');
INSERT INTO `kg_operator_log` VALUES ('475', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 12:33:57 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/8a216da85a3c0c39015a4089973601e1/SMS/TemplateSMS?sig=3B222FDA5E1EA6A2B9CD0837ECDA1E31', '2017-02-24 12:33:57');
INSERT INTO `kg_operator_log` VALUES ('476', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 13:16:51 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/8a216da85a3c0c39015a4089973601e1/SMS/TemplateSMS?sig=CADCBC5BEC83E979158DE418792202FB', '2017-02-24 13:16:51');
INSERT INTO `kg_operator_log` VALUES ('477', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 13:18:19 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/8a216da85a3c0c39015a4089973601e1/SMS/TemplateSMS?sig=8A2144B7CBC8540A2C2650BB24C08362', '2017-02-24 13:18:19');
INSERT INTO `kg_operator_log` VALUES ('478', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 13:19:59 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/8a216da85a3c0c39015a4089973601e1/SMS/TemplateSMS?sig=62B3B088CCF3D00EF5C430417F2C9E87', '2017-02-24 13:19:59');
INSERT INTO `kg_operator_log` VALUES ('479', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 13:39:54 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/8a216da85a3c0c39015a4089973601e1/SMS/TemplateSMS?sig=1F9C2655C23902003AE8B64AAF97D410', '2017-02-24 13:39:54');
INSERT INTO `kg_operator_log` VALUES ('480', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 13:39:54 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-24 13:39:54');
INSERT INTO `kg_operator_log` VALUES ('481', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 13:41:13 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/8a216da85a3c0c39015a4089973601e1/SMS/TemplateSMS?sig=8CD92E53E64C2D90BA0B30EAD4FB04C3', '2017-02-24 13:41:13');
INSERT INTO `kg_operator_log` VALUES ('482', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.204.88.104', '2017-02-24 14:28:58');
INSERT INTO `kg_operator_log` VALUES ('483', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.204.88.104', '2017-02-24 14:37:04');
INSERT INTO `kg_operator_log` VALUES ('484', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 14:37:50 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/8a216da85a3c0c39015a4089973601e1/SMS/TemplateSMS?sig=C8F270EE0576874F27EE5D6952EECF63', '2017-02-24 14:37:50');
INSERT INTO `kg_operator_log` VALUES ('485', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 14:37:51 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-24 14:37:51');
INSERT INTO `kg_operator_log` VALUES ('486', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 14:40:52 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/8a216da85a3c0c39015a4089973601e1/SMS/TemplateSMS?sig=FCBB8774C92D9536946A6B1AA8E80AFC', '2017-02-24 14:40:52');
INSERT INTO `kg_operator_log` VALUES ('487', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 14:40:52 CST 2017 sendTemplateSMS response body = <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n    <statusCode>160040</statusCode>\n    <statusMsg>验证码超出同模板同号码天发送上限</statusMsg>\n</Response>\n', '2017-02-24 14:40:52');
INSERT INTO `kg_operator_log` VALUES ('488', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 14:43:23 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/8a216da85a3c0c39015a4089973601e1/SMS/TemplateSMS?sig=6979A6454507601E3E9739212C4D57F1', '2017-02-24 14:43:23');
INSERT INTO `kg_operator_log` VALUES ('489', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.204.88.104', '2017-02-24 14:56:52');
INSERT INTO `kg_operator_log` VALUES ('490', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.204.88.104', '2017-02-24 14:58:58');
INSERT INTO `kg_operator_log` VALUES ('491', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 15:00:17 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/8a216da85a3c0c39015a4089973601e1/SMS/TemplateSMS?sig=E07EEB6693CA06FACB02740657300BF0', '2017-02-24 15:00:17');
INSERT INTO `kg_operator_log` VALUES ('492', 'com.cloopen.rest.sdk.utils.LoggerUtil', 'INFO', 'info', 'Fri Feb 24 15:09:28 CST 2017 sendTemplateSMS url = https://sandboxapp.cloopen.com:8883/2013-12-26/Accounts/8a216da85a3c0c39015a4089973601e1/SMS/TemplateSMS?sig=CE688F5255F24C2C9680BC592A9C73B3', '2017-02-24 15:09:28');
INSERT INTO `kg_operator_log` VALUES ('493', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.204.88.104', '2017-02-24 15:57:13');
INSERT INTO `kg_operator_log` VALUES ('494', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.204.88.104', '2017-02-24 16:02:08');
INSERT INTO `kg_operator_log` VALUES ('495', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.204.88.104', '2017-02-24 16:31:02');
INSERT INTO `kg_operator_log` VALUES ('496', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.204.88.104', '2017-02-24 16:33:45');
INSERT INTO `kg_operator_log` VALUES ('497', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.204.88.104', '2017-02-24 16:36:14');
INSERT INTO `kg_operator_log` VALUES ('498', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.204.88.104', '2017-02-24 16:41:58');
INSERT INTO `kg_operator_log` VALUES ('499', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.204.88.104', '2017-02-24 16:44:12');
INSERT INTO `kg_operator_log` VALUES ('500', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,115.204.88.104', '2017-02-24 16:58:21');
INSERT INTO `kg_operator_log` VALUES ('501', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,122.224.166.70', '2017-02-24 22:44:27');
INSERT INTO `kg_operator_log` VALUES ('502', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,122.224.166.70', '2017-02-24 22:45:17');
INSERT INTO `kg_operator_log` VALUES ('503', 'com.kg.controller.AdminController', 'INFO', 'login', '管理员登录:admin,122.224.166.70', '2017-02-25 00:05:43');

-- ----------------------------
-- Table structure for kg_pic_type
-- ----------------------------
DROP TABLE IF EXISTS `kg_pic_type`;
CREATE TABLE `kg_pic_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of kg_pic_type
-- ----------------------------
INSERT INTO `kg_pic_type` VALUES ('1', '蒙氏工作');
INSERT INTO `kg_pic_type` VALUES ('2', '户外感知');
INSERT INTO `kg_pic_type` VALUES ('3', '创想记录');

-- ----------------------------
-- Table structure for kg_picture
-- ----------------------------
DROP TABLE IF EXISTS `kg_picture`;
CREATE TABLE `kg_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片路径',
  `type_id` int(11) DEFAULT NULL COMMENT '图片类型',
  `baby_id` int(11) DEFAULT NULL COMMENT 'babyId',
  `create_time` datetime DEFAULT NULL COMMENT '上传时间',
  `content` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片说明',
  `min_path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '缩略图路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=487 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of kg_picture
-- ----------------------------
INSERT INTO `kg_picture` VALUES ('315', '/uploadstart/1488854489402716.jpg', '3', '32', '2017-03-07 10:41:29', '宝贝很乖今天，表现很好', '/uploadstart-min/1488854489402716.jpg');
INSERT INTO `kg_picture` VALUES ('334', '/uploadstart/1488888387672970.jpg', '1', '33', '2017-03-07 20:06:27', '', '/uploadstart-min/1488888387672970.jpg');
INSERT INTO `kg_picture` VALUES ('335', '/uploadstart/1488888389525368.jpg', '1', '33', '2017-03-07 20:06:29', '', '/uploadstart-min/1488888389525368.jpg');
INSERT INTO `kg_picture` VALUES ('336', '/uploadstart/1488888388688611.jpg', '1', '33', '2017-03-07 20:06:29', '', '/uploadstart-min/1488888388688611.jpg');
INSERT INTO `kg_picture` VALUES ('337', '/uploadstart/1488888391144554.jpg', '1', '33', '2017-03-07 20:06:31', '', '/uploadstart-min/1488888391144554.jpg');
INSERT INTO `kg_picture` VALUES ('338', '/uploadstart/1489386923179325.jpg', '3', '33', '2017-03-13 14:35:23', '', '/uploadstart-min/1489386923179325.jpg');
INSERT INTO `kg_picture` VALUES ('340', '/uploadstart/1489389953316506.jpg', '2', '32', '2017-03-13 15:25:53', '', '/uploadstart-min/1489389953316506.jpg');
INSERT INTO `kg_picture` VALUES ('341', '/uploadstart/1489390309549472.png', '1', '33', '2017-03-13 15:31:49', '', '/uploadstart-min/1489390309549472.png');
INSERT INTO `kg_picture` VALUES ('343', '/uploadstart/1489390336070675.jpg', '1', '33', '2017-03-13 15:32:16', '', '/uploadstart-min/1489390336070675.jpg');
INSERT INTO `kg_picture` VALUES ('344', '/uploadstart/1489390343279433.jpg', '1', '32', '2017-03-13 15:32:23', '', '/uploadstart-min/1489390343279433.jpg');
INSERT INTO `kg_picture` VALUES ('346', '/uploadstart/1489458993724932.jpg', '1', '43', '2017-03-14 10:36:34', '', '/uploadstart-min/1489458993724932.jpg');
INSERT INTO `kg_picture` VALUES ('347', '/uploadstart/1489458995939392.jpg', '1', '43', '2017-03-14 10:36:36', '', '/uploadstart-min/1489458995939392.jpg');
INSERT INTO `kg_picture` VALUES ('348', '/uploadstart/1489459000566709.jpg', '1', '43', '2017-03-14 10:36:40', '', '/uploadstart-min/1489459000566709.jpg');
INSERT INTO `kg_picture` VALUES ('349', '/uploadstart/1489459002154413.jpg', '1', '43', '2017-03-14 10:36:42', '', '/uploadstart-min/1489459002154413.jpg');
INSERT INTO `kg_picture` VALUES ('350', '/uploadstart/1489459002194923.jpg', '1', '43', '2017-03-14 10:36:42', '', '/uploadstart-min/1489459002194923.jpg');
INSERT INTO `kg_picture` VALUES ('351', '/uploadstart/1489459007916825.jpg', '1', '43', '2017-03-14 10:36:48', '', '/uploadstart-min/1489459007916825.jpg');
INSERT INTO `kg_picture` VALUES ('352', '/uploadstart/1489459379324387.jpg', '1', '43', '2017-03-14 10:42:59', '米饭好香呀\n', '/uploadstart-min/1489459379324387.jpg');
INSERT INTO `kg_picture` VALUES ('409', '/uploadstart/1489976672801847.jpg', '1', '32', '2017-03-20 10:24:33', '厉害了', '/uploadstart-min/1489976672801847.jpg');
INSERT INTO `kg_picture` VALUES ('410', '/uploadstart/1489976674210899.jpg', '1', '32', '2017-03-20 10:24:34', '厉害了', '/uploadstart-min/1489976674210899.jpg');
INSERT INTO `kg_picture` VALUES ('411', '/uploadstart/1489976676250283.jpg', '1', '32', '2017-03-20 10:24:36', '厉害了', '/uploadstart-min/1489976676250283.jpg');
INSERT INTO `kg_picture` VALUES ('412', '/uploadstart/1489976676116356.jpg', '1', '32', '2017-03-20 10:24:36', '厉害了', '/uploadstart-min/1489976676116356.jpg');
INSERT INTO `kg_picture` VALUES ('413', '/uploadstart/1489976677208647.jpg', '1', '32', '2017-03-20 10:24:37', '厉害了', '/uploadstart-min/1489976677208647.jpg');
INSERT INTO `kg_picture` VALUES ('414', '/uploadstart/1489976677809705.jpg', '1', '32', '2017-03-20 10:24:37', '厉害了', '/uploadstart-min/1489976677809705.jpg');
INSERT INTO `kg_picture` VALUES ('426', '/uploadstart/1489984444154770.jpg', '1', '32', '2017-03-20 12:34:04', '很厉害哈', '/uploadstart-min/1489984444154770.jpg');
INSERT INTO `kg_picture` VALUES ('427', '/uploadstart/1489984444220394.jpg', '1', '32', '2017-03-20 12:34:04', '很厉害哈', '/uploadstart-min/1489984444220394.jpg');
INSERT INTO `kg_picture` VALUES ('450', '/uploadstart/1490341972531561.jpg', '2', '51', '2017-03-24 15:52:52', '', '/uploadstart-min/1490341972531561.jpg');
INSERT INTO `kg_picture` VALUES ('451', '/uploadstart/1490341975600162.jpg', '2', '51', '2017-03-24 15:52:55', '', '/uploadstart-min/1490341975600162.jpg');
INSERT INTO `kg_picture` VALUES ('452', '/uploadstart/1490342091957386.jpg', '1', '51', '2017-03-24 15:54:52', '', '/uploadstart-min/1490342091957386.jpg');
INSERT INTO `kg_picture` VALUES ('453', '/uploadstart/1490342094340519.jpg', '1', '51', '2017-03-24 15:54:54', '', '/uploadstart-min/1490342094340519.jpg');
INSERT INTO `kg_picture` VALUES ('454', '/uploadstart/1490342094431907.jpg', '1', '51', '2017-03-24 15:54:54', '', '/uploadstart-min/1490342094431907.jpg');
INSERT INTO `kg_picture` VALUES ('455', '/uploadstart/1490342095558602.jpg', '1', '51', '2017-03-24 15:54:55', '', '/uploadstart-min/1490342095558602.jpg');
INSERT INTO `kg_picture` VALUES ('456', '/uploadstart/1490342097914279.jpg', '1', '51', '2017-03-24 15:54:58', '', '/uploadstart-min/1490342097914279.jpg');
INSERT INTO `kg_picture` VALUES ('457', '/uploadstart/1490342097954407.jpg', '1', '51', '2017-03-24 15:54:58', '', '/uploadstart-min/1490342097954407.jpg');
INSERT INTO `kg_picture` VALUES ('458', '/uploadstart/1490342100410653.jpg', '1', '51', '2017-03-24 15:55:00', '', '/uploadstart-min/1490342100410653.jpg');
INSERT INTO `kg_picture` VALUES ('483', '/uploadstart/1504242881645753.jpg', '2', '55', '2017-09-01 13:14:41', '不错的宝贝', '/uploadstart-min/1504242881645753.jpg');
INSERT INTO `kg_picture` VALUES ('484', '/uploadstart/1504578832592736.jpg', '1', '59', '2017-09-05 10:33:52', '', '/uploadstart-min/1504578832592736.jpg');
INSERT INTO `kg_picture` VALUES ('485', '/uploadstart/1506670877790888.jpg', '1', '62', '2017-09-29 15:41:18', '', '/uploadstart-min/1506670877790888.jpg');
INSERT INTO `kg_picture` VALUES ('486', '/uploadstart/1506670897387874.jpg', '2', '62', '2017-09-29 15:41:37', '', '/uploadstart-min/1506670897387874.jpg');

-- ----------------------------
-- Table structure for kg_role
-- ----------------------------
DROP TABLE IF EXISTS `kg_role`;
CREATE TABLE `kg_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of kg_role
-- ----------------------------
INSERT INTO `kg_role` VALUES ('1', '超级管理员');
INSERT INTO `kg_role` VALUES ('2', '园区管理员');
INSERT INTO `kg_role` VALUES ('3', '11111');
INSERT INTO `kg_role` VALUES ('4', 'a');

-- ----------------------------
-- Table structure for kg_role_module
-- ----------------------------
DROP TABLE IF EXISTS `kg_role_module`;
CREATE TABLE `kg_role_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `module_id` int(11) DEFAULT NULL COMMENT '模板id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1429 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of kg_role_module
-- ----------------------------
INSERT INTO `kg_role_module` VALUES ('13', '11', '13');
INSERT INTO `kg_role_module` VALUES ('1239', '1', '1');
INSERT INTO `kg_role_module` VALUES ('1240', '1', '6');
INSERT INTO `kg_role_module` VALUES ('1241', '1', '2');
INSERT INTO `kg_role_module` VALUES ('1242', '1', '7');
INSERT INTO `kg_role_module` VALUES ('1243', '1', '11');
INSERT INTO `kg_role_module` VALUES ('1244', '1', '12');
INSERT INTO `kg_role_module` VALUES ('1245', '1', '13');
INSERT INTO `kg_role_module` VALUES ('1246', '1', '14');
INSERT INTO `kg_role_module` VALUES ('1247', '1', '15');
INSERT INTO `kg_role_module` VALUES ('1248', '1', '16');
INSERT INTO `kg_role_module` VALUES ('1249', '1', '17');
INSERT INTO `kg_role_module` VALUES ('1250', '1', '18');
INSERT INTO `kg_role_module` VALUES ('1251', '1', '19');
INSERT INTO `kg_role_module` VALUES ('1252', '1', '20');
INSERT INTO `kg_role_module` VALUES ('1253', '1', '21');
INSERT INTO `kg_role_module` VALUES ('1254', '1', '22');
INSERT INTO `kg_role_module` VALUES ('1255', '1', '23');
INSERT INTO `kg_role_module` VALUES ('1256', '1', '3');
INSERT INTO `kg_role_module` VALUES ('1257', '1', '8');
INSERT INTO `kg_role_module` VALUES ('1258', '1', '24');
INSERT INTO `kg_role_module` VALUES ('1259', '1', '25');
INSERT INTO `kg_role_module` VALUES ('1260', '1', '26');
INSERT INTO `kg_role_module` VALUES ('1261', '1', '27');
INSERT INTO `kg_role_module` VALUES ('1262', '1', '28');
INSERT INTO `kg_role_module` VALUES ('1263', '1', '29');
INSERT INTO `kg_role_module` VALUES ('1264', '1', '30');
INSERT INTO `kg_role_module` VALUES ('1265', '1', '31');
INSERT INTO `kg_role_module` VALUES ('1266', '1', '32');
INSERT INTO `kg_role_module` VALUES ('1267', '1', '33');
INSERT INTO `kg_role_module` VALUES ('1268', '1', '34');
INSERT INTO `kg_role_module` VALUES ('1269', '1', '54');
INSERT INTO `kg_role_module` VALUES ('1270', '1', '55');
INSERT INTO `kg_role_module` VALUES ('1271', '1', '49');
INSERT INTO `kg_role_module` VALUES ('1272', '1', '50');
INSERT INTO `kg_role_module` VALUES ('1273', '1', '51');
INSERT INTO `kg_role_module` VALUES ('1274', '1', '52');
INSERT INTO `kg_role_module` VALUES ('1275', '1', '53');
INSERT INTO `kg_role_module` VALUES ('1276', '1', '4');
INSERT INTO `kg_role_module` VALUES ('1277', '1', '9');
INSERT INTO `kg_role_module` VALUES ('1278', '1', '35');
INSERT INTO `kg_role_module` VALUES ('1279', '1', '36');
INSERT INTO `kg_role_module` VALUES ('1280', '1', '37');
INSERT INTO `kg_role_module` VALUES ('1281', '1', '38');
INSERT INTO `kg_role_module` VALUES ('1282', '1', '5');
INSERT INTO `kg_role_module` VALUES ('1283', '1', '10');
INSERT INTO `kg_role_module` VALUES ('1284', '1', '39');
INSERT INTO `kg_role_module` VALUES ('1285', '1', '40');
INSERT INTO `kg_role_module` VALUES ('1286', '1', '41');
INSERT INTO `kg_role_module` VALUES ('1287', '1', '42');
INSERT INTO `kg_role_module` VALUES ('1288', '1', '43');
INSERT INTO `kg_role_module` VALUES ('1289', '1', '44');
INSERT INTO `kg_role_module` VALUES ('1290', '1', '45');
INSERT INTO `kg_role_module` VALUES ('1291', '1', '46');
INSERT INTO `kg_role_module` VALUES ('1292', '1', '47');
INSERT INTO `kg_role_module` VALUES ('1293', '1', '48');
INSERT INTO `kg_role_module` VALUES ('1349', '4', '1');
INSERT INTO `kg_role_module` VALUES ('1350', '4', '6');
INSERT INTO `kg_role_module` VALUES ('1351', '4', '2');
INSERT INTO `kg_role_module` VALUES ('1352', '4', '7');
INSERT INTO `kg_role_module` VALUES ('1353', '4', '11');
INSERT INTO `kg_role_module` VALUES ('1354', '4', '12');
INSERT INTO `kg_role_module` VALUES ('1355', '4', '13');
INSERT INTO `kg_role_module` VALUES ('1356', '4', '14');
INSERT INTO `kg_role_module` VALUES ('1357', '4', '15');
INSERT INTO `kg_role_module` VALUES ('1358', '4', '16');
INSERT INTO `kg_role_module` VALUES ('1359', '4', '17');
INSERT INTO `kg_role_module` VALUES ('1360', '4', '18');
INSERT INTO `kg_role_module` VALUES ('1361', '4', '19');
INSERT INTO `kg_role_module` VALUES ('1362', '4', '20');
INSERT INTO `kg_role_module` VALUES ('1363', '4', '21');
INSERT INTO `kg_role_module` VALUES ('1364', '4', '22');
INSERT INTO `kg_role_module` VALUES ('1365', '4', '23');
INSERT INTO `kg_role_module` VALUES ('1366', '4', '3');
INSERT INTO `kg_role_module` VALUES ('1367', '4', '8');
INSERT INTO `kg_role_module` VALUES ('1368', '4', '24');
INSERT INTO `kg_role_module` VALUES ('1369', '4', '25');
INSERT INTO `kg_role_module` VALUES ('1370', '4', '26');
INSERT INTO `kg_role_module` VALUES ('1371', '4', '27');
INSERT INTO `kg_role_module` VALUES ('1372', '4', '28');
INSERT INTO `kg_role_module` VALUES ('1373', '4', '29');
INSERT INTO `kg_role_module` VALUES ('1374', '4', '30');
INSERT INTO `kg_role_module` VALUES ('1375', '4', '31');
INSERT INTO `kg_role_module` VALUES ('1376', '4', '32');
INSERT INTO `kg_role_module` VALUES ('1377', '4', '33');
INSERT INTO `kg_role_module` VALUES ('1378', '4', '34');
INSERT INTO `kg_role_module` VALUES ('1379', '4', '54');
INSERT INTO `kg_role_module` VALUES ('1380', '4', '55');
INSERT INTO `kg_role_module` VALUES ('1381', '4', '49');
INSERT INTO `kg_role_module` VALUES ('1382', '4', '50');
INSERT INTO `kg_role_module` VALUES ('1383', '4', '51');
INSERT INTO `kg_role_module` VALUES ('1384', '4', '52');
INSERT INTO `kg_role_module` VALUES ('1385', '4', '53');
INSERT INTO `kg_role_module` VALUES ('1386', '2', '1');
INSERT INTO `kg_role_module` VALUES ('1387', '2', '6');
INSERT INTO `kg_role_module` VALUES ('1388', '2', '2');
INSERT INTO `kg_role_module` VALUES ('1389', '2', '7');
INSERT INTO `kg_role_module` VALUES ('1390', '2', '11');
INSERT INTO `kg_role_module` VALUES ('1391', '2', '12');
INSERT INTO `kg_role_module` VALUES ('1392', '2', '13');
INSERT INTO `kg_role_module` VALUES ('1393', '2', '14');
INSERT INTO `kg_role_module` VALUES ('1394', '2', '15');
INSERT INTO `kg_role_module` VALUES ('1395', '2', '16');
INSERT INTO `kg_role_module` VALUES ('1396', '2', '17');
INSERT INTO `kg_role_module` VALUES ('1397', '2', '18');
INSERT INTO `kg_role_module` VALUES ('1398', '2', '19');
INSERT INTO `kg_role_module` VALUES ('1399', '2', '20');
INSERT INTO `kg_role_module` VALUES ('1400', '2', '21');
INSERT INTO `kg_role_module` VALUES ('1401', '2', '22');
INSERT INTO `kg_role_module` VALUES ('1402', '2', '23');
INSERT INTO `kg_role_module` VALUES ('1403', '2', '3');
INSERT INTO `kg_role_module` VALUES ('1404', '2', '8');
INSERT INTO `kg_role_module` VALUES ('1405', '2', '24');
INSERT INTO `kg_role_module` VALUES ('1406', '2', '25');
INSERT INTO `kg_role_module` VALUES ('1407', '2', '26');
INSERT INTO `kg_role_module` VALUES ('1408', '2', '27');
INSERT INTO `kg_role_module` VALUES ('1409', '2', '28');
INSERT INTO `kg_role_module` VALUES ('1410', '2', '29');
INSERT INTO `kg_role_module` VALUES ('1411', '2', '30');
INSERT INTO `kg_role_module` VALUES ('1412', '2', '31');
INSERT INTO `kg_role_module` VALUES ('1413', '2', '32');
INSERT INTO `kg_role_module` VALUES ('1414', '2', '33');
INSERT INTO `kg_role_module` VALUES ('1415', '2', '34');
INSERT INTO `kg_role_module` VALUES ('1416', '2', '54');
INSERT INTO `kg_role_module` VALUES ('1417', '2', '55');
INSERT INTO `kg_role_module` VALUES ('1418', '2', '49');
INSERT INTO `kg_role_module` VALUES ('1419', '2', '50');
INSERT INTO `kg_role_module` VALUES ('1420', '2', '51');
INSERT INTO `kg_role_module` VALUES ('1421', '2', '52');
INSERT INTO `kg_role_module` VALUES ('1422', '2', '53');
INSERT INTO `kg_role_module` VALUES ('1423', '2', '4');
INSERT INTO `kg_role_module` VALUES ('1424', '2', '9');
INSERT INTO `kg_role_module` VALUES ('1425', '2', '35');
INSERT INTO `kg_role_module` VALUES ('1426', '2', '36');
INSERT INTO `kg_role_module` VALUES ('1427', '2', '37');
INSERT INTO `kg_role_module` VALUES ('1428', '2', '38');

-- ----------------------------
-- Table structure for kg_style
-- ----------------------------
DROP TABLE IF EXISTS `kg_style`;
CREATE TABLE `kg_style` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '标题',
  `content` mediumtext CHARACTER SET utf8 COMMENT '内容',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `garden_id` int(11) DEFAULT NULL COMMENT '园区id',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of kg_style
-- ----------------------------
INSERT INTO `kg_style` VALUES ('4', '【奔跑吧宝贝】玉园亲子大会--暨2017台历宝宝', '万众瞩目的“玉园台历宝宝”评选活动已经落下帷幕活动期间让我认识了玉园可爱的宝宝们据截止统计之日阅读量统计结果，恭喜以上十二位宝宝荣获2017年“玉园台历宝宝”。以上获奖宝贝家庭，请于12月25日前至青咖儿童摄影完成照片拍摄。逾期视自动放弃拍摄。12月13日儿童之家会电话联系获奖家庭，初步确认拍摄时间，请各位家长保持电话畅通。', '2017-02-15', '1', '1');
INSERT INTO `kg_style` VALUES ('7', '【奔跑吧宝贝】玉园亲子大会--暨2017台历宝宝', '万众瞩目的“玉园台历宝宝”评选活动已经落下帷幕活动期间让我认识了玉园可爱的宝宝们据截止统计之日阅读量统计结果，恭喜以上十二位宝宝荣获2017年“玉园台历宝宝”。以上获奖宝贝家庭，请于12月25日前至青咖儿童摄影完成照片拍摄。逾期视自动放弃拍摄。12月13日儿童之家会电话联系获奖家庭，初步确认拍摄时间，请各位家长保持电话畅通。', '2017-02-16', '1', '1');
INSERT INTO `kg_style` VALUES ('33', '防空火箭', '2222', '2017-03-01', '1', '39');
INSERT INTO `kg_style` VALUES ('34', '运费险电话v都是', '阿谀奉承雨下的大', '2017-03-01', '1', '39');
INSERT INTO `kg_style` VALUES ('35', '吞吞吐吐', '了', '2017-03-01', '1', '45');
INSERT INTO `kg_style` VALUES ('36', 'TT', 'TT', '2017-03-01', '1', '45');
INSERT INTO `kg_style` VALUES ('37', '停停停', '天天', '2017-03-01', '1', '45');
INSERT INTO `kg_style` VALUES ('38', 'ss', 'cc', '2017-03-01', '1', '44');
INSERT INTO `kg_style` VALUES ('40', '番外u而非哈哈热火分瑞安发挥让', '发诶氛围让范围范围', '2017-03-03', '2', '46');
INSERT INTO `kg_style` VALUES ('60', '啦啦啦啦', '春天来了，吃饭比那啥重要！', '2017-03-14', '1', '47');
INSERT INTO `kg_style` VALUES ('62', '今天五一节', '劳动真光荣', '2017-03-17', '1', '39');
INSERT INTO `kg_style` VALUES ('67', '2010', '哈哈哈啊哈', '2017-03-20', '1', '39');
INSERT INTO `kg_style` VALUES ('68', '2017', '哈哈回家', '2017-03-20', '1', '49');
INSERT INTO `kg_style` VALUES ('69', '哈哈哈', '嘿嘿嘿嘿', '2017-04-18', '1', '49');
INSERT INTO `kg_style` VALUES ('70', '加图', '哈哈', '2017-04-18', '1', '49');
INSERT INTO `kg_style` VALUES ('71', '毕业', '毕业', '2017-08-23', '1', '51');

-- ----------------------------
-- Table structure for kg_style_pic
-- ----------------------------
DROP TABLE IF EXISTS `kg_style_pic`;
CREATE TABLE `kg_style_pic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imgUrl` varchar(255) DEFAULT NULL,
  `style_id` int(11) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `min_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=71 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of kg_style_pic
-- ----------------------------
INSERT INTO `kg_style_pic` VALUES ('63', '/uploadstart/1489730467118468.jpg', '64', '2017-03-17 14:01', '/uploadstart-min/1489730467118468.jpg');
INSERT INTO `kg_style_pic` VALUES ('64', '/uploadstart/1489730598808793.jpg', '65', '2017-03-17 14:03', '/uploadstart-min/1489730598808793.jpg');
INSERT INTO `kg_style_pic` VALUES ('65', '/uploadstart/1489730655113201.jpg', '66', '2017-03-17 14:04', '/uploadstart-min/1489730655113201.jpg');
INSERT INTO `kg_style_pic` VALUES ('66', '/uploadstart/1489984652053363.jpg', '67', '2017-03-20 12:37', '/uploadstart-min/1489984652053363.jpg');
INSERT INTO `kg_style_pic` VALUES ('67', '/uploadstart/1489997809389208.jpg', '68', '2017-03-20 16:16', '/uploadstart-min/1489997809389208.jpg');
INSERT INTO `kg_style_pic` VALUES ('58', '/uploadstart/1489387081364821.jpg', '58', '2017-03-13 14:38', '/uploadstart-min/1489387081364821.jpg');
INSERT INTO `kg_style_pic` VALUES ('59', '/uploadstart/1489391290591902.jpg', '59', '2017-03-13 15:48', '/uploadstart-min/1489391290591902.jpg');
INSERT INTO `kg_style_pic` VALUES ('60', '/uploadstart/1489459646918777.jpg', '60', '2017-03-14 10:47', '/uploadstart-min/1489459646918777.jpg');
INSERT INTO `kg_style_pic` VALUES ('61', '/uploadstart/1489459648211720.jpg', '60', '2017-03-14 10:47', '/uploadstart-min/1489459648211720.jpg');
INSERT INTO `kg_style_pic` VALUES ('68', '/uploadstart/1492505124993316.jpg', '70', '2017-04-18 16:45', '/uploadstart-min/1492505124993316.jpg');
INSERT INTO `kg_style_pic` VALUES ('69', '/uploadstart/1503466897093884.jpg', '71', '2017-08-23 13:41', '/uploadstart-min/1503466897093884.jpg');
INSERT INTO `kg_style_pic` VALUES ('70', '/uploadstart/1504238324613856.jpg', '72', '2017-09-01 11:58', '/uploadstart-min/1504238324613856.jpg');

-- ----------------------------
-- Table structure for kg_teacher
-- ----------------------------
DROP TABLE IF EXISTS `kg_teacher`;
CREATE TABLE `kg_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登陆密码',
  `garden_id` int(11) DEFAULT NULL COMMENT '园区id',
  `join_time` datetime DEFAULT NULL COMMENT '注册时间',
  `header_pic_path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像路径',
  `tel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `introduce` mediumtext COLLATE utf8_unicode_ci COMMENT '个人简介',
  `state` int(11) DEFAULT NULL COMMENT '审核状态',
  `sex` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of kg_teacher
-- ----------------------------
INSERT INTO `kg_teacher` VALUES ('41', '桑小菜', 'D964173DC44DA83EEAFA3AEBBEE9A1A0', '1', '2017-02-27 16:36:50', '/uploadstart/1489387025606.jpg', '13685788505', '我是老师', '1', '男');
INSERT INTO `kg_teacher` VALUES ('44', 'cdng', '0A571F99E5667CB088DADCC9A2D1E161', '1', '2017-02-28 14:31:14', '/uploadstart/1488359325922531.jpg', '18267077503', 'ggghhhh', '1', '男');
INSERT INTO `kg_teacher` VALUES ('45', 'mlb', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '2017-03-01 14:25:27', null, '18767161991', null, '1', '男');
INSERT INTO `kg_teacher` VALUES ('47', '佳佳', '5690DDDFA28AE085D23518A035707282', '1', '2017-03-13 14:56:00', null, '18006861299', null, '1', '女');
INSERT INTO `kg_teacher` VALUES ('50', '柒柒', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '2017-04-20 11:27:41', null, '13967116683', null, '1', '男');
INSERT INTO `kg_teacher` VALUES ('51', '王淳', '5690DDDFA28AE085D23518A035707282', '1', '2017-08-23 11:56:27', null, '15906814879', null, '1', '男');
INSERT INTO `kg_teacher` VALUES ('56', '姚樱子', '87B7A50DF84AA2312F7984479B690234', '1', '2017-08-23 13:33:22', null, '15990615537', null, '1', '男');
INSERT INTO `kg_teacher` VALUES ('58', '师薇', '496E2083428B4D352E13475E54DFCE1E', '2', '2017-08-23 13:35:37', null, '18967149612', null, '1', '女');
INSERT INTO `kg_teacher` VALUES ('59', '翟燕飞', 'A933391E404F1532C8CA8B721EEE6253', '2', '2017-08-23 13:37:19', null, '15229716344', null, '1', '女');
INSERT INTO `kg_teacher` VALUES ('64', '田园牧歌IC一班', '3919EBC138E9FB084A9B057C0BCBFCF4', '3', '2017-09-02 20:55:44', null, '17757144015', null, '1', '女');
INSERT INTO `kg_teacher` VALUES ('65', '小鑫', '2973F872E60527D1ACB0A5C4B7EF1EE8', '14', '2017-09-02 21:02:30', null, '18106562679', null, '1', '男');
INSERT INTO `kg_teacher` VALUES ('66', '章妮娜', '1A9F8BEE20ACCFEEBAB0804A1C4A5E53', '1', '2017-09-04 09:40:46', null, '17767093686', null, '1', '女');
INSERT INTO `kg_teacher` VALUES ('67', '刘秋儒', '7D02217FFCA0E96659548691F24B6A4C', '8', '2017-09-05 10:13:56', null, '15757152760', null, '1', '男');
INSERT INTO `kg_teacher` VALUES ('70', '魏梦玉', 'BE1070802879D14DCEF1565242EC0C95', '12', '2017-09-29 17:00:39', null, '18706805028', null, '1', '男');
INSERT INTO `kg_teacher` VALUES ('71', '张添鑫', '4D30218965795EE303710D704930F4F9', '14', '2017-09-29 17:01:48', null, '15802909403', null, '0', '女');
INSERT INTO `kg_teacher` VALUES ('72', '章妮娜', '8648163798FB9F4FF8ACC3CF82982AF5', '1', '2017-09-29 17:02:17', null, '15869161747', null, '1', '男');
INSERT INTO `kg_teacher` VALUES ('74', '林玲玲', 'E10ADC3949BA59ABBE56E057F20F883E', '8', '2017-09-29 17:02:18', null, '17746807963', null, '1', '男');
INSERT INTO `kg_teacher` VALUES ('76', '小鑫', '2973F872E60527D1ACB0A5C4B7EF1EE8', '14', '2017-11-08 13:29:53', null, '13388616901', null, '1', '女');
INSERT INTO `kg_teacher` VALUES ('77', '邹超华', 'E10ADC3949BA59ABBE56E057F20F883E', '14', '2017-11-08 13:30:24', null, '18268158959', null, '1', '男');
INSERT INTO `kg_teacher` VALUES ('78', '王茹玥', '554A17D7248A1995A11DF833FE0E08DA', '7', '2017-11-08 13:30:46', null, '18291038588', null, '1', '女');
INSERT INTO `kg_teacher` VALUES ('79', '悦活轩IC1班', '5C09BD19FB6A8A6C91AE96D266D8E20B', '11', '2017-11-08 13:31:45', null, '17706413315', null, '1', '女');
INSERT INTO `kg_teacher` VALUES ('80', '梁静涵', '6F2E6881D67FB4AEE05868638E099E1B', '3', '2017-11-08 13:32:06', null, '18354288826', null, '1', '女');
INSERT INTO `kg_teacher` VALUES ('81', '姚新迎', 'EA07424201D3BD769B53A2FE9CFDFDE9', '3', '2017-11-08 13:32:35', null, '15397130272', null, '1', '女');
INSERT INTO `kg_teacher` VALUES ('82', '李培培', '200B47E5644E0DB2750F1DB99E382700', '10', '2017-11-08 13:33:22', '/uploadstart/1510119967325.jpg', '13388619582', '我们对儿童所做的一切，都会开花结果，不仅影响他的一生，也决定他的一生。', '1', '男');
INSERT INTO `kg_teacher` VALUES ('83', '嘉信国际主教老师', 'B3EE5FB9A962874B31A20C0F6B7A4921', '19', '2017-11-08 15:19:00', '/uploadstart/1510126986325.jpg', '13388617035', '源自对孩子的爱', '0', '女');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `m` char(4) DEFAULT NULL,
  `n` varchar(4) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('yyyy', 'yyyy');
INSERT INTO `test` VALUES ('y', 'yy ');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('yyyy', 'yyyy');
INSERT INTO `test` VALUES ('y', 'yy ');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('yyyy', 'yyyy');
INSERT INTO `test` VALUES ('y', 'yy ');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('yyyy', 'yyyy');
INSERT INTO `test` VALUES ('y', 'yy ');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('yyyy', 'yyyy');
INSERT INTO `test` VALUES ('y', 'yy ');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('yyyy', 'yyyy');
INSERT INTO `test` VALUES ('y', 'yy ');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('yyyy', 'yyyy');
INSERT INTO `test` VALUES ('y', 'yy ');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('????', '????');
INSERT INTO `test` VALUES ('yyyy', 'yyyy');
INSERT INTO `test` VALUES ('y', 'yy ');
INSERT INTO `test` VALUES ('Ñî¿¡', 'Ñî¿¡');
