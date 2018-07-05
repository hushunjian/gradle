package com.hushunjian.gradle.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hushunjian.gradle.dto.UserBasicInfoDto;
import com.hushunjian.gradle.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	List<User> findByUserName(String userName);

	List<User> findByUserNameLike(String userName);

	@Query("select new com.hushunjian.gradle.dto.UserBasicInfoDto(user.userName,user.age,user.status,operator.operatorName,operator.age,operator.status) from User user,Operator operator where user.createdBy=operator.operatorName")
	List<UserBasicInfoDto> getAllUserBasicInfoJPA();

	@Query("select user from User user where user.userName like ?1")
	List<User> getAllUserByConditionVo(String userName, Pageable pageable);
}
