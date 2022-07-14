package com.myweb.partner.vo;

import com.myweb.member.vo.MemberVO;

import lombok.Data;

@Data
public class partnerVO {
	
	private String id; 
	private String companyName; 
	private String email; 
	private String postcode;
	private String roadAddress;
	private String jibunAddress;
	private String detailAddress;
	private String extraAddress;
	
}
