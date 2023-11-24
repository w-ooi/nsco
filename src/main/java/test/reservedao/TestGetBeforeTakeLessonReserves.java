package test.reservedao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Reserve;
import dao.ConnectionManager;
import dao.ReserveDAO;

public class TestGetBeforeTakeLessonReserves {
	
	public static void main(String[] args) {
		Connection con = null;
		ArrayList<Reserve> reserveList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ReserveDAO reserveDao = new ReserveDAO(con);
            
        	//正常系(予約番号・日付の昇順)
        	reserveList = reserveDao.getBeforeTakeLessonReserves("2018100010");
			
			for(Reserve rsv : reserveList) {
				System.out.println(rsv.getReserveCode() + " : " + rsv.getSchedule().getEventDate());
			}
        	System.out.println("期待する件数:10");
			System.out.println("実行結果の件数:" + reserveList.size());
			System.out.println();
			
        	//異常系(存在しない)
        	reserveList = reserveDao.getBeforeTakeLessonReserves("1111111111");
			
			for(Reserve rsv : reserveList) {
				System.out.println(rsv.getReserveCode() + " : " + rsv.getSchedule().getEventDate());
			}
        	System.out.println("期待する件数:0");
			System.out.println("実行結果の件数:" + reserveList.size());
			System.out.println();

			//異常系(null)
        	reserveList = reserveDao.getBeforeTakeLessonReserves(null);
			
        	System.out.println("期待する件数:0");
			System.out.println("実行結果の件数:" + reserveList.size());
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
