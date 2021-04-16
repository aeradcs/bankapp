package com.databases.bankapp.entity;

import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@Column(name = "full_name", nullable = false)
    String fullName;

    GregorianCalendar dateOfBirth;
    String gender;
    String jobStatus;
    String phoneNumber;

    public enum GenderEnum{мужской, женский};
    public enum JobStatusEnum{работает, безработный};
    protected Client(){}

    public Client(String fullName, GregorianCalendar dateOfBirth, String gender, String jobStatus, String phoneNumber)
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

    public String getDateOfBirth() {
        return String.valueOf(dateOfBirth.get(Calendar.DAY_OF_MONTH)) + "." +
                String.valueOf(dateOfBirth.get(Calendar.MONTH) + 1) + "." +
                String.valueOf(dateOfBirth.get(Calendar.YEAR));
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
        return String.format(" id = %d fullname = %s ", this.id, this.fullName);
    }
}
