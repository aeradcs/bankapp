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

    @Query(value =
            "select * from Share a where a.stock = :param", nativeQuery = true)
    List<Share> getShareByStock(@Param("param") String param);

    @Query(value =
            "select * from Share a where a.country = :param", nativeQuery = true)
    List<Share> getShareByCountry(@Param("param") String param);

    @Query(value =
            "select * from Share a where a.name_of_company = :param", nativeQuery = true)
    List<Share> getShareByNameOfCompany(@Param("param") String param);

    @Query(value =
            "select * from Share a where a.capitalization <= :param", nativeQuery = true)
    List<Share> getShareByCapitalizationLess(@Param("param") Integer param);


    @Query(value =
            "select * from Share a where a.capitalization >= :param", nativeQuery = true)
    List<Share> getShareByCapitalizationMore(@Param("param") Integer param);
}
