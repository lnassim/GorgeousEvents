package com.apiGorgeousEvent.DataAccessObject;

import com.apiGorgeousEvent.Model.UserModel;


import java.util.List;

public interface UserDao {

	List<UserModel> allUsers();

	UserModel getUser(int id);

	UserModel getUserbyUsername(String username);

	void createUser(UserModel user);

	void deleteUser(UserModel user);

	int getLastId();

	Boolean userExists(String username, String password);
}
