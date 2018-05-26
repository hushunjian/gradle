package com.hushunjian.gradle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	List<User> findByUserName(String userName);

	List<User> findByUserNameLike(String userName);

}
