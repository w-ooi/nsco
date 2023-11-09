package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Lesson;
import beans.Schedule;
import beans.Staff;
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
			StaffDAO staffDao = new StaffDAO(con);

			// 結果を参照
			if (rs.next()) {
				int lessonCode = rs.getInt("lesson_code");
				Lesson lesson = lessonDao.getLesson(lessonCode);
				String eventDate = rs.getDate("event_date").toString();
				int timeFrameCode = rs.getInt("time_frame_code");
				TimeFrame timeFrame = timeFrameDao.getTimeFrame(timeFrameCode);
				int staffCode = rs.getInt("staff_code");
				Staff staff = staffDao.getStaff(staffCode);
				String streamingId = rs.getString("streaming_id");
				String streamingPass = rs.getString("streaming_pass");
				int cancelFlag = rs.getInt("cancel_flag");

				schedule = new Schedule(scheduleCode,lesson,eventDate,timeFrame,staff,streamingId,streamingPass,cancelFlag);
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
			StaffDAO staffDao = new StaffDAO(con);

			// 結果を参照
			while (rs.next()) {
				int scheduleCode = rs.getInt("schedule_code");
				int lessonCode = rs.getInt("lesson_code");
				Lesson lesson = lessonDao.getLesson(lessonCode);
				String eventDate = rs.getDate("event_date").toString();
				int timeFrameCode = rs.getInt("time_frame_code");
				TimeFrame timeFrame = timeFrameDao.getTimeFrame(timeFrameCode);
				int staffCode = rs.getInt("staff_code");
				Staff staff = staffDao.getStaff(staffCode);
				String streamingId = rs.getString("streaming_id");
				String streamingPass = rs.getString("streaming_pass");
				int cancelFlag = rs.getInt("cancel_flag");

				Schedule schedule = new Schedule(scheduleCode,lesson,eventDate,timeFrame,staff,streamingId,streamingPass,cancelFlag);

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
