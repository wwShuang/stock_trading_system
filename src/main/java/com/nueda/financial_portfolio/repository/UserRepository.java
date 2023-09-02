package com.nueda.financial_portfolio.repository;

import com.nueda.financial_portfolio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.repository
 * @interfaceName: UserRepository
 * @author: Team3
 * @description:
 * @date: 2023/8/15 14:50
 * @version: 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findTopByOrderByIdAsc();


    User findByAccount(String account);
}
