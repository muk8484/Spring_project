package com.myweb.notice.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class NoticeVO {
	
	private long no;
	private String title;
	private String content;
	// 날짜형을 입력 할때 -> 형태에 맞는 문자열로 만들어 주면 날짜형 데이터로 변경해서 받아온다.
	// 400 오류가 나면 데이터 제대로 전달되지 않고 있다.
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	private Date writeDate;
	private Date updateDate;
	
}
