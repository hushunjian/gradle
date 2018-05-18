package com.hushunjian.gradle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.dao.OperatorRepo;
import com.hushunjian.gradle.dao.UserRepo;
import com.hushunjian.gradle.entity.Operator;
import com.hushunjian.gradle.entity.User;

@Service	
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private OperatorRepo operatorRepo;

	public void addUser(User user, Operator operator) {
		userRepo.save(user);
		operatorRepo.save(operator);
	}
}
