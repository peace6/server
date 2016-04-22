package kr.ac.konkuk.dao;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import kr.ac.konkuk.dto.*;

@Component
public class MentorInfoDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public MentorInfoDto selectByMentorCode(String mentorCode) {
		List<MentorInfoDto > list = jdbcTemplate.query("select * from mentorInfo_tb where mentorCode=?", new Object[] {mentorCode}, new RowMapper<MentorInfoDto >() {
			@Override
			public MentorInfoDto  mapRow(ResultSet rs, int rowNum) throws SQLException {
				MentorInfoDto  mentorInfo  = new MentorInfoDto ();
				mentorInfo.setMentorCode(rs.getString("mentorCode"));
				mentorInfo.setMentorEmail(rs.getString("mentorEmail"));
				mentorInfo.setMentorPassword(rs.getString("mentorPassword"));
				mentorInfo.setMentorName(rs.getString("mentorName"));
				mentorInfo.setMentorTel(rs.getString("mentorTel"));
				mentorInfo.setMentorAddress(rs.getString("mentorAddress"));
				mentorInfo.setMentorIntro(rs.getString("mentorIntro"));
				mentorInfo.setMentorImage(rs.getString("mentorImage"));
				return mentorInfo;
			}
		});
		if(list.size() != 0) return list.get(0);
		else return null;
	}
	public MentorInfoDto selectByMentorEmail(String mentorEmail) {
		List<MentorInfoDto > list = jdbcTemplate.query("select * from mentor_info_tb where mentorEmail=?", new Object[] {mentorEmail}, new RowMapper<MentorInfoDto >() {
			@Override
			public MentorInfoDto  mapRow(ResultSet rs, int rowNum) throws SQLException {
				MentorInfoDto  mentorInfo  = new MentorInfoDto ();
				mentorInfo.setMentorCode(rs.getString("mentorCode"));
				mentorInfo.setMentorEmail(rs.getString("mentorEmail"));
				mentorInfo.setMentorPassword(rs.getString("mentorPassword"));
				mentorInfo.setMentorName(rs.getString("mentorName"));
				mentorInfo.setMentorTel(rs.getString("mentorTel"));
				mentorInfo.setMentorAddress(rs.getString("mentorAddress"));
				mentorInfo.setMentorIntro(rs.getString("mentorIntro"));
				mentorInfo.setMentorImage(rs.getString("mentorImage"));
				return mentorInfo;
			}
		});
		if(list.size() != 0) return list.get(0);
		else return null;
	}
	
	public String insert(MentorInfoDto mentorInfo) {
		String mentorCode=mentorCodeGenerator();
		mentorInfo.setMentorCode(mentorCode);
		System.out.println("insert구문까지여기까지오는건가?");
		/*int rows = jdbcTemplate.update("insert into mentor_info_tb values "
				+ "(?,?,?,?,?,?,?,?)", mentorCode,mentorInfo.getMentorEmail(),mentorInfo.getMentorPassword(),
				mentorInfo.getMentorName(),mentorInfo.getMentorTel(),mentorInfo.getMentorAddress(),
				mentorInfo.getMentorIntro(),mentorInfo.getMentorImage());*/
		int rows = jdbcTemplate.update("insert into mentor_info_tb(mentorCode,mentorEmail,mentorPassword,mentorName,mentorTel,mentorAddress,mentorIntro,mentorImage)"
				+ " values(?,?,?,?,?,?,?,?)",mentorCode,mentorInfo.getMentorEmail(),mentorInfo.getMentorPassword(),
				mentorInfo.getMentorName(),mentorInfo.getMentorTel(),mentorInfo.getMentorAddress(),
				mentorInfo.getMentorIntro(),mentorInfo.getMentorImage());
		 
		if(rows==1) return mentorInfo.getMentorCode();
		else return null;
	}
	public String saveContentDto(ContentDto contentDto){
		String contentId2=contentIdGenerator();
		
		System.out.println("insert구문까지여기까지오는건가?");
		/*int rows = jdbcTemplate.update("insert into mentor_info_tb values "
				+ "(?,?,?,?,?,?,?,?)", mentorCode,mentorInfo.getMentorEmail(),mentorInfo.getMentorPassword(),
				mentorInfo.getMentorName(),mentorInfo.getMentorTel(),mentorInfo.getMentorAddress(),
				mentorInfo.getMentorIntro(),mentorInfo.getMentorImage());*/
		int rows = jdbcTemplate.update("insert into content_tb(contentId,mentorId,contentTitle,contentImg)"
				+ " values(?,?,?,?)",contentId2,contentDto.getMentorId(),contentDto.getContentTitle(),
				contentDto.getContentImg());
		 
		if(rows==1) return contentId2;
		else return null;
	}
	
	public String mentorCodeGenerator(){
	      SimpleDateFormat formatter = new SimpleDateFormat ( "yyyyMMdd", Locale.KOREA );
	      Date currentTime = new Date( );
	      String dTime = formatter.format ( currentTime );
	      Random randomGenerator = new Random(); 
	      return dTime+"MI"+randomGenerator.nextInt(1000)+System.currentTimeMillis();
	}
	public String contentIdGenerator(){
	      SimpleDateFormat formatter = new SimpleDateFormat ( "yyyyMMdd", Locale.KOREA );
	      Date currentTime = new Date( );
	      String dTime = formatter.format ( currentTime );
	      Random randomGenerator = new Random(); 
	      return dTime+"CI"+randomGenerator.nextInt(1000)+System.currentTimeMillis();
	}
	
	public static void main(String args[]){
		MentorInfoDao dao = new MentorInfoDao();
		MentorInfoDto mentorInfo= new MentorInfoDto();
		mentorInfo.setMentorCode("ttCode");
		mentorInfo.setMentorEmail("testEmail");
		mentorInfo.setMentorName("ttName");
		mentorInfo.setMentorTel("testTel");
		mentorInfo.setMentorImage("ttImage");
		dao.insert(mentorInfo);
		
	}
	

}

