/*
 Date: 01/11/2020 11:05:34
 @author tanghaiyang
*/

CREATE DATABASE if not exists `alpha-driver-mybatis` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE `alpha-driver-mybatis`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_city
-- ----------------------------
DROP TABLE IF EXISTS `t_city`;
CREATE TABLE `t_city`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '城市名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_city
-- ----------------------------
INSERT INTO `t_city` VALUES (1035761969176342529, '北京');
INSERT INTO `t_city` VALUES (1035762001753501698, '成都');
INSERT INTO `t_city` VALUES (1035765839768121346, '上海');
INSERT INTO `t_city` VALUES (1035765875767832578, '深圳');
INSERT INTO `t_city` VALUES (1035788325201117185, '1');

-- ----------------------------
-- Table structure for t_idcard
-- ----------------------------
DROP TABLE IF EXISTS `t_idcard`;
CREATE TABLE `t_idcard`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `code` varchar(20) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '身份证号码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_idcard
-- ----------------------------
INSERT INTO `t_idcard` VALUES (1035788325276614657, '1');
INSERT INTO `t_idcard` VALUES (1035789714388168706, '123456789012345678');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `id` bigint(20) NOT NULL COMMENT '唯一标识，主键',
  `name` varchar(50) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '姓名',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `info` text CHARACTER SET utf8mb4 NULL COMMENT '介绍',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否逻辑删除（true：删除；false：正常（默认））',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `gender` int(1) NULL DEFAULT 0 COMMENT '性别（0：保密（默认）；1：男；2：女）',
  `idcard_id` bigint(20) NULL DEFAULT NULL COMMENT '身份证号码表编号（id）',
  `city_id` bigint(20) NULL DEFAULT NULL COMMENT '城市表编号（id）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES (1035788325322752001, '张三', 20, '千里之行，始于足下', 0, '2018-09-01 15:15:55', '2019-08-27 11:08:55', 1, 1035788325276614657, 1035788325201117185);
INSERT INTO `t_student` VALUES (1035789714459471874, '李四', 18, '不积跬步，无以至千里', 0, '2018-09-01 15:21:26', '2019-08-27 11:08:55', 1, 1035789714388168706, 1035762001753501698);

SET FOREIGN_KEY_CHECKS = 1;


DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `desc` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'name1', '11', 'ttt', '2020-03-27 16:32:17');
INSERT INTO `t_user` VALUES ('2', 'name2', '33', '333', '2020-03-27 16:32:59');
INSERT INTO `t_user` VALUES ('3', 'name3', '224', '222', '2020-03-27 16:32:57');
INSERT INTO `t_user` VALUES ('4', 'test1', '22', '4vfggg', '2020-03-27 16:33:10');
INSERT INTO `t_user` VALUES ('5', 'test2', '66', '34ghhh', '2020-03-27 16:33:20');
INSERT INTO `t_user` VALUES ('6', 'test3', '44', 'dfgh', '2020-03-27 16:33:28');
INSERT INTO `t_user` VALUES ('7', 'baidu1', '444', '2355', '2020-03-27 16:33:41');
INSERT INTO `t_user` VALUES ('8', 'baidu2', '223', '5555ddd', '2020-03-27 16:33:50');
INSERT INTO `t_user` VALUES ('9', 'jiuba1', '343', '222', '2020-03-27 16:34:00');
INSERT INTO `t_user` VALUES ('10', 'apple1', '33', '245dhgggg', '2020-03-27 16:34:12');
INSERT INTO `t_user` VALUES ('11', 'apple2', '12', 'gjmvvfgg', '2020-03-27 16:34:27');
INSERT INTO `t_user` VALUES ('12', 'stream1', '34', '223', '2020-03-27 16:34:39');
INSERT INTO `t_user` VALUES ('13', 'spark1', '33', '2444cdfgf', '2020-03-27 16:34:46');
INSERT INTO `t_user` VALUES ('14', 'spring3', '44', 'ffgdfdfg2', '2020-03-27 16:35:19');
INSERT INTO `t_user` VALUES ('15', 'glacier1', '23', 'dfff', '2020-03-27 16:35:21');
INSERT INTO `t_user` VALUES ('16', 'glacier2', '335', 'ww', '2020-03-27 16:35:31');
INSERT INTO `t_user` VALUES ('17', 'glacier3', '3355', '111', '2020-03-27 16:35:38');
