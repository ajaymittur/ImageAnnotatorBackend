package dev.cse.imageannotatorbackend.repository;

import dev.cse.imageannotatorbackend.model.AnnotatedImages;
import dev.cse.imageannotatorbackend.model.AnnotatedImagesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnotatedImagesRepository extends JpaRepository<AnnotatedImages, AnnotatedImagesId> {
	List<AnnotatedImages> findByAnnotatorUsername(String username);
}
