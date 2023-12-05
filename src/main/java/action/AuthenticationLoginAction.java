package action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Member;
import beans.Reserve;
import dao.ConnectionManager;
import dao.MemberDAO;
import dao.ReserveDAO;
import orgex.NSCOException;

public class AuthenticationLoginAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws NSCOException {
		String nextPage = "error.jsp";
		Connection con = null;
		Member member = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            // DAOクラスをインスタンス化
        	MemberDAO memberDao = new MemberDAO(con);
        	ReserveDAO reserveDao = new ReserveDAO(con);
	
        	String memberNo = request.getParameter("memberNo");
        	String password = request.getParameter("password");
        	member = memberDao.getMemberById(memberNo, password);

        	HttpSession session = request.getSession(); 
			session.setAttribute("member", member);
			
			if(member != null) {
				List<Reserve> reserveList = reserveDao.getBeforeTakeLessonReserves(member.getMemberNo());
				session.setAttribute("reserveList", reserveList);
				nextPage = (String) request.getSession().getAttribute("page");
			}else {
				session.setAttribute("authenticationMessage", "会員番号またはパスワードが間違っています");
				nextPage = "login.jsp";
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
