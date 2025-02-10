package br.com.fiap.ez.fastfood.adapters.out.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.infrastructure.mapper.OrderMapper;
import br.com.fiap.ez.fastfood.infrastructure.persistence.OrderEntity;

class OrderRepositoryImplTest {

    @Mock
    private JpaOrderRepository jpaOrderRepository;

    @InjectMocks
    private OrderRepositoryImpl orderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Order order = new Order();
        order.setOrderTime(ZonedDateTime.now());
        order.setOrderItems(new ArrayList<>());
        OrderEntity orderEntity = OrderMapper.domainToEntity(order);

        when(jpaOrderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        Order savedOrder = orderRepository.save(order);

        assertNotNull(savedOrder);
        verify(jpaOrderRepository).save(any(OrderEntity.class));
    }

    @Test
    void testFindAll() {
        OrderEntity orderEntity1 = new OrderEntity();
        OrderEntity orderEntity2 = new OrderEntity();
        List<OrderEntity> orderEntities = Arrays.asList(orderEntity1, orderEntity2);

        when(jpaOrderRepository.findAll()).thenReturn(orderEntities);

        List<Order> orders = orderRepository.findAll();

        assertNotNull(orders);
        assertEquals(2, orders.size());
        verify(jpaOrderRepository).findAll();
    }

    @Test
    void testFindOrderById() {
        Long orderId = 1L;
        OrderEntity orderEntity = new OrderEntity();

        when(jpaOrderRepository.findById(orderId)).thenReturn(Optional.of(orderEntity));

        Order foundOrder = orderRepository.findOrderById(orderId);

        assertNotNull(foundOrder);
        verify(jpaOrderRepository).findById(orderId);
    }

    @Test
    void testFindOrderByIdNotFound() {
        Long orderId = 1L;

        when(jpaOrderRepository.findById(orderId)).thenReturn(Optional.empty());

        Order foundOrder = orderRepository.findOrderById(orderId);

        assertNull(foundOrder);
        verify(jpaOrderRepository).findById(orderId);
    }

    @Test
    void testListUnfinishedOrders() {
        OrderEntity orderEntity1 = new OrderEntity();
        OrderEntity orderEntity2 = new OrderEntity();
        List<OrderEntity> orderEntities = Arrays.asList(orderEntity1, orderEntity2);

        when(jpaOrderRepository.listUnfinishedOrders()).thenReturn(orderEntities);

        List<Order> unfinishedOrders = orderRepository.listUnfinishedOrders();

        assertNotNull(unfinishedOrders);
        assertEquals(2, unfinishedOrders.size());
        verify(jpaOrderRepository).listUnfinishedOrders();
    }

    @Test
    void testFindLastOrder() {
        OrderEntity orderEntity = new OrderEntity();

        when(jpaOrderRepository.findLastOrder()).thenReturn(orderEntity);

        Order lastOrder = orderRepository.findLastOrder();

        assertNotNull(lastOrder);
        verify(jpaOrderRepository).findLastOrder();
    }

    @Test
    void testFindLastOrderNotFound() {
        when(jpaOrderRepository.findLastOrder()).thenReturn(null);

        Order lastOrder = orderRepository.findLastOrder();

        assertNull(lastOrder);
        verify(jpaOrderRepository).findLastOrder();
    }

    @Test
    void testListUnCompletedOrders() {
        OrderEntity orderEntity1 = new OrderEntity();
        OrderEntity orderEntity2 = new OrderEntity();
        List<OrderEntity> orderEntities = Arrays.asList(orderEntity1, orderEntity2);

        when(jpaOrderRepository.listUncompletedOrders()).thenReturn(orderEntities);

        List<Order> uncompletedOrders = orderRepository.listUnCompletedOrders();

        assertNotNull(uncompletedOrders);
        assertEquals(2, uncompletedOrders.size());
        verify(jpaOrderRepository).listUncompletedOrders();
    }
}