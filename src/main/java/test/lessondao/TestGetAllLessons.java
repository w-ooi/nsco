package test.lessondao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Lesson;
import dao.ConnectionManager;
import dao.LessonDAO;

public class TestGetAllLessons {

	public static void main(String[] args) {
		Connection con = null;
		ArrayList<Lesson> lessonList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
            LessonDAO lessonDao = new LessonDAO(con);
            
            //テスト対象メソッドの呼び出し
			lessonList = lessonDao.getAllLessons();
			
			//結果の確認 
			for(Lesson ls : lessonList) {
				System.out.print(ls.getLessonCode() + ":");
				System.out.println(ls.getLessonName());
			}
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
