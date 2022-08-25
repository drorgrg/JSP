<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp5/list.jsp</title>
</head>
<body>
<%
// model.addAttribute("boardlist", boardList);
// model.addAttribute("boardList",boardList); 
// model.addAttribute("pageDTO", pageDTO); 


%>
<table border="1">
<tr><td>번호</td><td>작성자</td><td>제목</td><td>등록일</td><td>조회수</td></tr>
<c:forEach var="BoardDTO" items="${boardlist }">
<tr><td>${BoardDTO.num }</td>
    <td>${BoardDTO.name } </td>
    <td><a href="${pageContext.request.contextPath }/board/content?num=${BoardDTO.num }">
    ${BoardDTO.subject }</a></td>
    <td>${BoardDTO.date }</td>
    <td>${BoardDTO.readcount }</td></tr>	
</c:forEach>
</table>
<c:if test="${pageDTO.startPage >  pageDTO.pageBlock }">
<a href="${pageContext.request.contextPath }/board/list?pageNum=${pageDTO.startPage - pageDTO.pageBlock }" step="1">Prev</a>
</c:if>


<c:forEach var="i" begin="${pageDTO.startPage }" end="${pageDTO.endPage }" step="1">
<a href="${pageContext.request.contextPath }/board/list?pageNum=${i}">${i}</a>
</c:forEach>

<c:if test="${pageDTO.endPage <  pageDTO.pageCount }">
<a href="${pageContext.request.contextPath }/board/list?pageNum=${pageDTO.startPage + pageDTO.pageBlock }" step="1">Next</a>
</c:if>
</body>
</html>
