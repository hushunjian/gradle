package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.Operator;

public interface OperatorRepo extends JpaRepository<Operator, Long> {

}
