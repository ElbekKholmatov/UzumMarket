package uz.market.uzum.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.market.uzum.domains.Document;
import uz.market.uzum.domains.product.Category;
import uz.market.uzum.domains.product.Product;
import uz.market.uzum.dtos.product.ProductCreateDTO;
import uz.market.uzum.dtos.product.ProductUpdateDTO;
import uz.market.uzum.enums.ProductStatus;
import uz.market.uzum.exceptions.ItemNotFoundException;
import uz.market.uzum.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    public Product createProduct(ProductCreateDTO dto) {
        Category categoryById = categoryService.getCategoryById(dto.getCategoryID());
        Product product = Product.childBuilder()
                .price(dto.getPrice())
                .count(dto.getCount())
                .discount(dto.getDiscount())
                .category(categoryById)
                .description(dto.getDescription())
                .status(ProductStatus.ACTIVE)
                .name(dto.getName())
                .build();
        return productRepository.save(product);
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product not found with id " + id));
    }

    public List<Product> getCategoryProducts(Integer categoryID) {
        return productRepository.findByCategoryId(categoryID);
    }

    public boolean deleteProduct(Integer id) {
        return productRepository.updateIsDeletedById(id) == 1;
    }

    public Product updateProduct(ProductUpdateDTO dto) {
        Product product = getProductById(dto.getProductID());
        Category category = categoryService.getCategoryById(dto.getCategoryID());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategory(category);
        product.setDescription(dto.getDescription());
        product.setDiscount(dto.getDiscount());
        return productRepository.save(product);
    }

    public List<Document> addImages(Integer id, List<MultipartFile> files) {
        List<Document> documents = List.of();//documentService.saveAll
        Product product = getProductById(id);
        product.setImages(documents);
        return documents;
    }
}
