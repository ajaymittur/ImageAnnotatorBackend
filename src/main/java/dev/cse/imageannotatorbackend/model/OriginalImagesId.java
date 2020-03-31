package dev.cse.imageannotatorbackend.model;

import java.io.Serializable;
import java.util.Objects;

public class OriginalImagesId implements Serializable {

	private String name;
	private String folderName;

	public OriginalImagesId() {
	}

	public OriginalImagesId(String name, String folderName) {
		this.name = name;
		this.folderName = folderName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OriginalImagesId that = (OriginalImagesId) o;
		return name.equals(that.name) &&
				folderName.equals(that.folderName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, folderName);
	}
}
