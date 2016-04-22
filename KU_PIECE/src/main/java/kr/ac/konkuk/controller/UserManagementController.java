package kr.ac.konkuk.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.konkuk.dto.MentorInfoDto;
import kr.ac.konkuk.dto.UserInfoDto;
import kr.ac.konkuk.service.UserService;
@Controller
public class UserManagementController {
	private static final Logger logger = LoggerFactory.getLogger(MentorManagementController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/register.do",method=RequestMethod.POST)
	public void userRegister(HttpServletResponse response,UserInfoDto userInfo) throws Exception{
		logger.info("Controller의 userRegister메소드:"+userInfo.toString());
		//String result=mentorService.join(mentorInfo);
		String result=userService.join(userInfo);
		System.out.println("result="+result);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		PrintWriter writer = response.getWriter();
		writer.write(jsonObject.toString());
		writer.flush();
		writer.close();		
	}
	
	@RequestMapping(value="/user/login.do", method =RequestMethod.POST)
	public void userLogin(UserInfoDto userInfo, HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
		String url = request.getRequestURL().toString();
		System.out.println("url매핑주소는:"+url);
		System.out.println("Controller의 login메소드 userInfo"+userInfo.toString());
		String result = userService.login(userInfo.getUserEmail(), userInfo.getUserPassword());
		
		System.out.println("Controller의 login메소드 result값"+result);
		if(result.equals("success")) {
			session.setAttribute("sessionUserEmail", userInfo.getUserEmail());
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		response.setContentType("application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");

		PrintWriter writer = response.getWriter();
		writer.write(jsonObject.toString());
		writer.flush();
		writer.close();
	}
	


}
