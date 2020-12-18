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
//    public void getInscription(@RequestParam String userName,@RequestParam String password, @RequestParam String email, @RequestParam String confirmPassword) {
        //Collection<UserModel> users = userService.getInscription();
       // return request.get("userName");//new ResponseEntity<UserModel>((UserModel) users, HttpStatus.FOUND);
    }


   /* @RequestMapping(value="/inscription")
    public void someMethod(@RequestParam String userName,@RequestParam String password, @RequestParam String email){
        ModelAndView modelAndView = new ModelAndView("inscription");

        repository.save(new User(userName,password));*/

    	/*
    	for (User u : repository.findByUser(user)){
    		if(u.getUser().equals(user)){
    			modelAndView.addObject("message","Il y a déjà un compte à ce nom");
    			System.out.println(u.getUser()+"already exist");
    			return modelAndView;
			}
		}
    	modelAndView.addObject("message","Il y a déjà un compte à ce nom");
    	repository.save(new User(user,password));
    	return modelAndView;*/


   /* @GetMapping(value = "/users")
    public ResponseEntity<Collection<UserModel>> getAllUsers() {
        Collection<UserModel> users = userService.getAllUsers();
        return new ResponseEntity<Collection<UserModel>>(users, HttpStatus.FOUND);
    }*/


}