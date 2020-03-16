package com.douzone.mysite.action.guestbook;

import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class GuestActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch (actionName) {
		case "deleteform":
			return new DeleteFormAction();
		case "delete":
			return new DeleteAction();
		case "add":
			return new AddAction();
		default:
			return new ListAction();
		}
	}

}
