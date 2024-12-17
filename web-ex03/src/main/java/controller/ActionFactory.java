package controller;

import board.action.*;
import user.action.*;

public class ActionFactory {
	
	private ActionFactory() {
	}
	
	private static ActionFactory instance = new ActionFactory();
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command, String method) {
		Action action = null;
		
		if(command.equals("join") && method.equals("POST"))
			return new JoinFormAction();
		else if(command.equals("login") && method.equals("POST"))
			return new LoginFormAction();
		else if(command.equals("logout") && method.equals("GET"))
			return new LogoutAction();
		else if(command.equals("update") && method.equals("POST"))
			return new user.action.UpdateFormAction();
		else if(command.equals("delete") && method.equals("POST"))
			return new DeleteFormAction();
		else if(command.equals("write") && method.equals("POST"))
			return new WriteFormAction();
		else if(command.equals("update-board") && method.equals("POST"))
			return new board.action.UpdateFormAction();
		else if(command.equals("view") && method.equals("GET"))
			return new DetailViewAction();
		else if(command.equals("delete-board") && method.equals("DELETE"))
			return new DeleteAction();
		else if(command.equals("list") && method.equals("GET"))
			return new BoardListAction();
		
		
		return action;
	}

}
