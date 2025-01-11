package br.com.fiap.ez.fastfood.application.usecases;

import br.com.fiap.ez.fastfood.adapters.out.http.PaymentHttpClient;
import br.com.fiap.ez.fastfood.adapters.out.http.ProductHttpClient;
import br.com.fiap.ez.fastfood.adapters.out.http.UserHttpClient;
import br.com.fiap.ez.fastfood.application.dto.CreateOrderDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderItemDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.application.dto.UserDTO;
import br.com.fiap.ez.fastfood.domain.model.*;

import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;

import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import br.com.fiap.ez.fastfood.infrastructure.mapper.OrderMapper;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

public class OrderUseCase {

	private final OrderRepository orderRepository;
	private final ProductHttpClient productHttpClient;
	private final UserHttpClient userHttpClient;
	private final PaymentHttpClient paymentHttpClient;

	public OrderUseCase(OrderRepository orderRepository, ProductHttpClient productHttpClient,
			UserHttpClient userHttpClient, PaymentHttpClient paymentHttpClient) {
		this.orderRepository = orderRepository;
		this.productHttpClient = productHttpClient;
		this.userHttpClient = userHttpClient;
		this.paymentHttpClient = paymentHttpClient;

	}

	public OrderResponseDTO registerOrder(CreateOrderDTO createOrderDTO) {
		Order saveOrder = new Order();
		UserDTO userDTO = userHttpClient.getUserByCpf(createOrderDTO.getUserCpf());
		
	    if(userDTO != null)     {   
	    	saveOrder.setUserId(userDTO.getId());
	    }

	    saveOrder.setUserName(createOrderDTO.getUserName());
		saveOrder.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
		saveOrder.setStatus(OrderStatus.WAITING_PAYMENT);

		List<OrderItem> orderItemList = new ArrayList<>();

		for (OrderItemDTO item : createOrderDTO.getOrderItems()) {
			OrderItem orderItem = new OrderItem();
			Product product = productRepository.findById(item.getProductId())
					.orElseThrow(() -> new BusinessException("Product not found"));

			orderItem.setProduct(product);
			orderItem.setQuantity(item.getQuantity());
			orderItem.setPrice(product.getPrice() * item.getQuantity());
			orderItem.setOrder(saveOrder);
			orderItemList.add(orderItem);
		}

		saveOrder.setOrderItems(orderItemList);
		saveOrder.calculateAndSetTotalPrice();

		Order lastOrder = orderRepository.findLastOrder();

		saveOrder.setOrderNumber(saveOrder.generateOrderNumber(lastOrder));

		Order savedOrder = orderRepository.save(saveOrder);

		
		paymentUseCase.registerPayment(savedOrder);

		
		return OrderMapper.domainToResponseDTO(savedOrder);
	}

	public OrderResponseDTO updateOrderStatus(Long orderId) {
		Order order = orderRepository.findOrderById(orderId);

		if (order.getStatus() == OrderStatus.RECEIVED) {
			order.setStatus(OrderStatus.IN_PREPARATION);
		} else if (order.getStatus() == OrderStatus.IN_PREPARATION) {
			order.setStatus(OrderStatus.READY);
			order.setCompletedTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
		} else if (order.getStatus() == OrderStatus.READY) {
			order.setStatus(OrderStatus.COMPLETED);
		} else if (order.getStatus() == OrderStatus.WAITING_PAYMENT) {
			throw new BusinessException(
					"Pedido não pode ser alterado, uma vez que o pagamento ainda não foi confirmado.");
		} else if (order.getStatus() == OrderStatus.CANCELLED) {
			throw new BusinessException(
					"Pedido não pode ser atualizado, uma vez que está cancelado por falta de pagamento.");
		}

		order = orderRepository.save(order);
		return OrderMapper.domainToResponseDTO(order);
	}

	public List<OrderResponseDTO> listUnfinishedOrders() {
		List<Order> unfinishedOrders = orderRepository.listUnfinishedOrders();
		return unfinishedOrders.stream().map(OrderMapper::domainToResponseDTO).collect(Collectors.toList());
	}

	public List<OrderResponseDTO> listAllOrders() {

		List<Order> orders = orderRepository.findAll();
		if (orders.isEmpty()) {
			throw new BusinessException("Lista de pedidos vazia");
		}
		return orders.stream().map(OrderMapper::domainToResponseDTO).collect(Collectors.toList());
	}

	public List<OrderResponseDTO> listUncompletedOrders() {
		List<Order> uncompletedOrders = orderRepository.listUnCompletedOrders();
		if (!uncompletedOrders.isEmpty()) {
			return uncompletedOrders.stream().map(OrderMapper::domainToResponseDTO).collect(Collectors.toList());
		} else {
			throw new BusinessException("Não há pedidos com status 'Pronto', 'Em preparação' ou 'Recebido'");
		}

	}

}
