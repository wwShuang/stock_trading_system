package com.nueda.financial_portfolio.service;

import com.nueda.financial_portfolio.entity.Stock;
import com.nueda.financial_portfolio.entity.StockOrder;
import com.nueda.financial_portfolio.entity.UserStock;
import com.nueda.financial_portfolio.repository.DailyStockRepository;
import com.nueda.financial_portfolio.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.service
 * @className: StockService
 * @author: Team3
 * @description:
 * @date: 2023/8/9 9:37
 * @version: 1.0
 */
@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private DailyStockRepository dailyStockRepository;


    public Stock add(Stock s) {
        return stockRepository.save(s);
    }

    /*
     * @param :
     * @return null
     * @author Team3
     * @description 测试
     * @date 2023/8/9 10:39
     */
    public List<Stock> showStock() {
        System.out.println(stockRepository.findById(1));
        return stockRepository.findAll();
    }

    public List<Long> getStockNumber() {
        return stockRepository.findAllNumber();
    }

    /*
    股票详细信息：
    返回类型Map<Long,Map<String , Object>> , 其中 Long 数据表示股票number，内层嵌套的Map为每只股票的各个属性和对应的值
    分别为：“name”，“company”，"currentPrice"
     */
    public Map<Long, Map<String , Object>> getStockInfo(){
        Map<Long, Map<String, Object>> stocksData = new HashMap<>();
        List <Stock> stocks = stockRepository.findAll();
        for (Stock stock : stocks){
            Map<String,Object> s = new HashMap<>();
            Long number = stock.getNumber();
            String name = stock.getName();
            String company = stock.getCompany();
            double currentPrice = dailyStockRepository.findTopByNumberOrderByDateDesc(number).getPrice();

            s.put("number", number);
            s.put("name",name);
            s.put("company",company);
            s.put("currentPrice",currentPrice);
            stocksData.put(number,s);
        }

        return stocksData;
    }

}

