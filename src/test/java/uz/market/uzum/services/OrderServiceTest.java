package uz.market.uzum.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.market.uzum.domains.product.Order;
import uz.market.uzum.domains.product.ProductOrder;
import uz.market.uzum.dtos.order.AddToOrderDTO;
import uz.market.uzum.dtos.order.PayOrderDTO;
import uz.market.uzum.enums.OrderStatus;
import uz.market.uzum.enums.Payment;
import uz.market.uzum.mappers.product.OrderMapper;
import uz.market.uzum.repositories.OrderRepository;
import uz.market.uzum.repositories.order.OrderPaginationRepository;

@ContextConfiguration(classes = {OrderService.class})
@ExtendWith(SpringExtension.class)
class OrderServiceTest {
    @MockBean
    private OrderMapper orderMapper;

    @MockBean
    private OrderPaginationRepository orderPaginationRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    /**
     * Method under test: {@link OrderService#addToOrderInstallment(AddToOrderDTO)}
     */
    @Test
    void testAddToOrderInstallment() {
        when(orderRepository.save((Order) any())).thenReturn(new Order());
        when(orderMapper.toOrder((AddToOrderDTO) any())).thenReturn(new Order());
        AddToOrderDTO addToOrderDTO = new AddToOrderDTO(new ArrayList<>(), 1L, Payment.CASH);

        when(orderMapper.toAppToOrderDTO((Order) any())).thenReturn(addToOrderDTO);
        assertSame(addToOrderDTO,
                orderService.addToOrderInstallment(new AddToOrderDTO(new ArrayList<>(), 1L, Payment.CASH)));
        verify(orderRepository).save((Order) any());
        verify(orderMapper).toOrder((AddToOrderDTO) any());
        verify(orderMapper).toAppToOrderDTO((Order) any());
    }

    /**
     * Method under test: {@link OrderService#addToOrderInstallment(AddToOrderDTO)}
     */
    @Test
    void testAddToOrderInstallment2() {
        when(orderRepository.save((Order) any())).thenReturn(new Order());
        when(orderMapper.toOrder((AddToOrderDTO) any())).thenThrow(new RuntimeException());
        when(orderMapper.toAppToOrderDTO((Order) any())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class,
                () -> orderService.addToOrderInstallment(new AddToOrderDTO(new ArrayList<>(), 1L, Payment.CASH)));
        verify(orderMapper).toOrder((AddToOrderDTO) any());
    }

    /**
     * Method under test: {@link OrderService#getOrderInstallment(Long)}
     */
    @Test
    void testGetOrderInstallment() {
        when(orderRepository.findById((Long) any())).thenReturn(Optional.of(new Order()));
        AddToOrderDTO addToOrderDTO = new AddToOrderDTO(new ArrayList<>(), 1L, Payment.CASH);

        when(orderMapper.toAppToOrderDTO((Order) any())).thenReturn(addToOrderDTO);
        assertSame(addToOrderDTO, orderService.getOrderInstallment(1L));
        verify(orderRepository).findById((Long) any());
        verify(orderMapper).toAppToOrderDTO((Order) any());
    }

