package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hushunjian.gradle.entity.TestDefault;

public interface TestDefaultRepo extends JpaRepository<TestDefault, Long>{

	
	@Query(value="DELETE FROM TestDefault WHERE id = ?1")
	@Modifying
	void deleteById(Long id);

}
