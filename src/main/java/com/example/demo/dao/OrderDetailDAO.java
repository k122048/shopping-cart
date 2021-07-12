package com.example.demo.dao;

import com.example.demo.model.OrderDetail;

import java.util.List;

public interface OrderDetailDAO {

    public void save(OrderDetail orderDetail);
    public List<OrderDetail> findByOrderId(int orderId );
    public void deleteById( int id );
    public OrderDetail findById( int id );
    public void deleteByOrderId( int id );
}
