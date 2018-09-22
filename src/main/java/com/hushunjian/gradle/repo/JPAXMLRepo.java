package com.hushunjian.gradle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.hushunjian.gradle.entity.JPAEntityA;

public interface JPAXMLRepo extends JpaRepository<JPAEntityA, Long> {
	
	List<JPAEntityA> test(@Param("userName")String userName);
}
