-- 用户表：
create table `user_stock`
(
    `id`               int       	  auto_increment not null comment '编号'
        primary key,
    `number`           Long       	  not null comment '股票代码',
	`count`            int       	  not null comment '股票数量'
);