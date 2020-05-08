package dev.cse.imageannotatorbackend.service;

import dev.cse.imageannotatorbackend.model.Admin;
import dev.cse.imageannotatorbackend.model.CustomAdminDetails;
import dev.cse.imageannotatorbackend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminDetailsService implements UserDetailsService {
    private AdminRepository adminRepo;

    @Autowired
    public AdminDetailsService(AdminRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepo.findByUsername(username);
        return new CustomAdminDetails(admin.orElseThrow(() -> new UsernameNotFoundException("Username not found")));
    }
}
