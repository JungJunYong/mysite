package com.douzone.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.action.guestbook.GuestActionFactory;
import com.douzone.web.action.Action;


public class GuestBookController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String actionName = request.getParameter("a");
		if(actionName == null) {
			actionName = "  ";
		}
		Action action = new GuestActionFactory().getAction(actionName);
		action.execute(request,response);
		
		
		/*
		 * if("deleteform".equals(action)) { request.setAttribute("no",
		 * request.getParameter("no"));
		 * WebUtil.forward("/WEB-INF/views/guestbook/deleteform.jsp", request,
		 * response);
		 * 
		 * }else if ("delete".equals(action)) { String no = request.getParameter("no");
		 * String password = request.getParameter("password"); GuestBookVo vo = new
		 * GuestBookVo(); vo.setNo(no); vo.setPassword(password); new
		 * GuestBookRepository().delete(vo);
		 * WebUtil.redirect(request.getContextPath()+"/guestbook", request, response);
		 * 
		 * }else if ("add".equals(action)) { String name = request.getParameter("name");
		 * String password = request.getParameter("pass"); String contents =
		 * request.getParameter("content"); GuestBookVo vo = new GuestBookVo();
		 * vo.setName(name); vo.setPassword(password); vo.setContents(contents); new
		 * GuestBookRepository().insert(vo); WebUtil.redirect("/mysite02/guestbook",
		 * request, response); }else { //default 요청처리 (list) List<GuestBookVo> list =
		 * new GuestBookRepository().findAll(); request.setAttribute("list", list);
		 * WebUtil.forward("/WEB-INF/views/guestbook/list.jsp", request, response); }
		 */
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
