package test.timeframedao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.TimeFrame;
import dao.ConnectionManager;
import dao.TimeFrameDAO;

public class TestGetTimeFrame {
	
	public static void main(String[] args) {
		Connection con = null;
		TimeFrame timeFrame = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	TimeFrameDAO timeFrameDao = new TimeFrameDAO(con);
            
            //正常系
        	timeFrame = timeFrameDao.getTimeFrame(1);
        	System.out.println("期待する結果:10:00 10:50");
			System.out.println("実行結果:" + timeFrame.getStartTime() + " " + timeFrame.getEndTime());
			System.out.println();
			
            //異常系
        	timeFrame = timeFrameDao.getTimeFrame(10);
        	System.out.println("期待する結果:null");
			System.out.println("実行結果:" + timeFrame);
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
