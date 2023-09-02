package com.nueda.financial_portfolio.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.entity
 * @className: StockOrder
 * @author: Team3
 * @description: 交易信息
 * @date: 2023/8/15 14:41
 * @version: 1.0
 */
@Entity
@Table(name = "stock_order")
public class StockOrder {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public StockOrder() {

    }
    /*
     * 股票代码
     */
    private Long number;
    /*
     * 股票交易日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date date;
    /*
     * 股票交易价格/每股
     */
    private double price;
    /*
     * 股票交易数量
     */
    private int count;

    public StockOrder(Long number, Date date, double price, int count, int state) {

        this.number = number;
        this.date = date;
        this.price = price;
        this.count = count;
        this.state = state;
    }

    /*
     * 买入  0
     * 卖出  1
     */
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
