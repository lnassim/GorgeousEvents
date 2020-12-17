package RestServer.controller;

import RestServer.model.UserModel;
import RestServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
// communication distante client serveur
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<Collection<UserModel>> getAllUsers() {
        Collection<UserModel> users = userService.getAllUsers();
        return new ResponseEntity<Collection<UserModel>>(users, HttpStatus.FOUND);
    }
}
