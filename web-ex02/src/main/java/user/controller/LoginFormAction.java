package user.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.User;
import user.model.UserDao;

import java.io.IOException;

/**
 * Servlet implementation class LoginFormAction
 */
public class LoginFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginFormAction() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserDao userDao = UserDao.getInstance();
		User user = userDao.findUserByUsername(username);

		String url = "";

		if(user != null && user.checkPassword(password)) {
			// 내장 객체 중, session에 로그인 유저 정보를 저장 
			// mypage 로 이동
			// ㄴ ***님 환영합니다.
			HttpSession session = request.getSession();
			session.setAttribute("log", user);
			url = "/myPage.jsp";
		} else {
			// login form 으로 이동
			url = "/loginForm.jsp";
		}

		response.sendRedirect(url);
	}

}
