package br.com.fiap.ez.fastfood.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;



@Configuration
public class AmazonSQSConfig {

	 private final AmazonSQSProperties amazonSQSProperties;

	    public AmazonSQSConfig(AmazonSQSProperties amazonSQSProperties) {
	        this.amazonSQSProperties = amazonSQSProperties;
	       
	    }
	    
	    @Bean
	    public SqsClient sqsClient() {
	        return SqsClient.builder()
	                .region(Region.of(amazonSQSProperties.getRegion())) 
	                .credentialsProvider(StaticCredentialsProvider.create(
	                        AwsBasicCredentials.create(amazonSQSProperties.getAccessKey(), amazonSQSProperties.getSecretKey())
	                )) 
	                .build();
	    }
	 

	 
}
