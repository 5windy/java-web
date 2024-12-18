package board.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import board.model.BoardDao;
import board.model.BoardResponseDto;
import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SearchBooksAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 옵셔널 파라미터 page 확인
		// ㄴ 없으면 디폴트 1 page 
		// ㄴ 있으면 숫자로 타입캐스팅 시도 -> 실패 시, 1페이지
		String pageStr = request.getParameter("page");
		
		int page = 1;
		
		if(pageStr != null) {
			try {
				page = Integer.parseInt(pageStr);
			} catch (Exception e) {
				System.err.println("유효하지 않은 페이지 값");
			}
		}
		
		// 2. 페이지 인자를 들고 BoardDao 객체의 게시글 조회 메소드 호출 
		BoardDao boardDao = BoardDao.getInstance();
		List<BoardResponseDto> list = boardDao.findBoardAll(page);
		
		// 3. 게시글 컬렉션 객체를 가지고 -> JSON 배열 생성 
		JSONArray array = new JSONArray(list);
		
		int total = boardDao.getTotalSize();
		
		JSONObject meta = new JSONObject();
		meta.put("total", total);
		meta.put("currentPage", page);
		
		array.put(meta);
		
		// 4. 응답 처리
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		out.append(array.toString());
		out.flush();
	}

}
