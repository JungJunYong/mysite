package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	@Autowired
	GuestBookService guestbookService;
	
	@RequestMapping(value="",method = RequestMethod.GET)
	public String guestbook(Model model) {
		System.out.println("testtesttest");
		List<GuestBookVo> list = guestbookService.findAll();
		model.addAttribute("list",list);
		return "guestbook/list";
	}
	@RequestMapping(value="",method = RequestMethod.POST)
	public String guestbook(GuestBookVo vo) {
		guestbookService.insert(vo);
		return "redirect:guestbook";
	}
	
	@RequestMapping(value="/delete/{no}",method = RequestMethod.GET)
	public String guestbookdel(@PathVariable("no")Long no,Model model) {
		model.addAttribute(no);
		return "guestbook/delete";
	}
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public String guestbookdel(GuestBookVo vo) {
		guestbookService.delete(vo);
		return "redirect:/guestbook";
	}
	@RequestMapping(value="/spa")
	public String guestbookspa() {
		
		return "guestbook/spa";
	}
	

}
