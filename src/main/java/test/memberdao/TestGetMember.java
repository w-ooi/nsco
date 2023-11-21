package test.memberdao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Member;
import dao.ConnectionManager;
import dao.MemberDAO;

public class TestGetMember {

	public static void main(String[] args) {
		Connection con = null;
		Member member = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	MemberDAO memberDao = new MemberDAO(con);
            
            //正常系
        	member = memberDao.getMember("2018100010");
        	System.out.println("期待する結果:鈴木 博");
			System.out.println("実行結果:" + member.getNameSei() + " " + member.getNameMei());
			System.out.println();
			
            //異常系
			member = memberDao.getMember("1111111111");
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
