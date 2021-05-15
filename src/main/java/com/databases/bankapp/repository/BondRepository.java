package com.databases.bankapp.repository;

import com.databases.bankapp.entity.Bond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BondRepository extends JpaRepository<Bond, Long> {

    @Query("select t from Bond t " +
            "where lower(t.name) like lower(concat('%', :searchTerm, '%'))")
    List<Bond> search(@Param("searchTerm") String searchTerm);
}
