package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Member;
import beans.Reserve;
import beans.Schedule;

public class ReserveDAO {
	private Connection con;

	public ReserveDAO(Connection con) {
		this.con = con;
	}

	public Reserve getReserve(int reserveCode) throws SQLException {
		Reserve reserve = null;
		PreparedStatement st = null;

		try {
			// PreparedStatementの取得
			st = con.prepareStatement("SELECT * FROM reserve where reserve_code=?");
			st.setInt(1, reserveCode);

			// SQL文を発行
			ResultSet rs = st.executeQuery();

			MemberDAO memberDao = new MemberDAO(con);
			ScheduleDAO scheduleDao = new ScheduleDAO(con);

			// 結果を参照
			if (rs.next()) {
				String memberNo = rs.getString("member_no");
				Member member = memberDao.getMember(memberNo);
				int scheduleCode = rs.getInt("schedule_code");
				Schedule schedule = scheduleDao.getSchedule(scheduleCode);
				int attendanceFlag = rs.getInt("attendance_flag");
				int cancelFlag = rs.getInt("cancel_flag");
				int lessonEvaluation = rs.getInt("lesson_evaluation");
				int instructorEvaluation = rs.getInt("instructor_evaluation");

				reserve = new Reserve(reserveCode, member, schedule, attendanceFlag, cancelFlag, lessonEvaluation,
						instructorEvaluation);
			}
		} finally {
			// リソースの解放
			if (st != null) {
				st.close();
			}
		}

		// 予約を返却
		return reserve;
	}

	public ArrayList<Reserve> getAllReserves() throws SQLException {
		ArrayList<Reserve> list = new ArrayList<Reserve>();
		PreparedStatement st = null;

		try {
			// PreparedStatementの取得
			st = con.prepareStatement("SELECT * FROM reserve");

			// SQL文を発行
			ResultSet rs = st.executeQuery();

			MemberDAO memberDao = new MemberDAO(con);
			ScheduleDAO scheduleDao = new ScheduleDAO(con);

			// 結果を参照
			while (rs.next()) {
				int reserveCode = rs.getInt("reserve_code");
				String memberNo = rs.getString("member_no");
				Member member = memberDao.getMember(memberNo);
				int scheduleCode = rs.getInt("schedule_code");
				Schedule schedule = scheduleDao.getSchedule(scheduleCode);
				int attendanceFlag = rs.getInt("attendance_flag");
				int cancelFlag = rs.getInt("cancel_flag");
				int lessonEvaluation = rs.getInt("lesson_evaluation");
				int instructorEvaluation = rs.getInt("instructor_evaluation");

				Reserve reserve = new Reserve(reserveCode, member, schedule, attendanceFlag, cancelFlag,
						lessonEvaluation, instructorEvaluation);

				list.add(reserve);
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

	public int setReserve(String memberNo, int scheduleCode) throws SQLException {
		int intResult = 0;
		PreparedStatement st = null;

		try {
			// PreparedStatementの取得
			st = con.prepareStatement("INSERT INTO reserve VALUES(null, ?, ?, 0, 0, 0, 0)");
			st.setString(1, memberNo);
			st.setInt(2, scheduleCode);

			// SQL文を発行
			intResult = st.executeUpdate();
		} finally {
			// リソースの解放
			if (st != null) {
				st.close();
			}
		}

		return intResult;
	}
}
