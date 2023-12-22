package test.instructordao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Instructor;
import dao.ConnectionManager;
import dao.InstructorDAO;

public class TestGetInstructor {
	
	public static void main(String[] args) {
		Connection con = null;
		Instructor instructor = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	InstructorDAO instructorDao = new InstructorDAO(con);
            
            //正常系
        	instructor = instructorDao.getInstructor(1);
        	System.out.println("期待する結果:山田　太郎");
			System.out.println("実行結果:" + instructor);
			System.out.println();
			
            //異常系
			instructor = instructorDao.getInstructor(9);
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
