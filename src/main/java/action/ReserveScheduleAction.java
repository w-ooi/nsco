package action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Member;
import beans.Reserve;
import beans.Schedule;
import dao.ConnectionManager;
import dao.ReserveDAO;
import dao.ScheduleDAO;
import orgex.NSCOException;

public class ReserveScheduleAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws NSCOException {
		String nextPage = "error.jsp";
		Connection con = null;
		
		Member member = (Member) request.getSession().getAttribute("member");

		if(member == null) {
			request.getSession().setAttribute("page", "searchResult.jsp");
			nextPage = "login.jsp";
		}else {
			try {
				//データベース接続情報を取得
	        	con = ConnectionManager.getConnection();
	
	            // DAOクラスをインスタンス化
	        	ScheduleDAO scheduleDao = new ScheduleDAO(con);
				ReserveDAO reserveDao = new ReserveDAO(con);
				
				HttpSession session = request.getSession();
				
				// スケジュール番号取得
				int scheduleCode = Integer.parseInt(request.getParameter("scheduleCode"));
				
				// 時間チェック
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				
				// 開催日をCalendar型へ変換
				Schedule schedule = scheduleDao.getSchedule(scheduleCode);

				// 文字列をDate型へ
				Date eventDate = null;
				try {
					eventDate = sf.parse(schedule.getEventDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				// Dateをカレンダーへ
				Calendar calendarDate = Calendar.getInstance();
				calendarDate.setTime(eventDate);

				// 現在日時を取得
				Calendar clNow = Calendar.getInstance();
				clNow.set(Calendar.HOUR, 0);	// 時を0
				clNow.set(Calendar.MINUTE, 0);	// 分を0
				clNow.set(Calendar.SECOND, 0);	// 秒を0
				clNow.set(Calendar.MILLISECOND, 0);	// ミリ秒を0
				
				// 現在時間を取得
				int clNowHour = clNow.get(Calendar.HOUR_OF_DAY);
				
				if(clNowHour < 18) {
					clNow.set(Calendar.DATE, clNow.get(Calendar.DATE)+1);	// 翌日以降予約可
				}else {
					clNow.set(Calendar.DATE, clNow.get(Calendar.DATE)+2);	// 翌々日以降予約可
				}
				
				//日時の比較
				//戻り値が0なら一致、正なら現在日時が指定日時を過ぎている、負なら現在日時は指定日時より前
				int diff = clNow.compareTo(calendarDate);

			    if (diff <= 0){
					int intResult = reserveDao.setReserve(member.getMemberNo(), scheduleCode);
					
					if(intResult == 1) {
						session.setAttribute("reserveMessage", "予約に成功しました");
						List<Reserve> reserveList = reserveDao.getBeforeTakeLessonReserves(member.getMemberNo());
						session.setAttribute("reserveList", reserveList);
					}else {
						session.setAttribute("reserveMessage", "予約に失敗しました");
					}
			    }else {
			    	// 検索結果をクリア
			    	List<Schedule> scheduleList = new ArrayList<Schedule>();
			    	session.setAttribute("scheduleList", scheduleList);
			    	session.setAttribute("reserveMessage", "予約可能時間を過ぎました<br>もう一度検索してください");
			    }
				
				nextPage = "searchResult.jsp";
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
		}
		
		return nextPage;
	}

}
