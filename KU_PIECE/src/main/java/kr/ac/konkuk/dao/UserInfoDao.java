package kr.ac.konkuk.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kr.ac.konkuk.dto.UserInfoDto;

@Component
public class UserInfoDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public UserInfoDto selectByUserCode(String userCode) {
		List<UserInfoDto > list = jdbcTemplate.query("select * from user_info_tb where userCode=?", new Object[] {userCode}, new RowMapper<UserInfoDto >() {
			@Override
			public UserInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserInfoDto  userInfo  = new UserInfoDto ();
				userInfo.setUserCode(rs.getString("userCode"));
				userInfo.setUserEmail(rs.getString("userEmail"));
				userInfo.setUserPassword(rs.getString("userPassword"));
				userInfo.setUserName(rs.getString("userName"));
				userInfo.setUserGender(rs.getString("userGender"));
				userInfo.setUserAge(rs.getString("userAge"));
				userInfo.setUserImage(rs.getString("userImage"));
				return userInfo;
			}
		});
		if(list.size() != 0) return list.get(0);
		else return null;
	}
	public UserInfoDto selectByUserEmail(String userEmail) {
		List<UserInfoDto > list = jdbcTemplate.query("select * from user_info_tb where userEmail=?", new Object[] {userEmail}, new RowMapper<UserInfoDto >() {
			@Override
			public UserInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserInfoDto  userInfo  = new UserInfoDto ();
				userInfo.setUserCode(rs.getString("userCode"));
				userInfo.setUserEmail(rs.getString("userEmail"));
				userInfo.setUserPassword(rs.getString("userPassword"));
				userInfo.setUserName(rs.getString("userName"));
				userInfo.setUserGender(rs.getString("userGender"));
				userInfo.setUserAge(rs.getString("userAge"));
				userInfo.setUserImage(rs.getString("userImage"));
				return userInfo;
			}
		});
		if(list.size() != 0) return list.get(0);
		else return null;
	}
	
	public String insert(UserInfoDto userInfo) {
		String userCode=userCodeGenerator();
		System.out.println("insert구문까지여기까지오는건가?");
		/*int rows = jdbcTemplate.update("insert into user_info_tb values "
				+ "(?,?,?,?,?,?,?,?)", userCode,userInfo.getuserEmail(),userInfo.getuserPassword(),
				userInfo.getuserName(),userInfo.getuserTel(),userInfo.getuserAddress(),
				userInfo.getuserIntro(),userInfo.getuserImage());*/
		int rows = jdbcTemplate.update("insert into user_info_tb"
				+ "(userCode,userEmail,userPassword,userName,userGender,userAge,userImage)"
				+ " values(?,?,?,?,?,?,?)",userCode,userInfo.getUserEmail(),userInfo.getUserPassword(),
				userInfo.getUserName(),userInfo.getUserGender(),userInfo.getUserAge(),
				userInfo.getUserImage());
		 
		if(rows==1) return userInfo.getUserCode();
		else return null;
	}
	public String userCodeGenerator(){
	      SimpleDateFormat formatter = new SimpleDateFormat ( "yyyyMMdd", Locale.KOREA );
	      Date currentTime = new Date( );
	      String dTime = formatter.format ( currentTime );
	      Random randomGenerator = new Random(); 
	      return dTime+"UI"+randomGenerator.nextInt(1000)+System.currentTimeMillis();
	}
	public static void main(String args[]){
		UserInfoDao dao= new UserInfoDao();
		UserInfoDto userInfo = new UserInfoDto();
		userInfo.setUserCode("userCode");
		userInfo.setUserEmail("userEmail");
		userInfo.setUserPassword("userPassword");
		userInfo.setUserName("userName");
		userInfo.setUserGender("userGender");
		userInfo.setUserAge("userAge");
		
		dao.insert(userInfo);
		
	}
	

}

