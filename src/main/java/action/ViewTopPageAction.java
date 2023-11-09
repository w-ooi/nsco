package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.LessonCategory;
import beans.Staff;
import beans.TimeFrame;

public class ViewTopPageAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "index.jsp";

		//検索項目用
		ArrayList<LessonCategory> lessonCategoryList = new ArrayList<LessonCategory>();
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		ArrayList<TimeFrame> timeFrameList = new ArrayList<TimeFrame>();

		return nextPage;
	}

}
