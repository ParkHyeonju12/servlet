<%@page import="kr.or.iei.member.model.vo.Member"%>
<%@page import="kr.or.iei.member.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% //다운캐스팅도 해야댕 ㄷㄷ
 	Member m = (Member)request.getAttribute("m");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%if(m == null) {%>
 <h3>회원을 조회할 수 없습니다</h3>
 <%} else { %>
 <ul>
 	<li><%=m.getMemberId() %></li>
 	<li><%=m.getMemberName() %></li>
 	<li><%=m.getMemberPhone() %></li>
 	<li><%=m.getMemberAddr() %></li>
 </ul>
 <%} %>
</body>
</html>