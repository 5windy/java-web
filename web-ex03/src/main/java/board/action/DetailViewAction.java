package board.action;

import java.io.IOException;

import board.model.BoardDao;
import board.model.BoardResponseDto;
import controller.Action;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DetailViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getParameter("no");

		int code = 0;
		try {
			code = Integer.parseInt(path);
		} catch (NumberFormatException e) {
			System.err.println("유효하지 않은 게시글 번호");
			response.sendRedirect("/service/boards?command=list");
		}

		BoardDao boardDao = BoardDao.getInstance();

		BoardResponseDto boardDto = boardDao.findBoardByCode(code);

		if (boardDto != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update");
			request.setAttribute("board", boardDto);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("/service/boards?command=list");
		}
		
	}

}
