package com.nueda.financial_portfolio.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.entity
 * @className: UserStock
 * @author: Team3
 * @description: 用户股票信息
 * @date: 2023/8/9 9:28
 * @version: 1.0
 */
@Entity
@Table(name = "user_stock")
public class UserStock {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /*
     * 股票代码
     */
    private Long number;

    public UserStock() {
    }

    public UserStock(Long number, int count) {
        this.number = number;
        this.count = count;
    }

    /*
     * 股票数量
     */
    private int count;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
