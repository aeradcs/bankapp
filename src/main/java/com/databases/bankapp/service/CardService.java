package com.databases.bankapp.service;

import com.databases.bankapp.entity.Card;
import com.databases.bankapp.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    private CardRepository cardRepository;
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    public long count() {
        return cardRepository.count();
    }

    public void delete(Card card) {
        cardRepository.delete(card);
    }

    public void save(Card card) {
        if (card == null) {
            System.out.println("NULL CARD WHILE SAVE");
            return;
        }
        cardRepository.save(card);
    }
}
