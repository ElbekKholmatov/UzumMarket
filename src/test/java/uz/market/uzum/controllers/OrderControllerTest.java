package uz.market.uzum.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uz.market.uzum.configuration.security.SessionUser;
import uz.market.uzum.domains.product.Order;
import uz.market.uzum.dtos.order.AddToOrderDTO;
import uz.market.uzum.enums.OrderStatus;
import uz.market.uzum.enums.Payment;
import uz.market.uzum.mappers.product.OrderMapperImpl;
import uz.market.uzum.repositories.OrderRepository;
import uz.market.uzum.repositories.ProductOrderRepository;
import uz.market.uzum.repositories.order.OrderPaginationRepository;
import uz.market.uzum.repositories.user.UserRepository;
import uz.market.uzum.services.OrderService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Autowired
    private ObjectMapper objectMapper;





        /**
         * Method under test: {@link OrderController#addToOrder(AddToOrderDTO)}
         */


    /**
     * Method under test: {@link OrderController#addToOrder(AddToOrderDTO)}
     */
    @Test
    void testAddToOrder2() {

        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        SessionUser sessionUser = mock(SessionUser.class);
        when(sessionUser.id()).thenReturn(1L);
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        when(productOrderRepository.findALLByIds(Mockito.
                <Collection<Long>>any())).thenReturn(new ArrayList<>());
        OrderController orderController = new OrderController(new OrderService(orderRepository, sessionUser,
                productOrderRepository, new OrderMapperImpl(), mock(OrderPaginationRepository.class)));
        ResponseEntity<Order> actualAddToOrderResult = orderController
                .addToOrder(new AddToOrderDTO(new ArrayList<>(), Payment.CASH));
        assertTrue(actualAddToOrderResult.hasBody());
        assertTrue(actualAddToOrderResult.getHeaders().isEmpty());
        assertEquals(200, actualAddToOrderResult.getStatusCodeValue());
        verify(orderRepository).save(Mockito.<Order>any());
        verify(sessionUser).id();
        verify(productOrderRepository).findALLByIds(Mockito.<Collection<Long>>any());
    }

    /**
     * Method under test: {@link OrderController#addToOrder(AddToOrderDTO)}
     */
    @Test
    void testAddToOrder3() {

        OrderService orderService = mock(OrderService.class);
        when(orderService.addToOrder(Mockito.<AddToOrderDTO>any())).thenReturn(new Order());
        OrderController orderController = new OrderController(orderService);
        ResponseEntity<Order> actualAddToOrderResult = orderController
                .addToOrder(new AddToOrderDTO(new ArrayList<>(), Payment.CASH));
        assertTrue(actualAddToOrderResult.hasBody());
        assertTrue(actualAddToOrderResult.getHeaders().isEmpty());
        assertEquals(200, actualAddToOrderResult.getStatusCodeValue());
        verify(orderService).addToOrder(Mockito.<AddToOrderDTO>any());
    }

    /**
     * Method under test: {@link OrderController#getAllNewOrders(Integer, Integer)}
     */
    @Test
    void testGetAllNewOrders() {

        OrderRepository orderRepository = mock(OrderRepository.class);
        PageImpl<Order> pageImpl = new PageImpl<>(new ArrayList<>());
        when(orderRepository.findAllByStatus(Mockito.<Pageable>any())).thenReturn(pageImpl);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        Page<Order> actualAllNewOrders = (new OrderController(new OrderService(orderRepository, sessionUser,
                productOrderRepository, new OrderMapperImpl(), mock(OrderPaginationRepository.class)))).getAllNewOrders(1, 3);
        assertSame(pageImpl, actualAllNewOrders);
        assertTrue(actualAllNewOrders.toList().isEmpty());
        verify(orderRepository).findAllByStatus(Mockito.<Pageable>any());
    }


    /**
     * Method under test: {@link OrderController#getAllNewOrders(Integer, Integer)}
     */
    @Test
    void testGetAllNewOrders3() {

        OrderService orderService = mock(OrderService.class);
        PageImpl<Order> pageImpl = new PageImpl<>(new ArrayList<>());
        when(orderService.getAllNewOrders(Mockito.<Pageable>any())).thenReturn(pageImpl);
        Page<Order> actualAllNewOrders = (new OrderController(orderService)).getAllNewOrders(1, 3);
        assertSame(pageImpl, actualAllNewOrders);
        assertTrue(actualAllNewOrders.toList().isEmpty());
        verify(orderService).getAllNewOrders(Mockito.<Pageable>any());
    }


    /**
     * Method under test: {@link OrderController#getAllOrders(Integer, Integer)}
     */
    @Test
    void testGetAllOrders() {

        OrderRepository orderRepository = mock(OrderRepository.class);
        PageImpl<Order> pageImpl = new PageImpl<>(new ArrayList<>());
        when(orderRepository.findAllOnPaying(Mockito.<Pageable>any())).thenReturn(pageImpl);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        Page<Order> actualAllOrders = (new OrderController(new OrderService(orderRepository, sessionUser,
                productOrderRepository, new OrderMapperImpl(), mock(OrderPaginationRepository.class)))).getAllOrders(1, 3);
        assertSame(pageImpl, actualAllOrders);
        assertTrue(actualAllOrders.toList().isEmpty());
        verify(orderRepository).findAllOnPaying(Mockito.<Pageable>any());
    }


    /**
     * Method under test: {@link OrderController#getAllOrders(Integer, Integer)}
     */
    @Test
    void testGetAllOrders3() {

        OrderService orderService = mock(OrderService.class);
        PageImpl<Order> pageImpl = new PageImpl<>(new ArrayList<>());
        when(orderService.getAllOrders(Mockito.<Pageable>any())).thenReturn(pageImpl);
        Page<Order> actualAllOrders = (new OrderController(orderService)).getAllOrders(1, 3);
        assertSame(pageImpl, actualAllOrders);
        assertTrue(actualAllOrders.toList().isEmpty());
        verify(orderService).getAllOrders(Mockito.<Pageable>any());
    }


    /**
     * Method under test: {@link OrderController#updateOrderCancel(Long)}
     */
    @Test
    void testUpdateOrderCancel() {

        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Order()));
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        ResponseEntity<Order> actualUpdateOrderCancelResult = (new OrderController(new OrderService(orderRepository,
                sessionUser, productOrderRepository, new OrderMapperImpl(), mock(OrderPaginationRepository.class))))
                .updateOrderCancel(1L);
        assertTrue(actualUpdateOrderCancelResult.hasBody());
        assertTrue(actualUpdateOrderCancelResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateOrderCancelResult.getStatusCodeValue());
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OrderController#updateOrderCancel(Long)}
     */
    @Test
    void testUpdateOrderCancel2() {

        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        ResponseEntity<Order> actualUpdateOrderCancelResult = (new OrderController(new OrderService(orderRepository,
                sessionUser, productOrderRepository, new OrderMapperImpl(), mock(OrderPaginationRepository.class))))
                .updateOrderCancel(1L);
        assertTrue(actualUpdateOrderCancelResult.hasBody());
        assertTrue(actualUpdateOrderCancelResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateOrderCancelResult.getStatusCodeValue());
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }


    /**
     * Method under test: {@link OrderController#updateOrderCancel(Long)}
     */
    @Test
    void testUpdateOrderCancel4() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional.of(order);
        OrderService orderService = mock(OrderService.class);
        when(orderService.updateOrderCancel(Mockito.<Long>any())).thenReturn(new Order());
        ResponseEntity<Order> actualUpdateOrderCancelResult = (new OrderController(orderService)).updateOrderCancel(1L);
        assertTrue(actualUpdateOrderCancelResult.hasBody());
        assertTrue(actualUpdateOrderCancelResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateOrderCancelResult.getStatusCodeValue());
        verify(orderService).updateOrderCancel(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OrderController#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Order()));
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        ResponseEntity<Order> actualUpdateOrderStatusResult = (new OrderController(new OrderService(orderRepository,
                sessionUser, productOrderRepository, new OrderMapperImpl(), mock(OrderPaginationRepository.class))))
                .updateOrderStatus(1L, OrderStatus.NEW);
        assertTrue(actualUpdateOrderStatusResult.hasBody());
        assertTrue(actualUpdateOrderStatusResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateOrderStatusResult.getStatusCodeValue());
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OrderController#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus2() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        ResponseEntity<Order> actualUpdateOrderStatusResult = (new OrderController(new OrderService(orderRepository,
                sessionUser, productOrderRepository, new OrderMapperImpl(), mock(OrderPaginationRepository.class))))
                .updateOrderStatus(1L, OrderStatus.NEW);
        assertTrue(actualUpdateOrderStatusResult.hasBody());
        assertTrue(actualUpdateOrderStatusResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateOrderStatusResult.getStatusCodeValue());
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }


    /**
     * Method under test: {@link OrderController#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus4() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional.of(order);
        OrderService orderService = mock(OrderService.class);
        when(orderService.updateOrderStatus(Mockito.<Long>any(), Mockito.<OrderStatus>any())).thenReturn(new Order());
        ResponseEntity<Order> actualUpdateOrderStatusResult = (new OrderController(orderService)).updateOrderStatus(1L,
                OrderStatus.NEW);
        assertTrue(actualUpdateOrderStatusResult.hasBody());
        assertTrue(actualUpdateOrderStatusResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateOrderStatusResult.getStatusCodeValue());
        verify(orderService).updateOrderStatus(Mockito.<Long>any(), Mockito.<OrderStatus>any());
    }

    /**
     * Method under test: {@link OrderController#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus5() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional.of(order);
        OrderService orderService = mock(OrderService.class);
        when(orderService.updateOrderStatus(Mockito.<Long>any(), Mockito.<OrderStatus>any())).thenReturn(new Order());
        ResponseEntity<Order> actualUpdateOrderStatusResult = (new OrderController(orderService)).updateOrderStatus(1L,
                OrderStatus.CANCELED);
        assertTrue(actualUpdateOrderStatusResult.hasBody());
        assertTrue(actualUpdateOrderStatusResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateOrderStatusResult.getStatusCodeValue());
        verify(orderService).updateOrderStatus(Mockito.<Long>any(), Mockito.<OrderStatus>any());
    }

    /**
     * Method under test: {@link OrderController#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus6() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional.of(order);
        OrderService orderService = mock(OrderService.class);
        when(orderService.updateOrderStatus(Mockito.<Long>any(), Mockito.<OrderStatus>any())).thenReturn(new Order());
        ResponseEntity<Order> actualUpdateOrderStatusResult = (new OrderController(orderService)).updateOrderStatus(1L,
                OrderStatus.DELIVERED);
        assertTrue(actualUpdateOrderStatusResult.hasBody());
        assertTrue(actualUpdateOrderStatusResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateOrderStatusResult.getStatusCodeValue());
        verify(orderService).updateOrderStatus(Mockito.<Long>any(), Mockito.<OrderStatus>any());
    }

    /**
     * Method under test: {@link OrderController#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus7() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional.of(order);
        OrderService orderService = mock(OrderService.class);
        when(orderService.updateOrderStatus(Mockito.<Long>any(), Mockito.<OrderStatus>any())).thenReturn(new Order());
        ResponseEntity<Order> actualUpdateOrderStatusResult = (new OrderController(orderService)).updateOrderStatus(1L,
                OrderStatus.RETURNED);
        assertTrue(actualUpdateOrderStatusResult.hasBody());
        assertTrue(actualUpdateOrderStatusResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateOrderStatusResult.getStatusCodeValue());
        verify(orderService).updateOrderStatus(Mockito.<Long>any(), Mockito.<OrderStatus>any());
    }

    /**
     * Method under test: {@link OrderController#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus8() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional.of(order);
        OrderService orderService = mock(OrderService.class);
        when(orderService.updateOrderStatus(Mockito.<Long>any(), Mockito.<OrderStatus>any())).thenReturn(new Order());
        ResponseEntity<Order> actualUpdateOrderStatusResult = (new OrderController(orderService)).updateOrderStatus(1L,
                OrderStatus.COMPLETED);
        assertTrue(actualUpdateOrderStatusResult.hasBody());
        assertTrue(actualUpdateOrderStatusResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateOrderStatusResult.getStatusCodeValue());
        verify(orderService).updateOrderStatus(Mockito.<Long>any(), Mockito.<OrderStatus>any());
    }

    /**
     * Method under test: {@link OrderController#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus9() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional.of(order);
        OrderService orderService = mock(OrderService.class);
        when(orderService.updateOrderStatus(Mockito.<Long>any(), Mockito.<OrderStatus>any())).thenReturn(new Order());
        ResponseEntity<Order> actualUpdateOrderStatusResult = (new OrderController(orderService)).updateOrderStatus(1L,
                OrderStatus.PROCESSING);
        assertTrue(actualUpdateOrderStatusResult.hasBody());
        assertTrue(actualUpdateOrderStatusResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateOrderStatusResult.getStatusCodeValue());
        verify(orderService).updateOrderStatus(Mockito.<Long>any(), Mockito.<OrderStatus>any());
    }




    /**
     * Method under test: {@link OrderController#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus10() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional.of(order);
        OrderService orderService = mock(OrderService.class);
        when(orderService.updateOrderStatus(Mockito.<Long>any(), Mockito.<OrderStatus>any())).thenReturn(new Order());
        ResponseEntity<Order> actualUpdateOrderStatusResult = (new OrderController(orderService)).updateOrderStatus(1L,
                OrderStatus.PAYMENT_PERIOD);
        assertTrue(actualUpdateOrderStatusResult.hasBody());
        assertTrue(actualUpdateOrderStatusResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateOrderStatusResult.getStatusCodeValue());
        verify(orderService).updateOrderStatus(Mockito.<Long>any(), Mockito.<OrderStatus>any());
    }
}

