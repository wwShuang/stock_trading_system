create table `stock_order`
(
	`id`               int       	  auto_increment not null comment '交易编号'
        primary key,
    `number`           Long       	  not null comment '股票代码',
    `date`             timestamp(0)   not null default  CURRENT_TIMESTAMP(0) comment '股票交易日期',
    `price`    		   double         not null comment '股票交易价格/每股',
    `count`            int            not null comment '股票交易数量',
    `state`            int            not null comment '买入  0
                                                        卖出  1'
);
