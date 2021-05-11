package com.databases.bankapp.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class InvestmentAccount {
    @Id
    @SequenceGenerator(name = "investment_account_sequence", sequenceName = "investment_account_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "investment_account_sequence")
    private Long id;

    private LocalDate dateOfOpening;
    private String moneySum;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public InvestmentAccount() {
    }

    public InvestmentAccount(Long id, Client client, LocalDate dateOfOpening, String moneySum) {
        this.id = id;
        this.client = client;
        this.dateOfOpening = dateOfOpening;
        this.moneySum = moneySum;
    }


    public Long getId() {
        return id;
    }

    public LocalDate getDateOfOpening() {
        return dateOfOpening;
    }

    public String getMoneySum() {
        return moneySum;
    }

    public Client getClient() {
        return client;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateOfOpening(LocalDate dateOfOpening) {
        this.dateOfOpening = dateOfOpening;
    }

    public void setMoneySum(String moneySum) {
        this.moneySum = moneySum;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "InvestmentAccount{" +
                "id=" + id +
                ", dateOfOpening=" + dateOfOpening +
                ", moneySum='" + moneySum + '\'' +
                ", client=" + client +
                '}';
    }
}

