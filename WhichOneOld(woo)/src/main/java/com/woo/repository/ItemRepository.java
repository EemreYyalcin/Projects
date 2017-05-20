package com.woo.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.woo.domain.Category;
import com.woo.domain.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

	Item findById(long id);

	ArrayList<Item> findByCategoryAndMapCount(Category category, int mapCount);

	Item findByFilenameLike(String filename);

}
