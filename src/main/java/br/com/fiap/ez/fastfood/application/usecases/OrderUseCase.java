package br.com.fiap.ez.fastfood.application.usecases;

import br.com.fiap.ez.fastfood.adapters.out.http.CatalogHttpClient;
import br.com.fiap.ez.fastfood.adapters.out.http.UserHttpClient;
import br.com.fiap.ez.fastfood.adapters.out.messaging.PaymentPublisher;
import br.com.fiap.ez.fastfood.application.dto.CatalogDTO;
import br.com.fiap.ez.fastfood.application.dto.CreateOrderDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderItemDTO;
import br.com.fiap.ez.fastfood.application.dto.PaymentIntegrationDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.application.dto.PaymentPublisherRequestDTO;
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
	private final CatalogHttpClient catalogHttpClient;
	private final UserHttpClient userHttpClient;
	private final PaymentPublisher paymentPublisher;
	

	public OrderUseCase(OrderRepository orderRepository, CatalogHttpClient catalogHttpClient,
			UserHttpClient userHttpClient, PaymentPublisher paymentPublisher) {
		this.orderRepository = orderRepository;
		this.catalogHttpClient = catalogHttpClient;
		this.userHttpClient = userHttpClient;
		this.paymentPublisher = paymentPublisher;

	}

	public OrderResponseDTO registerOrder(CreateOrderDTO createOrderDTO) {
		Order saveOrder = new Order();
		UserDTO userDTO = null;

		// chamar integracao somente quando o cpf for fornecido.
		if (createOrderDTO.getUserCpf() != null && !createOrderDTO.getUserCpf().isBlank()) {
			userDTO = userHttpClient.getUserByCpf(createOrderDTO.getUserCpf());
			saveOrder.setUserId(userDTO.getId());
		}

		saveOrder.setUserName(createOrderDTO.getUserName());
		saveOrder.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
		saveOrder.setStatus(OrderStatus.WAITING_PAYMENT);

		List<OrderItem> orderItemList = new ArrayList<>();

		for (OrderItemDTO item : createOrderDTO.getOrderItems()) {
			OrderItem orderItem = new OrderItem();

			// AVALIAR IMPACTO
			CatalogDTO catalogDTO = catalogHttpClient.findProductById(item.getProductId());
			if (catalogDTO == null) {
				throw new BusinessException("Product not found");
			} else {
				orderItem.setProductId(item.getProductId());
				orderItem.setPrice(catalogDTO.getPrice());
			}

			orderItem.setQuantity(item.getQuantity());

			orderItem.setOrder(saveOrder);
			orderItemList.add(orderItem);
		}

		saveOrder.setOrderItems(orderItemList);
		saveOrder.calculateAndSetTotalPrice();

		Order lastOrder = orderRepository.findLastOrder();

		saveOrder.setOrderNumber(saveOrder.generateOrderNumber(lastOrder));
		Order savedOrder = orderRepository.save(saveOrder);
		System.out.println("#################### SALVOU O PEDIDO ##################");

		try {

			PaymentPublisherRequestDTO paymentRequest = new PaymentPublisherRequestDTO();
			paymentRequest.setOrderId(savedOrder.getId());
			paymentRequest.setUserId(savedOrder.getUserId());
			paymentRequest.setAmount(savedOrder.getTotalPrice());
			System.out.println("#################### TENTARA ACIONAR O PUBLISH PAYMENT ##################");
			paymentPublisher.publishPaymentRequest(paymentRequest);

		} catch (Exception e) {
		    throw new RuntimeException("Failed to publish payment request for orderId: " + savedOrder.getId(), e);
		}

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
	
	public OrderResponseDTO notifyOrderPaymentStatus (PaymentIntegrationDTO dto) {
		Order order = orderRepository.findOrderById(dto.getOrderId());
		if(order!=null) {
			if(dto.getPaymentStatus().equalsIgnoreCase("OK")) {
				order.setStatus(OrderStatus.RECEIVED);
			}else {
				order.setStatus(OrderStatus.CANCELLED);
			}
			order.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
			return OrderMapper.domainToResponseDTO(orderRepository.save(order));
		}else {
			throw new BusinessException("Order not found");
		}
		
	}

}
