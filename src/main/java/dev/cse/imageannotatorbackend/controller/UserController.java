package dev.cse.imageannotatorbackend.controller;

import dev.cse.imageannotatorbackend.model.Users;
import dev.cse.imageannotatorbackend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

	private UsersService usersService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserController(UsersService usersService, PasswordEncoder passwordEncoder) {
		this.usersService = usersService;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/get-account-details")
	public Users getLoggedInUser(Principal principal) throws UsernameNotFoundException {
		String username = principal.getName();
		Optional<Users> loggedInUser = usersService.getUser(username);
		loggedInUser.ifPresent(users -> users.setPassword("***********"));
		return loggedInUser.orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found in records"));
	}

	@GetMapping("/login")
	public ResponseEntity<String> login(Principal principal) {
		// Authentication being handled by spring security
		// Pass credentials as basic auth in header
		return new ResponseEntity<>("User: " + principal.getName() + " Logged In", HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody Users newUser) {
		newUser.setCreated_on(new Date());
		String plainPassword = newUser.getPassword();
		String encryptedPassword = passwordEncoder.encode(plainPassword);
		newUser.setPassword(encryptedPassword);
		try {
			usersService.addUser(newUser);
			return new ResponseEntity<>("User Account Created Successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
	}
}
