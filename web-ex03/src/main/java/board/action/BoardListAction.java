package board.action;

import java.io.IOException;
import java.util.List;

import board.model.BoardDao;
import board.model.BoardResponseDto;
import controller.Action;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			System.err.println("유효하지 않은 페이지 정보");
		}
		
		BoardDao boardDao = BoardDao.getInstance();
		List<BoardResponseDto> list = boardDao.findBoardAll(page);
	
		if(page != 1 && list.size() == 0) {
			String referer = request.getHeader("Referer");
			
			if(referer != null)
				response.sendRedirect(referer);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list");
		request.setAttribute("list", list);
		request.setAttribute("size", boardDao.getTotalSize());
		dispatcher.forward(request, response);
		
	}

}
