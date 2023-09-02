package com.nueda.financial_portfolio.service;

import com.nueda.financial_portfolio.entity.HistoryStock;
import com.nueda.financial_portfolio.entity.Stock;
import com.nueda.financial_portfolio.repository.HistoryStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.service
 * @className: HistoryStockService
 * @author: Team3
 * @description:
 * @date: 2023/8/9 9:36
 * @version: 1.0
 */
@Service
public class HistoryStockService {

    @Autowired
    private HistoryStockRepository historyStockRepository;

    /**
     *
    * */
    public HistoryStock add(HistoryStock s) { return historyStockRepository.save(s);}
    public  List<HistoryStock> getStockHistory(Long number){
//        System.out.println(historyStockRepository.findById(id));
            return historyStockRepository.findByNumber(number);

    }

}
