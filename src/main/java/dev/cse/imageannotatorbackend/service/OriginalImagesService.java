package dev.cse.imageannotatorbackend.service;

import dev.cse.imageannotatorbackend.model.OriginalImages;
import dev.cse.imageannotatorbackend.model.Users;
import dev.cse.imageannotatorbackend.repository.OriginalImagesRepository;
import dev.cse.imageannotatorbackend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OriginalImagesService {

	private OriginalImagesRepository originalImagesRepository;
	private UsersRepository usersRepository;

	@Autowired
	public OriginalImagesService(OriginalImagesRepository originalImagesRepository, UsersRepository usersRepository) {
		this.originalImagesRepository = originalImagesRepository;
		this.usersRepository = usersRepository;
	}

	public void addImages(String username, String[] imageUrls) {
		Optional<Users> user = usersRepository.findByUsername(username);
		user.ifPresent((usr) -> {
			for (String url : imageUrls) {
				// Parse name (Image Name) and folderName from image url
				String[] urlSplit = url.split("/");
				int urlSplitLength = urlSplit.length;
				String name = urlSplit[urlSplitLength - 1];
				String folderName = urlSplit[urlSplitLength - 2];

				OriginalImages ogImg = new OriginalImages(usr, name, folderName, url);
				originalImagesRepository.save(ogImg);
			}
		});
	}

	// TODO: Test and complete functionality
	public List<String> getImages(String username) {
		List<OriginalImages> images =  originalImagesRepository.findByUserUsername(username);
		List<String> imageUrls = new ArrayList<String>();
		for (OriginalImages img : images) {
			imageUrls.add(img.getUrl());
		}
		return imageUrls;
	}
}
