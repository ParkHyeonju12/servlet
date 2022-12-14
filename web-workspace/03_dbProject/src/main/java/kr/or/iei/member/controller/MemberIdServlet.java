package kr.or.iei.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class MemberIdServlet
 */
@WebServlet(name = "MemberId", urlPatterns = { "/memberId.do" })
public class MemberIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberIdServlet() {
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
		String memberId = request.getParameter("member_id");
		//비지니스 로직
		MemberService service = new MemberService();
		Member m = service.serchIDMember(memberId);
		//결과처리
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html><head><title>아이디조회</title>");
		out.println("<body>");
		if(m==null) {
			out.println("<h1>조회결과 없음</h1>");
		}else {
			out.println("<ul>");
			out.println("<li>"+m.getMemberId()+"</li>");
			out.println("<li>"+m.getMemberName()+"</li>");
			out.println("<li>"+m.getMemberPhone()+"</li>");
			out.println("<li>"+m.getMemberAddr()+"</li>");
			out.println("</ul>");
		}
		
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
