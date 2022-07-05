package com.myweb.message.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.myweb.message.mapper.MessageMapper;
import com.myweb.message.vo.MessageVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@Service
@Qualifier("ms")
@Log4j
public class MessageServiceImpl implements MessageService{
	
	@Inject
	MessageMapper mapper;

	@Override
	public Long getTotalRow(PageObject pageObject) throws Exception{
		// TODO Auto-generated method stub
		return mapper.getTotalRow(pageObject);
	}

	@Override
	public List<MessageVO> list(PageObject pageObject) throws Exception{
		// TODO Auto-generated method stub
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		return mapper.list(pageObject);
	}

	@Override
	public MessageVO view(MessageVO vo) throws Exception{
		// TODO Auto-generated method stub
		mapper.setReaded(vo);
		return mapper.view(vo);
	}

	@Override
	public int write(MessageVO vo) throws Exception{
		// TODO Auto-generated method stub
		return mapper.write(vo);
	}
	
}
