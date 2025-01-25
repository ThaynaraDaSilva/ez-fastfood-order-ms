package br.com.fiap.ez.fastfood.domain.model;

public class OrderItem {

    private Long id;
    private Order order;
    private Long productId;
    private Integer quantity;
    private Double price;

    // Default constructor
    public OrderItem() {
    }

    public OrderItem(Order order, Long productId, Integer quantity, Double price) {
		this.order = order;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

    // Getters and setters
    public Long getId() {
        return id;
    }



	public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // Business logic method
    public Double getTotalPrice() {
        return price * quantity;
    }
}
