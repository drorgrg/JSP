package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.MemberDAO;
import com.itwillbs.domain.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService{
	
	// 멤버변수(부모인터페이스변수) 객체생성 자동화 됨 => @Repository MemberDAOImpl
	@Inject
	private MemberDAO memberDAO;
	
	
	
	@Override
	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberServiceImpl insertMember()");
		// 메서드 호출
		memberDAO.insertMember(memberDTO);
	}



	@Override
	public MemberDTO userCheck(MemberDTO memberDTO) {
		System.out.println("MemberServiceImpl userCheck()");
		return memberDAO.userCheck(memberDTO);
	}



	@Override
	public MemberDTO getMember(String id) {
		System.out.println("MemberServiceImpl getMember()");
		return memberDAO.getMember(id);
	}



	@Override
	public void updateMember(MemberDTO memberDTO) {
		System.out.println("MemberServiceImpl updateMember()");
		memberDAO.updateMember(memberDTO);
	}



	@Override
	public void deleteMember(MemberDTO memberDTO) {
		System.out.println("MemberServiceImpl updateMember()");
		memberDAO.deleteMember(memberDTO);
	}



	@Override
	public List<MemberDTO> getMemberlist() {
		System.out.println("MemberServiceImpl getMemberlist()");
		return memberDAO.getMemberList();
	}

}
