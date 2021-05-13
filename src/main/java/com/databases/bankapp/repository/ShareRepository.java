package com.databases.bankapp.repository;

import com.databases.bankapp.entity.Client;
import com.databases.bankapp.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {
    @Query("select t from Share t " +
            "where lower(t.nameOfCompany) like lower(concat('%', :searchTerm, '%'))")
    List<Share> search(@Param("searchTerm") String searchTerm);





}
