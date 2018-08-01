package com.hushunjian.gradle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.Operator;

public interface OperatorRepo extends JpaRepository<Operator, Long> {

	List<Operator> findByOperatorName(String operatorName);

	List<Operator> findByOperatorNameLike(String operatorName);

}
