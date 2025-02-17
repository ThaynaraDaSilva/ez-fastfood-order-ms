package br.com.fiap.ez.fastfood.infrastructure.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
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
	    
	    /*@Bean
	    public SqsClient sqsClient() {
	        return SqsClient.builder()
	                .region(Region.of(amazonSQSProperties.getRegion())) // Mantendo a região configurável
	                .credentialsProvider(DefaultCredentialsProvider.create()) // Usa credenciais do ambiente AWS automaticamente
	                .build();
	    }*/
	    @Bean
	    public SqsClient sqsClient() {
	        return SqsClient.builder()
	                .region(Region.of(amazonSQSProperties.getRegion())) 
	                .credentialsProvider(StaticCredentialsProvider.create(
	                        AwsBasicCredentials.create(amazonSQSProperties.getAccessKey(), amazonSQSProperties.getSecretKey())
	                )) //.endpointOverride(URI.create("http://localhost:4566")) // Endpoint do LocalStack
	                .build();
	    }
	 

	 
}
