package uz.market.uzum.services.basket;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.market.uzum.domains.product.Basket;
import uz.market.uzum.domains.product.Product;
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

@ContextConfiguration(classes = {BasketService.class})
@ExtendWith(SpringExtension.class)
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
    @Disabled("TODO: Complete this test")
    void testGetBasket() {

        basketService.getBasket();
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct() {
        BasketRepository basketRepository = mock(BasketRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        (new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class))).addProduct(null, null);
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct2() {
        BasketRepository basketRepository = mock(BasketRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), null);
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct3() {

        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(new Basket()));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), "6625550144");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct4() {

        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenThrow(new NullPointerException("Basket not found"));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), "6625550144");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct5() {

        Basket basket = mock(Basket.class);
        when(basket.getProduct()).thenThrow(new NullPointerException("foo"));
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), "6625550144");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct6() {

        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(null);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), "6625550144");
    }

    @Test
    void testAddProduct7() {

        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.empty());
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        assertThrows(ItemNotFoundException.class, () -> basketService.addProduct(new ProductOrderDTO(), "6625550144"));
        verify(basketRepository).findByUserId(Mockito.<Long>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct8() {
        Basket basket = mock(Basket.class);
        when(basket.getProduct()).thenThrow(new NullPointerException("foo"));
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(null);
        UserService userService = new UserService(userRepository);
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), "6625550144");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct9() {

        Basket basket = mock(Basket.class);
        when(basket.getProduct()).thenThrow(new NullPointerException("foo"));
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(mock(User.class));
        UserService userService = new UserService(userRepository);
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), "6625550144");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct10() {

        Basket basket = mock(Basket.class);
        when(basket.getProduct()).thenThrow(new NullPointerException("foo"));
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserService userService = mock(UserService.class);
        when(userService.getUserByPhoneNumber(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), "6625550144");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct11() {

        Basket basket = mock(Basket.class);
        when(basket.getProduct()).thenThrow(new NullPointerException("foo"));
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserService userService = mock(UserService.class);
        when(userService.getUserByPhoneNumber(Mockito.<String>any())).thenReturn(null);
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), "6625550144");
    }

    @Test
    void testAddProduct12() {

        Basket basket = mock(Basket.class);
        when(basket.getProduct()).thenThrow(new NullPointerException("foo"));
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserService userService = mock(UserService.class);
        when(userService.getUserByPhoneNumber(Mockito.<String>any())).thenReturn(Optional.empty());
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        assertThrows(ItemNotFoundException.class, () -> basketService.addProduct(new ProductOrderDTO(), "6625550144"));
        verify(userService).getUserByPhoneNumber(Mockito.<String>any());
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
    @Disabled("TODO: Complete this test")
    void testGetBasketByPhoneNumber2() {

        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenThrow(new NullPointerException("Basket not found"));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        (new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class))).getBasketByPhoneNumber("jane.doe@example.org");
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
    @Disabled("TODO: Complete this test")
    void testGetBasketByPhoneNumber4() {

        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(new Basket()));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(null);
        UserService userService = new UserService(userRepository);
        (new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class))).getBasketByPhoneNumber("jane.doe@example.org");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGetBasketByPhoneNumber5() {


        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(new Basket()));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(mock(User.class));
        UserService userService = new UserService(userRepository);
        (new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class))).getBasketByPhoneNumber("jane.doe@example.org");
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
    @Disabled("TODO: Complete this test")
    void testRemoveProduct() {

        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(new Basket()));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(new Product()));
        (new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), productRepository),
                mock(ProductOrderRepository.class))).removeProduct(1L, "6625550144");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testRemoveProduct2() {
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(new Basket()));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(Mockito.<Integer>any())).thenThrow(new NullPointerException("Product not found"));
        (new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), productRepository),
                mock(ProductOrderRepository.class))).removeProduct(1L, "6625550144");
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
    @Disabled("TODO: Complete this test")
    void testRemoveProduct4() {

        Basket basket = mock(Basket.class);
        when(basket.getProduct()).thenThrow(new NullPointerException("foo"));
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(new Product()));
        (new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), productRepository),
                mock(ProductOrderRepository.class))).removeProduct(1L, "6625550144");
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

    @Test
    @Disabled("TODO: Complete this test")
    void testRemoveProduct6() {

        Basket basket = mock(Basket.class);
        when(basket.getProduct()).thenThrow(new NullPointerException("foo"));
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(null);
        UserService userService = new UserService(userRepository);
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(new Product()));
        (new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), productRepository),
                mock(ProductOrderRepository.class))).removeProduct(1L, "6625550144");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testRemoveProduct7() {
        Basket basket = mock(Basket.class);
        when(basket.getProduct()).thenThrow(new NullPointerException("foo"));
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(mock(User.class));
        UserService userService = new UserService(userRepository);
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(new Product()));
        (new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), productRepository),
                mock(ProductOrderRepository.class))).removeProduct(1L, "6625550144");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testRemoveProduct8() {
        Basket basket = mock(Basket.class);
        when(basket.getProduct()).thenThrow(new NullPointerException("foo"));
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserService userService = mock(UserService.class);
        when(userService.getUserByPhoneNumber(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(new Product()));
        (new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), productRepository),
                mock(ProductOrderRepository.class))).removeProduct(1L, "6625550144");
    }

}

