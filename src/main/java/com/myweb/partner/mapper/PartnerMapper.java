package com.myweb.partner.mapper;

import java.util.List;

import com.myweb.board.vo.BoardVO;

import com.myweb.partner.vo.partnerVO;
import com.webjjang.util.PageObject;

// interface 작성 -> interface 상속 받은 클래스 작성 -> 클래스 생성 interface 타입으로 저장해서 실행
public interface PartnerMapper {
		
		// dao 에서 작성한 메서드 형식으로 만들어준다.
		// interface만 만들어 두면 MyBatis 라이브러리에서 DAO를 대신 만들어 준다.
		// 주고 받는 데이터 타입 정의, sql 문과 처리 명령문이 필요하다. ->  BoardMapper.xml
		
		// 파트너가입 폼 id, email 셋팅
		public partnerVO writeForm(String id) throws Exception;
		
		// 파트너가입 처리
		public Long write(partnerVO vo) throws Exception;
		
}
