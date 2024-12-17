package user.action;

import java.io.IOException;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.User;
import user.model.UserDao;

public class LoginFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			url = "/mypage";
		} else {
			// login form 으로 이동
			url = "/login";
		}

		response.sendRedirect(url);
	}

}
