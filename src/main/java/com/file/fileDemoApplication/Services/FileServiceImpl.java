package com.file.fileDemoApplication.Services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		//File name
		String name=file.getOriginalFilename();
		
		
		//generates random file name that we upload & we can upload any file pdf, jpg, png 
		String randomID=UUID.randomUUID().toString();
		String fileName1=randomID.concat(name.substring(name.lastIndexOf(".")));
		
		//Full path
		String filePath=path+File.separator+fileName1;
		
		
		//create folder if not created
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		//copy the file
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		//return file name
		return name;
	}

}
