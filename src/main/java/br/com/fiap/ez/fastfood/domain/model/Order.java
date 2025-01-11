package br.com.fiap.ez.fastfood.domain.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class Order {

	private Long id;
	private String orderNumber;
	private Long userId;
	private String userName;
	private ZonedDateTime orderTime;
	private ZonedDateTime completedTime;
	private Double totalPrice;
	private OrderStatus status;
	private List<OrderItem> orderItems;

	public Order() {
	}

	
	public Order(Long id, String orderNumber, Long userId, String userName, ZonedDateTime orderTime,
			ZonedDateTime completedTime, Double totalPrice, OrderStatus status,List<OrderItem> orderItems) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.userId = userId;
		this.userName = userName;
		this.orderTime = orderTime;
		this.completedTime = completedTime;
		this.totalPrice = totalPrice;
		this.status = status;
		this.orderItems = orderItems;
	}


	public static double calculateTotalPrice(List<OrderItem> orderItems) {
		double total = 0;
		for (OrderItem item : orderItems) {
			//total += item.getQuantity() * item.getProduct().getPrice(); ## fix 2025
		}
		return total;
	}

	public String calculateOrderWaitedTime(ZonedDateTime orderTime, ZonedDateTime orderCompletedTime) {

		ZonedDateTime time = orderTime.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));

		Duration duration;
		if (orderCompletedTime != null) {
			duration = Duration.between(time, orderCompletedTime);
		} else {

			ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
			duration = Duration.between(time, now);

		}

		long hours = duration.toHours();
		long minutes = duration.toMinutes() % 60;
		return String.format("%02dh%02d", hours, minutes);
	}

	public void calculateAndSetTotalPrice() {
		this.totalPrice = calculateTotalPrice(this.orderItems);
	}

	public String generateOrderNumber(Order lastOrder) {

		int nextOrderNumber = calculateNextOrderNumber(lastOrder);

		return formatOrderNumber(nextOrderNumber, userName);
	}

	// Obtem a data atual no fuso horário de São Paulo
	private LocalDate getCurrentDate() {
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
		return zonedDateTime.toLocalDate();
	}

	// Obtem a data do último pedido, ajustando o fuso horário para São Paulo
	private LocalDate getLastOrderDate(Order lastOrder) {
		if (lastOrder != null) {
			return lastOrder.getOrderTime().withZoneSameInstant(ZoneId.of("America/Sao_Paulo")).toLocalDate();
		}
		return null; // Caso não exista último pedido
	}

	// Calcula o próximo número do pedido com base no último pedido e na data atual
	private int calculateNextOrderNumber(Order lastOrder) {
		LocalDate lastOrderDate = getLastOrderDate(lastOrder);
		LocalDate now = getCurrentDate();
		int nextOrderNumber = 1;

		if (lastOrderDate != null && lastOrderDate.equals(now)) {
			//extrai parte numerica do pedido
			String lastOrderNumber = lastOrder.getOrderNumber().split(" ")[0]; 
			nextOrderNumber = Integer.parseInt(lastOrderNumber) + 1;
		} 
		// Novo dia ou primeiro pedido, numero do pedido sera 1
		return nextOrderNumber;
	}

	// Formata o numero do pedido no padrão "0000" + Nome do Cliente
	private String formatOrderNumber(int orderNumber, String customerName) {
		String formattedOrderNumber = String.format("%04d", orderNumber);
		return formattedOrderNumber + " " + customerName;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}


	public ZonedDateTime getOrderTime() {
		return orderTime.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
	}

	public void setOrderTime(ZonedDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public ZonedDateTime getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(ZonedDateTime completedTime) {
		this.completedTime = completedTime;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
