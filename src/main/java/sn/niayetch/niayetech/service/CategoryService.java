package sn.niayetch.niayetech.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sn.niayetch.niayetech.entity.Category;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<Category> getCategories();
    Category updateCategory(@PathVariable(value="id") Long categoryId, @RequestBody Category category);
    Map<String ,String> createCategory(@RequestBody Category category) throws ValidationException;
    Map<String ,Boolean> deleteCategory(@PathVariable(value="id") Long categoryId);

}
