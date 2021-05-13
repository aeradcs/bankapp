package com.databases.bankapp.service;

import com.databases.bankapp.entity.InvestmentAccount;
import com.databases.bankapp.entity.Share;
import com.databases.bankapp.repository.InvestmentAccountRepository;
import com.databases.bankapp.repository.ShareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareService {
    private ShareRepository shareRepository;
    public ShareService(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }
    public List<Share> findAll() {
        return shareRepository.findAll();
    }

    public long count() {
        return shareRepository.count();
    }

    public void delete(Share share) {
        shareRepository.delete(share);
    }

    public void save(Share share) {
        if (share == null) {
            System.out.println("NULL INVEST ACCOUNT WHILE SAVE");
            return;
        }
        shareRepository.save(share);
    }
}
