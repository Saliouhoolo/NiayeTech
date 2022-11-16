package sn.niayetch.niayetech.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.niayetch.niayetech.entity.Category;
import sn.niayetch.niayetech.repository.CategoryRepository;
import sn.niayetch.niayetech.service.CategoryService;

import javax.xml.bind.ValidationException;
import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(categoryOptional.isEmpty()){
            throw new ResolutionException("Category not found");
        }
        Category category1 = categoryOptional.get();
        category1.setLibelle(category.getLibelle());
        categoryRepository.save(category1);
        return category1;
    }

    @Override
    public Map<String, String> createCategory(Category category) throws ValidationException {
        if(categoryRepository.findByLibelle(category.getLibelle()).isPresent()){
            throw new ValidationException("Category exists");
        }
        category.setLibelle(category.getLibelle());


        categoryRepository.save(category);
        Map<String , String> response = new HashMap<>();
        response.put("message", "Ajout category réuissie");
        return response;
    }

    @Override
    public Map<String, Boolean> deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        Map<String , Boolean> response = new HashMap<>();
        if(categoryOptional.isEmpty()){
            response.put("delete", Boolean.FALSE);
        } else{
            response.put("delete", Boolean.TRUE);
            Category category = categoryOptional.get();
            categoryRepository.delete(category);
        }

        return response ;    }
}
