package com.apiGorgeousEvent;

import Modele.User;

import java.util.List;

public interface UserRepository {
    List<User> findByUser(String user);
    List<User> findByToken(String token);

    void save(User user);
}
