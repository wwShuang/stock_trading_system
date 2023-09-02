package com.nueda.financial_portfolio.repository;

import com.nueda.financial_portfolio.entity.UserStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.repository
 * @interfaceName: UserStockRepository
 * @author: Team3
 * @description:
 * @date: 2023/8/9 9:32
 * @version: 1.0
 */
@Repository
public interface UserStockRepository extends JpaRepository<UserStock, Integer> {

    Optional<UserStock> findByNumber(Long number);



}
