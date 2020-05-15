package dev.cse.imageannotatorbackend.repository;

import dev.cse.imageannotatorbackend.model.AnnotatedImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnotatedImagesRepository extends JpaRepository<AnnotatedImages, Long> {
	@Query(value = "SELECT * FROM Annotated_Images WHERE Annotated_By = ?1", nativeQuery = true)
	List<AnnotatedImages> findByAnnotatorUsername(String username);

	@Query(value = "SELECT * FROM Annotated_Images WHERE Image_Name IN ?1 AND Folder_Name IN ?2", nativeQuery = true)
	List<AnnotatedImages> findByNameInAndFolderNameIn(List<String> names, List<String> folderNames);
}
