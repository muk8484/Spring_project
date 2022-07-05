package com.myweb.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	// 404 예외 클래스 파일을 넘겨서 생성해서 사용하도록 지정
	@ExceptionHandler(NoHandlerFoundException.class)
	// 요청에 대한 응답을 할때 상태 코드값을 넘겨 준다. 정상 : 200, 페이지 없음 : 404
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		
		log.error("/////////////// 404 error //////////////");
		log.error("handle404() : 요청하신 페이지가 존재하지 않습니다.");;
		return "error/custom404";
	}
	
	// 500 예외
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		
		// 콘솔에 에러 메시지 출력
		log.error("///////////////예외처리//////////////");
		log.error("exception..." + ex.getMessage());
		
		// JSP에서 Exception 객체를 EL 객체에서 사용할 수 있도록 Model에 담는다.
		model.addAttribute("exception", ex);
		
		// model 출력
		log.error(model);
		
		return "error/error_500";
	}
}
