package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.CommentVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	public List<BoardVo> find(String key,Long page) {
		return  boardRepository.find(key,page);
	}
	
	public List<CommentVo> CommentList(Long no) {
		return  boardRepository.CommentList(no);
	}
	public BoardVo viewFind(Long no) {
		boardRepository.hitUpdate(no);
		return boardRepository.viewFind(no);
		
	}
	
	public void insert(BoardVo vo) {
		boardRepository.insert(vo);
	}
	public void boardUpdate(BoardVo vo) {
		boardRepository.boardupdate(vo);
	}

	public void delete(Long no) {
		boardRepository.delete(no);
	}

	public void boardWrite(BoardVo vo) {
		boardRepository.boardWrite(vo);
	}

	public void reply(BoardVo vo) {
		if(vo.getoNo() > 0) {
		boardRepository.replyUpdate(vo);
		boardRepository.replyWrite(vo);
		}else {
			boardRepository.replyWrite(vo);
		}
	}

	public void commentInsert(CommentVo vo) {
		boardRepository.commentInsert(vo);
	}

	public void commentDelete(Long no) {
		boardRepository.commentDelete(no);
		
	}

	

}
