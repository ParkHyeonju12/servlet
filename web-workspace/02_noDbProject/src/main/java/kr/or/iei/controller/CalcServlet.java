package kr.or.iei.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcServlet
 */
@WebServlet(name = "Calc", urlPatterns = { "/calc.do" })
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcServlet() {
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
		int num1 = Integer.parseInt(request.getParameter("su1"));
		int num2 = Integer.parseInt(request.getParameter("su2"));
		String oper = request.getParameter("oper");
		int result=0;
		switch (oper) {
		case "+":
			result = num1+num2;
			break;
		case "-":
			result = num1-num2;
		break;
		case "*":
			result = num1*num2;
		break;
		case "/":
			result = num1/num2;
		break;
		case "%":
			result = num1%num2;
			break;
		}
		//사용자 화면 처리
		response.setContentType("text/html;charset=utf-8");
		//html 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html><head><title>계산 결과</title></head>");
		out.println("<body>");
		out.println("<h1>계산 결과</h1>");
		out.println("<hr>");
		out.println("<h2>"+num1+oper+num2+"="+result+"</h2>");
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
