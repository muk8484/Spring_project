package com.myweb.notice.controller;

import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.notice.service.NoticeService;
import com.myweb.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;


@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	private static final Logger log = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	@Qualifier("ns")
	private NoticeService service;
	
	private final String MODULE = "notice";
	
	// 1. 공지사항 리스트
	// 현재 공지 : pre, 지난공지 : old, 예약공지 : res, 전체공지 : all
	@GetMapping("/list.do")
	public String list(PageObject pageObject, Model model) throws Exception{
		
		log.info("NoticeController.list().pageObject :  " + pageObject );
		
		// 페이지가 1보다 작으면 1페이지로 셋팅해 준다.
		if(pageObject.getPage() < 1) pageObject.setPage(1);
		
		model.addAttribute("list", service.list(pageObject));
		return MODULE + "/list";
	};

	// 2. 공지사항 글보기
	@GetMapping("/view.do")
	public String view(Long no, Model model, @ModelAttribute PageObject pageObject) throws Exception{
		
		log.info("NoticeController.view().no : " + no );
		
		NoticeVO vo = service.view(no);
		// 글 내용 중에서 줄바꿈 처리 해야만 한다.
		vo.setContent(vo.getContent().replace("\n", "<br>"));
		
		model.addAttribute("vo", vo );
		return MODULE + "/view";
	};
	
	// 3. 공지사항 글쓰기 폼
	@GetMapping("/write.do")
	public String write() throws Exception{
		return MODULE + "/write";
	};
	
	// 4. 공지사항 글쓰기 처리
	@PostMapping("/write.do")
	public String write(NoticeVO vo, RedirectAttributes rttr, int perPageNum) throws Exception{
		log.info("NoticeController.write().vo : " + vo);
		service.write(vo);
		rttr.addFlashAttribute("msg", "등록 완료!!");
		return "redirect:list.do?page=1&perPageNum=" + perPageNum;
	};
	
	// 5. 공지사항 글수정 폼
	@GetMapping("/update.do")
	public String update(Long no, Model model, 
			@ModelAttribute PageObject pageObject) throws Exception{
		
		log.info("NoticeController.updateForm().no : " + no);
		
		model.addAttribute("vo", service.view(no)) ;
		return MODULE + "/update";
	};
	
	// 6. 공지사항 글수정 처리
	@PostMapping("/update.do")
	public String update(NoticeVO vo, RedirectAttributes rttr, 
			@ModelAttribute PageObject pageObject) throws Exception{
		
		log.info("NoticeController.update().vo : " + vo);
		
		service.update(vo);
		
		rttr.addFlashAttribute("msg", "수정 완료!!");
		return "redirect:view.do?no=" + vo.getNo()
			+ "&page=" + pageObject.getPage()
			+ "&perPageNum=" + pageObject.getPerPageNum()
			+ "&key=" + pageObject.getKey()
			// 자바 부분의 한글코드 와 운영 한글코드 가 다르므로 자바에서 넣으면 깨진다. 엔코딩을 해야한다.
			+ "&word=" + URLEncoder.encode(pageObject.getWord(),"utf-8") ;
	};
	
	// 7. 공지사항 글삭제
	@GetMapping("/delete.do")
	public String delete(Long no, RedirectAttributes rttr) throws Exception{
		
		log.info("NoticeController.delete().no : " + no);
		
		service.delete(no);
		
		rttr.addFlashAttribute("msg", "삭제 완료!!");
		return "redirect:list.do";
	};
	
}
