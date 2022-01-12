/*
Navicat MySQL Data Transfer

Source Server         : 远程NALA精灵
Source Server Version : 50735
Source Host           : 106.55.153.91:30001
Source Database       : simba_mls

Target Server Type    : MYSQL
Target Server Version : 50735
File Encoding         : 65001

Date: 2022-01-12 12:16:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '逻辑主键',
  `phone` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
  `notify_time` datetime DEFAULT NULL COMMENT '提醒时间',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(11) DEFAULT NULL COMMENT '状态 0 草稿 1 待执行  2 已完成 -1 删除 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='备忘录';

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES ('3', '456', '测试一下', '2022-01-10 08:32:00', '2022-01-10 08:30:15', '2022-01-10 08:30:15', '1');
INSERT INTO `note` VALUES ('5', '456', '处理nala精灵', '2022-01-10 09:10:00', '2022-01-10 09:06:08', '2022-01-10 09:06:08', '1');
INSERT INTO `note` VALUES ('6', '456', '存个草稿', '2022-01-11 03:31:00', '2022-01-11 03:31:37', '2022-01-11 03:31:37', '0');
