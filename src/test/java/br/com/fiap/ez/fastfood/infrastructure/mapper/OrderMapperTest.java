package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.domain.model.OrderItem;
import br.com.fiap.ez.fastfood.domain.model.OrderStatus;
import br.com.fiap.ez.fastfood.infrastructure.persistence.OrderEntity;
import br.com.fiap.ez.fastfood.infrastructure.persistence.OrderItemEntity;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {

    @Test
    void testEntityToDomain() {
        OrderEntity entity = new OrderEntity();
        entity.setId(1L);
        entity.setUserId(2L);
        entity.setUserName("John Doe");
        entity.setOrderNumber("ORD-001");
        entity.setOrderTime(ZonedDateTime.now());
        entity.setCompletedTime(ZonedDateTime.now());
        entity.setTotalPrice(100.0);
        entity.setStatus(OrderStatus.RECEIVED);

        OrderItemEntity itemEntity = new OrderItemEntity();
        itemEntity.setProductId(1L);
        itemEntity.setQuantity(2);
        entity.setOrderItems(Collections.singletonList(itemEntity));

        Order order = OrderMapper.entityToDomain(entity);

        assertEquals(entity.getId(), order.getId());
        assertEquals(entity.getUserId(), order.getUserId());
        assertEquals(entity.getUserName(), order.getUserName());
        assertEquals(entity.getOrderNumber(), order.getOrderNumber());
        assertEquals(entity.getTotalPrice(), order.getTotalPrice());
        assertEquals(entity.getStatus(), order.getStatus());
        assertEquals(1, order.getOrderItems().size());
        assertEquals(itemEntity.getProductId(), order.getOrderItems().get(0).getProductId());
        assertEquals(itemEntity.getQuantity(), order.getOrderItems().get(0).getQuantity());
    }

    @Test
    void testDomainToEntity() {
        Order order = new Order();
        order.setId(1L);
        order.setUserId(2L);
        order.setUserName("John Doe");
        order.setOrderNumber("ORD-001");
        order.setOrderTime(ZonedDateTime.now());
        order.setCompletedTime(ZonedDateTime.now());
        order.setTotalPrice(100.0);
        order.setStatus(OrderStatus.RECEIVED);

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(1L);
        orderItem.setQuantity(2);
        order.setOrderItems(Collections.singletonList(orderItem));

        OrderEntity entity = OrderMapper.domainToEntity(order);

        assertEquals(order.getId(), entity.getId());
        assertEquals(order.getUserId(), entity.getUserId());
        assertEquals(order.getUserName(), entity.getUserName());
        assertEquals(order.getOrderNumber(), entity.getOrderNumber());
        assertEquals(order.getTotalPrice(), entity.getTotalPrice());
        assertEquals(order.getStatus(), entity.getStatus());
        assertEquals(1, entity.getOrderItems().size());
        assertEquals(orderItem.getProductId(), entity.getOrderItems().get(0).getProductId());
        assertEquals(orderItem.getQuantity(), entity.getOrderItems().get(0).getQuantity());
    }

    @Test
    void testDomainToResponseDTO() {
        Order order = new Order();
        order.setId(1L);
        order.setOrderNumber("ORD-001");
        order.setUserName("John Doe");
        order.setOrderTime(ZonedDateTime.now());
        order.setTotalPrice(100.0);
        order.setStatus(OrderStatus.RECEIVED);

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(1L);
        orderItem.setQuantity(2);
        order.setOrderItems(Collections.singletonList(orderItem));

        OrderResponseDTO responseDTO = OrderMapper.domainToResponseDTO(order);

        assertEquals(order.getId(), responseDTO.getOrderId());
        assertEquals(order.getOrderNumber(), responseDTO.getOrderNumber());
        assertEquals(order.getUserName(), responseDTO.getUserName());
        assertEquals(order.getTotalPrice(), responseDTO.getTotalPrice());
        assertEquals(order.getStatus(), responseDTO.getOrderStatus());
        assertEquals(1, responseDTO.getOrderItems().size());
        assertEquals(orderItem.getProductId(), responseDTO.getOrderItems().get(0).getProductId());
        assertEquals(orderItem.getQuantity(), responseDTO.getOrderItems().get(0).getQuantity());
    }

    @Test
    void testEntityToOrderResponseDTO() {
        OrderEntity entity = new OrderEntity();
        entity.setId(1L);
        entity.setUserId(2L);
        entity.setUserName("John Doe");
        entity.setOrderNumber("ORD-001");
        entity.setOrderTime(ZonedDateTime.now());
        entity.setTotalPrice(100.0);
        entity.setStatus(OrderStatus.RECEIVED);

        OrderItemEntity itemEntity = new OrderItemEntity();
        itemEntity.setProductId(1L);
        itemEntity.setQuantity(2);
        entity.setOrderItems(Collections.singletonList(itemEntity));

        OrderResponseDTO responseDTO = OrderMapper.entityToOrderResponseDTO(entity);

        assertEquals(entity.getId(), responseDTO.getOrderId());
        assertEquals(entity.getUserId(), responseDTO.getUserId());
        assertEquals(entity.getUserName(), responseDTO.getUserName());
        assertEquals(entity.getTotalPrice(), responseDTO.getTotalPrice());
        assertEquals(entity.getStatus(), responseDTO.getOrderStatus());
        assertEquals(1, responseDTO.getOrderItems().size());
        assertEquals(itemEntity.getProductId(), responseDTO.getOrderItems().get(0).getProductId());
        assertEquals(itemEntity.getQuantity(), responseDTO.getOrderItems().get(0).getQuantity());
    }

    @Test
    void testEntityListToDomainList() {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setProductId(1L);
        orderItemEntity.setQuantity(2);
        orderItemEntity.setPrice(10.0);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setUserId(1L);
        orderEntity.setUserName("John Doe");
        orderEntity.setOrderNumber("ORD-001");
        orderEntity.setOrderTime(ZonedDateTime.now());
        orderEntity.setCompletedTime(ZonedDateTime.now().plusHours(1));
        orderEntity.setTotalPrice(20.0);
        orderEntity.setStatus(OrderStatus.RECEIVED);
        orderEntity.setOrderItems(Collections.singletonList(orderItemEntity));

        List<OrderEntity> orderEntities = Collections.singletonList(orderEntity);
        List<Order> orders = OrderMapper.entityToDomain(orderEntities);

        assertEquals(1, orders.size());
        Order order = orders.get(0);
        assertEquals(orderEntity.getId(), order.getId());
        assertEquals(orderEntity.getUserId(), order.getUserId());
        assertEquals(orderEntity.getUserName(), order.getUserName());
        assertEquals(orderEntity.getOrderNumber(), order.getOrderNumber());
        assertEquals(orderEntity.getTotalPrice(), order.getTotalPrice());
        assertEquals(orderEntity.getStatus(), order.getStatus());
        assertEquals(1, order.getOrderItems().size());
        assertEquals(orderItemEntity.getProductId(), order.getOrderItems().get(0).getProductId());
        assertEquals(orderItemEntity.getQuantity(), order.getOrderItems().get(0).getQuantity());
    }

    @Test
    void testDomainListToEntityList() {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(1L);
        orderItem.setQuantity(2);
        orderItem.setPrice(10.0);

        Order order = new Order();
        order.setId(1L);
        order.setUserId(1L);
        order.setUserName("John Doe");
        order.setOrderNumber("ORD-001");
        order.setOrderTime(ZonedDateTime.now());
        order.setCompletedTime(ZonedDateTime.now().plusHours(1));
        order.setTotalPrice(20.0);
        order.setStatus(OrderStatus.RECEIVED);
        order.setOrderItems(Collections.singletonList(orderItem));

        List<Order> orders = Collections.singletonList(order);
        List<OrderEntity> orderEntities = OrderMapper.domainToEntity(orders);

        assertEquals(1, orderEntities.size());
        OrderEntity orderEntity = orderEntities.get(0);
        assertEquals(order.getId(), orderEntity.getId());
        assertEquals(order.getUserId(), orderEntity.getUserId());
        assertEquals(order.getUserName(), orderEntity.getUserName());
        assertEquals(order.getOrderNumber(), orderEntity.getOrderNumber());
        assertEquals(order.getTotalPrice(), orderEntity.getTotalPrice());
        assertEquals(order.getStatus(), orderEntity.getStatus());
        assertEquals(1, orderEntity.getOrderItems().size());
        assertEquals(orderItem.getProductId(), orderEntity.getOrderItems().get(0).getProductId());
        assertEquals(orderItem.getQuantity(), orderEntity.getOrderItems().get(0).getQuantity());
    }
}