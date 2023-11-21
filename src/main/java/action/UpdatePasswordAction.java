package action;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Member;
import dao.ConnectionManager;
import dao.MemberDAO;
import orgex.NSCOException;

public class UpdatePasswordAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws NSCOException {
		String nextPage = "error.jsp";
		Connection con = null;

		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		String nowPassword = request.getParameter("nowPassword");
		String newPassword = request.getParameter("newPassword");
		
		try {
			//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            // DAOクラスをインスタンス化
        	MemberDAO memberDao = new MemberDAO(con);
        	member = memberDao.getMemberById(member.getMemberNo(), nowPassword);
        	
        	if(member == null) {
        		session.setAttribute("updateMessage", "現在のパスワードが誤っています");
        		nextPage = "myPage.jsp";
        	} else {
            	session.setAttribute("nowPassword", nowPassword);
        		session.setAttribute("newPassword", newPassword);
        		nextPage = "confirmUpdatePassword.jsp";
        	}
		}catch (SQLException e) {
			throw new NSCOException(e.getMessage());
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
