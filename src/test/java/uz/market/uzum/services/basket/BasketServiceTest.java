package uz.market.uzum.services.basket;

import java.util.Optional;

import org.apache.http.util.Asserts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.market.uzum.domains.product.Basket;
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

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {BasketService.class})
@ExtendWith(SpringExtension.class)
class BasketServiceTest {

    @MockBean
    private ProductOrderRepository productOrderRepository;

    @MockBean
    private ProductService productService;

    @MockBean
    private UserService userService;

    @Autowired
    private BasketService basketService;

    @MockBean
    private BasketRepository basketRepository;

    /**
     * Method under test: {@link BasketService#getBasket()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetBasket() {
        // TODO: Complete this test.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        basketService.getBasket();
    }

    /**
     * Method under test: {@link BasketService#addProduct(ProductOrderDTO, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: pOrder is marked non-null but is null
        //       at uz.market.uzum.services.basket.BasketService.addProduct(BasketService.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        BasketRepository basketRepository = mock(BasketRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        (new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class))).addProduct(null, null);
    }

    /**
     * Method under test: {@link BasketService#addProduct(ProductOrderDTO, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: phoneNumber is marked non-null but is null
        //       at uz.market.uzum.services.basket.BasketService.addProduct(BasketService.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        BasketRepository basketRepository = mock(BasketRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), null);
    }

    /**
     * Method under test: {@link BasketService#addProduct(ProductOrderDTO, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Long.longValue()" because "productId" is null
        //       at uz.market.uzum.services.ProductService.getProductById(ProductService.java:89)
        //       at uz.market.uzum.services.basket.BasketService.addProduct(BasketService.java:44)
        //   See https://diff.blue/R013 to resolve this issue.

        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(new Basket()));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByPhoneNumber(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), "6625550144");
    }

    /**
     * Method under test: {@link BasketService#addProduct(ProductOrderDTO, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Basket not found
        //       at uz.market.uzum.services.basket.BasketService.getBasketByPhoneNumber(BasketService.java:66)
        //       at uz.market.uzum.services.basket.BasketService.addProduct(BasketService.java:41)
        //   See https://diff.blue/R013 to resolve this issue.

        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenThrow(new NullPointerException("Basket not found"));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByPhoneNumber(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), "6625550144");
    }

    /**
     * Method under test: {@link BasketService#addProduct(ProductOrderDTO, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: foo
        //       at uz.market.uzum.services.basket.BasketService.addProduct(BasketService.java:42)
        //   See https://diff.blue/R013 to resolve this issue.

        Basket basket = mock(Basket.class);
        when(basket.getProduct()).thenThrow(new NullPointerException("foo"));
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByPhoneNumber(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), "6625550144");
    }

    /**
     * Method under test: {@link BasketService#addProduct(ProductOrderDTO, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "uz.market.uzum.repositories.BasketRepository.findByUserId(java.lang.Long)" is null
        //       at uz.market.uzum.services.basket.BasketService.getBasketByPhoneNumber(BasketService.java:66)
        //       at uz.market.uzum.services.basket.BasketService.addProduct(BasketService.java:41)
        //   See https://diff.blue/R013 to resolve this issue.

        new NullPointerException("foo");
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(null);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByPhoneNumber(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), "6625550144");
    }

    /**
     * Method under test: {@link BasketService#addProduct(ProductOrderDTO, String)}
     */
    @Test
    void testAddProduct7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        new NullPointerException("foo");
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.empty());
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByPhoneNumber(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        assertThrows(ItemNotFoundException.class, () -> basketService.addProduct(new ProductOrderDTO(), "6625550144"));
        verify(basketRepository).findByUserId(Mockito.<Long>any());
        verify(userRepository).findByPhoneNumber(Mockito.<String>any());
    }

    /**
     * Method under test: {@link BasketService#addProduct(ProductOrderDTO, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct8() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.util.Objects.requireNonNull(Objects.java:208)
        //       at java.util.Optional.of(Optional.java:113)
        //       at uz.market.uzum.services.user.UserService.getUserByPhoneNumber(UserService.java:37)
        //       at uz.market.uzum.services.basket.BasketService.getBasketByPhoneNumber(BasketService.java:65)
        //       at uz.market.uzum.services.basket.BasketService.addProduct(BasketService.java:41)
        //   See https://diff.blue/R013 to resolve this issue.

        Basket basket = mock(Basket.class);
        when(basket.getProduct()).thenThrow(new NullPointerException("foo"));
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByPhoneNumber(Mockito.<String>any())).thenReturn(null);
        UserService userService = new UserService(userRepository);
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), "6625550144");
    }

    /**
     * Method under test: {@link BasketService#addProduct(ProductOrderDTO, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct9() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: foo
        //       at uz.market.uzum.services.basket.BasketService.getBasketByPhoneNumber(BasketService.java:66)
        //       at uz.market.uzum.services.basket.BasketService.addProduct(BasketService.java:41)
        //   See https://diff.blue/R013 to resolve this issue.

        new NullPointerException("foo");
        Basket basket = mock(Basket.class);
        when(basket.getProduct()).thenThrow(new NullPointerException("foo"));
        BasketRepository basketRepository = mock(BasketRepository.class);
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByPhoneNumber(Mockito.<String>any())).thenReturn(mock(User.class));
        UserService userService = new UserService(userRepository);
        BasketService basketService = new BasketService(basketRepository, userService,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                mock(ProductOrderRepository.class));
        basketService.addProduct(new ProductOrderDTO(), "6625550144");
    }

    /**
     * Method under test: {@link BasketService#addProduct(ProductOrderDTO, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct10() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: foo
        //       at uz.market.uzum.services.basket.BasketService.addProduct(BasketService.java:42)
        //   See https://diff.blue/R013 to resolve this issue.

        new NullPointerException("foo");
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

    /**
     * Method under test: {@link BasketService#addProduct(ProductOrderDTO, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct11() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "uz.market.uzum.services.user.UserService.getUserByPhoneNumber(String)" is null
        //       at uz.market.uzum.services.basket.BasketService.getBasketByPhoneNumber(BasketService.java:65)
        //       at uz.market.uzum.services.basket.BasketService.addProduct(BasketService.java:41)
        //   See https://diff.blue/R013 to resolve this issue.

        new NullPointerException("foo");
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

    /**
     * Method under test: {@link BasketService#addProduct(ProductOrderDTO, String)}
     */
    @Test
    void testAddProduct12() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        new NullPointerException("foo");
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

    /**
     * Method under test: {@link BasketService#getBasketByPhoneNumber(String)}
     */
    @Test
    void testGetBasketByPhoneNumber() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        BasketRepository basketRepository = mock(BasketRepository.class);
        Basket basket = new Basket();
        when(basketRepository.findByUserId(Mockito.<Long>any())).thenReturn(Optional.of(basket));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByPhoneNumber(Mockito.<String>any())).thenReturn(new User());
        UserService userService = new UserService(userRepository);
        assertSame(basket,
                (new BasketService(basketRepository, userService,
                        new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)),
                        mock(ProductOrderRepository.class))).getBasketByPhoneNumber("6625550144"));
        verify(basketRepository).findByUserId(Mockito.<Long>any());
        verify(userRepository).findByPhoneNumber(Mockito.<String>any());
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAddProductToBasket() {

    }


}