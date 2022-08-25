package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.MemberDTO;

public interface MemberService {
	// 추상메서드
	public void insertMember(MemberDTO memberDTO);
	
//	MemberDTO memberDTO2 = memberService.userCheck(memberDTO);
	public MemberDTO userCheck(MemberDTO memberDTO);
	
//	MemberDTO memberDTO = memberService.getMember(id);
	public MemberDTO getMember(String id);
	
	public void updateMember(MemberDTO memberDTO);

	public void deleteMember(MemberDTO memberDTO);

	public List<MemberDTO> getMemberlist();
		
	
}
