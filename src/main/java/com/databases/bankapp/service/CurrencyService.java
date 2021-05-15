package com.databases.bankapp.service;

import com.databases.bankapp.entity.Currency;
import com.databases.bankapp.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {
    private final CurrencyRepository repository;

    public CurrencyService(CurrencyRepository v) {
        this.repository = v;
    }

    /*public List<Share> findAll() {
        return shareRepository.findAll();
    }
*/
    public List<Currency> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.search(stringFilter);
        }
    }

    public long count() {
        return repository.count();
    }

    public void delete(Currency v) {
        repository.delete(v);
    }

    public void save(Currency v) {
        if (v == null) {
            System.out.println("NULL Currency WHILE SAVE");
            return;
        }
        repository.save(v);
    }
}
