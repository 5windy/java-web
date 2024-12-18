package controller;

import board.action.*;
import user.action.*;
import util.HttpMethod;

public class ActionFactory {
	
	private ActionFactory() {
	}
	
	private static ActionFactory instance = new ActionFactory();
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String path, String command, HttpMethod method) {
		Action action = null;
		
		if(path == null || command == null)
			return action;
		
		if(path.equals("users"))
			return getUserAction(command, method);
		else if(path.equals("boards"))
			return getBoardAction(command, method);
		else if(path.equals("api"))
			return getApiAction(command, method);
		
		return action;
	}
	
	private Action getUserAction(String command, HttpMethod method) {
		Action action = null;
		
		if(command.equals("join") && method == HttpMethod.POST)
			return new JoinFormAction();
		else if(command.equals("login") && method == HttpMethod.POST)
			return new LoginFormAction();
		else if(command.equals("logout") && method == HttpMethod.GET)
			return new LogoutAction();
		else if(command.equals("update") && method == HttpMethod.POST)
			return new user.action.UpdateFormAction();
		else if(command.equals("delete") && method == HttpMethod.POST)
			return new DeleteFormAction();
		
		return action;
	}
	
	private Action getBoardAction(String command, HttpMethod method) {
		Action action = null;
		
		if(command.equals("write") && method == HttpMethod.POST)
			return new WriteFormAction();
		else if(command.equals("update") && method == HttpMethod.POST)
			return new board.action.UpdateFormAction();
		else if(command.equals("view") && method == HttpMethod.GET)
			return new DetailViewAction();
		else if(command.equals("delete") && method == HttpMethod.DELETE)
			return new DeleteAction();
		else if(command.equals("list") && method == HttpMethod.GET)
			return new BoardListAction();
		
		return action;
	}
	
	private Action getApiAction(String command, HttpMethod method) {
		Action action  = null;
		
		if(command.equals("search-username") && method == HttpMethod.POST)
			return new SearchUsernameAction();
		else if(command.equals("search-email") && method == HttpMethod.POST)
			return new SearchEmailAction();
		else if(command.equals("search-phone") && method == HttpMethod.POST)
			return new SearchPhoneAction();
		else if(command.equals("books") && method == HttpMethod.GET)
			return new SearchBooksAction();
		
		return action;
	}

}
