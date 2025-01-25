package br.com.fiap.ez.fastfood.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderDTOTest {

	@Test
	public void testConstructorWithuserNameOnly() {
	  String userName = "Jane Doe";

	  CreateOrderDTO createOrderDTO = new CreateOrderDTO();
	  createOrderDTO.setUserName(userName); 

	  assertEquals(userName, createOrderDTO.getUserName());
	  assertEquals("", createOrderDTO.getUserCpf());
	  assertNull(createOrderDTO.getOrderItems());
	}

    @Test
    public void testAllGettersAndSetters() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO();

        String userName = "Michael Smith";
        String userCpf = "98765432100";
        List<OrderItemDTO> orderItems = new ArrayList<>();

        createOrderDTO.setUserName(userName);
        createOrderDTO.setUserCpf(userCpf);
        createOrderDTO.setOrderItems(orderItems);

        assertEquals(userName, createOrderDTO.getUserName());
        assertEquals(userCpf, createOrderDTO.getUserCpf());
        assertEquals(orderItems, createOrderDTO.getOrderItems());
    }

    @Test
    public void testNullOrEmptyuserCpf() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO();

        createOrderDTO.setUserCpf(null);
        createOrderDTO.setUserCpf("");

        assertEquals("", createOrderDTO.getUserCpf());

        createOrderDTO.setUserCpf(" ");
        assertEquals(" ", createOrderDTO.getUserCpf());
    }

}