package com.myweb.board.mapper;

import java.util.List;

import com.myweb.board.vo.BoardReplyVO;

public interface BoardReplyMapper {
	
	public List<BoardReplyVO> list(Long no) throws Exception;
	
	public int write(BoardReplyVO vo) throws Exception;
	
	public int update(BoardReplyVO vo) throws Exception;
	
	public int delete(Long rno) throws Exception;
	
}
