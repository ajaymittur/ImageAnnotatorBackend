package dev.cse.imageannotatorbackend.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Original_Images")
public class OriginalImages {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ImageId")
	private Long id;

	@Column(name = "Image_Name")
	private String name;

	@Column(name = "Folder_Name")
	private String folderName;

	@Column(name = "Image_URL")
	private String url;

	@Column(name = "Uploaded_By")
	private String uploaded_by;

	@Column(name = "UserId")
	private long userId;

	@ElementCollection
	@CollectionTable(name = "Image_Categories", joinColumns = @JoinColumn(name = "ImageId", referencedColumnName = "ImageId"))
	@Column(name = "Category")
	private Set<String> categories = new HashSet<>();

	public OriginalImages() {
	}

	public OriginalImages(Users user, String name, String folderName, String url, Set<String> categories) {
		this.name = name;
		this.folderName = folderName;
		this.url = url;
		this.uploaded_by = user.getUsername();
		this.userId = user.getId();
		this.categories = categories;
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

	public Set<String> getCategories() {
		return categories;
	}

	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUploaded_by() {
		return uploaded_by;
	}

	public void setUploaded_by(String uploaded_by) {
		this.uploaded_by = uploaded_by;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}