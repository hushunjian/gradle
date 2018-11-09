package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.TaskEntity;

public interface TaskRepo extends JpaRepository<TaskEntity,Long> {

}
