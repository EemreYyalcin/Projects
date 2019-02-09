package com.woo.core.generate;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;

import org.springframework.core.io.Resource;

import com.woo.core.attributes.Codes;
import com.woo.core.attributes.Link;
import com.woo.domain.Category;
import com.woo.domain.Item;
import com.woo.service.impl.CategoryServiceImpl;
import com.woo.service.impl.FileSystemStorageServiceImpl;
import com.woo.service.impl.ItemServiceImpl;
import com.woo.utils.converter.ConverterType;
import com.woo.utils.generater.GenerateRandom;
import com.woo.utils.log.LogMessage;

public class GenerateItem {

	public static void loadFromFileToDB(FileSystemStorageServiceImpl fileSystemStorageService, CategoryServiceImpl categoryService, ItemServiceImpl itemService) {

		try {
			ArrayList<String> fileList = fileSystemStorageService.getImgResourceFileList();
			if (fileList.size() <= 0) {
				LogMessage.logx("FileList is Empty!!");
				return;
			}
			Properties categories = getFileNamesWithCategoryName(fileList);
			if (categories == null) {
				LogMessage.logx("FileList, Category is Empty!!");
				return;
			}
			Set<Object> categoryObjects = categories.keySet();
			for (Object categoryObject : categoryObjects) {
				String categoryKey = (String) categoryObject;
				Properties decadeList = (Properties) categories.get(categoryKey);
				if (decadeList == null) {
					continue;
				}
				Category category;
				Set<Object> decadesObjects = decadeList.keySet();
				for (Object decadeObject : decadesObjects) {
					String decadeKey = (String) decadeObject;
					Properties dateAndFiles = (Properties) decadeList.get(decadeKey);
					if (dateAndFiles == null) {
						continue;
					}
					int decadeValue = ConverterType.toInt(decadeKey);
					if (decadeValue == Codes.errorIntCode) {
						continue;
					}
					category = categoryService.getCategoryByNameAndDecade(categoryKey, decadeValue);
					if (category == null) {
						category = new Category(categoryKey, decadeValue);
						categoryService.addCategory(category);
					}
					else {
						category.setLastUpdateDate(Calendar.getInstance().getTime());
					}

					ArrayList<Item> items = addItemToCategory(fileSystemStorageService, itemService, dateAndFiles, category);
					if (items == null) {
						continue;
					}
					ArrayList<Integer> randomItemList = GenerateRandom.getRandomList(0, items.size());
					int mapCountItem = category.getMapCountItem();
					for (Integer index : randomItemList) {
						items.get(index).setMapCount(mapCountItem++);
						itemService.addItem(items.get(index));
					}
					category.setMapCountItem(mapCountItem);
					categoryService.addCategory(category);
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static ArrayList<Item> addItemToCategory(FileSystemStorageServiceImpl fileSystemStorageService, ItemServiceImpl itemService, Properties dateAndFiles, Category category) {

		try {
			boolean emptyList = true;
			ArrayList<Item> items = new ArrayList<Item>();
			Set<Object> fileObjects = dateAndFiles.keySet();
			for (Object fileObject : fileObjects) {
				String dateKey = (String) fileObject;
				if (ConverterType.toInt(dateKey) == Codes.errorIntCode) {
					continue;
				}
				ArrayList<String> fileNames = (ArrayList<String>) dateAndFiles.get(dateKey);
				for (String fileName : fileNames) {
					Resource file = fileSystemStorageService.getFile(Paths.get(Link.rootLocationValue + "\\" + category.getName() + "\\" + dateKey), fileName);

					byte[] content = ImageCodec.resizeImageConvert(file.getFile());
					if (content == null) {
						LogMessage.logx("File Read Error !! Filename:" + Link.rootLocationValue + "\\" + category.getName() + "\\" + dateKey + "\\" + fileName);
						fileSystemStorageService.deleteFile(Paths.get(Link.rootLocationValue + "\\" + category.getName() + "\\" + dateKey + "\\" + fileName));
						continue;
					}
					if (content.length <= 0) {
						LogMessage.logx("File Size Error !! Filename:" + Link.rootLocationValue + "\\" + category.getName() + "\\" + dateKey + "\\" + fileName);
						fileSystemStorageService.deleteFile(Paths.get(Link.rootLocationValue + "\\" + category.getName() + "\\" + dateKey + "\\" + fileName));
						continue;
					}
					try {
						Item item = new Item();
						item.setFilename(dateKey + fileName);
						item.setContent(content);
						item.setYear(ConverterType.toInt(dateKey));
						item.setMapCount(Codes.errorIntCode);
						item.setCategory(category);
						item = itemService.addItem(item);
						items.add(item);
						fileSystemStorageService.deleteFile(Paths.get(Link.rootLocationValue + "\\" + category.getName() + "\\" + dateKey + "\\" + fileName));
						emptyList = false;
					}
					catch (Exception e) {
						// ignored
						fileSystemStorageService.deleteFile(Paths.get(Link.rootLocationValue + "\\" + category.getName() + "\\" + dateKey + "\\" + fileName));
					}
				}
			}
			if (emptyList) {
				return null;
			}
			return items;
		}
		catch (Exception e) {
			return null;
		}
	}

	/*
	 * @param fileList ->
	 * C:\\Images\\Category1\\1991\\name1,C:\\Images\\Category1\\1992\\name2 ...
	 */
	private static Properties getFileNamesWithCategoryName(ArrayList<String> fileList) {
		Properties categoryNames = new Properties();
		boolean emptyList = true;

		for (String path : fileList) {
			String[] parts = path.split("\\\\");
			if (parts == null) {
				continue;
			}
			int fieldSize = parts.length;
			if (fieldSize <= 3) {
				continue;
			}
			String keyCategoryName = parts[fieldSize - 3];
			ArrayList<String> list = (ArrayList<String>) categoryNames.get(keyCategoryName);
			if (list == null) {
				list = new ArrayList<String>();
				list.add(path);
				categoryNames.put(keyCategoryName, list);
				emptyList = false;
			}
			else {
				list.add(path);
			}
		}

		if (emptyList) {
			return null;
		}

		Properties categories = new Properties();
		emptyList = true;
		Set<Object> opjects = categoryNames.keySet();
		for (Object object : opjects) {
			String key = (String) object;
			ArrayList<String> list = (ArrayList<String>) categoryNames.get(key);
			Properties decadeList = getFileNamesWithDecade(list);
			if (decadeList == null) {
				continue;
			}
			categories.put(key, decadeList);
			emptyList = false;
		}

		if (emptyList) {
			return null;
		}
		return categories;

	}

	/*
	 * @param fileList ->
	 * C:\\Images\\Category1\\1991\\name1,C:\\Images\\Category1\\1992\\name2 ...
	 * 
	 * @return (key1 -> 1990, value1 -> ((key1 -> 1991, value1 -> name1), (key2
	 * -> 1992, value2 -> name2)))
	 */

	private static Properties getFileNamesWithDecade(ArrayList<String> fileList) {
		Properties decades = new Properties();
		boolean emptyList = true;

		for (String path : fileList) {
			String[] parts = path.split("\\\\");
			if (parts == null) {
				continue;
			}
			int fieldSize = parts.length;
			if (fieldSize <= 2) {
				continue;
			}
			String keyDate = parts[fieldSize - 2];
			String valueName = parts[fieldSize - 1];

			int keyValue = ConverterType.toInt(keyDate);
			if (keyValue == Codes.errorIntCode) {
				continue;
			}
			keyValue = (keyValue - (keyValue % 10));
			if (decades.get(keyValue + "") != null) {
				// Already Import
				continue;
			}
			String prefix = keyValue + "";
			prefix = prefix.substring(0, prefix.length() - 1);
			Properties namesAndDate = getFileNamesWithDate(fileList, prefix);
			if (namesAndDate == null) {
				continue;
			}
			decades.put(keyValue + "", namesAndDate);
			emptyList = false;
		}
		if (emptyList) {
			return null;
		}
		return decades;
	}

	/*
	 * @param fileList ->
	 * C:\\Images\\Category1\\1991\\name1,C:\\Images\\Category1\\1992\\name2,
	 * ,C:\\Images\\Category1\\2001\\name3 ... ...
	 * 
	 * @param prefix -> 199
	 * 
	 * @return (key1 -> 1991, value1 -> name1), (key2 -> 1992, value2 -> name2)
	 */
	private static Properties getFileNamesWithDate(ArrayList<String> fileList, String prefix) {
		Properties items = new Properties();
		boolean emptyList = true;

		for (String path : fileList) {
			String[] parts = path.split("\\\\");
			if (parts == null) {
				continue;
			}
			int fieldSize = parts.length;
			if (fieldSize <= 2) {
				continue;
			}
			String key = parts[fieldSize - 2];
			String value = parts[fieldSize - 1];
			if (!key.startsWith(prefix)) {
				continue;
			}
			int keyValue = ConverterType.toInt(key);
			if (keyValue == Codes.errorIntCode) {
				continue;
			}
			ArrayList<String> nameList = (ArrayList<String>) items.get(key);
			if (nameList == null) {
				nameList = new ArrayList<String>();
				nameList.add(value);
				items.put(key, nameList);
				emptyList = false;
				continue;
			}
			nameList.add(value);
		}

		if (emptyList) {
			return null;
		}
		return items;
	}

}
