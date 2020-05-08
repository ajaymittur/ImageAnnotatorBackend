package dev.cse.imageannotatorbackend.controller;

import dev.cse.imageannotatorbackend.model.Admin;
import dev.cse.imageannotatorbackend.model.Annotators;
import dev.cse.imageannotatorbackend.model.Messages;
import dev.cse.imageannotatorbackend.service.AdminService;
import dev.cse.imageannotatorbackend.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    private PasswordEncoder passwordEncoder;
    private MessagesService messagesService;

    @Autowired
    public AdminController(AdminService adminService, PasswordEncoder passwordEncoder, MessagesService messagesService) {
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
        this.messagesService = messagesService;
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(Principal principal) {
        return new ResponseEntity<>("Admin: " + principal.getName() + " logged in", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Admin newAdmin) {
        String plainPassword = newAdmin.getPassword();
        String encryptedPassword = passwordEncoder.encode(plainPassword);
        newAdmin.setPassword(encryptedPassword);
        try {
            adminService.addAdmin(newAdmin);
            return new ResponseEntity<>("Admin Account Created Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-account-details")
    public Admin getLoggedInUser(Principal principal) throws UsernameNotFoundException {
        String username = principal.getName();
        Optional<Admin> loggedInUser = adminService.getAdmin(username);
        loggedInUser.ifPresent(admin -> admin.setPassword("***********"));
        return loggedInUser.orElseThrow(() -> new UsernameNotFoundException("Admin with username: " + username + " not found in records"));
    }

    @GetMapping("/get-annotators")
    public List<Annotators> getAnnotators() {
        return adminService.getAllAnnotators();
    }

    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestBody Messages message) {
        try {
            messagesService.addMessage(message);
            return new ResponseEntity<>("Message Sent to Annotator Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
