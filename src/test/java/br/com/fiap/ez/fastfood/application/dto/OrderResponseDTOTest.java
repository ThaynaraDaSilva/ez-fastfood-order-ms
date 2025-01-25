/*
 * package br.com.fiap.ez.fastfood.application.dto;
 * 
 * import org.junit.jupiter.api.Test;
 * 
 * import static org.junit.jupiter.api.Assertions.*;
 * 
 * import java.time.ZonedDateTime; import java.util.ArrayList; import
 * java.util.List;
 * 
 * import br.com.fiap.ez.fastfood.domain.model.OrderStatus;
 * 
 * public class OrderResponseDTOTest {
 * 
 * @Test public void testDefaultConstructor() { OrderResponseDTO
 * orderResponseDTO = new OrderResponseDTO();
 * 
 * assertEquals(0L, orderResponseDTO.getOrderId()); assertEquals("",
 * orderResponseDTO.getOrderNumber()); assertEquals("",
 * orderResponseDTO.getUserName()); assertNull(orderResponseDTO.getOrderTime());
 * assertNull(orderResponseDTO.getCompletedTime());
 * assertNull(orderResponseDTO.getTotalPrice());
 * assertNull(orderResponseDTO.getOrderStatus());
 * assertNotNull(orderResponseDTO.getOrderItems());
 * assertTrue(orderResponseDTO.getOrderItems().isEmpty()); assertEquals("",
 * orderResponseDTO.getWaitedTime()); }
 * 
 * @Test public void testConstructorWithOrderIdAnduserName() { Long orderId =
 * 1L; String userName = "John Doe"; ZonedDateTime orderTime =
 * ZonedDateTime.now(); ZonedDateTime completedTime = null; Double totalPrice =
 * 10.0; OrderStatus orderStatus = OrderStatus.RECEIVED;
 * 
 * OrderResponseDTO orderResponseDTO = new OrderResponseDTO(orderId, userName,
 * orderTime, completedTime, totalPrice, orderStatus);
 * 
 * assertEquals(orderId, orderResponseDTO.getOrderId()); assertEquals(userName,
 * orderResponseDTO.getUserName()); assertEquals(orderTime,
 * orderResponseDTO.getOrderTime()); assertEquals(completedTime,
 * orderResponseDTO.getCompletedTime()); assertEquals(totalPrice,
 * orderResponseDTO.getTotalPrice()); assertEquals(orderStatus,
 * orderResponseDTO.getOrderStatus()); }
 * 
 * @Test public void testConstructorWithuserCpfAnduserName() { Long orderId =
 * 0L; String userCpf = "12345678900"; String userName = "Jane Doe";
 * ZonedDateTime orderTime = ZonedDateTime.now(); ZonedDateTime completedTime =
 * null; Double totalPrice = 15.0; OrderStatus orderStatus =
 * OrderStatus.WAITING_PAYMENT;
 * 
 * OrderResponseDTO orderResponseDTO = new OrderResponseDTO(orderId, userCpf,
 * userName, orderTime, completedTime, totalPrice, orderStatus);
 * 
 * assertEquals(0L, orderResponseDTO.getOrderId()); assertEquals(userCpf,
 * orderResponseDTO.getUserCpf()); assertEquals(userName,
 * orderResponseDTO.getUserName()); assertEquals(orderTime,
 * orderResponseDTO.getOrderTime()); assertEquals(completedTime,
 * orderResponseDTO.getCompletedTime()); assertEquals(totalPrice,
 * orderResponseDTO.getTotalPrice()); assertEquals(orderStatus,
 * orderResponseDTO.getOrderStatus()); }
 * 
 * @Test public void testConstructorWithAllFields() { Long orderId = 2L; String
 * orderNumber = "FASTFOOD-000001"; String userCpf = "98765432100"; String
 * userName = "Michael Smith"; ZonedDateTime orderTime =
 * ZonedDateTime.now().minusDays(1); ZonedDateTime completedTime =
 * ZonedDateTime.now(); Double totalPrice = 20.50; OrderStatus orderStatus =
 * OrderStatus.COMPLETED; List<OrderItemDTO> orderItems = new ArrayList<>();
 * String waitedTime = "00:15:00";
 * 
 * OrderResponseDTO orderResponseDTO = new OrderResponseDTO(orderId,
 * orderNumber, userCpf, userName, orderTime, completedTime, totalPrice,
 * orderStatus, orderItems, waitedTime);
 * 
 * assertEquals(orderId, orderResponseDTO.getOrderId());
 * assertEquals(orderNumber, orderResponseDTO.getOrderNumber());
 * assertEquals(userCpf, orderResponseDTO.getUserCpf()); assertEquals(userName,
 * orderResponseDTO.getUserName()); assertEquals(orderTime,
 * orderResponseDTO.getOrderTime()); assertEquals(completedTime,
 * orderResponseDTO.getCompletedTime()); assertEquals(totalPrice,
 * orderResponseDTO.getTotalPrice()); assertEquals(orderStatus,
 * orderResponseDTO.getOrderStatus()); assertEquals(orderItems,
 * orderResponseDTO.getOrderItems()); assertEquals(waitedTime,
 * orderResponseDTO.getWaitedTime()); }
 * 
 * @Test public void testConstructorWithAllFieldsExceptOrderIdAndOrderNumber() {
 * String userCpf = "12345678900"; String userName = "Jane Doe"; ZonedDateTime
 * orderTime = ZonedDateTime.now(); ZonedDateTime completedTime = null; Double
 * totalPrice = 15.0; OrderStatus orderStatus = OrderStatus.WAITING_PAYMENT;
 * List<OrderItemDTO> orderItems = new ArrayList<>();
 * 
 * OrderResponseDTO orderResponseDTO = new OrderResponseDTO(userCpf, userName,
 * orderTime, completedTime, totalPrice, orderStatus, orderItems);
 * 
 * assertEquals(0L, orderResponseDTO.getOrderId()); assertEquals(userCpf,
 * orderResponseDTO.getUserCpf()); assertEquals(userName,
 * orderResponseDTO.getUserName()); assertEquals(orderTime,
 * orderResponseDTO.getOrderTime()); assertEquals(completedTime,
 * orderResponseDTO.getCompletedTime()); assertEquals(totalPrice,
 * orderResponseDTO.getTotalPrice()); assertEquals(orderStatus,
 * orderResponseDTO.getOrderStatus()); assertEquals(orderItems,
 * orderResponseDTO.getOrderItems()); assertEquals("",
 * orderResponseDTO.getWaitedTime()); }
 * 
 * @Test public void testAllGettersAndSetters() { OrderResponseDTO
 * orderResponseDTO = new OrderResponseDTO();
 * 
 * orderResponseDTO.setOrderId(123L);
 * orderResponseDTO.setOrderNumber("ORDER-123");
 * orderResponseDTO.setUserCpf("12345678900");
 * orderResponseDTO.setUserName("John Doe");
 * orderResponseDTO.setOrderTime(ZonedDateTime.now());
 * orderResponseDTO.setCompletedTime(ZonedDateTime.now().plusHours(1));
 * orderResponseDTO.setTotalPrice(29.99);
 * orderResponseDTO.setOrderStatus(OrderStatus.COMPLETED); List<OrderItemDTO>
 * orderItems = new ArrayList<>(); orderResponseDTO.setOrderItems(orderItems);
 * orderResponseDTO.setWaitedTime("30 minutes");
 * 
 * assertEquals(123L, orderResponseDTO.getOrderId()); assertEquals("ORDER-123",
 * orderResponseDTO.getOrderNumber()); assertEquals("12345678900",
 * orderResponseDTO.getUserCpf()); assertEquals("John Doe",
 * orderResponseDTO.getUserName()); } }
 */