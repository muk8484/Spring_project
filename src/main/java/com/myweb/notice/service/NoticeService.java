package com.myweb.notice.service;

import java.util.List;

import com.myweb.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;

public interface NoticeService {
	
		// 1. 공지사항 리스트
		public List<NoticeVO> list(PageObject pageObject) throws Exception;
		
		
		// 2. 공지사항 글보기
		public NoticeVO view(Long no) throws Exception;
		
		// 3. 공지사항 글쓰기
		public int write(NoticeVO vo) throws Exception;
		
		// 4. 공지사항 글수정
		public int update(NoticeVO vo) throws Exception;
		
		// 5. 공지사항 글삭제
		public int delete(Long no) throws Exception;
}
