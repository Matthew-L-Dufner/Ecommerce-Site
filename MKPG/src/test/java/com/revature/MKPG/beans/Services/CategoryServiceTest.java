package com.revature.MKPG.beans.Services;

import com.revature.MKPG.beans.Repositories.CategoryRepository;
import com.revature.MKPG.entities.Category;
import com.revature.MKPG.entities.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;
    private List<Item> items;

    @BeforeEach
    public void setup(){
     Item item = Item.builder()
                .itemId(1)
                .itemName("Game1")
                .description("This is game")
                .price(123.0)
                .discountedPrice(100.0)
                .rating(" ")
                .build();

        category = Category.builder()
        .categoryId(1)
        .name("Football")
        .console("console")
        .item(items)
                .build();
    }
    @DisplayName("JUnit test for createCategory method")
    @Test
    void testForCreateCategory() {
        given(categoryRepository.save(category)).willReturn(category);

        Category saveCategory = categoryService.createCategory(category);

        assertThat(saveCategory).isNotNull();
        assertThat(saveCategory.getCategoryId()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for getAllCategory method")
    @Test
    void testForGetAllCategories() {
       Category category1 = Category.builder()
                .categoryId(1)
                .name("Football")
                .console("console")
                .item(items)
                .build();
       given(categoryRepository.findAll()).willReturn(List.of(category1, category));
       List<Category> categoryList = categoryService.getAllCategories();

       assertThat(categoryList).isNotNull();
       assertThat(categoryList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getCategoryById method")
    @Test
    void testForGetCategoryById() {
        given(categoryRepository.findById(1)).willReturn(Optional.of(category));
        Category saveCategory = categoryService.getCategoryById(category.getCategoryId()).get();

        assertThat(saveCategory).isNotNull();
    }

    @DisplayName("JUnit test for updateCategory method")
    @Test
    void testForUpdateCategory() {
        given(categoryRepository.save(category)).willReturn(category);
        category.setName("FootballOne");
        category.setConsole("Modern console");

        Category updateCategory = categoryService.updateCategory(category);
    }

    @DisplayName("JUnit test for deleteCategory method")
    @Test
    void testForDeleteById() {
        int categoryId = 1;
        willDoNothing().given(categoryRepository).deleteById(categoryId);

        categoryService.deleteById(categoryId);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}