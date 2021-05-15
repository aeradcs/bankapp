package com.databases.bankapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bond {
    @Id
    @SequenceGenerator(name = "bond_sequence", sequenceName = "bond_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bond_sequence")
    @Min(0)
    private Long id;

    private String country;
    private String name;
    private Double percentPerYear;
    private Integer amountOfYears;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "bonds")
    private final Set<InvestmentAccount> investmentAccounts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPercentPerYear(Double percentPerYear) {
        this.percentPerYear = percentPerYear;
    }

    public void setAmountOfYears(Integer amountOfYears) {
        this.amountOfYears = amountOfYears;
    }
}
