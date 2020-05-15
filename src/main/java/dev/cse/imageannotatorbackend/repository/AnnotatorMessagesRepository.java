package dev.cse.imageannotatorbackend.repository;

import dev.cse.imageannotatorbackend.model.AnnotatorMessages;
import dev.cse.imageannotatorbackend.model.AnnotatorMessagesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnotatorMessagesRepository extends JpaRepository<AnnotatorMessages, AnnotatorMessagesId> {
}
