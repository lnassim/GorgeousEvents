package Modele;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO) long id;
	@Column(name ="user")
	private String user;
	private String password;
	private String token;

	private User(){}

	public User(String user, String password, String Token){
		this.user=user;
		this.password = password;
		this.token = token;
	}

	public User(String user, String password){
		this.user= user;
		this.password = password;
	}
	public String getUser(){return user;}
	public String getPassword(){return password;}
	public String getToken(){return token;}
	public String setToken(String token){this.token =token;}


}
