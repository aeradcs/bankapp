package com.databases.bankapp.repository;

import com.databases.bankapp.entity.Metal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetalRepository extends JpaRepository<Metal, Long> {


    @Query("select t from Metal t " +
            "where lower(t.name) like lower(concat('%', :searchTerm, '%'))")
    List<Metal> search(@Param("searchTerm") String searchTerm);
}
