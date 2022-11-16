package sn.niayetch.niayetech.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.niayetch.niayetech.entity.Category;
import sn.niayetch.niayetech.service.CategoryService;

import javax.annotation.security.RolesAllowed;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Map;
/**
 *  - GET /api/category
 *
 * 	- POST /api/category
 *
 * 	- GET /api/category/{id}
 *
 * 	- PUT /api/category/{id}
 *
 * 	- DELETE /api/category/{id}
 */
@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/category")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }


    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/category")
    public Map<String ,String> createCategory(@RequestBody Category category) throws ValidationException {
        return categoryService.createCategory(category);
    }
    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value="id") Long categoryId, @RequestBody Category categoryDetails){
        Category category = categoryService.updateCategory(categoryId, categoryDetails);
        return ResponseEntity.ok().body(category);
    }
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("/category/{id}")
    public Map<String, Boolean> deleteCategory(@PathVariable(value="id") Long categoryId){
        return categoryService.deleteCategory(categoryId);
    }
}
