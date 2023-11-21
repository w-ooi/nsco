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
import action.ConfirmCancelReserveAction;
import action.ConfirmFillOutQuestionAction;
import action.ConfirmRegistrationMemberAction;
import action.ConfirmUpdatePasswordAction;
import action.FillOutQuestionAction;
import action.IAction;
import action.LoginAction;
import action.LogoutAction;
import action.MyPageAction;
import action.RegistrationMemberAction;
import action.ReserveScheduleAction;
import action.ScheduleSearchByInstructorAction;
import action.ScheduleSearchByLessonCategoryAction;
import action.ScheduleSearchByTimeFrameAction;
import action.UpdatePasswordAction;
import action.ViewRegistrationPageAction;
import action.ViewTopPageAction;
import orgex.NSCOException;

@WebServlet("/fc")
public class FrontController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		IAction action = new ViewTopPageAction();
        String nextPage = null;
        
        try {
			nextPage = action.execute(request, response);
		} catch (NSCOException e) {
			request.setAttribute("errorMessage", e.getMessage());
			nextPage = "error.jsp";
		}

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
		case "myPage":
			action = new MyPageAction();
			break;
		case "cancelReserve":
			action = new CancelReserveAction();
			break;
		case "confirmCancelReserve":
			action = new ConfirmCancelReserveAction();
			break;
		case "fillOutQuestion":
			action = new FillOutQuestionAction();
			break;
		case "confirmFillOutQuestion":
			action = new ConfirmFillOutQuestionAction();
			break;
		case "registrationPage":
			action = new ViewRegistrationPageAction();
			break;
		case "topPage":
			action = new ViewTopPageAction();
			break;
		case "registrationMember":
			action = new RegistrationMemberAction();
			break;
		case "confirmRegistrationMember":
			action = new ConfirmRegistrationMemberAction();
			break;
		case "updatePassword":
			action = new UpdatePasswordAction();
			break;
		case "confirmUpdatePassword":
			action = new ConfirmUpdatePasswordAction();
			break;
		}

        String nextPage = null;
        
        try {
			nextPage = action.execute(request, response);
		} catch (NSCOException e) {
			request.setAttribute("errorMessage", e.getMessage());
			nextPage = "error.jsp";
		}

        // フォワード設定
        RequestDispatcher rd = request.getRequestDispatcher(nextPage);

        // フォワード
        rd.forward(request, response);
	}
}
