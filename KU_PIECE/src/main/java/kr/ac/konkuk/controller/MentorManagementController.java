package kr.ac.konkuk.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	@RequestMapping(value="/uploadImage2",method = RequestMethod.POST)
    public @ResponseBody String uploadImage2(@RequestParam("imageValue") String imageValue,HttpServletRequest request)
    {
        try
        {
            //This will decode the String which is encoded by using Base64 class
            byte[] imageByte=Base64.decodeBase64(imageValue);
            String directory=request.getSession().getServletContext().getRealPath("/")+"images/sample.jpg";
            System.out.println("directory의경로:"+directory);
          //  String directory=servletContext.getRealPath("/")+"images/sample.jpg";

            //new FileOutputStream(directory).write(imageByte);
            return "success ";
        }
        catch(Exception e)
        {
            return "error = "+e;
        }

    }
	
	@RequestMapping(value="/test", method=RequestMethod.POST ,produces = "application/json")
	  @ResponseBody
	  public String addUser(@RequestBody ContentDto contentDto) {
		System.out.println(contentDto);
		byte[] decoded =Base64.decodeBase64(contentDto.getContentImg());
		System.out.println("테스트 이미지 출력"+new String(decoded));
		
	    return "{\"success\":1}";
	  }
	@RequestMapping(value="/test2", method=RequestMethod.POST ,produces = "application/json")
	  @ResponseBody
	  public String addUser2(@RequestBody ContentDto contentDto,HttpServletRequest request) {
		System.out.println("hi");
		try
        {
           System.out.println("toString:"+contentDto.toString());
			String img=contentDto.getContentImg();
			
			//byte[] encoded = Base64.encodeBase64(img.getBytes());
            byte[] imageByte=Base64.decodeBase64(img.getBytes());
            System.out.println("decoder:"+imageByte);
            String directory=request.getSession().getServletContext().getRealPath("/")+"images\\sample2.png";
            System.out.println("directory위치"+directory);
            new FileOutputStream(directory).write(imageByte);
            
            return "{\"success\":1}";
        }
        catch(Exception e)
        {
            return "error = "+e;
        }
		
	}

}
