package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.ProgressPlanTaskEntity;

public interface ProgressPlanTaskRepo extends JpaRepository<ProgressPlanTaskEntity, Long> {

}
