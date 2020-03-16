package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.utill.WebUtil;

public class CommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String n = request.getParameter("n");
		BoardVo vo = new BoardVo();
		vo.setContents(request.getParameter("contents"));
		vo.setNo(Long.parseLong(n));
		vo.setUserNo(Long.parseLong(request.getParameter("un")));
		System.out.println(vo);
		
		new BoardRepository().commentInsert(vo);
		
		WebUtil.redirect("/mysite02/board?a=view&&n="+n, request, response);
	}
}
