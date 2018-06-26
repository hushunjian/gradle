package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.ProductionPlanEntity;

public interface ProductionPlanRepo extends JpaRepository<ProductionPlanEntity, Long> {

}
