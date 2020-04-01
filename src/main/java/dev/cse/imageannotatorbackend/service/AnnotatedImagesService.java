package dev.cse.imageannotatorbackend.service;

import dev.cse.imageannotatorbackend.model.AnnotatedImages;
import dev.cse.imageannotatorbackend.model.Annotators;
import dev.cse.imageannotatorbackend.repository.AnnotatedImagesRepository;
import dev.cse.imageannotatorbackend.repository.AnnotatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnnotatedImagesService {

	private AnnotatedImagesRepository annotatedImagesRepository;
	private AnnotatorsRepository annotatorsRepository;

	@Autowired
	public AnnotatedImagesService(AnnotatedImagesRepository annotatedImagesRepository, AnnotatorsRepository annotatorsRepository) {
		this.annotatedImagesRepository = annotatedImagesRepository;
		this.annotatorsRepository = annotatorsRepository;
	}

	// TODO: Complete functionality
	public void addImages(String username, String[] imageUrls, String[] categories) {
		Optional<Annotators> annotator = annotatorsRepository.findByUsername(username);
		annotator.ifPresent((annot) -> {
			for (int i = 0; i < imageUrls.length; i++) {
				// Parse name (Image Name) and folderName from image url
				String[] urlSplit = imageUrls[i].split("/");
				int urlSplitLength = urlSplit.length;
				String name = urlSplit[urlSplitLength - 1];
				String folderName = urlSplit[urlSplitLength - 2];

				AnnotatedImages annotImg = new AnnotatedImages(annot, name, folderName, imageUrls[i], categories[i]);
				annotatedImagesRepository.save(annotImg);
			}
		});
	}

	public List<String> getImages(String username) {
		List<AnnotatedImages> images =  annotatedImagesRepository.findByAnnotatorUsername(username);
		List<String> imageUrls = new ArrayList<String>();
		for (AnnotatedImages img : images) {
			imageUrls.add(img.getUrl());
		}
		return imageUrls;
	}
}
