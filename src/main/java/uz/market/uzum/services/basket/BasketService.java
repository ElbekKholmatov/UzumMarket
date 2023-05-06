package uz.market.uzum.services.basket;

import lombok.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.market.uzum.domains.product.Basket;
import uz.market.uzum.domains.product.Product;
import uz.market.uzum.domains.product.ProductOrder;
import uz.market.uzum.domains.user.User;
import uz.market.uzum.dtos.ProductOrderDTO;
import uz.market.uzum.exceptions.ItemNotFoundException;
import uz.market.uzum.repositories.BasketRepository;
import uz.market.uzum.repositories.ProductOrderRepository;
import uz.market.uzum.services.ProductService;
import uz.market.uzum.services.user.UserService;

import java.util.Collection;

@Service
public class BasketService {


    private final BasketRepository basketRepository;
    private final UserService userService;
    private final ProductService productService;
    private final ProductOrderRepository productOrderRepository;

    public BasketService(BasketRepository basketRepository, UserService userService, ProductService productService, ProductOrderRepository productOrderRepository) {
        this.basketRepository = basketRepository;
        this.userService = userService;
        this.productService = productService;
        this.productOrderRepository = productOrderRepository;
    }

    public Basket getBasket() {
        String phoneNumber=String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return getBasketByPhoneNumber(phoneNumber);
    }

    public ProductOrder addProduct(@NonNull ProductOrderDTO pOrder, @NonNull String phoneNumber) {
        Basket basket = getBasketByPhoneNumber(phoneNumber);
        Collection<ProductOrder> products = basket.getProduct();
        ProductOrder productOrder = ProductOrder.productOrderBuilder()
                .product(productService.getProductById(pOrder.getProductId()))
                .count(pOrder.getCount())
                .basketId(basket.getId())
                .build();
        for (ProductOrder product : products) {
            if (product.getProduct().getName().equals(productOrder.getProduct().getName())) {
                products.remove(product);
                products.add(productOrder);
                basket.setProduct(products);
                productOrderRepository.delete(product);
                basketRepository.save(basket);
                return product;
            }
        }
        products.add(productOrder);
        basket.setProduct(products);
        basketRepository.save(basket);
        return productOrder;
    }


    public Basket getBasketByPhoneNumber(String phoneNumber) {
        User user=userService.getUserByPhoneNumber(phoneNumber).orElseThrow(()->new ItemNotFoundException("User not found"));
        return basketRepository.findByUserId(user.getId()).orElseThrow(()->new ItemNotFoundException("Basket not found"));
    }

    public ProductOrder removeProduct(Long productId, String phoneNumber) {
        Basket basket = getBasketByPhoneNumber(phoneNumber);
        Collection<ProductOrder> products = basket.getProduct();
        Product productById = productService.getProductById(productId);
        for (ProductOrder product : products) {
            if (product.getProduct().getName().equals(productById.getName())) {
                products.remove(product);
                basket.setProduct(products);
                basketRepository.save(basket);
                productOrderRepository.delete(product);
                return product;
            }
        }
        throw new ItemNotFoundException("Product not found");
    }
}
