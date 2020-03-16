package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.utill.WebUtil;

public class DetWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String no = request.getParameter("no");
		BoardVo vo = new BoardRepository().finByNo((long) Integer.parseInt(no));
		HttpSession session = request.getSession();
		UserVo vo2 = (UserVo)session.getAttribute("authUser");
		
		int gNo = vo.getgNo();
		int oNo = vo.getoNo();
		int depth = vo.getDepth();
		BoardVo bV = new BoardVo();
		bV.setgNo(gNo);
		bV.setoNo(oNo);
		bV.setDepth(depth);
		bV.setUserNo(vo2.getNo());
		bV.setContents(content);
		bV.setTitle(title);
		
		new BoardRepository().det(bV);
		new BoardRepository().detinsert(bV);

		WebUtil.redirect("/mysite02/board", request, response);
	}
}
