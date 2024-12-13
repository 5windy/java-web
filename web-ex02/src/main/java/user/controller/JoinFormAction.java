package user.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.User;
import user.model.UserDao;
import user.model.UserRequestDto;

import java.io.IOException;

/**
 * Servlet implementation class JoinFormAction
 */
public class JoinFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinFormAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String telecom = request.getParameter("telecom");
		String gender = request.getParameter("gender");
		String country = request.getParameter("country");
		String phone = request.getParameter("phone");
		String agree = request.getParameter("agree");

		// 사용자의 입력한 정보를 서버로 전달하여
		// 회원가입 처리 (DB에 INSERT : Transaction)

		// 생성할 객체는 UserRequestDto 
		UserRequestDto userDto = new UserRequestDto(username, password, email, name, birth, telecom, gender, country, phone, agree);

		UserDao userDao = UserDao.getInstance();

		boolean isValid = true;

		// 1) 중복 아이디 검사 findUserByUsername()
		User user = null;
		user = userDao.findUserByUsername(username);

		if(user != null) {
			isValid = false;
			System.err.println("회원 아이디 중복 발생");	
		}

		// 2) 중복 이메일 검사 findUserByEmail()
		user = null;
		user = userDao.findUserByEmail(email);

		if(user != null) {
			isValid = false;
			System.err.println("회원 이메일 중복 발생");	
		}

		// 3) 중복 연락처 검사 findUserByPhone()
		user = null;
		user = userDao.findUserByPhone(phone);

		if(user != null) {
			isValid = false;
			System.err.println("회원 연락처 중복 발생");	
		}

		// 중복이 확인되면 -> joinForm.jsp 페이지로 이동 
		// ㄴ 기존에 입력한 값이 폼에 다시 보이도록 처리
		// ㄴ JSP 내장 객체 중, request 객체 활용
		// ㄴ page < request < session < application 
		
		HttpSession session = request.getSession();
		
		if(!isValid) {
			System.err.println("joinForm으로 되돌아갑니다...");
			
			session.setAttribute("userData", userDto);
			// request.getRequestDispatcher("/joinForm.jsp").forward(request, response);
			// 주소의 변화가 없음
			response.sendRedirect("/joinForm.jsp");
			
		} else {
			System.out.println("loginForm으로 이동합니다.");
			
			session.removeAttribute("userData");
			
			// 통과하면 -> loginForm.jsp 페이지로 이동
			userDao.createUser(userDto);
			response.sendRedirect("/loginForm.jsp");
			// 단순 페이지 이동처리를 할 경우, request 객체를 들고 가지 못함
		}
	}

}
