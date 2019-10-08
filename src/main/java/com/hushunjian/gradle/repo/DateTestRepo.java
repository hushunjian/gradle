package com.hushunjian.gradle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hushunjian.gradle.entity.DateTestEntity;


public interface DateTestRepo extends JpaRepository<DateTestEntity, Long> {

	@Query(value = "select t1 from DateTestEntity t1 where t1.id in :ids")
	List<DateTestEntity> findByIdIn(@Param("ids") Long... ids);
}
