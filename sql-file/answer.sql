/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : utils

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-01-11 18:00:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` bigint NOT NULL COMMENT '逻辑主键',
  `question_id` bigint NOT NULL COMMENT '问题id',
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '答案内容',
  `creat_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='答案表';

-- ----------------------------
-- Records of answer
-- ----------------------------
