package com.databases.bankapp.service;

import com.databases.bankapp.entity.Deposit;
import com.databases.bankapp.repository.DepositRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositService {
    private final DepositRepository depositRepository;

    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public List<Deposit> findAll() {
        return depositRepository.findAll();
    }

    /*public List<Deposit> findAll(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return depositRepository.findAll();
        } else {
            return depositRepository.search(filterText);
        }
    }*/

    public long count() {
        return depositRepository.count();
    }

    public void delete(Deposit deposit) {
        depositRepository.delete(deposit);
    }

    public void save(Deposit deposit) {
        if (deposit == null) {
            System.out.println("NULL DEPOSIT WHILE SAVE");
            return;
        }
        depositRepository.save(deposit);
    }
}
