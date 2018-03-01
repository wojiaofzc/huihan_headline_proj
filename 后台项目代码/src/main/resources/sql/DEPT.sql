    CREATE TABLE `DEPT` (
      `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
      `deptname` varchar(40) DEFAULT NULL,
      `parentId` int(20) DEFAULT NULL,
      `corpId` int(20) FOREIGN KEY CORP(`corpID`),
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门';