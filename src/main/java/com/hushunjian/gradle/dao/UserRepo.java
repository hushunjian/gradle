package com.hushunjian.gradle.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
