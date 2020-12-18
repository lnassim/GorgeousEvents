package com.apiGorgeousEvent;

import com.apiGorgeousEvent.Model.UserModel;

import java.util.List;

public interface UserRepository {
    List<UserModel> findByUser(String user);
    List<UserModel> findByToken(String token);

    void save(UserModel user);
}
