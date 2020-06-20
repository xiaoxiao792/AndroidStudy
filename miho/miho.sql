/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : mysql:3306
 Source Schema         : miho

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 30/05/2020 21:58:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for daystatistics
-- ----------------------------
DROP TABLE IF EXISTS `daystatistics`;
CREATE TABLE `daystatistics` (
  `daystatisticsid` varchar(20) NOT NULL COMMENT '日统计表id',
  `userid` varchar(20) NOT NULL COMMENT '用户ID',
  `date` date NOT NULL COMMENT '日期',
  `plannum` int(5) NOT NULL COMMENT '计划数',
  `clockinnum` int(5) NOT NULL COMMENT '打卡数',
  `clockoutnum` int(5) NOT NULL COMMENT '漏卡数',
  `duration` int(11) NOT NULL DEFAULT '0' COMMENT '时间管理',
  PRIMARY KEY (`daystatisticsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for monthstatistics
-- ----------------------------
DROP TABLE IF EXISTS `monthstatistics`;
CREATE TABLE `monthstatistics` (
  `monthstatisticsid` varchar(20) NOT NULL COMMENT '月统计表ID',
  `userid` varchar(20) NOT NULL COMMENT '用户ID',
  `month` int(5) NOT NULL COMMENT '月份',
  `plannum` int(5) NOT NULL COMMENT '计划数',
  `clockinnum` int(5) NOT NULL COMMENT '打卡数',
  `clockoutnum` int(5) NOT NULL COMMENT '漏卡数',
  `duration` int(11) NOT NULL DEFAULT '0' COMMENT '时间管理',
  PRIMARY KEY (`monthstatisticsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `planid` varchar(20) NOT NULL COMMENT '计划ID',
  `userid` varchar(20) NOT NULL COMMENT '用户ID',
  `planname` varchar(255) NOT NULL COMMENT '计划名称',
  `icon` varchar(255) NOT NULL COMMENT '图标',
  `color` varchar(255) NOT NULL COMMENT '颜色',
  `timeclass` int(5) NOT NULL COMMENT '1 仅一次；2 每日；3 工作日；4 周末',
  `begindate` date NOT NULL COMMENT '开始日期',
  `enddate` date DEFAULT NULL COMMENT '结束日期',
  `state` int(5) NOT NULL COMMENT '计划状态：1 正在执行；2 已结束',
  `forceclass` int(5) NOT NULL COMMENT '计划模式：1 时间管理模式；2 仅打卡',
  `duration` int(5) NOT NULL DEFAULT '0' COMMENT '时长',
  `clockinnum` int(11) NOT NULL COMMENT '打卡次数',
  `clockoutnum` int(11) NOT NULL COMMENT '漏卡次数',
  PRIMARY KEY (`planid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `recordid` varchar(20) NOT NULL COMMENT '记录表ID',
  `planid` varchar(20) NOT NULL COMMENT '计划ID',
  `userid` varchar(20) NOT NULL COMMENT '用户ID',
  `date` date NOT NULL COMMENT '日期',
  `isclockin` int(5) NOT NULL DEFAULT '0' COMMENT '是否打卡：1打卡；0漏卡',
  PRIMARY KEY (`recordid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` varchar(20) NOT NULL COMMENT '用户名',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `registertime` datetime DEFAULT NULL COMMENT '注册时间',
  `userlevel` int(5) DEFAULT '0' COMMENT '用户等级 0-普通 1-低级会员 2-高级会员',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `other` varchar(255) DEFAULT NULL COMMENT '为以后留作它用',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
