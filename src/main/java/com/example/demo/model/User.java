package com.example.demo.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="username")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="enabled")
    private Boolean enabled;

    @Transient
    private String matchingPassword;


//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "username")
//    private List<Authorities> authorities;

    public String getPassword() {
        return password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

//    public List<Authorities> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(List<Authorities> authorities) {
//        this.authorities = authorities;
//    }

    //    public List<Orders> getOrdersList() {
//        return ordersList;
//    }
//
//    public void setOrdersList(List<Orders> ordersList) {
//        this.ordersList = ordersList;
//    }
}
