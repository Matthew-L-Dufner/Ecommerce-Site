package com.revature.MKPG.beans.Repositories;

import com.revature.MKPG.entities.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepo extends MyRepoBase<Item, Integer> {



    @Query("SELECT i FROM item i WHERE i.itemName =:item_name")
    Optional<Item> getItemByName(
            @Param("item_name") String itemName);

    @Query("SELECT i FROM item i WHERE i.price < :price")
    Optional<List<Item>> findAllByPrice(
            @Param("price") Double price);
}
