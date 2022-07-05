package com.myweb.board.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BoardReplyVO {
	
	private Long rno; // 댓글번호
	private Long no;  // 글번호 - FK
	private String content; // 내용
	private String writer;  // 작성자
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date writeDate; // 작성일
	
}
