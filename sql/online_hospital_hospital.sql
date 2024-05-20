/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           : localhost:3306
 Source Schema         : online_hospital_hospital

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 21/05/2024 03:55:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for doctor_info
-- ----------------------------
DROP TABLE IF EXISTS `doctor_info`;
CREATE TABLE `doctor_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '医生姓名',
  `gender` int NOT NULL DEFAULT 1 COMMENT '性别：1.男 0.女',
  `professional_title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职称',
  `introduce` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '简介',
  `hospital_id` bigint NULL DEFAULT NULL COMMENT '所属医院',
  `outpatient_id` bigint NULL DEFAULT NULL COMMENT '所属门诊',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_inquiry` int NULL DEFAULT 0 COMMENT '是否问诊(0.否 1.是)',
  `is_out_call` int NULL DEFAULT NULL COMMENT '是否出诊(0.否 1.是)',
  `waiting_num` int NULL DEFAULT 0 COMMENT '候诊人数',
  `picture` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '123456' COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1009 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '医生信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for hospital_info
-- ----------------------------
DROP TABLE IF EXISTS `hospital_info`;
CREATE TABLE `hospital_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话',
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `introduction` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '介绍',
  `picture` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分院信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for hospital_outpatient
-- ----------------------------
DROP TABLE IF EXISTS `hospital_outpatient`;
CREATE TABLE `hospital_outpatient`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `hospital_id` bigint NULL DEFAULT NULL COMMENT '医院id',
  `outpatient_id` bigint NULL DEFAULT NULL COMMENT '门诊id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '医院-门诊对应表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for hospital_specialist
-- ----------------------------
DROP TABLE IF EXISTS `hospital_specialist`;
CREATE TABLE `hospital_specialist`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `hospital_id` bigint NULL DEFAULT NULL COMMENT '医院id',
  `specialist_id` bigint NULL DEFAULT NULL COMMENT '专科id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '医院-专科对应表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for outpatient_info
-- ----------------------------
DROP TABLE IF EXISTS `outpatient_info`;
CREATE TABLE `outpatient_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `specialist_id` bigint NOT NULL COMMENT '所属专科',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `disc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `hospital_id` bigint NULL DEFAULT NULL COMMENT '医院id',
  `first_letter` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '首字母',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '门诊信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for specialist_info
-- ----------------------------
DROP TABLE IF EXISTS `specialist_info`;
CREATE TABLE `specialist_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `hospital_id` bigint NULL DEFAULT NULL COMMENT '医院id',
  `disc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '介绍',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '专科名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '专科' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
