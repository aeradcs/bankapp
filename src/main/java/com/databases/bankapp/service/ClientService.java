package com.databases.bankapp.service;

import com.databases.bankapp.entity.Client;
import com.databases.bankapp.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public List<Client> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return clientRepository.findAll();
        } else {
            return clientRepository.search(stringFilter);
        }
    }

    public long count() {
        return clientRepository.count();
    }

    public void delete(Client client) {
        clientRepository.delete(client);
    }

    public void save(Client client) {
        if (client == null) {
            System.out.println("NULL CLIENT WHILE SAVE");
            return;
        }
        clientRepository.save(client);
    }
}
