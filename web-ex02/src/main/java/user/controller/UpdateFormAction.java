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
 * Servlet implementation class UpdateFormAction
 */
public class UpdateFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFormAction() {
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
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("log");
		
		if(user == null) {
			response.sendRedirect("/login");
			return;
		}
		
		String username = user.getUsername();
		String password = request.getParameter("password");
		String newPassword = request.getParameter("new-password");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		int telecom = Integer.parseInt(request.getParameter("telecom"));
		String phone = request.getParameter("phone");

		UserDao userDao = UserDao.getInstance();

		UserRequestDto userDto = new UserRequestDto();

		userDto.setUsername(username);
		userDto.setCountry(country);
		userDto.setTelecom(telecom);

		if(!email.equals("") && !email.equals(user.getEmail())) {
			User target = userDao.findUserByEmail(email);
			if(target == null)
				userDto.setEmail(email);
		}

		if(!newPassword.equals("") && !password.equals(newPassword)) {
			if(user.checkPassword(password))
				userDto.setPassword(newPassword);
		}

		if(!user.getPhone().equals(phone)) {
			User target = userDao.findUserByPhone(phone);
			if(target == null) 
				userDto.setPhone(phone);
		}

		user = userDao.updateUser(userDto);
		session.setAttribute("log", user);

		response.sendRedirect("/mypage");
	}

}
