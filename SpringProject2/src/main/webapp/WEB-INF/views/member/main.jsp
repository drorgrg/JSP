<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/main.jsp</title>
</head>
<body>
<%
// String id=(String)session.getAttribute("id");
// 세션값이 없으면(세션값이 null이면) loginForm.jsp 이동
// if(id==null){
// 	response.sendRedirect("loginForm.jsp");
// }

// if문 for문 사용
// tag 함수 JSTL(JSP Standard Tag Library)
// 1. jstl 프로그램 설치
// 2. jstl 프로그램 가져오기  
// taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
%>
<c:if test="${empty sessionScope.id }"> <!-- if(id==null) -->
	<c:redirect url="/member/login">
	</c:redirect>
</c:if>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.js"></script>
<img alt="" src="${pageContext.request.contextPath }/resources/images/pepe.png" width=300;><br>
${sessionScope.id }님 로그인 하셨습니다. 
<a href="${pageContext.request.contextPath }/member/logout">로그아웃</a><br>
<a href="${pageContext.request.contextPath }/member/info">회원정보조회</a>
<a href="${pageContext.request.contextPath }/member/update">회원정보수정</a>
<a href="${pageContext.request.contextPath }/member/delete">회원정보삭제</a>
<%-- <a href="${pageContext.request.contextPath }/member/list">회원목록</a> --%>
<%
//  if(id!=null){
// 	if(id.equals("admin")){
		%>
		<!-- <a href="${pageContext.request.contextPath }/member/list">회원목록</a> -->
		<%
// 	}
	
// }
%>
<c:if test="${!(empty sessionScope.id) }">
	<c:if test="${sessionScope.id eq 'admin'}">
	<a href="${pageContext.request.contextPath }/member/list">회원목록</a>
	</c:if>
</c:if>

</body>
</html>


