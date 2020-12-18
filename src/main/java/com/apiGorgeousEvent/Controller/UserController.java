package com.apiGorgeousEvent.Controller;

import com.apiGorgeousEvent.Model.UserModel;
import com.apiGorgeousEvent.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// communication distante client serveur
@CrossOrigin
@RestController
@RequestMapping("/")

public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value="inscription")
    public Map<String,Object> getInscription(@RequestBody Map<String,Object> request) {
        UserModel user = new UserModel((String) request.get("userName"),(String) request.get("password"),(String)request.get("mail"));
        System.out.println(request.get("userName") + " " + request.get("password"));
        userService.saveOrUpdateUser(user);
        return request;

    }




}