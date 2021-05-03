package com.databases.bankapp.service;

import com.databases.bankapp.entity.Client;
import com.databases.bankapp.entity.InvestmentAccount;
import com.databases.bankapp.repository.ClientRepository;
import com.databases.bankapp.repository.InvestmentAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentAccountService {
    private InvestmentAccountRepository investmentAccountRepository;
    public InvestmentAccountService(InvestmentAccountRepository investmentAccountRepository) {
        this.investmentAccountRepository = investmentAccountRepository;
    }
    public List<InvestmentAccount> findAll() {
        return investmentAccountRepository.findAll();
    }

    public long count() {
        return investmentAccountRepository.count();
    }

    public void delete(InvestmentAccount investmentAccount) {
        investmentAccountRepository.delete(investmentAccount);
    }

    public void save(InvestmentAccount investmentAccount) {
        if (investmentAccount == null) {
            System.out.println("NULL CLIENT WHILE SAVE");
            return;
        }
        investmentAccountRepository.save(investmentAccount);
    }
}
