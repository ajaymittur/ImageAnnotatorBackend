package dev.cse.imageannotatorbackend.model;


import javax.persistence.*;

@Entity
@Table(name = "Annotated_Images")
@IdClass(AnnotatedImagesId.class)
public class AnnotatedImages {

	@Id
	@Column(name = "Image_Name")
	private String name;

	@Id
	@Column(name = "Folder_Name")
	private String folderName;

	@Column(name = "Image_URL")
	private String url;

	@Column(name = "Category")
	String category;

	@ManyToOne
	@JoinColumn(name = "Annotated_By")
	private Annotators annotator; // Enforces Username foreign key constraint

	public AnnotatedImages() {
	}

	public AnnotatedImages(Annotators annotator, String name, String folderName, String url, String category) {
		this.annotator = annotator;
		this.name = name;
		this.folderName = folderName;
		this.url = url;
		this.category = category;
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

	public Annotators getAnnotator() {
		return annotator;
	}

	public void setAnnotator(Annotators annotator) {
		this.annotator = annotator;
	}
}
