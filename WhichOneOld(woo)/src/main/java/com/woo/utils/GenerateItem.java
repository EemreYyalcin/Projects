package com.woo.utils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;

import org.springframework.core.io.Resource;

import com.woo.domain.Category;
import com.woo.domain.Item;
import com.woo.service.CategoryServiceImpl;
import com.woo.service.FileSystemStorageService;
import com.woo.service.ItemServiceImpl;

public class GenerateItem {

	public static void loadFromFileToDB(FileSystemStorageService fileSystemStorageService, CategoryServiceImpl categoryService, ItemServiceImpl itemService) {
		try {
			ArrayList<String> fileList = fileSystemStorageService.getImgResourceFileList();
			String currentCategoryName = "";
			Category category = null;
			int tempDecade = 1990;

			if (fileList.size() <= 0) {
				LogMessage.logx("FileList is Empty!!");
				return;
			}

			String prefix = fileList.get(0).split("\\\\")[0];
			Properties propCategoryYearDecade = getCategoryAndYearAndDecadeList(fileList);
			Properties categoryProp = new Properties();

			Set<Object> opjects = propCategoryYearDecade.keySet();
			for (Object object : opjects) {
				String keys = (String) object;
				String[] keyValues = keys.split(".YLC.");
				if (keyValues.length < 2) {
					continue;
				}
				currentCategoryName = keyValues[0];
				tempDecade = Integer.valueOf(keyValues[1]);
				category = (Category) categoryProp.get(currentCategoryName + tempDecade);
				if (category == null) {
					category = categoryService.getCategoryByNameAndDecade(currentCategoryName, tempDecade);
					if (category == null) {
						category = new Category(currentCategoryName, tempDecade);
						categoryService.addCategory(category);
					} else {
						category.setLastUpdateDate(Calendar.getInstance().getTime());
					}
					categoryProp.put(currentCategoryName + tempDecade, category);
				}
				ArrayList<String> randomList = getRandomList((ArrayList<String>) propCategoryYearDecade.get(keys));
				// addItem
				int categoryMapCountItem = category.getMapCountItem();
				for (int i = 0; i < randomList.size(); i++) {
					String yearAndFilenames = randomList.get(i);
					String[] yearAndFilename = yearAndFilenames.split(".YLC.");
					int year = Integer.valueOf(yearAndFilename[0]);
					String filename = yearAndFilename[1];
					Resource file = fileSystemStorageService.getFile(Paths.get(prefix + "\\" + category.getName() + "\\" + year), filename);

					// InputStream is = file.getInputStream();
					// byte[] content = IOUtils.toByteArray(is);
					// IOUtils.closeQuietly(is);

					byte[] content = ImageCodec.resizeImageConvert(file.getFile());

					if (content == null) {
						LogMessage.logx("File Read Error !! Filename:" + prefix + "\\" + category.getName() + "\\" + year + "\\" + filename);
//						fileSystemStorageService.deleteFile(Paths.get(prefix + "\\" + category.getName() + "\\" + year + "\\" + filename));
						continue;
					}
					if (content.length <= 0) {
						LogMessage.logx("File Size Error !! Filename:" + prefix + "\\" + category.getName() + "\\" + year + "\\" + filename);
//						fileSystemStorageService.deleteFile(Paths.get(prefix + "\\" + category.getName() + "\\" + year + "\\" + filename));
						continue;
					} 
					try {
						Item item = new Item();
						item.setFilename(year + filename);
						item.setContent(content);
						item.setYear(year);
						item.setMapCount(categoryMapCountItem);
						item.setCategory(category);
						itemService.addItem(item);
						++categoryMapCountItem;
						fileSystemStorageService.deleteFile(Paths.get(prefix + "\\" + category.getName() + "\\" + year + "\\" + filename));
					} catch (Exception e) {
						// ignored
						fileSystemStorageService.deleteFile(Paths.get(prefix + "\\" + category.getName() + "\\" + year + "\\" + filename));
					}
				}
				category.setMapCountItem(categoryMapCountItem);
				categoryService.addCategory(category);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static ArrayList<String> getRandomList(ArrayList<String> fileList) {
		ArrayList<Integer> randomList = Utils.getRandomList(0, fileList.size());
		ArrayList<String> decadeRandomItems = new ArrayList<>();
		for (int i = 0; i < randomList.size(); i++) {
			decadeRandomItems.add(fileList.get(randomList.get(i)));
		}
		return decadeRandomItems;
	}

	private static Properties getCategoryAndYearAndDecadeList(ArrayList<String> fileList) {
		if (fileList == null || fileList.size() <= 0) {
			return null;
		}
		Properties prop = new Properties();
		int decade = 1990;
		String key = "";
		for (int i = 0; i < fileList.size(); i++) {
			String[] parts = fileList.get(i).split("\\\\");
			if (parts.length < 4) {
				LogMessage.logx("File Path Error " + fileList.get(i));
				continue;
			}
			int year = Integer.valueOf(parts[2]);
			decade = year - year % 10;
			key = parts[1] + ".YLC." + decade;
			if (prop.get(key) == null) {
				ArrayList<String> fileNames = new ArrayList<String>();
				fileNames.add(year + ".YLC." + parts[3]);
				prop.put(key, fileNames);
			} else {
				ArrayList<String> fileNames = (ArrayList<String>) prop.get(key);
				fileNames.add(year + ".YLC." + parts[3]);
			}
		}
		return prop;
	}

}
