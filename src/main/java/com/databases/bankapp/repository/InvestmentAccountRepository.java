package com.databases.bankapp.repository;

import com.databases.bankapp.entity.InvestmentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentAccountRepository extends JpaRepository<InvestmentAccount, Long> {
}
