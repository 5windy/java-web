package user.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import user.model.User;
import user.model.UserDao;

public class SearchUserAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request 객체로 부터 바디 문자열 읽어오기
		StringBuilder builder = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		while(reader.ready()) {
			builder.append(reader.readLine());
		}
		
		JSONObject reqData = new JSONObject(builder.toString());
		String username = reqData.getString("username");
		
		UserDao userDao = UserDao.getInstance();
		User user = userDao.findUserByUsername(username);
		
		boolean isValid = user == null;
		
		JSONObject resData = new JSONObject();
		resData.put("isValid", isValid);
		
		// 응답 준비 
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		out.append(resData.toString());
		out.flush();
		
	}

}
