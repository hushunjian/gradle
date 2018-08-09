package com.hushunjian.gradle.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.copier.SourceTargetMapper;
import com.hushunjian.gradle.dto.UserBasicInfoDto;
import com.hushunjian.gradle.entity.Operator;
import com.hushunjian.gradle.entity.User;
import com.hushunjian.gradle.repo.OperatorRepo;
import com.hushunjian.gradle.repo.UserRepo;
import com.hushunjian.gradle.request.QueryUserRequest;
import com.hushunjian.gradle.request.User1Request;
import com.hushunjian.gradle.searchConditionEntiy.GetAllUserByConditionEntity;

@Service
@SuppressWarnings("all")
@Transactional
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private OperatorRepo operatorRepo;
	
	@PersistenceContext
    EntityManager entityManager;
	
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

	public User getUserById(Long id) {
		return userRepo.findOne(id);
	}

	public Object getAllUserByPage(int pageIndex, int pageSize) {
		//分页,排序
		Sort sort = new Sort(Direction.DESC, "id");
		//页码同0开始,第一页的页码为0
		Pageable pageable=new PageRequest(pageIndex-1,pageSize,sort);
		return userRepo.findAll(pageable);
	}

	public Object getAllUserByCondition(GetAllUserByConditionEntity getAllUserByConditionEntity) {
		//分页,排序
		Sort sort = new Sort(Direction.DESC, "id");
		Pageable pageable=new PageRequest(getAllUserByConditionEntity.getPageIndex()-1,getAllUserByConditionEntity.getPageSize(),sort);
		User user = new User();
		user.setUserName(getAllUserByConditionEntity.getName());
		user.setCreatedBy(getAllUserByConditionEntity.getCreatedBy());
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase(true).withMatcher("createdBy", GenericPropertyMatchers.exact()).withIgnorePaths("age","status");
		Example<User> example = Example.of(user, matcher); 
		return userRepo.findAll(example, pageable);
	}

	public void deleteUserById(Long id) {
		userRepo.delete(id);
	}

	public List<User> getAllUserByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}

	public List<User> getAllUserByUserNameLike(String userName) {
		return userRepo.findByUserNameLike('%'+userName+'%');
	}

	public List<UserBasicInfoDto> getAllUserBasicInfo(int pageIndex, int pageSize) {
		String sql = "SELECT t1.user_name AS userName,t1.age AS userAge,t1.status AS userStatus,t2.operator_name AS operatorName,t2.age AS operatorAge,t2.status operatorStatus FROM user t1,operator t2 WHERE t1.created_by = t2.operator_name LIMIT "+(pageIndex-1)*pageSize+","+pageSize;
		Query query = entityManager.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(UserBasicInfoDto.class));
		List<UserBasicInfoDto> userBasicInfoDtos = query.getResultList();       
		return userBasicInfoDtos;
	}

	public List<UserBasicInfoDto> getAllUserBasicInfoJPA(int pageIndex, int pageSize) {
		return userRepo.getAllUserBasicInfoJPA();
	}

	public User updateUserBySelect(User user) {
		return userRepo.save(user);
	}

	public List<User> getAllUserByConditionVo(QueryUserRequest queryUserRequest) {
		Sort sort = new Sort(Direction.DESC, "id");
		Pageable pageable=new PageRequest(queryUserRequest.getPageNo()-1,queryUserRequest.getPageSize(),sort);
		List<User> users = userRepo.getAllUserByConditionVo("%"+queryUserRequest.getUserName()+"%",pageable);
		return users;
	}

	public void deleteUserByUserName(String userName) {
		userRepo.deleteByUserName(userName);
	}

	public Long getAllUserAgeSum() {
		return 0L;
	}

	public List<User> getAllUserTest(User1Request user1Request) {
		System.out.println("sssssssssss");
		return null;
	}
}
