-- 每日股票信息：
create table `daily_stock`
(
    `id`               int       	  auto_increment not null comment '编号'
        primary key,
    `number`           Long       	  not null comment '股票代码',
    `date`             timestamp(0)   not null default  CURRENT_TIMESTAMP(0) comment '日期',
    `price`    		   double         not null comment '价格'
);