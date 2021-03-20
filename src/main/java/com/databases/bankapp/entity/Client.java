package com.databases.bankapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;

@Entity

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String fullName;
    Calendar dateOfBirth;
    String gender;
    String jobStatus;
    String phoneNumber;

    protected Client(){}

    public Client(String fullName, Calendar dateOfBirth, String gender, String jobStatus, String phoneNumber)
    {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.jobStatus = jobStatus;
        this.phoneNumber = phoneNumber;

    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Calendar getDateOfBirth() {
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

    @Override
    public String toString()
    {
        return String.format("Client[id = %d fullname = %s]");
    }
}
