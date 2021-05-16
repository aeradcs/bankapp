package com.databases.bankapp.repository;

import com.databases.bankapp.entity.Card;
import com.databases.bankapp.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value =
            "select * from Card a where (a.money_sum >= :param1 and a.money_sum <= :param2) order by a.money_sum asc", nativeQuery = true)
    List<Card> getCardByMoneySumBetweenAsc(@Param("param1") Integer param1, @Param("param2") Integer param2);

    @Query(value =
            "select * from Card a where (a.money_sum >= :param1 and a.money_sum <= :param2) order by a.money_sum desc", nativeQuery = true)
    List<Card> getCardByMoneySumBetweenDesc(@Param("param1") Integer param1, @Param("param2") Integer param2);

}
