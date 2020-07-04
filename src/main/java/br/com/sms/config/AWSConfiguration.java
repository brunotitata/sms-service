package br.com.sms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
public class AWSConfiguration {

    @Value("${aws.accesskey}")
    private String accessKey;

    @Value("${aws.secretkey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Bean
    public AmazonSNS amazonSNS() {
	return AmazonSNSClientBuilder.standard().withRegion(Regions.fromName(region))
		.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
		.build();
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQS) {
	return new QueueMessagingTemplate(amazonSQS);
    }

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQS(AWSCredentialsProvider credentials) {
	return AmazonSQSAsyncClientBuilder.standard().withCredentials(credentials)
		.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
			"https://sqs.sa-east-1.amazonaws.com/856478300339/sms-payment-send", "sa-east-1"))
		.build();
    }

    @Bean
    @Primary
    public AWSCredentialsProvider awsCredentialsProvider() {
	return new AWSCredentialsProviderChain(
		new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)));
    }

}
