package com.databases.bankapp.repository;

import com.databases.bankapp.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

    @Query(value =
            """
            
            select c.id as client_id, c.full_name, count(d.client_id) as count
            from client c
            join deposit d          
            on
            c.id = d.client_id
            group by c.id
            """, nativeQuery = true)
    List<Object[]> getDepositCountForEveryClientWhoHasIt();

}
