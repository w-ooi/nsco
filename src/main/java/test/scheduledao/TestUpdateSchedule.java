package test.scheduledao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Member;
import beans.Schedule;
import dao.ConnectionManager;
import dao.ScheduleDAO;

public class TestUpdateSchedule {

	public static void main(String[] args) {
		Connection con = null;
		Member member = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ScheduleDAO scheduleDao = new ScheduleDAO(con);
    		Schedule schedule = scheduleDao.getSchedule(1);
        	
    		int result = 0;
        	
        	//正常系
    		schedule.setStreamingId("nnn-nnn-1111");
    		schedule.setStreamingPass("pass1111");
    		schedule.setCancelFlag(1);
        	
        	result = scheduleDao.updateSchedule(schedule);
        	System.out.println("期待する結果:1");
			System.out.println("実行結果:" + result);
			System.out.println();
        	
            //異常系(null値で更新)
			/*
			schedule.setStreamingId(null);

			result = scheduleDao.updateSchedule(schedule);
        	System.out.println("期待する結果:例外");
        	*/

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
