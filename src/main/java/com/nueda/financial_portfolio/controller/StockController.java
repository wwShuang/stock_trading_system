package com.nueda.financial_portfolio.controller;

import com.nueda.financial_portfolio.entity.DailyStock;
import com.nueda.financial_portfolio.entity.HistoryStock;
import com.nueda.financial_portfolio.entity.Stock;
import com.nueda.financial_portfolio.entity.bo.AllDailyStockDo;
import com.nueda.financial_portfolio.service.DailyStockService;
import com.nueda.financial_portfolio.service.HistoryStockService;
import com.nueda.financial_portfolio.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.controller
 * @className: StockController
 * @author: Team3
 * @description:
 * @date: 2023/8/9 9:39
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private HistoryStockService historyStockservice;

    @Autowired
    private DailyStockService dailyStockService;

    @PostMapping("/")
    public ResponseEntity<Object> add(@RequestBody Stock s){
        Stock x = stockService.add(s);
        return new ResponseEntity<Object>(x, HttpStatus.OK);
    }

    /*
     * @param :
     * @return List<Stock>
     * @author Team3
     * @description 获取所有股票信息
     * @date 2023/8/9 10:42
     */
    @GetMapping("/get")
    public ResponseEntity<List<Stock>> get() {
        List<Stock> stocks= stockService.showStock();

        if(stocks != null)
            return new ResponseEntity<List<Stock>>(stocks, HttpStatus.OK);
        else
            return new ResponseEntity<List<Stock>>(stocks, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getNumber")
    public ResponseEntity<List<Long>> getStockNumber() {
        List<Long> stocks= stockService.getStockNumber();
        if(stocks != null)
            return new ResponseEntity<List<Long>>(stocks, HttpStatus.OK);
        else
            return new ResponseEntity<List<Long>>(stocks, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{number}")
    public ResponseEntity<List<HistoryStock>>getStockHistory (@PathVariable long number){
        List<HistoryStock> s= historyStockservice.getStockHistory(number);

        if(s!=null)
            return new ResponseEntity<List<HistoryStock>>(s, HttpStatus.OK);
        else
            return new ResponseEntity<List<HistoryStock>>(s, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/currentprice/{number}")
    public ResponseEntity<Double>getStockCurrentPrice (@PathVariable long number){
        Double price = dailyStockService.getStockCurrentPrice(number);
        if(price.isNaN())
            return new ResponseEntity<Double>(price, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Double>(price, HttpStatus.OK);
    }

    /*
     * @param :
     * @return ResponseEntity<List<AllDailyStockDo>>
     *{ number: '股票代码', name: '股票名称', company: '公司名称',
     *  date: '上市日期', dailyStockPrices: '每日股票时间和价格信息‘ }
     * dailyStockPrices: { price: '价格’, date: '日期‘ }
     * @author Team3
     * @description 获取所有的每日股票信息
     * @date 2023/8/16 8:11
     */
    @GetMapping("/daily/all")
    public ResponseEntity<List<AllDailyStockDo>> getAllDailyStock() {
        List<AllDailyStockDo> allDailyStockDos= dailyStockService.getAllDailyStock();

        if(allDailyStockDos != null)
            return new ResponseEntity<List<AllDailyStockDo>>(allDailyStockDos, HttpStatus.OK);
        else
            return new ResponseEntity<List<AllDailyStockDo>>(allDailyStockDos, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/daily/{number}")
    public ResponseEntity<List<DailyStock>> getAllDailyStockByNumber(@PathVariable Long number) {
        List<DailyStock> ds= dailyStockService.getDailyStockByNumber(number);
        System.out.println(ds);

        if(ds != null)
            return new ResponseEntity<List<DailyStock>>(ds, HttpStatus.OK);
        else
            return new ResponseEntity<List<DailyStock>>(ds, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/AllStockInfo")
    public ResponseEntity<Object> getStockInfoByNumber() {
        Map<Long, Map<String , Object>> stock = stockService.getStockInfo();
        if(stock == null)
            return new ResponseEntity<Object>(stock, HttpStatus.NOT_FOUND);
        return new ResponseEntity<Object>(stock, HttpStatus.OK);
    }






}