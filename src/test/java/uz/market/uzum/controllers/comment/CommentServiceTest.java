package uz.market.uzum.controllers.comment;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import uz.market.uzum.domains.product.Category;
import uz.market.uzum.domains.product.Comment;
import uz.market.uzum.domains.product.Product;
import uz.market.uzum.dtos.comment.CommentCreateDto;
import uz.market.uzum.dtos.comment.CommentUpdateDTO;
import uz.market.uzum.exceptions.ItemNotFoundException;
import uz.market.uzum.repositories.CategoryRepository;
import uz.market.uzum.repositories.CommentRepository;
import uz.market.uzum.repositories.ProductRepository;
import uz.market.uzum.services.comment.CommentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void create() {

        Product product = saveProduct();
        long beforeAdding = commentRepository.count();
        CommentCreateDto createDto = new CommentCreateDto();
        createDto.setText("commit -1");
        createDto.setRate((byte) 5);
        createDto.setProductId(product.getId());
        Comment comment = commentService.create(createDto);
        log.info("comment = " + comment);
        assertEquals(1, comment.getProductId());
        assertEquals(comment.getRate(), createDto.getRate());
        long afterAdding = commentRepository.count();
        assertEquals(beforeAdding, afterAdding - 1);
    }

    private Product saveProduct() {

        Category category = Category.childBuilder()
                .name("ovqat").build();
        Category saveCategory = categoryRepository.save(category);
        Product saveProduct = Product.childBuilder()
                .name("osh")
                .description("okey")
                .category(saveCategory).build();

        return productRepository.save(saveProduct);

    }

    @Test
    void update() {
        int id = 1;
        CommentUpdateDTO commentUpdateDTO = new CommentUpdateDTO("update commit", (byte) 4, 1);
        Comment update = commentService.update(commentUpdateDTO, id);
        Comment comment = commentRepository.findById(1).orElseThrow(() -> new ItemNotFoundException("Not found"));
        assertEquals(comment.getText(), update.getText());
    }

    @Test
    void delete() {
        int id = 1;
        Comment delete = commentService.delete(id);
        assertEquals(delete.isDeleted(), true);
    }

    @Test
    void getComments() {
        int productId = 1;
        List<Comment> comments = commentService.getComments(productId);
        List<Comment> notFound = commentRepository.findByProductId(productId).orElseThrow(() -> new ItemNotFoundException("Not found"));
        assertEquals(comments.size(), notFound.size());
    }
}