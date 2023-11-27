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
import orgex.NSCOException;

public class RegistrationMemberAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws NSCOException {
		String nextPage = "error.jsp";
		Connection con = null;
		
		String nameSei = request.getParameter("nameSei");
		String nameMei = request.getParameter("nameMei");
		String kanaSei = request.getParameter("kanaSei");
		String kanaMei = request.getParameter("kanaMei");
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickName");
		String password = request.getParameter("password");
		int crecaCompId = Integer.parseInt(request.getParameter("crecaCompId"));
		String crecaNo = request.getParameter("crecaNo");
		String creacaExpiration = request.getParameter("creacaExpiration");
		
		try {
			//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            // DAOクラスをインスタンス化
        	MemberDAO memberDao = new MemberDAO(con);
        	CrecaDAO crecaDao = new CrecaDAO(con);
			
			//ニックネーム重複チェック
			boolean resultNickname = memberDao.checkDuplicateNickname(nickname);
			
			if(!resultNickname) {
				nextPage = "registrationMember.jsp";
				request.getSession().setAttribute("registrationNickNameMessage", "ニックネームが重複しています<br>別のニックネームを登録してください");
				nickname = "";
			}

			//メールアドレス重複チェック
			boolean resultEmail = memberDao.checkDuplicateEmail(email);
			
			if(!resultEmail) {
				nextPage = "registrationMember.jsp";
				request.getSession().setAttribute("registrationEmailMessage", "メールアドレスが重複しています<br>別のメールアドレスを登録してください");
				email = "";
			}

			if(resultNickname && resultEmail) {
				nextPage = "confirmRegistrationMember.jsp";
			}
			
			//クレカ情報取得
			Creca creca = crecaDao.getCreca(crecaCompId);

			Member member = new Member(null, nameSei, nameMei, kanaSei, kanaMei, email,
					nickname, password, creca, crecaNo, creacaExpiration); 
			request.getSession().setAttribute("registrationMember", member);
		}catch (SQLException e) {
			throw new NSCOException(e.getMessage());
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
