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
    Set<Currency> currencies = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "investment_account_bond",
            joinColumns = { @JoinColumn(name = "id_invest_account") },
            inverseJoinColumns = { @JoinColumn(name = "id_bond") }
    )
    Set<Bond> bonds = new HashSet<>();

    /*@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "investment_account_bond",
            joinColumns = { @JoinColumn(name = "id_invest_account") },
            inverseJoinColumns = { @JoinColumn(name = "id_bond") }
    )
    Set<Asset> assets = new HashSet<>();*/

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "investment_account_metal",
            joinColumns = { @JoinColumn(name = "id_invest_account") },
            inverseJoinColumns = { @JoinColumn(name = "id_metal") }
    )
    Set<Metal> metals = new HashSet<>();


    public Set<Bond> getBonds() {
        return bonds;
    }

    public void setBonds(Set<Bond> bonds) {
        this.bonds = bonds;
    }

    public Set<Metal> getMetals() {
        return metals;
    }

    public void setMetals(Set<Metal> metals) {
        this.metals = metals;
    }

    public Set<Currency> getCurrencies() {
        return currencies;
    }

    /*public Set<Asset> getBonds() {
        return assets;
    }
*/
    public void setCurrencies(Set<Currency> currencies) {
        this.currencies = currencies;
    }

    /*public void setBonds(Set<Asset> bonds) {
        this.assets = bonds;
    }*/

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
            str.append(it.next().getName());
            if (it.hasNext()){
                str.append(", ");
            }
        }
        return str.toString();
    }

    public String getCurrenciesStr() {
        StringBuilder str = new StringBuilder();
        Iterator<Currency> it = currencies.iterator();
        while (it.hasNext()) {
            str.append(it.next().getName());
            if (it.hasNext()){
                str.append(", ");
            }
        }
        return str.toString();
    }

    public String getBondsStr() {
        StringBuilder str = new StringBuilder();
        Iterator<Bond> it = bonds.iterator();
        while (it.hasNext()) {
            str.append(it.next().getName());
            if (it.hasNext()){
                str.append(", ");
            }
        }
        return str.toString();
    }

    public String getMetalsStr() {
        StringBuilder str = new StringBuilder();
        Iterator<Metal> it = metals.iterator();
        while (it.hasNext()) {
            str.append(it.next().getName());
            if (it.hasNext()){
                str.append(", ");
            }
        }
        return str.toString();
    }



}

