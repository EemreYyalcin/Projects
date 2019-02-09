package com.woo.service.types;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import org.springframework.core.io.Resource;

public interface FileSystemStorageService {

	ArrayList<String> getImgResourceFileList() throws IOException;

	Resource getFile(Path path, String filename);

	void deleteFile(Path path);

	Path load(Path path, String filename);

	void createFileDirectory();

}
