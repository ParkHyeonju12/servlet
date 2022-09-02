package kr.or.iei.member.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import kr.or.iei.member.model.dao.MemberDao;
import kr.or.iei.member.model.vo.Member;

public class MemberService {
	private MemberDao dao;
	
	
	
	public MemberService() {
		super();
		dao = new MemberDao();
		// TODO Auto-generated constructor stub
	}



	public Member selectOneMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = dao.selectOneMember(conn,member);
		JDBCTemplate.close(conn);
		return m;
	}



	public Member selectOneMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = dao.selectOneMember(conn,memberId);
		JDBCTemplate.close(conn);
		return m;
	}



	public int joinMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.joinMember(conn,member);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}



	public int updateMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateMember(conn,member);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}



	public int deleteMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.deleteMember(conn, member);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}



	public ArrayList<Member> selectAllMember() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = dao.selectAllMember(conn);
		JDBCTemplate.close(conn);
		return list;
	}



	public int changeLevel(int memberNo, int memberLevel) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.changeLevel(conn,memberNo,memberLevel);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}



	public boolean checkedChangeLevel(String num, String level) {
		Connection conn = JDBCTemplate.getConnection();
		//num : 4/7/21
		//level : 1/2/3
		
		StringTokenizer sT1 = new StringTokenizer(num,"/");
		StringTokenizer sT2 = new StringTokenizer(level,"/");
		boolean result = true;
		//hasMoreElements(): 꺼낼 데이터 남아있어? true, 없어 false
		while(sT1.hasMoreElements()) {
			int memberNo = Integer.parseInt(sT1.nextToken());
			int memberLevel = Integer.parseInt(sT2.nextToken());
			int changeResult = dao.changeLevel(conn, memberNo, memberLevel);
			if(changeResult == 0) {
				result =false;
				break;
			}
		}
		if(result) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}



}
