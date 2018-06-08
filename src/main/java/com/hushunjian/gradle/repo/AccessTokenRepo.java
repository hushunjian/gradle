package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.AccessToken;

public interface AccessTokenRepo extends JpaRepository<AccessToken, Long>{

}
