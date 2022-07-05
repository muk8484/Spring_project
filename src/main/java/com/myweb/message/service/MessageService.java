package com.myweb.message.service;

import java.util.List;

import com.myweb.message.vo.MessageVO;
import com.webjjang.util.PageObject;

public interface MessageService {
	
	// 새로운 메시지 갯수
	public Long getTotalRow(PageObject pageObject) throws Exception;
	
	// 메세지 리스트
	public List<MessageVO> list(PageObject pageObject)throws Exception;
	
	// 메세지 보기
	public MessageVO view(MessageVO vo)throws Exception;	

	// 메세지 보내기
	public int write(MessageVO vo)throws Exception;	
	
	// 메세지 지우기
	
}
