package com.nueda.financial_portfolio.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.entity
 * @className: HistoryStock
 * @author: Team3
 * @description: 历史股票信息
 * @date: 2023/8/9 9:29
 * @version: 1.0
 */
@Entity
@Table(name = "history_stock")
public class HistoryStock {
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
     * 最高价
     */
    private double highestPrice;
    /*
     * 最低价
     */
    private double lowestPrice;
    /*
     * 开盘价
     */
    private double openingPrice;
    /*
     * 涨跌幅
     */
    private double amplitude;
    /*
     * 交易量
     */
    private double tradingVolume;

    public HistoryStock() {
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

    public double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public double getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(double openingPrice) {
        this.openingPrice = openingPrice;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getTradingVolume() {
        return tradingVolume;
    }

    public void setTradingVolume(double tradingVolume) {
        this.tradingVolume = tradingVolume;
    }
}
