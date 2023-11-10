package test.crecadao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Creca;
import dao.ConnectionManager;
import dao.CrecaDAO;

public class TestGetAllCrecas {

	public static void main(String[] args) {
		Connection con = null;
		ArrayList<Creca> crecaList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	CrecaDAO crecaDao = new CrecaDAO(con);
            
            //テスト対象メソッドの呼び出し
        	crecaList = crecaDao.getAllCrecas();
			
			//結果の確認 
			for(Creca cr : crecaList) {
				System.out.print(cr.getCrecaCompId() + ":");
				System.out.println(cr.getCrecaCompName());
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
