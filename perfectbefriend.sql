/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : perfectbefriend

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2015-07-31 14:54:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `level` int(11) NOT NULL,
  `time` varchar(255) DEFAULT '2014-04-22 15:14:50',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `summary` text,
  `time` varchar(255) DEFAULT NULL,
  `pathapk` varchar(255) DEFAULT NULL,
  `pathimg` varchar(255) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  `downloads` int(11) DEFAULT NULL,
  `pathimg1` varchar(255) DEFAULT NULL,
  `pathimg2` varchar(255) DEFAULT NULL,
  `pathimg3` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `vnum` varchar(255) DEFAULT NULL,
  `dpt` text,
  `realds` int(11) DEFAULT NULL,
  `appsize` float(11,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for appup
-- ----------------------------
DROP TABLE IF EXISTS `appup`;
CREATE TABLE `appup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apptv` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `updates` text,
  `upnum` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for behavior
-- ----------------------------
DROP TABLE IF EXISTS `behavior`;
CREATE TABLE `behavior` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `keyword` varchar(255) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `count` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=665 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cis
-- ----------------------------
DROP TABLE IF EXISTS `cis`;
CREATE TABLE `cis` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `senduserid` int(11) DEFAULT '0',
  `groupid` int(11) DEFAULT '0',
  `information` text,
  `time` datetime DEFAULT NULL,
  `userid` int(255) DEFAULT '0',
  `online` int(11) DEFAULT '0',
  `ip` varchar(255) DEFAULT '0.0.0.0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8107 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `newsid` int(11) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `newstitle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=367 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(11) DEFAULT NULL,
  `information` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for follect
-- ----------------------------
DROP TABLE IF EXISTS `follect`;
CREATE TABLE `follect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `forumid` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=304 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for forumone
-- ----------------------------
DROP TABLE IF EXISTS `forumone`;
CREATE TABLE `forumone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT '0',
  `total` int(11) DEFAULT '0',
  `userid` int(11) DEFAULT '0',
  `time` varchar(255) DEFAULT NULL,
  `content` longtext,
  `area` varchar(255) DEFAULT NULL,
  `areas` varchar(255) DEFAULT NULL,
  `follectnum` int(11) DEFAULT '0',
  `frs` int(11) DEFAULT '0',
  `fHits` int(11) DEFAULT '0',
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for forumthree
-- ----------------------------
DROP TABLE IF EXISTS `forumthree`;
CREATE TABLE `forumthree` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `touserid` int(11) DEFAULT NULL,
  `reply` longtext,
  `time` varchar(255) DEFAULT NULL,
  `forumid` int(11) DEFAULT NULL,
  `forumtwoid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for forumtwo
-- ----------------------------
DROP TABLE IF EXISTS `forumtwo`;
CREATE TABLE `forumtwo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `reply` longtext,
  `time` varchar(255) DEFAULT NULL,
  `forumid` int(11) DEFAULT NULL,
  `touserid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=624 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for groupchat
-- ----------------------------
DROP TABLE IF EXISTS `groupchat`;
CREATE TABLE `groupchat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '我的班级',
  `userid` int(255) DEFAULT '1234',
  `img` varchar(255) DEFAULT NULL,
  `schoolname` varchar(255) DEFAULT NULL,
  `schooladdress` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `gclass` varchar(255) DEFAULT NULL,
  `headteachername` varchar(255) DEFAULT NULL,
  `htphone` varchar(255) DEFAULT NULL,
  `groupno` int(11) DEFAULT '0',
  `time` varchar(255) DEFAULT '2014-02-11 16:52:30',
  `groupid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for groupmembers
-- ----------------------------
DROP TABLE IF EXISTS `groupmembers`;
CREATE TABLE `groupmembers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT '0',
  `groupid` int(11) DEFAULT '0',
  `urp` int(1) DEFAULT '0',
  `time` varchar(255) DEFAULT '2015-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for group_friend
-- ----------------------------
DROP TABLE IF EXISTS `group_friend`;
CREATE TABLE `group_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_group_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_group_id` (`user_group_id`),
  CONSTRAINT `group_friend_ibfk_1` FOREIGN KEY (`user_group_id`) REFERENCES `user_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7179 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `content` longtext,
  `img` text,
  `time` varchar(255) DEFAULT '2015-01-01 00:00:00',
  `collectnum` int(11) DEFAULT '0',
  `reviews` int(11) DEFAULT '0',
  `area` varchar(255) DEFAULT NULL,
  `areas` varchar(255) DEFAULT NULL,
  `type` int(255) DEFAULT '0',
  `types` int(255) DEFAULT '0',
  `hits` int(11) DEFAULT '0',
  `cah` int(11) DEFAULT '0',
  `admin` varchar(255) DEFAULT '无',
  `expert` int(11) DEFAULT '0',
  `label` longtext,
  `supports` int(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1887 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for password
-- ----------------------------
DROP TABLE IF EXISTS `password`;
CREATE TABLE `password` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `uid` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=593 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for profile
-- ----------------------------
DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupid` int(11) DEFAULT '0',
  `sdname` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `kip` varchar(255) DEFAULT NULL,
  `ddb` int(11) DEFAULT '0',
  `judge` int(11) DEFAULT '-1',
  `rsbs` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT '0',
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for registration
-- ----------------------------
DROP TABLE IF EXISTS `registration`;
CREATE TABLE `registration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT '未填写',
  `addcity` varchar(255) DEFAULT '未填写',
  `Note7` int(11) DEFAULT '0',
  `Note30` int(11) DEFAULT '0',
  `Note365` int(11) DEFAULT '0',
  `noteall` int(11) DEFAULT '0',
  `Note` int(11) DEFAULT '0',
  `time` varchar(255) DEFAULT '2015-01-01 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) DEFAULT NULL,
  `newsid` int(255) DEFAULT NULL,
  `review` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=626 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stas
-- ----------------------------
DROP TABLE IF EXISTS `stas`;
CREATE TABLE `stas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vored` int(11) DEFAULT '0',
  `usersaved` int(11) DEFAULT '0',
  `userlogined` int(11) DEFAULT '0',
  `usersyned` int(11) DEFAULT '0',
  `downloaded` int(11) DEFAULT '0',
  `time` varchar(255) DEFAULT NULL,
  `os` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for support
-- ----------------------------
DROP TABLE IF EXISTS `support`;
CREATE TABLE `support` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(11) NOT NULL,
  `newsid` varchar(11) NOT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT '未填写',
  `phone` varchar(255) DEFAULT '未填写',
  `stage` varchar(255) DEFAULT '未填写',
  `school` varchar(255) DEFAULT '未填写',
  `address` varchar(255) DEFAULT '未选取',
  `addcity` varchar(255) DEFAULT '未选取',
  `time` varchar(255) DEFAULT '2015-01-01 00:00:00',
  `img` varchar(255) DEFAULT NULL,
  `competence` int(11) DEFAULT '0',
  `gag` int(11) DEFAULT '0',
  `loginnum` int(11) DEFAULT '0',
  `finaltime` varchar(255) DEFAULT '2015-01-01 00:00:00',
  `ip` varchar(255) DEFAULT '0.0.0.0',
  `port` int(11) DEFAULT '0',
  `online` int(11) DEFAULT '0',
  `accnumno` varchar(255) DEFAULT NULL,
  `come` varchar(255) DEFAULT NULL,
  `os` varchar(255) DEFAULT NULL,
  `mac` varchar(255) DEFAULT NULL,
  `sex` varchar(11) DEFAULT '0',
  `signature` varchar(255) DEFAULT NULL,
  `childrenage` varchar(11) DEFAULT '3',
  `childrensex` varchar(11) DEFAULT '0',
  `age` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=676 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `order_num` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_group_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for visitor
-- ----------------------------
DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appmac` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `os` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
