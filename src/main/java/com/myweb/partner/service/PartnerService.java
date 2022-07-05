package com.myweb.partner.service;

import java.util.List;

import com.myweb.partner.vo.partnerVO;
import com.webjjang.util.PageObject;

public interface PartnerService {
		
	// 파트너 가입폼 - memberTable >> id, email 셋팅
	public partnerVO writeForm(String id) throws Exception;
	
	// 파트너 가입처리
	public Long write(partnerVO vo) throws Exception;
				
}
