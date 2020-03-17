package com.douzone.mysite.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.CommentVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value ={"","/{page}","/{page}/{key}"},method = RequestMethod.GET)
	public String BoardList(@PathVariable("page")Optional<Long> page,Model model,@PathVariable("key")Optional<String> key) {
		String kwd = null;
		Long pg = 0L;
		if(key.isPresent()) {
			kwd = key.get();
		}
		if(page.isPresent()) {
			pg = page.get(); 
		}
		model.addAttribute("kwd", kwd);
		if(!page.isPresent()) {
			pg = 0L;
			List<BoardVo> list = boardService.find(kwd,pg);
			model.addAttribute("page",pg);
			model.addAttribute("kwd",kwd);
			model.addAttribute("list",list);
			return "board/list";
		}else
		{
			model.addAttribute("kwd",kwd);
			pg = page.get()-1L;
			Long pe = (pg/(long) 5);
			List<BoardVo> list = boardService.find(kwd,pe);
			model.addAttribute("page",pg);
			model.addAttribute("pe",pe);
			model.addAttribute("list",list);
			return "board/list";
		}
	}
	
	@RequestMapping(value ={"","/{page}"},method = RequestMethod.POST)
	public String BoardList(@PathVariable("page")Optional<Long> page,BoardVo vo,Model model) {
		model.addAttribute("kwd",vo.getTitle());
		if(!page.isPresent()) {
			Long pg = 0L;
			List<BoardVo> list = boardService.find(vo.getTitle(),pg);
			model.addAttribute("page",pg);
			model.addAttribute("list",list);
			return "board/list";
		}else
		{
			Long pg = page.get()-1L;
			Long pe = (pg/(long) 5);
			System.out.println(pe);
			List<BoardVo> list = boardService.find(vo.getTitle(),pe);
			model.addAttribute("page",pg);
			model.addAttribute("pe",pe);
			model.addAttribute("list",list);
			return "board/list";
		}
	}
	

	
	
	@RequestMapping(value="/view/{no}",method = RequestMethod.GET)
	public String View(@PathVariable("no") Long no,Model model,BoardVo vo) {
		List<CommentVo> list = boardService.CommentList(no);
		BoardVo boardVo = boardService.viewFind(no);
		System.out.println(boardVo.getUserNo());
		model.addAttribute("vo",boardVo);
		model.addAttribute("list",list);
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value="/view/{no}",method = RequestMethod.POST)
	public String View(@AuthUser UserVo authUser, @PathVariable Long no,CommentVo vo) {
		vo.setUserNo(authUser.getNo());
		vo.setBoardNo(no);
		boardService.commentInsert(vo);
		return "redirect:/board/view/"+no;
	}
	
	@RequestMapping(value="/view/comment/{no}/{viewNo}",method = RequestMethod.GET)
	public String commentRm(@AuthUser UserVo authUser,@PathVariable Long no,@PathVariable Long viewNo) {
		if(authUser == null) {
			return "redirect:/board/view/"+no;
		}
		boardService.commentDelete(no);
		return "redirect:/board/view/"+viewNo;
	}
	
	@Auth
	@RequestMapping(value= {"/write","/write/{no}"},method = RequestMethod.GET)
	public String writeView(@PathVariable("no")Optional<Long> no,Model model) {
		if(!no.isPresent()) {
			return "board/write";
		}else {
		BoardVo boardVo = boardService.viewFind(no.get());
		model.addAttribute("vo",boardVo);
		return "board/write";
		}
	}
	
	
	
	@Auth
	@RequestMapping(value="/write",method = RequestMethod.POST)
	public String writeView(@AuthUser UserVo authUser,BoardVo vo) {
			BoardVo boardVo = boardService.viewFind(vo.getNo());
			vo.setUserNo(authUser.getNo());
			vo.setgNo(boardVo.getgNo());
			vo.setoNo(boardVo.getoNo());
			vo.setDepth(boardVo.getDepth());
			boardService.reply(vo);
		return "redirect:/board";
	}
	
	@RequestMapping(value= "/reply/{no}",method = RequestMethod.GET)
	public String replyView(@PathVariable("no")Optional<Long> no,Model model) {
		if(!no.isPresent()) {
			return "redirect:/board";
		}else {
			BoardVo boardVo = boardService.viewFind(no.get());
			model.addAttribute("vo", boardVo);
			return "board/write";
		}
	}	
	
	
	@Auth
	@RequestMapping(value="/delete/{no}",method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no,BoardVo vo) {
		boardService.delete(no);
		return "redirect:/board";
	}
	


}
