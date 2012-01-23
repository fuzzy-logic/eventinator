package com.netaporter.eventinator.test.customer.builder;

import com.netaporter.eventinator.test.customer.model.Customer;

import java.io.Serializable;

/**
 * User: gawain
 */
public class CustomerBuilder implements Builder<Customer> {

    Serializable id;
    String name;
    String email;
    String password;
    String dob;
    boolean deleted = false;
    private int version;

    public static CustomerBuilder create(String name, String password, String email) {
        CustomerBuilder builder = new CustomerBuilder();
        builder.name = name;
        builder.email = email;
        builder.password = password;
        builder.version = 0;
        return builder;
    }

    public static CustomerBuilder create(Customer customer) {
        CustomerBuilder builder = new CustomerBuilder();
        builder.id = customer.getId();
        builder.name = customer.getName();
        builder.email = customer.getEmailAddress();
        builder.password = customer.getPassword();
        builder.version = customer.getVersion();
        builder.deleted = false;
         builder.dob = customer.getDob();
        return builder;
    }

    public CustomerBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder setVersion(int version) {
        this.version = version;
         return this;
    }

    public CustomerBuilder setDeleted(boolean isDeleted) {
        this.deleted = isDeleted;
        return this;
    }

    public CustomerBuilder setDob(String dob) {
        this.dob = dob;
        return this;
    }


    public Serializable getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDob() {
        return dob;
    }

     public boolean getDeleted() {
        return deleted;
    }

    @Override
    public Customer build() {
        return new Customer(this);
    }

    public int getVersion() {
        return version;
    }

}
