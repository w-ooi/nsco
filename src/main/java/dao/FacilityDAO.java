package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Facility;

public class FacilityDAO {
	private Connection con;

	public FacilityDAO(Connection con) {
		this.con = con;
	}

	public Facility getFacility(int facilityCode) throws SQLException {
		Facility facility = null;
		PreparedStatement st = null;

		try {
			// PreparedStatementの取得
			st = con.prepareStatement("SELECT * FROM facility where facility_code=?");
			st.setInt(1, facilityCode);

			// SQL文を発行
			ResultSet rs = st.executeQuery();

			// 結果を参照
			if (rs.next()) {
				String name = rs.getString("facility_name");

				facility = new Facility(facilityCode, name);
			}
		} finally {
			// リソースの解放
			if (st != null) {
				st.close();
			}
		}

		// 施設を返却
		return facility;
	}

	public ArrayList<Facility> getAllFacilities() throws SQLException {
		ArrayList<Facility> list = new ArrayList<Facility>();
		PreparedStatement st = null;

		try {
			// PreparedStatementの取得
			st = con.prepareStatement("SELECT * FROM facility");

			// SQL文を発行
			ResultSet rs = st.executeQuery();

			// 結果を参照
			while (rs.next()) {
				int code = rs.getInt("facility_code");
				String name = rs.getString("facility_name");

				Facility facility = new Facility(code, name);
				list.add(facility);
			}
		} finally {
			// リソースの解放
			if (st != null) {
				st.close();
			}
		}

		// リストを返却
		return list;
	}
}
