package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Creca;
import beans.Member;

public class MemberDAO {
	private Connection con;

	public MemberDAO(Connection con) {
		this.con = con;
	}

	public Member getMember(String memberNo) throws SQLException {
		Member member = null;
		PreparedStatement st = null;

		try {
			// PreparedStatementの取得
			st = con.prepareStatement("SELECT * FROM member where member_no=?");
			st.setString(1, memberNo);

			// SQL文を発行
			ResultSet rs = st.executeQuery();

			CrecaDAO crecaDao = new CrecaDAO(con);

			// 結果を参照
			if (rs.next()) {
				String nameSei = rs.getString("name_sei");
				String nameMei = rs.getString("name_mei");
				String kanaSei = rs.getString("kana_sei");
				String kanaMei = rs.getString("kana_mei");
				String email = rs.getString("email");
				String nickname = rs.getString("nickname");
				String password = rs.getString("password");
				int crecaCompId = rs.getInt("creca_comp_id");
				Creca creca = crecaDao.getCreca(crecaCompId);
				String crecaNo = rs.getString("creca_no");
				String crecaExpiration = rs.getString("creca_expiration");

				member = new Member(memberNo,nameSei,nameMei,kanaSei,kanaMei,email,nickname,password,creca,crecaNo,crecaExpiration);
			}
		} finally {
			// リソースの解放
			if (st != null) {
				st.close();
			}
		}

		// 会員を返却
		return member;
	}

	public ArrayList<Member> getAllMembers() throws SQLException {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement st = null;

		try {
			// PreparedStatementの取得
			st = con.prepareStatement("SELECT * FROM member");

			// SQL文を発行
			ResultSet rs = st.executeQuery();

			CrecaDAO crecaDao = new CrecaDAO(con);

			// 結果を参照
			while (rs.next()) {
				String memberNo = rs.getString("member_no");
				String nameSei = rs.getString("name_sei");
				String nameMei = rs.getString("name_mei");
				String kanaSei = rs.getString("kana_sei");
				String kanaMei = rs.getString("kana_mei");
				String email = rs.getString("email");
				String nickname = rs.getString("nickname");
				String password = rs.getString("password");
				int crecaCompId = rs.getInt("creca_comp_id");
				Creca creca = crecaDao.getCreca(crecaCompId);
				String crecaNo = rs.getString("creca_no");
				String crecaExpiration = rs.getString("creca_expiration");

				Member member = new Member(memberNo,nameSei,nameMei,kanaSei,kanaMei,email,nickname,password,creca,crecaNo,crecaExpiration);
				list.add(member);
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

	public Member getMemberById(String memberNo, String password) throws SQLException {
		Member member = null;
		PreparedStatement st = null;

		try {
			// PreparedStatementの取得
			st = con.prepareStatement("SELECT * FROM member where member_no=? and password=?");
			st.setString(1, memberNo);
			st.setString(2, password);
			
			// SQL文を発行
			ResultSet rs = st.executeQuery();

			CrecaDAO crecaDao = new CrecaDAO(con);

			// 結果を参照
			if (rs.next()) {
				String nameSei = rs.getString("name_sei");
				String nameMei = rs.getString("name_mei");
				String kanaSei = rs.getString("kana_sei");
				String kanaMei = rs.getString("kana_mei");
				String email = rs.getString("email");
				String nickname = rs.getString("nickname");
				int crecaCompId = rs.getInt("creca_comp_id");
				Creca creca = crecaDao.getCreca(crecaCompId);
				String crecaNo = rs.getString("creca_no");
				String crecaExpiration = rs.getString("creca_expiration");

				member = new Member(memberNo,nameSei,nameMei,kanaSei,kanaMei,email,nickname,password,creca,crecaNo,crecaExpiration);
			}
		} finally {
			// リソースの解放
			if (st != null) {
				st.close();
			}
		}

		// 会員を返却
		return member;
	}
}
