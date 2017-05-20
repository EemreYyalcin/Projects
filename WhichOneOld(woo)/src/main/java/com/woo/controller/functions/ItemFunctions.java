package com.woo.controller.functions;

import com.woo.domain.Category;
import com.woo.domain.Item;
import com.woo.service.ItemServiceImpl;
import com.woo.utils.LogMessage;
import com.woo.utils.Utils;

public class ItemFunctions {

	public static Item getRandomItem(ItemServiceImpl itemService, Category category) {
		int randomMapCount = Utils.getRandomInt(1, category.getMapCountItem());
		LogMessage.logx("CategoryId:" + category.getId() + " randomMapCount:" + randomMapCount + " category.getMapCountItem():" + category.getMapCountItem());
		return itemService.getItemByCategoryAndMapCount(category, randomMapCount);
	}

}
