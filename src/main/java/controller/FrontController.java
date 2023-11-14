package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.IAction;
import action.ReserveScheduleAction;
import action.ScheduleSearchByInstructorAction;
import action.ScheduleSearchByLessonCategoryAction;
import action.ScheduleSearchByTimeFrameAction;
import action.ViewTopPageAction;

@WebServlet("/fc")
public class FrontController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		IAction action = new ViewTopPageAction();
        String nextPage = action.execute(request, response);

        // フォワード設定
        RequestDispatcher rd = request.getRequestDispatcher(nextPage);

        // フォワード
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		IAction action = null;
		String visit = request.getParameter("visit");
		
		switch(visit) {
		case "lessonCategorySearch":
			action = new ScheduleSearchByLessonCategoryAction();
			break;
		case "timeFrameSearch":
			action = new ScheduleSearchByTimeFrameAction();
			break;
		case "instructorSearch":
			action = new ScheduleSearchByInstructorAction();
			break;
		case "reserveSchedule":
			action = new ReserveScheduleAction();
			break;
		}

        String nextPage = action.execute(request, response);

        // フォワード設定
        RequestDispatcher rd = request.getRequestDispatcher(nextPage);

        // フォワード
        rd.forward(request, response);
	}
}
