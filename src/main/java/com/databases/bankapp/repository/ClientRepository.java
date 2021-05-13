package com.databases.bankapp.repository;


import com.databases.bankapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c " +
            "where lower(c.fullName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.fullName) like lower(concat('%', :searchTerm, '%'))")
    List<Client> search(@Param("searchTerm") String searchTerm);





}
