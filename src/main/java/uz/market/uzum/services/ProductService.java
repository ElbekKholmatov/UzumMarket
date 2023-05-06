package uz.market.uzum.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.market.uzum.domains.product.Product;
import uz.market.uzum.exceptions.ItemNotFoundException;
import uz.market.uzum.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ItemNotFoundException("Product not found"));
    }
}
