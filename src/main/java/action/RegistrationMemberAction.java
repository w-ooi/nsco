package action;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Creca;
import beans.Member;
import dao.ConnectionManager;
import dao.CrecaDAO;
import dao.MemberDAO;

public class RegistrationMemberAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "error.jsp";
		Connection con = null;
		
		String nameSei = request.getParameter("nameSei");
		String nameMei = request.getParameter("nameMei");
		String kanaSei = request.getParameter("kanaSei");
		String kanaMei = request.getParameter("kanaMei");
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		int crecaCompId = Integer.parseInt("crecaCompId");
		String crecaNo = request.getParameter("crecaNo");
		String creacaExpiration = request.getParameter("creacaExpiration");
		
		try {
			//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            // DAOクラスをインスタンス化
        	MemberDAO memberDao = new MemberDAO(con);
        	CrecaDAO crecaDao = new CrecaDAO(con);
			
			//ニックネーム重複チェック
			boolean result = memberDao.checkDuplicateNickname(nickname);
			
			if(result) {
				nextPage = "confirmRegistration.jsp";
			}else {
				nextPage = "registrationMember.jsp";
				request.getSession().setAttribute("registrationMessage", "ニックネームが重複しています<br>別のニックネームを登録してください");
				nickname = "";
			}
			
			//クレカ情報取得
			Creca creca = crecaDao.getCreca(crecaCompId);

			Member member = new Member(null, nameSei, nameMei, kanaSei, kanaMei, email,
					nickname, password, creca, crecaNo, creacaExpiration); 
			request.getSession().setAttribute("registrationMember", member);
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
		
		return nextPage;
	}

}
