package uz.market.uzum.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.market.uzum.domains.product.Category;
import uz.market.uzum.dtos.category.CategoryCreateDTO;
import uz.market.uzum.dtos.category.CategoryUpdateDTO;
import uz.market.uzum.exceptions.ItemNotFoundException;
import uz.market.uzum.repositories.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(CategoryCreateDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        if (dto.getParentID() != null)
            category.setParent(getCategoryById(dto.getParentID()));
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.getCategoryById(id)
                .orElseThrow(() -> new ItemNotFoundException("Category not found with id: " + id));
    }

    public boolean deleteCategory(Integer id) {
        Category category = getCategoryById(id);
        category.setDeleted(true);
        categoryRepository.save(category);
        return true;
    }

    public Category updateCategory(CategoryUpdateDTO dto) {
        Category category = getCategoryById(dto.getCategoryID());
        Category parent = getCategoryById(dto.getParentID());
        category.setName(dto.getName());
        category.setParent(parent);
        return categoryRepository.save(category);
    }

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }
}
