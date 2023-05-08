package uz.market.uzum.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.market.uzum.domains.product.Basket;
import uz.market.uzum.services.basket.BasketService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class BasketController {


    private final BasketService basketService;

    @GetMapping("/")
    public ResponseEntity<Basket> get() {
        Basket basket = basketService.getBasket();
        return ResponseEntity.ok().body(basket);
    }

}
