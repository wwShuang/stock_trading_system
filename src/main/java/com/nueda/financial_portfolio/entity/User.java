package com.nueda.financial_portfolio.entity;

import jakarta.persistence.*;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.entity
 * @className: User
 * @author: Team3
 * @description: 用户信息
 * @date: 2023/8/15 14:32
 * @version: 1.0
 */
@Entity
@Table(name = "user")
public class User {
    public User() {
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /*
     * 用户账号
     */
    private String account;

    /*
     * 用户余额
     */
    private double balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
