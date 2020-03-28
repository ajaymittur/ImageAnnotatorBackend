package dev.cse.imageannotatorbackend.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

	@Value("${AWS_ACCESS_KEY_ID}")
	private String accessKey;
	@Value("${AWS_SECRET_ACCESS_KEY}")
	private String secretAccessKey;
	@Value("${AWS_SESSION_TOKEN}")
	private String sessionToken;

	@Bean
	public AmazonS3 s3Client() {
		BasicSessionCredentials sessionCredentials = new BasicSessionCredentials(accessKey, secretAccessKey, sessionToken);

		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(sessionCredentials))
				.build();
	}
}
