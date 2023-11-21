package test.memberdao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Creca;
import beans.Member;
import dao.ConnectionManager;
import dao.MemberDAO;

public class TestInsertMember {

	public static void main(String[] args) {
		Connection con = null;
		Member member = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	MemberDAO memberDao = new MemberDAO(con);
            
            
    		int result = 0;
        	Creca creca = new Creca(1,"VISA");
        	
        	//正常系
        	member = new Member(null, "テスト姓", "テスト名", "テストセイ", "テストメイ", "test1@sample.com",
        			"テストネーム1", "testpass", creca, "1234223432344234", "20990101");
        	
        	result = memberDao.insertMember(member);
        	System.out.println("期待する結果:1");
			System.out.println("実行結果:" + result);
			System.out.println();
        	
            //異常系(番号重複)
			/*
        	member = new Member("2018100010", "テスト姓", "テスト名", "テストセイ", "テストメイ", "test2@sample.com",
        			"テストネーム2", "testpass", creca, "1234223432344234", "20990101");
        	
        	System.out.println("期待する結果:例外");
        	result = memberDao.insertMember(member);
			*/

            //異常系(email重複)
			/*
        	member = new Member(null, "テスト姓", "テスト名", "テストセイ", "テストメイ", "test1@sample.com",
        			"テストネーム3", "testpass", creca, "1234223432344234", "20990101");
        	
        	System.out.println("期待する結果:例外");
        	result = memberDao.insertMember(member);
			*/
			
            //異常系(ニックネーム重複)
			/*
        	member = new Member(null, "テスト姓", "テスト名", "テストセイ", "テストメイ", "test4@sample.com",
        			"テストネーム1", "testpass", creca, "1234223432344234", "20990101");
        	
        	System.out.println("期待する結果:例外");
        	result = memberDao.insertMember(member);
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
