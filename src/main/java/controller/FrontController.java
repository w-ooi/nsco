package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.AuthenticationLoginAction;
import action.CancelReserveAction;
import action.ConfirmCancelAction;
import action.ConfirmFillOutAction;
import action.FillOutQuestionAction;
import action.IAction;
import action.LoginAction;
import action.LogoutAction;
import action.MyPageAction;
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
		case "login":
			action = new LoginAction();
			break;
		case "logout":
			action = new LogoutAction();
			break;
		case "authenticationLogin":
			action = new AuthenticationLoginAction();
			break;
		case "mypage":
			action = new MyPageAction();
			break;
		case "cancelReserve":
			action = new CancelReserveAction();
			break;
		case "confirmCancel":
			action = new ConfirmCancelAction();
			break;
		case "fillOutQuestion":
			action = new FillOutQuestionAction();
			break;
		case "confirmFillOut":
			action = new ConfirmFillOutAction();
			break;
		}

        String nextPage = action.execute(request, response);

        // フォワード設定
        RequestDispatcher rd = request.getRequestDispatcher(nextPage);

        // フォワード
        rd.forward(request, response);
	}
}
