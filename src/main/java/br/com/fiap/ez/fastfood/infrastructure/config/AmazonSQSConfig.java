package br.com.fiap.ez.fastfood.infrastructure.config;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;


@Configuration
public class AmazonSQSConfig {

	 private final AmazonSQSProperties amazonSQSProperties;

	    public AmazonSQSConfig(AmazonSQSProperties amazonSQSProperties) {
	        this.amazonSQSProperties = amazonSQSProperties;
	       
	    }
	    @Bean
	    public SqsAsyncClient sqsAsyncClient() {
	        return SqsAsyncClient.builder()
	                .region(Region.of(amazonSQSProperties.getRegion()))
	                .credentialsProvider(DefaultCredentialsProvider.create()) // Usa credenciais padrão da AWS
	                .build();
	    }

	 
}
