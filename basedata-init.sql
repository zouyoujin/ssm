-- ----------------------------
--  Records of `tb_user`
-- ----------------------------
BEGIN;
INSERT INTO `tbl_users` (`id`, `account`, `password`, `user_type`, `user_name`, `name_pinyin`, `sex`, `avatar`, `phone`, `email`, `id_card`, `wei_xin`, `wei_bo`, `qq`, `birth_day`, `dept_id`, `position`, `address`, `staff_no`, `enable`, `remark`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, 'admin', 'i/sV2VpTPy7Y+ppesmkCmM==', '3', '管理员', 'GUANLIYUAN', 0, 'http://118.190.43.148/group1/M00/00/00/dr4rlFjNBguAfJl7AAcOE67NTFk744.png', '15333821711', '12@12', NULL, NULL, NULL, NULL, '2017-01-27', 2, '213', NULL, NULL, 1, '1', '2016-05-06 10:06:52', 1, '2017-03-18 18:03:55', 1),
	(2, 'test', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', 'admin', 'CESHIRENYUAN', 1, 'http://118.190.43.148/group1/M00/00/00/dr4rlFj3H0iATcqFAAv7S9z_iMg689.png', '12345678901', '123@163.com', NULL, NULL, NULL, NULL, '2017-02-01', 825363166504628224, '测试', '', NULL, 1, '1', '2016-05-13 16:58:17', 1, '2017-05-29 08:31:38', 1);
COMMIT;