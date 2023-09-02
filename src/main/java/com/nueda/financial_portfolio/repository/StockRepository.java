package com.nueda.financial_portfolio.repository;

import com.nueda.financial_portfolio.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.repository
 * @interfaceName: StockRepository
 * @author: Team3
 * @description:
 * @date: 2023/8/9 9:32
 * @version: 1.0
 */
@Repository
public interface StockRepository extends JpaRepository<Stock,Integer> {

    @Query("select s.number from Stock s")
    List<Long> findAllNumber();

    Stock findByNumber(Long number);
}
