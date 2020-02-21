package br.com.sms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Configuration
public class AWSConfiguration {

    @Value("${aws.accesskey}")
    private String accessKey;

    @Value("${aws.secretkey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Bean
    public AmazonSNS aws() {
	return AmazonSNSClientBuilder.standard().withRegion(Regions.fromName(region))
		.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
		.build();
    }

}
