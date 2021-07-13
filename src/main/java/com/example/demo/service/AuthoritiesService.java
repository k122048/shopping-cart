package com.example.demo.service;

import com.example.demo.dao.AuthoritiesDAO;
import com.example.demo.model.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthoritiesService {

    private AuthoritiesDAO authoritiesDAO;

    @Autowired
    public AuthoritiesService( AuthoritiesDAO authoritiesDAO ){ this.authoritiesDAO = authoritiesDAO; }

    @Transactional
    public void save(Authorities authorities ){
        authoritiesDAO.save( authorities );
    }
}
