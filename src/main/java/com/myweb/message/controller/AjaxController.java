package com.myweb.message.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myweb.member.vo.LoginVO;
import com.myweb.message.service.MessageService;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/ajax")
@Log4j
public class AjaxController {
	
	private final String MODULE = "ajax";
	
	@Autowired
	@Qualifier("ms")
	private MessageService service;
	

	PageObject pageObject = new PageObject();
	
	@GetMapping(value = "/msgCount.do", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> massageCnt(PageObject pageObject, HttpServletRequest request) throws Exception {
		
		String id = LoginVO.getId(request);
		
		pageObject.setAcceptMode(4);
		pageObject.setAccepter(id);
		
		log.info("ResponseEntity<String> messageCnt--------------" + id);
		String result = String.valueOf(service.getTotalRow(pageObject));
		log.info("ResponseEntity<String> messageCnt--------------" + result);
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
