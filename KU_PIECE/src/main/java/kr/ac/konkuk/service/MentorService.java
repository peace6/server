package kr.ac.konkuk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.ac.konkuk.dao.MentorInfoDao;
import kr.ac.konkuk.dto.ContentDto;
import kr.ac.konkuk.dto.MentorInfoDto;
@Component
public class MentorService {
	@Autowired
	private MentorInfoDao mentorInfoDao;
	
	public String join(MentorInfoDto mentorInfo) {
		return  mentorInfoDao.insert(mentorInfo);
	}
	public String emailCheck(String email){
		MentorInfoDto mentorInfo=mentorInfoDao.selectByMentorEmail(email);
		if(mentorInfo.getMentorEmail()==null){
			return "fail";
		}
		else{
			return "sucess";
		}
	}
	public String login(String mentorEmail,String mentorPassword ) {
		System.out.println("MentorServe의 login메소드"+mentorEmail+":"+mentorPassword);
		MentorInfoDto mentorInfo = mentorInfoDao.selectByMentorEmail(mentorEmail);
		
		if(mentorInfo  != null) {
			if(mentorInfo .getMentorPassword().equals(mentorPassword)) {
				return "success";
			} else {
				return "fail-mpassword";
			}
		} else {
			return "fail-mid";
		}
	}
	
	public String saveContent(ContentDto contentDto ) {
		
		String result = mentorInfoDao.saveContentDto(contentDto);
		
		if(result  != null) {
				return result;
		} 
		else {
			return "fail";
		}
	}

}
