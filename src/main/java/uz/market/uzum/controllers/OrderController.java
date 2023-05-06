package uz.market.uzum.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.market.uzum.domains.product.Order;
import uz.market.uzum.dtos.order.AddToOrderDTO;
import uz.market.uzum.dtos.order.PayOrderDTO;
import uz.market.uzum.services.OrderService;



@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
@Tag(name = "Order", description = "API to work with orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("add/to/order")
    public ResponseEntity<AddToOrderDTO> addToOrder(
            @Valid @ParameterObject AddToOrderDTO addToOrderDTO
    ) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201))
                .body(orderService.addToOrderInstallment(addToOrderDTO));
    }

    @GetMapping("get/order/{id}")
    public ResponseEntity<AddToOrderDTO> getOrder(@PathVariable Long id){
        return ResponseEntity.ok().body(orderService.getOrderInstallment(id));
    }

    @GetMapping("get/allOrders")
    public ResponseEntity<Page<Order>> getAllOrders(@RequestParam(name="size", defaultValue = "10") int size,
                                                            @RequestParam(name="page", defaultValue = "0") int page){
        Pageable pageable= PageRequest.of(page,size);
    return ResponseEntity.ok().body(orderService.getAllOrders(pageable));
    }



    @PostMapping("pay/to/order")
    public ResponseEntity<Order> payForOrder(@RequestBody PayOrderDTO payOrderDTO){
            return ResponseEntity.ok().body(orderService.payForOrder(payOrderDTO));
    }



}



