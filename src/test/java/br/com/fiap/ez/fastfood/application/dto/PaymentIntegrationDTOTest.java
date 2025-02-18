package br.com.fiap.ez.fastfood.application.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PaymentIntegrationDTOTest {

    @Test
    void testGettersAndSetters() {
        PaymentIntegrationDTO paymentIntegrationDTO = new PaymentIntegrationDTO();
        Long expectedOrderId = 1L;
        String expectedPaymentStatus = "OK";

        paymentIntegrationDTO.setOrderId(expectedOrderId);
        paymentIntegrationDTO.setPaymentStatus(expectedPaymentStatus);

        assertEquals(expectedOrderId, paymentIntegrationDTO.getOrderId());
        assertEquals(expectedPaymentStatus, paymentIntegrationDTO.getPaymentStatus());
    }

    @Test
    void testDefaultValues() {
        PaymentIntegrationDTO paymentIntegrationDTO = new PaymentIntegrationDTO();

        assertEquals(0L, paymentIntegrationDTO.getOrderId());
        assertEquals("", paymentIntegrationDTO.getPaymentStatus());
    }
}