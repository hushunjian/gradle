package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.ProgressPlanEntity;

public interface ProgressPlanRepo extends JpaRepository<ProgressPlanEntity,Long>{

}
