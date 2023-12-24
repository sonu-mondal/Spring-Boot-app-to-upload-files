package com.file.fileDemoApplication.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.file.fileDemoApplication.Payload.FileResponse;
import com.file.fileDemoApplication.Services.FileServiceImpl;

@RestController
@RequestMapping("/file")
public class fileController {
	@Autowired
	private FileServiceImpl fileServiceImpl;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUpload(@RequestParam("image") MultipartFile image){
		
		String fileName=null;
		try {
			fileName = this.fileServiceImpl.uploadImage(path, image);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<FileResponse>(new FileResponse(null,"Image is not uploaded due to error on server"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<FileResponse>(new FileResponse(fileName,"Image is successfuly uploaded"), HttpStatus.OK);
	}

}
