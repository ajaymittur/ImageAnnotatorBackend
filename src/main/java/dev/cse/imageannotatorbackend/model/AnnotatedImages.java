package dev.cse.imageannotatorbackend.model;


import javax.persistence.*;

@Entity
@Table(name = "Annotated_Images")
public class AnnotatedImages {

	@Id
	@Column(name = "ImageId")
	private Long id;

	@Column(name = "Image_Name")
	private String name;

	@Column(name = "Folder_Name")
	private String folderName;

	@Column(name = "Image_URL")
	private String url;

	@Column(name = "Category")
	String category;

	@Column(name = "Annotated_By")
	private String annotated_by;

	@Column(name = "AnnotatorId")
	private long annotatorId;

	public AnnotatedImages() {
	}

	public AnnotatedImages(long imageId, Annotators annotator, String name, String folderName, String url, String category) {
		this.id = imageId;
		this.name = name;
		this.folderName = folderName;
		this.url = url;
		this.category = category;
		this.annotated_by = annotator.getUsername();
		this.annotatorId = annotator.getId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnnotated_by() {
		return annotated_by;
	}

	public void setAnnotated_by(String annotated_by) {
		this.annotated_by = annotated_by;
	}

	public long getAnnotatorId() {
		return annotatorId;
	}

	public void setAnnotatorId(long annotatorId) {
		this.annotatorId = annotatorId;
	}
}
