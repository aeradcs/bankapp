package com.databases.bankapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Currency extends Asset{
    /*@Id
    @SequenceGenerator(name = "currency_sequence", sequenceName = "currency_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_sequence")
    @Min(0)
    private Long id;*/

    private String country;
    //private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "currencies")
    private final Set<InvestmentAccount> investmentAccounts = new HashSet<>();

    /*public Long getId() {
        return id;
    }
*/

    public Currency(String name, Double cost, String country) {
        super(name, cost);
        this.country = country;
    }

    public Currency() {
    }

    public String getCountry() {
        return country;
    }



    /*public String getName() {
        return name;
    }*/

    public Set<InvestmentAccount> getInvestmentAccounts() {
        return investmentAccounts;
    }

    /*public void setId(Long id) {
        this.id = id;
    }*/

    public void setCountry(String country) {
        this.country = country;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/
}
