package com.work.club.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.work.club.service.FileService;
@Service
public class FileServiceImpl  implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
	     //fileName;
		String fileName = path + file.getOriginalFilename();
		//full path;
		String filePath = path + File.separator+fileName;
		//create folder if not created;
		File folder = new File(path);
		if(!folder.exists()) {
			folder.mkdir();
		}
		//file copy;
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return fileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullFilePath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullFilePath);
		return inputStream;
	}

}
