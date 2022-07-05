package com.myweb.message.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.member.vo.LoginVO;
import com.myweb.message.service.MessageService;
import com.myweb.message.vo.MessageVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/message")
@Log4j
public class MessageController {
	
	@Autowired
	@Qualifier("ms")
	private MessageService service;
	
	private final String MODULE = "message";
	
	// 1. 메세지 리스트
	@GetMapping("/list.do")
	public String list(PageObject pageObject, Model model, HttpServletRequest request,
			@RequestParam(value = "mode", defaultValue = "3") String mode) throws Exception{	
		
		pageObject.setAccepter(LoginVO.getId(request));
		pageObject.setAcceptMode(Integer.parseInt(mode));
		
		model.addAttribute("list", service.list(pageObject));
		model.addAttribute("pageObject", pageObject);
		
		return MODULE + "/list";
		
	};
	
	// 2. 메세지 보기
	@GetMapping("/view.do")
	public String view(Long no, Model model, HttpServletRequest request,
			@ModelAttribute PageObject pageObject) throws Exception{
		
		log.info("no= " + no );
		
		// 데이터 수집 - no, id(읽음처리 를 하기위해서 필요하다.)
		MessageVO vo = new MessageVO();
		vo.setNo(no);
		vo.setAccepter(LoginVO.getId(request));
		vo = service.view(vo);
		
		// 글 내용 중에서 줄바꿈 처리 해야만 한다.
		vo.setContent(vo.getContent().replace("\n", "<br>"));
		
		model.addAttribute("vo", vo);
		
		return MODULE + "/view";
	};
	
	// 3. 메세지 쓰기 폼
	@GetMapping("/writeForm.do")
	public String write() throws Exception{
		return MODULE + "/writeForm";
	};
	
	// 4. 메세지 글쓰기 처리
	@PostMapping("/write.do")
	public String write(MessageVO vo, RedirectAttributes rttr, HttpServletRequest request) throws Exception{
		log.info("write() : " + vo);
		
		vo.setSender(LoginVO.getId(request));
		int result = service.write(vo);
		
		rttr.addFlashAttribute("msg", "메세지를 보냈습니다.");
		
		return "redirect:list.do";
	};

	// 7. 메세지 글삭제
	@GetMapping("/delete.do")
	public String delete(Long no, RedirectAttributes rttr) throws Exception{
//		service.delete(no);
		rttr.addFlashAttribute("msg", "삭제 완료!!");
		return "redirect:list.do";
	};
	
}
