package br.com.fiap.ez.fastfood.adapters.out.messaging;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.fiap.ez.fastfood.application.dto.PaymentPublisherRequestDTO;
import br.com.fiap.ez.fastfood.infrastructure.config.AmazonSQSProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class PaymentPublisherTest {

    @Mock
    private SqsClient sqsClient;

    @Mock
    private AmazonSQSProperties sqsProperties;

    @InjectMocks
    private PaymentPublisher paymentPublisher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPublishPaymentRequest() throws Exception {
        PaymentPublisherRequestDTO paymentRequest = new PaymentPublisherRequestDTO();
        paymentRequest.setOrderId(1L);
        paymentRequest.setUserId(2L);
        paymentRequest.setAmount(100.0);

        String queueUrl = "http://localhost:4566/queue/payment-queue";
        when(sqsProperties.getPaymentQueueUrl()).thenReturn(queueUrl);

        paymentPublisher.publishPaymentRequest(paymentRequest);

        String expectedMessageBody = new ObjectMapper().writeValueAsString(paymentRequest);
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(expectedMessageBody)
                .build();

        verify(sqsClient).sendMessage(sendMessageRequest);
    }

    @Test
    void testPublishPaymentRequestThrowsException() {
        PaymentPublisherRequestDTO paymentRequest = new PaymentPublisherRequestDTO();
        when(sqsProperties.getPaymentQueueUrl()).thenReturn("http://localhost:4566/queue/payment-queue");
        doThrow(new RuntimeException("SQS error")).when(sqsClient).sendMessage(any(SendMessageRequest.class));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            paymentPublisher.publishPaymentRequest(paymentRequest);
        });

        assertEquals("Failed to publish payment request to SQS", thrown.getMessage());
    }
}