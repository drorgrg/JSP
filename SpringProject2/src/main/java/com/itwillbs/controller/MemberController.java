package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.service.MemberService;
import com.itwillbs.service.MemberServiceImpl;

@Controller
public class MemberController {
	
	// 멤버변수 (부모인터페이스 변수) 객체생성 자동화 됨 => @Service 
//	MemberService memberService = new MemberServiceImpl();
	@Inject
	private MemberService memberService;
	
	
//	 가상주소 시작점: http://localhost:8080/myweb/member/insert
//		=> /memeber/insert 	=> member/insertForm.jsp 이동 
	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String insert() {
		// 주소변경없이 이동
		// Web-INF/view/member/insertForm.jsp 이동
		return "member/insertForm";
	}
	
	@RequestMapping(value = "/member/insertPro", method = RequestMethod.POST)
	public String insertPro(MemberDTO memberDTO) {
		System.out.println("MemberController insertPro()");
		//회원가입
		
		// 객체생성
//		MemberService memberService = new MemberServiceImpl();
		// 메서드 호출
		memberService.insertMember(memberDTO);
		
		// 주소변경없이 이동
		// Web-INF/view/member/insertPro.jsp 이동
		return "redirect:/member/login";
	}
	
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login() {
		System.out.println("MemberController login()");
		return "member/loginForm";
	}
	
	@RequestMapping(value = "/member/loginPro", method = RequestMethod.POST)
	public String loginPro(MemberDTO memberDTO, HttpSession session) {
		System.out.println("MemberController loginPro()");
		// 메서드 호출
		MemberDTO memberDTO2 = memberService.userCheck(memberDTO);
		if(memberDTO2!=null) {
			// 아이디 비밀번호 일치
			// 세션값 생성 "id",id
			session.setAttribute("id", memberDTO2.getId());
			// 주소변경없이 이동
			// Web-INF/view/member/insertPro.jsp 이동
			return "redirect:/member/main";
		} else {
			// 아이디 비밀번호가 틀림
			// "틀림" 뒤로이동
			// 주소변경없이 이동
			// Web-INF/view/member/msg.jsp 이동
			return "member/msg";
		}
		
	}
	
	@RequestMapping(value = "/member/main", method = RequestMethod.GET)
	public String main() {
		System.out.println("MemberController main()");
		// 주소변경없이 이동
		// Web-INF/view/member/main.jsp 이동
		return "member/main";
	}
	
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("MemberController logout()");
		// 세션값 초기화
		session.invalidate();
		// /member/main.jsp 이동
		// 주소변경이동
		return "redirect:/member/main";
	}
	
	@RequestMapping(value = "/member/info", method = RequestMethod.GET)
	public String info(HttpSession session,Model model) {
		// 세션값 가져오기
		String id=(String) session.getAttribute("id");
		// id에 대한 정보를 디비에서 가져와서
		MemberDTO memberDTO = memberService.getMember(id);
		// 가져온 정보를 담아서 info.jsp 이동
		model.addAttribute("memberDTO", memberDTO);
		System.out.println("MemberController info()");
		// 세션값 초기화
		// 주소 변경 없이이동
		// /member/info.jsp 이동
		return "member/info";
	}
	
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String update(HttpSession session,Model model) {
		// 세션값 가져오기
		String id=(String) session.getAttribute("id");
		// id에 대한 정보를 디비에서 가져와서
		MemberDTO memberDTO = memberService.getMember(id);
		// 가져온 정보를 담아서 info.jsp 이동
		model.addAttribute("memberDTO", memberDTO);
		System.out.println("MemberController update()");
		// 세션값 초기화
		// 주소 변경 없이이동
		// /member/updateForm.jsp 이동
		return "member/updateForm";
	}
	
	@RequestMapping(value = "/member/updatePro", method = RequestMethod.POST)
	public String updatePro(MemberDTO memberDTO, HttpSession session) {
		System.out.println("MemberController updatePro()");
		// 메서드 호출
		MemberDTO memberDTO2 = memberService.userCheck(memberDTO);
		if(memberDTO2!=null) {
			// 아이디 비밀번호 일치
			// 수정 작업
			memberService.updateMember(memberDTO );
			// 주소변경없이 이동
			return "redirect:/member/main";
		} else {
			// 아이디 비밀번호가 틀림
			// "틀림" 뒤로이동
			// 주소변경없이 이동
			// Web-INF/view/member/msg.jsp 이동
			return "member/msg";
		}
		
	}
	
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String delete() {
		// 주소 변경 없이이동
		// /member/delete.jsp 이동
		return "member/deleteForm";
	}
	
	@RequestMapping(value = "/member/deletePro", method = RequestMethod.POST)
	public String deletePro(MemberDTO memberDTO, HttpSession session) {
		System.out.println("MemberController deletePro()");
		// 메서드 호출
		MemberDTO memberDTO2 = memberService.userCheck(memberDTO);
		if(memberDTO2!=null) {
			// 아이디 비밀번호 일치
			// 삭제 작업
			memberService.deleteMember(memberDTO);
			// 세션값 초기화
			session.invalidate();
			// member/main 이동
			// 주소변경없이 이동
			return "redirect:/member/main";
		} else {
			// 아이디 비밀번호가 틀림
			// "틀림" 뒤로이동
			// 주소변경없이 이동
			// Web-INF/view/member/msg.jsp 이동
			return "member/msg";
		}
		
	}
	
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String list(HttpSession session,Model model) {
		// id에 대한 정보를 디비에서 가져와서
		List<MemberDTO> memberList = memberService.getMemberlist();
		// 가져온 정보를 담아서 list.jsp 이동
		model.addAttribute("memberList", memberList);
		System.out.println("MemberController memberList()");
		// 세션값 초기화
		// 주소 변경 없이이동
		// /member/list.jsp 이동
		return "member/list";
	}
	
	@RequestMapping(value = "/member/list2", method = RequestMethod.GET)
	public String list2() {
		// 주소 변경 없이이동
		// /member/list.jsp 이동
		return "member/list2";
	}
	
	
	
}
