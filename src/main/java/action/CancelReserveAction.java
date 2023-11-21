package action;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Reserve;
import dao.ConnectionManager;
import dao.ReserveDAO;
import orgex.NSCOException;

public class CancelReserveAction implements IAction {

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
    		Reserve reserve = reserveDao.getReserve(reserveCode);
			
        	HttpSession session = request.getSession(); 
			session.setAttribute("canselReserve", reserve);
			
			nextPage = "confirmCancelReserve.jsp";
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
