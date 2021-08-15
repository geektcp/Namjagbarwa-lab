SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_group`;
CREATE TABLE `xxl_job_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行器名称',
  `order` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `address_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器地址列表，多地址逗号分隔',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------
INSERT INTO `xxl_job_group` VALUES (1, 'kunming-job-executor', '跨城项目定时任务执行器', 2, 1, '172.17.0.2:9999');
INSERT INTO `xxl_job_group` VALUES (2, 'logistics-job-executor', '中台项目定时任务执行器', 1, 1, '172.17.0.5:9998');

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_info`;
CREATE TABLE `xxl_job_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_cron` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务执行CRON',
  `job_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `author` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '报警邮件',
  `executor_route_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(11) NOT NULL DEFAULT 0 COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime(0) NULL DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint(13) NOT NULL DEFAULT 0 COMMENT '上次调度时间',
  `trigger_next_time` bigint(13) NOT NULL DEFAULT 0 COMMENT '下次调度时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------
INSERT INTO `xxl_job_info` VALUES (1, 1, '0 0 2,8,14,20 * * ?', '科邦运单详情定时抓取任务', '2020-02-14 22:21:31', '2020-03-11 18:57:46', '王炜', '', 'FIRST', 'kbLogisticsInfoSyncJobHandler', '', 'SERIAL_EXECUTION', 0, 1, 'BEAN', '', '初始化', '2020-02-14 22:21:31', '', 1, 0, 1583928000000);
INSERT INTO `xxl_job_info` VALUES (2, 2, '0 0 3,9,15,21 * * ?', '赢联物流平台信息同步', '2020-03-01 22:25:10', '2020-03-11 19:23:49', '王炜/伍鸿成', '', 'FIRST', 'doLogisticsBasicSyncYlJobHandler', '', 'SERIAL_EXECUTION', 0, 1, 'BEAN', '', 'GLUE代码初始化', '2020-03-01 22:25:10', '', 1, 1583910000000, 1583931600000);
INSERT INTO `xxl_job_info` VALUES (4, 2, '0 0 3,9,15,21 * * ?', '蓝桥物流平台信息同步', '2020-03-11 13:55:43', '2020-03-11 19:24:04', '伍鸿成', '', 'FIRST', 'doLogisticsBasicSyncLqJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2020-03-11 13:55:43', '', 1, 0, 1583931600000);
INSERT INTO `xxl_job_info` VALUES (5, 2, '0 0 0,3,9,15,21 * * ?', '科邦物流平台信息同步	', '2020-03-11 14:22:12', '2020-03-11 14:31:16', '王炜', '', 'FIRST', 'doLogisticsBasicSyncKbJobHandler', '', 'SERIAL_EXECUTION', 0, 1, 'BEAN', '', 'GLUE代码初始化', '2020-03-11 14:22:12', '', 1, 1583910000000, 1583931600000);
INSERT INTO `xxl_job_info` VALUES (6, 2, '0 10 3,9,15,21 * * ?', '中台公共物流信息同步', '2020-03-11 14:25:13', '2020-03-11 14:26:56', '伍鸿成/王炜', '', 'FIRST', 'doLogisticsBasicSyncBaseJobHandler', '', 'SERIAL_EXECUTION', 0, 1, 'BEAN', '', 'GLUE代码初始化', '2020-03-11 14:25:13', '', 1, 1583910600000, 1583932200000);
INSERT INTO `xxl_job_info` VALUES (7, 1, '0 0 4,10,16,22 * * ?', '蓝桥运单详情定时抓取任务', '2020-03-11 18:20:21', '2020-03-11 19:06:37', '伍鸿成', '', 'FIRST', 'lqLogisticsInfoSyncJobHandler', '', 'SERIAL_EXECUTION', 0, 1, 'BEAN', '', 'GLUE代码初始化', '2020-03-11 18:20:21', '', 1, 0, 1583935200000);

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_lock`;
CREATE TABLE `xxl_job_lock`  (
  `lock_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_lock
