package com.woo.controller.functions;

import com.woo.domain.Category;
import com.woo.domain.Item;
import com.woo.service.impl.ItemServiceImpl;
import com.woo.utils.generater.GenerateRandom;
import com.woo.utils.log.LogMessage;

public class ItemFunctions {

	public static Item getRandomItem(ItemServiceImpl itemService, Category category) {
		int randomMapCount = GenerateRandom.getRandomInt(1, category.getMapCountItem());
		LogMessage.logx("CategoryId:" + category.getId() + " randomMapCount:" + randomMapCount + " category.getMapCountItem():" + category.getMapCountItem());
		return itemService.getItemByCategoryAndMapCount(category, randomMapCount);
	}

}
