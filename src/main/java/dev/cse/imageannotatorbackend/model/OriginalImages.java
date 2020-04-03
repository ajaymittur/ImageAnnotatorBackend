package dev.cse.imageannotatorbackend.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Original_Images")
@IdClass(OriginalImagesId.class)
public class OriginalImages {

	@Id
	@Column(name = "Image_Name")
	private String name;

	@Id
	@Column(name = "Folder_Name")
	private String folderName;

	@Column(name = "Image_URL")
	private String url;

	@ManyToOne
	@JoinColumn(name = "Username")
	private Users user;

	@ElementCollection
	@CollectionTable(name = "Image_Categories", joinColumns = {
			@JoinColumn(name = "Image_Name", referencedColumnName = "Image_Name"),
			@JoinColumn(name = "Folder_Name", referencedColumnName = "Folder_Name")
	})
	@Column(name = "Category")
	private Set<String> categories = new HashSet<>();

	@ElementCollection
	@CollectionTable(name = "Image_Tags", joinColumns = {
			@JoinColumn(name = "Image_Name", referencedColumnName = "Image_Name"),
			@JoinColumn(name = "Folder_Name", referencedColumnName = "Folder_Name")
	})
	@Column(name = "Tag")
	private Set<String> tags = new HashSet<>();

	public OriginalImages() {
	}

	public OriginalImages(Users user, String name, String folderName, String url, Set<String> categories, Set<String> tags) {
		this.user = user;
		this.name = name;
		this.folderName = folderName;
		this.url = url;
		this.categories = categories;
		this.tags = tags;
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Set<String> getCategories() {
		return categories;
	}

	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
}