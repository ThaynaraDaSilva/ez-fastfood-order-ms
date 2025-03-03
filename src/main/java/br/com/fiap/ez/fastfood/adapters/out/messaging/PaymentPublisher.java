package br.com.fiap.ez.fastfood.adapters.out.messaging;

import org.springframework.stereotype.Service;


import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.ez.fastfood.application.dto.PaymentPublisherRequestDTO;
import br.com.fiap.ez.fastfood.infrastructure.config.AmazonSQSProperties;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;



@Service
public class PaymentPublisher {
	
	private final SqsClient sqsClient;
    private final AmazonSQSProperties sqsProperties;

    public PaymentPublisher(SqsClient sqsClient, AmazonSQSProperties sqsProperties) {
        this.sqsClient = sqsClient;
        this.sqsProperties = sqsProperties;
        
    }

    @Retryable (value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 1000)) //1seg de intervalo
    public void publishPaymentRequest(PaymentPublisherRequestDTO paymentRequest) {
        try {
            String messageBody = new ObjectMapper().writeValueAsString(paymentRequest);
            System.out.println("#################### ANTES DE SAVE MESSAGE ON PUBLISH PAYMENT ##################");
            SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                    .queueUrl(sqsProperties.getPaymentQueueUrl()) // Use the property from the config class
                    .messageBody(messageBody)
                    .build();
            sqsClient.sendMessage(sendMessageRequest);
            System.out.println("#################### POS SEND MESSAGE ON PUBLISH PAYMENT ##################");
        } catch (Exception e) {
            throw new RuntimeException("Failed to publish payment request to SQS", e);
        }
    }
    
    

}
