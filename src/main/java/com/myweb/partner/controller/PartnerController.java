package com.myweb.partner.controller;

import java.net.http.HttpRequest;

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
import com.myweb.partner.service.PartnerService;
import com.myweb.partner.vo.partnerVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.file.FileUtil;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/partner")
@Log4j
public class PartnerController {
	
	@Autowired
	@Qualifier("ps")
	private PartnerService service;
	
	private final String MODULE = "partner";
	
	
	// 1. 파트너가입 페이지 이동
	@GetMapping("/writeForm.do")
	public String list(HttpServletRequest request, Model model) throws Exception{
		
		String id = LoginVO.getId(request);
		model.addAttribute("vo", service.writeForm(id));
		return MODULE + "/writeForm";
	}
	
	// 2. 파트너 가입처리
	@PostMapping("/write.do")
	public String write(partnerVO vo, Model model, RedirectAttributes rttr) throws Exception { 
		
		log.info("write().vo : " + vo);
		
		// DB에 데이터 쓰기
		service.write(vo);
		rttr.addFlashAttribute("msg", "파트너 가입 완료!!");
		
		return "redirect:/";
	}

}
