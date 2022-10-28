package com.revature.MKPG.beans.Repositories;

import com.revature.MKPG.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface MyRepoBase<T, ID extends Serializable> extends JpaRepository<Item, Integer> {
    Optional<List<T>> findAllByPrice(Double price);
    Optional<T> getItemByName(String itemName);
}

