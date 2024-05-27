package Model;

public class user {
	private String fullName;
	public user(String fullName, String phoneNumber, String passWord, String rePass, boolean is_approved) {
		super();
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.passWord = passWord;
		this.rePass = rePass;
		this.is_approved = is_approved;
	}
	private String phoneNumber;
	
	private String gender;
	private String birth;
	private String passWord;
	private String rePass;
	private boolean is_approved;
	public String getRePass() {
		return rePass;
	}

	public void setRePass(String rePass) {
		this.rePass = rePass;
	}

	public boolean isIs_approved() {
		return is_approved;
	}

	public void setIs_approved(boolean is_approved) {
		this.is_approved = is_approved;
	}

	public user() {
		super();
	}
	
	public user(String phoneNumber, String fullName, String gender, String birth, String passWord) {
		super();
		this.phoneNumber = phoneNumber;
		this.fullName = fullName;
		this.gender = gender;
		this.birth = birth;
		this.passWord = passWord;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	@Override
	public String toString() {
		return "user [phoneNumber=" + phoneNumber + ", fullName=" + fullName + ", gender=" + gender + ", birth=" + birth
				+ ", passWord=" + passWord + "]";
	}
	
	

}