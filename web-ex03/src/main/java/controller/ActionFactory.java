package controller;

import user.action.JoinFormAction;

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
		
		return action;
	}

}
