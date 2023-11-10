package test.reservedao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Reserve;
import dao.ConnectionManager;
import dao.ReserveDAO;

public class TestGetAllReserves {

	public static void main(String[] args) {
		Connection con = null;
		ArrayList<Reserve> reserveList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ReserveDAO reserveDao = new ReserveDAO(con);
            
            //テスト対象メソッドの呼び出し
        	reserveList = reserveDao.getAllReserves();
			
			//結果の確認 
			for(Reserve rsv : reserveList) {
				System.out.print(rsv.getReserveCode() + ":");
				System.out.print(rsv.getLessonEvaluation() + ":");
				System.out.println(rsv.getInstructorEvaluation());
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
