package br.com.fiap.ez.fastfood.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentRequestDTOTest {

    private PaymentRequestDTO paymentRequestDTO;

    @BeforeEach
    void setUp() {
        paymentRequestDTO = new PaymentRequestDTO();
    }

    @Test
    void testSetAndGetOrderId() {
        Long orderId = 123L;
        paymentRequestDTO.setOrderId(orderId);
        assertEquals(orderId, paymentRequestDTO.getOrderId());
    }

    @Test
    void testSetAndGetUserId() {
        Long userId = 456L;
        paymentRequestDTO.setUserId(userId);
        assertEquals(userId, paymentRequestDTO.getUserId());
    }

    @Test
    void testSetAndGetAmount() {
        Double amount = 99.99;
        paymentRequestDTO.setAmount(amount);
        assertEquals(amount, paymentRequestDTO.getAmount());
    }

    @Test
    void testDefaultValues() {
        assertNull(paymentRequestDTO.getOrderId());
        assertNull(paymentRequestDTO.getUserId());
        assertNull(paymentRequestDTO.getAmount());
    }
}