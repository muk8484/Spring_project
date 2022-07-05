package com.myweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.image.service.ImageService;
import com.myweb.image.vo.ImageVO2;
import com.myweb.member.vo.LoginVO;
import com.myweb.util.fileUpload.multipleUtil;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/image")
@Log4j
public class UploadController {
	
	@Autowired
	@Qualifier("is")
	private ImageService service;
	
	private final String MODULE = "image";
	
	// 등록 폼
		@GetMapping("/write2.do")
		public String writeForm() {
			return MODULE + "/write2";
		}
		
		// 등록 처리
		@PostMapping("/write2.do")
		public String write(ImageVO2 vo, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
			
			vo.setId(LoginVO.getId(request));
			
			// 한줄로 간단하게 만든다. 
//			String path = "/upload/image";
//			String fileName = FileUtil.upload(path, vo.getImageFile(), request);
//			vo.setFileName(fileName);
			
			// 파일 저장후 vo객체에 저장 파일의 정보를 저장해 놓는다.
			vo.setFileName(multipleUtil.upload("/upload/" + MODULE, vo.getImageFile(), request));
			
			// DB에 저장한다.
			service.write2(vo);
			
			log.info(vo);
			
			// 처리 결과 메시지 저장
			rttr.addFlashAttribute("msg","이미지 등록 성공!!");
			
			// 프로그램 재우기 - 1초 재우기
			// 이미지 게시판 리스트로 갈때 파일 처리가 마무리 되지 않은 상태에서 표시하라고 하면 새로 등록한 파일이 보이지 않게된다.
			Thread.sleep(1000);
			
			
			return "redirect:list.do";
		}
	
}
