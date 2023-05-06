package uz.market.uzum;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uz.market.uzum.repositories.CategoryRepository;
import uz.market.uzum.services.CategoryService;

@SpringBootTest
class UzumMarketApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

}
