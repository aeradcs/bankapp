package com.databases.bankapp.repository;


import com.databases.bankapp.entity.Card;
import com.databases.bankapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c " +
            "where lower(c.fullName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.fullName) like lower(concat('%', :searchTerm, '%'))")
    List<Client> search(@Param("searchTerm") String searchTerm);


    @Query(value =
            """
            select * from client c   
            join investment_account i          
            on c.id = i.client_id
            """, nativeQuery = true)
    List<Client> getClientsWhoHasInvestAcc();





}
