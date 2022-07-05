package com.myweb.image.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.myweb.image.mapper.ImageMapper;
import com.myweb.image.vo.ImageVO;
import com.myweb.image.vo.ImageVO2;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@Service
@Qualifier("is")
@Log4j
public class ImageServiceImpl implements ImageService {
	
	@Inject
	private ImageMapper mapper;
	
	@Override
	public List<ImageVO> list(PageObject pageObject) throws Exception{
		// TODO Auto-generated method stub
		// 전체 데이터의 갯수구해서 PageObject에 세팅한다.
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		log.info("ImageServiceImpl.list() : " + pageObject);
		// 데이터를 가져와서 리턴
		return mapper.list(pageObject);
	}

	@Override
	public ImageVO view(Long no) throws Exception{
		// TODO Auto-generated method stub
		return mapper.view(no);
	}

	@Override
	public int write(ImageVO vo) throws Exception{
		// TODO Auto-generated method stub
		return mapper.write(vo);
	}

	@Override
	public int changeImage(ImageVO vo) throws Exception{
		// TODO Auto-generated method stub
		return mapper.changeImage(vo);
	}

	@Override
	public int update(ImageVO vo) throws Exception{
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(ImageVO vo) throws Exception{
		// TODO Auto-generated method stub
		return mapper.delete(vo);
	}

	@Override
	public int write2(ImageVO2 vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.write2(vo);
	}

}
