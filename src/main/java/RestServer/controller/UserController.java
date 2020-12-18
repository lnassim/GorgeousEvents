package RestServer.controller;

import RestServer.model.UserModel;
import RestServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

// communication distante client serveur
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<Collection<UserModel>> getAllUsers() {
        Collection<UserModel> users = userService.getAllUsers();
        return new ResponseEntity<Collection<UserModel>>(users, HttpStatus.FOUND);
    }

    @PostMapping(value="inscription")
    public Map<String,Object> getInscription(@RequestBody Map<String,Object> request) {
        UserModel user = new UserModel((String) request.get("userName"),(String) request.get("password"),(String)request.get("mail"));
        System.out.println(request.get("userName") + " " + request.get("password"));
        userService.saveOrUpdateUser(user);
        return request;
//    public void getInscription(@RequestParam String userName,@RequestParam String password, @RequestParam String email, @RequestParam String confirmPassword) {
        //Collection<UserModel> users = userService.getInscription();
        // return request.get("userName");//new ResponseEntity<UserModel>((UserModel) users, HttpStatus.FOUND);
    }
}
