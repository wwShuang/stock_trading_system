package com.nueda.financial_portfolio.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @projectName: Stock
 * @package: com.nueda.financial_portfolio.entity
 * @className: Stock
 * @author: team3
 * @description: 股票信息
 * @date: 2023/8/8 20:56
 * @version: 1.0
 */
@Entity
@Table(name = "stock")
public class Stock {
    public Stock() {
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
}

