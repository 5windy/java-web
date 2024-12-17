package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.HttpMethod;

import java.io.IOException;
import java.io.PrintWriter;

public class ServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// Action 인스턴스를 요청 내용에 따라 서로 다르게 생성해주는 ActionFatory 
		// Action 인스턴스를 얻었다면 -> execute() 호출하여 Processing
		
		String path = request.getPathInfo(); 
		String command = request.getParameter("command");
		String method = request.getMethod();
		
		ActionFactory actionFactory = ActionFactory.getInstance();
		Action action = actionFactory.getAction(path, command, HttpMethod.valueOf(method));
		
		if(action != null) {
			action.execute(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
