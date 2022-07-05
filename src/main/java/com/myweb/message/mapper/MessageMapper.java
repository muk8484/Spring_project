package com.myweb.message.mapper;

import java.util.List;

import com.myweb.message.vo.MessageVO;
import com.webjjang.util.PageObject;

public interface MessageMapper {
	
	// 메시지 갯수 ( 조건에 맞는 ) 
	public Long getTotalRow(PageObject pageObject)throws Exception;
	
	// 메세지 리스트
	public List<MessageVO> list(PageObject pageObject)throws Exception;
	
	// 메세지 보기
	public MessageVO view(MessageVO vo)throws Exception;	
	
	// 메세지 읽은날짜 업데이트
	public int setReaded(MessageVO vo)throws Exception;	
	
	// 메세지 보내기
	public int write(MessageVO vo)throws Exception;
	
	// 메세지 지우기
}
