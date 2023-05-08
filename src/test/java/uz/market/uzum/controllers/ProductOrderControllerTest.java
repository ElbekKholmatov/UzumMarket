package uz.market.uzum.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import uz.market.uzum.domains.product.Basket;
import uz.market.uzum.repositories.CategoryRepository;
import uz.market.uzum.repositories.ProductOrderRepository;
import uz.market.uzum.repositories.ProductRepository;
import uz.market.uzum.services.CategoryService;
import uz.market.uzum.services.ProductOrderService;
import uz.market.uzum.services.ProductService;
import uz.market.uzum.services.basket.BasketService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ProductOrderControllerTest {
    @Test
    void testGetBasket2() {

        BasketService basketService = mock(BasketService.class);
        when(basketService.getBasket()).thenReturn(new Basket());
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        ResponseEntity<Basket> actualBasket = (new ProductOrderController(
                new ProductOrderService(productOrderRepository,
                        new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class))),
                basketService)).getBasket();
        assertTrue(actualBasket.hasBody());
        assertTrue(actualBasket.getHeaders().isEmpty());
        assertEquals(200, actualBasket.getStatusCodeValue());
        verify(basketService).getBasket();
    }
}

