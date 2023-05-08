package uz.market.uzum.domains.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ProductOrderTest {
    @Test
    void testConstructor() {
        ProductOrder actualProductOrder = new ProductOrder();
        actualProductOrder.setBasketId(1L);
        actualProductOrder.setCount((short) 1);
        actualProductOrder.setId(1L);
        Product product = new Product();
        actualProductOrder.setProduct(product);
        actualProductOrder.productOrderBuilder();
        assertEquals(1L, actualProductOrder.getBasketId().longValue());
        assertEquals((short) 1, actualProductOrder.getCount().shortValue());
        assertEquals(1L, actualProductOrder.getId().longValue());
        assertSame(product, actualProductOrder.getProduct());
    }

    @Test
    void testConstructor2() {
        ProductOrder actualProductOrder = new ProductOrder(1L, new Product(), (short) 1, 1L);
        actualProductOrder.setBasketId(1L);
        actualProductOrder.setCount((short) 1);
        actualProductOrder.setId(1L);
        Product product = new Product();
        actualProductOrder.setProduct(product);
        actualProductOrder.productOrderBuilder();
        assertEquals(1L, actualProductOrder.getBasketId().longValue());
        assertEquals((short) 1, actualProductOrder.getCount().shortValue());
        assertEquals(1L, actualProductOrder.getId().longValue());
        assertSame(product, actualProductOrder.getProduct());
    }
}

