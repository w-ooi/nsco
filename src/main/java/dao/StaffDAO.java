package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Facility;
import beans.Staff;

public class StaffDAO {
	private Connection con;

	public StaffDAO(Connection con) {
		this.con = con;
	}

	public Staff getStaff(int staffCode) throws SQLException {
		Staff staff = null;
		PreparedStatement st = null;

		try {
			// PreparedStatementの取得
			st = con.prepareStatement("SELECT * FROM staff where staff_code=?");
			st.setInt(1, staffCode);

			// SQL文を発行
			ResultSet rs = st.executeQuery();

			FacilityDAO facilityDao = new FacilityDAO(con);

			// 結果を参照
			if (rs.next()) {
				String staffName = rs.getString("staff_name");
				int facilityCode = rs.getInt("facility_code");
				Facility facility = facilityDao.getFacility(facilityCode);
				String imageFile = rs.getString("image_file");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");

				staff = new Staff(staffCode,staffName,facility,imageFile,loginId,password);
			}
		} finally {
			// リソースの解放
			if (st != null) {
				st.close();
			}
		}

		// スタッフを返却
		return staff;
	}

	public ArrayList<Staff> getAllStaff() throws SQLException {
		ArrayList<Staff> list = new ArrayList<Staff>();
		PreparedStatement st = null;

		try {
			// PreparedStatementの取得
			st = con.prepareStatement("SELECT * FROM staff");

			// SQL文を発行
			ResultSet rs = st.executeQuery();

			FacilityDAO facilityDao = new FacilityDAO(con);

			// 結果を参照
			while (rs.next()) {
				int staffCode = rs.getInt("staff_code");
				String staffName = rs.getString("staff_name");
				int facilityCode = rs.getInt("facility_code");
				Facility facility = facilityDao.getFacility(facilityCode);
				String imageFile = rs.getString("image_file");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");

				Staff staff = new Staff(staffCode,staffName,facility,imageFile,loginId,password);

				list.add(staff);
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
