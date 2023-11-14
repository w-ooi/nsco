package action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Schedule;
import dao.ConnectionManager;
import dao.ScheduleDAO;

public class ScheduleSearchByInstructorAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "erro.jsp";
		Connection con = null;
		List<Schedule> scheduleList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            // DAOクラスをインスタンス化
        	ScheduleDAO scheduleDao = new ScheduleDAO(con);
	
			//検索項目用
        	String code = request.getParameter("code");
        	scheduleList = scheduleDao.getScheduleByInstructor(code);
			
        	HttpSession session = request.getSession(); 
			session.setAttribute("scheduleList", scheduleList);
			
			nextPage = "searchResult.jsp";
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