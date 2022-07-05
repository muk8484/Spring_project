package com.myweb.member.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberVO {
	
	private String id;
	private String pw;
	private String name;
	private String gender;
	// 날짜 형식의 데이터를 입력하는 곳에 어떤 형식의 날짜 문자열이 들어오는지 정해주는 어노테이션
	// 날짜형 입력을 받을 때 문자열로 들어오므로 패턴을 지정해서 정의해 놓으면 Date 객체로 만들 때 사용한다.
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	private String tel;
	private String email;
	private Date regDate;
	private Date conDate;
	private String status;
	private String Photo;
	private int gradeNo;
	private String gradeName;
	// 이미지 데이터를 받는 변수 선언
	private MultipartFile imageFile;
	// 이미지 바꾸기나 삭제를 할 경우 지워질 파일 정보가 필요하다.
	private String deleteFileName;
}
