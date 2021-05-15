package com.databases.bankapp.service;

import com.databases.bankapp.entity.Metal;
import com.databases.bankapp.repository.MetalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetalService {
    private final MetalRepository repository;

    public MetalService(MetalRepository v) {
        this.repository = v;
    }

    /*public List<Share> findAll() {
        return shareRepository.findAll();
    }
*/
    public List<Metal> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.search(stringFilter);
        }
    }

    public long count() {
        return repository.count();
    }

    public void delete(Metal v) {
        repository.delete(v);
    }

    public void save(Metal v) {
        if (v == null) {
            System.out.println("NULL Metal WHILE SAVE");
            return;
        }
        repository.save(v);
    }
}
