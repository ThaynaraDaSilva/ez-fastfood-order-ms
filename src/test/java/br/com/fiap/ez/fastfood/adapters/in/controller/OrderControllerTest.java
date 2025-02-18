package br.com.fiap.ez.fastfood.adapters.in.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.fiap.ez.fastfood.application.dto.CreateOrderDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.application.usecases.OrderUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

class OrderControllerTest {

    @Mock
    private OrderUseCase orderUseCase;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void testRegisterOrder() throws Exception {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO();

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrderId(1L);
        orderResponseDTO.setOrderNumber("ORD-001");

        when(orderUseCase.registerOrder(any(CreateOrderDTO.class))).thenReturn(orderResponseDTO);

        mockMvc.perform(post("/api/orders/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createOrderDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.order_id").value(orderResponseDTO.getOrderId())) // Adjusted to match the DTO
                .andExpect(jsonPath("$.order_number").value(orderResponseDTO.getOrderNumber())); // Check other properties

        verify(orderUseCase).registerOrder(any(CreateOrderDTO.class));
    }

    @Test
    void testListOrders() throws Exception {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrderId(1L);
        orderResponseDTO.setOrderNumber("ORD-001");

        when(orderUseCase.listAllOrders()).thenReturn(Collections.singletonList(orderResponseDTO));

        mockMvc.perform(get("/api/orders/list-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].order_id").value(orderResponseDTO.getOrderId())) // Adjusted to match the DTO
                .andExpect(jsonPath("$[0].order_number").value(orderResponseDTO.getOrderNumber())); // Check other properties

        verify(orderUseCase).listAllOrders();
    }

    @Test
    void testListUncompletedOrders() throws Exception {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrderId(1L);
        orderResponseDTO.setOrderNumber("ORD-001");

        when(orderUseCase.listUncompletedOrders()).thenReturn(Collections.singletonList(orderResponseDTO));

        mockMvc.perform(get("/api/orders/list-uncompleted-orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].order_id").value(orderResponseDTO.getOrderId())) // Adjusted to match the DTO
                .andExpect(jsonPath("$[0].order_number").value(orderResponseDTO.getOrderNumber())); // Check other properties

        verify(orderUseCase).listUncompletedOrders();
    }

    @Test
    void testListUnfinishedOrders() throws Exception {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrderId(1L);
        orderResponseDTO.setOrderNumber("ORD-001");

        when(orderUseCase.listUnfinishedOrders()).thenReturn(Collections.singletonList(orderResponseDTO));

        mockMvc.perform(get("/api/orders/list-orders-in-queue")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].order_id").value(orderResponseDTO.getOrderId())) // Adjusted to match the DTO
                .andExpect(jsonPath("$[0].order_number").value(orderResponseDTO.getOrderNumber())); // Check other properties

        verify(orderUseCase).listUnfinishedOrders();
    }

    @Test
    void testUpdateOrderStatus() throws Exception {
        Long orderId = 1L;

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrderId(orderId);

        when(orderUseCase.updateOrderStatus(orderId)).thenReturn(orderResponseDTO);

        mockMvc.perform(post("/api/orders/update-order-status")
                        .param("orderId", String.valueOf(orderId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.order_id").value(orderResponseDTO.getOrderId())); // Adjust based on your DTO structure

        verify(orderUseCase).updateOrderStatus(orderId);
    }

}