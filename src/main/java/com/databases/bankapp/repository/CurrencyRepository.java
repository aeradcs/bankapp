package com.databases.bankapp.repository;

import com.databases.bankapp.entity.Card;
import com.databases.bankapp.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Query("select t from Currency t " +
            "where lower(t.name) like lower(concat('%', :searchTerm, '%'))")
    List<Currency> search(@Param("searchTerm") String searchTerm);
}
