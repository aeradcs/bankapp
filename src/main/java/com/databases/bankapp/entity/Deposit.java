package com.databases.bankapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
public class Deposit {
    @Id
    @SequenceGenerator(name = "deposit_sequence", sequenceName = "deposit_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deposit_sequence")
    @Min(0)
    private Long id;

    private LocalDate dateOfOpening;
    private LocalDate dateOfEnding;
    private Double moneySum;
    private Double percentPerYear;
    private Integer isReplenish;
    private Integer isWithdraw;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Deposit() {
    }

    public Deposit(LocalDate dateOfOpening, LocalDate dateOfEnding, Double moneySum, Double percentPerYear, Integer isReplenish, Integer isWithdraw, Client client) {
        this.dateOfOpening = dateOfOpening;
        this.dateOfEnding = dateOfEnding;
        this.moneySum = moneySum;
        this.percentPerYear = percentPerYear;
        this.isReplenish = isReplenish;
        this.isWithdraw = isWithdraw;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateOfOpening() {
        return dateOfOpening;
    }

    public LocalDate getDateOfEnding() {
        return dateOfEnding;
    }

    public Double getMoneySum() {
        return moneySum;
    }

    public Double getPercentPerYear() {
        return percentPerYear;
    }

    public Integer getIsReplenish() {
        return isReplenish;
    }

    public Integer getIsWithdraw() {
        return isWithdraw;
    }

    public Client getClient() {
        return client;
    }

    public void setDateOfOpening(LocalDate dateOfOpening) {
        this.dateOfOpening = dateOfOpening;
    }

    public void setDateOfEnding(LocalDate dateOfEnding) {
        this.dateOfEnding = dateOfEnding;
    }

    public void setMoneySum(Double moneySum) {
        this.moneySum = moneySum;
    }

    public void setPercentPerYear(Double percentPerYear) {
        this.percentPerYear = percentPerYear;
    }

    public void setIsReplenish(Integer isReplenish) {
        this.isReplenish = isReplenish;
    }

    public void setIsWithdraw(Integer isWithdraw) {
        this.isWithdraw = isWithdraw;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", dateOfOpening=" + dateOfOpening +
                ", dateOfEnding=" + dateOfEnding +
                ", moneySum=" + moneySum +
                ", percentPerYear=" + percentPerYear +
                ", isReplenish=" + isReplenish +
                ", isWithdraw=" + isWithdraw +
                ", client=" + client +
                '}';
    }
}
