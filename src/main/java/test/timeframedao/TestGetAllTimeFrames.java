package test.timeframedao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.TimeFrame;
import dao.ConnectionManager;
import dao.TimeFrameDAO;

public class TestGetAllTimeFrames {

	public static void main(String[] args) {
		Connection con = null;
		ArrayList<TimeFrame> timeFrameList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	TimeFrameDAO timeFrameDao = new TimeFrameDAO(con);
            
            //テスト対象メソッドの呼び出し
        	timeFrameList = timeFrameDao.getAllTimeFrames();
			
			//結果の確認 
			for(TimeFrame tf : timeFrameList) {
				System.out.print(tf.getStartTime() + ":");
				System.out.println(tf.getEndTime());
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
