package action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Member;
import beans.Reserve;
import dao.ConnectionManager;
import dao.ReserveDAO;
import orgex.NSCOException;

public class MyPageAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws NSCOException {
		String nextPage = "error.jsp";
		Connection con = null;
		
		try {
			//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            // DAOクラスをインスタンス化
			ReserveDAO reserveDao = new ReserveDAO(con);
			
			// 会員情報取得
			Member member = (Member)request.getSession().getAttribute("member");
			String memberNo = member.getMemberNo();
			
			// 受講前リスト
			List<Reserve> beforeTakeLesson = reserveDao.getBeforeTakeLessonReserves(memberNo);
			
			// 受講後リスト
			List<Reserve> afterTakeLesson = reserveDao.getAfterTakeLessonReserves(memberNo);
			
			request.getSession().setAttribute("beforeTakeLesson", beforeTakeLesson);
			request.getSession().setAttribute("afterTakeLesson", afterTakeLesson);
			
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
