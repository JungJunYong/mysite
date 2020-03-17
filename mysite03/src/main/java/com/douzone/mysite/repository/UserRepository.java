package com.douzone.mysite.repository;


import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private HttpSession session;

	public UserVo find(Long no) {
		return sqlSession.selectOne("user.find", no);
	}

	public boolean delete(UserVo vo) {
		int count =sqlSession.delete("user.delete");
		return count == 1 ;
	}

	public boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		return count == 1 ;
	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		return sqlSession.selectOne("user.findByEmailAndPassword", vo);
	}

	public boolean update(UserVo vo) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		vo.setNo(userVo.getNo());
		System.out.println(vo);
		int count = sqlSession.update("user.update", vo);
		return count == 1;
	}

}
