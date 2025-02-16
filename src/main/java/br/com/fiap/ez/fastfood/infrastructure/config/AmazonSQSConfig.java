package br.com.fiap.ez.fastfood.infrastructure.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;



@Configuration
public class AmazonSQSConfig {

	 private final AmazonSQSProperties amazonSQSProperties;

	    public AmazonSQSConfig(AmazonSQSProperties amazonSQSProperties) {
	        this.amazonSQSProperties = amazonSQSProperties;
	       
	    }
	    
	    @Bean
	    public SqsClient sqsClient() {
	        return SqsClient.builder()
	                .region(Region.of(amazonSQSProperties.getRegion())) // Mantendo a região configurável
	                .credentialsProvider(DefaultCredentialsProvider.create()) // Usa credenciais do ambiente AWS automaticamente
	                .build();
	    }

	 
}
