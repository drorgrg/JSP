<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp5/updateForm.jsp</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/board/updatePro" method="post">
<input type="hidden" name="num" value="${boardDTO.num }">
<table border="1">
<tr><td>글쓴이</td><td><input type="text" name="name" value="${boardDTO.name }"></td></tr>
<tr><td>비밀번호</td><td><input type="password" name="pass"></td></tr>
<tr><td>제목</td>
    <td><input type="text" name="subject" value="${boardDTO.subject }"></td></tr>
<tr><td>내용</td>
    <td><textarea name="content" rows="10" cols="20">${boardDTO.content }</textarea></td></tr>
<tr><td colspan="2"><input type="submit" value="글수정"></td></tr>
</table>
</form>
</body>
</html>