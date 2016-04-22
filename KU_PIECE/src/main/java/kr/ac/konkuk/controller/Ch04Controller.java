package kr.ac.konkuk.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Ch04Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch04Controller.class);
	
	@RequestMapping("/ch04/content")
	public String content() {
		return "ch04/content";
	}
	
	@RequestMapping("/ch04/ppt")
	public String ppt() {
		return "ch04/ppt";
	}		
	
	@RequestMapping("/ch04/method1")
	public String method1(@RequestHeader("User-Agent") String userAgent) {
		logger.info("User-Agent: " + userAgent);
		return "ch04/content";
	}
	
	@RequestMapping("/ch04/method2")
	public String method2(HttpServletResponse response) {
		Cookie cookie1 = new Cookie("memberId", "white");
		Cookie cookie2 = new Cookie("loginStatus", "ok");
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		return "ch04/content";
	}
	
	@RequestMapping("/ch04/method3")
	public String method3(@CookieValue String memberId, @CookieValue("loginStatus") String status) {
		logger.info("memberId: " + memberId);
		logger.info("loginStatus: " + status);
		return "ch04/content";
	}	
}
