package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.CommentVo;


@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> find(String key,Long page) {
		Map<String, Object> map = new HashMap<>();
		map.put("startpage", (page*50));
		map.put("lastpage",(((page*50)+51)));
		map.put("key", key);
		System.out.println(map);
		return sqlSession.selectList("board.find",map);
	}
	
	public List<CommentVo> CommentList(Long no){
		return sqlSession.selectList("commentlist",no);
	}
	
	public BoardVo viewFind(Long no) {
		return sqlSession.selectOne("viewfind",no);
	}

	public boolean delete(Long no) {
		int count = sqlSession.update("board.delete", no);
		return count == 1;
	}

	public boolean insert(BoardVo vo) {
		int count = sqlSession.insert("board.insert", vo);
		return count == 1;
	}

	public boolean boardWrite(BoardVo vo) {
		int count = sqlSession.insert("boardwrite",vo);
		return count == 1;
	}
	public boolean boardupdate(BoardVo vo) {
		int count = sqlSession.insert("boardupdate",vo);
		return count == 1;
	}

	public boolean replyWrite(BoardVo vo) {
		int count = sqlSession.insert("replywrite",vo);
		return count == 1;
	}
	public boolean replyUpdate(BoardVo vo) {
		int count = sqlSession.update("replyupdate",vo);
		return count == 1;
	}

	public boolean commentInsert(CommentVo vo) {
		int count = sqlSession.insert("commentinsert",vo);
		return count == 1;
	}

	public boolean commentDelete(Long no) {
		int count = sqlSession.delete("commentdelete",no);
		return count == 1;
	}

	public void hitUpdate(Long no) {
		sqlSession.update("hitupdate",no);
	}

}
