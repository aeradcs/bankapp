package com.databases.bankapp.repository;

import com.databases.bankapp.entity.Client;
import com.databases.bankapp.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<Share, Long> {
}
