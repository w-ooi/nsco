package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Lesson;
import beans.LessonCategory;

public class LessonDAO {
	private Connection con;

	public LessonDAO(Connection con) {
		this.con = con;
	}

	public Lesson getLesson(int lessonCode) throws SQLException {
		Lesson lesson = null;
		PreparedStatement st = null;

		try {
			// PreparedStatementの取得
			st = con.prepareStatement("SELECT * FROM lesson where lesson_code=?");
			st.setInt(1, lessonCode);

			// SQL文を発行
			ResultSet rs = st.executeQuery();

			LessonCategoryDAO lcdao = new LessonCategoryDAO(con);

			// 結果を参照
			if (rs.next()) {
				String name = rs.getString("lesson_name");
				String description = rs.getString("description");
				int lessonCategoryCode = rs.getInt("lesson_category_code");
				LessonCategory category = lcdao.getLessonCategory(lessonCategoryCode);

				lesson = new Lesson(lessonCode, name, description, category);
			}
		} finally {
			// リソースの解放
			if (st != null) {
				st.close();
			}
		}

		// レッスンを返却
		return lesson;
	}

	public ArrayList<Lesson> getAllLessons() throws SQLException {
		ArrayList<Lesson> list = new ArrayList<Lesson>();
		PreparedStatement st = null;

		try {
			// PreparedStatementの取得
			st = con.prepareStatement("SELECT * FROM lesson");

			// SQL文を発行
			ResultSet rs = st.executeQuery();

			LessonCategoryDAO lcdao = new LessonCategoryDAO(con);

			// 結果を参照
			while (rs.next()) {
				int lessonCode = rs.getInt("lesson_code");
				String name = rs.getString("lesson_name");
				String description = rs.getString("description");
				int lessonCategoryCode = rs.getInt("lesson_category_code");
				LessonCategory category = lcdao.getLessonCategory(lessonCategoryCode);

				Lesson lesson = new Lesson(lessonCode, name, description, category);
				list.add(lesson);
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
