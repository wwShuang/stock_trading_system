package com.nueda.financial_portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nueda.financial_portfolio.controller", "com.nueda.financial_portfolio.service", "com.nueda.financial_portfolio.entity", "com.nueda.financial_portfolio.repository"})
public class FinancialPortfolioApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancialPortfolioApplication.class, args);
    }

}
