package uz.market.uzum.domains.product;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import uz.market.uzum.domains.user.User;
import uz.market.uzum.enums.UserStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {
    @Test
    void testConstructor() {
        LocalDate ofResult = LocalDate.of(1970, 1, 1);
        LocalDateTime createdAt = ofResult.atStartOfDay();
        LocalDate ofResult2 = LocalDate.of(1970, 1, 1);
        LocalDateTime updatedAt = ofResult2.atStartOfDay();
        ArrayList<ProductOrder> product = new ArrayList<>();
        User user = new User();
        Basket actualBasket = new Basket(1L, 1L, createdAt, updatedAt, true, 1L, product, user);

        LocalDateTime createdAt2 = actualBasket.getCreatedAt();
        assertSame(createdAt, createdAt2);
        assertTrue(actualBasket.isDeleted());
        assertEquals("00:00", createdAt2.toLocalTime().toString());
        LocalDate toLocalDateResult = createdAt2.toLocalDate();
        assertSame(ofResult, toLocalDateResult);
        assertEquals("1970-01-01", toLocalDateResult.toString());
        User user2 = actualBasket.getUser();
        assertSame(user, user2);
        LocalDateTime updatedAt2 = actualBasket.getUpdatedAt();
        assertSame(updatedAt, updatedAt2);
        assertEquals(1L, actualBasket.getCreatedBy().longValue());
        Collection<ProductOrder> product2 = actualBasket.getProduct();
        assertSame(product, product2);
        assertTrue(product2.isEmpty());
        assertEquals(1L, actualBasket.getUpdateBy().longValue());
        LocalDate toLocalDateResult2 = updatedAt2.toLocalDate();
        assertSame(ofResult2, toLocalDateResult2);
        assertEquals("1970-01-01", toLocalDateResult2.toString());
        assertEquals("00:00", updatedAt2.toLocalTime().toString());
        assertEquals(1L, actualBasket.getId().longValue());
        assertNull(user2.getUsername());
        assertNull(user2.getUpdatedAt());
        assertEquals(UserStatus.INACTIVE, user2.getStatus());
        assertNull(user2.getRoles());
        assertNull(user2.getPassword());
        assertNull(user2.getLastName());
        assertNull(user2.getId());
        assertNull(user2.getLastLogin());
        assertNull(user2.getFirstName());
        assertNull(user2.getEmail());
        assertNull(user2.getCreatedAt());
        Collection<? extends GrantedAuthority> authorities = user2.getAuthorities();
        assertEquals(product2, authorities);
        assertTrue(authorities.isEmpty());
        assertTrue(user2.isCredentialsNonExpired());
        assertFalse(user2.isEnabled());
        assertTrue(user2.isAccountNonExpired());
        assertTrue(user2.isAccountNonLocked());
    }

    @Test
    void testConstructor2() {
        ArrayList<ProductOrder> product = new ArrayList<>();
        User user = new User();
        Basket actualBasket = new Basket(product, user);

        assertNull(actualBasket.getCreatedAt());
        assertFalse(actualBasket.isDeleted());
        User user2 = actualBasket.getUser();
        assertSame(user, user2);
        assertNull(actualBasket.getUpdatedAt());
        assertNull(actualBasket.getCreatedBy());
        Collection<ProductOrder> product2 = actualBasket.getProduct();
        assertSame(product, product2);
        assertTrue(product2.isEmpty());
        assertNull(actualBasket.getUpdateBy());
        assertNull(actualBasket.getId());
        assertNull(user2.getUsername());
        assertNull(user2.getUpdatedAt());
        assertEquals(UserStatus.INACTIVE, user2.getStatus());
        assertNull(user2.getRoles());
        assertNull(user2.getPassword());
        assertNull(user2.getLastName());
        assertNull(user2.getLastLogin());
        assertNull(user2.getId());
        assertNull(user2.getFirstName());
        assertNull(user2.getEmail());
        assertNull(user2.getCreatedAt());
        Collection<? extends GrantedAuthority> authorities = user2.getAuthorities();
        assertEquals(product2, authorities);
        assertTrue(authorities.isEmpty());
        assertTrue(user2.isCredentialsNonExpired());
        assertFalse(user2.isEnabled());
        assertTrue(user2.isAccountNonExpired());
        assertTrue(user2.isAccountNonLocked());
    }
}