package br.com.fiap.ez.fastfood.infrastructure.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HttpClientPropertiesTest {

    @Test
    void testGettersAndSetters() {
        HttpClientProperties properties = new HttpClientProperties();

        properties.setUserUrl("http://localhost:8080/users");
        properties.setCatalogUrl("http://localhost:8080/catalog");
        properties.setPaymentUrl("http://localhost:8080/payments");

        assertEquals("http://localhost:8080/users", properties.getUserUrl());
        assertEquals("http://localhost:8080/catalog", properties.getCatalogUrl());
        assertEquals("http://localhost:8080/payments", properties.getPaymentUrl());
    }
}