<%@page import="kr.or.iei.member.model.vo.Member"%>
<%@page import="kr.or.iei.member.model.service.MemberService"%>
<%@page import="org.apache.catalina.tribes.membership.Membership"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //인코딩
    request.setCharacterEncoding("utf-8");
    //값추출
    String memberId = request.getParameter("memberId");
    String memberPw = request.getParameter("memberPw");
    String memberName = request.getParameter("memberName");
    String memberPhone = request.getParameter("memberPhone");
    String memberAddr = request.getParameter("memberAddr");
    Member m = new Member();
    m.setMemberId(memberId);
    m.setMemberPw(memberPw);
    m.setMemberName(memberName);
    m.setMemberPhone(memberPhone);
    m.setMemberAddr(memberAddr);
    //비지니스로직
   
    MemberService service = new MemberService();
    int result = service.insertMember(m);
    
    //결과처리
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입 결과</h1>
	<hr>
	<%if(result>0){ %>
	<h3>회원가입 성공</h3>
	<%} else{ %>
	<h3>회원가입 실패</h3>
	<%} %>
</body>
</html>