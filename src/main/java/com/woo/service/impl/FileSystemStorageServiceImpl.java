package com.woo.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.woo.core.attributes.Link;
import com.woo.service.types.FileSystemStorageService;

@Service
public class FileSystemStorageServiceImpl implements FileSystemStorageService {

	private Path rootLocation;

	@Autowired
	public FileSystemStorageServiceImpl() {
		try {
			rootLocation = Paths.get(Link.rootLocationValue);
			boolean exists = Files.exists(rootLocation);
			if (!exists) {
				Files.createDirectory(rootLocation);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<String> getImgResourceFileList() throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		Files.walk(rootLocation).forEach(filePath -> {
			if (Files.isRegularFile(filePath)) {
				String file = filePath.getFileName().toString();
				String ext = file.substring(file.lastIndexOf("."));
				if (ext.equals(".jpg") || ext.equals(".jpeg") || ext.equals(".JPG") || ext.equals(".JPEG")) {
					list.add(filePath.toString());
				}
			}
		});
		return list;
	}

	@Override
	public Resource getFile(Path path, String filename) {
		try {
			Path file = load(path, filename);
			if (file == null) {
				return null;
			}
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				System.out.println("File not exist or readable filename:" + filename);
			}
		}
		catch (Exception e) {
			System.out.println("Exception Here");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteFile(Path path) {
		try {
			Files.delete(path);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Path load(Path path, String filename) {
		return path.resolve(filename);
	}

	@Override
	public void createFileDirectory() {
		try {
			String category = "upload-dir\\person";
			Path rootLocation = Paths.get(category);
			boolean exists = Files.exists(rootLocation);
			if (!exists) {
				Files.createDirectory(rootLocation);
			}

			for (int year = 1992; year < 2019; year++) {
				if (!Files.exists(Paths.get(category + "\\" + year))) {
					Files.createDirectory(Paths.get(category + "\\" + year));
					System.out.println("File Created : " + category + "\\" + year);
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
