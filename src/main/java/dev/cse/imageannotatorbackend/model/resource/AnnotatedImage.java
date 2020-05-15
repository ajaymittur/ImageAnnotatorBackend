package dev.cse.imageannotatorbackend.model.resource;

import dev.cse.imageannotatorbackend.model.AnnotatedImages;

public class AnnotatedImage extends Image {

	private String category;
	private String annotatedBy;

	public AnnotatedImage(AnnotatedImages annotImg) {
		super(annotImg.getName(), annotImg.getFolderName(), annotImg.getUrl());
		this.category = annotImg.getCategory();
		this.annotatedBy = annotImg.getAnnotated_by();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAnnotatedBy() {
		return annotatedBy;
	}

	public void setAnnotatedBy(String annotatedBy) {
		this.annotatedBy = annotatedBy;
	}
}
