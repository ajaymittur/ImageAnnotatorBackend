package dev.cse.imageannotatorbackend.controller;

import dev.cse.imageannotatorbackend.model.resource.Image;
import dev.cse.imageannotatorbackend.service.AnnotatedImagesService;
import dev.cse.imageannotatorbackend.service.ImageUtilsService;
import dev.cse.imageannotatorbackend.service.OriginalImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("**/images")
public class ImageController {

	private ImageUtilsService imageUtilsService;
	private OriginalImagesService originalImagesService;
	private AnnotatedImagesService annotatedImagesService;

	@Autowired
	public ImageController(ImageUtilsService imageUtilsService, OriginalImagesService originalImagesService, AnnotatedImagesService annotatedImagesService) {
		this.imageUtilsService = imageUtilsService;
		this.originalImagesService = originalImagesService;
		this.annotatedImagesService = annotatedImagesService;
	}

	@PostMapping("/upload")
	public Map<String, String[]> uploadImages(@RequestPart(value = "files") MultipartFile[] files, @RequestParam String[] categories, @RequestParam(required = false) String[] tags, Authentication authentication) throws IOException {
		/*
			For USER: Categories is the possible labels of the images
			For ANNOTATOR: Categories is the label of corresponding image in the files array
		*/

		// Convert files from Multipart to File
		File[] filesConverted = new File[files.length];
		for (int i = 0; i < files.length; i++) {
			filesConverted[i] = imageUtilsService.multipartToFileConverter(files[i]);
		}

		// Upload files and create JSON response
		Map<String, String[]> response = new HashMap<>();
		String[] image_urls = imageUtilsService.uploadFilesAndGetUrls(filesConverted, authentication);
		response.put("image-urls", image_urls);

		// Save images along with related data in database
		String role = imageUtilsService.getRoleFromAuth(authentication);
		if (role.equals("USER")) {
			originalImagesService.addImages(authentication.getName(), image_urls, categories, tags);
		} else if (role.equals("ANNOTATOR")) {
			annotatedImagesService.addImages(authentication.getName(), image_urls, categories);
		}

		return response;
	}

	@GetMapping("/get-all-images")
	public List<? extends Image> getImages(Authentication authentication) {
		String role = imageUtilsService.getRoleFromAuth(authentication);
		List<? extends Image> response = null;

		if (role.equals("USER")) {
			response = originalImagesService.getImages(authentication.getName());
		} else if (role.equals("ANNOTATOR")) {
			response = annotatedImagesService.getImages(authentication.getName());
		}

		return response;
	}

    @GetMapping("/get-annotated-images")
    public List<? extends Image> getAnnotatedImages(Authentication authentication) {
        String role = imageUtilsService.getRoleFromAuth(authentication);
        List<? extends Image> response = null;

        if (role.equals("USER")) {
            response = annotatedImagesService.getImagesForUser(authentication.getName());
        } else if (role.equals("ANNOTATOR")) {
            response = annotatedImagesService.getImages(authentication.getName());
        }

        return response;
    }

	@GetMapping("/get-unannotated-images")
	public List<? extends Image> getUnAnnotatedImages(Authentication authentication) {
		String role = imageUtilsService.getRoleFromAuth(authentication);
		List<? extends Image> response = null;

		if (role.equals("USER")) {
			response = originalImagesService.getUnAnnotatedImages(authentication.getName());
		} else if (role.equals("ANNOTATOR")) {
			response = originalImagesService.getUnAnnotatedImages();
		}

		return response;
	}
}
