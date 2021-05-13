package com.databases.bankapp.repository;

import com.databases.bankapp.entity.InvestmentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvestmentAccountRepository extends JpaRepository<InvestmentAccount, Long> {
    @Query("select t from InvestmentAccount t " +
            "where lower(t.id) like lower(concat('%', :searchTerm, '%'))")
    List<InvestmentAccount> search(@Param("searchTerm") String searchTerm);
}