    /**
     * Method under test: {@link OrderService#getOrderInstallment(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOrderInstallment2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at uz.market.uzum.services.OrderService.getOrderInstallment(OrderService.java:34)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderRepository.findById((Long) any())).thenReturn(Optional.of(new Order()));
        when(orderMapper.toAppToOrderDTO((Order) any())).thenThrow(new NullPointerException());
        orderService.getOrderInstallment(1L);
    }

    /**
     * Method under test: {@link OrderService#getOrderInstallment(Long)}
     */
    @Test
    void testGetOrderInstallment3() {
        when(orderRepository.findById((Long) any())).thenReturn(Optional.empty());
        when(orderMapper.toAppToOrderDTO((Order) any()))
                .thenReturn(new AddToOrderDTO(new ArrayList<>(), 1L, Payment.CASH));
        assertThrows(RuntimeException.class, () -> orderService.getOrderInstallment(1L));
        verify(orderRepository).findById((Long) any());
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uz.market.uzum.configuration.security.SessionUser;
import uz.market.uzum.domains.product.Order;
import uz.market.uzum.dtos.AddToOrderDTO;
import uz.market.uzum.enums.OrderStatus;
import uz.market.uzum.enums.Payment;
import uz.market.uzum.repositories.OrderRepository;
import uz.market.uzum.repositories.ProductOrderRepository;
import uz.market.uzum.repositories.user.UserRepository;

class OrderServiceTest {
    /**
     * Method under test: {@link OrderService#addToOrder(AddToOrderDTO)}
     */

    /**
     * Method under test: {@link OrderService#addToOrder(AddToOrderDTO)}
     */
    @Test
    void testAddToOrder2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order);
        SessionUser sessionUser = mock(SessionUser.class);
        when(sessionUser.id()).thenReturn(1L);
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        when(productOrderRepository.findALLByIds(Mockito.<Collection<Long>>any())).thenReturn(new ArrayList<>());
        OrderService orderService = new OrderService(orderRepository, sessionUser, productOrderRepository);
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
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        SessionUser sessionUser = mock(SessionUser.class);
        when(sessionUser.id()).thenReturn(1L);
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        when(productOrderRepository.findALLByIds(Mockito.<Collection<Long>>any()))
                .thenThrow(new RuntimeException("An error occurred"));
        OrderService orderService = new OrderService(orderRepository, sessionUser, productOrderRepository);
        assertThrows(RuntimeException.class,
                () -> orderService.addToOrder(new AddToOrderDTO(new ArrayList<>(), Payment.CASH)));
        verify(sessionUser).id();
        verify(productOrderRepository).findALLByIds(Mockito.<Collection<Long>>any());
    }


