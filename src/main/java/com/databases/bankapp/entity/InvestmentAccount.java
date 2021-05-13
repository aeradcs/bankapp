package com.databases.bankapp.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
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

    @ManyToMany(cascade = {/*CascadeType.PERSIST, CascadeType.DETACH*/CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "investment_account_share",
            joinColumns = { @JoinColumn(name = "id_invest_account") },
            inverseJoinColumns = { @JoinColumn(name = "id_share") }
    )
    Set<Share> shares = new HashSet<>();


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

    public Set<Share> getShares() {
        return shares;
    }
    /*public HashSet<Share> getShares(){
        return new HashSet<>(shares);
    }*/
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

    public void setShares(Set<Share> shares) {
        this.shares = shares;
    }
    /*public void setShares(Share shares) {
        this.shares = (Set<Share>) shares;
    }*/

    public String getSharesStr() {
        StringBuilder str = new StringBuilder();
        Iterator<Share> it = shares.iterator();
        while (it.hasNext()) {
            str.append(it.next().getNameOfCompany());
            if (it.hasNext()){
                str.append(", ");
            }
        }
        return str.toString();
    }

    @Override
    public String toString() {
        return "InvestmentAccount{" +
                "id=" + id +
                ", dateOfOpening=" + dateOfOpening +
                ", moneySum=" + moneySum +
                ", client=" + client +
                ", shares=" + shares +
                '}';
    }
}

