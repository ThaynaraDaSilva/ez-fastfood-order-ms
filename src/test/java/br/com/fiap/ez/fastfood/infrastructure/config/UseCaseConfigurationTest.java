package br.com.fiap.ez.fastfood.infrastructure.config;

import static org.junit.jupiter.api.Assertions.*;

import br.com.fiap.ez.fastfood.adapters.out.http.CatalogHttpClient;
import br.com.fiap.ez.fastfood.adapters.out.http.UserHttpClient;
import br.com.fiap.ez.fastfood.adapters.out.messaging.PaymentPublisher;
import br.com.fiap.ez.fastfood.application.usecases.OrderUseCase;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UseCaseConfigurationTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CatalogHttpClient catalogHttpClient;

    @Mock
    private UserHttpClient userHttpClient;

    @Mock
    private PaymentPublisher paymentPublisher;

    @InjectMocks
    private UseCaseConfiguration useCaseConfiguration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOrderUseCaseBean() {
        OrderUseCase orderUseCase = useCaseConfiguration.orderUseCase(orderRepository, catalogHttpClient, userHttpClient, paymentPublisher);

        assertNotNull(orderUseCase);

        assertTrue(orderUseCase instanceof OrderUseCase);
    }
}