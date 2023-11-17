package action;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ConnectionManager;
import dao.ReserveDAO;

public class ConfirmFillOutAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "error.jsp";
		Connection con = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            // DAOクラスをインスタンス化
        	ReserveDAO reserveDao = new ReserveDAO(con);
	
    		int reserveCode = Integer.parseInt(request.getParameter("reserveCode"));
    		int lessonEvaluation = Integer.parseInt(request.getParameter("lessonEvaluation"));
    		int instructorEvaluation = Integer.parseInt(request.getParameter("instructorEvaluation"));
    		
    		int result = reserveDao.updateEvaluation(reserveCode, lessonEvaluation, instructorEvaluation);
			
        	HttpSession session = request.getSession(); 
    		if(result == 1) {
				session.setAttribute("fillOutMessage", "アンケートを入力しました");
    		}else {
    			session.setAttribute("fillOutMessage", "アンケートを入力できませんでした");
    		}
				
			nextPage = "mypage.jsp";
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
