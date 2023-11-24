package test.reservedao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Member;
import dao.ConnectionManager;
import dao.ReserveDAO;

public class TestSetReserve {
	
	public static void main(String[] args) {
		Connection con = null;
		Member member = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ReserveDAO reserveDao = new ReserveDAO(con);
            
    		int result = 0;
        	
        	//正常系
        	result = reserveDao.setReserve("2015230042", 5);
        	
        	System.out.println("期待する結果:1");
			System.out.println("実行結果:" + result);
			System.out.println();
        	
            //異常系(会員番号が存在しない)
			/*
			result = reserveDao.setReserve("1111111111", 5);
        	
        	System.out.println("期待する結果:例外");
			*/
			
            //異常系(スケジュール番号が存在しない)
			/*
			result = reserveDao.setReserve("2015230042", 0);
        	
			System.out.println("期待する結果:例外");
			*/
			
            //異常系(会員番号がnull)
			/*
			result = reserveDao.setReserve(null, 2);
        	
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
