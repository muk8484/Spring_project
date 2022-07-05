package com.myweb.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.board.service.BoardService;
import com.myweb.image.service.ImageService;
import com.myweb.notice.service.NoticeService;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MainController {
	
	@Autowired
	@Qualifier("bs")
	private BoardService boardService;
	
	@Autowired
	@Qualifier("ns")
	private NoticeService noticeService;
	
	@Autowired
	@Qualifier("is")
	private ImageService imageService;
	
	private final String MODULE = "main";
	
	@RequestMapping("/")
	public String mainList(PageObject pageObject, Model model) throws Exception {
		
		pageObject.setPerPageNum(6);
		
		model.addAttribute("boardList", boardService.list(pageObject));
		model.addAttribute("noticeList", noticeService.list(pageObject));
		
		pageObject.setPerPageNum(4);
		model.addAttribute("imageList", imageService.list(pageObject));
		model.addAttribute("pageObject", pageObject);
		
		return MODULE + "/main";
	}
	
}



