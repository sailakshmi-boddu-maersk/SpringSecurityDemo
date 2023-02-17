package com.slb.SpringSecurityDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.slb.SpringSecurityDemo.Repo.UserRepository;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses=UserRepository.class)
public class SpringSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}
}
