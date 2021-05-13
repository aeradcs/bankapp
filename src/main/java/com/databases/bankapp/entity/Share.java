package com.databases.bankapp.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Share {
    @Id
    @SequenceGenerator(name = "share_sequence", sequenceName = "share_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "share_sequence")
    private Long id;

    private String country;
    private String nameOfCompany;
    private Integer capitalization;
    private String stock;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "shares")
    private final Set<InvestmentAccount> investAccounts = new HashSet<>();



}
