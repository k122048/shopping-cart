package com.example.demo.dao;

import com.example.demo.model.Orders;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
public class OrderHibernateImpl implements OrderDAO {

    @Autowired
    private EntityManager entityManager;

    public Orders findById(int id ){
        Session currentSession = entityManager.unwrap(Session.class);
        Orders orders = currentSession.get(Orders.class,id);
        return orders;
    }

    public void save( Orders orders){
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(orders);
    }

    public void deleteById( int id ){
        Session currentSession = entityManager.unwrap( Session.class );
        Query query = currentSession.createQuery("delete from Orders where id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    public Orders findByUserName(String userName ){
        Session currentSession = entityManager.unwrap( Session.class );
        Query<Orders> query = currentSession.createQuery("from Orders where userName=:username");
        query.setParameter("username", userName);
        Orders orders = null;
        try {
            orders = query.getSingleResult();
        }catch (NoResultException nre){
//Ignore this because as per your logic this is ok!
        }
        return orders;
    }

}
