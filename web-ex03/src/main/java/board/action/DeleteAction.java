package board.action;

import java.io.IOException;

import board.model.BoardDao;
import board.model.BoardResponseDto;
import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.User;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			url = "/service/boards?command=list";
		} else {
			session.removeAttribute("log");
			session.invalidate();

			url = "/login";
		}

		response.sendRedirect(url);		
	}

}
