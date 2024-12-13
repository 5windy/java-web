package user.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// DTO : Data Transfer Object 
// ㄴ 데이터 교환(처리용) 객체 
// ㄴ Request Dto는 사용자단에 요청값으로 넘어온 데이터를 교환하는 용도

public class UserRequestDto {

	private String username;
	private String password;
	private String email;
	private String name;
	private Date birth;
	private int telecom;
	private String gender;
	private String country;
	private String phone;
	private boolean agree;
	private Timestamp regDate;
	private Timestamp modDate;

	public UserRequestDto() {

	}

	public UserRequestDto(String username, String password, String name, Date birth, int telecom, String gender,
			String country, String phone, boolean agree) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.telecom = telecom;
		this.gender = gender;
		this.country = country;
		this.phone = phone;
		this.agree = agree;
	}

	public UserRequestDto(String username, String password, String email, String name, Date birth, int telecom,
			String gender, String country, String phone, boolean agree) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.birth = birth;
		this.telecom = telecom;
		this.gender = gender;
		this.country = country;
		this.phone = phone;
		this.agree = agree;
	}
	
	public UserRequestDto(String username, String password, String email, String name, String birth, String telecom, String gender, String country, String phone, String agree) {
		this.username = username;
		this.password = password;
		this.email = email.equals("") ? null : email;
		this.name = name;
		
//		int year = Integer.parseInt(birth.substring(0, 4));
//		int month = Integer.parseInt(birth.substring(4, 6));
//		int day = Integer.parseInt(birth.substring(6, 8));
//		this.birth = new Date(year-1900, month+1, day);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			java.util.Date date = sdf.parse(birth);
			this.birth = new Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.telecom = Integer.parseInt(telecom);
		this.gender = gender;
		this.country = country;
		this.phone = phone;
		this.agree = agree.equals("on");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public int getTelecom() {
		return telecom;
	}

	public void setTelecom(int telecom) {
		this.telecom = telecom;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isAgree() {
		return agree;
	}

	public void setAgree(boolean agree) {
		this.agree = agree;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}

	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}

}
