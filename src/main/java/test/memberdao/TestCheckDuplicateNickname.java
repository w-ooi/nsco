package test.memberdao;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ConnectionManager;
import dao.MemberDAO;

public class TestCheckDuplicateNickname {

	public static void main(String[] args) {
		Connection con = null;
		boolean result = false;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	MemberDAO memberDao = new MemberDAO(con);
            
            //正常系(重複しない)
        	result = memberDao.checkDuplicateNickname("テスト");
        	System.out.println("期待する結果:true");
			System.out.println("実行結果:" + result);
			System.out.println();
			
            //異常系(重複する)
			result = memberDao.checkDuplicateNickname("ヒロ");
        	System.out.println("期待する結果:false");
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
