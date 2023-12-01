package test.lessondao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import beans.Lesson;
import dao.ConnectionManager;
import dao.LessonDAO;

public class TestGetAllLessons {

	public static void main(String[] args) {
		Connection con = null;
		List<Lesson> lessonList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	LessonDAO lessonDao = new LessonDAO(con);
            
            //テスト対象メソッドの呼び出し
        	lessonList = lessonDao.getAllLessons();
			
			//結果の確認 
			for(Lesson lsn : lessonList) {
				System.out.print(lsn.getLessonCode() + ":");
				System.out.println(lsn.getLessonName());
			}
			System.out.println("件数:" + lessonList.size());
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
