package dev.cse.imageannotatorbackend.repository;

import dev.cse.imageannotatorbackend.model.AnnotatedImages;
import dev.cse.imageannotatorbackend.model.AnnotatedImagesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnotatedImagesRepository extends JpaRepository<AnnotatedImages, AnnotatedImagesId> {
	@Query(value = "SELECT * FROM Annotated_Images WHERE Annotated_By = ?", nativeQuery = true)
	List<AnnotatedImages> findByAnnotatorUsername(String username);

	@Query(value = "SELECT * FROM Annotated_Images WHERE Image_Name IN ? AND Folder_Name IN ?", nativeQuery = true)
	List<AnnotatedImages> findByNameInAndFolderNameIn(List<String> names, List<String> folderNames);
}
