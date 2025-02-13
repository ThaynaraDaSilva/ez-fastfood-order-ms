package br.com.fiap.ez.fastfood.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class OrderTest {

    private Order order;
    private List<OrderItem> orderItems;

    @BeforeEach
    public void setUp() {
        order = new Order(1L, "0001 John", 1L, "John",
                ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")),
                null, 0.0, OrderStatus.RECEIVED, new ArrayList<>());

        orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(order, 1L, 2, 10.0));
        orderItems.add(new OrderItem(order, 2L, 1, 20.0));

        order.setOrderItems(orderItems);
    }

    @Test
    public void testCalculateTotalPrice() {
        double totalPrice = Order.calculateTotalPrice(orderItems);
        Assertions.assertEquals(40.0, totalPrice);
    }

    @Test
    public void testCalculateOrderWaitedTime() {
        ZonedDateTime orderTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).minusHours(1);
        String waitedTime = order.calculateOrderWaitedTime(orderTime, null);
        Assertions.assertTrue(waitedTime.startsWith("01h"));
    }

    @Test
    public void testCalculateAndSetTotalPrice() {
        order.calculateAndSetTotalPrice();
        Assertions.assertEquals(40.0, order.getTotalPrice());
    }

    @Test
    public void testGenerateOrderNumber() {
        Order lastOrder = new Order(2L, "0001 John", 1L, "John",
                ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")),
                null, 0.0, OrderStatus.RECEIVED, orderItems);
        String orderNumber = order.generateOrderNumber(lastOrder);
        Assertions.assertEquals("0002 John", orderNumber);
    }

    @Test
    public void testGettersAndSetters() {
        order.setId(2L);
        Assertions.assertEquals(2L, order.getId());

        order.setUserId(2L);
        Assertions.assertEquals(2L, order.getUserId());

        order.setUserName("Doe");
        Assertions.assertEquals("Doe", order.getUserName());

        order.setOrderNumber("0002 Doe");
        Assertions.assertEquals("0002 Doe", order.getOrderNumber());

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        order.setOrderTime(now);
        Assertions.assertEquals(now.withZoneSameInstant(ZoneId.of("America/Sao_Paulo")), order.getOrderTime());

        order.setCompletedTime(now);
        Assertions.assertEquals(now, order.getCompletedTime());

        order.setTotalPrice(50.0);
        Assertions.assertEquals(50.0, order.getTotalPrice());

        order.setStatus(OrderStatus.COMPLETED);
        Assertions.assertEquals(OrderStatus.COMPLETED, order.getStatus());

        List<OrderItem> newOrderItems = new ArrayList<>();
        order.setOrderItems(newOrderItems);
        Assertions.assertEquals(newOrderItems, order.getOrderItems());
    }
}