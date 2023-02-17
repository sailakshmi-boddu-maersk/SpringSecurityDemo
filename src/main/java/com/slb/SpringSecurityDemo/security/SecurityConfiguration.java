package com.slb.SpringSecurityDemo.security;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.slb.SpringSecurityDemo.Repo.UserRepository;
import com.slb.SpringSecurityDemo.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

	@Autowired
	MyUserDetailsService MyUserDetailsService;
	
	@Autowired
	DataSource dataSource;

	
	
//jdbc connectiong to actual db
	  
	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
	   http.csrf().disable()
	   .authorizeHttpRequests()
	  .requestMatchers("/admin").hasRole("ADMIN")
	  .requestMatchers("/user").hasAnyRole("USER","ADMIN")
	  .requestMatchers("/user/insert").hasRole("ADMIN")
	  .requestMatchers("/").permitAll() 
	  .requestMatchers("/actuator/**").permitAll()
	  .requestMatchers("/swagger-ui.html").permitAll()
	  .and().formLogin();
	   
	  http.authenticationProvider(daoAutenticationProvider()); return http.build();
	  }
	  
	  @Bean 
	  public DaoAuthenticationProvider daoAutenticationProvider() {
	  DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
	  provider.setUserDetailsService(MyUserDetailsService);
	  provider.setPasswordEncoder( getPasswordEncoder() ); return provider;
	  
	  }
	  
	  @Bean 
	  public PasswordEncoder getPasswordEncoder() {
		  return NoOpPasswordEncoder.getInstance();
	  }
	 

	
	
	
//	@Bean
//  public InMemoryUserDetailsManager userDetailsService() {
//		
//		List<UserDetails> userList=new ArrayList<>();
//		
//      UserDetails user = User.withDefaultPasswordEncoder()
//          .username("user")
//          .password("pass")
//          .roles("USER")
//          .build();
//      UserDetails user1 = User.withDefaultPasswordEncoder()
//              .username("user1")
//              .password("pass")
//              .roles("ADMIN")
//              .build();
//      userList.add(user);
//      userList.add(user1);
//      
//      return new InMemoryUserDetailsManager(userList);
//  }
//	 @Bean
//	    public DataSource dataSource() {
//	        return new EmbeddedDatabaseBuilder()
//	            .setType(EmbeddedDatabaseType.H2)
//	            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//	            .build();
//	    }

//	 @Bean
//	 public UserDetailsService userDetailsService() {
//		  UserDetails user = User.withDefaultPasswordEncoder()
//		            .username("user")
//		            .password("pass")
//		            .roles("USER")
//		            .build();
//		        UserDetails user1 = User.withDefaultPasswordEncoder()
//		                .username("user1")
//		                .password("pass")
//		                .roles("ADMIN")
//		                .build();
//		        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//		        users.getUsersByUsernameQuery("select * from users where user_name=?");
//		        users.createUser(user);
//		        users.createUser(user1);
//		        return users;
//	 }

	
//	 @Bean
//	 public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//	     return authenticationConfiguration.getAuthenticationManager();
//	 }
	 
//	 @Bean
//		public AuthenticationManager authenticationManagerBean() throws Exception {
//			return new AuthenticationManagerBean();
//		}

	 
//	    @Bean
//	    public UserDetailsManager users(DataSource dataSource) {
//	        UserDetails user = User.withDefaultPasswordEncoder()
//	            .username("user")
//	            .password("password")
//	            .roles("USER")
//	            .build();
//	        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//	        users.createUser(user);
//	        return users;
//	    }
}
