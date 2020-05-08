package dev.cse.imageannotatorbackend.repository;

import dev.cse.imageannotatorbackend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    @Query(value = "SELECT * FROM Admin WHERE Username = ?1", nativeQuery = true)
    Optional<Admin> findByUsername(String username);
}
