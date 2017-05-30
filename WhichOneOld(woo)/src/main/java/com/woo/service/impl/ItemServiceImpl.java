package com.woo.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.domain.Category;
import com.woo.domain.Item;
import com.woo.repository.ItemRepository;
import com.woo.service.types.ItemService;
import com.woo.utils.LogMessage;

@Service
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;

	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	@Override
	public void addItem(Item item) {
		itemRepository.save(item);
	}
	@Override
	public Iterable<Item> getItems() {
		return itemRepository.findAll();
	}
	@Override
	public void deleteItemsById(long id) {
		itemRepository.delete(id);
	}
	@Override
	public Item getItemById(long id) {
		return itemRepository.findById(id);
	}
	@Override
	public Item getItemByCategoryAndMapCount(Category category, int mapCount) {
		ArrayList<Item> list = getItemByCategoryAndMapCountList(category, mapCount);
		if (list == null || list.size() == 0) {
			LogMessage.logx("Size 0 or null");
			return null;
		}
		return list.get(0);
	}
	private ArrayList<Item> getItemByCategoryAndMapCountList(Category category, int mapCount) {
		return itemRepository.findByCategoryAndMapCount(category, mapCount);
	}
	@Override
	public Item getItemByFilename(String filename) {
		return itemRepository.findByFilenameLike(filename);
	}
	@Override
	public long getRowCount() {
		return itemRepository.count();
	}
	@Override
	public void addItemFromFile(Item item) {
		itemRepository.save(item);

	}

}
