package com.myweb.sample.mapper;

import org.apache.ibatis.annotations.Insert;

public interface SampleMapper {
	
	@Insert("insert into grade values(5, '우수회원')")
	public int insertCol();
}
