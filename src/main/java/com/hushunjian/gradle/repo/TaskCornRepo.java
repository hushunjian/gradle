package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.TaskCorn;

public interface TaskCornRepo extends JpaRepository<TaskCorn, Long>{

	TaskCorn findByTaskName(String taskName);
}
