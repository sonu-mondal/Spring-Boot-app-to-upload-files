package com.file.fileDemoApplication.Services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	public String uploadImage(String path, MultipartFile file) throws IOException;

}
