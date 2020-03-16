package com.douzone.mysite.action.board;

import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch (actionName) {
		case "view":
			return new ViewAction();
		case "modify":
			return new ModifyAction();
		case "modifyform":
			return new ModifyFormAction();
		case "modifyDetform":
			return new ModifyDetFormAction();
		case "writeform":
			return new WriteformAction();
		case "write":
			return new WriteAction();
		case "detwrite":
			return new DetWriteAction();
		case "remove":
			return new RemoveAction();
		case "find":
			return new FindAction();
		case "delete":
			return new DeleteAction();
		case "comment":
			return new CommentAction();
		case "commentdel":
			return new CommentDeleteAction();
		default:
			return new ListAction();
		}
	}

}
