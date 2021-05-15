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
            "where lower(t.name) like lower(concat('%', :searchTerm, '%'))")
    List<Share> search(@Param("searchTerm") String searchTerm);

    @Query(value =
            "select s.id, a.name, a.cost, s.country, s.capitalization, s.stock from Share s join Asset a on s.id=a.id where s.stock = :param", nativeQuery = true)
    List<Object[]> getShareByStock(@Param("param") String param);


    @Query(value =
            "select s.id, a.name, a.cost, s.country, s.capitalization, s.stock from Share s join Asset a on s.id=a.id where s.country = :param", nativeQuery = true)
    List<Object[]> getShareByCountry(@Param("param") String param);

    @Query(value =
            "select s.id, a.name, a.cost, s.country, s.capitalization, s.stock from Share s join Asset a on s.id=a.id where a.name = :param", nativeQuery = true)
    List<Object[]> getShareByNameOfCompany(@Param("param") String param);


    @Query(value =
            "select s.id, a.name, a.cost, s.country, s.capitalization, s.stock from Share s join Asset a on s.id=a.id where (s.capitalization >= :param1 and s.capitalization <= :param2)", nativeQuery = true)
    List<Object[]> getShareByCapitalizationBetween(@Param("param1") Integer param1, @Param("param2") Integer param2);
}
