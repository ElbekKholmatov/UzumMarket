package uz.market.uzum.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.market.uzum.domains.product.Order;
import uz.market.uzum.dtos.AddToOrderDTO;
import uz.market.uzum.services.OrderService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
@Tag(name = "Order", description = "API to work with orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("add/to/order")
    public Order addToOrder(
            @Valid @ParameterObject AddToOrderDTO addToOrderDTO
    ) {
        return null;
    }
}
