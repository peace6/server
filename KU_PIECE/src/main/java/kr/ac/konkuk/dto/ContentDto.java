package kr.ac.konkuk.dto;

public class ContentDto {
	private String mentorId;
	private String contentTitle;
	private String contentImg;//base64 encoded;
	public String getMentorId() {
		return mentorId;
	}
	public void setMentorId(String mentorId) {
		this.mentorId = mentorId;
	}
	public String getContentTitle() {
		return contentTitle;
	}
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}
	public String getContentImg() {
		return contentImg;
	}
	public void setContentImg(String contentImg) {
		this.contentImg = contentImg;
	}
	@Override
	public String toString() {
		return "ContentDto [mentorId=" + mentorId + ", contentTitle=" + contentTitle + ", contentImg=" + contentImg
				+ "]";
	}
	
	
	

}
