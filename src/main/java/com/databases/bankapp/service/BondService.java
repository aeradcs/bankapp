package com.databases.bankapp.service;

import com.databases.bankapp.entity.Bond;
import com.databases.bankapp.repository.BondRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BondService {
    private final BondRepository repository;

    public BondService(BondRepository v) {
        this.repository = v;
    }

    /*public List<Share> findAll() {
        return shareRepository.findAll();
    }
*/
    public List<Bond> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.search(stringFilter);
        }
    }

    public long count() {
        return repository.count();
    }

    public void delete(Bond v) {
        repository.delete(v);
    }

    public void save(Bond v) {
        if (v == null) {
            System.out.println("NULL BOND WHILE SAVE");
            return;
        }
        repository.save(v);
    }
}
