package com.myweb.sample.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.sample.mapper.SampleMapper;

@Service
@Qualifier("stsi")
public class SamplTxServiceImpl implements SampleTxService {
	
	@Inject
	private SampleMapper mapper;
	
	// 만약에 메서드안의 DB처리가 한개라도 오류가 나면 rollback시킨다.
	@Transactional
	@Override
	public int addData() {
		// TODO Auto-generated method stub
		
		// 정상 입력 처리 - 위에 Transactional 처리를 하면 처리 단위 블록안에 하나라도 오류가 있으면 rollback 시킨다.
		// ex) 댓글 insert 후 댓글 카운트 업데이트 
		mapper.insertCol();
		// PK 5가 중복 오류가 남.
		mapper.insertCol();
		
		return 1;
	}

}
