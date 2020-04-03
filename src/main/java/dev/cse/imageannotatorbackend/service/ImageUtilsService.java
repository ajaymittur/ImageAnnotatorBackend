package dev.cse.imageannotatorbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ImageUtilsService {

	private S3Service s3Service;

	@Autowired
	public ImageUtilsService(S3Service s3Service) {
		this.s3Service = s3Service;
	}

	public File multipartToFileConverter(MultipartFile file) throws IOException {
		File convertedFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(file.getBytes());
		fos.close();
		return convertedFile;
	}

	public String getRoleFromAuth(Authentication authentication) {
		String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the role of the user
		role = role.replace("ROLE_", "");
		return role;
	}

	public String[] uploadFilesAndGetUrls(File[] files, Authentication authentication) {
		String role = getRoleFromAuth(authentication);
		String username = authentication.getName();
		return s3Service.uploadImage(files, role, username); // Upload the file
	}
}