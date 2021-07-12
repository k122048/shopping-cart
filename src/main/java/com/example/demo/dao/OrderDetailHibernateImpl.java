package com.example.demo.dao;

import com.example.demo.model.Inventory;
import com.example.demo.model.OrderDetail;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class OrderDetailHibernateImpl implements OrderDetailDAO {

   @Autowired
   private EntityManager entityManager;

 public void save(OrderDetail orderDetail){
     Session currentSession = entityManager.unwrap(Session.class);

     currentSession.saveOrUpdate(orderDetail);
 }

 public List<OrderDetail> findByOrderId( int orderId ){
     Session currentSession = entityManager.unwrap(Session.class);
     Query<OrderDetail> query = currentSession.createQuery("from OrderDetail where order_id =:id");
     query.setParameter("id", orderId);
     List<OrderDetail> orderDetails = null;
     try {
         orderDetails = query.getResultList();
     }catch (NoResultException nre){
//Ignore this because as per your logic this is ok!
     }
     return orderDetails;
 }

 public void deleteById( int id ){
     Session currentSession = entityManager.unwrap(Session.class);
     Query query = currentSession.createQuery("delete from OrderDetail where id=:id");
     query.setParameter("id", id);
     query.executeUpdate();
 }

 public void deleteByOrderId( int id ){
     Session currentSession = entityManager.unwrap(Session.class);
     Query query = currentSession.createQuery("delete from OrderDetail where order_id=:id");
     query.setParameter("id", id);
     query.executeUpdate();
 }

 public OrderDetail findById( int id ){
     Session currentSession = entityManager.unwrap(Session.class);
     Query<OrderDetail> query = currentSession.createQuery("from OrderDetail where id=:id");
     query.setParameter("id", id );
     OrderDetail orderDetail = null;
     try{
         orderDetail = query.getSingleResult();
     }catch (NoResultException nre){

     }
     return orderDetail;
 }

 public OrderDetail findByOrderIdAndInventoryId( int orderId, int inventoryId ){
     Session currentSession = entityManager.unwrap(Session.class);
     Query<OrderDetail> query = currentSession.createQuery("from OrderDetail where order_id=:id and product_id=:inv_id");
     query.setParameter("id", orderId );
     query.setParameter("inv_id", inventoryId);
     OrderDetail orderDetail = null;
     try{
         orderDetail = query.getSingleResult();
     }catch (NoResultException nre){

     }
     return orderDetail;
 }


}
