package dev.cse.imageannotatorbackend.controller;

import dev.cse.imageannotatorbackend.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/images")
public class ImageController {

	private S3Service s3Service;

	@Autowired
	public ImageController(S3Service s3Service) {
		this.s3Service = s3Service;
	}

	private File multipartToFileConverter(MultipartFile file) throws IOException {
		File convertedFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(file.getBytes());
		fos.close();
		return convertedFile;
	}

	@PostMapping("/upload")
	public Map<String, String[]> uploadImages(@RequestPart(value = "images") MultipartFile[] files) throws IOException {
		File[] filesConverted = new File[files.length];
		for (int i = 0; i < files.length; i++) {
			filesConverted[i] = multipartToFileConverter(files[i]);
		}
		Map<String, String[]> response = new HashMap<>();
		response.put("image-url", s3Service.uploadImage(filesConverted));
		return response;
	}
}
