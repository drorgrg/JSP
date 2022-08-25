<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp5/writePro.jsp</title>
</head>
<body>
<%
//post request 한글처리
request.setCharacterEncoding("utf-8");
// request name pass subject content 파라미터 값 가져오기 => 변수 저장
String name=request.getParameter("name");
String pass=request.getParameter("pass");
String subject=request.getParameter("subject");
String content=request.getParameter("content");
// 조회수
int readcount=0;
// 1단계 JDBC 프로그램 불러오기
Class.forName("com.mysql.cj.jdbc.Driver");
// 2단계 JDBC 프로그램 이용해서 디비서버에 접속 => 연결정보 저장
// 디비서버에 접근할 디비주소
String dbUrl="jdbc:mysql://localhost:3306/jspdb1?serverTimezone=UTC";
// 디비에 접근할 아이디
String dbId="root";
// 디비에 접근할 비밀번호
String dbPass="1234";
// 디비연결 정보를 Connection형 con변수에 저장
Connection con =DriverManager.getConnection(dbUrl, dbId, dbPass);

// num구하기 1씩 증가
int num=0;
String sql="select max(num) from board";
PreparedStatement pstmt=con.prepareStatement(sql);
ResultSet rs=pstmt.executeQuery();
if(rs.next()){
	num=rs.getInt("max(num)")+1;
}

// 3단계 연결정보를 이용해서 sql구문을 만들기 => PreparedStatement 내장객체 준비
// 날짜 now()
sql="insert into board(num,name,pass,subject,content,readcount,date) values(?,?,?,?,?,?,now())";
pstmt=con.prepareStatement(sql);
pstmt.setInt(1, num);
pstmt.setString(2, name);
pstmt.setString(3, pass);
pstmt.setString(4, subject);
pstmt.setString(5, content);
pstmt.setInt(6, readcount);

// 4단계 sql구문을 실행 (insert, update, delete)
pstmt.executeUpdate();

//글목록 이동
response.sendRedirect("list.jsp");
%>
글등록 성공 : <%=pstmt %>
</body>
</html>