package test.crecadao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Creca;
import dao.ConnectionManager;
import dao.CrecaDAO;

public class TestGetCreca {
	
	public static void main(String[] args) {
		Connection con = null;
		Creca creca = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	CrecaDAO crecaDao = new CrecaDAO(con);
            
            //正常系
        	creca = crecaDao.getCreca(1);
        	System.out.println("期待する結果:VISA");
			System.out.println("実行結果:" + creca.getCrecaCompName());
			System.out.println();
			
            //異常系
        	creca = crecaDao.getCreca(11);
        	System.out.println("期待する結果:null");
			System.out.println("実行結果:" + creca);
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
