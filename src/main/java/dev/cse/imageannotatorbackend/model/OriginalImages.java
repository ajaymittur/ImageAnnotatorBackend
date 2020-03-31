package dev.cse.imageannotatorbackend.model;

import javax.persistence.*;

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
	private Users user; // Enforces Username foreign key constraint

	public OriginalImages() {
	}

	public OriginalImages(Users user, String name, String folderName, String url) {
		this.user = user;
		this.name = name;
		this.folderName = folderName;
		this.url = url;
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
}