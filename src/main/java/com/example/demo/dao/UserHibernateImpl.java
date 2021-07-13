package com.example.demo.dao;

import com.example.demo.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
public class UserHibernateImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public User findByUserName( String userName ){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("From User where userName=:userName");
        query.setParameter("userName", userName );
        User user = null;
        try {
            user = query.getSingleResult();
        }catch (NoResultException nre){
//Ignore this because as per your logic this is ok!
        }
        return user;
    }

    @Override
    public void save( User user ){
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(user);
    }
}
