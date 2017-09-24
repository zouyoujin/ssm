-- ----------------------------
-- Table structure for tbl_users
-- ----------------------------
DROP TABLE IF EXISTS `tbl_users`;
CREATE TABLE `tbl_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `username` varchar(50) NOT NULL COMMENT '用户名称',
  `password` varchar(32) NOT NULL COMMENT '用户密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for tbl_user_addr
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_addr`;
CREATE TABLE `tbl_user_addr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `receiver_name` varchar(20) DEFAULT NULL COMMENT '收货人全名',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '固定电话',
  `receiver_mobile` varchar(30) DEFAULT NULL COMMENT '移动电话',
  `receiver_state` varchar(10) DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(10) DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(500) DEFAULT NULL COMMENT '收货地址，如：xx路xx号',
  `receiver_zip` varchar(6) DEFAULT NULL COMMENT '邮政编码,如：110000',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='用户地址信息表';

-- ----------------------------
-- Table structure for tbl_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_dict`;
CREATE TABLE `tbl_sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `dict_type` varchar(50) NOT NULL DEFAULT '' COMMENT '字典类型',
  `dict_key` varchar(50) NOT NULL DEFAULT '' COMMENT '键',
  `dict_value` varchar(100) NOT NULL DEFAULT '' COMMENT '值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='数据字典';
