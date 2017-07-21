/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : ssm_demo

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2017-07-21 13:27:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_demo
-- ----------------------------
DROP TABLE IF EXISTS `t_demo`;
CREATE TABLE `t_demo` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID，自增，非业务属性',
  `LOGIN_NAME` varchar(255) NOT NULL COMMENT '登录名',
  `LOGIN_PWD` varchar(255) NOT NULL COMMENT '登录密码',
  `NICK_NAME` varchar(255) NOT NULL COMMENT '昵称',
  `REAL_NAME` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `GENDER` varchar(1) DEFAULT NULL COMMENT '性别，F-女，M-男，N-其他',
  `PHONE` char(11) DEFAULT NULL COMMENT '联系方式',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_demo
-- ----------------------------
INSERT INTO `t_demo` VALUES ('1', 'zhangsan', 'zhangsan', 'zhangsan', '张三', 'M', '12345678911', '中国');
