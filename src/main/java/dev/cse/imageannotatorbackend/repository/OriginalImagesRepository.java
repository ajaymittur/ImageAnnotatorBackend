package dev.cse.imageannotatorbackend.repository;

import dev.cse.imageannotatorbackend.model.OriginalImages;
import dev.cse.imageannotatorbackend.model.OriginalImagesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OriginalImagesRepository extends JpaRepository<OriginalImages, OriginalImagesId> {
	List<OriginalImages> findByUserUsername(String username);

	@Query(value="SELECT * FROM Original_Images og WHERE (og.Image_Name, og.Folder_Name) NOT IN (SELECT Image_Name, Folder_Name from Annotated_Images ai)", nativeQuery=true)
	List<OriginalImages> findAllUnAnnotatedImages();

	@Query(value="SELECT * FROM Original_Images og WHERE og.Username = :username AND (og.Image_Name, og.Folder_Name) NOT IN (SELECT Image_Name, Folder_Name from Annotated_Images ai)", nativeQuery=true)
	List<OriginalImages> findUnAnnotatedImagesByUsername(@Param("username") String username);
}
