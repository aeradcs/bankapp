package com.databases.bankapp.repository;


import com.databases.bankapp.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findById(long id);


}
