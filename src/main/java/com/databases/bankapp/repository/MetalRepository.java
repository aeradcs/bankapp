package com.databases.bankapp.repository;

import com.databases.bankapp.entity.Metal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetalRepository extends JpaRepository<Metal, Long> {
}
