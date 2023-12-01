package test.scheduledao;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Instructor;
import beans.Lesson;
import beans.Member;
import beans.Schedule;
import beans.TimeFrame;
import dao.ConnectionManager;
import dao.InstructorDAO;
import dao.LessonDAO;
import dao.ScheduleDAO;
import dao.TimeFrameDAO;

public class TestInsertSchedule {

	public static void main(String[] args) {
		Connection con = null;
		Member member = null;
		
		try {
        	//データベース接続情報を取得
        	con = ConnectionManager.getConnection();

            //DAOクラスをインスタンス化
        	ScheduleDAO scheduleDao = new ScheduleDAO(con);
        	LessonDAO lessonDao = new LessonDAO(con);
        	TimeFrameDAO timeFrameDao = new TimeFrameDAO(con);
        	InstructorDAO instructorDao = new InstructorDAO(con);

    		Lesson lesson = null;
    		TimeFrame timeFrame = null;
    		Instructor instructor = null;
    		Schedule schedule = null;
        	
    		int result = 0;
        	
        	//正常系
    		lesson = lessonDao.getLesson(1);
    		timeFrame = timeFrameDao.getTimeFrame(1);
    		instructor = instructorDao.getInstructor(5);
    		schedule = new Schedule(0, lesson, "2023-12-20", timeFrame, instructor, "xxx-xxx-9999", "pass9", 0);
        	
        	result = scheduleDao.insertSchedule(schedule);
        	System.out.println("期待する結果:1");
			System.out.println("実行結果:" + result);
			System.out.println();
        	
            //異常系(存在しない日付)
			/*
			schedule = new Schedule(0, lesson, "2023-12-32", timeFrame, instructor, "xxx-xxx-9999", "pass9", 0);
        	result = scheduleDao.insertSchedule(schedule);
        	System.out.println("期待する結果:例外");
        	*/

        	//異常系(配信IDがnull)
    		/*
    		schedule = new Schedule(0, lesson, "2023-12-20", timeFrame, instructor, null, "pass9", 0);
        	
        	result = scheduleDao.insertSchedule(schedule);
        	System.out.println("期待する結果:例外");
    		 */
    		
        	//異常系(配信パスコードがnull)
    		/*
    		schedule = new Schedule(0, lesson, "2023-12-20", timeFrame, instructor, "xxx-xxx-9999", null, 0);
        	
        	result = scheduleDao.insertSchedule(schedule);
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
