package user.model;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.mindrot.jbcrypt.BCrypt;

// 목적 : 연동된 데이터베이스로부터 얻어온 튜플(레코드)=행 을 자바에서 객체로 다루고 정보를 읽기 위함 
// ㄴ VO (Value Object)
// ㄴ Read Only (public getter 제공, setter는 제공 X)
public class User {

	private String username;
	private String password;
	private String email;
	private String name;
	private LocalDate birth;
	private int telecom;
	private String gender;
	private String country;
	private String phone;
	private boolean agree;
	private Timestamp regDate;
	private Timestamp modDate;
	
	public User(String username, String password, String email, String name, LocalDate birth, int telecom,
			String gender, String country, String phone, boolean agree, Timestamp regDate, Timestamp modDate) {
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
		this.regDate = regDate;
		this.modDate = modDate;
	}

	public String getUsername() {
		return username;
	}

	public boolean checkPassword(String password) {
		boolean isChecked = true;
		try {
			isChecked = BCrypt.checkpw(password, this.password);
		} catch (Exception e) {
			System.err.println("암호화되지 않은 값이 저장되어 있습니다.");
		}
		return isChecked;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public int getTelecom() {
		return telecom;
	}

	public String getGender() {
		return gender;
	}

	public String getCountry() {
		return country;
	}

	public String getPhone() {
		return phone;
	}

	public boolean isAgree() {
		return agree;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}
	
	@Override
	public String toString() {
		return String.format("%s \n%s \n%s \n%s \n%d \n%s \n%s \n%s \n%b \n%s \n%s", 
				username, email, name, birth, telecom, gender, country, phone, agree, regDate, modDate);
	}
}
