package dev.cse.imageannotatorbackend.repository;

import dev.cse.imageannotatorbackend.model.OriginalImages;
import dev.cse.imageannotatorbackend.model.OriginalImagesId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OriginalImagesRepository extends JpaRepository<OriginalImages, OriginalImagesId> {
	List<OriginalImages> findByUserUsername(String username);
}
