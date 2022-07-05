package com.myweb.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.myweb.board.mapper.BoardReplyMapper;
import com.myweb.board.vo.BoardReplyVO;

@Service
@Qualifier("brs")
public class BoardReplyServiceImpl implements BoardReplyService{
	
	@Inject
	private BoardReplyMapper mapper;
	
	@Override
	public List<BoardReplyVO> list(Long no) throws Exception{
		// TODO Auto-generated method stub
		return mapper.list(no);
	}

	@Override
	public int write(BoardReplyVO vo) throws Exception{
		// TODO Auto-generated method stub
		return mapper.write(vo);
	}

	@Override
	public int update(BoardReplyVO vo) throws Exception{
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(Long rno) throws Exception{
		// TODO Auto-generated method stub
		return mapper.delete(rno);
	}

}
