package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.utill.WebUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 접근제어(Access Control List, ACL)
		HttpSession session = request.getSession();
		if(session == null) {
			WebUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if( authUser == null ) {
			WebUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String password = request.getParameter("password");
		String nowpas = request.getParameter("nowpas");
		UserVo vo = new UserVo();
		vo.setNo(Long.parseLong(no));
		vo.setName(name);
		vo.setGender(gender);
		vo.setEmail(email);
		if(!password.isEmpty()) {
			vo.setPassword(password);
		} else {
			vo.setPassword(nowpas);
		}
		new UserRepository().update(vo);

		
		UserVo au = (UserVo)session.getAttribute("authUser");
		au.setName(name);
		session.setAttribute("authUser", au);
		
		WebUtil.redirect(request.getContextPath(), request, response);
		
	}

}
