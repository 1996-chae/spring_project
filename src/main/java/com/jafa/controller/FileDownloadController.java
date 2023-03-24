package com.jafa.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDownloadController {
	
	// 이미지 나타내기
	@GetMapping("/imgDisplay")
	public ResponseEntity<byte[]>  imgDisplay(String filePath, String fileName) throws IOException {
		File file = new File(filePath);
		if(!file.exists()) {
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		HttpHeaders headers = new HttpHeaders();
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		headers.add("Content-Type", fileNameMap.getContentTypeFor(fileName));
		
		return new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),headers,HttpStatus.OK);
	}
	
	
	// 파일 다운로드
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> download( @RequestHeader("User-Agent") String userAgent,
			String filePath,String fileName) throws UnsupportedEncodingException{
		
		Resource resource = new FileSystemResource(filePath);
		if(!resource.exists()) { // 파일이 존재하지 않으면 
			return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		// 다운로드시 이름 처리 
		HttpHeaders headers = new HttpHeaders();
		String downloadFileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		headers.add("Content-Disposition", "attach;fileName="+downloadFileName);
		
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK); 
	}
}
