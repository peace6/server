package kr.ac.konkuk.dto;

import org.springframework.stereotype.*;

@Component
public class UserInfoDto {
	private String userCode;
	private String userEmail;
	private String userPassword;
	private String userName;
	private String userGender;
	private String userAge;
	private String userImage;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserAge() {
		return userAge;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
	

}
