package com.nueda.financial_portfolio.entity.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.entity.bo
 * @className: AllDailyStockDo
 * @author: Team3
 * @description: TODO
 * @date: 2023/8/16 7:44
 * @version: 1.0
 */
public class AllDailyStockDo {
    /*
     * 股票代码
     */
    private Long number;
    /*
     * 股票名称
     */
    private String name;
    /*
     * 公司名称
     */
    private String company;
    /*
     * 上市日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date date;

    private List<DailyStockPrice> dailyStockPrices;

    public AllDailyStockDo(Long number, String name, String company, Date date, List<DailyStockPrice> dailyStockPrices) {
        this.number = number;
        this.name = name;
        this.company = company;
        this.date = date;
        this.dailyStockPrices = dailyStockPrices;
    }

    public AllDailyStockDo(Long number, String name, String company, Date date) {
        this.number = number;
        this.name = name;
        this.company = company;
        this.date = date;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<DailyStockPrice> getDailyStockPrices() {
        return dailyStockPrices;
    }

    public void setDailyStockPrices(List<DailyStockPrice> dailyStockPrices) {
        this.dailyStockPrices = dailyStockPrices;
    }
}
