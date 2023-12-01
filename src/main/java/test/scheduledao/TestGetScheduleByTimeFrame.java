package test.scheduledao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import beans.Schedule;
import dao.ConnectionManager;
import dao.ScheduleDAO;

public class TestGetScheduleByTimeFrame {
	
	public static void main(String[] args) {
		Connection con = null;
		List<Schedule> scheduleList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ScheduleDAO scheduleDao = new ScheduleDAO(con);
            
            //正常系(利用者側)
        	scheduleList = scheduleDao.getScheduleByTimeFrame("2023-12-15", "1", "user");
        	
        	for(Schedule scd : scheduleList) {
				System.out.println(scd.getEventDate() + " : " + scd.getTimeFrame().getStartTime());
        	}
        	System.out.println("期待する件数:2");
        	System.out.println("実行結果の件数:" + scheduleList.size());
			System.out.println();
			
            //正常系(管理者側)
        	scheduleList = scheduleDao.getScheduleByTimeFrame("2023-12-15", "1", "headOffice");
        	
        	for(Schedule scd : scheduleList) {
				System.out.println(scd.getEventDate() + " : " + scd.getTimeFrame().getStartTime());
        	}
        	System.out.println("期待する件数:2");
        	System.out.println("実行結果の件数:" + scheduleList.size());
			System.out.println();
			

			//異常系(存在しない時間枠番号)
        	scheduleList = scheduleDao.getScheduleByTimeFrame("2023-12-15", "0", "user");
        	
        	System.out.println("期待する件数:0");
        	System.out.println("実行結果の件数:" + scheduleList.size());
			System.out.println();

            //異常系(存在しない日付)
			/*
        	scheduleList = scheduleDao.getScheduleByTimeFrame("2023-12-32", "1", "user");
        	
        	System.out.println("期待する件数:例外");
			 */
			
			//異常系(日付がnull)
			/*
        	scheduleList = scheduleDao.getScheduleByTimeFrame(null, "1", "user");
        	
        	System.out.println("期待する件数:例外");
			*/
			
			//異常系(時間枠番号がnull)
			/*
        	scheduleList = scheduleDao.getScheduleByTimeFrame("2023-12-15", null, "user");
        	
        	System.out.println("期待する件数:例外");
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
