package com.databases.bankapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
public class Share {
    @Id
    @SequenceGenerator(name = "share_sequence", sequenceName = "share_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "share_sequence")
    @Min(0)
    private Long id;

    private String country;
    private String nameOfCompany;
    private Integer capitalization;
    private String stock;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "shares")
    private final Set<InvestmentAccount> investmentAccounts = new HashSet<>();



    /*public Set<InvestmentAccount> getInvestmentAccounts() {
        return new HashSet<InvestmentAccount>(investmentAccounts);
    }*/

    /*public void setInvestmentAccounts(Set<InvestmentAccount> investmentAccounts) {
        if(investmentAccounts != null){
            investmentAccounts.forEach(el->{
                el.setShares((Set<Share>) this);
            });
        }
        this.investmentAccounts = investmentAccounts;

    }*/

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public Integer getCapitalization() {
        return capitalization;
    }

    public String getStock() {
        return stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public void setCapitalization(Integer capitalization) {
        this.capitalization = capitalization;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Share{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", nameOfCompany='" + nameOfCompany + '\'' +
                ", capitalization=" + capitalization +
                ", stock='" + stock + '\'' +
                ", investAccounts=" + investmentAccounts +
                '}';
    }
}
