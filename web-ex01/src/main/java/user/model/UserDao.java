package user.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import util.DBManager;

// DAO : Data Access Object 
// ㄴ 데이터베이스에 접근할 수 있도록 만든 객체
public class UserDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 단일 인스턴스로 생성
	private UserDao() {
	}

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}

	// User 객체에 대한 CRUD 기능을 정의

	// Create
	public void createUser(UserRequestDto userDto) {
		conn = DBManager.getConnection();
		
		String sql = "INSERT INTO users(username, password, email, name, birth, telecom, gender, country, phone, agree) VALUES(?,?,?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getUsername());
			
			String rawPassword = userDto.getPassword();
			String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
			
			pstmt.setString(2, hashedPassword);
			pstmt.setString(3, userDto.getEmail());
			pstmt.setString(4, userDto.getName());
			pstmt.setDate(5, userDto.getBirth());
			pstmt.setInt(6, userDto.getTelecom());
			pstmt.setString(7, userDto.getGender());
			pstmt.setString(8, userDto.getCountry());
			pstmt.setString(9, userDto.getPhone());
			pstmt.setBoolean(10, userDto.isAgree());
			
			pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// Read
	public List<User> findUserAll() {
		ArrayList<User> list = new ArrayList<>();

		conn = DBManager.getConnection();

		if (conn != null) {
			String sql = "SELECT * FROM users";

			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					String username = rs.getString(1);
					String password = rs.getString(2);
					String email = rs.getString(3);
					String name = rs.getString(4);

					// User VO 객체 생성 시 필요한 타입으로의 변환
					Date date = rs.getDate(5);
					LocalDate birth = date.toLocalDate();

					int telecom = rs.getInt(6);
					String gender = rs.getString(7);
					String country = rs.getString(8);
					String phone = rs.getString(9);
					boolean agree = rs.getBoolean(10);
					Timestamp regDate = rs.getTimestamp(11);
					Timestamp modDate = rs.getTimestamp(12);

					User user = new User(username, password, email, name, birth, telecom, gender, country, phone, agree,
							regDate, modDate);

					list.add(user);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public User findUserByUsername(String username) {
		User user = null;

		// 1. DB 연동
		conn = DBManager.getConnection();

		String sql = "SELECT * FROM users WHERE username=?";

		// 2.
		// sql 구문을 태운, pstmt 객체의
		try {
			pstmt = conn.prepareStatement(sql);
			// setter를 호출해서 columnIndex와 맵핑할 값을 인자로 넘겨서 처리
			pstmt.setString(1, username);

			// 3. 준비된 구문을 실행 (결과 테이블이 존재)
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String password = rs.getString(2);
				String email = rs.getString(3);
				String name = rs.getString(4);
				Date date = rs.getDate(5);
				LocalDate birth = date.toLocalDate();
				int telecom = rs.getInt(6);
				String gender = rs.getString(7);
				String country = rs.getString(8);
				String phone = rs.getString(9);
				boolean agree = rs.getBoolean(10);
				Timestamp regDate = rs.getTimestamp(11);
				Timestamp modDate = rs.getTimestamp(12);

				user = new User(username, password, email, name, birth, telecom, gender, country, phone, agree, regDate,
						modDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}
	
	public User findUserByPhone(String phone) {
		User user = null;

		conn = DBManager.getConnection();

		String sql = "SELECT * FROM users WHERE phone=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String username = rs.getString(1);
				String password = rs.getString(2);
				String email = rs.getString(3);
				String name = rs.getString(4);
				Date date = rs.getDate(5);
				LocalDate birth = date.toLocalDate();
				int telecom = rs.getInt(6);
				String gender = rs.getString(7);
				String country = rs.getString(8);
				boolean agree = rs.getBoolean(10);
				Timestamp regDate = rs.getTimestamp(11);
				Timestamp modDate = rs.getTimestamp(12);

				user = new User(username, password, email, name, birth, telecom, gender, country, phone, agree, regDate,
						modDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}
	
	public User findUserByEmail(String email) {
		User user = null;

		// 1. DB 연동
		conn = DBManager.getConnection();

		String sql = "SELECT * FROM users WHERE email=?";

		// 2.
		// sql 구문을 태운, pstmt 객체의
		try {
			pstmt = conn.prepareStatement(sql);
			// setter를 호출해서 columnIndex와 맵핑할 값을 인자로 넘겨서 처리
			pstmt.setString(1, email);

			// 3. 준비된 구문을 실행 (결과 테이블이 존재)
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String username = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString(4);
				Date date = rs.getDate(5);
				LocalDate birth = date.toLocalDate();
				int telecom = rs.getInt(6);
				String gender = rs.getString(7);
				String country = rs.getString(8);
				String phone = rs.getString(9);
				boolean agree = rs.getBoolean(10);
				Timestamp regDate = rs.getTimestamp(11);
				Timestamp modDate = rs.getTimestamp(12);

				user = new User(username, password, email, name, birth, telecom, gender, country, phone, agree, regDate,
						modDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}
	
	// Update
	public User updateUser(UserRequestDto userDto) {
		conn = DBManager.getConnection();
		
		if(conn != null) {
			String sql = "UPDATE users SET country=?, telecom=?";
			if(userDto.getPassword() != null)
				sql += ", password=?";
			if(userDto.getEmail() != null)
				sql += ", email=?";
			if(userDto.getPhone() != null)	
				sql += ", phone=?";
			sql += " WHERE username=?";
			
			try {
				int cnt = 0;
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(++ cnt, userDto.getCountry());
				pstmt.setInt(++ cnt, userDto.getTelecom());
				
				if(userDto.getPassword() != null) {
					String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
					pstmt.setString(++ cnt, hashedPassword);
				}
				if(userDto.getEmail() != null)
					pstmt.setString(++ cnt, userDto.getEmail());
				if(userDto.getPhone() != null)	
					pstmt.setString(++ cnt, userDto.getPhone());
				
				pstmt.setString(++ cnt, userDto.getUsername());
				
				System.out.println("pstmt : " + pstmt);
				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
		
		return findUserByUsername(userDto.getUsername());
	}
	
	// Delete
	public void deleteUserByUsername(String username) {
		conn = DBManager.getConnection();
		
		if(conn != null) {
			String sql = "DELETE FROM users WHERE username=?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, username);
				
				pstmt.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
}
