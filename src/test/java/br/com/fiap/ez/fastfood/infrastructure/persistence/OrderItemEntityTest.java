package br.com.fiap.ez.fastfood.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderItemEntityTest {

    @Test
    void testGettersAndSetters() {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setId(1L);
        orderItemEntity.setProductId(2L);
        orderItemEntity.setQuantity(3);
        orderItemEntity.setPrice(10.0);

        assertEquals(1L, orderItemEntity.getId());
        assertEquals(2L, orderItemEntity.getProductId());
        assertEquals(3, orderItemEntity.getQuantity());
        assertEquals(10.0, orderItemEntity.getPrice());
    }

    @Test
    void testConstructor() {
        OrderEntity orderEntity = new OrderEntity();
        OrderItemEntity orderItemEntity = new OrderItemEntity(1L, orderEntity, 2L, 3, 10.0);

        assertEquals(1L, orderItemEntity.getId());
        assertEquals(orderEntity, orderItemEntity.getOrder());
        assertEquals(2L, orderItemEntity.getProductId());
        assertEquals(3, orderItemEntity.getQuantity());
        assertEquals(10.0, orderItemEntity.getPrice());
    }

    @Test
    void testSetOrder() {
        OrderEntity orderEntity = new OrderEntity();
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrder(orderEntity);

        assertEquals(orderEntity, orderItemEntity.getOrder());
    }
}