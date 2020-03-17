package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public int join(UserVo vo) {
		userRepository.insert(vo);
		return 1;
	}

	public UserVo getUser(UserVo vo) {
		return userRepository.findByEmailAndPassword(vo);
	}

	public UserVo getUser(Long no) {
		return userRepository.find(no);
	}

	public boolean update(UserVo vo) {
		return userRepository.update(vo);
	}


}
