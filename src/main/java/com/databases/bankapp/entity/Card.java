package com.databases.bankapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
public class Card {
    @Id
    @SequenceGenerator(name = "card_sequence", sequenceName = "card_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_sequence")
    @Min(0)
    private Long id;

    private LocalDate expiryDate;
    private Double moneySum;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Card(Long id, LocalDate expiryDate, Double moneySum, Client client) {
        this.id = id;
        this.expiryDate = expiryDate;
        this.moneySum = moneySum;
        this.client = client;
    }

    public Card() {

    }

    public Long getId() {
        return id;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public Double getMoneySum() {
        return moneySum;
    }

    public Client getClient() {
        return client;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setMoneySum(Double moneySum) {
        this.moneySum = moneySum;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", expiry_date=" + expiryDate +
                ", money_sum=" + moneySum +
                '}';
    }

}
