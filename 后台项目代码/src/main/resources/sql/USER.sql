    CREATE TABLE `USER` (
      `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
      `username` varchar(40) DEFAULT NULL,
      `password` varchar(50) DEFAULT NULL,
      `name` varchar(40) DEFAULT NULL,
      `phone` varchar(50) DEFAULT NULL,
      `mail` varchar(50) DEFAULT NULL,
      `create_date` datetime,
      `modify_date` datetime,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员用户';