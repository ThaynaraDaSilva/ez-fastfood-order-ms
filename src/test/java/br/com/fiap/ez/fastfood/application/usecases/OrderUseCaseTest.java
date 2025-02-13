package br.com.fiap.ez.fastfood.application.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.ez.fastfood.adapters.out.http.CatalogHttpClient;
import br.com.fiap.ez.fastfood.adapters.out.http.PaymentHttpClient;
import br.com.fiap.ez.fastfood.adapters.out.http.UserHttpClient;
import br.com.fiap.ez.fastfood.adapters.out.messaging.PaymentPublisher;
import br.com.fiap.ez.fastfood.application.dto.CatalogDTO;
import br.com.fiap.ez.fastfood.application.dto.CreateOrderDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderItemDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.application.dto.PaymentRequestDTO;
import br.com.fiap.ez.fastfood.application.dto.UserDTO;
import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.domain.model.OrderStatus;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;

class OrderUseCaseTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CatalogHttpClient catalogHttpClient;

    @Mock
    private UserHttpClient userHttpClient;

    @Mock
    private PaymentPublisher paymentPublisher;

    @InjectMocks
    private OrderUseCase orderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterOrderWithUserCpf() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO();
        createOrderDTO.setUserCpf("123.456.789-00");
        createOrderDTO.setUserName("John Doe");

        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductId(1L);
        orderItemDTO.setQuantity(2);
        createOrderDTO.setOrderItems(Arrays.asList(orderItemDTO));

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        CatalogDTO catalogDTO = new CatalogDTO();
        catalogDTO.setPrice(10.0);

        Order savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setTotalPrice(20.0);
        savedOrder.setOrderNumber("ORD-001");
        savedOrder.setStatus(OrderStatus.WAITING_PAYMENT);
        savedOrder.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        savedOrder.setOrderItems(new ArrayList<>());

        when(userHttpClient.getUserByCpf(createOrderDTO.getUserCpf())).thenReturn(userDTO);
        when(catalogHttpClient.findProductById(1L)).thenReturn(catalogDTO);
        when(orderRepository.findLastOrder()).thenReturn(null);
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        OrderResponseDTO response = orderUseCase.registerOrder(createOrderDTO);

        assertNotNull(response);
        assertEquals(savedOrder.getId(), response.getOrderId());
        assertEquals(savedOrder.getOrderNumber(), response.getOrderNumber());
        verify(paymentPublisher).publishPaymentRequest(any(PaymentRequestDTO.class));
    }

    @Test
    void testRegisterOrderWithoutUserCpf() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO();
        createOrderDTO.setUserName("John Doe");

        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductId(1L);
        orderItemDTO.setQuantity(2);
        createOrderDTO.setOrderItems(Arrays.asList(orderItemDTO));

        CatalogDTO catalogDTO = new CatalogDTO();
        catalogDTO.setPrice(10.0);

        Order savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setTotalPrice(20.0);
        savedOrder.setOrderNumber("ORD-001");
        savedOrder.setStatus(OrderStatus.WAITING_PAYMENT);
        savedOrder.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        savedOrder.setOrderItems(new ArrayList<>());

        when(catalogHttpClient.findProductById(1L)).thenReturn(catalogDTO);
        when(orderRepository.findLastOrder()).thenReturn(null);
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        OrderResponseDTO response = orderUseCase.registerOrder(createOrderDTO);

        assertNotNull(response);
        assertEquals(savedOrder.getId(), response.getOrderId());
        assertEquals(savedOrder.getOrderNumber(), response.getOrderNumber());
        verify(paymentPublisher).publishPaymentRequest(any(PaymentRequestDTO.class));
    }

    @Test
    void testUpdateOrderStatusReceived() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.RECEIVED);
        order.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        order.setOrderItems(new ArrayList<>());

        when(orderRepository.findOrderById(1L)).thenReturn(order);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderResponseDTO response = orderUseCase.updateOrderStatus(1L);

        assertNotNull(response);
        assertEquals(OrderStatus.IN_PREPARATION, response.getOrderStatus());
    }

    @Test
    void testUpdateOrderStatusInPreparation() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.IN_PREPARATION);
        order.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        order.setOrderItems(new ArrayList<>());

        when(orderRepository.findOrderById(1L)).thenReturn(order);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderResponseDTO response = orderUseCase.updateOrderStatus(1L);

        assertNotNull(response);
        assertEquals(OrderStatus.READY, response.getOrderStatus());
        assertNotNull(order.getCompletedTime());
    }

    @Test
    void testUpdateOrderStatusReady() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.READY);
        order.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        order.setOrderItems(new ArrayList<>());

        when(orderRepository.findOrderById(1L)).thenReturn(order);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderResponseDTO response = orderUseCase.updateOrderStatus(1L);

        assertNotNull(response);
        assertEquals(OrderStatus.COMPLETED, response.getOrderStatus());
    }

    @Test
    void testUpdateOrderStatusWaitingPayment() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        order.setOrderItems(new ArrayList<>());

        when(orderRepository.findOrderById(1L)).thenReturn(order);

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            orderUseCase.updateOrderStatus(1L);
        });

        assertEquals("Pedido não pode ser alterado, uma vez que o pagamento ainda não foi confirmado.", exception.getMessage());
    }

    @Test
    void testUpdateOrderStatusCancelled() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.CANCELLED);
        order.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        order.setOrderItems(new ArrayList<>());

        when(orderRepository.findOrderById(1L)).thenReturn(order);

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            orderUseCase.updateOrderStatus(1L);
        });

        assertEquals("Pedido não pode ser atualizado, uma vez que está cancelado por falta de pagamento.", exception.getMessage());
    }

    @Test
    void testListUnfinishedOrders() {
        List<Order> unfinishedOrders = new ArrayList<>();
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.RECEIVED);
        order.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        order.setOrderItems(new ArrayList<>());
        unfinishedOrders.add(order);

        when(orderRepository.listUnfinishedOrders()).thenReturn(unfinishedOrders);

        List<OrderResponseDTO> response = orderUseCase.listUnfinishedOrders();

        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(1, response.size());
    }

    @Test
    void testListAllOrders() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.RECEIVED);
        order.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        order.setOrderItems(new ArrayList<>());
        orders.add(order);

        when(orderRepository.findAll()).thenReturn(orders);

        List<OrderResponseDTO> response = orderUseCase.listAllOrders();

        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(1, response.size());
    }

    @Test
    void testListAllOrdersEmpty() {
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            orderUseCase.listAllOrders();
        });

        assertEquals("Lista de pedidos vazia", exception.getMessage());
    }

    @Test
    void testListUncompletedOrders() {
        List<Order> uncompletedOrders = new ArrayList<>();
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.RECEIVED);
        order.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        order.setOrderItems(new ArrayList<>());
        uncompletedOrders.add(order);

        when(orderRepository.listUnCompletedOrders()).thenReturn(uncompletedOrders);

        List<OrderResponseDTO> response = orderUseCase.listUncompletedOrders();

        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(1, response.size());
    }


    @Test
    void testListUncompletedOrdersEmpty() {
        when(orderRepository.listUnCompletedOrders()).thenReturn(new ArrayList<>());

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            orderUseCase.listUncompletedOrders();
        });

        assertEquals("Não há pedidos com status 'Pronto', 'Em preparação' ou 'Recebido'", exception.getMessage());
    }
}