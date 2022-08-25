<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp5/deleteForm.jsp</title>
</head>
<body>
<%
//request에서   num파라미터 가져오기
int num=Integer.parseInt(request.getParameter("num"));
%>
<form action="deletePro.jsp" method="post">
<input type="hidden" name="num" value="<%=num %>">
<table border="1">
<tr><td>비밀번호</td><td><input type="password" name="pass"></td></tr>
<tr><td colspan="2"><input type="submit" value="글삭제"></td></tr>
</table>
</form>
</body>
</html>

