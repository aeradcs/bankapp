package com.databases.bankapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
public class InvestmentAccount {
    @Id
    @SequenceGenerator(name = "investment_account_sequence", sequenceName = "investment_account_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "investment_account_sequence")
    @Min(0)
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

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "investment_account_currency",
            joinColumns = { @JoinColumn(name = "id_invest_account") },
            inverseJoinColumns = { @JoinColumn(name = "id_currency") }
    )
    Set<Share> currencies = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "investment_account_bond",
            joinColumns = { @JoinColumn(name = "id_invest_account") },
            inverseJoinColumns = { @JoinColumn(name = "id_bond") }
    )
    Set<Share> bonds = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "investment_account_metal",
            joinColumns = { @JoinColumn(name = "id_invest_account") },
            inverseJoinColumns = { @JoinColumn(name = "id_metal") }
    )
    Set<Share> metals = new HashSet<>();

    public Set<Share> getMetals() {
        return metals;
    }

    public void setMetals(Set<Share> metals) {
        this.metals = metals;
    }

    public Set<Share> getCurrencies() {
        return currencies;
    }

    public Set<Share> getBonds() {
        return bonds;
    }

    public void setCurrencies(Set<Share> currencies) {
        this.currencies = currencies;
    }

    public void setBonds(Set<Share> bonds) {
        this.bonds = bonds;
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

    public String getCurrenciesStr() {
        StringBuilder str = new StringBuilder();
        Iterator<Share> it = currencies.iterator();
        while (it.hasNext()) {
            str.append(it.next().getNameOfCompany());
            if (it.hasNext()){
                str.append(", ");
            }
        }
        return str.toString();
    }

    public String getBondsStr() {
        StringBuilder str = new StringBuilder();
        Iterator<Share> it = bonds.iterator();
        while (it.hasNext()) {
            str.append(it.next().getNameOfCompany());
            if (it.hasNext()){
                str.append(", ");
            }
        }
        return str.toString();
    }

    public String getMetalsStr() {
        StringBuilder str = new StringBuilder();
        Iterator<Share> it = metals.iterator();
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
                ", currencies=" + currencies +
                ", bonds=" + bonds +
                ", metals=" + metals +
                '}';
    }
}

