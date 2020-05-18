package dev.cse.imageannotatorbackend.repository;

import dev.cse.imageannotatorbackend.model.Annotators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnnotatorsRepository extends JpaRepository<Annotators, Long> {
	@Query(value = "SELECT * FROM Annotators a WHERE a.Username = ?1", nativeQuery = true)
	Optional<Annotators> findByUsername(String username);

//	@Query(value = "DELETE FROM Annotators a WHERE a.Username = ?1", nativeQuery = true)
	long deleteByUsername(String username);
}
