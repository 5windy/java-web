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
 * Servlet implementation class DeleteFormAction
 */
public class DeleteFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFormAction() {
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
		String password = request.getParameter("password");

		String url = "";

		if(user.checkPassword(password)) {
			UserDao userDao = UserDao.getInstance();
			userDao.deleteUserByUsername(user.getUsername());
			
			session.removeAttribute("log");
			session.invalidate();
			
			url = "/";
			
		} else {
			url = "/deleteForm.jsp";
		}
		response.sendRedirect(url);
	}

}