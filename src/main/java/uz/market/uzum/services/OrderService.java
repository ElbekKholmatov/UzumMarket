package uz.market.uzum.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.market.uzum.repositories.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;


}
