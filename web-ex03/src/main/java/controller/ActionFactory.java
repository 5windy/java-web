package controller;

import user.action.DeleteFormAction;
import user.action.JoinFormAction;
import user.action.LoginFormAction;
import user.action.LogoutAction;
import user.action.UpdateFormAction;

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
			return new UpdateFormAction();
		else if(command.equals("delete") && method.equals("POST"))
			return new DeleteFormAction();
		
		return action;
	}

}
