package com.apiGorgeousEvent;

import com.apiGorgeousEvent.DataAccessObject.UserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class GorgeousEventApplication {



	public static void main(String[] args) {
		SpringApplication.run(GorgeousEventApplication.class, args);
	}

}
