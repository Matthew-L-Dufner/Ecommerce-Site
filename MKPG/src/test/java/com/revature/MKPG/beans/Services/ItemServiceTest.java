package com.revature.MKPG.beans.Services;

import com.revature.MKPG.beans.Repositories.ItemRepo;
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
class ItemServiceTest {

    @Mock
    private ItemRepo itemRepo;

    @InjectMocks
    private ItemService itemService;

    private Item item;

    @BeforeEach
    public void setup(){
        item = Item.builder()
                .itemId(1)
                .itemName("Game1")
                .description("This is game")
                .price(123.0)
                .discountedPrice(100.0)
                .rating(" ")
                .build();
    }

    @DisplayName("JUnit test for createItem method")
    @Test
    void testForCreateItem() {

        given(itemRepo.save(item)).willReturn(item);

        Item savedItem = itemService.createItem(item);

        assertThat(savedItem).isNotNull();
        assertThat(savedItem.getItemId()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for getAllItems method")
    @Test
    void testForGetAllItems() {
        Item item1 = Item.builder()
                .itemId(2)
                .itemName("Game2")
                .description("This is game2")
                .price(300.0)
                .discountedPrice(150.0)
                .rating(" ")
                .build();

        given(itemRepo.findAll()).willReturn(List.of(item, item1));
        List<Item> itemList = itemService.getAllItems();

        assertThat(itemList).isNotNull();
        assertThat(itemList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getItemById method")
    @Test
    void testForGetItemById() {
        given(itemRepo.findById(1)).willReturn(Optional.of(item));

        Item savedItem = itemService.getItemById(item.getItemId()).get();

        assertThat(savedItem).isNotNull();
    }

    @DisplayName("JUnit test for FindByName method")
    @Test
    void testForGetItemByName() {
        given(itemRepo.getItemByName("Game1")).willReturn(Optional.of(item));

        Item savedItem = itemService.getByItemName(item.getItemName()).get();

        assertThat(savedItem).isNotNull();
    }

//    @DisplayName("JUnit test for FindByPrice method")
//    @Test
//    void testForFindByPrice() {
//        List<Item> items = new ArrayList<>();
//        items.add(item);
//        given(itemRepo.findAllByPrice(300.00)).willReturn(Optional.of(items));
//
//        List<Item> savedItem = itemService.findByPrice(item.getPrice()).get();
//
//        assertThat(savedItem).isNotNull();
//    }

    @DisplayName("JUnit test for updateItem method")
    @Test
    void testForUpdateItem() {
        given(itemRepo.save(item)).willReturn(item);
        item.setItemName("superGame");
        item.setPrice(500.00);

        Item updatedItem = itemService.updateItem(item);
        assertThat(updatedItem.getItemName()).isEqualTo("superGame");
        assertThat(updatedItem.getPrice()).isEqualTo(500.00);
    }

    @DisplayName("JUnit test for deleteItemById method")
    @Test
    void testForDeleteItemById() {
        int itemId = 1;
        willDoNothing().given(itemRepo).deleteById(itemId);

        itemService.deleteItemById(itemId);
        verify(itemRepo, times(1)).deleteById(itemId);
    }
}