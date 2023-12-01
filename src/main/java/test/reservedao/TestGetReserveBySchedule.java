package test.reservedao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import beans.Reserve;
import dao.ConnectionManager;
import dao.ReserveDAO;

public class TestGetReserveBySchedule {

	public static void main(String[] args) {
		Connection con = null;
		List<Reserve> reserveList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ReserveDAO reserveDao = new ReserveDAO(con);
            
            //正常系
        	reserveList = reserveDao.getReserveBySchedule("1");
        	for(Reserve rsv : reserveList) {
				System.out.println(rsv.getReserveCode() + " : " + rsv.getSchedule().getEventDate());
			}
        	System.out.println("期待する件数:9");
			System.out.println("実行結果の件数:" + reserveList.size());
			System.out.println();
			
            //異常系(スケジュール番号が存在しない)
	    	reserveList = reserveDao.getReserveBySchedule("0");
        	System.out.println("期待する件数:0");
			System.out.println("実行結果の件数:" + reserveList.size());
			System.out.println();
			
            //異常系(スケジュール番号がnull)
			/*
	    	reserveList = reserveDao.getReserveBySchedule(null);
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
