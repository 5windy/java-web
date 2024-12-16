package board.controller;

import java.io.IOException;

import board.model.BoardDao;
import board.model.BoardResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.User;

public class DeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		User log = (User) session.getAttribute("log");
		
		if(log == null) {
			response.sendRedirect("/login");
			return;
		}

		int code = Integer.parseInt(request.getParameter("code"));

		BoardDao boardDao = BoardDao.getInstance();
		BoardResponseDto board = boardDao.findBoardByCode(code);

		String username = board.getUsername();

		String url = "";

		if (log != null && username.equals(log.getUsername())) {
			boardDao.deleteBoardByCode(code);
			url = "/boards";
		} else {
			session.removeAttribute("log");
			session.invalidate();

			url = "/login";
		}

		response.sendRedirect(url);
	}

}
