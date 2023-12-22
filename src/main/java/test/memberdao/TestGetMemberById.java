package test.memberdao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Member;
import dao.ConnectionManager;
import dao.MemberDAO;

public class TestGetMemberById {

	public static void main(String[] args) {
		Connection con = null;
		Member member = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	MemberDAO memberDao = new MemberDAO(con);
            
            //正常系
        	member = memberDao.getMemberById("2018100010","InuZuki55");
        	System.out.println("期待する結果:鈴木 博");
			System.out.println("実行結果:" + member);
			System.out.println();
			
            //異常系(パスワード誤り)
			member = memberDao.getMemberById("2018100010","test");
        	System.out.println("期待する結果:null");
			System.out.println("実行結果:" + member);
			System.out.println();
			
            //異常系(IDが存在しない)
			member = memberDao.getMemberById("1111111111","InuZuki55");
        	System.out.println("期待する結果:null");
			System.out.println("実行結果:" + member);
			System.out.println();
			
            //異常系(パスワードがnull)
			member = memberDao.getMemberById("2018100010",null);
        	System.out.println("期待する結果:null");
			System.out.println("実行結果:" + member);
			System.out.println();
			
            //異常系(IDがnull)
			member = memberDao.getMemberById(null,"test");
        	System.out.println("期待する結果:null");
			System.out.println("実行結果:" + member);
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
