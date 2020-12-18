package com.apiGorgeousEvent.Service;

import com.apiGorgeousEvent.Exception.BusinessResourceException;
import com.apiGorgeousEvent.Model.UserModel;
import com.apiGorgeousEvent.Model.UserModel;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Collection<UserModel> getAllUsers() throws BusinessResourceException;

    Optional<UserModel> getUserById(Long id) throws BusinessResourceException;


    Optional<UserModel> findByLogin(String login)throws BusinessResourceException;

    Optional<UserModel> findUserById(Long id) throws BusinessResourceException;

    UserModel saveOrUpdateUser(UserModel user) throws BusinessResourceException;

    void deleteUser(Long id) throws BusinessResourceException;

    Optional<UserModel> findByLoginAndPassword(String login, String password) throws BusinessResourceException;

}