    /**
     * Method under test: {@link OrderService#getAllNewOrders(Pageable)}
     */
    @Test
    void testGetAllNewOrders() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        PageImpl<Order> pageImpl = new PageImpl<>(new ArrayList<>());
        when(orderRepository.findAllByStatus(Mockito.<Pageable>any())).thenReturn(pageImpl);
        Page<Order> actualAllNewOrders = (new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                mock(ProductOrderRepository.class))).getAllNewOrders(null);
        assertSame(pageImpl, actualAllNewOrders);
        assertTrue(actualAllNewOrders.toList().isEmpty());
        verify(orderRepository).findAllByStatus(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link OrderService#getAllNewOrders(Pageable)}
     */
    @Test
    void testGetAllNewOrders2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findAllByStatus(Mockito.<Pageable>any()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> (new OrderService(orderRepository,
                new SessionUser(mock(UserRepository.class)), mock(ProductOrderRepository.class))).getAllNewOrders(null));
        verify(orderRepository).findAllByStatus(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link OrderService#getAllOrders(Pageable)}
     */
    @Test
    void testGetAllOrders() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        PageImpl<Order> pageImpl = new PageImpl<>(new ArrayList<>());
        when(orderRepository.findAllOnPaying(Mockito.<Pageable>any())).thenReturn(pageImpl);
        Page<Order> actualAllOrders = (new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                mock(ProductOrderRepository.class))).getAllOrders(null);
        assertSame(pageImpl, actualAllOrders);
        assertTrue(actualAllOrders.toList().isEmpty());
        verify(orderRepository).findAllOnPaying(Mockito.<Pageable>any());
    }
    /**
     * Method under test: {@link OrderService#getAllOrders(Pageable)}
     */
    @Test
    void testGetAllOrders() {
        PageImpl<Order> pageImpl = new PageImpl<>(new ArrayList<>());
        when(orderPaginationRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        Page<Order> actualAllOrders = orderService.getAllOrders(null);
        assertSame(pageImpl, actualAllOrders);
        assertTrue(actualAllOrders.toList().isEmpty());
        verify(orderPaginationRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link OrderService#getAllOrders(Pageable)}
     */
    @Test
    void testGetAllOrders2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findAllOnPaying(Mockito.<Pageable>any()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> (new OrderService(orderRepository,
                new SessionUser(mock(UserRepository.class)), mock(ProductOrderRepository.class))).getAllOrders(null));
        verify(orderRepository).findAllOnPaying(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderCancel(Long)}
     */
    @Test
    void testUpdateOrderCancel() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Order()));
        assertSame(order, (new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                mock(ProductOrderRepository.class))).updateOrderCancel(1L));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderCancel(Long)}
     */
    @Test
    void testUpdateOrderCancel2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenThrow(new RuntimeException("An error occurred"));
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Order()));
        assertThrows(RuntimeException.class, () -> (new OrderService(orderRepository,
                new SessionUser(mock(UserRepository.class)), mock(ProductOrderRepository.class))).updateOrderCancel(1L));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderCancel(Long)}
     */
    @Test
    void testUpdateOrderCancel3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(order2, (new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                mock(ProductOrderRepository.class))).updateOrderCancel(1L));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderCancel(Long)}
     */
    @Test
    void testUpdateOrderCancel4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> (new OrderService(orderRepository,
                new SessionUser(mock(UserRepository.class)), mock(ProductOrderRepository.class))).updateOrderCancel(1L));
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderCancel(Long)}
     */
    @Test
    void testUpdateOrderCancel5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        Order order = mock(Order.class);
        doThrow(new RuntimeException("An error occurred")).when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> (new OrderService(orderRepository,
                new SessionUser(mock(UserRepository.class)), mock(ProductOrderRepository.class))).updateOrderCancel(1L));
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Order()));
        assertSame(order, (new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                mock(ProductOrderRepository.class))).updateOrderStatus(1L, OrderStatus.NEW));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenThrow(new RuntimeException("An error occurred"));
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Order()));
        assertThrows(RuntimeException.class,
                () -> (new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                        mock(ProductOrderRepository.class))).updateOrderStatus(1L, OrderStatus.NEW));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(order2, (new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                mock(ProductOrderRepository.class))).updateOrderStatus(1L, OrderStatus.NEW));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
                () -> (new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                        mock(ProductOrderRepository.class))).updateOrderStatus(1L, OrderStatus.NEW));
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(order2, (new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                mock(ProductOrderRepository.class))).updateOrderStatus(1L, OrderStatus.CANCELED));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(order2, (new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                mock(ProductOrderRepository.class))).updateOrderStatus(1L, OrderStatus.DELIVERED));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(order2, (new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                mock(ProductOrderRepository.class))).updateOrderStatus(1L, OrderStatus.RETURNED));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus8() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(order2, (new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                mock(ProductOrderRepository.class))).updateOrderStatus(1L, OrderStatus.COMPLETED));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus9() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        Order order = mock(Order.class);
        doThrow(new RuntimeException("An error occurred")).when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(new Order());
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class,
                () -> (new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                        mock(ProductOrderRepository.class))).updateOrderStatus(1L, OrderStatus.NEW));
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus10() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(order2, (new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                mock(ProductOrderRepository.class))).updateOrderStatus(1L, OrderStatus.PROCESSING));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    }

    /**
     * Method under test: {@link OrderService#updateOrderStatus(Long, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus11() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        Order order = mock(Order.class);
        doNothing().when(order).setStatus(Mockito.<OrderStatus>any());
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Order order2 = new Order();
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(order2, (new OrderService(orderRepository, new SessionUser(mock(UserRepository.class)),
                mock(ProductOrderRepository.class))).updateOrderStatus(1L, OrderStatus.PAYMENT_PERIOD));
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        verify(order).setStatus(Mockito.<OrderStatus>any());
    @Disabled("TODO: Complete this test")
    void testGetAllOrders2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at uz.market.uzum.services.OrderService.getAllOrders(OrderService.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderPaginationRepository.findAll((Pageable) any())).thenThrow(new NullPointerException());
        orderService.getAllOrders(null);
    }

    /**
     * Method under test: {@link OrderService#payForOrder(PayOrderDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPayForOrder() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Collection.stream()" because "that" is null
        //       at uz.market.uzum.services.OrderService.payForOrder(OrderService.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderRepository.findById((Long) any())).thenReturn(Optional.of(new Order()));
        orderService.payForOrder(new PayOrderDTO(1L, 1L, 10));
    }

    /**
     * Method under test: {@link OrderService#payForOrder(PayOrderDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPayForOrder2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "uz.market.uzum.enums.Payment.equals(Object)" because the return value of "uz.market.uzum.domains.product.Order.getPayment()" is null
        //       at uz.market.uzum.services.OrderService.payForOrder(OrderService.java:48)
        //   See https://diff.blue/R013 to resolve this issue.

        Order order = new Order();
        order.setProducts(new ArrayList<>());
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findById((Long) any())).thenReturn(ofResult);
        orderService.payForOrder(new PayOrderDTO(1L, 1L, 10));
    }

    /**
     * Method under test: {@link OrderService#payForOrder(PayOrderDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPayForOrder3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "uz.market.uzum.domains.product.Product.getPrice()" because the return value of "uz.market.uzum.domains.product.ProductOrder.getProduct()" is null
        //       at uz.market.uzum.services.OrderService.lambda$payForOrder$2(OrderService.java:46)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.reduce(ReferencePipeline.java:657)
        //       at uz.market.uzum.services.OrderService.payForOrder(OrderService.java:47)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(new ProductOrder());

        Order order = new Order();
        order.setProducts(productOrderList);
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.findById((Long) any())).thenReturn(ofResult);
        orderService.payForOrder(new PayOrderDTO(1L, 1L, 10));
    }

    /**
     * Method under test: {@link OrderService#payForOrder(PayOrderDTO)}
     */
    @Test
    void testPayForOrder4() {
        ArrayList<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(new ProductOrder());
        Order order = mock(Order.class);
        doNothing().when(order).setStatus((OrderStatus) any());
        when(order.getProducts()).thenReturn(new ArrayList<>());
        when(order.getPayment()).thenReturn(Payment.CASH);
        doNothing().when(order).setProducts((Collection<ProductOrder>) any());
        order.setProducts(productOrderList);
        Optional<Order> ofResult = Optional.of(order);
        Order order1 = new Order();
        when(orderRepository.save((Order) any())).thenReturn(order1);
        when(orderRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(order1, orderService.payForOrder(new PayOrderDTO(1L, 1L, 10)));
        verify(orderRepository).save((Order) any());
        verify(orderRepository).findById((Long) any());
        verify(order).getProducts();
        verify(order).getPayment();
        verify(order).setProducts((Collection<ProductOrder>) any());
        verify(order).setStatus((OrderStatus) any());
    }

    /**
     * Method under test: {@link OrderService#payForOrder(PayOrderDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPayForOrder5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at uz.market.uzum.services.OrderService.payForCard(OrderService.java:60)
        //       at uz.market.uzum.services.OrderService.payForOrder(OrderService.java:51)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(new ProductOrder());
        Order order = mock(Order.class);
        doThrow(new NullPointerException()).when(order).setStatus((OrderStatus) any());
        when(order.getProducts()).thenReturn(new ArrayList<>());
        when(order.getPayment()).thenReturn(Payment.CASH);
        doNothing().when(order).setProducts((Collection<ProductOrder>) any());
        order.setProducts(productOrderList);
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.save((Order) any())).thenReturn(new Order());
        when(orderRepository.findById((Long) any())).thenReturn(ofResult);
        orderService.payForOrder(new PayOrderDTO(1L, 1L, 10));
    }

    /**
     * Method under test: {@link OrderService#payForOrder(PayOrderDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPayForOrder6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "uz.market.uzum.repositories.OrderRepository.findById(Object)" is null
        //       at uz.market.uzum.services.OrderService.payForOrder(OrderService.java:44)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderRepository.save((Order) any())).thenReturn(new Order());
        when(orderRepository.findById((Long) any())).thenReturn(null);

        ArrayList<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(new ProductOrder());
        Order order = mock(Order.class);
        doNothing().when(order).setStatus((OrderStatus) any());
        when(order.getProducts()).thenReturn(new ArrayList<>());
        when(order.getPayment()).thenReturn(Payment.CASH);
        doNothing().when(order).setProducts((Collection<ProductOrder>) any());
        order.setProducts(productOrderList);
        orderService.payForOrder(new PayOrderDTO(1L, 1L, 10));
    }

    /**
     * Method under test: {@link OrderService#payForOrder(PayOrderDTO)}
     */
    @Test
    void testPayForOrder7() {
        when(orderRepository.save((Order) any())).thenReturn(new Order());
        when(orderRepository.findById((Long) any())).thenReturn(Optional.empty());

        ArrayList<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(new ProductOrder());
        Order order = mock(Order.class);
        doNothing().when(order).setStatus((OrderStatus) any());
        when(order.getProducts()).thenReturn(new ArrayList<>());
        when(order.getPayment()).thenReturn(Payment.CASH);
        doNothing().when(order).setProducts((Collection<ProductOrder>) any());
        order.setProducts(productOrderList);
        assertThrows(RuntimeException.class, () -> orderService.payForOrder(new PayOrderDTO(1L, 1L, 10)));
        verify(orderRepository).findById((Long) any());
        verify(order).setProducts((Collection<ProductOrder>) any());
    }

    /**
     * Method under test: {@link OrderService#payForOrder(PayOrderDTO)}
     */
    @Test
    void testPayForOrder8() {
        ArrayList<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(new ProductOrder());
        Order order = mock(Order.class);
        doNothing().when(order).setStatus((OrderStatus) any());
        when(order.getProducts()).thenReturn(new ArrayList<>());
        when(order.getPayment()).thenReturn(Payment.CASH);
        doNothing().when(order).setProducts((Collection<ProductOrder>) any());
        order.setProducts(productOrderList);
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.save((Order) any())).thenReturn(new Order());
        when(orderRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> orderService.payForOrder(new PayOrderDTO(1L, 1L, -1)));
        verify(orderRepository).findById((Long) any());
        verify(order).getProducts();
        verify(order).getPayment();
        verify(order).setProducts((Collection<ProductOrder>) any());
    }

    /**
     * Method under test: {@link OrderService#payForOrder(PayOrderDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPayForOrder9() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Integer.intValue()" because the return value of "uz.market.uzum.dtos.order.PayOrderDTO.amount()" is null
        //       at uz.market.uzum.services.OrderService.payForCard(OrderService.java:59)
        //       at uz.market.uzum.services.OrderService.payForOrder(OrderService.java:51)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(new ProductOrder());
        Order order = mock(Order.class);
        doNothing().when(order).setStatus((OrderStatus) any());
        when(order.getProducts()).thenReturn(new ArrayList<>());
        when(order.getPayment()).thenReturn(Payment.CASH);
        doNothing().when(order).setProducts((Collection<ProductOrder>) any());
        order.setProducts(productOrderList);
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.save((Order) any())).thenReturn(new Order());
        when(orderRepository.findById((Long) any())).thenReturn(ofResult);
        orderService.payForOrder(new PayOrderDTO(1L, 1L, null));
    }

    /**
     * Method under test: {@link OrderService#payForOrder(PayOrderDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPayForOrder10() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: payOrderDTO is marked non-null but is null
        //       at uz.market.uzum.services.OrderService.payForOrder(OrderService.java:42)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(new ProductOrder());
        Order order = mock(Order.class);
        doNothing().when(order).setStatus((OrderStatus) any());
        when(order.getProducts()).thenReturn(new ArrayList<>());
        when(order.getPayment()).thenReturn(Payment.CASH);
        doNothing().when(order).setProducts((Collection<ProductOrder>) any());
        order.setProducts(productOrderList);
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.save((Order) any())).thenReturn(new Order());
        when(orderRepository.findById((Long) any())).thenReturn(ofResult);
        orderService.payForOrder(null);
    }

    /**
     * Method under test: {@link OrderService#payForOrder(PayOrderDTO)}
     */
    @Test
    void testPayForOrder11() {
        ArrayList<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(new ProductOrder());
        Order order = mock(Order.class);
        doNothing().when(order).setStatus((OrderStatus) any());
        when(order.getProducts()).thenReturn(new ArrayList<>());
        when(order.getPayment()).thenReturn(Payment.INSTALLMENT);
        doNothing().when(order).setProducts((Collection<ProductOrder>) any());
        order.setProducts(productOrderList);
        Optional<Order> ofResult = Optional.of(order);
        Order order1 = new Order();
        when(orderRepository.save((Order) any())).thenReturn(order1);
        when(orderRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(order1, orderService.payForOrder(new PayOrderDTO(1L, 1L, 10)));
        verify(orderRepository).save((Order) any());
        verify(orderRepository, atLeast(1)).findById((Long) any());
        verify(order).getProducts();
        verify(order).getPayment();
        verify(order).setProducts((Collection<ProductOrder>) any());
        verify(order).setStatus((OrderStatus) any());
    }

    /**
     * Method under test: {@link OrderService#payForOrder(PayOrderDTO)}
     */
    @Test
    void testPayForOrder12() {
        ArrayList<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(new ProductOrder());
        Order order = mock(Order.class);
        doThrow(new RuntimeException()).when(order).setStatus((OrderStatus) any());
        when(order.getProducts()).thenReturn(new ArrayList<>());
        when(order.getPayment()).thenReturn(Payment.INSTALLMENT);
        doNothing().when(order).setProducts((Collection<ProductOrder>) any());
        order.setProducts(productOrderList);
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.save((Order) any())).thenReturn(new Order());
        when(orderRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> orderService.payForOrder(new PayOrderDTO(1L, 1L, 10)));
        verify(orderRepository, atLeast(1)).findById((Long) any());
        verify(order).getProducts();
        verify(order).getPayment();
        verify(order).setProducts((Collection<ProductOrder>) any());
        verify(order).setStatus((OrderStatus) any());
    }

    /**
     * Method under test: {@link OrderService#payForCard(Order, Double, PayOrderDTO)}
     */
    @Test
    void testPayForCard() {
        Order order = new Order();
        when(orderRepository.save((Order) any())).thenReturn(order);
        Order order1 = new Order();
        assertSame(order, orderService.payForCard(order1, 10.0d, new PayOrderDTO(1L, 1L, 10)));
        verify(orderRepository).save((Order) any());
        assertEquals(OrderStatus.COMPLETED, order1.getStatus());
    }

    /**
     * Method under test: {@link OrderService#payForCard(Order, Double, PayOrderDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPayForCard2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "uz.market.uzum.domains.product.Order.setStatus(uz.market.uzum.enums.OrderStatus)" because "order" is null
        //       at uz.market.uzum.services.OrderService.payForCard(OrderService.java:60)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderRepository.save((Order) any())).thenReturn(new Order());
        orderService.payForCard(null, 10.0d, new PayOrderDTO(1L, 1L, 10));
    }

    /**
     * Method under test: {@link OrderService#payForCard(Order, Double, PayOrderDTO)}
     */
    @Test
    void testPayForCard3() {
        when(orderRepository.save((Order) any())).thenReturn(new Order());
        Order order = new Order();
        assertThrows(RuntimeException.class,
                () -> orderService.payForCard(order, Double.NaN, new PayOrderDTO(1L, 1L, 10)));
    }

    /**
     * Method under test: {@link OrderService#payForCard(Order, Double, PayOrderDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPayForCard4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "uz.market.uzum.dtos.order.PayOrderDTO.amount()" because "payOrderDTO" is null
        //       at uz.market.uzum.services.OrderService.payForCard(OrderService.java:59)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderRepository.save((Order) any())).thenReturn(new Order());
        orderService.payForCard(new Order(), 10.0d, null);
    }

    /**
     * Method under test: {@link OrderService#payForCard(Order, Double, PayOrderDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPayForCard5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at uz.market.uzum.services.OrderService.payForCard(OrderService.java:61)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderRepository.save((Order) any())).thenThrow(new NullPointerException());
        Order order = new Order();
        orderService.payForCard(order, 10.0d, new PayOrderDTO(1L, 1L, 10));
    }

    /**
     * Method under test: {@link OrderService#payForInstallment(Order, Double, PayOrderDTO)}
     */
    @Test
    void testPayForInstallment() {
        Order order = new Order();
        when(orderRepository.save((Order) any())).thenReturn(order);
        when(orderRepository.findById((Long) any())).thenReturn(Optional.of(new Order()));
        Order order1 = new Order();
        assertSame(order, orderService.payForInstallment(order1, 10.0d, new PayOrderDTO(1L, 1L, 10)));
        verify(orderRepository).save((Order) any());
        verify(orderRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link OrderService#payForInstallment(Order, Double, PayOrderDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPayForInstallment2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at uz.market.uzum.services.OrderService.payForInstallment(OrderService.java:73)
        //   See https://diff.blue/R013 to resolve this issue.

        when(orderRepository.save((Order) any())).thenThrow(new NullPointerException());
        when(orderRepository.findById((Long) any())).thenReturn(Optional.of(new Order()));
        Order order = new Order();
        orderService.payForInstallment(order, 10.0d, new PayOrderDTO(1L, 1L, 10));
    }

    /**
     * Method under test: {@link OrderService#payForInstallment(Order, Double, PayOrderDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPayForInstallment3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at uz.market.uzum.services.OrderService.payForInstallment(OrderService.java:72)
        //   See https://diff.blue/R013 to resolve this issue.

        Order order = mock(Order.class);
        doThrow(new NullPointerException()).when(order).setStatus((OrderStatus) any());
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.save((Order) any())).thenReturn(new Order());
        when(orderRepository.findById((Long) any())).thenReturn(ofResult);
        Order order1 = new Order();
        orderService.payForInstallment(order1, 10.0d, new PayOrderDTO(1L, 1L, 10));
    }

    /**
     * Method under test: {@link OrderService#payForInstallment(Order, Double, PayOrderDTO)}
     */
    @Test
    void testPayForInstallment4() {
        when(orderRepository.save((Order) any())).thenReturn(new Order());
        when(orderRepository.findById((Long) any())).thenReturn(Optional.empty());
        new NullPointerException();
        Order order = new Order();
        assertThrows(RuntimeException.class,
                () -> orderService.payForInstallment(order, 10.0d, new PayOrderDTO(1L, 1L, 10)));
        verify(orderRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link OrderService#payForInstallment(Order, Double, PayOrderDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPayForInstallment5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "uz.market.uzum.dtos.order.PayOrderDTO.orderId()" because "payOrderDTO" is null
        //       at uz.market.uzum.services.OrderService.payForInstallment(OrderService.java:69)
        //   See https://diff.blue/R013 to resolve this issue.

        Order order = mock(Order.class);
        doThrow(new NullPointerException()).when(order).setStatus((OrderStatus) any());
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.save((Order) any())).thenReturn(new Order());
        when(orderRepository.findById((Long) any())).thenReturn(ofResult);
        orderService.payForInstallment(new Order(), 10.0d, null);
    }

    /**
     * Method under test: {@link OrderService#payForInstallment(Order, Double, PayOrderDTO)}
     */
    @Test
    void testPayForInstallment6() {
        Order order = mock(Order.class);
        doThrow(new NullPointerException()).when(order).setStatus((OrderStatus) any());
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepository.save((Order) any())).thenReturn(new Order());
        when(orderRepository.findById((Long) any())).thenReturn(ofResult);
        Order order1 = new Order();
        assertThrows(RuntimeException.class,
                () -> orderService.payForInstallment(order1, Double.NaN, new PayOrderDTO(1L, 1L, 10)));
        verify(orderRepository).findById((Long) any());
    }
}

