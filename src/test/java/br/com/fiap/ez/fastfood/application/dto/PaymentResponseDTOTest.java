package br.com.fiap.ez.fastfood.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentResponseDTOTest {

    private PaymentResponseDTO paymentResponseDTO;

    @BeforeEach
    void setUp() {
        paymentResponseDTO = new PaymentResponseDTO();
    }

    @Test
    void testSetAndGetStatus() {
        String status = "SUCCESS";
        paymentResponseDTO.setStatus(status);
        assertEquals(status, paymentResponseDTO.getStatus());
    }

    @Test
    void testSetAndGetTransactionId() {
        String transactionId = "TX123456";
        paymentResponseDTO.setTransactionId(transactionId);
        assertEquals(transactionId, paymentResponseDTO.getTransactionId());
    }

    @Test
    void testDefaultValues() {
        assertNull(paymentResponseDTO.getStatus());
        assertNull(paymentResponseDTO.getTransactionId());
    }
}