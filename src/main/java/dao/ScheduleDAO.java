package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import beans.Instructor;
import beans.Lesson;
import beans.Schedule;
import beans.TimeFrame;

public class ScheduleDAO {
	private Connection con;

	public ScheduleDAO(Connection con) {
		this.con = con;
	}

	public Schedule getSchedule(int scheduleCode) throws SQLException {
		Schedule schedule = null;
		PreparedStatement st = null;

		try {
			// PreparedStatementの取得
			st = con.prepareStatement("SELECT * FROM schedule WHERE schedule_code=?");
			st.setInt(1, scheduleCode);

			// SQL文を発行
			ResultSet rs = st.executeQuery();

			LessonDAO lessonDao = new LessonDAO(con);
			TimeFrameDAO timeFrameDao = new TimeFrameDAO(con);
			InstructorDAO instructorDao = new InstructorDAO(con);

			// 結果を参照
			if (rs.next()) {
				int lessonCode = rs.getInt("lesson_code");
				Lesson lesson = lessonDao.getLesson(lessonCode);
				String eventDate = rs.getDate("event_date").toString();
				int timeFrameCode = rs.getInt("time_frame_code");
				TimeFrame timeFrame = timeFrameDao.getTimeFrame(timeFrameCode);
				int instructorCode = rs.getInt("instructor_code");
				Instructor instructor = instructorDao.getInstructor(instructorCode);
				String streamingId = rs.getString("streaming_id");
				String streamingPass = rs.getString("streaming_pass");
				int cancelFlag = rs.getInt("cancel_flag");

				schedule = new Schedule(scheduleCode,lesson,eventDate,timeFrame,instructor,streamingId,streamingPass,cancelFlag);
			}
		} finally {
			// リソースの解放
			if (st != null) {
				st.close();
			}
		}

		// スケジュールを返却
		return schedule;
	}
	
	private ArrayList<Schedule> checkDateSchedules(ArrayList<Schedule> list){
		ArrayList<Schedule> newList = new ArrayList<Schedule>();
		
    	//現在日時を取得
		Calendar clNow = Calendar.getInstance();

		int nowMonth = clNow.get(Calendar.MONTH)+1;	//現在月
		int nowDate = clNow.get(Calendar.DATE);	//現在日
		int nowHour = clNow.get(Calendar.HOUR_OF_DAY);	//現在時間

		//現在日時の時と分を0にする
		clNow.set(clNow.get(Calendar.YEAR),clNow.get(Calendar.MONTH),clNow.get(Calendar.DATE),0,0);

		//日時比較用のクラスに現在日時コピー
		Calendar clCompStart = (Calendar) clNow.clone();
		Calendar clCompEnd = (Calendar) clNow.clone();

		//18時以前なら翌日分より、後なら翌々日分より表示
		if(nowHour <= 18) {	
			clCompStart.set(Calendar.DATE, nowDate+1);	
		}else {
			clCompStart.set(Calendar.DATE, nowDate+2);
		}
		
		//15日までなら当月分を、16日以降なら当月分と翌月分を表示
		if(nowDate <= 15) {
			clCompEnd.set(Calendar.MONTH, nowMonth+1);
			clCompEnd.set(Calendar.DATE, 1);
		}else {
			clCompEnd.set(Calendar.MONTH, nowMonth+2);
			clCompEnd.set(Calendar.DATE, 1);
		}
		
		for(Schedule schedule : list) {
			//文字型からCalendar型へ変換
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			
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
			
			//日時の比較
			//戻り値が0なら一致、正なら現在日時が指定日時を過ぎている、負なら現在日時は指定日時より前
			int diffStart = calendarDate.compareTo(clCompStart);
			int diffEnd = calendarDate.compareTo(clCompEnd);

		    if (diffStart >= 0 && diffEnd < 0){
		    	newList.add(schedule);
		    }
		}
		
		return newList;
	}

	public List<Schedule> getScheduleByLessonCategory(String code) throws SQLException {
		ArrayList<Schedule> list = new ArrayList<Schedule>();
		PreparedStatement st = null;

		if(!code.equals("all")) {
			// PreparedStatementの取得
			st = con.prepareStatement("SELECT schedule_code,s.lesson_code,event_date,time_frame_code,instructor_code,streaming_id,streaming_pass,cancel_flag,lesson_category_code FROM schedule s INNER JOIN lesson l ON s.lesson_code=l.lesson_code WHERE l.lesson_category_code=?");
			st.setString(1, code);
		}else {
			st = con.prepareStatement("SELECT * FROM schedule");
		}
		
		// SQL文を発行
		ResultSet rs = st.executeQuery();

		LessonDAO lessonDao = new LessonDAO(con);
		TimeFrameDAO timeFrameDao = new TimeFrameDAO(con);
		InstructorDAO instructorDao = new InstructorDAO(con);

		// 結果を参照
		while (rs.next()) {
			int scheduleCode = rs.getInt("schedule_code");
			int lessonCode = rs.getInt("lesson_code");
			Lesson lesson = lessonDao.getLesson(lessonCode);
			String eventDate = rs.getDate("event_date").toString();
			int timeFrameCode = rs.getInt("time_frame_code");
			TimeFrame timeFrame = timeFrameDao.getTimeFrame(timeFrameCode);
			int instructorCode = rs.getInt("instructor_code");
			Instructor instructor = instructorDao.getInstructor(instructorCode);
			String streamingId = rs.getString("streaming_id");
			String streamingPass = rs.getString("streaming_pass");
			int cancelFlag = rs.getInt("cancel_flag");

			Schedule schedule = new Schedule(scheduleCode,lesson,eventDate,timeFrame,instructor,streamingId,streamingPass,cancelFlag);

			list.add(schedule);
		}

		// リストを返却
		return checkDateSchedules(list);
	}

