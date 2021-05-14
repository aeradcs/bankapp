package com.databases.bankapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Client {
    @Id
    @SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @Min(0)
    private Long id;

    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String jobStatus;
    private String phoneNumber;

    @OneToMany( fetch = FetchType.EAGER, mappedBy = "client"/*,
    cascade = CascadeType.ALL, orphanRemoval = true*/)
    private Set<InvestmentAccount> investmentAccounts = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private Set<Deposit> deposits = new HashSet<>();

    public Client() {
    }

    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(Set<Deposit> deposits) {
        if(deposits != null){
            deposits.forEach(el->{
                el.setClient(this);
            });
        }
        this.deposits = deposits;
    }

    public Client(String fullName, LocalDate dateOfBirth, String gender, String jobStatus, String phoneNumber, Set<InvestmentAccount> investmentAccounts, Set<Deposit> deposits) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.jobStatus = jobStatus;
        this.phoneNumber = phoneNumber;
        this.investmentAccounts = investmentAccounts;
        this.deposits = deposits;
    }

    public Set<InvestmentAccount> getInvestmentAccounts() {
        return investmentAccounts;
    }

    public void setInvestmentAccounts(Set<InvestmentAccount> investmentAccounts) {
        if(investmentAccounts != null){
            investmentAccounts.forEach(el->{
                el.setClient(this);
            });
        }
        this.investmentAccounts = investmentAccounts;

    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return String.format("%d ", this.id);
    }



    public String getIdStr(){
        return id.toString();
    }

}
