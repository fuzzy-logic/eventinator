package com.netaporter.eventinator.test.customer.model;


import com.netaporter.eventinator.test.customer.builder.CustomerBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;

/**
 * User: Chris Wright [chris.wright@net-a-porter.com]
 * Date: 05/01/12
 * Time: 16:48
 */
public class Customer {


    @Indexed(unique = true)
     @Id
    protected Serializable id;
    protected int version;
    protected boolean deleted = false;
    private final String emailAddress;
    private final String name;
    private final int age;
    private final String password;
    private final String  dob;

    public Customer() {
        this.emailAddress = null;
        this.name = null;
        this.age = 0;
        this.password = null;
        this.dob = null;
    }

    public Customer(String emailAddress, String name, int age) {

        this.emailAddress = emailAddress;
        this.name = name;
        this.age = age;
        this.dob = null;
        this.password = null;
        this.deleted = false;
        this.version = 0;
    }

    public Customer(CustomerBuilder builder) {
        this.id = builder.getId();
       this.emailAddress = builder.getEmail();
        this.name = builder.getName();
        this.dob = builder.getDob();
        this.password = builder.getPassword();
        this.age = 0;
        this.deleted = builder.getDeleted();
        this.version = builder.getVersion();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getDob() {
        return dob;
    }

    public int getVersion() {
        return version;
    }

    public boolean isDeleted() {
        return deleted;
    }


    public Serializable getId() {
        return id;
    }
}