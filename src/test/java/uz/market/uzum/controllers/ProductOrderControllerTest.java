package uz.market.uzum.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import uz.market.uzum.domains.product.Basket;
import uz.market.uzum.repositories.BasketRepository;
import uz.market.uzum.repositories.CategoryRepository;
import uz.market.uzum.repositories.ProductOrderRepository;
import uz.market.uzum.repositories.ProductRepository;
import uz.market.uzum.repositories.user.UserRepository;
import uz.market.uzum.services.CategoryService;
import uz.market.uzum.services.ProductOrderService;
import uz.market.uzum.services.ProductService;
import uz.market.uzum.services.basket.BasketService;
import uz.market.uzum.services.user.UserService;

class ProductOrderControllerTest {
    @Test
    @Disabled("TODO: Complete this test")
    void testGetBasket() {

        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        ProductOrderService productOrderService = new ProductOrderService(productOrderRepository,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)));

        BasketRepository basketRepository = mock(BasketRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        (new ProductOrderController(productOrderService,
                new BasketService(basketRepository, userService,
                        new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                        mock(ProductOrderRepository.class)))).getBasket();
    }
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

