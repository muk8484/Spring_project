package com.myweb.member.vo;

import javax.servlet.http.HttpServletRequest;

import lombok.Data;

// 아이디, 비밀번호, 이름, 등급번호, 사진이름
@Data
public class LoginVO {
	
	private String id, pw, name, photo;
	private int gradeNo;
	private String gradeName;
	// 새로운 메시지를 저장하는 변수 -> 서브쿼리
	// 메세지 시스템에서 새 메시지를 읽으면 세션에 있는 새메시지 정보를 -1 처리를 해 줘야한다.
	private long newMessage;
	
	
	// request에서 로그인한 정보 중에서 아이디를 꺼내서 전달해 주는 static 메서드
		public static String getId(HttpServletRequest request) throws Exception{
			LoginVO vo = (LoginVO)request.getSession().getAttribute("login");
			// 로그인이 되지 않은 경우
//			if (vo == null) throw new Exception("로그인이 되어 있지 않습니다.");
			if (vo == null) return null;
			return vo.getId();
		}
}
