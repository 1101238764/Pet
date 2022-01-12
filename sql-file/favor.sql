/*
Navicat MySQL Data Transfer

Source Server         : 远程NALA精灵
Source Server Version : 50735
Source Host           : 106.55.153.91:30001
Source Database       : simba_mls

Target Server Type    : MYSQL
Target Server Version : 50735
File Encoding         : 65001

Date: 2022-01-12 12:17:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for favor
-- ----------------------------
DROP TABLE IF EXISTS `favor`;
CREATE TABLE `favor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '逻辑主键',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'os账号',
  `type` varchar(128) NOT NULL COMMENT '内容类型',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收藏内容',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1476014336787292163 DEFAULT CHARSET=utf8mb4 COMMENT='收藏表';

-- ----------------------------
-- Records of favor
-- ----------------------------
INSERT INTO `favor` VALUES ('1476010430262923265', '123', '工具', 'http://www.nalaos.com/oa/wiki/page/detail?id=898', '2021-12-29 10:38:45', '2021-12-29 10:38:45', 'NALA wiki');
INSERT INTO `favor` VALUES ('1476014336787292162', '123', '1', 'http://www.nalaos.com/oa/files', '2021-12-29 10:22:10', '2021-12-29 10:22:10', 'NALA file center');
