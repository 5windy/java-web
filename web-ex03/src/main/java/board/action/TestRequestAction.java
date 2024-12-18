package board.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONObject;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TestRequestAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		
		String uri = "https://dapi.kakao.com/v3/search/book?query=";
		uri += URLEncoder.encode(search, "utf-8");
		
		String restApiKey = "";
		try {
			Context init = new InitialContext();
			Context ctx = (Context) init.lookup("java:comp/env");
			restApiKey = (String) ctx.lookup("apiKey/KakaoRestApiKey");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		String authorization = String.format("KakaoAK %s", restApiKey);
		
		// 1. URL 객체 생성 
		URL url = new URL(uri);
		
		// 2. 연결 준비 
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", authorization);
		
		// 3. 요청 및 응답 받기 
		InputStream in = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		StringBuilder builder = new StringBuilder();
		while(reader.ready()) {
			builder.append(reader.readLine());
		}
		
		// 4. 리소스 정리
		reader.close();
		conn.disconnect();
		
		JSONObject resData = new JSONObject(builder.toString());
		
		JSONObject meta = new JSONObject(resData.get("meta").toString());
		JSONArray documents = new JSONArray(resData.get("documents").toString());
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.getWriter().append(documents.toString());
		response.getWriter().flush();
	}

}
