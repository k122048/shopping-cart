package com.example.demo.dao;

import com.example.demo.model.Inventory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class InventoryHibernateImpl implements InventoryDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Inventory> findAll(){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Inventory> theQuery = currentSession.createQuery("from Inventory",Inventory.class);
        List<Inventory> inventories = theQuery.getResultList();
        return inventories;
    }

    @Override
    public Inventory findById( int id ){
        Session currentSession = entityManager.unwrap(Session.class);
        Inventory inventory = currentSession.get(Inventory.class,id);
        return inventory;
    }

    @Override
    public void save( Inventory inventory ){
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(inventory);
    }
}
