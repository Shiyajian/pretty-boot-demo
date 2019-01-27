CREATE database pretty_enum;
use pretty_enum;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `gender` int(11) NOT NULL,
  `status` int(11) DEFAULT '0',
  `age` int(11) NOT NULL,
  `description` varchar(255) DEFAULT '',
  `birthday` date NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `created_time` datetime  DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
