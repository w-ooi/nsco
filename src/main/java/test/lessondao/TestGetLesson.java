package test.lessondao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Lesson;
import dao.ConnectionManager;
import dao.LessonDAO;

public class TestGetLesson {

	public static void main(String[] args) {
		Connection con = null;
		Lesson lesson = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	LessonDAO lessonDao = new LessonDAO(con);
            
            //正常系
        	lesson = lessonDao.getLesson(1);
        	System.out.println("期待する結果:誰でも始められる簡単エクササイズ");
			System.out.println("実行結果:" + lesson);
			System.out.println();
			
            //異常系
			lesson = lessonDao.getLesson(6);
        	System.out.println("期待する結果:null");
			System.out.println("実行結果:" + lesson);
			System.out.println();
		}catch (SQLException e) {
            e.printStackTrace();
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
}
