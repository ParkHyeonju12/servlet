package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class DeleteMemberServlet
 */
@WebServlet(name = "DeleteMember", urlPatterns = { "/deleteMember.do" })
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("utf-8");
		//값추출
		HttpSession session = request.getSession(false);
		Member member = (Member)session.getAttribute("m");
		//비즈니스로직
		MemberService service = new MemberService();
		int result = service.deleteMember(member);
		//결과처리
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "탈퇴 성공");
			request.setAttribute("msg", "^^!");
			request.setAttribute("icon", "success");
			if(session != null) {
				session.invalidate(); //현재 세션을 파기하는 메소드
			}
			request.setAttribute("loc", "/");
		}else {
			request.setAttribute("title", "탈퇴 실패");
			request.setAttribute("msg", "ㅠ!");
			request.setAttribute("icon", "error");
			
			request.setAttribute("loc", "/");
		}
	     view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
