package com.nueda.financial_portfolio.service;

import com.nueda.financial_portfolio.entity.User;
import com.nueda.financial_portfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.service
 * @className: UserService
 * @author: Team3
 * @description: TODO
 * @date: 2023/8/15 14:54
 * @version: 1.0
 */
@Repository
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String account) {
        return userRepository.findByAccount(account);
    }
}
