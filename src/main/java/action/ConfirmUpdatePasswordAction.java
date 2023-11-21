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

public class ConfirmUpdatePasswordAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws NSCOException {
		String nextPage = "error.jsp";
		Connection con = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            // DAOクラスをインスタンス化
        	MemberDAO memberDao = new MemberDAO(con);
	
        	HttpSession session = request.getSession();
        	Member member = (Member)session.getAttribute("member");
        	String newPassword = (String)session.getAttribute("newPassword");
        	
        	int result = memberDao.updatePassword(member.getMemberNo(), newPassword);
			
    		if(result == 1) {
    			member.setPassword(newPassword);
    			session.setAttribute("member", member);
				session.setAttribute("updateMessage", "パスワードを変更しました");
    		}else {
    			session.setAttribute("updateMessage", "パスワードを変更できませんでした");
    		}
				
			nextPage = "myPage.jsp";
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
