package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.application.dto.OrderItemDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.domain.model.OrderStatus;

import br.com.fiap.ez.fastfood.infrastructure.persistence.OrderEntity;
import br.com.fiap.ez.fastfood.infrastructure.persistence.OrderItemEntity;


import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

	// Convert OrderEntity (Persistence) to Order (Domain)
	public static Order entityToDomain(OrderEntity entity) {
		Order order = new Order();
		order.setId(entity.getId());
		if (entity.getUserId() != null) {
			order.setUserId(entity.getUserId());
			order.setUserName(entity.getUserName());
		}else {
			order.setUserName(entity.getUserName());
		}
		order.setOrderNumber(entity.getOrderNumber());
		order.setOrderTime(entity.getOrderTime());
		order.setCompletedTime(entity.getCompletedTime());
		order.setTotalPrice(entity.getTotalPrice());
		order.setStatus(entity.getStatus());
		order.setOrderItems(entity.getOrderItems().stream().map(OrderItemMapper::entityToDomain).collect(Collectors.toList()));
		
		return order;
	}
	
	public static OrderEntity domainToEntity(Order order) {
	    if (order == null) {
	        return null;
	    }

	    OrderEntity entity = new OrderEntity();
	    entity.setId(order.getId());
	    entity.setOrderNumber(order.getOrderNumber());
	    entity.setOrderTime(order.getOrderTime());
	    entity.setCompletedTime(order.getCompletedTime());
	    entity.setTotalPrice(order.getTotalPrice());
	    entity.setStatus(order.getStatus());
	    entity.setUserName(order.getUserName());
	    //entity.setUserId(CustomerMapper.domainToEntity(order.getCustomer())); ## COMENTADO 2025

	    
	    List<OrderItemEntity> orderItemEntities = order.getOrderItems().stream()
	            .map(OrderItemMapper::domainToEntity)
	            .collect(Collectors.toList());
	    
	    orderItemEntities.forEach(item -> item.setOrder(entity)); 
	    entity.setOrderItems(orderItemEntities);
	    
	    return entity;
	}

	// Convert a List of OrderEntity (Persistence) to a List of Order (Domain)
	public static List<Order> entityToDomain(List<OrderEntity> entities) {
		return entities.stream().map(OrderMapper::entityToDomain).collect(Collectors.toList());
	}

	// Convert a List of Order (Domain) to a List of OrderEntity (Persistence)
	public static List<OrderEntity> domainToEntity(List<Order> orders) {
		return orders.stream().map(OrderMapper::domainToEntity).collect(Collectors.toList());
	}

	public static OrderResponseDTO domainToResponseDTO(Order order) {
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		
		orderResponseDTO.setOrderId(order.getId());
		orderResponseDTO.setOrderNumber(order.getOrderNumber());
		orderResponseDTO.setOrderTime(order.getOrderTime());
		orderResponseDTO.setTotalPrice(order.getTotalPrice());
		if(order.getCompletedTime()!=null) {
			orderResponseDTO.setCompletedTime(order.getCompletedTime());
		}

		if (order.getUserId() != null) {
			//orderResponseDTO.setUserIdCpf(order.getUserId().getCpf()); ## fix 2025
			orderResponseDTO.setUserId(order.getUserId());
		}

		orderResponseDTO.setUserName(order.getUserName());
		orderResponseDTO.setOrderStatus(order.getStatus());
		if(order.getStatus() == OrderStatus.RECEIVED 
				|| order.getStatus() == OrderStatus.IN_PREPARATION 
				|| order.getStatus() == OrderStatus.READY 
				|| order.getStatus() == OrderStatus.COMPLETED) {
			orderResponseDTO.setWaitedTime(order.calculateOrderWaitedTime(order.getOrderTime(),order.getCompletedTime()));
		}

		// Map Order Items to DTO
		List<OrderItemDTO> orderItemDTOs = order.getOrderItems().stream()
				.map(orderItem -> new OrderItemDTO(orderItem.getProductId(), orderItem.getQuantity()))
				.collect(Collectors.toList());
		orderResponseDTO.setOrderItems(orderItemDTOs);

		return orderResponseDTO;
	}

	public static OrderResponseDTO entityToOrderResponseDTO(OrderEntity entity) {
    	
		Order order = new Order();
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        
        orderResponseDTO.setOrderId(entity.getId());
        if(entity.getUserId()!= null) {
        	// orderResponseDTO.setUserName(entity.getUserName()); ## FIX 2025
        }
        orderResponseDTO.setUserName(entity.getUserName());
        orderResponseDTO.setOrderTime(entity.getOrderTime());
        orderResponseDTO.setCompletedTime( entity.getCompletedTime());
        orderResponseDTO.setTotalPrice(entity.getTotalPrice());
        orderResponseDTO.setOrderStatus(entity.getStatus());
        orderResponseDTO.setWaitedTime(order.calculateOrderWaitedTime(entity.getOrderTime(),entity.getOrderTime()));
        List<OrderItemDTO> orderItemDTOs = entity.getOrderItems().stream()
				.map(orderItem -> new OrderItemDTO(
						orderItem.getProductId(),
						orderItem.getQuantity()))
				.collect(Collectors.toList());
        orderResponseDTO.setOrderItems(orderItemDTOs);
       
        return orderResponseDTO;
    }
	
	
}
