package com.nueda.financial_portfolio.repository;

import com.nueda.financial_portfolio.entity.HistoryStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.repository
 * @interfaceName: HistoryStockRepository
 * @author: Team3
 * @description:
 * @date: 2023/8/9 9:33
 * @version: 1.0
 */
@Repository
public interface HistoryStockRepository extends JpaRepository<HistoryStock, Integer> {
    List<HistoryStock> findByNumber(Long number);
}
