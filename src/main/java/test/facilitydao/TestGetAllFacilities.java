package test.facilitydao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Facility;
import dao.ConnectionManager;
import dao.FacilityDAO;

public class TestGetAllFacilities {

	public static void main(String[] args) {
		Connection con = null;
		ArrayList<Facility> facilityList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	FacilityDAO facilityDao = new FacilityDAO(con);
            
            //テスト対象メソッドの呼び出し
        	facilityList = facilityDao.getAllFacilities();
			
			//結果の確認 
			for(Facility fc : facilityList) {
				System.out.print(fc.getFacilityCode() + ":");
				System.out.println(fc.getFacilityName());
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
