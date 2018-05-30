package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.MeetingManage;

public interface MeetingManageRepo extends JpaRepository<MeetingManage, Long>{

}
