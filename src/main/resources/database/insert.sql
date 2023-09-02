INSERT INTO `user` (`account`, `balance`)
VALUES ('ueser1', 10000000);

INSERT INTO `daily_stock` (`number`, `date`, `price`)
VALUES (10001, NOW(), 100.0),
       (10001, DATE_ADD(NOW(), INTERVAL -1 HOUR), 101.0),
       (10001, DATE_ADD(NOW(), INTERVAL -2 HOUR), 102.0);

INSERT INTO `daily_stock` (`number`, `date`, `price`)
VALUES (10002, NOW(), 110.0),
       (10002, DATE_ADD(NOW(), INTERVAL -1 HOUR), 111.0),
       (10002, DATE_ADD(NOW(), INTERVAL -2 HOUR), 112.0);

INSERT INTO `daily_stock` (`number`, `date`, `price`)
VALUES (10003, NOW(), 140.0),
       (10003, DATE_ADD(NOW(), INTERVAL -1 HOUR), 121.0),
       (10003, DATE_ADD(NOW(), INTERVAL -2 HOUR), 102.0);


INSERT INTO `user_stock` (`number`, `count`)
VALUES (10001, 100);  -- 假设用户持有100股的股票1

-- 用户持有股票2
INSERT INTO `user_stock` (`number`, `count`)
VALUES (10002, 50);   -- 假设用户持有50股的股票2

-- 用户持有股票3
INSERT INTO `user_stock` (`number`, `count`)
VALUES (10003, 200);  -- 假设用户持有200股的股票3

INSERT INTO `stock` (`number`, `name`, `company`, `date`)
VALUES
    (10001, '股票A', '公司A', '2022-01-01 00:00:00'),
    (10002, '股票B', '公司B', '2022-01-02 00:00:00'),
    (10003, '股票C', '公司C', '2022-01-03 00:00:00');

INSERT INTO `stock_order` (`number`, `date`, `price`, `count`, `state`)
VALUES
    (10001, '2022-02-01 10:30:00', 50.0, 100, 0),  -- 买入100股股票10001，单价50.0
    (10001, '2022-02-05 11:00:00', 55.0, 50, 1),   -- 卖出50股股票10001，单价55.0
    (10002, '2022-02-02 14:15:00', 150.0, 200, 0), -- 买入200股股票10002，单价150.0
    (10002, '2022-02-06 15:30:00', 145.0, 100, 1), -- 卖出100股股票10002，单价145.0
    (10003, '2022-02-03 09:45:00', 75.0, 150, 0),  -- 买入150股股票10003，单价75.0
    (10003, '2022-02-07 10:15:00', 80.0, 50, 1);   -- 卖出50股股票10003，单价80.0


