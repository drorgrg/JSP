<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/updatePro.jsp</title>
</head>
<body>
<%
//post request 한글처리
request.setCharacterEncoding("utf-8");
// request id pass name 파라미터 값 가져오기 => 변수 저장
String id=request.getParameter("id");
String pass=request.getParameter("pass");
String name=request.getParameter("name");

//MemberDAO 객체생성
MemberDAO memberDAO =new MemberDAO();
//  MemberDTO memberDTO =userCheck 메서드 호출
MemberDTO memberDTO=memberDAO.userCheck(id, pass);

if(memberDTO!=null){
	// if  memberDTO !=null => 아이디 비밀번호 일치
//		MemberDTO	updateDTO  객체생성
	MemberDTO updateDTO=new MemberDTO();
//    set메서드 호출 id pass name 저장 
	updateDTO.setId(id);
	updateDTO.setPass(pass);
	updateDTO.setName(name);
//    updateMember(MemberDTO updateDTO) 메서드 정의 
//    updateMember(updateDTO) 메서드 호출
	memberDAO.updateMember(updateDTO);

	// main.jsp 이동
	response.sendRedirect("main.jsp");
}else{
	// else  memberDTO ==null => 아이디 비밀번호 틀림
//  // 아이디 비밀번호 틀림메시지 출력 , 뒤로이동
	%>
	<script type="text/javascript">
		alert("아이디 비밀번호 틀림");
		history.back();
	</script>
	<%
}
%>
</body>
</html>