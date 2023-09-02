package com.nueda.financial_portfolio.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.entity
 * @className: DailyStock
 * @author: Team3
 * @description: 每日股票信息
 * @date: 2023/8/9 9:30
 * @version: 1.0
 */
@Entity
@Table(name = "daily_stock")
public class DailyStock {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /*
     * 股票代码
     */
    private Long number;
    /*
     * 日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date date;
    /*
     * 价格
     */
    private double price;

    public DailyStock() {

    }

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
}
