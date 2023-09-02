create table `user`
(
	`id`                int       	      auto_increment not null comment '编号'
        primary key,
    `account`           varchar(255)      not null comment '用户账号',
	`balance`           double       	  not null comment '用户余额'
);