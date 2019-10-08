package com.hushunjian.gradle.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hushunjian.gradle.dto.StringToIntegerDTO;
import com.hushunjian.gradle.entity.TestStringToInteger;

public interface StringToIntegerRepo extends JpaRepository<TestStringToInteger, Long>{

	@Query("select new com.hushunjian.gradle.dto.StringToIntegerDTO(t1.id,cast(t1.number as integer)) from TestStringToInteger t1")
	List<StringToIntegerDTO> test();
	
	@Query("select new com.hushunjian.gradle.dto.StringToIntegerDTO(sum(cast(t1.number as integer))) from TestStringToInteger t1")
	StringToIntegerDTO test1();
	
	List<TestStringToInteger> findByIdIn(Long... ids);

	List<TestStringToInteger> findByIdIn(Collection<Long> ids);

	@Query("select t1 from TestStringToInteger t1 where CONCAT(t1.id,'|',t1.number) in :strs")
	List<TestStringToInteger> testListIn(@Param("strs") List<String> strs);
	
	
	List<TestStringToInteger> findByNumberContains(String number);
	
	List<TestStringToInteger> findByNumberNotContains(String number);
	
	Long countByNumber(String number);
	
	List<TestStringToInteger> findByNumberStartingWith(String number);
	
	@Query("select t1 from TestStringToInteger t1 where t1.number like :number%")
	List<TestStringToInteger> findByNumberQuery(@Param("number") String number);
	
	List<TestStringToInteger> findByNumberInAndNumber0(List<String> numbers, String number0);
	
	@Query("select distinct t1.number from TestStringToInteger t1 where t1.number in :parentIds")
	List<String> checkHasChildrenParentId(@Param("parentIds") List<String> parentIds);
}
