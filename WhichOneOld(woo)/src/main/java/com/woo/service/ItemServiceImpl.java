package com.woo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.domain.Category;
import com.woo.domain.Item;
import com.woo.repository.ItemRepository;
import com.woo.utils.LogMessage;

@Service
public class ItemServiceImpl {

	private final ItemRepository itemRepository;

	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public void addItem(Item item) {
		itemRepository.save(item);
	}

	public Iterable<Item> getItems() {
		return itemRepository.findAll();
	}

	public void deleteItemsById(long id) {
		itemRepository.delete(id);
	}

	public Item getItemById(long id) {
		return itemRepository.findById(id);
	}

	public Item getItemByCategoryAndMapCount(Category category, int mapCount) {
		ArrayList<Item> list = getItemByCategoryAndMapCountList(category, mapCount);
		if (list == null || list.size() == 0) {
			LogMessage.logx("Size 0 or null" );
			return null;
		}
		return list.get(0);
	}
	private ArrayList<Item> getItemByCategoryAndMapCountList(Category category, int mapCount) {
		return itemRepository.findByCategoryAndMapCount(category, mapCount);
	}
	
	public Item getItemByFilename(String filename){
		return itemRepository.findByFilenameLike(filename);
	}
	

	public long getRowCount() {
		return itemRepository.count();
	}

	public void addItemFromFile(Item item) {
		itemRepository.save(item);

	}

}
