package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.action.Action;
import com.douzone.web.utill.WebUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String n = request.getParameter("n");
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		BoardVo vo = new BoardVo();
		vo.setNo((long) Integer.parseInt(n));
		vo.setTitle(title);
		vo.setContents(contents);
		new BoardRepository().update(vo);
		
		WebUtil.redirect("/mysite02/board", request, response);
	} 

}
