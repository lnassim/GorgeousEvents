package com.apiGorgeousEvent.Service;

import com.apiGorgeousEvent.DataAccessObject.UserDao;

import com.apiGorgeousEvent.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (dao.getUserbyUsername(username) == null) {
            throw new UsernameNotFoundException("L Utilisateur n a pas ete trouve avec le user suivant: " + username);
        }
        return new UserModel(dao.getUserbyUsername(username).getUsername(),
                bcryptEncoder.encode(dao.getUserbyUsername(username).getPassword()));

    }
}
