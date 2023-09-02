package com.nueda.financial_portfolio.service;

import com.nueda.financial_portfolio.entity.Stock;
import com.nueda.financial_portfolio.entity.StockOrder;
import com.nueda.financial_portfolio.entity.UserStock;
import com.nueda.financial_portfolio.repository.DailyStockRepository;
import com.nueda.financial_portfolio.repository.StockOrderRepository;
import com.nueda.financial_portfolio.repository.StockRepository;
import com.nueda.financial_portfolio.repository.UserStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.service
 * @className: UserStockService
 * @author: Team3
 * @description:
 * @date: 2023/8/9 9:37
 * @version: 1.0
 */
@Service
public class UserStockService {

    @Autowired
    private UserStockRepository userStockRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockOrderRepository stockOrderRepository;

    @Autowired
    private DailyStockRepository dailyStockRepository;

    /*
     * @param :
     * @return List<UserStock>
     * @author Team3
     * @description
     * @date 2023/8/15 0:14
     */
    public List<UserStock> getAll() {
        return userStockRepository.findAll();
    }

    /*
    用户拥有股票详细信息：
    返回类型Map<Long,Map<String , Object>> , 其中 Long 数据表示股票number，内层嵌套的Map为每只股票的各个属性和对应的值
    分别为：“name”，“company”，“purchaseDate”，"currentPrice","count",“profit”
     */
    public Map<Long,Map<String , Object>> getDetailAll(){
        Map<Long, Map<String, Object>> stocksData = new HashMap<>();
        List<UserStock> all_stock = userStockRepository.findAll();
        for (UserStock userStock : all_stock) {
            Map<String,Object> s = new HashMap<>();
            Long number = userStock.getNumber();
            int count = userStock.getCount();
            Stock stock = stockRepository.findByNumber(number);
            String name = stock.getName();
            String company = stock.getCompany();
            List<StockOrder> orders = stockOrderRepository.findAllByNumber(number);
            Date purchaseDate = stockOrderRepository.findTopByNumberOrderByDateAsc(number).getDate();
            double currentPrice = dailyStockRepository.findTopByNumberOrderByDateDesc(number).getPrice();
            double profit = currentPrice * count;
            for (StockOrder order : orders) {
                if (order.getState()== 0){ // 买入
                    profit -= order.getCount() * order.getPrice();
                }
                else{
                    profit += order.getCount() * order.getPrice();
                }
            }
            s.put("number", number);
            s.put("name",name);
            s.put("company",company);
            s.put("purchaseDate",purchaseDate);
            s.put("profit",profit);
            s.put("count",count);
            s.put("currentPrice",currentPrice);
            stocksData.put(number,s);
        }
    return stocksData;
    }

}
