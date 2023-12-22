package test.instructordao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Instructor;
import dao.ConnectionManager;
import dao.InstructorDAO;

public class TestGetInstructorByLoginId {

	public static void main(String[] args) {
		Connection con = null;
		Instructor instructor = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	InstructorDAO instructorDao = new InstructorDAO(con);
            
            //正常系
        	instructor = instructorDao.getInstructorByLoginId("t-yamada", "taropass");
        	System.out.println("期待する結果:山田　太郎");
			System.out.println("実行結果:" + instructor);
			System.out.println();
			
            //異常系(パスワード不一致)
			instructor = instructorDao.getInstructorByLoginId("t-yamada", "password");
        	System.out.println("期待する結果:null");
			System.out.println("実行結果:" + instructor);
			System.out.println();
			
            //異常系(ログインIDがnull)
			instructor = instructorDao.getInstructorByLoginId(null, "taropass");
        	System.out.println("期待する結果:null");
			System.out.println("実行結果:" + instructor);
			System.out.println();
			
            //異常系(パスワードがnull)
			instructor = instructorDao.getInstructorByLoginId("t-yamada", null);
        	System.out.println("期待する結果:null");
			System.out.println("実行結果:" + instructor);
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
