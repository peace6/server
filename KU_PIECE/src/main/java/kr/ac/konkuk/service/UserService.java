package kr.ac.konkuk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.ac.konkuk.dao.UserInfoDao;
import kr.ac.konkuk.dto.UserInfoDto;

@Component
public class UserService {
	@Autowired
	private UserInfoDao userInfoDao;
	
	public String join(UserInfoDto userInfo){
		return userInfoDao.insert(userInfo);
	}
	public String login(String userEmail,String userPassword){
		UserInfoDto userInfo = userInfoDao.selectByUserEmail(userEmail);
/*		System.out.println("UserInfoDao의 login:"+userInfo.toString());*/
		
		if(userInfo  != null) {
			if(userInfo .getUserPassword().equals(userPassword)) {
				return "success";
			} else {
				return "fail-u-password";
			}
		} else {
			return "fail-u-email";
		}
	}
}
		

