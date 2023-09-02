package com.nueda.financial_portfolio.service;

import com.nueda.financial_portfolio.entity.StockOrder;
import com.nueda.financial_portfolio.entity.User;
import com.nueda.financial_portfolio.entity.UserStock;
import com.nueda.financial_portfolio.repository.DailyStockRepository;
import com.nueda.financial_portfolio.repository.StockOrderRepository;
import com.nueda.financial_portfolio.repository.UserRepository;
import com.nueda.financial_portfolio.repository.UserStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.service
 * @className: StockOrderService
 * @author: Team3
 * @description: TODO
 * @date: 2023/8/15 14:53
 * @version: 1.0
 */
@Service
public class StockOrderService {

    @Autowired
    private StockOrderRepository stockOrderRepository;

    @Autowired
    private DailyStockRepository dailyStockRepository;

    @Autowired UserRepository userRepository;

    @Autowired
    private UserStockRepository userStockRepository;

    /*
    用户购买股票：
    1.确认余额（checkBalance），若余额充足则直接扣除余额，否则提示失败。（或者直接修改成在页面显示最多可购买数量）
    2.购买股票（buyStock），增添股票交易信息、修改用户持有股票信息(先判断用户是否持有该股票以进行后续操作)。
     */

    public boolean checkBalance(Long number,int count) {
        User u = userRepository.findTopByOrderByIdAsc();
        double balance = u.getBalance();
        double price = dailyStockRepository.findTopByNumberOrderByDateDesc(number).getPrice();
        double total_price = price * count;
        if (balance > total_price) {
            u.setBalance(balance - total_price);
            userRepository.save(u);
            return true;
        }
        else {
            return false;
        }
    }
    public StockOrder buyStock(Long number,int count){
        double price = dailyStockRepository.findTopByNumberOrderByDateDesc(number).getPrice();
        Date date = new Date();
        StockOrder order = new StockOrder(number,date,price,count,0);
        stockOrderRepository.save(order);

        Optional <UserStock> usOpt= userStockRepository.findByNumber(number);
        UserStock us;
        if (usOpt.isPresent()) {
            us = usOpt.get();
            us.setCount(us.getCount() + count);
        } else {
            us = new UserStock(number,count);
        }
        userStockRepository.save(us);

        return order;
    }

    /*
    用户贩售股票：
    1.股票的持有量应大于抛售的数量（前端判断）
    2.买股票（buyStock），修改用户余额信息、修改用户持有股票信息、增添股票交易信息。
     */

    public StockOrder sellStock(Long number,int count){
        double price = dailyStockRepository.findTopByNumberOrderByDateDesc(number).getPrice();
        User u = userRepository.findTopByOrderByIdAsc();
        u.setBalance(u.getBalance() + price * count);
        userRepository.save(u);

        Date date = new Date();
        StockOrder order = new StockOrder(number,date,price,count,1);
        stockOrderRepository.save(order);

        UserStock us = userStockRepository.findByNumber(number).get();
        if ((us.getCount() - count)>0){
            us.setCount(us.getCount() - count);
            userStockRepository.save(us);
        }
        else{//对于已经全部抛售的股票则在user_stock表中删除
            userStockRepository.delete(us);
        }

        return order;


    }

    public Map<Integer,Map<String , Object>> getStockByNumber(Long number) {
        Map<Integer, Map<String, Object>> stocksData = new HashMap<>();
        List<StockOrder> stockOrders = stockOrderRepository.findAllByNumber(number);
        int i = 0;
        for(StockOrder stockOrder: stockOrders) {
            Map<String,Object> s = new HashMap<>();
            s.put("date",stockOrder.getDate());
            s.put("count",stockOrder.getCount());
            s.put("price",stockOrder.getPrice());
            s.put("state",stockOrder.getState());
            s.put("currentPrice",stockOrder.getPrice() * stockOrder.getCount());
            stocksData.put(i++,s);
        }
        return stocksData;
    }

}



