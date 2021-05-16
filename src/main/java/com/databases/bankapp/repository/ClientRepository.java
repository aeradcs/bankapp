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
            select c.id as client_id, c.full_name, i.id as acc_id, i.money_sum from client c   
            join investment_account i          
            on c.id = i.client_id
            """, nativeQuery = true)
    List<Object[]> getClientsWhoHasInvestAcc();

    @Query(value =
            """
            select * from (
                    select c.id as client_id, c.full_name, i.id as acc_id, i.money_sum 
                    from client c
                    join investment_account i          
                    on
                    c.id = i.client_id
            ) new
            where (money_sum >= :param1 and money_sum <= :param2)
            """, nativeQuery = true)

    List<Object[]> getClientsWhoHasInvestAccAndSumBetween(@Param("param1") Double param1, @Param("param2") Double param2);



    @Query(value =
            """
            
                    
            select acc_id, currency_id, c_name 
            from(
                    select i.id as acc_id, c.id as currency_id, a.name as c_name
                    from investment_account i
                    
                    full outer join investment_account_currency ic
                    on i.id=ic.id_invest_account
                    full outer join currency c
                    on c.id = ic.id_currency
                    left join asset a
                    on
                    c.id = a.id
                    order by i.id) main
                    
            where acc_id not in(
                                select i.id as acc_id
                                from investment_account i
                                join investment_account_currency ic
                                on i.id=ic.id_invest_account
                                join currency c
                                on c.id = ic.id_currency
                                ) 
            or currency_id not in (
                                select c.id as currency_id
                                from investment_account i
                                join investment_account_currency ic
                                on i.id=ic.id_invest_account
                                join currency c
                                on c.id = ic.id_currency
                                ) 
                    
            
            """, nativeQuery = true)
    List<Object[]> getClientsWhoHasntDepositAndHasntCard();


    @Query(value =
            "select * from Client a order by a.full_name desc", nativeQuery = true)
    List<Client> sortByNameDecs();

    @Query(value =
            "select * from Client a order by a.full_name asc", nativeQuery = true)
    List<Client> sortByNameAsc();

    @Query(value =
            "select * from Client a order by a.gender desc", nativeQuery = true)
    List<Client> sortByGenderDecs();

    @Query(value =
            "select * from Client a order by a.gender asc", nativeQuery = true)
    List<Client> sortByGenderAcs();

    @Query(value =
            "select * from Client a order by a.date_of_birth desc", nativeQuery = true)
    List<Client> sortByDateDecs();

    @Query(value =
            "select * from Client a order by a.date_of_birth asc", nativeQuery = true)
    List<Client> sortByDateAsc();

    @Query(value =
            "select * from Client a order by a.job_status desc", nativeQuery = true)
    List<Client> sortByJobDecs();

    @Query(value =
            "select * from Client a order by a.job_status asc", nativeQuery = true)
    List<Client> sortByJobAcs();

    @Query(value =
            "select * from Client a order by a.phone_number desc", nativeQuery = true)
    List<Client> sortByPhoneDecs();

    @Query(value =
            "select * from Client a order by a.phone_number asc", nativeQuery = true)
    List<Client> sortByPhoneAcs();
}
