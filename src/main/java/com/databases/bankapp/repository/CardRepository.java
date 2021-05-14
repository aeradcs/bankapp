package com.databases.bankapp.repository;

import com.databases.bankapp.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
