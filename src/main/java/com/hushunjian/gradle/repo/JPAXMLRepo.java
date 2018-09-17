package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.JPAEntityA;

public interface JPAXMLRepo extends JpaRepository<JPAEntityA, Long> {
	
}
