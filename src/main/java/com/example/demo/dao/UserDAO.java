package com.example.demo.dao;

import com.example.demo.model.User;

public interface UserDAO {

    public User findByUserName( String userName );
    public void save( User user );
}
