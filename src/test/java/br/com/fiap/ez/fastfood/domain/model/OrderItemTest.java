package br.com.fiap.ez.fastfood.domain.model;

import br.com.fiap.ez.fastfood.infrastructure.mapper.OrderItemMapper;
import br.com.fiap.ez.fastfood.infrastructure.persistence.OrderItemEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderItemTest {

    private Order order;
    private OrderItem orderItem;

    @BeforeEach
    public void setUp() {
        order = new Order();
        orderItem = new OrderItem(order, 1L, 2, 10.0);
    }

    @Test
    public void testDefaultConstructor() {
        OrderItem defaultOrderItem = new OrderItem();
        Assertions.assertNull(defaultOrderItem.getId());
        Assertions.assertNull(defaultOrderItem.getOrder());
        Assertions.assertNull(defaultOrderItem.getProductId());
        Assertions.assertNull(defaultOrderItem.getQuantity());
        Assertions.assertNull(defaultOrderItem.getPrice());
    }

    @Test
    public void testParameterizedConstructor() {
        Assertions.assertEquals(order, orderItem.getOrder());
        Assertions.assertEquals(1L, orderItem.getProductId());
        Assertions.assertEquals(2, orderItem.getQuantity());
        Assertions.assertEquals(10.0, orderItem.getPrice());
    }

    @Test
    public void testGettersAndSetters() {
        orderItem.setId(1L);
        Assertions.assertEquals(1L, orderItem.getId());

        Order newOrder = new Order();
        orderItem.setOrder(newOrder);
        Assertions.assertEquals(newOrder, orderItem.getOrder());

        orderItem.setProductId(2L);
        Assertions.assertEquals(2L, orderItem.getProductId());

        orderItem.setQuantity(3);
        Assertions.assertEquals(3, orderItem.getQuantity());

        orderItem.setPrice(20.0);
        Assertions.assertEquals(20.0, orderItem.getPrice());
    }

    @Test
    public void testGetTotalPrice() {
        Assertions.assertEquals(20.0, orderItem.getTotalPrice());
    }

    @Test
    void testEntityToDomainList() {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setProductId(1L);
        orderItemEntity.setQuantity(2);
        orderItemEntity.setPrice(10.0);

        List<OrderItemEntity> orderItemEntities = Collections.singletonList(orderItemEntity);
        List<OrderItem> orderItems = OrderItemMapper.entityToDomain(orderItemEntities);

        assertEquals(1, orderItems.size());
        OrderItem orderItem = orderItems.get(0);
        assertEquals(orderItemEntity.getProductId(), orderItem.getProductId());
        assertEquals(orderItemEntity.getQuantity(), orderItem.getQuantity());
        assertEquals(orderItemEntity.getPrice(), orderItem.getPrice());
    }

    @Test
    void testDomainToEntityList() {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(1L);
        orderItem.setQuantity(2);
        orderItem.setPrice(10.0);

        List<OrderItem> orderItems = Collections.singletonList(orderItem);
        List<OrderItemEntity> orderItemEntities = OrderItemMapper.domainToEntity(orderItems);

        assertEquals(1, orderItemEntities.size());
        OrderItemEntity orderItemEntity = orderItemEntities.get(0);
        assertEquals(orderItem.getProductId(), orderItemEntity.getProductId());
        assertEquals(orderItem.getQuantity(), orderItemEntity.getQuantity());
        assertEquals(orderItem.getPrice(), orderItemEntity.getPrice());
    }
}