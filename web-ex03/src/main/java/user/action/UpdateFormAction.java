package user.action;

import java.io.IOException;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.User;
import user.model.UserDao;
import user.model.UserRequestDto;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
