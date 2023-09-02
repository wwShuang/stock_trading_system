package com.nueda.financial_portfolio.repository;

import com.nueda.financial_portfolio.entity.StockOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.repository
 * @interfaceName: StockOrderRepository
 * @author: Team3
 * @description: TODO
 * @date: 2023/8/15 14:52
 * @version: 1.0
 */
@Repository
public interface StockOrderRepository extends JpaRepository<StockOrder, Integer> {
    List<StockOrder> findAllByNumber(Long number);
    StockOrder findTopByNumberOrderByDateAsc(Long number);
}
