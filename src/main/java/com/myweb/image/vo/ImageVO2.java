package com.myweb.image.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImageVO2 {
	
	private Long no;
	private String title;
	private String content;
	private String id;
	private String name; // member table에서 가져온다. 조인해서 해야만 한다.
	private Date writeDate;
	private int hit;
	private String fileName;
	// 이미지 데이터를 받는 변수 선언
	private MultipartFile[] imageFile;
	// 이미지 바꾸기나 삭제를 할 경우 지워질 파일 정보가 필요하다.
	private String deleteFileName;
}
