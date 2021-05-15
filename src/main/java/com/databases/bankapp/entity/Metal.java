package com.databases.bankapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Metal extends Asset{
    /*@Id
    @SequenceGenerator(name = "metal_sequence", sequenceName = "metal_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "metal_sequence")
    @Min(0)
    private Long id;*/


    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "metals")
    private final Set<InvestmentAccount> investmentAccounts = new HashSet<>();


    /*public Long getId() {
        return id;
    }*/

    public Metal(String name, Double cost) {
        super(name, cost);
    }

    public Metal() {
    }

   /* public String getName() {
        return name;
    }*/

    public Set<InvestmentAccount> getInvestmentAccounts() {
        return investmentAccounts;
    }


    /*public void setName(String name) {
        this.name = name;
    }*/
}

