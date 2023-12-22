package test.reservedao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Reserve;
import dao.ConnectionManager;
import dao.ReserveDAO;

public class TestGetAfterTakeLessonReserves {
	
	public static void main(String[] args) {
		Connection con = null;
		ArrayList<Reserve> reserveList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ReserveDAO reserveDao = new ReserveDAO(con);
            
        	//正常系(30件未満)
        	reserveList = reserveDao.getAfterTakeLessonReserves("2019455595");
			
			for(Reserve rsv : reserveList) {
				System.out.println(rsv.getReserveCode() + " : " + rsv.getSchedule().getEventDate());
			}
        	System.out.println("期待する件数:1");
			System.out.println("実行結果の件数:" + reserveList.size());
			System.out.println();

			//正常系(30件以上,予約番号・日付の降順)
        	reserveList = reserveDao.getAfterTakeLessonReserves("2020442403");
			
			for(Reserve rsv : reserveList) {
				System.out.println(rsv);
			}
        	System.out.println("期待する件数:30");
			System.out.println("実行結果の件数:" + reserveList.size());
			System.out.println();
			
        	//異常系(存在しない)
        	reserveList = reserveDao.getAfterTakeLessonReserves("1111111111");
			
			for(Reserve rsv : reserveList) {
				System.out.println(rsv);
			}
        	System.out.println("期待する件数:0");
			System.out.println("実行結果の件数:" + reserveList.size());
			System.out.println();

			//異常系(null)
        	reserveList = reserveDao.getAfterTakeLessonReserves(null);
			
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
