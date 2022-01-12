/*
Navicat MySQL Data Transfer

Source Server         : 远程NALA精灵
Source Server Version : 50735
Source Host           : 106.55.153.91:30001
Source Database       : simba_mls

Target Server Type    : MYSQL
Target Server Version : 50735
File Encoding         : 65001

Date: 2022-01-12 15:12:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pet
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pet_name` varchar(64) DEFAULT NULL COMMENT '精灵名称',
  `phone` varchar(64) NOT NULL COMMENT 'os账号',
  `show` int(1) NOT NULL DEFAULT '0' COMMENT '精灵开关 0 关闭  1开启',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_id` (`phone`) USING BTREE,
  KEY `pet_name` (`pet_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='个人信息表';

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES ('1', '羊驼', '17614427501', '0');
INSERT INTO `pet` VALUES ('3', '12', '1', '0');
