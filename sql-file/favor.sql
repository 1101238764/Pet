/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : utils

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-01-11 18:01:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for favor
-- ----------------------------
DROP TABLE IF EXISTS `favor`;
CREATE TABLE `favor` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '逻辑主键',
  `account_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'os账号',
  `type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容类型',
  `content` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收藏内容',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1476014336787292163 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='收藏表';

-- ----------------------------
-- Records of favor
-- ----------------------------
INSERT INTO `favor` VALUES ('1476010430262923265', '123', '工具', 'http://www.nalaos.com/oa/wiki/page/detail?id=898', '2021-12-29 10:38:45', '2021-12-29 10:38:45', 'NALA wiki');
INSERT INTO `favor` VALUES ('1476014336787292162', '123', '1', 'http://www.nalaos.com/oa/files', '2021-12-29 10:22:10', '2021-12-29 10:22:10', 'NALA file center');
