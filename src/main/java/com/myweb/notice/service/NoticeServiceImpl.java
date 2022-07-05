package com.myweb.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.myweb.notice.mapper.NoticeMapper;
import com.myweb.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;

@Service
@Qualifier("ns")
public class NoticeServiceImpl implements NoticeService{
	
	// Mapper 자동 DI
	@Inject
	private NoticeMapper mapper;
	
	// 1. 공지사항 리스트
	@Override
	public List<NoticeVO> list(PageObject pageObject) throws Exception{
		// 페이지 정보 계산
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
//		System.out.println("noticeService.list().pageObject - " + pageObject);
		return mapper.list(pageObject);
	};
	
	// 2. 공지사항 글보기
	@Override
	public NoticeVO view(Long no) throws Exception{
		return mapper.view(no);
	};
	
	// 3. 공지사항 글쓰기
	@Override
	public int write(NoticeVO vo) throws Exception{
		return mapper.write(vo);
	};
	
	// 4. 공지사항 글수정
	@Override
	public int update(NoticeVO vo) throws Exception{
		return mapper.update(vo);
	};
	
	// 5. 공지사항 글삭제
	@Override
	public int delete(Long no) throws Exception{
		return mapper.delete(no);
	}


}