-- ----------------------------
INSERT INTO `xxl_job_lock` VALUES ('schedule_lock');

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log`;
CREATE TABLE `xxl_job_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `trigger_time` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '调度-日志',
  `handle_time` datetime(0) NULL DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(11) NOT NULL COMMENT '执行-状态',
  `handle_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '执行-日志',
  `alarm_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `I_trigger_time`(`trigger_time`) USING BTREE,
  INDEX `I_handle_code`(`handle_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_log
-- ----------------------------
INSERT INTO `xxl_job_log` VALUES (60, 2, 2, '172.17.0.5:9998', 'doLogisticsBasicSyncYlJobHandler', '', NULL, 1, '2020-03-11 14:33:31', 200, '任务触发类型：手动触发<br>调度机器：172.17.0.4<br>执行器-注册方式：手动录入<br>执行器-地址列表：[172.17.0.5:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：1<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：172.17.0.5:9998<br>code：200<br>msg：null', '2020-03-11 14:34:02', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (61, 2, 5, '172.17.0.5:9998', 'doLogisticsBasicSyncKbJobHandler', '', NULL, 1, '2020-03-11 14:33:35', 200, '任务触发类型：手动触发<br>调度机器：172.17.0.4<br>执行器-注册方式：手动录入<br>执行器-地址列表：[172.17.0.5:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：1<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：172.17.0.5:9998<br>code：200<br>msg：null', '2020-03-11 14:33:39', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (62, 2, 6, '172.17.0.5:9998', 'doLogisticsBasicSyncBaseJobHandler', '', NULL, 1, '2020-03-11 14:33:38', 200, '任务触发类型：手动触发<br>调度机器：172.17.0.4<br>执行器-注册方式：手动录入<br>执行器-地址列表：[172.17.0.5:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：1<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：172.17.0.5:9998<br>code：200<br>msg：null', '2020-03-11 14:33:39', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (63, 2, 2, '172.17.0.5:9998', 'doLogisticsBasicSyncYlJobHandler', '', NULL, 1, '2020-03-11 14:54:17', 200, '任务触发类型：手动触发<br>调度机器：172.17.0.4<br>执行器-注册方式：手动录入<br>执行器-地址列表：[172.17.0.5:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：1<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：172.17.0.5:9998<br>code：200<br>msg：null', '2020-03-11 14:54:47', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (64, 2, 5, '172.17.0.5:9998', 'doLogisticsBasicSyncKbJobHandler', '', NULL, 1, '2020-03-11 14:54:20', 200, '任务触发类型：手动触发<br>调度机器：172.17.0.4<br>执行器-注册方式：手动录入<br>执行器-地址列表：[172.17.0.5:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：1<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：172.17.0.5:9998<br>code：200<br>msg：null', '2020-03-11 14:54:27', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (65, 2, 6, '172.17.0.5:9998', 'doLogisticsBasicSyncBaseJobHandler', '', NULL, 1, '2020-03-11 14:54:23', 200, '任务触发类型：手动触发<br>调度机器：172.17.0.4<br>执行器-注册方式：手动录入<br>执行器-地址列表：[172.17.0.5:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：1<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：172.17.0.5:9998<br>code：200<br>msg：null', '2020-03-11 14:54:23', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (66, 2, 2, '172.17.0.5:9998', 'doLogisticsBasicSyncYlJobHandler', '', NULL, 1, '2020-03-11 15:00:00', 200, '任务触发类型：Cron触发<br>调度机器：172.17.0.4<br>执行器-注册方式：手动录入<br>执行器-地址列表：[172.17.0.5:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：1<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：172.17.0.5:9998<br>code：200<br>msg：null', '2020-03-11 15:00:30', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (67, 2, 5, '172.17.0.5:9998', 'doLogisticsBasicSyncKbJobHandler', '', NULL, 1, '2020-03-11 15:00:00', 200, '任务触发类型：Cron触发<br>调度机器：172.17.0.4<br>执行器-注册方式：手动录入<br>执行器-地址列表：[172.17.0.5:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：1<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：172.17.0.5:9998<br>code：200<br>msg：null', '2020-03-11 15:00:05', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (68, 2, 6, '172.17.0.5:9998', 'doLogisticsBasicSyncBaseJobHandler', '', NULL, 1, '2020-03-11 15:10:00', 200, '任务触发类型：Cron触发<br>调度机器：172.17.0.4<br>执行器-注册方式：手动录入<br>执行器-地址列表：[172.17.0.5:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：1<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：172.17.0.5:9998<br>code：200<br>msg：null', '2020-03-11 15:10:00', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (69, 1, 1, '172.17.0.2:9999', 'kbLogisticsInfoSyncJobHandler', '', NULL, 1, '2020-03-11 18:22:57', 200, '任务触发类型：手动触发<br>调度机器：172.17.0.4<br>执行器-注册方式：手动录入<br>执行器-地址列表：[172.17.0.2:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：1<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：172.17.0.2:9999<br>code：200<br>msg：null', '2020-03-11 18:23:01', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (70, 1, 1, '172.17.0.2:9999', 'kbLogisticsInfoSyncJobHandler', '', NULL, 1, '2020-03-11 18:57:10', 200, '任务触发类型：手动触发<br>调度机器：172.17.0.4<br>执行器-注册方式：手动录入<br>执行器-地址列表：[172.17.0.2:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：1<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：172.17.0.2:9999<br>code：200<br>msg：null', '2020-03-11 18:57:12', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (71, 1, 7, '172.17.0.2:9999', 'lqLogisticsInfoSyncJobHandler', '', NULL, 1, '2020-03-11 18:57:50', 200, '任务触发类型：手动触发<br>调度机器：172.17.0.4<br>执行器-注册方式：手动录入<br>执行器-地址列表：[172.17.0.2:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：1<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：172.17.0.2:9999<br>code：200<br>msg：null', '2020-03-11 18:57:50', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (72, 1, 7, '172.17.0.2:9999', 'lqLogisticsInfoSyncJobHandler', '', NULL, 1, '2020-03-11 19:03:43', 200, '任务触发类型：手动触发<br>调度机器：172.17.0.4<br>执行器-注册方式：手动录入<br>执行器-地址列表：[172.17.0.2:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：1<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：172.17.0.2:9999<br>code：200<br>msg：null', '2020-03-11 19:03:44', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (73, 1, 7, '172.17.0.2:9999', 'lqLogisticsInfoSyncJobHandler', '', NULL, 1, '2020-03-11 19:06:42', 200, '任务触发类型：手动触发<br>调度机器：172.17.0.4<br>执行器-注册方式：手动录入<br>执行器-地址列表：[172.17.0.2:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：1<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：172.17.0.2:9999<br>code：200<br>msg：null', '2020-03-11 19:06:42', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (74, 1, 7, '172.17.0.2:9999', 'lqLogisticsInfoSyncJobHandler', '', NULL, 1, '2020-03-11 19:08:25', 200, '任务触发类型：手动触发<br>调度机器：172.17.0.4<br>执行器-注册方式：手动录入<br>执行器-地址列表：[172.17.0.2:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：1<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：172.17.0.2:9999<br>code：200<br>msg：null', '2020-03-11 19:08:25', 200, '', 0);

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log_report`;
CREATE TABLE `xxl_job_log_report`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `running_count` int(11) NOT NULL DEFAULT 0 COMMENT '运行中-日志数量',
  `suc_count` int(11) NOT NULL DEFAULT 0 COMMENT '执行成功-日志数量',
  `fail_count` int(11) NOT NULL DEFAULT 0 COMMENT '执行失败-日志数量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_trigger_day`(`trigger_day`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_log_report
-- ----------------------------
INSERT INTO `xxl_job_log_report` VALUES (1, '2020-02-14 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (2, '2020-02-13 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (3, '2020-02-12 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (4, '2020-02-15 00:00:00', 0, 3, 3);
INSERT INTO `xxl_job_log_report` VALUES (5, '2020-02-16 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (6, '2020-02-17 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (7, '2020-02-18 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (8, '2020-02-19 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (9, '2020-02-20 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (10, '2020-02-21 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (11, '2020-02-22 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (12, '2020-02-23 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (13, '2020-02-24 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (14, '2020-02-25 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (15, '2020-02-26 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (16, '2020-02-27 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (17, '2020-02-28 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (18, '2020-02-29 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (19, '2020-03-01 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (20, '2020-03-02 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (21, '2020-03-03 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (22, '2020-03-04 00:00:00', 0, 0, 1);
INSERT INTO `xxl_job_log_report` VALUES (23, '2020-03-05 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (24, '2020-03-06 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (25, '2020-03-07 00:00:00', 0, 0, 1);
INSERT INTO `xxl_job_log_report` VALUES (26, '2020-03-08 00:00:00', 0, 4, 0);
INSERT INTO `xxl_job_log_report` VALUES (27, '2020-03-09 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (28, '2020-03-10 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (29, '2020-03-11 00:00:00', 0, 15, 0);

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_logglue`;
CREATE TABLE `xxl_job_logglue`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_logglue
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_registry`;
CREATE TABLE `xxl_job_registry`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `registry_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `registry_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `i_g_k_v`(`registry_group`, `registry_key`, `registry_value`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 791 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_registry
-- ----------------------------
INSERT INTO `xxl_job_registry` VALUES (575, 'EXECUTOR', 'logistics-job-executor', '172.17.0.7:9998', '2020-03-11 19:24:24');
INSERT INTO `xxl_job_registry` VALUES (576, 'EXECUTOR', 'logistics-job-executor', '172.17.0.5:9998', '2020-03-11 19:24:48');
INSERT INTO `xxl_job_registry` VALUES (628, 'EXECUTOR', 'kunming-job-executor', '172.17.0.2:9999', '2020-03-11 19:24:46');
INSERT INTO `xxl_job_registry` VALUES (758, 'EXECUTOR', 'kunming-job-executor', '10.118.180.197:9999', '2020-03-11 19:24:37');
INSERT INTO `xxl_job_registry` VALUES (765, 'EXECUTOR', 'kunming-job-executor', '10.118.180.195:9999', '2020-03-11 19:18:59');
INSERT INTO `xxl_job_registry` VALUES (784, 'EXECUTOR', 'kunming-job-executor', '10.118.180.201:9999', '2020-03-11 19:24:32');
INSERT INTO `xxl_job_registry` VALUES (788, 'EXECUTOR', 'logistics-job-executor', '10.118.180.186:9998', '2020-03-11 18:42:38');
INSERT INTO `xxl_job_registry` VALUES (789, 'EXECUTOR', 'logistics-job-executor', '10.118.180.201:9998', '2020-03-11 19:24:35');
INSERT INTO `xxl_job_registry` VALUES (790, 'EXECUTOR', 'logistics-job-executor', '10.118.180.197:9998', '2020-03-11 18:51:47');

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_user`;
CREATE TABLE `xxl_job_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `role` tinyint(4) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------
INSERT INTO `xxl_job_user` VALUES (1, 'admin', 'efc84ff613714b3c89aa45e5084dc7f4', 1, NULL);
INSERT INTO `xxl_job_user` VALUES (2, 'team', 'a9fd112269948d1ac500cfdc26b4a486', 1, '');

SET FOREIGN_KEY_CHECKS = 1;
