package br.com.fiap.ez.fastfood.infrastructure.config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.ez.fastfood.adapters.out.repository.JpaOrderRepository;
import br.com.fiap.ez.fastfood.adapters.out.repository.OrderRepositoryImpl;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RepositoryConfigurationTest {

    @Mock
    private JpaOrderRepository orderJpaRepository;

    @InjectMocks
    private RepositoryConfiguration repositoryConfiguration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOrderRepositoryBean() {
        OrderRepository orderRepository = repositoryConfiguration.orderRepository(orderJpaRepository);

        assertNotNull(orderRepository);
        assertTrue(orderRepository instanceof OrderRepositoryImpl);
    }
}