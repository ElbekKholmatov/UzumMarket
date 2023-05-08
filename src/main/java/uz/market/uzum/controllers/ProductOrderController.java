package uz.market.uzum.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.market.uzum.domains.product.Basket;
import uz.market.uzum.domains.product.ProductOrder;
import uz.market.uzum.dtos.ProductOrderDTO;
import uz.market.uzum.services.ProductOrderService;
import uz.market.uzum.services.basket.BasketService;

@RestController(value = "BasketController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/basket")
public class ProductOrderController {

    private final ProductOrderService productOrderService;
    private final BasketService basketService;

    @PostMapping("/addProduct")
    public ResponseEntity<ProductOrder> add(@RequestBody @Valid ProductOrderDTO dto) {
        String phoneNumber = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        ProductOrder pOrder=productOrderService.add(dto);
        ProductOrder pOrder = basketService.addProduct(dto, phoneNumber);
        return ResponseEntity.ok().body(pOrder);
    }

    @PostMapping("/removeProduct")
    public ResponseEntity<ProductOrder> add(@RequestBody Long productId) {
        String phoneNumber = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        ProductOrder pOrder=productOrderService.add(dto);
        ProductOrder pOrder = basketService.removeProduct(productId, phoneNumber);
        return ResponseEntity.ok().body(pOrder);
    }


    @GetMapping("/getBasket")
    public ResponseEntity<Basket> getBasket() {
        Basket basket = basketService.getBasket();
        return ResponseEntity.ok().body(basket);
    }


}
