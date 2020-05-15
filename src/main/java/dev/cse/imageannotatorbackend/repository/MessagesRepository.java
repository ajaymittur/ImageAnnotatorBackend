package dev.cse.imageannotatorbackend.repository;

import dev.cse.imageannotatorbackend.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Long> {
    @Query(value = "SELECT * FROM Messages m WHERE m.MessageId = ?1", nativeQuery = true)
    List<Messages> findById(long id);
}
