package test.memberdao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Member;
import dao.ConnectionManager;
import dao.MemberDAO;

public class TestUpdatePassword {

	public static void main(String[] args) {
		Connection con = null;
		Member member = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	MemberDAO memberDao = new MemberDAO(con);
            
    		int result = 0;
        	
        	//正常系
        	result = memberDao.updatePassword("2019455595", "testpass");
        	System.out.println("期待する結果:1");
			System.out.println("実行結果:" + result);
			System.out.println();
			
            //異常系(番号がnull)
        	result = memberDao.updatePassword(null, "testpass");
        	System.out.println("期待する結果:0");
			System.out.println("実行結果:" + result);
			System.out.println();

			//異常系(パスワードがnull)
			/*
        	System.out.println("期待する結果:例外");
        	result = memberDao.updatePassword("2019455595", null);
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
