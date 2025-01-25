package br.com.fiap.ez.fastfood.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws.sqs")
public class AmazonSQSProperties {

	private String paymentQueueUrl;
    private String paymentResultQueueUrl;
    private String region;
    private String accessKey;
    private String secretKey;


    // Getters and Setters
    public String getPaymentQueueUrl() {
        return paymentQueueUrl;
    }

    public void setPaymentQueueUrl(String paymentQueueUrl) {
        this.paymentQueueUrl = paymentQueueUrl;
    }

    public String getPaymentResultQueueUrl() {
        return paymentResultQueueUrl;
    }

    public void setPaymentResultQueueUrl(String paymentResultQueueUrl) {
        this.paymentResultQueueUrl = paymentResultQueueUrl;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    
}
