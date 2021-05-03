package com.databases.bankapp.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "investment_account")
public class InvestmentAccount {
    @Id
    @SequenceGenerator(name = "investment_account_sequence", sequenceName = "investment_account_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "investment_account_sequence")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Client client;

    private LocalDate dateOfOpening;
    private Double moneySum;

    public InvestmentAccount() {
    }

    public InvestmentAccount(LocalDate dateOfOpening, Double moneySum) {
        this.dateOfOpening = dateOfOpening;
        this.moneySum = moneySum;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDateOfOpening() {
        return dateOfOpening;
    }

    public void setDateOfOpening(LocalDate dateOfOpening) {
        this.dateOfOpening = dateOfOpening;
    }

    public Double getMoneySum() {
        return moneySum;
    }

    public void setMoneySum(Double moneySum) {
        this.moneySum = moneySum;
    }
}

