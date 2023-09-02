-- 历史股票信息：
create table `history_stock`
(
    `id`               int       	  auto_increment not null comment '编号'
        primary key,
    `number`           Long       	  not null comment '股票代码',
    `date`             timestamp(0)   not null default  CURRENT_TIMESTAMP(0) comment '日期',
    `highest_price`    double         not null comment '最高价',
    `lowest_price`     double         not null comment '最低价',
    `opening_price`    double         not null comment '开盘价',
    `amplitude`        double         not null comment '涨跌幅',
    `trading_volume`   double         not null comment '交易量'
);
-- 开启事件调度器
set global event_scheduler = ON;

create event event_stu_info
on schedule
	EVERY 1 DAY STARTS '2023-08-15 00:00:00'
	comment '每1天将daily中的值插入到history，并删除daily数据'
-- 	测试
-- 	every 1 minute
-- 	ends now() + interval 5 minute
-- 	comment '每分钟插入一条记录，间隔 5 分钟'
do
	begin
	   insert into history_stock (`number`, `date`, highest_price, lowest_price, opening_price, amplitude, trading_volume)
             select d.`number`, min(d.`date`) as `date`, max(d.price) as highest_price, min(d.price) as lowest_price,
	   		     (  select ds.price
	          		from daily_stock ds
	            	where ds.`number` =  d.`number` order by ds.`date` limit 1 ) as opening_price,
	          		if((max(d.price) = min(d.price)), max(d.price), (max(d.price) - min(d.price))) as amplitude,
	          	( 	select coalesce(sum(so.price),0)
	          		from stock_order so
	            	where so.`number` = d.`number`) as trading_volume
	   		 from daily_stock d group by d.`number`;
	   truncate table stock.daily_stock;
	end