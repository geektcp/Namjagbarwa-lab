/*
Navicat MySQL Data Transfer

Source Server         : esin
Source Server Version : 50623
Source Host           : 43.247.68.115:63306
Source Database       : alpha-spring-security

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2020-04-18 14:45:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` varchar(255) NOT NULL,
  `chunk` int(11) DEFAULT NULL,
  `chunk_num` int(11) DEFAULT NULL,
  `ismerge` int(11) DEFAULT NULL,
  `md5` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attachment
-- ----------------------------

-- ----------------------------
-- Table structure for attachment_detail
-- ----------------------------
DROP TABLE IF EXISTS `attachment_detail`;
CREATE TABLE `attachment_detail` (
  `id` varchar(255) NOT NULL,
  `md5` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attachment_detail
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '资源名称',
  `type` varchar(30) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '资源类型',
  `parent_id` int(11) NOT NULL COMMENT '父ID，根节点默认为0',
  `url` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '资源访问URL地址',
  `permission` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `desc` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '权限分组，eg:P,S',
  `is_enable` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态:1-是; 0-否',
  `created_by_id` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_id` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22063 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='资源表';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '下单', '0', '0', null, 'order:add', null, '1', '', '2020-04-18 14:38:36', '', '2020-04-18 14:44:38');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(255) DEFAULT NULL,
  `parent_role_code` varchar(255) DEFAULT NULL,
  `role_desc` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '1', 'admin', '1');
INSERT INTO `sys_role` VALUES ('2', 'manager', '1', 'common role', '1');
INSERT INTO `sys_role` VALUES ('3', 'employ', '2', null, '1');

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT 'sys_role.id',
  `resource_id` int(11) NOT NULL COMMENT 'sys_resource.id',
  `is_enable` int(255) DEFAULT '1' COMMENT '可用状态:1-是; 0-否',
  `created_by_id` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by_id` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2008050 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='角色与资源';

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('1', '1', '1', '1', '', '2020-04-18 14:42:52', '', '2020-04-18 14:42:52');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'thy', '123', '3333');
INSERT INTO `sys_user` VALUES ('2', 'test', '123', null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_name` varchar(255) NOT NULL,
  `role_code` varchar(255) NOT NULL,
  KEY `FKqk75w971cfrg7om3fst93lhx1` (`role_code`),
  KEY `FK2rway3xn5jmje2yeq5iup8ogd` (`user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('thy', 'admin');
INSERT INTO `sys_user_role` VALUES ('test', 'employ');
