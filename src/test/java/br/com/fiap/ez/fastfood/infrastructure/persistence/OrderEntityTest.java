package br.com.fiap.ez.fastfood.infrastructure.persistence;

import br.com.fiap.ez.fastfood.domain.model.OrderStatus;
import org.junit.jupiter.api.Test;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderEntityTest {

    @Test
    void testGettersAndSetters() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setOrderNumber("ORD-001");
        orderEntity.setUserId(2L);
        orderEntity.setUserName("John Doe");
        orderEntity.setOrderTime(ZonedDateTime.now());
        orderEntity.setCompletedTime(ZonedDateTime.now().plusHours(1));
        orderEntity.setTotalPrice(100.0);
        orderEntity.setStatus(OrderStatus.RECEIVED);

        assertEquals(1L, orderEntity.getId());
        assertEquals("ORD-001", orderEntity.getOrderNumber());
        assertEquals(2L, orderEntity.getUserId());
        assertEquals("John Doe", orderEntity.getUserName());
        assertNotNull(orderEntity.getOrderTime());
        assertNotNull(orderEntity.getCompletedTime());
        assertEquals(100.0, orderEntity.getTotalPrice());
        assertEquals(OrderStatus.RECEIVED, orderEntity.getStatus());
    }

    @Test
    void testAddOrderItem() {
        OrderEntity orderEntity = new OrderEntity();
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setProductId(1L);
        orderItemEntity.setQuantity(2);
        orderItemEntity.setPrice(10.0);

        orderEntity.addOrderItem(orderItemEntity);

        assertEquals(1, orderEntity.getOrderItems().size());
        assertEquals(orderItemEntity, orderEntity.getOrderItems().get(0));
        assertEquals(orderEntity, orderItemEntity.getOrder());
    }

    @Test
    void testConstructor() {
        List<OrderItemEntity> orderItems = new ArrayList<>();
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setProductId(1L);
        orderItemEntity.setQuantity(2);
        orderItemEntity.setPrice(10.0);
        orderItems.add(orderItemEntity);

        OrderEntity orderEntity = new OrderEntity(1L, "ORD-001", 2L, "John Doe", ZonedDateTime.now(), ZonedDateTime.now().plusHours(1), 100.0, OrderStatus.RECEIVED, orderItems);

        assertEquals(1L, orderEntity.getId());
        assertEquals("ORD-001", orderEntity.getOrderNumber());
        assertEquals(2L, orderEntity.getUserId());
        assertEquals("John Doe", orderEntity.getUserName());
        assertNotNull(orderEntity.getOrderTime());
        assertNotNull(orderEntity.getCompletedTime());
        assertEquals(100.0, orderEntity.getTotalPrice());
        assertEquals(OrderStatus.RECEIVED, orderEntity.getStatus());
        assertEquals(1, orderEntity.getOrderItems().size());
        assertEquals(orderItemEntity, orderEntity.getOrderItems().get(0));
    }
}