	public List<Schedule> getScheduleByTimeFrame(String strDate, String code) throws SQLException {
		ArrayList<Schedule> list = new ArrayList<Schedule>();
		PreparedStatement st = null;

		try {
			if(!code.equals("all")) {
				int iCode = Integer.parseInt(code);

				// PreparedStatementの取得
				st = con.prepareStatement("SELECT * FROM schedule WHERE event_date=? AND time_frame_code=?");
				
				st.setDate(1, java.sql.Date.valueOf(strDate));
				st.setInt(2, iCode);
			}else {
				// PreparedStatementの取得
				st = con.prepareStatement("SELECT * FROM schedule WHERE event_date=?");
				
				st.setDate(1, java.sql.Date.valueOf(strDate));
			}

			// SQL文を発行
			ResultSet rs = st.executeQuery();

			LessonDAO lessonDao = new LessonDAO(con);
			TimeFrameDAO timeFrameDao = new TimeFrameDAO(con);
			InstructorDAO instructorDao = new InstructorDAO(con);

			// 結果を参照
			while (rs.next()) {
				int scheduleCode = rs.getInt("schedule_code");
				int lessonCode = rs.getInt("lesson_code");
				Lesson lesson = lessonDao.getLesson(lessonCode);
				String eventDate = rs.getDate("event_date").toString();
				int timeFrameCode = rs.getInt("time_frame_code");
				TimeFrame timeFrame = timeFrameDao.getTimeFrame(timeFrameCode);
				int instructorCode = rs.getInt("instructor_code");
				Instructor instructor = instructorDao.getInstructor(instructorCode);
				String streamingId = rs.getString("streaming_id");
				String streamingPass = rs.getString("streaming_pass");
				int cancelFlag = rs.getInt("cancel_flag");

				Schedule schedule = new Schedule(scheduleCode,lesson,eventDate,timeFrame,instructor,streamingId,streamingPass,cancelFlag);

				list.add(schedule);
			}
		} finally {
			// リソースの解放
			if (st != null) {
				st.close();
			}
		}

		// リストを返却
		return  checkDateSchedules(list);
	}

	public List<Schedule> getScheduleByInstructor(String code) throws SQLException {
		ArrayList<Schedule> list = new ArrayList<Schedule>();
		PreparedStatement st = null;

		try {
			if(!code.equals("all")) {
				int iCode = Integer.parseInt(code);

				// PreparedStatementの取得
				st = con.prepareStatement("SELECT * FROM schedule WHERE instructor_code=?");
				st.setInt(1, iCode);
			}else {
				st = con.prepareStatement("SELECT * FROM schedule");
			}

			// SQL文を発行
			ResultSet rs = st.executeQuery();

			LessonDAO lessonDao = new LessonDAO(con);
			TimeFrameDAO timeFrameDao = new TimeFrameDAO(con);
			InstructorDAO instructorDao = new InstructorDAO(con);

			// 結果を参照
			while (rs.next()) {
				int scheduleCode = rs.getInt("schedule_code");
				int lessonCode = rs.getInt("lesson_code");
				Lesson lesson = lessonDao.getLesson(lessonCode);
				String eventDate = rs.getDate("event_date").toString();
				int timeFrameCode = rs.getInt("time_frame_code");
				TimeFrame timeFrame = timeFrameDao.getTimeFrame(timeFrameCode);
				int instructorCode = rs.getInt("instructor_code");
				Instructor instructor = instructorDao.getInstructor(instructorCode);
				String streamingId = rs.getString("streaming_id");
				String streamingPass = rs.getString("streaming_pass");
				int cancelFlag = rs.getInt("cancel_flag");

				Schedule schedule = new Schedule(scheduleCode,lesson,eventDate,timeFrame,instructor,streamingId,streamingPass,cancelFlag);

				list.add(schedule);
			}
		} finally {
			// リソースの解放
			if (st != null) {
				st.close();
			}
		}

		// リストを返却
		return  checkDateSchedules(list);
	}
}
