package com.jafa.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jafa.domain.AttachVO;
import com.jafa.domain.FileType;

@Component
public class FileUploadUtils {
	
	private final static String BOARD_UPLOAD_FOLDER = "c:/book_board/board";
	
	public List<AttachVO> getAttachVOAndUpload(Long bno, MultipartFile[] multipartFiles){
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		for(MultipartFile mf : multipartFiles) {
			if(!mf.isEmpty()) { // MultipartFile객체에 파일정보가 있을 때 
				AttachVO attachVo = AttachVO.builder()
						.bno(bno)
						.fileName(mf.getOriginalFilename())
						.filePath(getFilePath(bno,mf.getOriginalFilename()))
						.fileType(getFileType(mf.getContentType()))
						.build();
				attachList.add(attachVo);
				
				// 파일업로드 
				File folder = new File(BOARD_UPLOAD_FOLDER+"/"+bno);
				File file = new File(attachVo.getFilePath());
				if(!folder.exists()) {
					folder.mkdirs();
				}
				try {
					mf.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return attachList; 
	}

	// 업로드한 파일 경로 
	private String getFilePath(Long bno, String originalFilename) {
		// 1번게시물, 코끼리.png
		// c:/file02_repo/board/1/f390dkd93-d89dkdid-dkdkd-dd38djddh_코끼리.png
		String uuid = UUID.randomUUID().toString();
		return BOARD_UPLOAD_FOLDER+"/"+bno+"/"+uuid+"_"+originalFilename;
	}

	// 파일 타입 결정
	private FileType getFileType(String contentType) {
		if(contentType.startsWith("image")) return FileType.IMAGE; 
		else return FileType.OTHER; 
	}

	public void deleteAllFile(Long bno) {
		// 1번 게시물 : c:/file02_repo/board/1
		File delFolder = new File(BOARD_UPLOAD_FOLDER+"/"+bno);
		if(delFolder.exists()) {
			// 대상 폴더안에 존재하는 모든 파일의 경로를 파일객체로 만듬 
			File[] listFiles = delFolder.listFiles();
			for(File f : listFiles) {
				f.delete(); // 모든파일 삭제 
			}
			delFolder.delete(); // 폴더 삭제 
		}
	}
}
