package test.lessoncategorydao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.LessonCategory;
import dao.ConnectionManager;
import dao.LessonCategoryDAO;

public class TestGetLessonCategory {

	public static void main(String[] args) {
		Connection con = null;
		LessonCategory lessonCategory = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	LessonCategoryDAO lessonCategoryDao = new LessonCategoryDAO(con);
            
            //正常系
        	lessonCategory = lessonCategoryDao.getLessonCategory(1);
        	System.out.println("期待する結果:ヨガ");
			System.out.println("実行結果:" + lessonCategory.getLessonCategoryName());
			System.out.println();
			
            //異常系
			lessonCategory = lessonCategoryDao.getLessonCategory(8);
        	System.out.println("期待する結果:null");
			System.out.println("実行結果:" + lessonCategory);
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
