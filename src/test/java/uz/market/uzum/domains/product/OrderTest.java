package uz.market.uzum.domains.product;

import org.junit.jupiter.api.Test;
import uz.market.uzum.enums.OrderStatus;
import uz.market.uzum.enums.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    void testConstructor() {
        LocalDate ofResult = LocalDate.of(1970, 1, 1);
        LocalDateTime createdAt = ofResult.atStartOfDay();
        LocalDate ofResult2 = LocalDate.of(1970, 1, 1);
        LocalDateTime updatedAt = ofResult2.atStartOfDay();
        ArrayList<ProductOrder> products = new ArrayList<>();
        Order actualOrder = new Order(1L, 1L, createdAt, updatedAt, true, 1L, 1L, products, OrderStatus.NEW, Payment.CASH);

        LocalDateTime createdAt2 = actualOrder.getCreatedAt();
        assertSame(createdAt, createdAt2);
        assertTrue(actualOrder.isDeleted());
        assertEquals("00:00", createdAt2.toLocalTime().toString());
        LocalDate toLocalDateResult = createdAt2.toLocalDate();
        assertSame(ofResult, toLocalDateResult);
        assertEquals("1970-01-01", toLocalDateResult.toString());
        assertEquals(1L, actualOrder.getUserId().longValue());
        LocalDateTime updatedAt2 = actualOrder.getUpdatedAt();
        assertSame(updatedAt, updatedAt2);
        assertEquals(1L, actualOrder.getUpdateBy().longValue());
        assertEquals("00:00", updatedAt2.toLocalTime().toString());
        LocalDate toLocalDateResult2 = updatedAt2.toLocalDate();
        assertSame(ofResult2, toLocalDateResult2);
        assertEquals("1970-01-01", toLocalDateResult2.toString());
        assertEquals(OrderStatus.NEW, actualOrder.getStatus());
        Collection<ProductOrder> products2 = actualOrder.getProducts();
        assertSame(products, products2);
        assertTrue(products2.isEmpty());
        assertEquals(Payment.CASH, actualOrder.getPayment());
        assertEquals(1L, actualOrder.getId().longValue());
        assertEquals(1L, actualOrder.getCreatedBy().longValue());
    }
}

