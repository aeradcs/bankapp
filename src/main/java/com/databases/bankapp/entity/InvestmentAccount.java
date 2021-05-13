package com.databases.bankapp.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class InvestmentAccount {
    @Id
    @SequenceGenerator(name = "investment_account_sequence", sequenceName = "investment_account_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "investment_account_sequence")
    private Long id;

    private LocalDate dateOfOpening;
    private Double moneySum;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "investment_account_share",
            joinColumns = { @JoinColumn(name = "id_invest_account") },
            inverseJoinColumns = { @JoinColumn(name = "id_share") }
    )
    Set<Share> shares = new HashSet<>();

    public InvestmentAccount() {
    }

    public InvestmentAccount(Client client, LocalDate dateOfOpening, Double moneySum) {
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

    public Double getMoneySum() {
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

    public void setMoneySum(Double moneySum) {
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

