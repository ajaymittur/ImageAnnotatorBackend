package dev.cse.imageannotatorbackend.controller;

import dev.cse.imageannotatorbackend.service.AnnotatedImagesService;
import dev.cse.imageannotatorbackend.service.OriginalImagesService;
import dev.cse.imageannotatorbackend.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("**/images")
public class ImageController {

	private S3Service s3Service;
	private OriginalImagesService originalImagesService;
	private AnnotatedImagesService annotatedImagesService;

	@Autowired
	public ImageController(S3Service s3Service, OriginalImagesService originalImagesService, AnnotatedImagesService annotatedImagesService) {
		this.s3Service = s3Service;
		this.originalImagesService = originalImagesService;
		this.annotatedImagesService = annotatedImagesService;
	}

	private File multipartToFileConverter(MultipartFile file) throws IOException {
		File convertedFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(file.getBytes());
		fos.close();
		return convertedFile;
	}

	private String getRoleFromAuth(Authentication authentication) {
		String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the role of the user
		role = role.replace("ROLE_", "");
		return role;
	}

	@PostMapping("/upload")
	public Map<String, String[]> uploadImages(@RequestPart(value = "files") MultipartFile[] files, @RequestParam String[] categories, Authentication authentication) throws IOException {
		// Convert files from Multipart to File
		File[] filesConverted = new File[files.length];
		for (int i = 0; i < files.length; i++) {
			filesConverted[i] = multipartToFileConverter(files[i]);
		}

		// Upload files and create JSON response
		Map<String, String[]> response = new HashMap<>();
		String role = getRoleFromAuth(authentication);
		String username = authentication.getName();
		String[] image_urls = s3Service.uploadImage(filesConverted, role, username); // Upload the file
		response.put("image-urls", image_urls);

		// Save image urls in database
		if (role.equals("USER")) {
			originalImagesService.addImages(username, image_urls);
		} else if (role.equals("ANNOTATOR")) {
			for (String cat : categories) {
				System.out.println(cat);
			}
			annotatedImagesService.addImages(username, image_urls, categories);
		}

		return response;
	}

	@GetMapping("/get-all-images")
	// TODO: Return JSON Object with image-urls: [urls] and {categories}:{category} if required to allow annotator images to also be returned
	public List<String> getImages(Authentication authentication) {
		String role = getRoleFromAuth(authentication);
		List<String> response = null;

		if (role.equals("USER")) {
			response = originalImagesService.getImages(authentication.getName());
		} else if (role.equals("ANNOTATOR")) {
			// TODO: Return category as well
			response = annotatedImagesService.getImages(authentication.getName());
		}

		return response;
	}
}
