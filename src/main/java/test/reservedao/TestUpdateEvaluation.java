package test.reservedao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Member;
import dao.ConnectionManager;
import dao.ReserveDAO;

public class TestUpdateEvaluation {

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
        	result = reserveDao.updateEvaluation(1,	3, 5);
        	
        	System.out.println("期待する結果:1");
			System.out.println("実行結果:" + result);
			System.out.println();
        	
            //異常系(予約番号が存在しない)
			result = reserveDao.updateEvaluation(0,	3, 5);
        	
        	System.out.println("期待する結果:0");
			System.out.println("実行結果:" + result);
			System.out.println();
			
            //異常系(レッスン評価が範囲外)
			result = reserveDao.updateEvaluation(2,	-1, 5);
        	
        	System.out.println("期待する結果:1");
			System.out.println("実行結果:" + result);
			System.out.println();
			
            //異常系(インストラクター評価が範囲外)
			result = reserveDao.updateEvaluation(2,	3, 6);
        	
        	System.out.println("期待する結果:1");
			System.out.println("実行結果:" + result);
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
