package com.apiGorgeousEvent.DataAccessObject;

import com.apiGorgeousEvent.Model.UserModel;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public interface UserDao {

	List<UserModel> allUsers();

	UserModel getUser(int id);

	UserModel getUserbyUsername(String username);

	void createUser(UserModel user);

	void deleteUser(UserModel user);

	int getLastId();

	Boolean userExists(String username, String password);
}
