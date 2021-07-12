package com.example.demo.dao;

import com.example.demo.model.Orders;

public interface OrderDAO {

    public Orders findById(int id);
    public void save( Orders orders);
    public Orders findByUserName(String userName );
    public void deleteById( int id );
}
