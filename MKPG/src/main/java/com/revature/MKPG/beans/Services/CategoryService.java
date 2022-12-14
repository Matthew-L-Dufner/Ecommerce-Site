package com.revature.MKPG.beans.Services;

import com.revature.MKPG.beans.Repositories.CategoryRepository;
import com.revature.MKPG.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Integer id){
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category){
        return categoryRepository.save(category);
    }
    public void deleteById(Integer id){
        categoryRepository.deleteById(id);
    }

}
