package dev.cse.imageannotatorbackend.repository;

import dev.cse.imageannotatorbackend.model.Annotators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnnotatorsRepository extends JpaRepository<Annotators, String> {
	@Query(value = "SELECT * FROM Annotators WHERE Username = ?1", nativeQuery = true)
	Optional<Annotators> findByUsername(String username);
}
