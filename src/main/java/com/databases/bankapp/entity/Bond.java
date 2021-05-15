package com.databases.bankapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bond extends Asset   {


    private String country;
    private Double percentPerYear;
    private Integer amountOfYears;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "bonds")
    private final Set<InvestmentAccount> investmentAccounts = new HashSet<>();

    public Bond(String country, String name, Double cost, Double percentPerYear, Integer amountOfYears) {
        super(name, cost);
        this.country = country;
        this.percentPerYear = percentPerYear;
        this.amountOfYears = amountOfYears;
    }

    public Bond(){

    }


    public String getCountry() {
        return country;
    }


    public Double getPercentPerYear() {
        return percentPerYear;
    }

    public Integer getAmountOfYears() {
        return amountOfYears;
    }

    public Set<InvestmentAccount> getInvestmentAccounts() {
        return investmentAccounts;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public void setPercentPerYear(Double percentPerYear) {
        this.percentPerYear = percentPerYear;
    }

    public void setAmountOfYears(Integer amountOfYears) {
        this.amountOfYears = amountOfYears;
    }


    @Override
    public String toString() {
        return "Bond{" +
                "country='" + country + '\'' +
                ", percentPerYear=" + percentPerYear +
                ", amountOfYears=" + amountOfYears +
                '}';
    }
}
