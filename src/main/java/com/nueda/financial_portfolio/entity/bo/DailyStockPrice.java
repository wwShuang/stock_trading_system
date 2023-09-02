package com.nueda.financial_portfolio.entity.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.entity.bo
 * @className: DailyStockPrice
 * @author: Team3
 * @description: TODO
 * @date: 2023/8/16 7:36
 * @version: 1.0
 */
public class DailyStockPrice {
    private double price;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date date;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DailyStockPrice(double price, Date date) {
        this.price = price;
        this.date = date;
    }
}
