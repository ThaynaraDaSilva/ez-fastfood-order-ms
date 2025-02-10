package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.application.dto.OrderItemDTO;
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

import static org.assertj.core.api.Assertions.assertThat;

public class OrderMapperTest {

    @Test
    public void testEntityToDomain() {
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

        Order order = OrderMapper.entityToDomain(orderEntity);

        assertThat(order).isNotNull();
        assertThat(order.getId()).isEqualTo(1L);
        assertThat(order.getUserId()).isEqualTo(1L);
        assertThat(order.getUserName()).isEqualTo("John Doe");
        assertThat(order.getOrderNumber()).isEqualTo("ORD-001");
        assertThat(order.getOrderTime()).isEqualTo(orderEntity.getOrderTime());
        assertThat(order.getCompletedTime()).isEqualTo(orderEntity.getCompletedTime());
        assertThat(order.getTotalPrice()).isEqualTo(20.0);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.RECEIVED);
        assertThat(order.getOrderItems()).hasSize(1);
    }

    @Test
    public void testDomainToEntity() {
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

        OrderEntity orderEntity = OrderMapper.domainToEntity(order);

        assertThat(orderEntity).isNotNull();
        assertThat(orderEntity.getId()).isEqualTo(1L);
        assertThat(orderEntity.getUserId()).isEqualTo(1L);
        assertThat(orderEntity.getUserName()).isEqualTo("John Doe");
        assertThat(orderEntity.getOrderNumber()).isEqualTo("ORD-001");
        assertThat(orderEntity.getOrderTime()).isEqualTo(order.getOrderTime());
        assertThat(orderEntity.getCompletedTime()).isEqualTo(order.getCompletedTime());
        assertThat(orderEntity.getTotalPrice()).isEqualTo(20.0);
        assertThat(orderEntity.getStatus()).isEqualTo(OrderStatus.RECEIVED);
        assertThat(orderEntity.getOrderItems()).hasSize(1);
    }

    @Test
    public void testDomainToResponseDTO() {
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

        OrderResponseDTO orderResponseDTO = OrderMapper.domainToResponseDTO(order);

        assertThat(orderResponseDTO).isNotNull();
        assertThat(orderResponseDTO.getOrderId()).isEqualTo(1L);
        assertThat(orderResponseDTO.getUserId()).isEqualTo(1L);
        assertThat(orderResponseDTO.getUserName()).isEqualTo("John Doe");
        assertThat(orderResponseDTO.getOrderNumber()).isEqualTo("ORD-001");
        assertThat(orderResponseDTO.getOrderTime()).isEqualTo(order.getOrderTime());
        assertThat(orderResponseDTO.getCompletedTime()).isEqualTo(order.getCompletedTime());
        assertThat(orderResponseDTO.getTotalPrice()).isEqualTo(20.0);
        assertThat(orderResponseDTO.getOrderStatus()).isEqualTo(OrderStatus.RECEIVED);
        assertThat(orderResponseDTO.getOrderItems()).hasSize(1);
    }

    @Test
    public void testEntityToOrderResponseDTO() {
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

        OrderResponseDTO orderResponseDTO = OrderMapper.entityToOrderResponseDTO(orderEntity);

        assertThat(orderResponseDTO).isNotNull();
        assertThat(orderResponseDTO.getOrderId()).isEqualTo(1L);
        assertThat(orderResponseDTO.getUserId()).isEqualTo(1L);
        assertThat(orderResponseDTO.getUserName()).isEqualTo("John Doe");
        assertThat(orderResponseDTO.getOrderNumber()).isEqualTo("ORD-001");
        assertThat(orderResponseDTO.getOrderTime()).isEqualTo(orderEntity.getOrderTime());
        assertThat(orderResponseDTO.getCompletedTime()).isEqualTo(orderEntity.getCompletedTime());
        assertThat(orderResponseDTO.getTotalPrice()).isEqualTo(20.0);
        assertThat(orderResponseDTO.getOrderStatus()).isEqualTo(OrderStatus.RECEIVED);
        assertThat(orderResponseDTO.getOrderItems()).hasSize(1);
    }
}