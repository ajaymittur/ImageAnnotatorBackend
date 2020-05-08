package dev.cse.imageannotatorbackend.repository;

import dev.cse.imageannotatorbackend.model.Messages;
import dev.cse.imageannotatorbackend.model.MessagesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, MessagesId> {
    List<Messages> findByUsername(String username);
}
