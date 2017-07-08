CREATE DATABASE basedata;
USE basedata;

-- ----------------------------
-- tbl_users
-- ----------------------------
DROP TABLE IF EXISTS `tbl_users`;
CREATE TABLE `alarm_settings` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(80) NOT NULL,
  `password` varchar(50) NOT NULL
  PRIMARY KEY  (`users_id`)
) ENGINE=InnoDB;