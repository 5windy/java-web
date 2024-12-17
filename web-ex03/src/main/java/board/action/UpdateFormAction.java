package board.action;

import java.io.IOException;

import board.model.BoardDao;
import board.model.BoardRequestDto;
import board.model.BoardResponseDto;
import controller.Action;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.User;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		User log = (User) session.getAttribute("log");

		if (log == null) {
			response.sendRedirect("/login");
			return;
		}
		
		String path = request.getPathInfo();
		int code = 0;
		
		try {
			code = Integer.parseInt(path.substring(1)); 
		} catch (Exception e) {
			System.err.println("유효하지 않은 게시글 번호");
		}

		String username = request.getParameter("username");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String status = request.getParameter("status");
		String postDate = request.getParameter("postDate");
		
		if (log.getUsername().equals(username)) {
			BoardRequestDto boardDto = new BoardRequestDto(username, title, content, status, postDate);

			BoardDao boardDao = BoardDao.getInstance();
			BoardResponseDto board = boardDao.updateBoard(code, boardDto);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/update");
			request.setAttribute("board", board);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("/login");
		}
	}

}
