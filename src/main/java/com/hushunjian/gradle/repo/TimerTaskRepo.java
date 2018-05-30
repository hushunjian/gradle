package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.TimerTask;

public interface TimerTaskRepo extends JpaRepository<TimerTask, Long>{

}
