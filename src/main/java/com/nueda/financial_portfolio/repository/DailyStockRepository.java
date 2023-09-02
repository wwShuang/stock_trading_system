package com.nueda.financial_portfolio.repository;

import com.nueda.financial_portfolio.entity.DailyStock;
import com.nueda.financial_portfolio.entity.UserStock;
import com.nueda.financial_portfolio.entity.bo.DailyStockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @projectName: nueda_training
 * @package: com.nueda.financial_portfolio.repository
 * @interfaceName: DailyStockRepository
 * @author: Team3
 * @description:
 * @date: 2023/8/9 9:34
 * @version: 1.0
 */
@Repository
public interface DailyStockRepository extends JpaRepository<DailyStock, Integer> {
    DailyStock findTopByNumberOrderByDateDesc(Long number);

    @Query("select new com.nueda.financial_portfolio.entity.bo.DailyStockPrice( d.price, d.date) " +
            "from DailyStock d where d.number = ?1 order by d.date desc")
    List<DailyStockPrice> findDailyStockPriceByNumber(Long number);

    Optional<List<DailyStock>> findAllByNumberOrderByDateAsc(Long number);

    @Query("select d.price " +
            "from DailyStock d where d.number = ?1 and d.date <= ?2 order by d.date desc limit 1")
    double findDailyStockCurrentPrice(Long number, Date date);
}
