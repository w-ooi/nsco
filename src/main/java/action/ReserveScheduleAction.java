package action;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Member;
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
				
				if(intResult == 1) {
					request.setAttribute("reserveMessage", "予約に成功しました");
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
