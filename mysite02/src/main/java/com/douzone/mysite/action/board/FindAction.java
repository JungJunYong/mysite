package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.utill.WebUtil;

public class FindAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String title = request.getParameter("title");
		request.setAttribute("title", title);
		title = "%"+title+"%";
		List<BoardVo> list = (List<BoardVo>) new BoardRepository().findByName(title);
		
		request.setAttribute("list", list);
		HttpSession session = request.getSession();
		UserVo vo = (UserVo)session.getAttribute("authUser");
		
		request.setAttribute("vo", vo);
		
		request.setAttribute("find", "find");
		
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
	}

}
