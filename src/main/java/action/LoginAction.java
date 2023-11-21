package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("page", request.getParameter("page"));
		return "login.jsp";
	}
}
