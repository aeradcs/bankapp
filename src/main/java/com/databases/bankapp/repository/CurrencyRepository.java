package com.databases.bankapp.repository;

import com.databases.bankapp.entity.Card;
import com.databases.bankapp.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
