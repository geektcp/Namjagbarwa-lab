CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `code` varchar(128) DEFAULT NULL COMMENT '角色代码',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `enabled_flag` char(1) NOT NULL DEFAULT 'Y' COMMENT '可用状态:Y-是;N-否',
  `created_by` varchar(50) NOT NULL DEFAULT '',
  `created_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` varchar(50) NOT NULL DEFAULT '',
  `updated_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`)
);

CREATE TABLE `sys_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT 'sys_role.id',
  `resource_id` int(11) NOT NULL COMMENT 'sys_resource.id',
  PRIMARY KEY (`id`)
);

CREATE TABLE `sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50)  NOT NULL COMMENT '资源名称',
  `code` varchar(50)  DEFAULT NULL COMMENT '权限code,前端使用',
  `group` varchar(30)  DEFAULT NULL COMMENT '权限分组，eg:P,S',
  `type` varchar(30)  NOT NULL DEFAULT '' COMMENT '类型，按子产品或模块划分，取值[DMP-数据平台，GAP-分析平台，AP-应用平台]',
  `sub_type` varchar(30)  DEFAULT NULL COMMENT '子类型, 如:SERVICE',
  `parent_id` int(11) NOT NULL COMMENT '父ID，根节点默认为0',
  `url` varchar(200)  DEFAULT NULL COMMENT '资源访问URL地址',
  `remark` varchar(200)  DEFAULT NULL COMMENT '备注',
  `enabled_flag` char(1)  NOT NULL DEFAULT 'Y' COMMENT '可用状态:Y-是;N-否',
  `created_by` varchar(50)  NOT NULL DEFAULT '',
  `created_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` varchar(50)  NOT NULL DEFAULT '',
  `updated_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`)
);

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_no` varchar(128)  NOT NULL COMMENT '用户名-登录',
  `password` varchar(128)  DEFAULT NULL COMMENT '登录密码',
  `name` varchar(50)  NOT NULL COMMENT '姓名',
  `phone` varchar(20)  DEFAULT NULL COMMENT '手机',
  `email` varchar(128)  DEFAULT NULL COMMENT '邮箱',
  `user_source` varchar(30)  NOT NULL COMMENT '用户来源:[SSO-单点登录,SYS_CREATION-系统创建]',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态，取值[1-正常，0-冻结]',
  `enabled_flag` char(1)  NOT NULL DEFAULT 'Y' COMMENT '可用状态:Y-是;N-否',
  `created_by` varchar(50)  NOT NULL DEFAULT '',
  `created_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` varchar(50)  NOT NULL DEFAULT '',
  `updated_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`)
);

