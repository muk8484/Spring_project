package com.myweb.partner.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.myweb.partner.mapper.PartnerMapper;
import com.myweb.partner.vo.partnerVO;

@Service
@Qualifier("ps")
public class PartnerServiceImpl implements PartnerService{
	
	// Mapper 자동 DI
	@Inject
	private PartnerMapper mapper;
	

	@Override
	public partnerVO writeForm(String id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.writeForm(id);
	}


	@Override
	public Long write(partnerVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.write(vo);
	}

}
