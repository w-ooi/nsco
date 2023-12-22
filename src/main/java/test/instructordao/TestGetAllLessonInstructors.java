package test.instructordao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Instructor;
import dao.ConnectionManager;
import dao.InstructorDAO;

public class TestGetAllLessonInstructors {

	public static void main(String[] args) {
		Connection con = null;
		ArrayList<Instructor> instructorList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	InstructorDAO instructorDao = new InstructorDAO(con);
            
            //テスト対象メソッドの呼び出し
        	instructorList = instructorDao.getAllInstructors();
			
			//結果の確認 
			for(Instructor ist : instructorList) {
				System.out.println(ist);
			}
			System.out.println("件数:" + instructorList.size());
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
