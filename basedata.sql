-- ----------------------------
-- Table structure for tbl_users
-- ----------------------------
DROP TABLE IF EXISTS `tbl_users`;
CREATE TABLE `tbl_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID主键',
  `username` varchar(120) NOT NULL COMMENT '用户名称',
  `password` varchar(60) NOT NULL COMMENT '用户密码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Table structure for tbl_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_dict`;
CREATE TABLE `tbl_sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID主键',
  `dict_type` varchar(50) NOT NULL DEFAULT '' COMMENT '字典类型',
  `dict_key` varchar(50) NOT NULL DEFAULT '' COMMENT '键',
  `dict_value` varchar(100) NOT NULL DEFAULT '' COMMENT '值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='数据字典';
