package test.scheduledao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import beans.Schedule;
import dao.ConnectionManager;
import dao.ScheduleDAO;

public class TestGetScheduleByToday {

	public static void main(String[] args) {
		Connection con = null;
		List<Schedule> scheduleList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ScheduleDAO scheduleDao = new ScheduleDAO(con);
            
            //正常系(利用者側)
        	scheduleList = scheduleDao.getScheduleByToday();
        	
        	for(Schedule scd : scheduleList) {
				System.out.println(scd.getEventDate() + " : " + scd.getTimeFrame().getStartTime());
        	}
        	System.out.println("期待する件数:2");
        	System.out.println("実行結果の件数:" + scheduleList.size());
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
