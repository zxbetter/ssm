/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : ssm_spittr

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2017-08-28 23:18:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for spitter
-- ----------------------------
DROP TABLE IF EXISTS `spitter`;
CREATE TABLE `spitter` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `FIRST_NAME` varchar(30) NOT NULL COMMENT '名',
  `LAST_NAME` varchar(30) NOT NULL COMMENT '姓',
  `USERNAME` varchar(16) NOT NULL COMMENT '登录名',
  `PASSWORD` varchar(25) NOT NULL COMMENT '登录密码',
  `EMAIL` varchar(255) DEFAULT NULL COMMENT 'email',
  `UPDATE_BY_EMAIL` varchar(1) DEFAULT NULL COMMENT 'Y-true, N-false',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spitter
-- ----------------------------
INSERT INTO `spitter` VALUES ('1', 'abc', 'def', 'admin', 'admin', null, null);

-- ----------------------------
-- Table structure for spittle
-- ----------------------------
DROP TABLE IF EXISTS `spittle`;
CREATE TABLE `spittle` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `MESSAGE` varchar(600) NOT NULL COMMENT '内容',
  `TIME` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `LATITUDE` double DEFAULT NULL COMMENT '维度',
  `LONGITUDE` double DEFAULT NULL COMMENT '经度',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spittle
-- ----------------------------
INSERT INTO `spittle` VALUES ('1', '123', '2017-08-02 17:10:28', '12', '13');
INSERT INTO `spittle` VALUES ('2', '234', '2017-08-06 17:10:22', '34', '23');
INSERT INTO `spittle` VALUES ('3', '345', '2017-08-08 17:10:16', '63', '35');
INSERT INTO `spittle` VALUES ('4', '456', '2017-08-17 17:10:09', '34', '43');
INSERT INTO `spittle` VALUES ('5', '567', '2017-08-23 17:10:00', '45', '76');
INSERT INTO `spittle` VALUES ('6', '678', '2017-08-28 17:09:35', null, null);
