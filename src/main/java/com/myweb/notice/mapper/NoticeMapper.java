package com.myweb.notice.mapper;

import java.util.List;

import com.myweb.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;

// interface 작성 -> interface 상속 받은 클래스 작성 -> 클래스 생성 interface 타입느로 저장해서 실행
public interface NoticeMapper {
		
		// dao 에서 작성한 메서드 형식으로 만들어준다.
		// interface만 만들어 두면 MyBatis 라이브러리에서 DAO를 대신 만들어 준다.
		// 주고 받는 데이터 타입 정의, sql 문과 처리 명령문이 필요하다. ->  NoticeMapper.xml
	
		// 1. 게시판 리스트
		public List<NoticeVO> list(PageObject pageObject) throws Exception;
		
		// 1-1. 게시판 리스트의 페이지 처리를 위한 전체 데이터 갯수
		public Long getTotalRow(PageObject pageObject) throws Exception;
		
		// 2. 게시판 글보기
		public NoticeVO view(Long no) throws Exception;
		
		// 3. 게시판 글쓰기
		public int write(NoticeVO vo) throws Exception;
		
		// 4. 게시판 글수정
		public int update(NoticeVO vo) throws Exception;
		
		// 4. 게시판 글삭제
		public int delete(Long no) throws Exception;
}
