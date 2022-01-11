/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : utils

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-01-11 18:01:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for lock_resource
-- ----------------------------
DROP TABLE IF EXISTS `lock_resource`;
CREATE TABLE `lock_resource` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '逻辑主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '锁名',
  `expire` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '失效时间戳 ms',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='资源控制';

-- ----------------------------
-- Records of lock_resource
-- ----------------------------
INSERT INTO `lock_resource` VALUES ('6', 'NOTE_NOTIFY_LOCK', '2022-01-12 03:52:36', '2022-01-11 03:52:36', '2022-01-11 03:52:36');
