package test.facilitydao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Facility;
import dao.ConnectionManager;
import dao.FacilityDAO;

public class TestGetFacility {

	public static void main(String[] args) {
		Connection con = null;
		Facility facility = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	FacilityDAO facilityDao = new FacilityDAO(con);
            
            //正常系
        	facility = facilityDao.getFacility(0);
        	System.out.println("期待する結果:本社");
			System.out.println("実行結果:" + facility.getFacilityName());
			System.out.println();
			
            //異常系
			facility = facilityDao.getFacility(9);
        	System.out.println("期待する結果:null");
			System.out.println("実行結果:" + facility);
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
