package kr.or.iei.notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.iei.notice.model.service.NoticeService;
import kr.or.iei.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet(name = "NoticeUpdate", urlPatterns = { "/noticeUpdate.do" })
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
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
		//2-1. 파일 업로드 경로 설정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/notice";
		//2-2. 파일 최대크기 지정
		int maxSize = 10*1024*1024;
		//2-3. request -> MultipartRequest 변환(파일 업로드 시점)
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		int noticeNo = Integer.parseInt(mRequest.getParameter("noticeNo"));
		String noticeTitle = mRequest.getParameter("noticeTitle");
		String noticeContent = mRequest.getParameter("noticeContent");
		//기존파일이 지워졌으면 delete, 그외 모두 stay
		String status = mRequest.getParameter("status");
		String filename = mRequest.getOriginalFileName("upfile");
		String filepath = mRequest.getFilesystemName("upfile");
		//기존 첨부파일이 있었으면 기존첨부파일값, 없었으면 null
		String oldFilename = mRequest.getParameter("oldFilename");
		String oldFilepath = mRequest.getParameter("oldFilepath");

		/*
		 기존 첨부파일이 있는 경우 
		 	-> 새 첨부파일이 없는 경우  
		 			-> 기존 첨부 파일을 삭제 case4
		 			-> 기존 첨부 파일을 유지 case3
		 			
		 	-> 새 첨부파일이 있는 경우 ->기존파일 삭제 case5 
		 기존 첨부파일이 없는 경우
		 	-> 새 첨부파일이 없는 경우 case1
		 	-> 새 첨부파일이 있는 경우 case2
		 	
		 case4,case5 -> 기존파일을 삭제해야하는 경우
		 				-> status == "delete"
		 case1,case2 -> filename,filepath를 db업데이트
		  */ 
		
		if(oldFilename != null && status.equals("stay")) {
			filename = oldFilename;
			filepath = oldFilepath;
		}
		Notice n = new Notice();
		n.setNoticeNo(noticeNo);
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		n.setFilename(filename);
		n.setFilepath(filepath);
		//비즈니스로직
		NoticeService service = new NoticeService();
		int result = service.updateNotice(n);
		//결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "변경완료");
			request.setAttribute("msg", "공지사항이 수정되었습니다.");
			request.setAttribute("icon", "success");
			if(status.equals("delete")) {
				File delFile = new File(saveDirectory+"/"+oldFilepath);
				delFile.delete();
			}
		}else {
			request.setAttribute("title", "변경실패");
			request.setAttribute("msg", "관리자에게 문의하세요");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/noticeView.do?noticeNo="+noticeNo);
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
