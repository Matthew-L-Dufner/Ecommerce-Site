package com.revature.MKPG.beans.Controllers;

import com.revature.MKPG.beans.Services.CategoryService;
import com.revature.MKPG.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<Category> getCategories(){ return categoryService.getAllCategories();}

    @GetMapping(value = "/{categoryId}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Category getCategoryById(@PathVariable Integer categoryId){
        Optional<Category> optionalCategory = categoryService.getCategoryById(categoryId);
        //Do the isPresent() check and throw exception if needed
        try{
            optionalCategory.isPresent();
        }catch(Exception e){
            System.out.println(e);
        }
        return optionalCategory.get();
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Category createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return category;
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Category updateCategory(@RequestBody Category category){

        categoryService.updateCategory(category);
        return category;
    }

    @DeleteMapping(value = "/{categoryId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCategory(@PathVariable(name = "categoryId") Integer id){ categoryService.deleteById(id);}
}
