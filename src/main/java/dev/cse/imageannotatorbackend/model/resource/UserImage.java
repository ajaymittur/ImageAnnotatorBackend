package dev.cse.imageannotatorbackend.model.resource;

import dev.cse.imageannotatorbackend.model.OriginalImages;

import java.util.Set;

public class UserImage extends Image {

	private String uploadedBy;
	private Set<String> categories;
	private Set<String> tags;

	public UserImage(OriginalImages img) {
		super(img.getName(), img.getFolderName(), img.getUrl());
		this.uploadedBy = img.getUser().getUsername();
		this.categories = img.getCategories();
		this.tags = img.getTags();
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
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
