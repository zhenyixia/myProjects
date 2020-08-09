CREATE TABLE `auth_user` (
  `id`          BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(30) NOT NULL
  COMMENT '用户名',
  `password`    VARCHAR(50) NOT NULL,
  `phone`       VARCHAR(20) NULL     DEFAULT NULL,
  `role`        VARCHAR(10) NULL     DEFAULT NULL,
  `modify_time` DATETIME    NULL     DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name` (`name`)
)
  COLLATE = 'utf8_general_ci'
  ENGINE = InnoDB;

-- 插入超级管理员
INSERT INTO auth_user (name, password, phone, role, modify_time) VALUES ('lyp', 'lyp', '1234567890', 'admin', now());
