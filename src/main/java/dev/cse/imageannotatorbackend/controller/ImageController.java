package dev.cse.imageannotatorbackend.controller;

import dev.cse.imageannotatorbackend.service.OriginalImagesService;
import dev.cse.imageannotatorbackend.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("**/images")
public class ImageController {

	private S3Service s3Service;
	private OriginalImagesService originalImagesService;

	@Autowired
	public ImageController(S3Service s3Service, OriginalImagesService originalImagesService) {
		this.s3Service = s3Service;
		this.originalImagesService = originalImagesService;
	}

	private File multipartToFileConverter(MultipartFile file) throws IOException {
		File convertedFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(file.getBytes());
		fos.close();
		return convertedFile;
	}

	@PostMapping("/upload")
	public Map<String, String[]> uploadImages(@RequestPart(value = "files") MultipartFile[] files, Authentication authentication) throws IOException {
		// Convert files from Multipart to File
		File[] filesConverted = new File[files.length];
		for (int i = 0; i < files.length; i++) {
			filesConverted[i] = multipartToFileConverter(files[i]);
		}

		// Upload files and create JSON response
		Map<String, String[]> response = new HashMap<>();
		String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the role of the user
		role = role.replace("ROLE_", "");
		String username = authentication.getName();
		String[] image_urls = s3Service.uploadImage(filesConverted, role, username); // Upload the file
		response.put("image-urls", image_urls);

		// Save image urls in database
		originalImagesService.addImages(username, image_urls);

		return response;
	}
}
