package br.com.fiap.ez.fastfood.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AmazonSQSConfig {

	 private final AmazonSQSProperties amazonSQSProperties;
	 
	 public AmazonSQSConfig(AmazonSQSProperties amazonSQSProperties) {
	        this.amazonSQSProperties = amazonSQSProperties;
	    }

	    @Bean
	    public SqsClient sqsClient() {
	        return SqsClient.builder()
	                .region(Region.of(amazonSQSProperties.getRegion())) // Usa a região das propriedades
	                .build();
	    }
	 
}
