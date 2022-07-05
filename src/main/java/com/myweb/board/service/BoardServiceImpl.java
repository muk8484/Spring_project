package com.myweb.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.myweb.board.mapper.BoardMapper;
import com.myweb.board.vo.BoardVO;
import com.webjjang.util.PageObject;

@Service
@Qualifier("bs")
public class BoardServiceImpl implements BoardService{
	
	// Mapper 자동 DI
	@Inject
	private BoardMapper mapper;
	
	// 1. 게시판 리스트
	@Override
	public List<BoardVO> list(PageObject pageObject) throws Exception{
		// 페이지 정보 계산
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
//		System.out.println("boardService.list().pageObject - " + pageObject);
		return mapper.list(pageObject);
	};
	
	// 2. 게시판 글보기
	@Override
	public BoardVO view(Long no, int inc) throws Exception{
		if(inc == 1)mapper.increase(no);
		return mapper.view(no);
	};
	
	// 3. 게시판 글쓰기
	@Override
	public int write(BoardVO vo) throws Exception{
		return mapper.write(vo);
	};
	
	// 4. 게시판 글수정
	@Override
	public int update(BoardVO vo) throws Exception{
		return mapper.update(vo);
	};
	
	// 5. 게시판 글삭제
	@Override
	public int delete(Long no) throws Exception{
		return mapper.delete(no);
	}


}
