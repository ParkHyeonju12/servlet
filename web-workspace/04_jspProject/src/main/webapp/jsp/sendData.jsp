<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//인코딩
		request.setCharacterEncoding("utf-8");
		//값 추출
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		//비지니스로직
		//결과처리
	%>
	<h2><%=data1 %></h2>
	<h2><%=data2 %></h2>
</body>
</html>