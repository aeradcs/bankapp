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

    public List<Client> sortByNameDesc(){
        return clientRepository.sortByNameDecs();
    }

    public List<Client> sortByNameAsc(){
        return clientRepository.sortByNameAsc();
    }

    public List<Client> sortByGenderDesc(){
        return clientRepository.sortByGenderDecs();
    }

    public List<Client> sortByGenderAsc(){
        return clientRepository.sortByGenderAcs();
    }

    public List<Client> sortByDateDesc(){
        return clientRepository.sortByDateDecs();
    }

    public List<Client> sortByDateAsc(){
        return clientRepository.sortByDateAsc();
    }

    public List<Client> sortByJobDesc(){
        return clientRepository.sortByJobDecs();
    }

    public List<Client> sortByJobAsc(){
        return clientRepository.sortByJobAcs();
    }

    public List<Client> sortByPhoneDesc(){
        return clientRepository.sortByPhoneDecs();
    }

    public List<Client> sortByPhoneAsc(){
        return clientRepository.sortByPhoneAcs();
    }

    public List<Object[]> getClientsWhoHasntDepositAndHasntCard(){
        return clientRepository.getClientsWhoHasntDepositAndHasntCard();
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

    public List<Object[]> getClientsWhoHasInvestAcc(){
        return clientRepository.getClientsWhoHasInvestAcc();
    }

    public List<Object[]> getClientsWhoHasInvestAccAndSumBetween(Double param1, Double param2){
        return clientRepository.getClientsWhoHasInvestAccAndSumBetween(param1, param2);
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
