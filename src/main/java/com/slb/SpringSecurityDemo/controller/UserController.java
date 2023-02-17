package com.slb.SpringSecurityDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slb.SpringSecurityDemo.model.MyUserDetails;
import com.slb.SpringSecurityDemo.model.User;
import com.slb.SpringSecurityDemo.service.MyUserDetailsService;

import jakarta.annotation.security.RolesAllowed;

@RestController
public class UserController {

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@RequestMapping("/")
	public String index() {
		return "<h1>Welcome</h1>";
	}
	
	@RequestMapping("/user")
//	@RolesAllowed({"USER","ADMIN"})
	public String user() {
		return "<h1>Welcome User</h1>";
	}
	
	@RequestMapping("/admin")
//	@RolesAllowed("ADMIN")
	public String admin() {
		return "<h1>Welcome Admin</h1>";
	}
	
	@RequestMapping("/user/insert")
	public String insertUser(User user) {
		myUserDetailsService.insertUser(user);
		return"<h1>User inserted successfully!!</h1>";
	}
}
