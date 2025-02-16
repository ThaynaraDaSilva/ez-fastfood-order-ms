
package br.com.fiap.ez.fastfood.infrastructure.config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.services.sqs.SqsClient;

public class AmazonSQSConfigTest {

	@Mock
	private AmazonSQSProperties amazonSQSProperties;

	@InjectMocks
	private AmazonSQSConfig amazonSQSConfig;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSqsClient() {
		when(amazonSQSProperties.getRegion()).thenReturn("us-east-1");


		SqsClient sqsClient = amazonSQSConfig.sqsClient();

		assertNotNull(sqsClient);
	}
}
