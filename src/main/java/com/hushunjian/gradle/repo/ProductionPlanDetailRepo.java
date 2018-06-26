package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hushunjian.gradle.entity.ProductionPlanDetailEntity;

public interface ProductionPlanDetailRepo extends JpaRepository<ProductionPlanDetailEntity, Long> {

	@Query("select max(t1.beamSpanNum) from ProductionPlanDetailEntity t1")
	Long getMaxBeamSpanNum();

}
