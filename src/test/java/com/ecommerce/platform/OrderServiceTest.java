package com.ecommerce.platform;

import com.ecommerce.platform.model.Order;
import com.ecommerce.platform.model.OrderItem;
import com.ecommerce.platform.model.Product;
import com.ecommerce.platform.model.User;
import com.ecommerce.platform.repository.OrderRepository;
import com.ecommerce.platform.repository.UserRepository;
import com.ecommerce.platform.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderService orderService;

    private User user;
    private Order order;
    private OrderItem orderItem;

    @BeforeEach
    public void initializeOrder() {
        user = new User();
        user.setUserId(1L);
        user.setUsername("tester");

        Product product = new Product();
        product.setProductId(1L);
        product.setName("Test Product");

        orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setProduct(product);
        orderItem.setQuantity(1);
        orderItem.setPrice(100.0);

        order = new Order();
        order.setId(1L);
        order.setUser(user);
        order.setOrderItems(Collections.singletonList(orderItem));
        order.setOrderDate(new Date());
        order.setStatus("PLACED");
    }

    @Test
    public void shouldPlaceOrderSuccessfully() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order placedOrder = orderService.placeOrder(1L, Collections.singletonList(orderItem));

        assertNotNull(placedOrder);
        assertEquals("PLACED", placedOrder.getStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    public void shouldGetOrdersByUserIdSuccessfully() {
        when(orderRepository.findByUserUserId(1L)).thenReturn(Collections.singletonList(order));

        List<Order> orders = orderService.getOrdersByUserId(1L);

        assertNotNull(orders);
        assertEquals(1, orders.size());
        verify(orderRepository, times(1)).findByUserUserId(1L);
    }

    @Test
    public void shouldGetOrderByIdSuccessfully() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order foundOrder = orderService.getOrderById(1L);

        assertNotNull(foundOrder);
        assertEquals(1L, foundOrder.getId());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldCancelOrderSuccessfully() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        orderService.cancelOrder(1L);

        assertEquals("CANCELLED", order.getStatus());
        verify(orderRepository, times(1)).save(order);
    }

}