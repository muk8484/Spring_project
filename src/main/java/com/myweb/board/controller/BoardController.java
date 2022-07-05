package com.myweb.board.controller;

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

import com.myweb.board.service.BoardService;
import com.myweb.board.vo.BoardVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	@Autowired
	@Qualifier("bs")
	private BoardService service;
	
	private final String MODULE = "board";
	
	// 1. 게시판 리스트
	@GetMapping("/list.do")
	public String list(PageObject pageObject, Model model) throws Exception{
		model.addAttribute("list", service.list(pageObject));
		return MODULE + "/list";
	};

	// 2. 게시판 글보기
	@GetMapping("/view.do")
	public String view(Long no, @RequestParam(defaultValue = "0") int inc, Model model,
			@ModelAttribute PageObject pageObject) throws Exception{
		log.info("no= " + no + " , inc= " + inc);
		model.addAttribute("vo", service.view(no, inc));
		return MODULE + "/view";
	};
	
	// 3. 게시판 글쓰기 폼
	@GetMapping("/write.do")
	public String write() throws Exception{
		return MODULE + "/write";
	};
	
	// 4. 게시판 글쓰기 처리
	@PostMapping("/write.do")
	public String write(BoardVO vo, RedirectAttributes rttr, int perPageNum) throws Exception{
		log.info("write() : " + vo);
		service.write(vo);
		rttr.addFlashAttribute("msg", "등록 완료!!");
		return "redirect:list.do?page=1&perPageNum=" + perPageNum ;
	};
	
	// 5. 게시판 글수정 폼
	@GetMapping("/update.do")
	public String update(Long no, Model model, 
			@ModelAttribute PageObject pageObject) throws Exception{
		model.addAttribute("vo", service.view(no, 0)) ;
		return MODULE + "/update";
	};
	
	// 6. 게시판 글수정 처리
	@PostMapping("/update.do")
	public String update(BoardVO vo, RedirectAttributes rttr, 
			@ModelAttribute PageObject pageObject) throws Exception{
		log.info("update() : " + vo);
		service.update(vo);
		rttr.addFlashAttribute("msg", "수정 완료!!");
		return "redirect:view.do?no=" + vo.getNo()
			+ "&page=" + pageObject.getPage()
			+ "&perPageNum=" + pageObject.getPerPageNum();
	};
	
	// 7. 게시판 글삭제
	@GetMapping("/delete.do")
	public String delete(Long no, RedirectAttributes rttr) throws Exception{
		service.delete(no);
		rttr.addFlashAttribute("msg", "삭제 완료!!");
		return "redirect:list.do";
	};
	
}
