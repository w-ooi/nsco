package test.reservedao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Reserve;
import dao.ConnectionManager;
import dao.ReserveDAO;

public class TestUpdateAttendance {

	public static void main(String[] args) {
		Connection con = null;
		List<Reserve> reserveList = new ArrayList<Reserve>();
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ReserveDAO reserveDao = new ReserveDAO(con);
            
    		int result = 0;
        	
        	//正常系
    		Reserve r1 = reserveDao.getReserve(1);
    		Reserve r2 = reserveDao.getReserve(2);
    		reserveList.add(r1);
    		reserveList.add(r2);
    		
        	result = reserveDao.updateAttendance(reserveList);
        	
        	System.out.println("期待する結果:2");
			System.out.println("実行結果:" + result);
			System.out.println();
        	
            //異常系(リストに要素がない)
			reserveList.clear();
        	result = reserveDao.updateAttendance(reserveList);
        	
        	System.out.println("期待する結果:0");
			System.out.println("実行結果:" + result);
			System.out.println();
			
            //異常系(リストがnull)
			/*
        	result = reserveDao.updateAttendance(null);
        	System.out.println("期待する結果:例外");
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
