package com.databases.bankapp.repository;

import com.databases.bankapp.entity.InvestmentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentAccountRepository extends JpaRepository<InvestmentAccount, Long> {
    @Query("select t from InvestmentAccount t " +
            "where lower(t.id) like lower(concat('%', :searchTerm, '%'))")
    List<InvestmentAccount> search(@Param("searchTerm") String searchTerm);


    @Query(value =
            """
            
            select c.id as client_id, c.full_name, count(i.client_id) as count
            from client c
            join investment_account i          
            on
            c.id = i.client_id
            group by c.id
            having (count(i.client_id) >= :param1 and count(i.client_id) <= :param2)
            order by count asc
            """, nativeQuery = true)
    List<Object[]> getInvAccCountBetweenForEveryClientWhoHasItAsc(@Param("param1") Integer param1, @Param("param2") Integer param2);

    @Query(value =
            """
            
            select c.id as client_id, c.full_name, count(i.client_id) as count
            from client c
            join investment_account i          
            on
            c.id = i.client_id
            group by c.id
            having (count(i.client_id) >= :param1 and count(i.client_id) <= :param2)
            order by count desc
            """, nativeQuery = true)
    List<Object[]> getInvAccCountBetweenForEveryClientWhoHasItDesc(@Param("param1") Integer param1, @Param("param2") Integer param2);
}
