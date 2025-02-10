package br.com.fiap.ez.fastfood.infrastructure.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AmazonSQSPropertiesTest {

    @Test
    void testGettersAndSetters() {
        AmazonSQSProperties properties = new AmazonSQSProperties();

        properties.setPaymentQueueUrl("https://sqs.us-east-1.amazonaws.com/123456789012/payment-queue");
        properties.setPaymentResultQueueUrl("https://sqs.us-east-1.amazonaws.com/123456789012/payment-result-queue");
        properties.setRegion("us-east-1");
        properties.setAccessKey("testAccessKey");
        properties.setSecretKey("testSecretKey");

        assertEquals("https://sqs.us-east-1.amazonaws.com/123456789012/payment-queue", properties.getPaymentQueueUrl());
        assertEquals("https://sqs.us-east-1.amazonaws.com/123456789012/payment-result-queue", properties.getPaymentResultQueueUrl());
        assertEquals("us-east-1", properties.getRegion());
        assertEquals("testAccessKey", properties.getAccessKey());
        assertEquals("testSecretKey", properties.getSecretKey());
    }
}