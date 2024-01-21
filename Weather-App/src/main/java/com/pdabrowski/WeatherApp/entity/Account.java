package com.pdabrowski.WeatherApp.entity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import javax.management.relation.Role;
import java.util.Set;


@Entity
@Table(name = "accounts")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "accountId")
public class Account {

    @Id
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "pw")
    private String pwVarchar;

    @Column(name = "active")
    private String active;

    @OneToMany(mappedBy = "account")
    private Set<com.pdabrowski.WeatherApp.entity.Role> roles;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private User users;

    // Assuming you're using bidirectional relationships
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Employee employee;


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

//    public String getId() {
//        return email;
//    }
//
//    public void setId(String id) {
//        this.email = id;
//    }
//
//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public String getPasswordHash() {
//        return passwordHash;
//    }
//
//    public void setPasswordHash(String passwordHash) {
//        this.passwordHash = passwordHash;
//    }
//
//    public AccountType getAccountType() {
//        return accountType;
//    }
//
//    public void setAccountType(AccountType accountType) {
//        this.accountType = accountType;
//    }
}

