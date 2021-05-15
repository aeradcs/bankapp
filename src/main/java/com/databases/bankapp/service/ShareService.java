package com.databases.bankapp.service;

import com.databases.bankapp.entity.InvestmentAccount;
import com.databases.bankapp.entity.Share;
import com.databases.bankapp.repository.InvestmentAccountRepository;
import com.databases.bankapp.repository.ShareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareService {
    private final ShareRepository shareRepository;

    public ShareService(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    /*public List<Share> findAll() {
        return shareRepository.findAll();
    }
*/
    public List<Share> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return shareRepository.findAll();
        } else {
            return shareRepository.search(stringFilter);
        }
    }

    public List<Object[]> getShareByStock(String param){
        return  shareRepository.getShareByStock(param);
    }

    public List<Object[]> getShareByCountry(String param){
        return  shareRepository.getShareByCountry(param);
    }

    public List<Object[]> getShareByNameOfCompany(String param){
        return  shareRepository.getShareByNameOfCompany(param);
    }


    public List<Object[]> getShareByCapitalizationBetween(Integer param1, Integer param2){
        return  shareRepository.getShareByCapitalizationBetween(param1, param2);
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
