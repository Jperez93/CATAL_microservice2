package com.secondHand.catalog.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagesManager {
	private String folder = "images//";
	
	public String saveImage(MultipartFile file) throws IOException {
		if(!file.isEmpty()) {
			byte[] imagesBytes = file.getBytes();
			Path path = Paths.get(folder+file.getOriginalFilename());
			Files.write(path, imagesBytes);
			return file.getOriginalFilename();
		}
		return "default.png";
	}
	
	public void deleteImage(String name) {
		File file = new File(folder+name);
		file.delete();
	}

}
