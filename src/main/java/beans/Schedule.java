package beans;

//スケジュール
public class Schedule {
	private int scheduleCode;		//スケジュールコード
	private Lesson lesson;			//レッスン
	private String event_date;		//開催日
	private TimeFrame timeFrame;	//時間枠
	private Staff staff;			//スタッフ
	private String streamingId;		//配信ツールID
	private String streamingPass;	//配信ツールパスコード
	private int cancelFlag;			//中止フラグ

	public Schedule() {
	}

	public Schedule(int scheduleCode, Lesson lesson, String event_date, TimeFrame timeFrame, Staff staff,
			String streamingId, String streamingPass, int cancelFlag) {
		super();
		this.scheduleCode = scheduleCode;
		this.lesson = lesson;
		this.event_date = event_date;
		this.timeFrame = timeFrame;
		this.staff = staff;
		this.streamingId = streamingId;
		this.streamingPass = streamingPass;
		this.cancelFlag = cancelFlag;
	}

	public int getScheduleCode() {
		return scheduleCode;
	}

	public void setScheduleCode(int scheduleCode) {
		this.scheduleCode = scheduleCode;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public String getEvent_date() {
		return event_date;
	}

	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}

	public TimeFrame getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(TimeFrame timeFrame) {
		this.timeFrame = timeFrame;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getStreamingId() {
		return streamingId;
	}

	public void setStreamingId(String streamingId) {
		this.streamingId = streamingId;
	}

	public String getStreamingPass() {
		return streamingPass;
	}

	public void setStreamingPass(String streamingPass) {
		this.streamingPass = streamingPass;
	}

	public int getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(int cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
}
