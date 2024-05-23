/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           : localhost:3306
 Source Schema         : online_hospital_registration

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 23/05/2024 12:21:22
*/

CREATE DATABASE online_hospital_registration;
USE online_hospital_registration;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` bigint NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  `out_trade_no` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单交易号',
  `hospital_id` bigint NULL DEFAULT NULL COMMENT '医院编号',
  `hospital_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '医院名称',
  `outpatient_id` bigint NULL DEFAULT NULL COMMENT '门诊编号',
  `outpatient_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '门诊名称',
  `reserve_date` date NULL DEFAULT NULL,
  `reserve_half` int NULL DEFAULT NULL,
  `reserve_time` int NULL DEFAULT NULL,
  `patient_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `patient_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `amount` decimal(10, 0) NULL DEFAULT 0 COMMENT '预约费',
  `order_status` tinyint NULL DEFAULT NULL COMMENT '订单状态:0.未完成1.已完成 2.已超时 3.已取消',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `is_deleted` tinyint NULL DEFAULT 0,
  `schedule_id` bigint NULL DEFAULT NULL COMMENT '排班id',
  `doctor_id` bigint NULL DEFAULT NULL COMMENT '医生编号',
  `doctor_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '医生姓名',
  `patient_id` bigint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_info
-- ----------------------------

-- ----------------------------
-- Table structure for report_info
-- ----------------------------
DROP TABLE IF EXISTS `report_info`;
CREATE TABLE `report_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id\n',
  `patient_id` bigint NULL DEFAULT NULL COMMENT '就诊人id',
  `patient_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '就诊人姓名',
  `send_time` datetime NULL DEFAULT NULL COMMENT '发送时间',
  `report_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报告名称',
  `file_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件地址',
  `doctor_id` bigint NULL DEFAULT NULL COMMENT '医生id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '检验报告' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of report_info
-- ----------------------------

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `hospital_id` bigint NULL DEFAULT NULL COMMENT '医院id',
  `outpatient_id` bigint NULL DEFAULT NULL COMMENT '门诊id',
  `doctor_id` bigint NULL DEFAULT NULL COMMENT '医生id',
  `work_date` date NULL DEFAULT NULL COMMENT '排版日期',
  `work_half` int NULL DEFAULT NULL COMMENT '排版时间（0.上午1.下午）',
  `work_time` int NULL DEFAULT NULL COMMENT '排版具体时间：1-7\r\n如果是上午，则是从8点到11点半，中间以半小时为一格，分别为1-7\r\n如果是下午，则是从1点到4点半',
  `reserved_number` int NULL DEFAULT 0 COMMENT '可预约数',
  `available_number` int NULL DEFAULT 0 COMMENT '剩余预约数',
  `amount` decimal(10, 0) NULL DEFAULT NULL COMMENT '挂号费',
  `status` int NULL DEFAULT NULL COMMENT '排版状态（-1.停诊 0.停约 1.可约）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '排版信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
