package test.memberdao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Member;
import dao.ConnectionManager;
import dao.MemberDAO;

public class TestGetAllMembers {

	public static void main(String[] args) {
		Connection con = null;
		ArrayList<Member> memberList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
            MemberDAO memberDao = new MemberDAO(con);
            
            //テスト対象メソッドの呼び出し
            memberList = memberDao.getAllMembers();
			
			//結果の確認 
			for(Member mm : memberList) {
				System.out.print(mm.getMemberNo() + ":");
				System.out.print(mm.getNameSei() + ":");
				System.out.println(mm.getNameMei());
			}
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
