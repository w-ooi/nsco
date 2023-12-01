package test.scheduledao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import beans.Member;
import dao.ConnectionManager;
import dao.ScheduleDAO;

public class TestGetOutputCsvMember {

	public static void main(String[] args) {
		Connection con = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ScheduleDAO scheduleDao = new ScheduleDAO(con);
        	List<Member> memberList = null;
        	
    		int result = 0;
        	
        	//正常系
    		memberList = scheduleDao.getOutputCsvMember("1");
        	
    		for(Member member:memberList) {
    			System.out.println(member.getNameSei() +" "+ member.getNameMei());
    		}
    		System.out.println("件数:" + memberList.size());
			System.out.println();
        	
            //異常系(スケジュール番号が存在しない)
    		memberList = scheduleDao.getOutputCsvMember("0");
        	
    		for(Member member:memberList) {
    			System.out.println(member.getNameSei() +" "+ member.getNameMei());
    		}
    		System.out.println("件数:" + memberList.size());
			System.out.println();

            //異常系(スケジュール番号がnull)
			/*
    		memberList = scheduleDao.getOutputCsvMember(null);
        	
    		System.out.println("期待する結果:例外");
    		*/
			
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
