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

public class SearchPhoneAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder builder = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		while(reader.ready()) {
			builder.append(reader.readLine());
		}
		
		JSONObject reqData = new JSONObject(builder.toString());
		String phone = reqData.getString("phone");
		
		UserDao userDao = UserDao.getInstance();
		User user = userDao.findUserByPhone(phone);
		
		boolean isValid = user == null;
		
		JSONObject resData = new JSONObject();
		resData.put("isValid", isValid);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		out.append(resData.toString());
		out.flush();
	}

}
