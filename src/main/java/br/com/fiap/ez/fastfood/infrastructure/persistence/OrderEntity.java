package br.com.fiap.ez.fastfood.infrastructure.persistence;

import jakarta.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ez.fastfood.domain.model.OrderStatus;

@Entity
@Table(name = "`order`")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_number")
	private String orderNumber;

	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "user_name", nullable = true)
	private String userName;

	@Column(name = "order_time")
	private ZonedDateTime orderTime;

	@Column(name = "completed_time", nullable = true)
	private ZonedDateTime completedTime;

	@Column(name = "total_price")
	private Double totalPrice;

	@Enumerated(EnumType.STRING)
	@Column(name = "order_status", nullable = true)
	private OrderStatus status;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)

	
	private List<OrderItemEntity> orderItems = new ArrayList<>();

	public OrderEntity() {
		super();
	}

	

	public OrderEntity(Long id, String orderNumber, Long userId, String userName, ZonedDateTime orderTime,
			ZonedDateTime completedTime, Double totalPrice, OrderStatus status, List<OrderItemEntity> orderItems) {
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



	public void addOrderItem(OrderItemEntity orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this); 
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
		return orderTime;
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


	public List<OrderItemEntity> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemEntity> orderItems) {
		this.orderItems = orderItems;
	}

}