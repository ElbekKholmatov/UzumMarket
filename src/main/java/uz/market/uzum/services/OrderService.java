package uz.market.uzum.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.market.uzum.configuration.security.SessionUser;
import uz.market.uzum.domains.product.Order;
import uz.market.uzum.domains.product.ProductOrder;
import uz.market.uzum.dtos.AddToOrderDTO;
import uz.market.uzum.enums.OrderStatus;
import uz.market.uzum.domains.product.Order;
import uz.market.uzum.dtos.order.AddToOrderDTO;
import uz.market.uzum.dtos.order.PayOrderDTO;
import uz.market.uzum.enums.OrderStatus;
import uz.market.uzum.enums.Payment;
import uz.market.uzum.mappers.product.OrderMapper;
import uz.market.uzum.repositories.OrderRepository;
import uz.market.uzum.repositories.ProductOrderRepository;
import uz.market.uzum.services.user.UserService;

import java.util.Collection;

import uz.market.uzum.repositories.order.OrderPaginationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final SessionUser sessionUser;
    private final ProductOrderRepository productOrderRepository;


    public Order addToOrder(AddToOrderDTO addToOrderDTO) {
        Long userId = sessionUser.id();
        Collection<ProductOrder> allProducts = productOrderRepository.findALLByIds(addToOrderDTO.productIds());
        return orderRepository.save(Order.childBuilder()
                .userId(userId)
                .status(OrderStatus.NEW)
                .payment(addToOrderDTO.payment())
                .products(allProducts)
                .build());
    }


    @Cacheable(value = "orders", key = "#pageable.pageNumber")
    public Page<Order> getAllNewOrders(Pageable pageable) {
        return orderRepository.findAllByStatus(pageable);
    }
    private final OrderMapper orderMapper;
    private final OrderPaginationRepository orderPaginationRepository;

    public AddToOrderDTO addToOrderInstallment(AddToOrderDTO addToOrderDTO) {
        Order order = orderMapper.toOrder(addToOrderDTO);
        return orderMapper.toAppToOrderDTO(orderRepository.save(order));
    }

    public AddToOrderDTO getOrderInstallment(Long id) {
     Order order=orderRepository.findById(id)
             .orElseThrow(()->new RuntimeException("Order not found"));
       return orderMapper.toAppToOrderDTO(order);
    }
    @Cacheable(value = "orders", key = "#pageable.pageNumber")
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAllOnPaying(pageable);
    }

    public Page<Order> getAllOrders(Pageable pageable) {
       return orderPaginationRepository.findAll(pageable);

    }

    public Order payForOrder(@NonNull PayOrderDTO payOrderDTO){
        Order orderFound = orderRepository.findById(payOrderDTO.orderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Double prices= orderFound.getProducts().stream()
                .map(productOrder -> productOrder.getProduct().getPrice())
                .reduce(0.0,Double::sum);
        if(orderFound.getPayment().equals(Payment.INSTALLMENT)){
            return payForInstallment(orderFound,prices, payOrderDTO);
        }
        else return payForCard(orderFound,prices,payOrderDTO);


    }

    public Order payForCard(Order order, Double prices, PayOrderDTO payOrderDTO) {


        if(prices-payOrderDTO.amount()<=0){
            order.setStatus(OrderStatus.COMPLETED);
            return orderRepository.save(order);
        }
        else{
            throw new RuntimeException("Sum is not enough to complete order");
        }
    }

    public Order payForInstallment(Order order, Double prices,PayOrderDTO payOrderDTO) {
        Order orderFound = orderRepository.findById(payOrderDTO.orderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        if(prices-payOrderDTO.amount()<=0){
            orderFound.setStatus(OrderStatus.COMPLETED);
            return orderRepository.save(orderFound);
        }
        else{
            throw new RuntimeException("Sum is not enough to complete order");
        }
    }
}
