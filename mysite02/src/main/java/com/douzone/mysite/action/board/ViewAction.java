package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.utill.WebUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String n = request.getParameter("n");
		Long a = (long) Integer.parseInt(n);
		BoardVo vo = new BoardRepository().finByNo(a);
		int hit = vo.getHit();
		hit = hit+1;
		Long no = vo.getNo();
		BoardVo hitVo = new BoardVo();
		hitVo.setNo(no);
		hitVo.setHit(hit);
		request.setAttribute("vo", vo);		
		List<BoardVo> list = new BoardRepository().commentFind(vo);
		request.setAttribute("list", list);

		new BoardRepository().hitUpdate(hitVo);
		
		WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
	}
}
