package dev.cse.imageannotatorbackend.repository;

import dev.cse.imageannotatorbackend.model.AnnotatorMessages;
import dev.cse.imageannotatorbackend.model.AnnotatorMessagesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnotatorMessagesRepository extends JpaRepository<AnnotatorMessages, AnnotatorMessagesId> {
    @Query(value = "SELECT am.MessageId from Annotator_Messages am WHERE am.AnnotatorId = ?1", nativeQuery = true)
    List<Long> findByAnnotatorId(long id);
}
