package test.scheduledao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Schedule;
import dao.ConnectionManager;
import dao.ScheduleDAO;

public class TestGetSchedule {

	public static void main(String[] args) {
		Connection con = null;
		Schedule schedule = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ScheduleDAO scheduleDao = new ScheduleDAO(con);
            
            //正常系
        	schedule = scheduleDao.getSchedule(1);
        	System.out.println("期待する結果:2023-09-29");
			System.out.println("実行結果:" + schedule.getEventDate());
			System.out.println();
			
            //異常系
        	schedule = scheduleDao.getSchedule(6);
        	System.out.println("期待する結果:null");
			System.out.println("実行結果:" + schedule);
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
