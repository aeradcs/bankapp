package com.databases.bankapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Asset {
    @Id
    @SequenceGenerator(name = "asset_sequence", sequenceName = "asset_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_sequence")
    @Min(0)
    private Long id;

    private String name;
    private Double cost;

    /*@ManyToMany(fetch = FetchType.EAGER, mappedBy = "assets")
    private final Set<InvestmentAccount> investmentAccounts = new HashSet<>();*/

   /* public Set<InvestmentAccount> getInvestmentAccounts() {
        return investmentAccounts;
    }*/

    public Asset(String name, Double cost) {
        this.name = name;
        this.cost = cost;
    }

    public Asset(){

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
