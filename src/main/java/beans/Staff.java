package beans;

//スタッフ
public class Staff {
	private int staffCode;		//スタッフコード
	private String staffName;	//スタッフ名
	private Facility facility;	//所属施設
	private String imageFile;	//イメージファイル
	private String loginId;		//ログインID
	private String password;	//パスワード

	public Staff() {
	}

	public Staff(int staffCode, String staffName, Facility facility, String imageFile, String loginId, String password) {
		this.staffCode = staffCode;
		this.staffName = staffName;
		this.facility = facility;
		this.imageFile = imageFile;
		this.loginId = loginId;
		this.password = password;
	}

	public int getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(int staffCode) {
		this.staffCode = staffCode;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
