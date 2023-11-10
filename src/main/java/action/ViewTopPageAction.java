package action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Instructor;
import beans.LessonCategory;
import beans.TimeFrame;
import dao.ConnectionManager;
import dao.InstructorDAO;
import dao.LessonCategoryDAO;
import dao.TimeFrameDAO;

public class ViewTopPageAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "error.jsp";
		Connection con = null;
		ArrayList<LessonCategory> lessonCategoryList = null;
		ArrayList<Instructor> instructorList = null;
		ArrayList<TimeFrame> timeFrameList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            // DAOクラスをインスタンス化
            LessonCategoryDAO lessonCategoryDao = new LessonCategoryDAO(con);
            InstructorDAO instructorDao = new InstructorDAO(con);
            TimeFrameDAO timeFrameDao = new TimeFrameDAO(con);
	
			//検索項目用
			lessonCategoryList = lessonCategoryDao.getAllLessonCategories();
			instructorList = instructorDao.getAllInstructors();
			timeFrameList = timeFrameDao.getAllTimeFrames();
			
			request.setAttribute("lessonCategoryList", lessonCategoryList);
			request.setAttribute("instructorList", instructorList);
			request.setAttribute("timeFrameList", timeFrameList);
			
			nextPage = "index.jsp";
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
