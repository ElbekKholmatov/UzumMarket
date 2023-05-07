package uz.market.uzum.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uz.market.uzum.domains.product.Product;
import uz.market.uzum.domains.product.ProductOrder;
import uz.market.uzum.dtos.ProductOrderDTO;
import uz.market.uzum.repositories.CategoryRepository;
import uz.market.uzum.repositories.ProductOrderRepository;
import uz.market.uzum.repositories.ProductRepository;

class ProductOrderServiceTest {

    @Test

    void testAdd() {

        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        ProductOrderService productOrderService = new ProductOrderService(productOrderRepository,
                new ProductService(new CategoryService(mock(CategoryRepository.class)), mock(ProductRepository.class)));
        productOrderService.add(new ProductOrderDTO());
    }

    @Test
    void testAdd2() {
        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        ProductOrder productOrder = new ProductOrder();
        when(productOrderRepository.save(Mockito.<ProductOrder>any())).thenReturn(productOrder);
        ProductService productService = mock(ProductService.class);
        when(productService.getProductById(Mockito.<Long>any())).thenReturn(new Product());
        ProductOrderService productOrderService = new ProductOrderService(productOrderRepository, productService);
        assertSame(productOrder, productOrderService.add(new ProductOrderDTO()));
        verify(productOrderRepository).save(Mockito.<ProductOrder>any());
        verify(productService).getProductById(Mockito.<Long>any());
    }

    @Test
    void testAdd3() {

        ProductOrderRepository productOrderRepository = mock(ProductOrderRepository.class);
        when(productOrderRepository.save(Mockito.<ProductOrder>any())).thenReturn(new ProductOrder());
        ProductService productService = mock(ProductService.class);
        when(productService.getProductById(Mockito.<Long>any())).thenReturn(new Product());
        (new ProductOrderService(productOrderRepository, productService)).add(null);
    }
}

