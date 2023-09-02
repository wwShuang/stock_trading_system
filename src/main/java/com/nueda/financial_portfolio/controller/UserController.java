package com.nueda.financial_portfolio.controller;

import com.nueda.financial_portfolio.entity.*;
import com.nueda.financial_portfolio.service.StockOrderService;
import com.nueda.financial_portfolio.service.StockService;
import com.nueda.financial_portfolio.service.UserService;
import com.nueda.financial_portfolio.service.UserStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.controller
 * @className: UserController
 * @author: Team3
 * @description:
 * @date: 2023/8/9 9:39
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserStockService userStockService;

    @Autowired
    private UserService userService;

    @Autowired
    private StockOrderService stockOrderService;


    @GetMapping("/all")
    public ResponseEntity<List<UserStock>> getAll() {
        List<UserStock> userStocks= userStockService.getAll();

        if(userStocks != null)
            return new ResponseEntity<List<UserStock>>(userStocks, HttpStatus.OK);
        else
            return new ResponseEntity<List<UserStock>>(userStocks, HttpStatus.NOT_FOUND);
    }


    @PutMapping("/buy/{number}_{count}")
    public ResponseEntity<Object> buyStock(@PathVariable Long number,@PathVariable int count){
        if (stockOrderService.checkBalance(number,count)){
            StockOrder so = stockOrderService.buyStock(number,count);
            return new ResponseEntity<Object>(so, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Object>("Balance Not Enough!",HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/sell/{number}_{count}")
    public ResponseEntity<Object> sellStock(@PathVariable Long number,@PathVariable int count){
        StockOrder so = stockOrderService.sellStock(number,count);
        return new ResponseEntity<Object>(so, HttpStatus.OK);
    }

    @GetMapping("/allDetail")
    public ResponseEntity<Object> getAllDetail(){
        Map<Long, Map<String , Object>> stocks = userStockService.getDetailAll();
        return new ResponseEntity<Object>(stocks, HttpStatus.OK);
    }

    @GetMapping("/detail/{number}")
    public ResponseEntity<Object> getStockDetail(@PathVariable Long number){
        Map<Integer, Map<String , Object>> stockOrders = stockOrderService.getStockByNumber(number);
        if(stockOrders == null)
            return new ResponseEntity<Object>(stockOrders, HttpStatus.NOT_FOUND);
        return new ResponseEntity<Object>(stockOrders, HttpStatus.OK);
    }

    @GetMapping("/account/{account}")
    public ResponseEntity<Object> getUserDetail(@PathVariable String account){
        User user = userService.getUser(account);
        if(user == null)
            return new ResponseEntity<Object>(user, HttpStatus.NOT_FOUND);
        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }

}
