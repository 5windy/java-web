package board.controller;

import java.io.IOException;

import board.model.BoardDao;
import board.model.BoardRequestDto;
import board.model.BoardResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.model.User;

public class WriteFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("log");
		
		if(user == null) {
			response.sendRedirect("/login");
			return;
		}
		
		String username = user.getUsername();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String status = request.getParameter("status");
		String postDate = request.getParameter("postDate");
		
		BoardRequestDto boardDto = new BoardRequestDto(username, title, content, status, postDate);
		
		BoardDao boardDao = BoardDao.getInstance();
		BoardResponseDto board = boardDao.createBoard(boardDto);
		
		if(board != null) {
			response.sendRedirect("/boards/" + board.getCode());
		} else {
			response.sendRedirect("/write");
		}
		
	}
       
}
