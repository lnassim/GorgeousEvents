package RestServer.service;

import RestServer.model.UserModel;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Collection<UserModel> getAllUsers();

    Optional<UserModel> getUserById(Long id);

    Optional<UserModel> findByLogin(String login);

    UserModel saveOrUpdateUser(UserModel user);

    void deleteUser(Long id);

    Optional<UserModel> findByLoginAndPassword(String login, String password);

}