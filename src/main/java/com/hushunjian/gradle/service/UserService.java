package com.hushunjian.gradle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.copier.SourceTargetMapper;
import com.hushunjian.gradle.entity.Operator;
import com.hushunjian.gradle.entity.User;
import com.hushunjian.gradle.repo.OperatorRepo;
import com.hushunjian.gradle.repo.UserRepo;
import com.hushunjian.gradle.searchConditionEntiy.GetAllUserByConditionEntity;

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

	public User updateUser(User user) {
		User userOriginal = userRepo.findOne(user.getId());
		userOriginal = SourceTargetMapper.INSTANCE.asUser(user);
		userRepo.save(userOriginal);
		return userOriginal;
	}

	public Object getUserById(Long id) {
		return userRepo.findOne(id);
	}

	public Object getAllUserByPage(int pageIndex, int pageSize) {
		//分页,排序
		Sort sort = new Sort(Direction.DESC, "id");
		Pageable pageable=new PageRequest(pageIndex,pageSize,sort);
		return userRepo.findAll(pageable);
	}

	public Object getAllUserByCondition(GetAllUserByConditionEntity getAllUserByConditionEntity) {
		//分页,排序
		Sort sort = new Sort(Direction.DESC, "id");
		Pageable pageable=new PageRequest(getAllUserByConditionEntity.getPageIndex(),getAllUserByConditionEntity.getPageSize(),sort);
		User user = new User();
		user.setUserName(getAllUserByConditionEntity.getName());
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase(true).withIgnorePaths("age","status");
		Example<User> example = Example.of(user, matcher); 
		return userRepo.findAll(example, pageable);
	}
	

}
