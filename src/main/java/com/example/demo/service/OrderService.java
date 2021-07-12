package com.example.demo.service;

import com.example.demo.dao.OrderDAO;
import com.example.demo.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private OrderDAO orderDAO;

    @Autowired
    public OrderService( OrderDAO orderDAO ){ this.orderDAO = orderDAO;}

    @Transactional
    public Orders getOrderById(int id ){
        return orderDAO.findById( id );
    }

    @Transactional
    public Orders getOrderByUserName (String userName ){ return orderDAO.findByUserName( userName );}

    @Transactional
    public void saveOrder( Orders orders){
        orderDAO.save(orders);
    }

    @Transactional
    public void deleteById( int id ){ orderDAO.deleteById( id );}

}
