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

public class ConfirmFillOutQuestionAction implements IAction {

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
    		int lessonEvaluation = Integer.parseInt(request.getParameter("lessonEvaluation"));
    		int instructorEvaluation = Integer.parseInt(request.getParameter("instructorEvaluation"));
    		
    		int result = reserveDao.updateEvaluation(reserveCode, lessonEvaluation, instructorEvaluation);
			
        	HttpSession session = request.getSession(); 
    		if(result == 1) {
    			Member member = (Member)session.getAttribute("member");
    			List<Reserve> reserveList = reserveDao.getAfterTakeLessonReserves(member.getMemberNo());
    			session.setAttribute("afterTakeLesson", reserveList);
				session.setAttribute("fillOutMessage", "アンケートを入力しました");
    		}else {
    			session.setAttribute("fillOutMessage", "アンケートを入力できませんでした");
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
