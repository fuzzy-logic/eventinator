package com.netaporter.eventinator.test.customer.model;


import com.netaporter.eventinator.domain.DomainId;
import com.netaporter.eventinator.domain.DomainVersion;
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

    @Id @DomainId
    protected final Serializable id;
    @DomainVersion
    protected final int version;
    private final String emailAddress;
    private final String name;
    private final int age;
    private final String password;
    private final String  dob;

    public Customer() {
        this.id = null;
        this.emailAddress = null;
        this.name = null;
        this.age = 0;
        this.password = null;
        this.dob = null;
        this.version = -1;
    }



    public Customer(CustomerBuilder builder) {
       this.id = builder.getId();
       this.emailAddress = builder.getEmail();
       this.name = builder.getName();
       this.dob = builder.getDob();
       this.password = builder.getPassword();
       this.age = 0;
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

    public Serializable getId() {
        return id;
    }
}