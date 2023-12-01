package test.scheduledao;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ConnectionManager;
import dao.ScheduleDAO;

public class TestCheckDuplicateInstructor {

	public static void main(String[] args) {
		Connection con = null;
		boolean result = false;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ScheduleDAO scheduleDao = new ScheduleDAO(con);
            
            //正常系(重複しない)
        	result = scheduleDao.checkDuplicateInstructor("2023-12-15",1,1);
        	System.out.println("期待する結果:true");
			System.out.println("実行結果:" + result);
			System.out.println();
			
            //異常系(重複する)
        	result = scheduleDao.checkDuplicateInstructor("2023-12-15",1,6);
        	System.out.println("期待する結果:false");
			System.out.println("実行結果:" + result);
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
