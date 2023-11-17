package action;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Member;
import dao.ConnectionManager;
import dao.MemberDAO;

public class AuthenticationLoginAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "error.jsp";
		Connection con = null;
		Member member = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            // DAOクラスをインスタンス化
        	MemberDAO memberDao = new MemberDAO(con);
	
			//検索項目用
        	String memberNo = request.getParameter("memberNo");
        	String password = request.getParameter("password");
        	member = memberDao.getMemberById(memberNo, password);

        	HttpSession session = request.getSession(); 
			session.setAttribute("member", member);
			
			nextPage = (String) request.getSession().getAttribute("page");
		}catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	if(con != null) {
        		try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
		
		return nextPage;
	}
}
