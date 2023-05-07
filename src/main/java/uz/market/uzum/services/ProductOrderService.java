package uz.market.uzum.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.market.uzum.domains.product.ProductOrder;
import uz.market.uzum.dtos.ProductOrderDTO;
import uz.market.uzum.repositories.ProductOrderRepository;

@RequiredArgsConstructor
@Service
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;

    private final ProductService productService;

    public ProductOrder add(ProductOrderDTO dto) {
        ProductOrder pOrder = ProductOrder.productOrderBuilder()
                .product(productService.getProductById(dto.getProductId()))
                .count(dto.getCount())
                .build();
        return productOrderRepository.save(pOrder);
    }
}
