package dev.cse.imageannotatorbackend.model.resource;

public abstract class Image {

	public String imageName;
	public String folderName;
	public String url;

	public Image(String imageName, String folderName, String url) {
		this.imageName = imageName;
		this.folderName = folderName;
		this.url = url;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
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
}
