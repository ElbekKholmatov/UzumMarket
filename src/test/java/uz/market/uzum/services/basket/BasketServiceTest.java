package uz.market.uzum.services.basket;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.market.uzum.domains.product.Basket;
import uz.market.uzum.domains.product.Product;
import uz.market.uzum.domains.product.ProductOrder;
import uz.market.uzum.domains.user.User;
import uz.market.uzum.dtos.ProductOrderDTO;
import uz.market.uzum.exceptions.ItemNotFoundException;
import uz.market.uzum.repositories.BasketRepository;
import uz.market.uzum.repositories.CategoryRepository;
import uz.market.uzum.repositories.ProductOrderRepository;
import uz.market.uzum.repositories.ProductRepository;
import uz.market.uzum.repositories.user.UserRepository;
import uz.market.uzum.services.CategoryService;
import uz.market.uzum.services.ProductService;
import uz.market.uzum.services.user.UserService;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BasketService.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BasketServiceTest {
    @MockBean
    private BasketRepository basketRepository;

    @Autowired
    private BasketService basketService;

    @MockBean
    private ProductOrderRepository productOrderRepository;

    @MockBean
    private ProductService productService;

    @MockBean
    private UserService userService;

    @Test
    void addProductTest() {
        Basket basket = new Basket();
        basket.setId(1L);
        basket.setProduct(new ArrayList<ProductOrder>());
        when(this.basketRepository.save((Basket) Mockito.any())).thenReturn(basket);
        when(this.productService.getProductById((Long) Mockito.any())).thenReturn(new Product());
        when(this.basketRepository.findById((Long) Mockito.any())).thenReturn(Optional.<Basket>empty());
        assertThrows(ItemNotFoundException.class, () -> this.basketService.addProduct(new ProductOrderDTO(), "6625550144"));
    }


    @Test
    void testGetBasketByPhoneNumber() {

        BasketRepository basketRepository = mock(BasketRepository.class);
        Basket basket = new Basket();
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        assertSame(basket,
                (new BasketService(basketRepository, userService,
                        new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                        mock(ProductOrderRepository.class))).getBasketByPhoneNumber("jane.doe@example.org"));
        verify(basketRepository).findByUserId(Mockito.<Long>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    @Test
    void testGetBasketByPhoneNumber3() {

        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.empty());
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        assertThrows(ItemNotFoundException.class,
                () -> (new BasketService(basketRepository, userService,
                        new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                        mock(ProductOrderRepository.class))).getBasketByPhoneNumber("jane.doe@example.org"));
        verify(basketRepository).findByUserId(Mockito.<Long>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    @Test
    void testGetBasketByPhoneNumber6() {
        BasketRepository basketRepository = mock(BasketRepository.class);
        Basket basket = new Basket();
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserService userService = mock(UserService.class);
        when(userService.getUserByPhoneNumber(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        assertSame(basket,
                (new BasketService(basketRepository, userService,
                        new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                        mock(ProductOrderRepository.class))).getBasketByPhoneNumber("jane.doe@example.org"));
        verify(basketRepository).findByUserId(Mockito.<Long>any());
        verify(userService).getUserByPhoneNumber(Mockito.<String>any());
    }

    @Test
    void testGetBasketByPhoneNumber7() {
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(new Basket()));
        UserService userService = mock(UserService.class);
        when(userService.getUserByPhoneNumber(Mockito.<String>any())).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class,
                () -> (new BasketService(basketRepository, userService,
                        new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                        mock(ProductOrderRepository.class))).getBasketByPhoneNumber("jane.doe@example.org"));
        verify(userService).getUserByPhoneNumber(Mockito.<String>any());
    }


    @Test
    void testRemoveProduct3() {

        Basket basket = new Basket();
        basket.setProduct(new ArrayList<>());
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(new Product()));
        assertThrows(ItemNotFoundException.class,
                () -> (new BasketService(basketRepository, userService,
                        new ProductService(new CategoryService(mock(CategoryRepository.class)), productRepository),
                        mock(ProductOrderRepository.class))).removeProduct(1L, "6625550144"));
        verify(basketRepository).findByUserId(Mockito.<Long>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(productRepository).findById(Mockito.<Integer>any());
    }

    @Test
    void testRemoveProduct5() {

        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.empty());
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(new Product()));
        assertThrows(ItemNotFoundException.class,
                () -> (new BasketService(basketRepository, userService,
                        new ProductService(new CategoryService(mock(CategoryRepository.class)), productRepository),
                        mock(ProductOrderRepository.class))).removeProduct(1L, "6625550144"));
        verify(basketRepository).findByUserId(Mockito.<Long>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

}

