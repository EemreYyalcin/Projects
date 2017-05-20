package com.woo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.woo.domain.Item;
import com.woo.service.ItemServiceImpl;
import com.woo.utils.LogMessage;

@Controller
public class ItemController {

	@Autowired
	private ItemServiceImpl itemService;

	@RequestMapping(value = "/woo/items")
	public ModelAndView getItems() {
		ModelAndView view = new ModelAndView("items", "items", itemService.getItems());
		view.addObject("serverAddress", "http://127.0.0.1:8080/woo/files/");
		return view;
	}

	@GetMapping("/woo/files/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable("id") Item item) {
		if (item == null || item.getId() == 0) {
			LogMessage.error("Item is null. Code:Console");
			return null;
		}
		Resource file = new ByteArrayResource(item.getContent());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + item.getFilename() + "\"").body(file);
	}

}
