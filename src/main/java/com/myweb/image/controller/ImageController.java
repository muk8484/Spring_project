package com.myweb.image.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.image.service.ImageService;
import com.myweb.image.vo.ImageVO;
import com.myweb.member.vo.LoginVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.file.FileUtil;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/image")
@Log4j
public class ImageController {
	
	@Autowired
	@Qualifier("is")
	private ImageService service;
	
	private final String MODULE = "image";
	
	// 리스트
	@GetMapping("/list.do")
	public String list(PageObject pageObject, Model model) throws Exception{
		
		pageObject.setPerPageNum(8);
		
		model.addAttribute("list", service.list(pageObject));
		model.addAttribute("pageObject", pageObject);
		return MODULE + "/list";
	}
	
	// 보기
	@GetMapping("view.do")
	public String view(Long no, @ModelAttribute PageObject pageObject, Model model) throws Exception{
		
		ImageVO vo = service.view(no);
		
		// 글 내용의 엔터를 <br>로 바꾸어야 보기를 할 때 줄 바꿈이 된다.
		vo.setContent(vo.getContent().replace("\n", "<br>"));
		
		model.addAttribute("vo", vo);
		
		return MODULE + "/view";
	}
	// 등록 폼
	@GetMapping("/write.do")
	public String writeForm() {
		return MODULE + "/write";
	}
	
	// 등록 처리
	@PostMapping("/write.do")
	public String write(ImageVO vo, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		vo.setId(LoginVO.getId(request));
		
		// 한줄로 간단하게 만든다. 
//		String path = "/upload/image";
//		String fileName = FileUtil.upload(path, vo.getImageFile(), request);
//		vo.setFileName(fileName);
		
		// 파일 저장후 vo객체에 저장 파일의 정보를 저장해 놓는다.
		vo.setFileName(FileUtil.upload("/upload/" + MODULE, vo.getImageFile(), request));
		
		// DB에 저장한다.
		service.write(vo);
		
		log.info(vo);
		
		// 처리 결과 메시지 저장
		rttr.addFlashAttribute("msg","이미지 등록 성공!!");
		
		// 프로그램 재우기 - 1초 재우기
		// 이미지 게시판 리스트로 갈때 파일 처리가 마무리 되지 않은 상태에서 표시하라고 하면 새로 등록한 파일이 보이지 않게된다.
		Thread.sleep(1000);
		
		
		return "redirect:list.do";
	}
	
	// 사진 바꾸기 - 번호 , 지울파일이름(기존파일), 바꿀파일, 페이지, 한페이지당 갯수
	@PostMapping("/changeImage.do")
	public String changeImage(ImageVO vo, PageObject pageObject, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		log.info(pageObject);
		
		// 새로운 파일 올리기
		vo.setFileName(FileUtil.upload("/upload/" + MODULE, vo.getImageFile(), request));
		log.info(vo);
		// DB 정보 수정
		service.changeImage(vo);
		// 원래 파일 지우기
		// FileUtil.getRealPath(path, 지우려는 파일정보, request) -->실제적인 위치를 잡는다.
		FileUtil.remove(FileUtil.getRealPath("", vo.getDeleteFileName(), request));
		
		// 처리결과 메시지 저장
		rttr.addFlashAttribute("msg","이미지 변경 성공!!");
		
		return "redirect:view.do?no=" + vo.getNo() 
			+ "&page=" + pageObject.getPage()
			+ "&perPageNum=" + pageObject.getPerPageNum();
	}
	
	// 수정 폼
	@GetMapping("/update.do")
	public String updateForm(Long no, Model model, @ModelAttribute PageObject pageObject) throws Exception{
		System.out.println(no);
		model.addAttribute("vo", service.view(no));
		
		return MODULE + "/update";
	}
	
	// 수정 처리
	@PostMapping("/update.do")
	public String update(ImageVO vo, RedirectAttributes rttr, PageObject pageObject) throws Exception {
		
		log.info("update().vo : " + vo);
		
		// DB 저보수정 처리
		service.update(vo);
		
		rttr.addFlashAttribute("msg","이미지정보 수정 성공!!");
		
		return "redirect:view.do?no=" + vo.getNo() 
		+ "&page=" + pageObject.getPage()
		+ "&perPageNum=" + pageObject.getPerPageNum();
	}
	// 삭제
	@GetMapping("/delete.do")
	public String delete(ImageVO vo, PageObject pageObject, RedirectAttributes rttr, HttpServletRequest request) throws Exception {
		
		log.info("delete().vo : " + vo);
		
		// DB에서 데이터 삭제
		service.delete(vo);
		
		// 서버의 파일을 삭제
		// 원래 파일 지우기
		// FileUtil.getRealPath(path, 지우려는 파일정보, request) -->실제적인 위치를 잡는다.
		FileUtil.remove(FileUtil.getRealPath("", vo.getDeleteFileName(), request));
		
		rttr.addFlashAttribute("msg","이미지 글삭제 성공!!");
		
		return "redirect:list.do?perPageNum=" + pageObject.getPerPageNum();
	}
}
