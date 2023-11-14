package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
			st = con.prepareStatement("SELECT * FROM schedule where schedule_code=?");
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

	public ArrayList<Schedule> getAllSchedules() throws SQLException {
		ArrayList<Schedule> list = new ArrayList<Schedule>();
		PreparedStatement st = null;

		try {
			// PreparedStatementの取得
			st = con.prepareStatement("SELECT * FROM schedule");

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
		return list;
	}

	public List<Schedule> getScheduleByLessonCategory(String code) throws SQLException {
		ArrayList<Schedule> list = new ArrayList<Schedule>();

		ArrayList<Schedule> scheduleList = getAllSchedules();

		if(!code.equals("all")) {
			int iCode = Integer.parseInt(code);

			for(Schedule schedule : scheduleList) {
				if(schedule.getLesson().getLessonCategory().getLessonCategoryCode() == iCode) {
					list.add(schedule);
				}
			}
		}else {
			list = scheduleList;
		}

		// リストを返却
		return list;
	}

	public List<Schedule> getScheduleByTimeFrame(String strDate, String code) throws SQLException {
		ArrayList<Schedule> list = new ArrayList<Schedule>();
		PreparedStatement st = null;

		try {
			if(!code.equals("all")) {
				int iCode = Integer.parseInt(code);

				// PreparedStatementの取得
				st = con.prepareStatement("SELECT * FROM schedule where event_date=? AND time_frame_code=?");
				
				st.setDate(1, java.sql.Date.valueOf(strDate));
				st.setInt(2, iCode);
			}else {
				// PreparedStatementの取得
				st = con.prepareStatement("SELECT * FROM schedule where event_date=?");
				
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
		return list;
	}

	public List<Schedule> getScheduleByInstructor(String code) throws SQLException {
		ArrayList<Schedule> list = new ArrayList<Schedule>();
		PreparedStatement st = null;

		try {
			if(!code.equals("all")) {
				int iCode = Integer.parseInt(code);

				// PreparedStatementの取得
				st = con.prepareStatement("SELECT * FROM schedule where instructor_code=?");
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
		return list;
	}
}
