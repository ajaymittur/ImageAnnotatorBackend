package dev.cse.imageannotatorbackend.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

	AmazonS3 s3Client;
	@Value("${AWS_S3_BUCKET}")
	String bucketName;
	@Value("https://s3.us-east-1.amazonaws.com")
	String awsS3Endpoint;

	@Autowired
	public S3Service(AmazonS3 s3Client) {
		this.s3Client = s3Client;
	}

	public String uploadImage(File file) {
		String uploadedUrl = "";
		try {
			s3Client.putObject(new PutObjectRequest(bucketName, file.getName(), file)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			uploadedUrl = awsS3Endpoint + '/' + bucketName + '/' + file.getName();
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
		}
		file.delete(); // remove file created in server to prevent memory overflow
		return uploadedUrl;
	}

	public String[] uploadImage(File[] files) {
		String[] uploadedUrls = new String[files.length];
		try {
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				s3Client.putObject(new PutObjectRequest(bucketName, file.getName(), file)
						.withCannedAcl(CannedAccessControlList.PublicRead));
				uploadedUrls[i] = awsS3Endpoint + '/' + bucketName + '/' + file.getName();
				file.delete(); // remove file created in server to prevent memory overflow
			}
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
		}
		return uploadedUrls;
	}
}
