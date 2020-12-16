package Controller;

import Modele.User;
import com.apiGorgeousEvent.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.plugin2.message.Message;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class Controller {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private static final int EXPIRATION =100;

	@Autowired
	private UserRepository repository;

	/*@GetMapping(value="/api");
	public String index(); */

/*	@RequestMapping(value="/connection", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE);
    @ResponseBody
	public Message generateToken(@RequestBody Map<String, Object> playload){
    	String user = playload.get("user").toString();
    	String password = playload.get("password").toString();
    	System.out.println(user + "user");
    	System.out.println(password + "password");
	}*/

	@RequestMapping(value="/inscription")
	public void someMethod(@RequestParam String userName,@RequestParam String password, @RequestParam String email){
    	ModelAndView modelAndView = new ModelAndView("inscription");

    	repository.save(new User(userName,password));

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
	}



}
