package com.woo.service.types;

import com.woo.domain.Category;
import com.woo.domain.Item;

public interface ItemService {

	Item addItem(Item item);

	Iterable<Item> getItems();

	void deleteItemsById(long id);

	Item getItemById(long id);

	Item getItemByCategoryAndMapCount(Category category, int mapCount);

	Item getItemByFilename(String filename);

	long getRowCount();

	void addItemFromFile(Item item);

	Item getRandomItem(Category category);

}
