package dev.cse.imageannotatorbackend.service;

import dev.cse.imageannotatorbackend.model.Admin;
import dev.cse.imageannotatorbackend.model.Annotators;
import dev.cse.imageannotatorbackend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private AdminRepository adminRepository;
    private AnnotatorsService annotatorsService;

    @Autowired
    public AdminService(AdminRepository adminRepository, AnnotatorsService annotatorsService) {
        this.adminRepository = adminRepository;
        this.annotatorsService = annotatorsService;
    }

    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public Optional<Admin> getAdmin(String username) {
        return adminRepository.findByUsername(username);
    }

    public List<Annotators> getAllAnnotators() {
        return annotatorsService.findAll();
    }

    public void deleteAnnotatorAccount(String username) {
        annotatorsService.deleteAnnotatorAccount(username);
    }
}
