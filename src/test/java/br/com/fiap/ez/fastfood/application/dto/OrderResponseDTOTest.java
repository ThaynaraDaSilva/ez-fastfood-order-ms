package br.com.fiap.ez.fastfood.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.ez.fastfood.domain.model.OrderStatus;

class OrderResponseDTOTest {

    private OrderResponseDTO orderResponseDTO;

    @BeforeEach
    void setUp() {
        orderResponseDTO = new OrderResponseDTO();
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(orderResponseDTO);
        assertEquals(0L, orderResponseDTO.getOrderId());
        assertEquals("", orderResponseDTO.getOrderNumber());
        assertNull(orderResponseDTO.getUserId());
        assertEquals("", orderResponseDTO.getUserName());
        assertNull(orderResponseDTO.getOrderTime());
        assertNull(orderResponseDTO.getCompletedTime());
        assertNull(orderResponseDTO.getTotalPrice());
        assertNull(orderResponseDTO.getOrderStatus());
        assertTrue(orderResponseDTO.getOrderItems().isEmpty());
        assertEquals("", orderResponseDTO.getWaitedTime());
    }

    @Test
    void testParameterizedConstructorWithOrderId() {
        Long orderId = 1L;
        String userName = "John Doe";
        ZonedDateTime orderTime = ZonedDateTime.now();
        ZonedDateTime completedTime = ZonedDateTime.now().plusHours(1);
        Double totalPrice = 99.99;
        OrderStatus orderStatus = OrderStatus.WAITING_PAYMENT;

        orderResponseDTO = new OrderResponseDTO(orderId, userName, orderTime, completedTime, totalPrice, orderStatus);

        assertEquals(orderId, orderResponseDTO.getOrderId());
        assertEquals(userName, orderResponseDTO.getUserName());
        assertEquals(orderTime, orderResponseDTO.getOrderTime());
        assertEquals(completedTime, orderResponseDTO.getCompletedTime());
        assertEquals(totalPrice, orderResponseDTO.getTotalPrice());
        assertEquals(orderStatus, orderResponseDTO.getOrderStatus());

        List<OrderItemDTO> orderItems = orderResponseDTO.getOrderItems();
        assertTrue(orderItems == null || orderItems.isEmpty());
        assertEquals("", orderResponseDTO.getWaitedTime());
    }

    @Test
    void testParameterizedConstructorWithOrderItems() {
        String userName = "Jane Doe";
        ZonedDateTime orderTime = ZonedDateTime.now();
        ZonedDateTime completedTime = ZonedDateTime.now().plusHours(2);
        Double totalPrice = 49.99;
        OrderStatus orderStatus = OrderStatus.COMPLETED;
        List<OrderItemDTO> orderItems = new ArrayList<>();
        orderItems.add(new OrderItemDTO());

        orderResponseDTO = new OrderResponseDTO(userName, orderTime, completedTime, totalPrice, orderStatus, orderItems);

        assertEquals(userName, orderResponseDTO.getUserName());
        assertEquals(orderTime, orderResponseDTO.getOrderTime());
        assertEquals(completedTime, orderResponseDTO.getCompletedTime());
        assertEquals(totalPrice, orderResponseDTO.getTotalPrice());
        assertEquals(orderStatus, orderResponseDTO.getOrderStatus());
        assertEquals(orderItems, orderResponseDTO.getOrderItems());
        assertEquals("", orderResponseDTO.getWaitedTime());
    }

    @Test
    void testParameterizedConstructorWithAllFields() {
        Long orderId = 1L;
        String orderNumber = "12345";
        String userName = "John Doe";
        ZonedDateTime orderTime = ZonedDateTime.now();
        ZonedDateTime completedTime = ZonedDateTime.now().plusHours(1);
        Double totalPrice = 99.99;
        OrderStatus orderStatus = OrderStatus.WAITING_PAYMENT;
        List<OrderItemDTO> orderItems = new ArrayList<>();
        orderItems.add(new OrderItemDTO());
        String waitedTime = "10 mins";

        orderResponseDTO = new OrderResponseDTO(orderId, orderNumber, userName, orderTime, completedTime, totalPrice, orderStatus, orderItems, waitedTime);

        assertEquals(orderId, orderResponseDTO.getOrderId());
        assertEquals(orderNumber, orderResponseDTO.getOrderNumber());
        assertEquals(userName, orderResponseDTO.getUserName());
        assertEquals(orderTime, orderResponseDTO.getOrderTime());
        assertEquals(completedTime, orderResponseDTO.getCompletedTime());
        assertEquals(totalPrice, orderResponseDTO.getTotalPrice());
        assertEquals(orderStatus, orderResponseDTO.getOrderStatus());

        List<OrderItemDTO> retrievedOrderItems = orderResponseDTO.getOrderItems();
        assertNotNull(retrievedOrderItems);
        assertEquals(orderItems, retrievedOrderItems);
        assertEquals(waitedTime, orderResponseDTO.getWaitedTime());
    }

    @Test
    void testGettersAndSetters() {
        Long orderId = 2L;
        String orderNumber = "54321";
        String userName = "Jane Doe";
        ZonedDateTime orderTime = ZonedDateTime.now();
        ZonedDateTime completedTime = ZonedDateTime.now().plusHours(2);
        Double totalPrice = 49.99;
        OrderStatus orderStatus = OrderStatus.COMPLETED;
        List<OrderItemDTO> orderItems = new ArrayList<>();
        String waitedTime = "5 mins";

        orderResponseDTO.setOrderId(orderId);
        orderResponseDTO.setOrderNumber(orderNumber);
        orderResponseDTO.setUserName(userName);
        orderResponseDTO.setOrderTime(orderTime);
        orderResponseDTO.setCompletedTime(completedTime);
        orderResponseDTO.setTotalPrice(totalPrice);
        orderResponseDTO.setOrderStatus(orderStatus);
        orderResponseDTO.setOrderItems(orderItems);
        orderResponseDTO.setWaitedTime(waitedTime);

        assertEquals(orderId, orderResponseDTO.getOrderId());
        assertEquals(orderNumber, orderResponseDTO.getOrderNumber());
        assertEquals(userName, orderResponseDTO.getUserName());
        assertEquals(orderTime, orderResponseDTO.getOrderTime());
        assertEquals(completedTime, orderResponseDTO.getCompletedTime());
        assertEquals(totalPrice, orderResponseDTO.getTotalPrice());
        assertEquals(orderStatus, orderResponseDTO.getOrderStatus());
        assertEquals(orderItems, orderResponseDTO.getOrderItems());
        assertEquals(waitedTime, orderResponseDTO.getWaitedTime());
    }
}