package dev.cse.imageannotatorbackend.controller;

import dev.cse.imageannotatorbackend.model.Annotators;
import dev.cse.imageannotatorbackend.service.AnnotatorsService;
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
@RequestMapping("/annotator")
public class AnnotatorController {

	private AnnotatorsService annotatorsService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public AnnotatorController(AnnotatorsService annotatorsService, PasswordEncoder passwordEncoder) {
		this.annotatorsService = annotatorsService;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/get-account-details")
	public Annotators getLoggedInUser(Principal principal) throws UsernameNotFoundException {
		String username = principal.getName();
		Optional<Annotators> loggedInUser = annotatorsService.getAnnotator(username);
		loggedInUser.ifPresent(annotators -> annotators.setPassword("***********"));
		return loggedInUser.orElseThrow(() -> new UsernameNotFoundException("Annotator with username: " + username + " not found in records"));
	}

	@GetMapping("/login")
	public ResponseEntity<String> login(Principal principal) {
		// Authentication being handled by spring security
		// Pass credentials as basic auth in header
		return new ResponseEntity<>("Annotator: " + principal.getName() + " Logged In", HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody Annotators newAnnotator) {
		newAnnotator.setCreated_on(new Date());
		String plainPassword = newAnnotator.getPassword();
		String encryptedPassword = passwordEncoder.encode(plainPassword);
		newAnnotator.setPassword(encryptedPassword);
		try {
			annotatorsService.addAnnotator(newAnnotator);
			return new ResponseEntity<>("Annotator Account Created Successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
		}
	}
}
