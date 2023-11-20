package action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Creca;
import dao.ConnectionManager;
import dao.CrecaDAO;

public class ViewRegistrationPageAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "error.jsp";
		Connection con = null;
		List<Creca> crecaList = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            // DAOクラスをインスタンス化
        	CrecaDAO crecaDao = new CrecaDAO(con);
	
			//検索項目用
        	crecaList = crecaDao.getAllCrecas();
			
			HttpSession session = request.getSession();
			session.setAttribute("crecaList", crecaList);
			
			nextPage = "registrationMember.jsp";
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
