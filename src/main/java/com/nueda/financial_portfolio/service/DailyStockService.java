package com.nueda.financial_portfolio.service;

import com.nueda.financial_portfolio.entity.DailyStock;
import com.nueda.financial_portfolio.entity.HistoryStock;
import com.nueda.financial_portfolio.entity.Stock;
import com.nueda.financial_portfolio.entity.bo.AllDailyStockDo;
import com.nueda.financial_portfolio.entity.bo.DailyStockPrice;
import com.nueda.financial_portfolio.repository.DailyStockRepository;
import com.nueda.financial_portfolio.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.service
 * @className: DailyStockService
 * @author: Team3
 * @description:
 * @date: 2023/8/9 9:35
 * @version: 1.0
 */
@Service
public class DailyStockService {

    @Autowired
    private DailyStockRepository dailyStockRepository;

    @Autowired
    private StockRepository stockRepository;

    public double getStockCurrentPrice(long number) {
        return dailyStockRepository.findTopByNumberOrderByDateDesc(number).getPrice();
    }

    public List<AllDailyStockDo> getAllDailyStock() {
        List<AllDailyStockDo> allDailyStockDos = new ArrayList<>();
        List<Long> stocks = stockRepository.findAllNumber();
        for (Long stock : stocks) {
            List<DailyStockPrice> dailyStockPrices = dailyStockRepository.findDailyStockPriceByNumber(stock);
            Stock s = stockRepository.findByNumber(stock);
            System.out.println(s);
            if (dailyStockPrices == null || dailyStockPrices.isEmpty()) {
                AllDailyStockDo allDailyStockDo = new AllDailyStockDo(stock,
                        s.getName(), s.getCompany(), s.getDate());
            }
            AllDailyStockDo allDailyStockDo = new AllDailyStockDo(stock,
                    s.getName(), s.getCompany(), s.getDate(), dailyStockPrices);
            allDailyStockDos.add(allDailyStockDo);
        }
        return allDailyStockDos;
    }

    public List<DailyStock> getDailyStockByNumber(Long number) {
        Optional<List<DailyStock>> optDs = dailyStockRepository.findAllByNumberOrderByDateAsc(number);
        if (optDs.isPresent()) {
            return optDs.get();
        }
        else return  null;


    }



}
