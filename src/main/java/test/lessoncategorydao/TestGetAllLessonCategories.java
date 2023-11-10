package test.lessoncategorydao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.LessonCategory;
import dao.ConnectionManager;
import dao.LessonCategoryDAO;

public class TestGetAllLessonCategories {

	public static void main(String[] args) {
		Connection con = null;
		ArrayList<LessonCategory> lessonCategoryList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
            LessonCategoryDAO lessonCategoryDao = new LessonCategoryDAO(con);
            
            //テスト対象メソッドの呼び出し
			lessonCategoryList = lessonCategoryDao.getAllLessonCategories();
			
			//結果の確認 
			for(LessonCategory lc : lessonCategoryList) {
				System.out.print(lc.getLessonCategoryCode() + ":");
				System.out.println(lc.getLessonCategoryName());
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
