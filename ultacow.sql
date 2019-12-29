/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MariaDB
 Source Server Version : 100320
 Source Host           : localhost:3306
 Source Schema         : suppercow

 Target Server Type    : MariaDB
 Target Server Version : 100320
 File Encoding         : 65001

 Date: 19/12/2019 01:02:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cow
-- ----------------------------
DROP TABLE IF EXISTS `cow`;
CREATE TABLE `cow`  (
  `cow_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cow_id` int(11) NOT NULL,
  `cow_gene` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cow_bd` date NULL DEFAULT NULL,
  `cust_id` int(11) NULL DEFAULT NULL,
  `rescen_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cow_id`) USING BTREE,
  INDEX `cust_id`(`cust_id`) USING BTREE,
  INDEX `rescen_id`(`rescen_id`) USING BTREE,
  CONSTRAINT `cust_id` FOREIGN KEY (`cust_id`) REFERENCES `customer` (`cust_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rescen_id` FOREIGN KEY (`rescen_id`) REFERENCES `rescen` (`rescen_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cow
-- ----------------------------
INSERT INTO `cow` VALUES ('pu_cow', 1, 'kana', '2011-07-18', 1, 66);
INSERT INTO `cow` VALUES ('supdad_cow', 2, 'chaina-japan', '2016-02-18', 1, 66);
INSERT INTO `cow` VALUES ('suppmom_cow', 3, 'chaina-japan\r\n', '2016-12-22', 1, 66);
INSERT INTO `cow` VALUES ('yay_cow', 4, 'puked', '2016-02-18', 1, 66);
INSERT INTO `cow` VALUES ('dad_cow', 5, 'japan', '2017-10-18', 1, 66);
INSERT INTO `cow` VALUES ('mom_cow', 6, 'china', '2017-12-07', 1, 66);
INSERT INTO `cow` VALUES ('luk_cow', 60, 'thai', '2019-12-17', 1, 66);
INSERT INTO `cow` VALUES ('no_cow', 99, 'no', '1946-12-19', 1, 66);

-- ----------------------------
-- Table structure for cowdetail
-- ----------------------------
DROP TABLE IF EXISTS `cowdetail`;
CREATE TABLE `cowdetail`  (
  `cow_id` int(11) NOT NULL,
  `cow_dadID` int(255) NOT NULL,
  `cow_momID` int(255) NOT NULL,
  `cust_id` int(255) NOT NULL,
  `rescen_id` int(255) NOT NULL,
  PRIMARY KEY (`cow_id`) USING BTREE,
  INDEX `cow_dadID`(`cow_dadID`) USING BTREE,
  INDEX `cow_momID`(`cow_momID`) USING BTREE,
  CONSTRAINT `cow_dadID` FOREIGN KEY (`cow_dadID`) REFERENCES `cow` (`cow_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cow_id` FOREIGN KEY (`cow_id`) REFERENCES `cow` (`cow_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cow_momID` FOREIGN KEY (`cow_momID`) REFERENCES `cow` (`cow_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cowdetail
-- ----------------------------
INSERT INTO `cowdetail` VALUES (1, 99, 99, 99, 99);
INSERT INTO `cowdetail` VALUES (2, 99, 99, 99, 99);
INSERT INTO `cowdetail` VALUES (3, 99, 99, 99, 99);
INSERT INTO `cowdetail` VALUES (4, 99, 99, 99, 99);
INSERT INTO `cowdetail` VALUES (5, 1, 4, 1, 0);
INSERT INTO `cowdetail` VALUES (6, 2, 3, 1, 0);
INSERT INTO `cowdetail` VALUES (60, 5, 6, 1, 0);
INSERT INTO `cowdetail` VALUES (99, 99, 99, 99, 99);

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `cust_id` int(11) NOT NULL,
  `cust_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cust_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `farm_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cust_id`) USING BTREE,
  INDEX `farm_id`(`farm_id`) USING BTREE,
  CONSTRAINT `farm_id` FOREIGN KEY (`farm_id`) REFERENCES `farm` (`farm_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'อดิเทพ   คำภิระ', 'อุดรธานี', 1234567891);

-- ----------------------------
-- Table structure for farm
-- ----------------------------
DROP TABLE IF EXISTS `farm`;
CREATE TABLE `farm`  (
  `farm_id` int(11) NOT NULL,
  `farm_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `farm_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`farm_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of farm
-- ----------------------------
INSERT INTO `farm` VALUES (1234567891, 'koka farm', 'udonthani');

-- ----------------------------
-- Table structure for rescen
-- ----------------------------
DROP TABLE IF EXISTS `rescen`;
CREATE TABLE `rescen`  (
  `rescen_id` int(11) NOT NULL,
  `rescen_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rescen_district` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rescen_districtID` int(11) NULL DEFAULT NULL,
  `rescen_Subdistrict` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rescen_SubdistrictID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`rescen_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rescen
-- ----------------------------
INSERT INTO `rescen` VALUES (66, 'msu-rescen', 'kantarawichai', 2, 'khamriang', 5);

-- ----------------------------
-- View structure for familycow
-- ----------------------------
DROP VIEW IF EXISTS `familycow`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `familycow` AS select cow.cow_id,cow.cow_name,cow.cow_gene,cowdetail.cow_momID,(select cow_gene from cow where  cow_id = cowdetail.cow_momID) as gene_mom ,cowdetail.cow_dadID ,(select cow_gene from cow where  cow.cow_id = cowdetail.cow_dadID) as gene_dad from cow,cowdetail where cow.cow_id =  cowdetail.cow_id ; ;

-- ----------------------------
-- View structure for supperfamilycow
-- ----------------------------
DROP VIEW IF EXISTS `supperfamilycow`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `supperfamilycow` AS select cow_id,cow_gene,cow_momID as momid,gene_mom,(select cow_momID from familycow where  cow_id = momid) as suppmom_id,(select gene_mom from familycow where  cow_id = momid) as suppmom_gene,cow_dadID as dadid,gene_dad,(select cow_dadID from familycow where  cow_id = dadid) as suppdad_id ,(select gene_dad from familycow where  cow_id = momid) as suppdad_gene from familycow ;

-- ----------------------------
-- View structure for ultacow
-- ----------------------------
DROP VIEW IF EXISTS `ultacow`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `ultacow` AS select supperfamilycow.cow_id,cow.cow_gene,cow.cow_bd,cow.cow_name,supperfamilycow.dadid as cow_dadID,supperfamilycow.momid as cow_momID ,supperfamilycow.suppmom_id as cow_supmomID ,supperfamilycow.suppdad_id as cow_supdadID,supperfamilycow.gene_dad as cow_dadgene ,supperfamilycow.gene_mom as cow_momgene,supperfamilycow.suppmom_gene as cow_supmomgene,supperfamilycow.suppdad_gene as cow_supdadgene,rescen.rescen_id,rescen.rescen_districtID,rescen.rescen_SubdistrictID,rescen.rescen_name,rescen.rescen_district,rescen.rescen_Subdistrict,farm.farm_id,farm.farm_name,farm.farm_address,customer.cust_id,customer.cust_name,customer.cust_address from supperfamilycow,cow,rescen,farm,customer WHERE cow.cow_id = supperfamilycow.cow_id and cow.rescen_id = rescen.rescen_id and  cow.cust_id = customer.cust_id and  customer.farm_id = farm.farm_id ;

SET FOREIGN_KEY_CHECKS = 1;
