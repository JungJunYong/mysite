package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.service.MainService;
import com.douzone.mysite.vo.MainVo;
import com.douzone.mysite.vo.UserVo;


@Controller
public class MainController {
	
	@Autowired
	MainService mainService;
	
	@RequestMapping({"","/main"})
	public String index(Model model) {
		MainVo mainVo = mainService.find();
		model.addAttribute("vo",mainVo);
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public UserVo hello() {
		return new UserVo();
	}
	
	

}
