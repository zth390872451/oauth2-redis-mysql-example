/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50639
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2019-01-29 11:25:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', 'e10adc3949ba59abbe56e057f20f883e', 'username', '123456');

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(255) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('alipay_auth_mode', 'oauth2-resource', '$2a$10$TiQGOXIhO2eWFMcxlGY2Vu1MrEFr30gKv84q7j/lkJyRiZgh6DieG', 'read', 'authorization_code', 'http://www.newbee.cn:8052/oauth2-client/thirdLogin/AliPay', 'ROLE_MEMBER', '600', '0', null, 'false');
INSERT INTO `oauth_client_details` VALUES ('client_auth_mode', 'oauth2-resource', '$2a$10$TiQGOXIhO2eWFMcxlGY2Vu1MrEFr30gKv84q7j/lkJyRiZgh6DieG', 'read,write', 'client_credentials,refresh_token', null, 'ROLE_ADMIN', '2592000', null, null, null);
INSERT INTO `oauth_client_details` VALUES ('password_auth_mode', 'oauth2-resource', '$2a$10$TiQGOXIhO2eWFMcxlGY2Vu1MrEFr30gKv84q7j/lkJyRiZgh6DieG', 'read', 'refresh_token,password', '', 'ROLE_MEMBER', '600', '0', null, 'false');
INSERT INTO `oauth_client_details` VALUES ('qq_client_id', 'oauth2-resource', '$2a$10$TiQGOXIhO2eWFMcxlGY2Vu1MrEFr30gKv84q7j/lkJyRiZgh6DieG', 'read', 'authorization_code', 'http://www.newbee.cn:8052/oauth2-client/thirdLogin/QQ', 'ROLE_MEMBER', '600', '0', null, 'false');
INSERT INTO `oauth_client_details` VALUES ('wechat_client_id', 'oauth2-resource', '$2a$10$TiQGOXIhO2eWFMcxlGY2Vu1MrEFr30gKv84q7j/lkJyRiZgh6DieG', 'read', 'authorization_code', 'http://www.newbee.cn:8052/oauth2-client/thirdLogin/WeChat', 'ROLE_MEMBER', '600', '0', null, 'false');

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `code` varchar(255) DEFAULT NULL,
  `authentication` blob,
  KEY `code_index` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------