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

 Date: 23/05/2024 12:21:09
*/

CREATE DATABASE online_hospital_hospital;
USE online_hospital_hospital;

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
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '医生信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of doctor_info
-- ----------------------------
INSERT INTO `doctor_info` VALUES (1, '博裕', 0, '住院医师', '住院医师', 1, 1, '2024-03-07 19:38:39', '2024-05-16 22:25:07', 1, 1, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/16630596851710.8934494188127735.jpg', 'netmedic1', '123456');
INSERT INTO `doctor_info` VALUES (2, '博豪', 1, '住院医师', '住院医师', 1, 1, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/04163509651710.6988727811880961.jpg', 'netmedic2', '123456');
INSERT INTO `doctor_info` VALUES (3, '思翰', 0, '住院医师', '住院医师', 1, 2, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/30652049651710.1388778998901563.jpg', 'netmedic3', '123456');
INSERT INTO `doctor_info` VALUES (4, '雨萌', 0, '住院医师', '住院医师', 1, 2, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/57711249651710.8627065531255446.jpg', 'netmedic4', '123456');
INSERT INTO `doctor_info` VALUES (5, '晓晴', 1, '住院医师', '住院医师', 1, 3, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/54407159651710.35339890196417645.jpg', 'netmedic5', '123456');
INSERT INTO `doctor_info` VALUES (6, '浩霖', 0, '住院医师', '住院医师', 1, 3, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/04163509651710.6988727811880961.jpg', 'netmedic6', '123456');
INSERT INTO `doctor_info` VALUES (7, '怡香', 1, '住院医师', '住院医师', 1, 4, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/30652049651710.1388778998901563.jpg', 'netmedic7', '123456');
INSERT INTO `doctor_info` VALUES (8, '尹智', 1, '住院医师', '住院医师', 1, 4, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/57711249651710.8627065531255446.jpg', 'netmedic8', '123456');
INSERT INTO `doctor_info` VALUES (9, '墨含', 1, '住院医师', '住院医师', 1, 5, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/54407159651710.35339890196417645.jpg', 'netmedic9', '123456');
INSERT INTO `doctor_info` VALUES (10, '墨含', 1, '住院医师', '住院医师', 1, 5, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/04163509651710.6988727811880961.jpg', 'netmedic10', '123456');
INSERT INTO `doctor_info` VALUES (11, '雪丽', 0, '住院医师', '住院医师', 1, 6, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/30652049651710.1388778998901563.jpg', 'netmedic11', '123456');
INSERT INTO `doctor_info` VALUES (12, '依娜', 1, '住院医师', '住院医师', 1, 6, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/57711249651710.8627065531255446.jpg', 'netmedic12', '123456');
INSERT INTO `doctor_info` VALUES (13, '博豪', 1, '住院医师', '住院医师', 1, 7, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/54407159651710.35339890196417645.jpg', 'netmedic13', '123456');
INSERT INTO `doctor_info` VALUES (14, '凰羽', 1, '住院医师', '住院医师', 1, 7, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/04163509651710.6988727811880961.jpg', 'netmedic14', '123456');
INSERT INTO `doctor_info` VALUES (15, '伟菘', 0, '住院医师', '住院医师', 1, 8, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/30652049651710.1388778998901563.jpg', 'netmedic15', '123456');
INSERT INTO `doctor_info` VALUES (16, '俊誉', 1, '住院医师', '住院医师', 1, 8, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/57711249651710.8627065531255446.jpg', 'netmedic16', '123456');
INSERT INTO `doctor_info` VALUES (17, '嫦曦', 1, '住院医师', '住院医师', 1, 9, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/54407159651710.35339890196417645.jpg', 'netmedic17', '123456');
INSERT INTO `doctor_info` VALUES (18, '思翰', 0, '住院医师', '住院医师', 1, 9, '2024-03-07 19:38:39', '2024-03-07 19:38:39', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/04163509651710.6988727811880961.jpg\r\nhttps://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/30652049651710.1388778998901563.jpg\r\nhttps://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/57711249651710.8627065531255446.jpg\r\nhttps://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/54407159651710.35339890196417645.jpg', 'netmedic18', '123456');
INSERT INTO `doctor_info` VALUES (19, '李默一', 1, '住院医师', '专业医师团队成员', 1, 1, '2024-05-14 19:03:52', '2024-05-14 21:20:50', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/04163509651710.6988727811880961.jpg', 'netmedic19', '123456');
INSERT INTO `doctor_info` VALUES (20, '李承', 1, '主刀医师', '暂无', 1, 1, '2024-05-14 21:40:28', '2024-05-14 21:40:28', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/30652049651710.1388778998901563.jpg', 'netmedic20', '123456');
INSERT INTO `doctor_info` VALUES (21, '李路', 1, '主刀医师', '暂无', 1, 1, '2024-05-14 21:48:13', '2024-05-14 21:48:15', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/57711249651710.8627065531255446.jpg', 'netmedic21', '123456');
INSERT INTO `doctor_info` VALUES (22, '艺龙', 1, '主任医师', '暂无', 1, 2, '2024-05-14 21:59:32', '2024-05-14 21:59:33', 1, 0, 0, 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/54407159651710.35339890196417645.jpg', 'netmedic22', '123456');

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
-- Records of hospital_info
-- ----------------------------
INSERT INTO `hospital_info` VALUES (1, '主院区', '12345678910', '西安市西咸新区秦汉新城泾渭大道南段5000号', '1.就诊路线提醒：儿童就诊和住院请走友谊路北大门进入；急诊就诊请走友谊路北二门进入；门诊就诊可选择友谊路北大门、含光路东一门进入。友谊院区可乘坐地铁5号线，在“陕西省人民医院”站下车从B2通道出站，由东一门进入；西咸院区可乘坐咸阳市公交副840路、1071路、1147路至“省人民医院西咸院区”站下车。', 'https://online-hospital-nwu.oss-cn-hangzhou.aliyuncs.com/52149552651710.4066003828641307.jpg', '2024-03-07 17:56:03', '2024-05-14 16:17:40');

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
-- Records of hospital_outpatient
-- ----------------------------
INSERT INTO `hospital_outpatient` VALUES (1, 1, 1, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (2, 1, 2, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (3, 1, 3, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (4, 1, 4, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (5, 1, 5, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (6, 1, 6, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (7, 1, 7, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (8, 1, 8, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (9, 1, 9, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (10, 1, 10, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (11, 1, 11, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (12, 1, 12, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (13, 1, 13, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (14, 1, 14, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (15, 1, 15, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (16, 1, 16, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (17, 1, 17, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (18, 1, 18, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (19, 1, 19, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (20, 1, 20, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (21, 1, 21, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (22, 1, 22, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (23, 1, 23, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (24, 1, 24, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (25, 1, 25, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (26, 1, 26, '2024-03-07 19:29:26');
INSERT INTO `hospital_outpatient` VALUES (27, 1, 27, '2024-03-07 19:29:26');

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
-- Records of hospital_specialist
-- ----------------------------
INSERT INTO `hospital_specialist` VALUES (1, 1, 1, '2024-03-07 19:01:24');
INSERT INTO `hospital_specialist` VALUES (2, 1, 2, '2024-03-07 19:01:24');
INSERT INTO `hospital_specialist` VALUES (3, 1, 3, '2024-03-07 19:01:24');
INSERT INTO `hospital_specialist` VALUES (4, 1, 4, '2024-03-07 19:01:24');
INSERT INTO `hospital_specialist` VALUES (5, 1, 5, '2024-03-07 19:01:24');
INSERT INTO `hospital_specialist` VALUES (6, 1, 6, '2024-03-07 19:01:24');

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
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '门诊信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of outpatient_info
-- ----------------------------
INSERT INTO `outpatient_info` VALUES (1, '感染性疾病科', 1, '2024-03-07 19:08:20', '2024-05-14 18:19:26', '传染病的筛查和感染性疾病的治疗。', 1, 'G');
INSERT INTO `outpatient_info` VALUES (2, '心血管内科', 1, '2024-03-07 19:09:19', '2024-03-07 19:09:20', '心血管内科门诊', 1, 'X');
INSERT INTO `outpatient_info` VALUES (3, '神经内科', 1, '2024-03-07 19:12:42', '2024-03-07 19:12:43', '神经内科门诊', 1, 'S');
INSERT INTO `outpatient_info` VALUES (4, '周围神经疾病门诊', 1, '2024-03-07 19:13:20', '2024-03-07 19:13:20', '周围神经疾病门诊', 1, 'Z');
INSERT INTO `outpatient_info` VALUES (5, '头痛门诊', 1, '2024-03-07 19:13:35', '2024-03-07 19:13:36', '头痛门诊', 1, 'T');
INSERT INTO `outpatient_info` VALUES (6, '眩晕门诊', 1, '2024-03-07 19:14:02', '2024-03-07 19:14:02', '眩晕门诊', 1, 'X');
INSERT INTO `outpatient_info` VALUES (7, '呼吸与危重症医学科', 1, '2024-03-07 19:14:38', '2024-03-07 19:14:38', '呼吸与危重症医学科', 1, 'H');
INSERT INTO `outpatient_info` VALUES (8, '内分泌科', 1, '2024-03-07 19:14:55', '2024-03-07 19:14:56', '内分泌科门诊', 1, 'N');
INSERT INTO `outpatient_info` VALUES (9, '代谢减重门诊', 1, '2024-03-07 19:15:25', '2024-03-07 19:15:26', '代谢减重门诊', 1, 'D');
INSERT INTO `outpatient_info` VALUES (10, '普外科', 2, '2024-03-07 19:15:57', '2024-03-07 19:15:57', '普外科', 1, 'P');
INSERT INTO `outpatient_info` VALUES (11, '骨外科', 2, '2024-03-07 19:16:08', '2024-03-07 19:16:08', '骨外科', 1, 'G');
INSERT INTO `outpatient_info` VALUES (12, '脊柱椎间盘专病门诊', 2, '2024-03-07 19:16:38', '2024-03-07 19:16:38', '脊柱椎间盘专病门诊', 1, 'J');
INSERT INTO `outpatient_info` VALUES (13, '移植泌尿外科', 2, '2024-03-07 19:17:07', '2024-03-07 19:17:08', '移植泌尿外科', 1, 'Y');
INSERT INTO `outpatient_info` VALUES (14, '胸外科', 2, '2024-03-07 19:17:21', '2024-03-07 19:17:23', '胸外科', 1, 'X');
INSERT INTO `outpatient_info` VALUES (15, '皮肤科', 2, '2024-03-07 19:17:36', '2024-03-07 19:17:36', '皮肤科', 1, 'P');
INSERT INTO `outpatient_info` VALUES (16, '肛肠外科', 2, '2024-03-07 19:19:01', '2024-03-07 19:19:01', '肛肠外科', 1, 'G');
INSERT INTO `outpatient_info` VALUES (17, '产科', 3, '2024-03-07 19:19:13', '2024-03-07 19:19:13', '产科', 1, 'C');
INSERT INTO `outpatient_info` VALUES (18, '备孕与早孕门诊', 3, '2024-03-07 19:19:33', '2024-03-07 19:19:34', '备孕与早孕门诊', 1, 'B');
INSERT INTO `outpatient_info` VALUES (19, '复发性流产', 3, '2024-03-07 19:19:46', '2024-03-07 19:19:47', '复发性流产', 1, 'F');
INSERT INTO `outpatient_info` VALUES (20, '妊娠期糖尿病', 3, '2024-03-07 19:20:25', '2024-03-07 19:20:26', '妊娠期糖尿病', 1, 'R');
INSERT INTO `outpatient_info` VALUES (21, '儿童内科门诊', 4, '2024-03-07 19:20:45', '2024-03-07 19:20:45', '儿童内科门诊', 1, 'E');
INSERT INTO `outpatient_info` VALUES (22, '儿童保健科', 4, '2024-03-07 19:20:57', '2024-03-07 19:20:57', '儿童保健科', 1, 'E');
INSERT INTO `outpatient_info` VALUES (23, '新生儿科', 4, '2024-03-07 19:21:09', '2024-03-07 19:21:09', '新生儿科', 1, 'X');
INSERT INTO `outpatient_info` VALUES (24, '耳鼻喉科', 5, '2024-03-07 19:21:27', '2024-03-07 19:21:28', '耳鼻喉科', 1, 'E');
INSERT INTO `outpatient_info` VALUES (25, '眼科', 5, '2024-03-07 19:21:37', '2024-03-07 19:21:38', '眼科', 1, 'Y');
INSERT INTO `outpatient_info` VALUES (26, 'PICC导管维护门诊', 6, '2024-03-07 19:21:57', '2024-03-07 19:21:57', 'PICC导管维护门诊', 1, 'P');
INSERT INTO `outpatient_info` VALUES (27, '管道护理门诊', 6, '2024-03-07 19:22:09', '2024-03-07 19:22:10', '管道护理门诊', 1, 'G');

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '专科' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of specialist_info
-- ----------------------------
INSERT INTO `specialist_info` VALUES (1, 1, '主要承担基层门急诊和综合病房工作。', '2024-03-07 18:51:54', '2024-05-14 16:56:29', '内科系统');
INSERT INTO `specialist_info` VALUES (2, 1, '外科系统', '2024-03-07 18:52:47', '2024-03-07 18:52:49', '外科系统');
INSERT INTO `specialist_info` VALUES (3, 1, '妇产科', '2024-03-07 18:52:50', '2024-03-07 18:52:51', '妇产科');
INSERT INTO `specialist_info` VALUES (4, 1, '儿科系统', '2024-03-07 18:53:03', '2024-03-07 18:53:04', '儿科系统');
INSERT INTO `specialist_info` VALUES (5, 1, '五官科', '2024-03-07 18:53:25', '2024-03-07 18:53:23', '五官科');
INSERT INTO `specialist_info` VALUES (6, 1, '护理', '2024-03-07 18:53:52', '2024-03-07 18:53:53', '护理');

SET FOREIGN_KEY_CHECKS = 1;
