package com.douzone.mysite.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.MainService;
import com.douzone.mysite.vo.MainVo;
import com.douzone.security.Auth;

@Auth("ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	MainService mainService;
	
	@RequestMapping(value="",method = RequestMethod.GET)
	public String main(Model model) {
		MainVo mainVo = mainService.find();
		model.addAttribute("vo",mainVo);
		return "admin/main";
	}
	@RequestMapping(value="/board",method = RequestMethod.GET)
	public String board() {
		return "admin/board";
	}
	@RequestMapping(value="/guestbook",method = RequestMethod.GET)
	public String guestbook() {
		return "admin/guestbook";
	}
	@RequestMapping(value="/user",method = RequestMethod.GET)
	public String user() {
		return "admin/user";
	}
	
	@RequestMapping(value={"/update"},method = RequestMethod.POST)
	public String upload(MainVo vo) {
		mainService.restore(vo);
		return "redirect:/admin";
	}
	
}
