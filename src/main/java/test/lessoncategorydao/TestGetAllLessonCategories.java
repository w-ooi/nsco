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
				System.out.println(lc);
			}
			System.out.println("件数:" + lessonCategoryList.size());
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
