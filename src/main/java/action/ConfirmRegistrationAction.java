package action;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Member;
import dao.ConnectionManager;
import dao.MemberDAO;

public class ConfirmRegistrationAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "error.jsp";
		Connection con = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

        	HttpSession session = request.getSession(); 

        	// DAOクラスをインスタンス化
        	MemberDAO memberDao = new MemberDAO(con);
        	Member member = (Member)session.getAttribute("registrationMember");
    		int result = memberDao.insertMember(member);
			
    		if(result == 1) {
				session.setAttribute("registrationMessage", "会員情報を登録しました");
    		}else {
    			session.setAttribute("registrationMessage", "会員情報を登録できませんでした");
    		}
				
			nextPage = "logion.jsp";
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
