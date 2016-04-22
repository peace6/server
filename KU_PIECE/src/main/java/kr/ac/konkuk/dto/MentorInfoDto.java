package kr.ac.konkuk.dto;

import org.springframework.stereotype.*;

@Component
public class MentorInfoDto {
	
	private String mentorCode;
	private String mentorEmail;
	private String mentorPassword;
	private String mentorName;
	private String mentorTel;
	private String mentorAddress;
	private String mentorIntro;
	private String mentorImage;
	
	public String getMentorCode() {
		return mentorCode;
	}
	public void setMentorCode(String mentorCode) {
		this.mentorCode = mentorCode;
	}
	public String getMentorEmail() {
		return mentorEmail;
	}
	public void setMentorEmail(String mentorEmail) {
		this.mentorEmail = mentorEmail;
	}
	public String getMentorPassword() {
		return mentorPassword;
	}
	public void setMentorPassword(String mentorPassword) {
		this.mentorPassword = mentorPassword;
	}
	public String getMentorName() {
		return mentorName;
	}
	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}
	public String getMentorTel() {
		return mentorTel;
	}
	public void setMentorTel(String mentorTel) {
		this.mentorTel = mentorTel;
	}
	public String getMentorAddress() {
		return mentorAddress;
	}
	public void setMentorAddress(String mentorAddress) {
		this.mentorAddress = mentorAddress;
	}
	public String getMentorIntro() {
		return mentorIntro;
	}
	public void setMentorIntro(String mentorIntro) {
		this.mentorIntro = mentorIntro;
	}
	public String getMentorImage() {
		return mentorImage;
	}
	public void setMentorImage(String mentorImage) {
		this.mentorImage = mentorImage;
	}
	@Override
	public String toString() {
		return "MentorInfo [mentorCode=" + mentorCode + ", mentorEmail=" + mentorEmail + ", mentorPassword="
				+ mentorPassword + ", mentorName=" + mentorName + ", mentorTel=" + mentorTel + ", mentorAddress="
				+ mentorAddress + ", mentorIntro=" + mentorIntro + ", mentorImage=" + mentorImage + "]";
	}

}
