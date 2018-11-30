package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.TaskV2Entity;

public interface TaskV2Repo extends JpaRepository<TaskV2Entity, Long>{

}
