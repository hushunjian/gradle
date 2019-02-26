package com.hushunjian.gradle.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hushunjian.gradle.dto.StringToIntegerDTO;
import com.hushunjian.gradle.entity.TestStringToInteger;

public interface StringToIntegerRepo extends JpaRepository<TestStringToInteger, Long>{

	@Query("select new com.hushunjian.gradle.dto.StringToIntegerDTO(t1.id,cast(t1.number as integer)) from TestStringToInteger t1")
	List<StringToIntegerDTO> test();
	
	@Query("select new com.hushunjian.gradle.dto.StringToIntegerDTO(sum(cast(t1.number as integer))) from TestStringToInteger t1")
	StringToIntegerDTO test1();
	
	List<TestStringToInteger> findByIdIn(Long... ids);

	List<TestStringToInteger> findByIdIn(Collection<Long> ids);
	
}
