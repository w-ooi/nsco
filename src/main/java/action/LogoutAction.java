package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("member");
		return "index.jsp";
	}

}
