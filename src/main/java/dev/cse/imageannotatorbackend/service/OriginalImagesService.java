package dev.cse.imageannotatorbackend.service;

import dev.cse.imageannotatorbackend.model.OriginalImages;
import dev.cse.imageannotatorbackend.model.Users;
import dev.cse.imageannotatorbackend.model.resource.UserImage;
import dev.cse.imageannotatorbackend.repository.OriginalImagesRepository;
import dev.cse.imageannotatorbackend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OriginalImagesService {

	private OriginalImagesRepository originalImagesRepository;
	private UsersRepository usersRepository;

	@Autowired
	public OriginalImagesService(OriginalImagesRepository originalImagesRepository, UsersRepository usersRepository) {
		this.originalImagesRepository = originalImagesRepository;
		this.usersRepository = usersRepository;
	}

	public void addImages(String username, String[] imageUrls, String[] categories, String[] tags) {
		Optional<Users> user = usersRepository.findByUsername(username);
		Set<String> categoriesSet = new HashSet<>(Arrays.asList(categories));
		Set<String> tagsSet = new HashSet<>(Arrays.asList(tags));
		user.ifPresent((usr) -> {
			for (String url : imageUrls) {
				// Parse name (Image Name) and folderName from image url
				String[] urlSplit = url.split("/");
				int urlSplitLength = urlSplit.length;
				String name = urlSplit[urlSplitLength - 1];
				String folderName = urlSplit[urlSplitLength - 2];

				OriginalImages ogImg = new OriginalImages(usr, name, folderName, url, categoriesSet, tagsSet);
				originalImagesRepository.save(ogImg);
			}
		});
	}

	public List<UserImage> getImages(String username) {
		List<OriginalImages> images =  originalImagesRepository.findByUserUsername(username);
		List<UserImage> userImages = new ArrayList<>();

		for (OriginalImages img : images) {
			userImages.add(new UserImage(img));
		}

		return userImages;
	}

	public List<UserImage> getUnAnnotatedImages() {
		List<OriginalImages> images =  originalImagesRepository.findAllUnAnnotatedImages();;
		List<UserImage> userImages = new ArrayList<>();

		for (OriginalImages img : images) {
			userImages.add(new UserImage(img));
		}

		return userImages;
	}

	public List<UserImage> getUnAnnotatedImages(String username) {
		List<OriginalImages> images =  originalImagesRepository.findUnAnnotatedImagesByUsername(username);;
		List<UserImage> userImages = new ArrayList<>();

		for (OriginalImages img : images) {
			userImages.add(new UserImage(img));
		}

		return userImages;
	}
}
