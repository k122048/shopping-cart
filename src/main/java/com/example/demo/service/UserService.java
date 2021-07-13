package com.example.demo.service;

import com.example.demo.dao.AuthoritiesDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.model.Authorities;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class UserService {

    private UserDAO userDAO;

    private AuthoritiesDAO authoritiesDAO;

    @Autowired
    public UserService( UserDAO userDAO, AuthoritiesDAO authoritiesDAO ){  this.userDAO = userDAO; this.authoritiesDAO = authoritiesDAO;  }

    public User registerNewUserAccount(User user) throws UserAlreadyExistException {
        if (userNameExist(user.getUserName())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + user.getUserName());
        }

        User registeredUser = new User();
        registeredUser.setUserName( user.getUserName() );
        registeredUser.setPassword( user.getPassword() );
        registeredUser.setEnabled(true);
        Authorities authorities = new Authorities();
        authorities.setAuthority("ROLE_USER");
        authorities.setUserName( user.getUserName() );
        userDAO.save( registeredUser );
        authoritiesDAO.save( authorities );
        return registeredUser;
        // the rest of the registration operation
    }

    @Transactional
    public boolean userNameExist(String userName) {
        return userDAO.findByUserName(userName) != null;
    }

    @Transactional
    public void save( User user ){
        userDAO.save(user);
    }
}
