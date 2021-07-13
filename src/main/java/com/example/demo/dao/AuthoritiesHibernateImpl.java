package com.example.demo.dao;

import com.example.demo.model.Authorities;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AuthoritiesHibernateImpl implements AuthoritiesDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void save(Authorities authorities){
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save( authorities );
    }
}
