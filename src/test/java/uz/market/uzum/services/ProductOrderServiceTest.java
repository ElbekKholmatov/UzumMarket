package uz.market.uzum.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uz.market.uzum.domains.product.Product;
import uz.market.uzum.domains.product.ProductOrder;
import uz.market.uzum.dtos.ProductOrderDTO;
import uz.market.uzum.repositories.ProductOrderRepository;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class ProductOrderServiceTest {

    @Test
    void testAdd() {
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
}

