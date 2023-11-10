package test.scheduledao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Schedule;
import dao.ConnectionManager;
import dao.ScheduleDAO;

public class TestGetAllSchedules {

	public static void main(String[] args) {
		Connection con = null;
		ArrayList<Schedule> scheduleList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ScheduleDAO scheduleDao = new ScheduleDAO(con);
            
            //テスト対象メソッドの呼び出し
        	scheduleList = scheduleDao.getAllSchedules();
			
			//結果の確認 
			for(Schedule sc : scheduleList) {
				System.out.print(sc.getScheduleCode() + ":");
				System.out.print(sc.getStreamingId() + ":");
				System.out.println(sc.getStreamingPass());
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
