package uz.market.uzum.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uz.market.uzum.configuration.security.SessionUser;
import uz.market.uzum.domains.product.Order;
import uz.market.uzum.dtos.order.AddToOrderDTO;
import uz.market.uzum.dtos.order.PayOrderDTO;
import uz.market.uzum.enums.OrderStatus;
import uz.market.uzum.enums.Payment;
import uz.market.uzum.mappers.product.OrderMapper;
import uz.market.uzum.mappers.product.OrderMapperImpl;
import uz.market.uzum.repositories.OrderRepository;
import uz.market.uzum.repositories.ProductOrderRepository;
import uz.market.uzum.repositories.order.OrderPaginationRepository;
import uz.market.uzum.repositories.user.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    @Test
    void testAddToOrder2() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order);
        SessionUser sessionUser = mock(SessionUser.class);
        when(sessionUser.id()).thenReturn(1L);
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        when(productOrderRepository.findALLByIds(Mockito.<Collection<Long>>any())).thenReturn(new ArrayList<>());
        OrderService orderService = new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class));
        assertSame(order, orderService.addToOrder(new AddToOrderDTO(new ArrayList<>(), Payment.CASH)));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(sessionUser).id();
        verify(productOrderRepository).findALLByIds(Mockito.<Collection<Long>>any());
    }

    /**
     * Method under test: {@link OrderService#addToOrder(AddToOrderDTO)}
     */
    @Test
    void testAddToOrder3() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        SessionUser sessionUser = mock(SessionUser.class);
        when(sessionUser.id()).thenReturn(1L);
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        when(productOrderRepository.findALLByIds(Mockito.<Collection<Long>>any()))
                .thenThrow(new RuntimeException("An error occurred"));
        OrderService orderService = new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class));
        assertThrows(RuntimeException.class,
                () -> orderService.addToOrder(new AddToOrderDTO(new ArrayList<>(), Payment.CASH)));
        verify(sessionUser).id();
        verify(productOrderRepository).findALLByIds(Mockito.<Collection<Long>>any());
    }

    @Test
    void testGetAllNewOrders() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        PageImpl<Order> pageImpl = new PageImpl<>(new ArrayList<>());
        when(orderRepository.findAllByStatus(Mockito.<Pageable>any())).thenReturn(pageImpl);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        Page<Order> actualAllNewOrders = (new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class))).getAllNewOrders(null);
        assertSame(pageImpl, actualAllNewOrders);
        assertTrue(actualAllNewOrders.toList().isEmpty());
        verify(orderRepository).findAllByStatus(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link OrderService#getAllNewOrders(Pageable)}
     */
    @Test
    void testGetAllNewOrders2() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findAllByStatus(Mockito.<Pageable>any()))
                .thenThrow(new RuntimeException("An error occurred"));
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertThrows(RuntimeException.class, () -> (new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class))).getAllNewOrders(null));
        verify(orderRepository).findAllByStatus(Mockito.<Pageable>any());
    }

    @Test
    void testAddToOrderInstallment() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        OrderService orderService = new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class));
        AddToOrderDTO actualAddToOrderInstallmentResult = orderService
                .addToOrderInstallment(new AddToOrderDTO(new ArrayList<>(), Payment.CASH));
        assertNull(actualAddToOrderInstallmentResult.payment());
        assertNull(actualAddToOrderInstallmentResult.productIds());
        verify(orderRepository).save(Mockito.<Order>any());
    }

    @Test
    void testAddToOrderInstallment2() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(null);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        OrderService orderService = new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class));
        assertNull(orderService.addToOrderInstallment(new AddToOrderDTO(new ArrayList<>(), Payment.CASH)));
        verify(orderRepository).save(Mockito.<Order>any());
    }

    @Test
    void testAddToOrderInstallment5() {
        new RuntimeException("An error occurred");
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(mock(Order.class));
        OrderMapper orderMapper = mock(OrderMapper.class);
        when(orderMapper.toOrder(Mockito.<AddToOrderDTO>any())).thenReturn(new Order());
        AddToOrderDTO addToOrderDTO = new AddToOrderDTO(new ArrayList<>(), Payment.CASH);

        when(orderMapper.toAppToOrderDTO(Mockito.<Order>any())).thenReturn(addToOrderDTO);
        OrderService orderService = new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                mock(ProductOrderRepository.class), orderMapper, mock(OrderPaginationRepository.class));
        assertSame(addToOrderDTO, orderService.addToOrderInstallment(new AddToOrderDTO(new ArrayList<>(), Payment.CASH)));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderMapper).toOrder(Mockito.<AddToOrderDTO>any());
        verify(orderMapper).toAppToOrderDTO(Mockito.<Order>any());
    }

    @Test
    void testGetOrderInstallment() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Order()));
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        AddToOrderDTO actualOrderInstallment = (new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class))).getOrderInstallment(1L);
        assertNull(actualOrderInstallment.payment());
        assertNull(actualOrderInstallment.productIds());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testGetOrderInstallment2() {
        Order order = mock(Order.class);
        when(order.getPayment()).thenReturn(Payment.CASH);
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(order));
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        AddToOrderDTO actualOrderInstallment = (new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class))).getOrderInstallment(1L);
        assertEquals(Payment.CASH, actualOrderInstallment.payment());
        assertNull(actualOrderInstallment.productIds());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).getPayment();
    }

    @Test
    void testGetOrderInstallment3() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertThrows(RuntimeException.class, () -> (new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class))).getOrderInstallment(1L));
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testGetAllOrders() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        PageImpl<Order> pageImpl = new PageImpl<>(new ArrayList<>());
        when(orderRepository.findAllOnPaying(Mockito.<Pageable>any())).thenReturn(pageImpl);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        Page<Order> actualAllOrders = (new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class))).getAllOrders(null);
        assertSame(pageImpl, actualAllOrders);
        assertTrue(actualAllOrders.toList().isEmpty());
        verify(orderRepository).findAllOnPaying(Mockito.<Pageable>any());
    }

    @Test
    void testGetAllOrders2() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findAllOnPaying(Mockito.<Pageable>any()))
                .thenThrow(new RuntimeException("An error occurred"));
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertThrows(RuntimeException.class, () -> (new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class))).getAllOrders(null));
        verify(orderRepository).findAllOnPaying(Mockito.<Pageable>any());
    }

    @Test
    void testUpdateOrderCancel() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Order()));
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertSame(order, (new OrderService(orderRepository, sessionUser, productOrderRepository, new OrderMapperImpl(),
                mock(OrderPaginationRepository.class))).updateOrderCancel(1L));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testUpdateOrderCancel2() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenThrow(new RuntimeException("An error occurred"));
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Order()));
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertThrows(RuntimeException.class, () -> (new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class))).updateOrderCancel(1L));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testUpdateOrderCancel3() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertSame(order2, (new OrderService(orderRepository, sessionUser, productOrderRepository, new OrderMapperImpl(),
                mock(OrderPaginationRepository.class))).updateOrderCancel(1L));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    @Test
    void testUpdateOrderCancel4() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertThrows(RuntimeException.class, () -> (new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class))).updateOrderCancel(1L));
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testUpdateOrderCancel5() {
        Order order = mock(Order.class);
        doThrow(new RuntimeException("An error occurred")).when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertThrows(RuntimeException.class, () -> (new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class))).updateOrderCancel(1L));
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    @Test
    void testUpdateOrderStatus() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Order()));
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertSame(order, (new OrderService(orderRepository, sessionUser, productOrderRepository, new OrderMapperImpl(),
                mock(OrderPaginationRepository.class))).updateOrderStatus(1L, OrderStatus.NEW));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testUpdateOrderStatus2() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenThrow(new RuntimeException("An error occurred"));
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Order()));
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertThrows(RuntimeException.class, () -> (new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class))).updateOrderStatus(1L, OrderStatus.NEW));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testUpdateOrderStatus3() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertSame(order2, (new OrderService(orderRepository, sessionUser, productOrderRepository, new OrderMapperImpl(),
                mock(OrderPaginationRepository.class))).updateOrderStatus(1L, OrderStatus.NEW));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    @Test
    void testUpdateOrderStatus4() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertThrows(RuntimeException.class, () -> (new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class))).updateOrderStatus(1L, OrderStatus.NEW));
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testUpdateOrderStatus5() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertSame(order2, (new OrderService(orderRepository, sessionUser, productOrderRepository, new OrderMapperImpl(),
                mock(OrderPaginationRepository.class))).updateOrderStatus(1L, OrderStatus.CANCELED));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    @Test
    void testUpdateOrderStatus6() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertSame(order2, (new OrderService(orderRepository, sessionUser, productOrderRepository, new OrderMapperImpl(),
                mock(OrderPaginationRepository.class))).updateOrderStatus(1L, OrderStatus.DELIVERED));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    @Test
    void testUpdateOrderStatus7() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertSame(order2, (new OrderService(orderRepository, sessionUser, productOrderRepository, new OrderMapperImpl(),
                mock(OrderPaginationRepository.class))).updateOrderStatus(1L, OrderStatus.RETURNED));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    @Test
    void testUpdateOrderStatus8() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertSame(order2, (new OrderService(orderRepository, sessionUser, productOrderRepository, new OrderMapperImpl(),
                mock(OrderPaginationRepository.class))).updateOrderStatus(1L, OrderStatus.COMPLETED));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    @Test
    void testUpdateOrderStatus9() {
        Order order = mock(Order.class);
        doThrow(new RuntimeException("An error occurred")).when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertThrows(RuntimeException.class, () -> (new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class))).updateOrderStatus(1L, OrderStatus.NEW));
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    @Test
    void testUpdateOrderStatus10() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertSame(order2, (new OrderService(orderRepository, sessionUser, productOrderRepository, new OrderMapperImpl(),
                mock(OrderPaginationRepository.class))).updateOrderStatus(1L, OrderStatus.PROCESSING));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    @Test
    void testUpdateOrderStatus11() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        assertSame(order2, (new OrderService(orderRepository, sessionUser, productOrderRepository, new OrderMapperImpl(),
                mock(OrderPaginationRepository.class))).updateOrderStatus(1L, OrderStatus.PAYMENT_PERIOD));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    @Test
    void testPayForInstallment() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Order()));
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        OrderService orderService = new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class));
        Order order2 = new Order();
        assertSame(order, orderService.payForInstallment(order2, 10.0d, new PayOrderDTO(1L, 1L, 10)));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testPayForInstallment2() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenThrow(new RuntimeException("An error occurred"));
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Order()));
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        OrderService orderService = new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class));
        Order order = new Order();
        assertThrows(RuntimeException.class,
                () -> orderService.payForInstallment(order, 10.0d, new PayOrderDTO(1L, 1L, 10)));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testPayForInstallment3() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        OrderService orderService = new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class));
        Order order3 = new Order();
        assertSame(order2, orderService.payForInstallment(order3, 10.0d, new PayOrderDTO(1L, 1L, 10)));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    @Test
    void testPayForInstallment4() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        OrderService orderService = new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class));
        Order order = new Order();
        assertThrows(RuntimeException.class,
                () -> orderService.payForInstallment(order, 10.0d, new PayOrderDTO(1L, 1L, 10)));
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testPayForInstallment5() {
        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        OrderService orderService = new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class));
        Order order2 = new Order();
        assertThrows(RuntimeException.class,
                () -> orderService.payForInstallment(order2, Double.NaN, new PayOrderDTO(1L, 1L, 10)));
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testPayForInstallment7() {
        Order order = mock(Order.class);
        doThrow(new RuntimeException("An error occurred")).when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SessionUser sessionUser = new SessionUser(mock(UserRepository.class));
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        OrderService orderService = new OrderService(orderRepository, sessionUser, productOrderRepository,
                new OrderMapperImpl(), mock(OrderPaginationRepository.class));
        Order order2 = new Order();
        assertThrows(RuntimeException.class,
                () -> orderService.payForInstallment(order2, 10.0d, new PayOrderDTO(1L, 1L, 10)));
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }
}

