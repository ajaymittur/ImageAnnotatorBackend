package dev.cse.imageannotatorbackend.controller;

import dev.cse.imageannotatorbackend.model.Users;
import dev.cse.imageannotatorbackend.repository.UsersRepository;
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

	private UsersRepository userRepo;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserController(UsersRepository userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/getAccountDetails")
	public Users getLoggedInUser(Principal principal) throws UsernameNotFoundException {
		String username = principal.getName();
		Optional<Users> loggedInUser = userRepo.findByUsername(username);
		loggedInUser.ifPresent(users -> users.setPassword("***SECRET***"));
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
			userRepo.save(newUser);
			return new ResponseEntity<>("User Account Created Successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
	}
}
