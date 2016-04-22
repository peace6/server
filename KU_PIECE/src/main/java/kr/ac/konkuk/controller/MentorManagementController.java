package kr.ac.konkuk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.konkuk.dto.ContentDto;
import kr.ac.konkuk.dto.MentorInfoDto;
import kr.ac.konkuk.service.MentorService;
import sun.misc.BASE64Decoder;
@Controller
public class MentorManagementController {
	private static final Logger logger = LoggerFactory.getLogger(MentorManagementController.class);
	
	@Autowired
	private MentorService mentorService;
	
	@RequestMapping(value="/mentor/register.do",method=RequestMethod.POST)
	public void mentorRegister(HttpServletResponse response,MentorInfoDto mentorInfo) throws Exception{
	
		logger.info(mentorInfo.toString());
		String result=mentorService.join(mentorInfo);
		System.out.println("result="+result);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(jsonObject.toString());
		writer.flush();
		writer.close();		
	}
	
	@RequestMapping(value="/mentor/login.do", method =RequestMethod.POST)
	public void mentorLogin(MentorInfoDto mentorInfo, HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
		String url = request.getRequestURL().toString();
		System.out.println("url매핑주소는:"+url);
		System.out.println("Controller의 login메소드 mentorInfo"+mentorInfo.toString());
		String result = mentorService.login(mentorInfo.getMentorEmail(), mentorInfo.getMentorPassword());
		
		System.out.println("Controller의 login메소드 result값"+result);
		if(result.equals("success")) {
			session.setAttribute("sessionMentorEmail", mentorInfo.getMentorEmail());
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(jsonObject.toString());
		writer.flush();
		writer.close();
	}
	@RequestMapping(value="mentor/emailCheck.do", method =RequestMethod.POST)
	public void mentorIdCheck(String email, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String url = request.getRequestURL().toString();
		String result = mentorService.emailCheck(email);
		
		System.out.println("Controller의 idcheck메소드 result값"+result);
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("result", result);
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(jsonObject.toString());
		writer.flush();
		writer.close();	
		
	}
	
	
	
	@RequestMapping(value="/mentor/uploadContent.do",method =RequestMethod.POST)
	@ResponseBody
	public String modify(HttpServletRequest request,@RequestBody List<Map<String, Object>> list) throws IOException {
		System.out.println("들어와라");
		logger.debug("modify");
		/*logger.debug("param : " + contentDto);*/
		BASE64Decoder decoder = new BASE64Decoder();
/*        byte[] decode = decoder.decodeBuffer(encode);
*/		/*ArrayList<ContentDto> contentDtoList = new ArrayList<ContentDto>();
		*/
		
		ContentDto dto = new ContentDto();
		for(Map<String, Object> m : list) {
			logger.debug("menu : " + m);
			
			dto.setMentorId(m.get("mentorId").toString());
			dto.setContentTitle(m.get("contentTitle").toString());
			byte[] decode = decoder.decodeBuffer(m.get("contentImg").toString());
			dto.setContentImg(decode.toString());
		}
		System.out.println(dto.toString());
		/*menuService.insertMenu(menuList);*/
		return "{}";
	}
	

}
