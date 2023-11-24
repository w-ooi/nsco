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

public class ReserveScheduleAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws NSCOException {
		String nextPage = "error.jsp";
		Connection con = null;
		
		Member member = (Member) request.getSession().getAttribute("member");

		if(member == null) {
			request.getSession().setAttribute("page", "searchResult.jsp");
			nextPage = "login.jsp";
		}else {
			try {
				//データベース接続情報を取得
	        	con = ConnectionManager.getConnection();
	
	            // DAOクラスをインスタンス化
				ReserveDAO reserveDao = new ReserveDAO(con);
				
				// スケジュール番号取得
				int scheduleCode = Integer.parseInt(request.getParameter("scheduleCode"));
				
				int intResult = reserveDao.setReserve(member.getMemberNo(), scheduleCode);
				
				HttpSession session = request.getSession();
				if(intResult == 1) {
					session.setAttribute("reserveMessage", "予約に成功しました");
					List<Reserve> reserveList = reserveDao.getBeforeTakeLessonReserves(member.getMemberNo());
					session.setAttribute("reserveList", reserveList);
				}else {
					request.setAttribute("reserveMessage", "予約に失敗しました");
				}
				nextPage = "searchResult.jsp";
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
		}
		
		return nextPage;
	}

}
