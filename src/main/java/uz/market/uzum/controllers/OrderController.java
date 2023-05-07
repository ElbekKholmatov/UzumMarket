package uz.market.uzum.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.market.uzum.domains.product.Order;
import uz.market.uzum.dtos.order.AddToOrderDTO;
import uz.market.uzum.enums.OrderStatus;
import uz.market.uzum.services.OrderService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
@Tag(name = "Order", description = "API to work with orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("add/to/order")
    public ResponseEntity<Order> addToOrder(
            @Valid @ParameterObject AddToOrderDTO addToOrderDTO
    ) {
        return ResponseEntity.ok(orderService.addToOrder(addToOrderDTO));
    }

    @GetMapping("get/all/new")
    public Page<Order> getAllNewOrders(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return orderService.getAllNewOrders(pageable);
    }


    @GetMapping("get/all/payment")
    public Page<Order> getAllOrders(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return orderService.getAllOrders(pageable);
    }

    @PutMapping("update/cancel/{id}")
    public ResponseEntity<Order> updateOrderCancel(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(orderService.updateOrderCancel(id));
    }

    @PutMapping("update/change/status/{id}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable("id") Long id,
            @RequestParam("status") OrderStatus status
    ) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }
}
