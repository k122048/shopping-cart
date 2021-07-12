package com.example.demo.service;

import com.example.demo.dao.OrderDetailDAO;
import com.example.demo.model.Inventory;
import com.example.demo.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderDetailService {

    private OrderDetailDAO orderDetailDAO;

    @Autowired
    public OrderDetailService( OrderDetailDAO orderDetailDAO ){
        this.orderDetailDAO = orderDetailDAO;
    }

    @Transactional
    public void saveOrderDetail(OrderDetail orderDetail){
        orderDetailDAO.save( orderDetail );
    }

    @Transactional
    public List<OrderDetail> getByOrderId( int id ){  return orderDetailDAO.findByOrderId( id ); }

    @Transactional
    public void deleteById( int id ){  orderDetailDAO.deleteById( id ); }

    @Transactional
    public OrderDetail findById( int id ){ return orderDetailDAO.findById( id ); }

    @Transactional
    public void deleteByOrderId( int id ){ orderDetailDAO.deleteByOrderId( id ); }

    @Transactional
    public OrderDetail findByOrderIdAndInventoryId(int orderId, int inventoryId ){
        return  orderDetailDAO.findByOrderIdAndInventoryId( orderId, inventoryId );
    }
}
