/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           : localhost:3306
 Source Schema         : online_hospital_inquiry

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 23/05/2024 12:21:15
*/

CREATE DATABASE online_hospital_inquiry;
USE online_hospital_inquiry;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chat_list
-- ----------------------------
DROP TABLE IF EXISTS `chat_list`;
CREATE TABLE `chat_list`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `link_id` bigint NULL DEFAULT NULL COMMENT '用户聊天关系表id',
  `from_user_id` bigint NULL DEFAULT NULL COMMENT '发送方id',
  `from_user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发送方姓名',
  `from_user_identity` int NULL DEFAULT NULL COMMENT '发送方身份：0.用户 1.医生',
  `to_user_id` bigint NULL DEFAULT NULL COMMENT '接收方id',
  `to_user_identity` int NULL DEFAULT NULL COMMENT '接收方身份：0.用户 1.医生',
  `to_user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接收方姓名',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `from_window` int NULL DEFAULT NULL COMMENT '发送方是否在窗口(0.否 1.是)',
  `to_window` int NULL DEFAULT NULL COMMENT '接收方是否在窗口(0.否 1.是)',
  `unread` int NULL DEFAULT NULL COMMENT '未读数',
  `status` int NULL DEFAULT 0 COMMENT '列表状态(0.未完成 1.已完成)',
  `inquiry_application_id` bigint NULL DEFAULT NULL COMMENT '问诊申请id',
  `last_message_time` datetime NULL DEFAULT NULL COMMENT '最后一条消息时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '聊天列表表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of chat_list
-- ----------------------------

-- ----------------------------
-- Table structure for chat_message
-- ----------------------------
DROP TABLE IF EXISTS `chat_message`;
CREATE TABLE `chat_message`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `link_id` bigint NULL DEFAULT NULL COMMENT '用户聊天关系表id',
  `inquiry_application_id` bigint NULL DEFAULT NULL COMMENT '问诊申请id',
  `from_user_id` bigint NULL DEFAULT NULL COMMENT '发送方id',
  `from_user_identity` int NULL DEFAULT NULL COMMENT '发送方身份：0.用户 1.医生',
  `from_user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发送方姓名',
  `to_user_id` bigint NULL DEFAULT NULL COMMENT '接收方id',
  `to_user_identity` int NULL DEFAULT NULL COMMENT '接收方身份：0.用户 1.医生',
  `to_user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接收方姓名',
  `content` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '消息内容',
  `send_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  `type` int NULL DEFAULT NULL COMMENT '类型：0.文字 1.图片',
  `is_latest` int NULL DEFAULT NULL COMMENT '是否是最后一条消息:0.否 1.是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '聊天内容详情表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of chat_message
-- ----------------------------

-- ----------------------------
-- Table structure for chat_user_link
-- ----------------------------
DROP TABLE IF EXISTS `chat_user_link`;
CREATE TABLE `chat_user_link`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `from_user_id` bigint NULL DEFAULT NULL COMMENT '发送方id',
  `from_user_identity` int NULL DEFAULT NULL COMMENT '发送方身份：0.用户 1.医生',
  `from_user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发送方姓名',
  `to_user_id` bigint NULL DEFAULT NULL COMMENT '接收方id',
  `to_user_identity` int NULL DEFAULT NULL COMMENT '接收方身份：0.用户 1.医生',
  `to_user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接收方姓名',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `inquiry_application_id` bigint NULL DEFAULT NULL COMMENT '问诊申请id',
  `is_finished` int NULL DEFAULT NULL COMMENT '是否结束 0.否 1.是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户聊天关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of chat_user_link
-- ----------------------------

-- ----------------------------
-- Table structure for inquiry_application
-- ----------------------------
DROP TABLE IF EXISTS `inquiry_application`;
CREATE TABLE `inquiry_application`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `patient_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '就诊人姓名',
  `description` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '病情描述信息（300字以内）',
  `image` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片',
  `hospital_id` bigint NULL DEFAULT NULL COMMENT '医院id',
  `hospital_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '医院名称',
  `outpatient_id` bigint NULL DEFAULT NULL COMMENT '门诊id',
  `outpatient_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '门诊名称',
  `doctor_id` bigint NULL DEFAULT NULL COMMENT '医生id',
  `doctor_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '医生姓名',
  `status` int NULL DEFAULT NULL COMMENT '状态（0.排队 1.进行 2.结束 3.取消）',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cancel_time` datetime NULL DEFAULT NULL COMMENT '取消时间',
  `patient_id` bigint NULL DEFAULT NULL COMMENT '就诊人id',
  `remain_number` int NULL DEFAULT 10 COMMENT '剩余聊天次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of inquiry_application
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
