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
import dao.ReserveDAO;
import orgex.NSCOException;

public class ConfirmCancelReserveAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws NSCOException {
		String nextPage = "error.jsp";
		Connection con = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            // DAOクラスをインスタンス化
        	ReserveDAO reserveDao = new ReserveDAO(con);
	
    		int reserveCode = Integer.parseInt(request.getParameter("reserveCode"));
    		int result = reserveDao.cancelReserve(reserveCode);
			
        	HttpSession session = request.getSession(); 
    		if(result == 1) {
    			Member member = (Member)session.getAttribute("member");
    			List<Reserve> reserveList = reserveDao.getBeforeTakeLessonReserves(member.getMemberNo());
    			session.setAttribute("reserveList", reserveList);
				session.setAttribute("cancelMessage", "レッスンをキャンセルしました");
    		}else {
    			session.setAttribute("cancelMessage", "レッスンをキャンセルできませんでした");
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
