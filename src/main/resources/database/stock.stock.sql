-- 股票基本信息表：
create table `stock`
(
    `id`               int       	  auto_increment not null comment '编号'
        primary key,
    `number`           Long       	  not null comment '股票代码',
    `name`             varchar(255)   not null comment '股票名称',
    `company`          varchar(255)   not null comment '公司名称',
    `date`             timestamp(0)   not null default  CURRENT_TIMESTAMP(0) comment '上市日期'
);