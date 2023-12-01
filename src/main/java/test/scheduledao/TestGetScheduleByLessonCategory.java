package test.scheduledao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import beans.Schedule;
import dao.ConnectionManager;
import dao.ScheduleDAO;

public class TestGetScheduleByLessonCategory {
	
	public static void main(String[] args) {
		Connection con = null;
		List<Schedule> scheduleList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ScheduleDAO scheduleDao = new ScheduleDAO(con);
            
            //正常系(利用者側)
        	scheduleList = scheduleDao.getScheduleByLessonCategory("2", "user");
        	
        	for(Schedule scd : scheduleList) {
				System.out.println(scd.getLesson().getLessonCategory().getLessonCategoryName());
        	}
        	System.out.println("期待する件数:1");
        	System.out.println("実行結果の件数:" + scheduleList.size());
			System.out.println();
			
            //正常系(管理者側)
        	scheduleList = scheduleDao.getScheduleByLessonCategory("2", "headOffice");
        	
        	for(Schedule scd : scheduleList) {
				System.out.println(scd.getLesson().getLessonCategory().getLessonCategoryName());
        	}
        	System.out.println("期待する件数:4");
        	System.out.println("実行結果の件数:" + scheduleList.size());
			System.out.println();
			
            //異常系(存在しない番号)
        	scheduleList = scheduleDao.getScheduleByLessonCategory("0", "user");
        	
        	System.out.println("期待する件数:0");
        	System.out.println("実行結果の件数:" + scheduleList.size());
			System.out.println();

			//異常系(null)
			/*
        	scheduleList = scheduleDao.getScheduleByLessonCategory(null, "user");
        	
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
