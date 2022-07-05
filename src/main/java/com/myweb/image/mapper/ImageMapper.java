package com.myweb.image.mapper;

import java.util.List;

import com.myweb.image.vo.ImageVO;
import com.myweb.image.vo.ImageVO2;
import com.webjjang.util.PageObject;

public interface ImageMapper {
	
	// 리스트
	public List<ImageVO> list(PageObject pageObject) throws Exception;
	// 전테 데이터 갯수
	public Long getTotalRow(PageObject pageObject) throws Exception;
	// 보기
	public ImageVO view(Long no) throws Exception;
	// 등록
	public int write(ImageVO vo) throws Exception;
	// 등록2
	public int write2(ImageVO2 vo) throws Exception;
	// 정보 수정
	public int update(ImageVO vo) throws Exception;
	// 사진 수정
	public int changeImage(ImageVO vo) throws Exception;
	// 삭제
	public int delete(ImageVO vo) throws Exception;

}
