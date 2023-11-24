package test.reservedao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Reserve;
import dao.ConnectionManager;
import dao.ReserveDAO;

public class TestGetReserve {

	public static void main(String[] args) {
		Connection con = null;
		Reserve reserve = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ReserveDAO reserveDao = new ReserveDAO(con);
            
            //正常系
        	reserve = reserveDao.getReserve(1);
        	System.out.println("期待する結果:2018100010");
			System.out.println("実行結果:" + reserve.getMember().getMemberNo());
			System.out.println();
			
            //異常系
        	reserve = reserveDao.getReserve(0);
        	System.out.println("期待する結果:null");
			System.out.println("実行結果:" + reserve);
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
