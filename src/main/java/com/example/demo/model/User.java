package com.example.demo.model;


import javax.persistence.*;
import java.util.List;

@Embeddable
@Table(name="users")
public class User {

    @Column(name="username")
    private String userName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private List<Orders> ordersList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }
}
