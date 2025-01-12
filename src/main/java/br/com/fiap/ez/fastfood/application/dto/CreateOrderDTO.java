package br.com.fiap.ez.fastfood.application.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class CreateOrderDTO {

	@JsonProperty("user_name")
	private String userName;

	@JsonProperty("user_cpf")
	@Schema(description = "User CPF (optional)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String userCpf = "";

	@JsonProperty("order_items")
	private List<OrderItemDTO> orderItems;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCpf() {
		return userCpf;
	}

	public void setUserCpf(String userCpf) {
		this.userCpf = userCpf;
	}

	public List<OrderItemDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}
}
